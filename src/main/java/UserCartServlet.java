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

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_CART_ITEM_BY_ID = "SELECT cart_item.id, shoppingCartId, itemId, itemAmount, totalPrice FROM cart_item WHERE id = ?";
	private static final String SELECT_ALL_CART_ITEMS = "SELECT * FROM cart_item LEFT JOIN item ON cart_item.itemId = item.id";
	private static final String UPDATE_CART_ITEM_BY_ID = "UPDATE cart_item set id = ?, shoppingCartId = ?, itemId = ?, itemAmount = ?, totalPrice = ? WHERE id = ?;";
	private static final String DELETE_CART_ITEM_BY_ID = "DELETE FROM cart_item WHERE id = ?";
	private static final String DELETE_ALL_CART_ITEMS = "DELETE FROM cart_item";
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

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/UserCartServlet/delete":
				deleteCartItem(request, response);
				break;
			case "/UserCartServlet/wipe":
//				deleteAllCartItems(request, response);
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
		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CART_ITEMS);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer shoppingCartId = rs.getInt("shoppingCartId");
				Integer itemId = rs.getInt("itemId");
				Integer itemAmount = rs.getInt("itemAmount");
				double totalPrice = rs.getDouble("totalPrice");
				String image = rs.getString("image");
				String name = rs.getString("name");
				Integer quantity = rs.getInt("quantity");
				Integer userId = rs.getInt("userId");
				cartItems.add(new UserCart(id, shoppingCartId, itemId, itemAmount, totalPrice, image, name, quantity, userId));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("listCartItems", cartItems);
		request.getRequestDispatcher("/UserCart.jsp").forward(request, response);
	}

	// To compute the total amount of all items from user cart and to update the
	// total amount from user cart

//	public double getTotalCartPrice(List<UserCart> cartitems) {
//		double sum = 0;
//
//		try (Connection connection = getConnection()) {
//			if (cartitems.size() > 0) {
//				for (UserCart item : cartitems) {
//					String query = "select ItemAmount from cart_item where Id=?";
//					PreparedStatement preparedStatement = connection.prepareStatement(query);
//					preparedStatement.setInt(1, item.getId());
//					ResultSet rs = preparedStatement.executeQuery();
//					rs = preparedStatement.executeQuery();
//
//					while (rs.next()) {
//						sum += rs.getDouble("TotalPrice") * item.getItemAmount();
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return sum;
//	}

	// The function to delete one cart item from user cart
	private void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		Integer id = Integer.parseInt(request.getParameter("id"));
		// Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CART_ITEM_BY_ID);) {
			statement.setInt(1, id);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserCartServlet (note: remember to change the
		// url to your project name)
		response.sendRedirect("http://localhost:8090/devopsproject/UserCartServlet");
	}

	// The function to delete all cart items from user cart once user proceeded to
	// check out
	private void deleteAllCartItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_CART_ITEMS);) {
			int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8090/devopsproject/UserTransaction.jsp");
	}
	
	// The function to insert a transaction from user cart once user proceeded to
	// check out
	private void insertTransactionByUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		response.setContentType("text/html");

		String buyinguserid = request.getParameter("buyinguserid");
		String sellinguserid = request.getParameter("sellinguserid");
		String itemid = request.getParameter("itemid");
		String itemname = request.getParameter("name");
		String itemquantity = request.getParameter("quantity");
		String itemimage = request.getParameter("image");
		String totalamount = request.getParameter("totalamount");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);

		try {
			Connection connection = getConnection();
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");

			PreparedStatement ps = connection.prepareStatement(INSERT_ALL_CART_ITEMS_TO_TRANSACTION_BY_USER);

			ps.setInt(1, 0);
			ps.setString(2, buyinguserid);
			ps.setString(3, sellinguserid);
			ps.setString(4, itemid);
			ps.setString(5, itemname);
			ps.setString(6, itemimage);
			ps.setString(7, itemquantity);
			ps.setString(8, totalamount);
			ps.setString(9, time);

			int i = ps.executeUpdate();

			// to execute the deleteAllCartItems function
//			if (i > 0) {
//				deleteAllCartItems(request, response);
//			}

		}

		catch (Exception exception) {
			System.out.println(exception);
			System.out.close();
		}
		
//		deleteAllCartItems(request, response);
		
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
