
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
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Prepared SQL Statements to perform CRUD operations
	private static final String INSERT_ITEM_SQL = "INSERT INTO item"
			+ " (id, name, description, image, pricing, userid, datelisted) VALUES " + " (?,?,?,?,?,?,?) ";

	// create a function to create a item
//	private void addItem(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//		// get parameter passed in the URL
//		String name = request.getParameter("name");
//		Date currentDate = new Date();
//		Item newItem = new Item(0, "", "", "", 0.0, 0, currentDate);
//		// Step 1: Establishing a Connection
//		try (Connection connection = getConnection();
//				// Step 2:Create a statement using connection object
//				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL);) {
//			preparedStatement.setString(1, name);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//			// Step 4: Process the ResultSet object
//			while (rs.next()) {
//				name = rs.getString("name");
//				String password = rs.getString("password");
//				String email = rs.getString("email");
//				String language = rs.getString("language");
//				// Idk what to pass in for id???? it cannot be null.
//				newItem = new Item(null, password, email, language);
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//		// Step 5: Set existingUser to request and serve up the userEdit form
//		request.setAttribute("item", newItem);
//		request.getRequestDispatcher("/AddItem.jsp").forward(request, response);
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// do a routing here to invoke the create function
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

//		String action = request.getServletPath();
//		try {
//			switch (action) {
//			case "/AddItemServlet":
//				addItem(request, response);
//				break;
//			}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		System.out.println("heyt his works");

		response.setContentType("text/html");
		// Step 1: Initialize a PrintWriter object to return the html values via the
		// response
		PrintWriter out = response.getWriter();
		// Step 2: retrieve the parameters from the request from the web form
		String itemImage = request.getParameter("itemImage");
		String itemName = request.getParameter("itemName");
		String itemDescription = request.getParameter("itemDescription");
		String itemPricing = request.getParameter("itemPricing");
		String itemQuantity = request.getParameter("itemQuantity");

		// Attempt connection to database using JDBC.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:", "3306", "");

			PreparedStatement ps = con.prepareStatement("insert into ITEM values(?,?,?,?)");

			ps.setString(1, itemImage);
			ps.setString(2, itemName);
			ps.setString(3, itemDescription);
			ps.setString(4, itemPricing);
			ps.setString(5, itemQuantity);

			int i = ps.executeUpdate();

			if (i > 0) {
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "You have successfully added a new Item!" + "</h1>");
				writer.close();
			}
		}

		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
	}

}
