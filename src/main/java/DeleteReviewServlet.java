

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet("/DeleteReviewServlet")
public class DeleteReviewServlet extends HttpServlet {
	
	// Prepared list of variables used for database connections
		private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
		private String jdbcUsername = "root";
		private String jdbcPassword = "";

		// Prepared list of SQL prepared statements to perform Delete from database
		private static final String DELETE_REVIEW_BY_ID = "delete from review where Id = ?";

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
    public DeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			deleteUser(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
			//Retrieve review id
			int id = Integer.parseInt(request.getParameter("id"));
			
			//Attempt connection with database and execute delete review SQL query
			try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW_BY_ID);) {
			statement.setInt(1, id);
			int i = statement.executeUpdate();
			 }
			 //Redirect back to ReviewsManagement.jsp
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
