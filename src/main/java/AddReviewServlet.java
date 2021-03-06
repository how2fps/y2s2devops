

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// global item id variable
	private static int globalItemId;
       
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
		// itemId from itemView.jsp
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        globalItemId = itemId;
    	request.getRequestDispatcher("/AddReview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		
		 // Retrieve parameters from the request from the review form
		String content = request.getParameter("content");
		
		// These parameters can only be retrieved after user login
		HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("detailsId").toString());
        String displayName = session.getAttribute("displayName").toString();
        
        // Get item id from the global variable
        int itemReviewId = globalItemId;
		 
		 // Generate current Time parameter
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		 LocalDateTime now = LocalDateTime.now(); 
		 String time = dtf.format(now);
		
		// Attempt connection to database using JDBC
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/devops", "root", "");
			 
			// Implement the sql query using prepared statement 
			 PreparedStatement ps = con.prepareStatement("insert into REVIEW values(?,?,?,?,?,?)");
			 
			// Parsed in the data retrieved from the review form request into the prepared statement
			 ps.setInt(1, 0);
			 ps.setInt(2, userId);
			 ps.setString(3, displayName);
			 ps.setString(4, content);
			 ps.setInt(5, itemReviewId);
			 ps.setString(6, time);
			 
			// Perform the query on the database using the prepared statement
			 int i = ps.executeUpdate();
			 
			// Check if the query had been successfully executed and redirect
			 if (i > 0){
				 response.sendRedirect("http://localhost:8090/devopsproject/ItemsShopServlet");
			 }
		}
		// Catch and print out any exception
		catch (Exception exception) {
			 System.out.println(exception);
			 out.close();
		}	       
	}
}
