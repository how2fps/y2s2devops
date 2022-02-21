
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

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		int detailsId = (int) session.getAttribute("detailsId");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");
		PreparedStatement ps = con.prepareStatement("select * from user_details where Id = ?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, detailsId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int userLoginInfoId = rs.getInt("UserAuthId");
			PreparedStatement ps2 = con.prepareStatement("DELETE from user_login_information WHERE Id = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps2.setInt(1, userLoginInfoId);
			int check = ps2.executeUpdate();
			System.out.println(check);
			if (check==1) {
				session.invalidate();
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			deleteUser(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
