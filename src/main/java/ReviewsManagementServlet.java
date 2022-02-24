
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReviewsManagementServlet
 */
@WebServlet("/ReviewsManagementServlet")
public class ReviewsManagementServlet extends HttpServlet {

	// Prepared list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	// Prepared list of SQL prepared statements to perform Retrieve from database
	private static final String SELECT_REVIEWS_BY_USER = "select * from review where UserId =?";

	// Implement the getConnection method to facilitate connection to the database
	// via JDBC
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

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewsManagementServlet() {
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
		try {
			listReviews(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	// listItems function to connect to the database and retrieve all items listed
	// by
	// listItems function to connect to the database and retrieve all items listed
	// by
	private void listReviews(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("detailsId").toString());

		List<Review> reviews = new ArrayList<>();
		try (Connection connection = getConnection();

				// Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEWS_BY_USER);) {

			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String displayName = rs.getString("displayName");
				String content = rs.getString("content");
				int itemId = rs.getInt("itemId");
				String time = rs.getString("time");
				reviews.add(new Review(id, userId, displayName, content, itemId, time));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Set the items list into the listItems attribute to be pass to the
		// ReviewsManagement.jsp
		request.setAttribute("listReviews", reviews);
		request.getRequestDispatcher("/ReviewsManagement.jsp").forward(request, response);
	}

	// listItems function to connect to the database and retrieve all items listed by 
	private void listReviews(HttpServletRequest request, HttpServletResponse response) 
	  throws SQLException, IOException, ServletException {
		
	  HttpSession session = request.getSession();
	  int userId = Integer.parseInt(session.getAttribute("detailsId").toString());
		
	  List <Review> reviews = new ArrayList <>();
	  try (Connection connection = getConnection();
			   
	   // Create a statement using connection object
	  PreparedStatement preparedStatement = 
	  connection.prepareStatement(SELECT_REVIEWS_BY_USER);) {
	  preparedStatement.setInt(1, userId);
		   
	   // Execute the query or update query
	   ResultSet rs = preparedStatement.executeQuery();
	   
	   // Process the ResultSet object.
	   while (rs.next()) {
	   int id = rs.getInt("id");
	   String displayName = rs.getString("displayName");
	   String content = rs.getString("content");
	   int itemId = rs.getInt("itemId");
	   String time = rs.getString("time");
	   reviews.add(new Review(id, userId, displayName, content, itemId, time));
	   }
	   } catch (SQLException e) {
	   System.out.println(e.getMessage());
	   }
	   
	  // Set the items list into the listItems attribute to be pass to the ReviewsManagement.jsp
	  request.setAttribute("listReviews", reviews);
	  request.getRequestDispatcher("/ReviewsManagement.jsp").forward(request, response);
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
