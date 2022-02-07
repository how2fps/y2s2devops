
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/EditItemServlet")
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Global itemId
	private static int globalItemId;

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	// Prepared SQL Statements to perform CRUD operations
	private static final String GET_ITEM_INFORMATION = "SELECT * FROM item WHERE Id = ?";
	private static final String UPDATE_ITEM_INFORMATION = "UPDATE item SET Name = ?, Description = ?, Image = ?, Pricing = ?, Quantity = ?, DateListed = ? WHERE Id = ?";

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

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// This is the itemId we get from the previous page.
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		System.out.println(request.getParameter("itemId"));
		globalItemId = itemId;

		Item existingItem = new Item(0, "", "", "", 0.0, 0, 0, new java.sql.Date(System.currentTimeMillis()));
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_INFORMATION);) {
			// we are passing in the Int in teh SQL query so that
			preparedStatement.setInt(1, itemId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				String itemName = rs.getString("name");
				String itemDescription = rs.getString("description");
				String itemImage = rs.getString("image");
				double itemPrice = rs.getDouble("pricing");
				int itemQuantity = rs.getInt("quantity");
				System.out.println(itemQuantity);
				int itemUserId = rs.getInt("userid");
				java.sql.Date itemDateListed = rs.getDate("datelisted");
				existingItem = new Item(itemId, itemName, itemDescription, itemImage, itemPrice, itemQuantity,
						itemUserId, itemDateListed);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("item", existingItem);
		request.getRequestDispatcher("/EditItemDetails.jsp").forward(request, response);
	}

	// The function to update the item
	private void updateItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// This is the itemId we get from the previous page.
//		int itemId = Integer.parseInt(request.getParameter("itemId"));
//		System.out.println(itemId);
		System.out.println(globalItemId);
		int itemId = globalItemId;

		// Step 1: Retrieve value from the request
		String newItemName = request.getParameter("itemName");
		String newItemDescription = request.getParameter("itemDescription");
		String newItemImage = request.getParameter("itemImage");
		double newItemPrice = Double.parseDouble(request.getParameter("itemPricing"));
		int newItemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
		java.sql.Date newItemDateListed = new java.sql.Date(System.currentTimeMillis());

		System.out.println(newItemName);
		System.out.println(newItemDescription);
		System.out.println(newItemImage);
//		System.out.println(newItemPrice);
//		System.out.println(newItemQuantity);
//		System.out.println(itemId);

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_INFORMATION);) {
			statement.setString(1, newItemName);
			statement.setString(2, newItemDescription);
			statement.setString(3, newItemImage);
			statement.setDouble(4, newItemPrice);
			statement.setInt(5, newItemQuantity);
			statement.setDate(6, newItemDateListed);
			statement.setInt(7, itemId);
			int i = statement.executeUpdate();

			System.out.println(newItemName);
			System.out.println(newItemDescription);
			System.out.println(newItemImage);
//			System.out.println(newItemPrice);
//			System.out.println(newItemQuantity);
//			System.out.println(itemId);
		}
		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("http://localhost:8081/devopsproject/ItemsListed.jsp");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditItemServlet() {
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
			System.out.println("in the try block");

			switch (action) {
			case "/EditItemServlet":
				showEditForm(request, response);
				break;
			case "/EditItemServlet/update":
				updateItem(request, response);
				break;
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
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
