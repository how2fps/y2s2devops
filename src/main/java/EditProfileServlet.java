
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

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
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
		HttpSession session = request.getSession();
		String userDisplayName = session.getAttribute("displayName").toString();
		String userPhoneNumber = session.getAttribute("phoneNumber").toString();
		String userEmail = session.getAttribute("email").toString();
		int detailsId = Integer.parseInt(session.getAttribute("detailsId").toString());

		request.setAttribute("displayName", userDisplayName);
		request.setAttribute("phoneNumber", userPhoneNumber);
		request.setAttribute("email", userEmail);
		request.setAttribute("detailsId", detailsId);
		request.getRequestDispatcher("/EditProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String displayName = request.getParameter("displayName");
		String phoneNumber = request.getParameter("phoneNumber");
		int detailsId = (int) session.getAttribute("detailsId");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");
			PreparedStatement ps = con.prepareStatement(
					"UPDATE user_details SET DisplayName = ?, PhoneNumber = ? WHERE Id = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, displayName);
			ps.setString(2, phoneNumber);
			ps.setInt(3, detailsId);
			int check = ps.executeUpdate();
			System.out.println(check);
			if (check == 1) {
				PreparedStatement ps2 = con.prepareStatement("select * from user_details where Id = ?",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps2.setInt(1, detailsId);
				ResultSet rs2 = ps2.executeQuery();
				
				if (rs2.next()) {
					System.out.println(rs2);
					int userLoginInfoId = rs2.getInt("UserAuthId");
					System.out.println(userLoginInfoId);
					PreparedStatement ps3 = con.prepareStatement(
							"UPDATE user_login_information SET Email = ? WHERE Id = ?",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps3.setString(1, email);
					ps3.setInt(2, userLoginInfoId);
					int check2 = ps3.executeUpdate();
					if (check2 == 1) {
						session.setAttribute("displayName", displayName);
						session.setAttribute("email", email);
						session.setAttribute("phoneNumber", phoneNumber);
						request.setAttribute("displayName", displayName);
						request.setAttribute("email", email);
						request.setAttribute("phoneNumber", phoneNumber);
						request.setAttribute("detailsId", detailsId);
						request.getRequestDispatcher("/UserServlet").forward(request, response);
					}
				}

			}

		}
		// Step 8: catch and print out any exception
		catch (Exception exception) {
			if (exception.toString().contains("Duplicate entry") && exception.toString().contains("Email")) {
				request.setAttribute("alert", "The email " + email + " is already in use!");
				request.getRequestDispatcher("/EditProfile.jsp").forward(request, response);
			} else if (exception.toString().contains("Duplicate entry")
					&& exception.toString().contains("DisplayName")) {
				request.setAttribute("alert", "The display name " + displayName + " is already in use!");
				request.getRequestDispatcher("/EditProfile.jsp").forward(request, response);
			} else {
				request.setAttribute("alert", exception);
				request.getRequestDispatcher("/UserServlet").forward(request, response);
			}
		}
	}

}
