
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void getUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String userDisplayName = session.getAttribute("userDisplayName").toString();
		String userPhoneNumber = session.getAttribute("userPhoneNumber").toString();
		String userEmail = session.getAttribute("userEmail").toString();
		int userAuthId = Integer.parseInt(session.getAttribute("userAuthId").toString());

		request.setAttribute("userDisplayName", userDisplayName);
		request.setAttribute("userPhoneNumber", userPhoneNumber);
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("userAuthId", userAuthId);
		request.getRequestDispatcher("/User.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getUser(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getUser(request, response);
	}

}
