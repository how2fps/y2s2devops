
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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		//Step 2: retrieve the four parameters from the request from the web form
		String fullName = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/devops", "how2fps", "l0l0ksql");
			 PreparedStatement ps = con.prepareStatement("insert into user_login_information values(?,?,?)");
			 ps.setInt(1, 0);
			 ps.setString(2, email);
			 ps.setString(3, password);
			 int i = ps.executeUpdate();
			 if (i > 0){
				 PrintWriter writer = response.getWriter();
				 writer.println("<h1>" + "You have successfully registered an account!" +
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