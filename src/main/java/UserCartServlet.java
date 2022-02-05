import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserCartServlet
 */
@WebServlet("/UserCartServlet")
public class UserCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String SELECT_CART_ITEM_BY_ID = "select Id,ShoppingCartId,ItemId,ItemAmount,TotalPrice from Cart_Item where Id =?";
	private static final String SELECT_ALL_CART_ITEMS = "select * from Cart_Item";
	private static final String DELETE_CART_ITEM_BY_ID = "delete from Cart_Item where Id = ?;";

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

	public UserCartServlet() {
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
			switch (action) {
			case "/edit":
				break;
			case "/update":
				break;
			default:
				listCartItems(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCartItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<UserCart> cartitems = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CART_ITEMS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String shoppingCartId = rs.getString("ShoppingCartId");
				String itemId = rs.getString("ItemId");
				String itemAmount = rs.getString("ItemAmount");
				String totalPrice = rs.getString("TotalPrice");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("listCartItems", cartitems);
		request.getRequestDispatcher("/UserCart.jsp").forward(request, response);
		
		RequestDispatcher view = request.getRequestDispatcher("/UserCart.jsp");      
        view.include(request, response);
	}

	public double getTotalCartPrice(List<UserCart> cartitems) {
		double sum = 0;

		try (Connection connection = getConnection()) {
			if (cartitems.size() > 0) {
				for (UserCart item : cartitems) {
					String query = "select ItemAmount from cart_item where Id=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, item.getId());
					ResultSet rs = preparedStatement.executeQuery();
					rs = preparedStatement.executeQuery();

					while (rs.next()) {
						sum += rs.getDouble("TotalPrice") * item.getItemAmount();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return sum;
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
