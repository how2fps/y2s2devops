import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsShopServlet
 */
@WebServlet("/ItemsShopServlet")
public class ItemsShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_ITEM_BY_ID = "SELECT id, name, description, image, pricing, quantity, userId, dateListed FROM item WHERE id =?";
	private static final String SELECT_ALL_ITEMS_LISTED = "SELECT * FROM item";
	private static final String UPDATE_ITEM_BY_ID = "UPDATE item set id = ?, name = ?, description = ?, image = ?, pricing = ?, quantity = ?, userId = ?, dateListed = ? WHERE id = ?;";

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

	public ItemsShopServlet() {
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
			listItems(request, response);

		} 
		catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	// The function to redirects to the user cart page after user adds an item to their user cart
	protected void addCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		updateItem(request, response);
		String action = request.getServletPath();
		response.sendRedirect("http://localhost:8090/devopsproject/UserCartServlet");

	}

	// The function to get all items listed to the items shop
	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ItemsShop> itemsShopList = new ArrayList<>();
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_LISTED);) {
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String image = rs.getString("image");
				double pricing = rs.getDouble("pricing");
				Integer quantity = rs.getInt("quantity");
				Integer userId = rs.getInt("userId");
				java.sql.Date dateListed = rs.getDate("dateListed");
				itemsShopList.add(new ItemsShop(id, name, description, image, pricing, quantity, userId, dateListed));
			}

		} catch (SQLException e) {
			System.out.println(SELECT_ALL_ITEMS_LISTED);
			System.out.println(e.getMessage());
		}
		System.out.println(Arrays.deepToString(itemsShopList.toArray()));
		request.setAttribute("itemsShopList", itemsShopList);
		request.getRequestDispatcher("/ItemsShop.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	// To update the quantity of a selected item by user after adding to their user cart
	private void updateItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// Step 1: Retrieve value from the request

		String oriId = request.getParameter("oriId");
		String id = request.getParameter("id");
		String name = request.getParameter("name");			
		String description = request.getParameter("description");
		String image = request.getParameter("image");
		String pricing = request.getParameter("pricing");
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		String userId = request.getParameter("userId");
		String dateListed = request.getParameter("dateListed");
		
		Integer minusquantity = Integer.parseInt(request.getParameter("additemquantityofuser"));
		
		Integer calculatedquantity = Math.subtractExact(quantity, minusquantity);
		
		String resultquantity = String.valueOf(calculatedquantity);
		
		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_BY_ID);) {

			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, description);
			statement.setString(4, image);
			statement.setString(5, pricing);
			statement.setString(6, resultquantity);
			statement.setString(7, userId);
			statement.setString(8, dateListed);
			statement.setString(9, oriId);

			int i = statement.executeUpdate();

		}
		
	}
	
	// the function to add cart item by user to their user cart
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		//To get user id of the current user logged in
		Integer shoppingcartid = Integer.parseInt(request.getParameter("shoppingcartid"));
		
		//To get the current quantity of item
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//To get one item information after user add the item to their user cart
		Integer sellinguserid = Integer.parseInt(request.getParameter("sellinguserid"));
		Integer itemid = Integer.parseInt(request.getParameter("itemid"));
		String pricing = request.getParameter("pricing");
		
		//To get input quantity set by the current user from item quantity
		Integer additemquantity = Integer.parseInt(request.getParameter("additemquantityofuser"));

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");
			
			PreparedStatement ps = con.prepareStatement("INSERT into cart_item values(?,?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("INSERT into shopping_cart values(?,?)");
			
			ps.setInt(1, 0);
			ps.setInt(2, sellinguserid);
			ps.setInt(3, itemid);
			ps.setInt(4, additemquantity);
			ps.setString(5, pricing);
			
			// to insert the current user of their shopping cart id to shopping_cart table for key relationships
			ps2.setInt(1, 0);
			ps2.setInt(2, shoppingcartid);

			int i = ps.executeUpdate();
			int i2 = ps2.executeUpdate();

			// to execute the addCartItem function
			if (i > 0 && i2 > 0) {
				addCartItem(request, response);
			}

		}

		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}

	}

}
