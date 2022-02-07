
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsBoughtServlet
 */
@WebServlet("/ItemsBoughtServlet")
public class ItemsBoughtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	// Need to get the userId here to add to our SQL Statement String

	// Prepared SQL Statements to perform CRUD operations
	private static final String SELECT_ALL_ITEMS_BOUGHT = "SELECT * FROM transaction WHERE BuyingUserId = ?";

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

	// The function to get all items that the user bought
	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// This is the itemId we get from the previous page.
		int userId = Integer.parseInt(request.getParameter("userId"));

		List<Item> itemsBoughtList = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BOUGHT);) {
			// sets the userId into the SQL statement
			preparedStatement.setInt(1, userId);
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int itemId = rs.getInt("itemid");
				String name = rs.getString("itemname");
				String image = rs.getString("itemimage");
				double amountPaid = rs.getDouble("amountdealt");
				System.out.println(amountPaid);
				int quantity = rs.getInt("quantity");
				System.out.println(quantity);
				java.sql.Date date = rs.getDate("date");
				itemsBoughtList.add(new Item(itemId, name, "", image, amountPaid, quantity, userId, date));
			}
		} catch (SQLException e) {
			System.out.println(SELECT_ALL_ITEMS_BOUGHT);
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// ItemsBought.jsp
		System.out.println(Arrays.deepToString(itemsBoughtList.toArray()));
		request.setAttribute("itemsBoughtList", itemsBoughtList);
		request.getRequestDispatcher("/ItemsBought.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsBoughtServlet() {
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
			listItems(request, response);
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
