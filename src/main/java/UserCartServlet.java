import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

/**
 * Servlet implementation class UserCartServlet
 */
@WebServlet("/UserCartServlet")
public class UserCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Global variable for Current User Logged In
	private static int currentuserloggedin;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_ALL_CART_ITEMS_BY_SHOPPINGCARTID = "SELECT * FROM cart_item LEFT JOIN item ON cart_item.itemId = item.id WHERE cart_item.shoppingCartId = ?";
	private static final String UPDATE_CART_ITEM_BY_ID = "UPDATE item set id = ?, name = ?, description = ?, image = ?, pricing = ?, quantity = ?, userId = ?, dateListed = ? WHERE id = ?;";
	private static final String DELETE_CART_ITEM_BY_ID = "DELETE FROM cart_item WHERE id = ?";
	private static final String DELETE_ALL_CART_ITEMS = "DELETE FROM cart_item WHERE shoppingCartId = ?";
	private static final String INSERT_ALL_CART_ITEMS_TO_TRANSACTION_BY_USER = "INSERT INTO transaction VALUES(?,?,?,?,?,?,?,?,?)";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public UserCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// To get the Id of current user logged in
		HttpSession session = request.getSession();
		currentuserloggedin = Integer.parseInt(session.getAttribute("detailsId").toString());

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/UserCartServlet/delete":
				deleteCartItem(request, response);
				updateItemFromCart(request, response);
				break;
			case "/UserCartServlet/wipe":
				deleteAllCartItems(request, response);
				insertTransactionByUser(request, response);
				break;
			case "/UserCartServlet":
				listCartItems(request, response);
				break;
			}
		} catch (SQLException ex) {

			throw new ServletException(ex);
		}

	}

	private void listCartItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<UserCart> cartItems = new ArrayList<>();
		Connection connection = getConnection();

		HttpSession session = request.getSession();
		Integer currentuserId = (Integer) (session.getAttribute("detailsId"));
		PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM shopping_cart WHERE UserId = ?");
		preparedStatement2.setInt(1, currentuserId);
		ResultSet rs2 = preparedStatement2.executeQuery();
		while (rs2.next()) {
			Integer shoppingCartId = rs2.getInt("Id");
			System.out.println(shoppingCartId);
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cart_item LEFT JOIN item ON cart_item.itemId = item.id WHERE cart_item.shoppingCartId = ?");
			preparedStatement.setInt(1, shoppingCartId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer itemId = rs.getInt("itemId");
				Integer itemAmount = rs.getInt("itemAmount");
				double totalPrice = rs.getDouble("totalPrice");
				String image = rs.getString("image");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Integer pricing = rs.getInt("pricing");
				Integer quantity = rs.getInt("quantity");
				Integer userId = rs.getInt("userId");
				String dateListed = rs.getString("dateListed");
				System.out.println(shoppingCartId);
				// To check the current user logged in about their cart items belongs to their shopping cart
				request.setAttribute("isShoppingCartUser", "true");
				request.setAttribute("currentUserLoggedInShoppingCart", shoppingCartId);
				cartItems.add(new UserCart(id, shoppingCartId, itemId, itemAmount, totalPrice, name, description, image,
						pricing, quantity, userId, dateListed));

			}
			request.setAttribute("listCartItems", cartItems);
			request.getRequestDispatcher("/UserCart.jsp").forward(request, response);
		}

	}

	// To retrieve and update the quantity of a selected item reserved by user after
	// removing from their shopping cart
	private void updateItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String oriId = request.getParameter("oriId");
		String id = request.getParameter("id");
		String itemname = request.getParameter("itemname");
		String itemdescription = request.getParameter("itemdescription");
		String itemimage = request.getParameter("itemimage");
		String itempricing = request.getParameter("itempricing");
		Integer itemquantity = Integer.parseInt(request.getParameter("itemquantity"));
		String itemuserId = request.getParameter("itemuserId");
		String itemdateListed = request.getParameter("itemdateListed");
		Integer plusquantity = Integer.parseInt(request.getParameter("currentstockitemquantity"));

		Integer calculatedquantity = Math.addExact(itemquantity, plusquantity);

		String resultquantity = String.valueOf(calculatedquantity);

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE item set id = ?, name = ?, description = ?, image = ?, pricing = ?, quantity = ?, userId = ?, dateListed = ? WHERE id = ?;");) {

			statement.setString(1, id);
			statement.setString(2, itemname);
			statement.setString(3, itemdescription);
			statement.setString(4, itemimage);
			statement.setString(5, itempricing);
			statement.setString(6, resultquantity);
			statement.setString(7, itemuserId);
			statement.setString(8, itemdateListed);
			statement.setString(9, oriId);

			int i = statement.executeUpdate();

		}

	}

	// The function to delete one cart item from user cart
	private void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CART_ITEM_BY_ID);) {
			statement.setInt(1, id);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect to UserCartServlet
		response.sendRedirect("http://localhost:8090/devopsproject/UserCartServlet");
	}

	// The function to delete all cart items from user cart once user proceeded to
	// check out
	private void deleteAllCartItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id = request.getParameter("shoppingcartid");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_CART_ITEMS);) {
			statement.setString(1, id);
			int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8090/devopsproject/UserTransaction.jsp");
	}

	// The function to insert a transaction from user cart once user proceeded to
	// check out
	private void insertTransactionByUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		HttpSession session = request.getSession();
		Integer shoppingCartId = (Integer) (session.getAttribute("detailsId"));

		response.setContentType("text/html");

		String ItemBuyingUserId2 = " ";
		String ItemSellingUserId3 = " ";
		String ItemId4 = " ";
		String ItemName5 = " ";
		String ItemImage6 = " ";
		String ItemQuantity7 = " ";
		String ItemTotalAmount8 = " ";

		// To get all the parameter values as of all cart items from current user login
		String sellinguserid[] = request.getParameterValues("sellinguserid");
		String itemid[] = request.getParameterValues("itemid");
		String itemname[] = request.getParameterValues("itemname");
		String itemimage[] = request.getParameterValues("itemimage");
		String itemquantity[] = request.getParameterValues("itemquantity");
		String totalamount[] = request.getParameterValues("totalamount");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String transactiontime = dtf.format(now);

			try {
				request.setAttribute("currentUserLoggedInShoppingCart", shoppingCartId);
				Connection connection = getConnection();

				PreparedStatement ps = connection.prepareStatement(INSERT_ALL_CART_ITEMS_TO_TRANSACTION_BY_USER);

				for (int i = 0; i < sellinguserid.length; i++) {
						
						ItemSellingUserId3 = sellinguserid[i];
						ItemId4 = itemid[i];
						ItemName5 = itemname[i];
						ItemImage6 = itemimage[i];
						ItemQuantity7 = itemquantity[i];
						ItemTotalAmount8 = totalamount[i];

				ps.setInt(1, 0);
				ps.setInt(2, shoppingCartId);
				ps.setString(3, ItemSellingUserId3);
				ps.setString(4, ItemId4);
				ps.setString(5, ItemName5);
				ps.setString(6, ItemImage6);
				ps.setString(7, ItemQuantity7);
				ps.setString(8, ItemTotalAmount8);
				ps.setString(9, transactiontime);

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception exception) {
			System.out.println(exception);
			System.out.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
