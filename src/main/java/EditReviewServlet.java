

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditReviewServlet
 */
@WebServlet("/EditReviewServlet")
public class EditReviewServlet extends HttpServlet {
	    
	    // Global variables
	    private static int globalReviewId;
	    private static int globalUserId;
	    private static int globalItemId;
	    private static String globalDisplayName;
	
	    // Prepared list of variables used for database connections
		private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
		private String jdbcUsername = "root";
		private String jdbcPassword = "";

		// Prepared list of SQL prepared statements to perform Retrieve and Update to our database
		private static final String SELECT_REVIEW_BY_ID = "select Id, UserId, DisplayName, Content, ItemId, Time from review where Id = ?";
		private static final String UPDATE_REVIEW_BY_ID = "update review set UserId = ?, DisplayName = ?, Content = ?, ItemId = ?, Time = ? where Id = ?;";

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
    public EditReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/EditReviewServlet":
				showEditForm(request, response);
				break;
			case "/EditReviewServlet/update":
				updateReview(request, response);
				break;
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	//method to get parameter, query database for existing review data and redirect to edit review page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException {
		
	// get parameter passed in the URL
	int id = Integer.parseInt(request.getParameter("id"));
	Review existingReview = new Review(0, 0, "", "", 0, "");
	
	// Establishing a Connection
	try (Connection connection = getConnection();
			
	// Create a statement using connection object
	PreparedStatement preparedStatement = 
	connection.prepareStatement(SELECT_REVIEW_BY_ID);) {
	preparedStatement.setInt(1, id);
	
	// Execute the query or update query
	ResultSet rs = preparedStatement.executeQuery();
	
	// Process the ResultSet object 
	while (rs.next()) {
	int userId = rs.getInt("userId");
	String displayName = rs.getString("displayName");
	String content = rs.getString("content");
	int itemId = rs.getInt("itemId");
	String time = rs.getString("time");
	
	// Pass the values to updateReview function
	 globalReviewId = id;
	 globalUserId = userId;
	 globalItemId = itemId;
	 globalDisplayName = displayName;
	existingReview = new Review(id, userId, displayName, content, itemId, time);
	}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	
	// Set existingReview to request and serve up the EditReview form
	request.setAttribute("review", existingReview);
	request.getRequestDispatcher("/EditReview.jsp").forward(request, response);
	}
	
	// Method to update the review table base on the form data
	private void updateReview(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
		
	// Retrieve values from the request and global variables
	int id = globalReviewId;
	 int userId = globalUserId;
	 String displayName = globalDisplayName;
	 String content = request.getParameter("content");
	 int itemId = globalItemId;
	 
	 // Generate time when user updates review
	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	 LocalDateTime now = LocalDateTime.now(); 
	 String time = dtf.format(now);
	 
	 // Attempt connection with database and execute update review SQL query
	 try (Connection connection = getConnection(); 
	 PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW_BY_ID);) {
	 statement.setInt(1, userId);
	 statement.setString(2, displayName);
	 statement.setString(3, content);
	 statement.setInt(4, itemId);
	 statement.setString(5, time);
	 statement.setInt(6, id);
	 int i = statement.executeUpdate();
	 }
	 
	 // Redirect back to ReviewsManagementServlet
	 response.sendRedirect("http://localhost:8090/devopsproject/ReviewsManagementServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
