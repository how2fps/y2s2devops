
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import javax.servlet.http.HttpSession;

@WebServlet("/ItemsListedServlet")
public class ItemsListedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemsListedServlet() {
		super();
	}

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

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

	// Prepared SQL Statements to perform CRUD operations
	private static final String GET_LISTED_ITEMS = "SELECT * FROM item WHERE UserId = ?";

	// The function to get all items that the user bought
	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("detailsId").toString());

		List<Item> itemsListedList = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LISTED_ITEMS);) {
			// sets the userId into the SQL statement
			preparedStatement.setInt(1, userId);
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int itemId = rs.getInt("id");
				String name = rs.getString("name");
				// we need to get the image here.
				String image = rs.getString("image");
				// we need to decode the image back to ISO in order to display it
				byte[] bytes = image.getBytes(StandardCharsets.ISO_8859_1);
				String isoimage = new String(bytes, StandardCharsets.ISO_8859_1);
				System.out.println(isoimage);

				double amountPaid = rs.getDouble("pricing");
				int quantity = rs.getInt("quantity");
				java.sql.Date date = rs.getDate("dateListed");
				itemsListedList.add(new Item(itemId, name, "", isoimage, amountPaid, quantity, userId, date));
			}
		} catch (SQLException e) {
			System.out.println(GET_LISTED_ITEMS);
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// ItemsBought.jsp
		System.out.println(Arrays.deepToString(itemsListedList.toArray()));
		request.setAttribute("itemsListedList", itemsListedList);
		request.getRequestDispatcher("/ItemsListed.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		try {
			System.out.println("in the try block");
			listItems(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
