
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ItemsBoughtServlet")
public class ItemsBoughtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	// Prepared SQL Statements to perform CRUD operations
	private static final String SELECT_ALL_ITEMS_BOUGHT = "SELECT * FROM transaction WHERE BuyingUserId = ?";

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

	// The function to get all items that the user bought
	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userAuthId").toString());

		List<Item> itemsBoughtList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BOUGHT);) {
			// sets the userId into the SQL statement
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int itemId = rs.getInt("itemid");
				String name = rs.getString("itemname");
				String image = rs.getString("itemimage");
				double amountPaid = rs.getDouble("amountdealt");
				System.out.println(amountPaid);
				int quantity = rs.getInt("quantity");
				System.out.println(quantity);
				java.sql.Date date = rs.getDate("date");
				itemsBoughtList.add(new Item(itemId, name, "", image, amountPaid, quantity, userId, date));
			}
		} catch (SQLException e) {
			System.out.println(SELECT_ALL_ITEMS_BOUGHT);
			System.out.println(e.getMessage());
		}
		System.out.println(Arrays.deepToString(itemsBoughtList.toArray()));
		request.setAttribute("itemsBoughtList", itemsBoughtList);
		request.getRequestDispatcher("/ItemsBought.jsp").forward(request, response);
	}

	public ItemsBoughtServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		try {
			System.out.println("in the try block");
			listItems(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
