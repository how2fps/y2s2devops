
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
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");
			PreparedStatement ps = con.prepareStatement(
					"select id, password from user_login_information where email = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String hashedPassword = rs.getString("password");
				int userAuthId = rs.getInt("id");
				if (BCrypt.checkpw(password, hashedPassword)) {
					PreparedStatement ps2 = con.prepareStatement("select * from user_details where UserAuthId = ?",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps2.setInt(1, userAuthId);
					ResultSet rs2 = ps2.executeQuery();
					while (rs2.next()) {
						String displayName = rs2.getString("displayName");
						String phoneNumber = rs2.getString("phoneNumber");
						int id = rs2.getInt("id");
						HttpSession session = request.getSession();
						session.setAttribute("detailsId", id);
						session.setAttribute("displayName", displayName);
						session.setAttribute("phoneNumber", phoneNumber);
						session.setAttribute("email", email);
						request.setAttribute("alert", "Successful login!");
						request.getRequestDispatcher("/UserServlet").forward(request, response);
					}
				} else {
					request.setAttribute("alert", "Password is invalid!");
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("alert", "The email " + email + " is not registered!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}

		}
		// Step 8: catch and print out any exception
		catch (Exception exception) {
			request.setAttribute("alert", exception);
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		doGet(request, response);
	}

}
