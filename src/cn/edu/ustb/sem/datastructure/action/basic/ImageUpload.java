package cn.edu.ustb.sem.datastructure.action.basic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/ImageUpload")
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(ImageUpload.class);

	private String UPLOAD_DIRECTORY;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		UPLOAD_DIRECTORY = this.getServletContext().getRealPath("/")+"WebRoot/res/image";
//		UPLOAD_DIRECTORY = "D:/Files";
		
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		
		// process only if its multipart content
		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						String time = String.valueOf(System.currentTimeMillis());
						item.write(new File(UPLOAD_DIRECTORY + File.separator + time + name));
						String filePath = "/QHMS/WebRoot/res/image" + File.separator + time + name;
						HttpSession session = request.getSession(true);
						session.setAttribute("currentFilePath", filePath);
						jsonArray.add(filePath);
						logger.debug(filePath);
						json.element("success", true);
					}
				}
			} catch (Exception e) {
				System.out.println("File upload failed");
			}
		}
		json.element("ImageURLs", jsonArray);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
		logger.debug(json);
		out.close();
	}

}
