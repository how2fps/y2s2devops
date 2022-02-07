
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		response.setContentType("text/html");
		// Initialize a PrintWriter object to return the html values via the
		// response
		PrintWriter out = response.getWriter();
		// Retrieve the parameters from the request from the web form
		String itemImage = request.getParameter("itemImage");
		String itemName = request.getParameter("itemName");
		String itemDescription = request.getParameter("itemDescription");
		double itemPricing = Double.parseDouble(request.getParameter("itemPricing"));
		int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));

		// Fill up item parameters with those that are not in the WebForm
		int itemUserId = 1;// gotta get this from user after login
		// int itemId =
		java.sql.Date itemDateListed = new java.sql.Date(System.currentTimeMillis()); // current DateTime in SQL Date

		// Attempt connection to database using JDBC.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");

			PreparedStatement ps = con.prepareStatement("insert into ITEM values(?,?,?,?,?,?,?,?)");

			// This is the userid
			ps.setInt(1, 0);
			ps.setString(2, itemName);
			ps.setString(3, itemDescription);
			ps.setString(4, itemImage);
			ps.setDouble(5, itemPricing);
			ps.setInt(6, itemQuantity);
			ps.setInt(7, itemUserId);
			ps.setDate(8, itemDateListed);

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
