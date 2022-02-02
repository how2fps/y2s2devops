
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String displayName = request.getParameter("displayName");
		String phoneNumber = request.getParameter("phoneNumber");
		
		if (password.length() < 8) {
			request.setAttribute("alert", "Password needs to have at least 8 characters!");
			request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
		}
		if (!password.equals(confirmPassword)) {
			request.setAttribute("alert", "Passwords are not the same!");
			request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
		}
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/devops", "how2fps", "l0l0ksql");
			 PreparedStatement ps = con.prepareStatement("insert into user_login_information values(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			 ps.setInt(1, 0);
			 ps.setString(2, email);
			 ps.setString(3, password);
			 int i = ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
		        if (rs.next()) {
		            int userId = rs.getInt(1);
		            if (i > 0){
						 PreparedStatement ps2 = con.prepareStatement("insert into user_details values(?,?,?,?)");
						 ps2.setInt(1, 0);
						 ps2.setString(2, displayName);
						 ps2.setString(3, phoneNumber);
						 ps2.setInt(4, userId);
						 int x = ps2.executeUpdate();
						 if (x > 0){
							request.setAttribute("alert", "Registration Successful!");
							request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
						 }
					 }
		        }
			 
		}
		//Step 8: catch and print out any exception
		catch (Exception exception) {
		 if(exception.toString().contains("Duplicate entry")) {
			 request.setAttribute("alert", email + "already in use!");
			 request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
		 }
		 else 
		 {
			 request.setAttribute("alert", exception);
			 request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
		 }
		}
		doGet(request, response);
	}
}