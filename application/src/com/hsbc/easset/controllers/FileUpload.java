package com.hsbc.easset.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      String path;
	  	PrintWriter out=response.getWriter();
	  	EAssetBL d1=new EAssetBLImpl();
		ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> multifiles=sf.parseRequest(request);
			String filepath="D:/";  //this file uploaded  on server will get stored on  D:/ of ur pc
			for(FileItem item:multifiles)
			{
				try {
					item.write(new File(filepath+item.getName()));  
					path=filepath+item.getName();///final path where file will get stored
					
					int count=d1.addImportUser(path);  //call dao layer
					//System.out.println(count);
					if(count==0)
					{
						//request.getRequestDispatcher("importUsers.html").include(request, response);
						//out.println("<h1>Phone Number or Email Id not valid in your data file.Also check all fields have to be mandatory.Please check your Users.json File</h1>");	
					}
					else if(count>0)
					{
					//request.getRequestDispatcher("importUsers.html").include(request, response);
					//out.println("<h1>The total number of users imported to database is</h1>"+count);
					}
				} catch (DBConnCreationException e) {
					// TODO Auto-generated catch block
					//request.getRequestDispatcher("importUsers.html").include(request, response);
					out.println("<h1>DB Conection Error</h1>");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					//request.getRequestDispatcher("importUsers.html").include(request, response);
					//out.println(e.getMessage());
					
				//System.out.println("file uploaded");
				}
				//System.out.println("file uploaded");
			}
			out.println("All users successfully added.....");
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//request.getRequestDispatcher("importUsers.html").include(request, response);
			out.println("<h1>File Upload errot</h1>");
		}
		
	
	
		
		
	}

}
