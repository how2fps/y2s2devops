import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private static final String UPDATE_CART_ITEM_BY_ID = "UPDATE cart_item set cart_item.id = ?, shoppingCartId= ?, itemId =?, itemAmount =?, totalPrice =? WHERE id = ?;";
	private static final String DELETE_CART_ITEM_BY_ID = "DELETE FROM cart_item WHERE id = ?";
	private static final String DELETE_ALL_CART_ITEMS = "DELETE FROM cart_item";

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
				deleteAllCartItems(request, response);
				break;	
			case "/UserCartServlet/edit":
				addDeliveryChargeForm(request, response);
				break;
			case "/UserCartServlet/update":
				updateCartItem(request, response);
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
				cartItems.add(new UserCart(id, shoppingCartId, itemId, itemAmount, totalPrice, image, name));
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

	private void addDeliveryChargeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String id = request.getParameter("id");
		UpdateUserCart existingUserCart = new UpdateUserCart("", "", "", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_ITEM_BY_ID);) {
			preparedStatement.setString(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				String string = rs.getString("id");			
				String string2 = rs.getString("shoppingCartId");
				String string3 = rs.getString("itemId");
				String string4 = rs.getString("itemAmount");
				String string5 = rs.getString("totalPrice");
				existingUserCart = new UpdateUserCart(string, string2, string3, string4, string5);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUserCart to request and serve up the AddDeliveryCharge
		// form
		request.setAttribute("existingUserCart", existingUserCart);
		request.getRequestDispatcher("/AddDeliveryCharge.jsp").forward(request, response);
	}

	// method to update the cart_item table base on the form data
	private void updateCartItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// Step 1: Retrieve value from the request
		
//		String id = request.getParameter("id");			
//		String shoppingCartId = request.getParameter("shoppingCartId");
//		String itemId = request.getParameter("itemId");
//		String itemAmount = request.getParameter("itemAmount");
//		String totalPrice = request.getParameter("totalPrice");
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer shoppingCartId = Integer.parseInt(request.getParameter("shoppingCartId"));
		Integer itemId = Integer.parseInt(request.getParameter("itemId"));
		Integer itemAmount = Integer.parseInt(request.getParameter("itemAmount"));
		double totalPrice = Double.parseDouble("totalPrice");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CART_ITEM_BY_ID);) {
			
//			statement.setString(1, id);
//			statement.setString(2, shoppingCartId);
//			statement.setString(3, itemId);
//			statement.setString(4, itemAmount);
//			statement.setString(5, totalPrice);
			
			statement.setInt(1, id);
			statement.setInt(2, shoppingCartId);
			statement.setInt(3, itemId);
			statement.setInt(4, itemAmount);
			statement.setDouble(5, totalPrice);
			
			int i = statement.executeUpdate();
			
		}
		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("http://localhost:8090/devopsproject/UserCartServlet");
	}

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
	
	// The function to delete all cart items from user cart once user proceeded to check out
	private void deleteAllCartItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_CART_ITEMS);) {
			int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8090/devopsproject/UserTransaction.jsp");
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
