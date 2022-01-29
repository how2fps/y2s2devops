
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	private String jdbcURL = "jdbc:mysql://localhost:3306/itemsbought";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// SQL Command Variables
	private static final String SELECT_ALL_ITEMS_BOUGHT = "select * from UserDetails ";

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
		List<Item> itemsList = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BOUGHT);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int itemId = rs.getInt("userid");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String image = rs.getString("image");
				double pricing = rs.getDouble("pricing");
				int userId = rs.getInt("userid");
				Date dateListed = rs.getDate("dateListed");
				itemsList.add(new Item(itemId, name, description, image, pricing, userId, dateListed));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// ItemsBought.jsp
		request.setAttribute("listItems", itemsList);
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/read":
				break;
			default:
				listItems(request, response);
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
