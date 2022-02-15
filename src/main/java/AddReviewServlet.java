

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		
		 //Retrieve parameters from the request from the review form
		String comment = request.getParameter("comment");
		
		//These parameters can only be retrieved after user login
		 int userId = 1;
		 int itemId = 1;
		 
		 //Current Time parameter
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		 LocalDateTime now = LocalDateTime.now(); 
		 String time = dtf.format(now);
		
		//Attempt connection to database using JDBC
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/devopsproject", "root", "");
			 
			//Implement the sql query using prepared statement 
			 PreparedStatement ps = con.prepareStatement("insert into REVIEW values(?,?,?,?)");
			 
			//Parsed in the data retrieved from the review form request into the prepared statement
			 ps.setString(1, comment);
			 ps.setInt(2, userId);
			 ps.setInt(3, itemId);
			 ps.setString(4, time);
			 
			//Perform the query on the database using the prepared statement
			 int i = ps.executeUpdate();
			 
			//Check if the query had been successfully executed, return "You have successfully posted" via the response,
			 if (i > 0){
				 PrintWriter writer = response.getWriter();
				 writer.println("<h1>" + "You have successfully posted a Review!" + 
				 "</h1>");
				 writer.close();
			 } 
		}
		//Step 8: catch and print out any exception
		catch (Exception exception) {
			 System.out.println(exception);
			 out.close();
		}
        doGet(request, response);
        }		       
}
