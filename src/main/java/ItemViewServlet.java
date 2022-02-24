
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ItemViewServlet")
public class ItemViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Global variables
	private static int itemId;
	private static int userId;

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	// Prepared SQL Statements to perform CRUD operations
	private static final String GET_ITEM_INFORMATION = "SELECT * FROM item WHERE Id = ?";
	// To get all reviews by item id
	private static final String GET_REVIEWS_BY_ITEM = "SELECT * FROM review WHERE itemId = ?";
	// To update the item information
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
				System.out.println(itemUserId);
				// we will be using the userId we get to check if we are actually the same user
				// who listed the item
				if (userId == itemUserId) {
					request.setAttribute("isListedUser", "true");
				} else {
					request.setAttribute("isListedUser", "false");
				}
				java.sql.Date itemDateListed = rs.getDate("datelisted");
				item = new Item(itemId, itemName, itemDescription, itemImage, itemPrice, itemQuantity, itemUserId,
						itemDateListed);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
			// Now that we have the itemId, we can now get it's reviews here
			List <Review> reviews = new ArrayList <>();
			 try (Connection connection = getConnection();
			 // Create a statement using connection object
			 PreparedStatement preparedStatement = 
			 connection.prepareStatement(GET_REVIEWS_BY_ITEM);) {
		     preparedStatement.setInt(1, itemId);
			 // Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Process the ResultSet object.
			 while (rs.next()) {
			 int id = rs.getInt("id");
			 int userId = rs.getInt("userId");
			 String displayName = rs.getString("displayName");
			 String content = rs.getString("content");
			 int itemId = rs.getInt("itemId");
			 String time = rs.getString("time");
			 reviews.add(new Review(id, userId, displayName, content, itemId, time));
			 }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("item", item);
		request.setAttribute("reviews", reviews);
		request.getRequestDispatcher("/ItemView.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		userId = Integer.parseInt(session.getAttribute("detailsId").toString()); // changed this to detailsId
																				 // from userAuthId

		// I need to get the following things:
		// the itemid via url params
		itemId = Integer.parseInt(request.getParameter("itemId"));
		// the item details based on itemid and reviews
		try {
			System.out.println("in the try block");
			getItemInformationAndReviews(request, response);
			System.out.println(userId);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	// The function to redirects to the user cart page after user adds an item to
	// their user cart
	protected void addCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		updateItem(request, response);
		response.sendRedirect("http://localhost:8090/devopsproject/UserCartServlet");

	}

	// To update the quantity of a selected item by user after adding to their user
	// cart
	private void updateItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

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
		
		// To validate the current quantity of the item before updating the current item
		if (quantity == 0) {
//			ITEM IS NOT UPDATED
		} else if(quantity < minusquantity) {
//			ITEM IS NOT UPDATED
		} else {
			
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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		// TODO Auto-generated method stub

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		// To get the current quantity of the item before adding the item
		Integer quantitytest = Integer.parseInt(request.getParameter("quantity"));

		// To get one item information after user add the item to their user cart
		String pricing = request.getParameter("pricing");

		// To get input quantity set by the current user from item quantity
		Integer additemquantity = Integer.parseInt(request.getParameter("additemquantityofuser"));
		
		if (quantitytest == 0) {
			request.setAttribute("alert","Item is out of stock!");
			getItemInformationAndReviews(request,response);
			return;
		} else if(quantitytest < additemquantity) {
			request.setAttribute("alert","Your reserved quantity is more than the current stock of this item!");
			getItemInformationAndReviews(request,response);
			return;
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");

				PreparedStatement ps = con.prepareStatement("INSERT into cart_item values(?,?,?,?,?)");
				PreparedStatement ps2 = con.prepareStatement("SELECT * FROM shopping_cart WHERE Id = ?");

				// To set the specified target shopping cart id of the current user logged in
				ps2.setInt(1, userId);

				ResultSet rs = ps2.executeQuery();

				ps.setInt(1, 0);
				ps.setInt(2, userId);
				ps.setInt(3, itemId);
				ps.setInt(4, additemquantity);
				ps.setString(5, pricing);

				// To get the shopping cart id by the current user logged in
				while (rs.next()) {
					rs.getInt(userId);
				}

				int i = ps.executeUpdate();

				if (i > 0) {
					addCartItem(request, response);
				}

			}

			catch (Exception exception) {
				System.out.println(exception);
				out.close();
			}

		}

	}

}
