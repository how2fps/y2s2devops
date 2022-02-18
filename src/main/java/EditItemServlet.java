
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/EditItemServlet")
@MultipartConfig
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// this is the upload directory where we will be uploading our files.
	private static final String UPLOAD_DIRECTORY = "image_upload";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	// Global itemId
	private static int globalItemId;

	// Database Connection Variables
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	public EditItemServlet() {
		super();
	}

	// Prepared SQL Statements to perform CRUD operations
	private static final String GET_ITEM_INFORMATION = "SELECT * FROM item WHERE Id = ?";
	private static final String UPDATE_ITEM_INFORMATION = "UPDATE item SET Name = ?, Description = ?, Image = ?, Pricing = ?, Quantity = ?, DateListed = ? WHERE Id = ?";

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

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// This is the itemId we get from the previous page.
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		System.out.println(request.getParameter("itemId"));
		globalItemId = itemId;

		Item existingItem = new Item(0, "", "", "", 0.0, 0, 0, new java.sql.Date(System.currentTimeMillis()));
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_INFORMATION);) {
			// we are passing in the Int in teh SQL query so that
			preparedStatement.setInt(1, itemId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				String itemName = rs.getString("name");
				String itemDescription = rs.getString("description");
				String itemImage = rs.getString("image");
				double itemPrice = rs.getDouble("pricing");
				int itemQuantity = rs.getInt("quantity");
				System.out.println(itemQuantity);
				int itemUserId = rs.getInt("userid");
				java.sql.Date itemDateListed = rs.getDate("datelisted");
				existingItem = new Item(itemId, itemName, itemDescription, itemImage, itemPrice, itemQuantity,
						itemUserId, itemDateListed);
			}

			// We also need to check for the userId from Session if its the correct user.
			// If not the same user, then we simply return unauthorised page

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("item", existingItem);
		request.getRequestDispatcher("/EditItemDetails.jsp").forward(request, response);
	}

	// The function to update the item
	private void updateItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		System.out.println(globalItemId);
		int itemId = globalItemId;

		String newItemImage = null;
		String newItemName = null;
		String newItemDescription = null;
		double newItemPricing = 0.0;
		int newItemQuantity = 0;

		response.setContentType("text/html");

		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
			PrintWriter writer = response.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			String fileName1 = "";
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						fileName1 += fileName;
						String filePath = uploadPath + File.separator + fileName;
						newItemImage = filePath;
						File storeFile = new File(filePath);
						// saves the file on disk
						item.write(storeFile);
					} else {
						// here...
						String fieldname = item.getFieldName();
						String fieldvalue = item.getString();
						if (fieldname.equals("itemName")) {
							System.out.println("Im in itemName");
							// logic goes here...
							newItemName = fieldvalue;
						} else if (fieldname.equals("itemDescription")) {
							System.out.println("Im in itemDescription");
							// next logic goes here...
							newItemDescription = fieldvalue;
						} else if (fieldname.equals("itemPricing")) {
							System.out.println("Im in itemPricing");
							// next logic goes here...
							newItemPricing = Double.parseDouble(fieldvalue);
						} else if (fieldname.equals("itemQuantity")) {
							System.out.println("Im in itemQuantity");
							// next logic goes here...
							newItemQuantity = Integer.parseInt(fieldvalue);
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		java.sql.Date newItemDateListed = new java.sql.Date(System.currentTimeMillis());

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_INFORMATION);) {
			statement.setString(1, newItemName);
			statement.setString(2, newItemDescription);
			statement.setString(3, newItemImage);
			statement.setDouble(4, newItemPricing);
			statement.setInt(5, newItemQuantity);
			statement.setDate(6, newItemDateListed);
			statement.setInt(7, itemId);
			int i = statement.executeUpdate();

		}
		response.sendRedirect("http://localhost:8090/devopsproject/ItemsListedServlet");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();

		try {
			System.out.println("in the try block");

			switch (action) {
			case "/EditItemServlet":
				showEditForm(request, response);
				break;
			case "/EditItemServlet/update":
				updateItem(request, response);
				break;
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
