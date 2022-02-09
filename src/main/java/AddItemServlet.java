
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddItemServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddItemServlet() {
		super();
	}

	// Prepared SQL Statements to perform CRUD operations
	private static final String INSERT_ITEM_SQL = "INSERT INTO item"
			+ " (id, name, description, image, pricing, userid, datelisted) VALUES " + " (?,?,?,?,?,?,?) ";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		HttpSession session = request.getSession();
		int itemUserId = Integer.parseInt(session.getAttribute("userAuthId").toString());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

//		if (ServletFileUpload.isMultipartContent(request)) {
//
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			factory.setSizeThreshold(123);
//			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setFileSizeMax(MAX_FILE_SIZE);
//			upload.setSizeMax(MAX_REQUEST_SIZE);
//			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//			File uploadDir = new File(uploadPath);
//			if (!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
//			// ...
//		}

		String itemImage = request.getParameter("itemImage");
		String itemName = request.getParameter("itemName");
		String itemDescription = request.getParameter("itemDescription");
		double itemPricing = Double.parseDouble(request.getParameter("itemPricing"));
		int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
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
//				PrintWriter writer = response.getWriter();
//				writer.println("<h1>" + "You have successfully added a new Item!" + "</h1>");
//				writer.close();
				// I want to redirect to the ItemsListed Page.
				request.getRequestDispatcher("/ItemsListedServlet").forward(request, response);
			}
		}

		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
	}

}
