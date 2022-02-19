
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
import javax.servlet.http.HttpSession;

@WebServlet("/ItemViewServlet")
public class ItemViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Global itemId
	private static int itemId;
	private static int userId;

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	// Prepared SQL Statements to perform CRUD operations
	private static final String GET_ITEM_INFORMATION = "SELECT * FROM item WHERE Id = ?";
	// jing yan pls change this if it is wrong for you.
	private static final String GET_ITEM_REVIEWS = "SELECT * FROM review WHERE itemId = ?";

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

	public ItemViewServlet() {
		super();
	}

	private void getItemInformationAndReviews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Item item = new Item(0, "", "", "", 0.0, 0, 0, new java.sql.Date(System.currentTimeMillis()));
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
				// we will be using the userId we get to check if we are actually the same user
				// who listed the item
				if (itemUserId == userId) {
					boolean isListedUser = true;
				} else {
					boolean isListedUser = false;
				}
				java.sql.Date itemDateListed = rs.getDate("datelisted");
				item = new Item(itemId, itemName, itemDescription, itemImage, itemPrice, itemQuantity, itemUserId,
						itemDateListed);
			}
			// Now that we have the itemId, we can now get it's reviews here

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("item", item);
		request.getRequestDispatcher("/ItemView.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("detailsId").toString()); // changed this to detailsId
																						// from userAuthId
		String action = request.getServletPath();

		// I need to get the following things:
		// the itemid via url params
		itemId = Integer.parseInt(request.getParameter("itemId"));
		// the item details based on itemid and reviews
		try {
			System.out.println("in the try block");
			getItemInformationAndReviews(request, response);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
