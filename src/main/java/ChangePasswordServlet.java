
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");
		if (!newPassword.equals(confirmNewPassword)) {
			request.setAttribute("alert", "New password and confirm new password is not the same!");
			request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		int detailsId = Integer.parseInt(session.getAttribute("detailsId").toString());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");
			PreparedStatement ps = con.prepareStatement("select UserAuthId from user_details where Id = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, detailsId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int userAuthId = rs.getInt("UserAuthId");
				PreparedStatement ps2 = con.prepareStatement("select password from user_login_information where Id = ?",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps2.setInt(1, userAuthId);
				ResultSet rs2 = ps2.executeQuery();
				if (rs2.next()) {
					String oldHashedPassword = rs2.getString("Password");
					if (BCrypt.checkpw(oldPassword, oldHashedPassword)) {
						String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
						PreparedStatement ps3 = con.prepareStatement(
								"UPDATE user_login_information SET Password = ? WHERE Id = ?",
								PreparedStatement.RETURN_GENERATED_KEYS);
						ps3.setString(1, newHashedPassword);
						ps3.setInt(2, userAuthId);
						ps3.executeUpdate();
						request.setAttribute("alert", "Password successfully changed!");
						request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
					} else {
						request.setAttribute("alert", "Old password is wrong!");
						request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
						return;
					}
				}
			}
		} catch (Exception exception) {
			request.setAttribute("alert", exception);
			request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
		}
	}

}
