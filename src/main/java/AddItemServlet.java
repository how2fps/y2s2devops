
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// this is the upload directory where we will be uploading our files.
	private static final String UPLOAD_DIRECTORY = "image_upload";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

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
		PrintWriter out = response.getWriter();

		String itemImage = null;
		String itemName = null;
		String itemDescription = null;
		double itemPricing = 0.0;
		int itemQuantity = 0;

		HttpSession session = request.getSession();
		int itemUserId = Integer.parseInt(session.getAttribute("detailsId").toString()); // changed this to detailsId
																							// from userAuthId

		System.out.println(itemUserId);

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
						itemImage = filePath;
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
							itemName = fieldvalue;
						} else if (fieldname.equals("itemDescription")) {
							System.out.println("Im in itemDescription");
							// next logic goes here...
							itemDescription = fieldvalue;
						} else if (fieldname.equals("itemPricing")) {
							System.out.println("Im in itemPricing");
							// next logic goes here...
							itemPricing = Double.parseDouble(fieldvalue);
						} else if (fieldname.equals("itemQuantity")) {
							System.out.println("Im in itemQuantity");
							// next logic goes here...
							itemQuantity = Integer.parseInt(fieldvalue);
						}
					}
				}
			}
		} catch (Exception ex) {
			out.println(ex.getMessage());
		}

		System.out.println(itemImage);

		java.sql.Date itemDateListed = new java.sql.Date(System.currentTimeMillis()); // current DateTime in SQL Date

		// Attempt connection to database using JDBC.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "");

			PreparedStatement ps = con.prepareStatement("insert into ITEM values(?,?,?,?,?,?,?,?)");

			System.out.println(itemName);
			System.out.println(itemDescription);
			System.out.println(itemImage);
			System.out.println(itemPricing);
			System.out.println(itemQuantity);

			// This is the itemId that will auto-increment
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
				// I want to redirect to the ItemsListed Page.
				response.sendRedirect("http://localhost:8090/devopsproject/ItemsListedServlet");
			}
		}

		catch (Exception exception) {
			System.out.println(exception);
		}
	}

}
