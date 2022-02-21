import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsShopServlet
 */
@WebServlet("/ItemsShopServlet")
public class ItemsShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_ALL_ITEMS_LISTED = "SELECT * FROM item";

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

	public ItemsShopServlet() {
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

		String action = request.getServletPath();

		try {
			listItems(request, response);

		} 
		catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	// The function to get all items listed to the items shop
	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ItemsShop> itemsShopList = new ArrayList<>();
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_LISTED);) {
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String image = rs.getString("image");
				double pricing = rs.getDouble("pricing");
				Integer quantity = rs.getInt("quantity");
				Integer userId = rs.getInt("userId");
				java.sql.Date dateListed = rs.getDate("dateListed");
				itemsShopList.add(new ItemsShop(id, name, description, image, pricing, quantity, userId, dateListed));
			}

		} catch (SQLException e) {
			System.out.println(SELECT_ALL_ITEMS_LISTED);
			System.out.println(e.getMessage());
		}
		System.out.println(Arrays.deepToString(itemsShopList.toArray()));
		request.setAttribute("itemsShopList", itemsShopList);
		request.getRequestDispatcher("/ItemsShop.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
