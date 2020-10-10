package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.RoleType;
import com.hsbc.easset.models.User;

import org.json.*;

/**
 * Servlet implementation class BorrowerShowAssetController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/BorrowerShowAssetController" })
public class BorrowerShowAssetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerShowAssetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	System.out.println("Request received");
		//Enumeration<String> enumeration=request.getParameterNames();
	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //can be used later!!
		//String parameterName=null;
		//System.out.println("in controller");
		PrintWriter out=response.getWriter();
		int userid=Integer.parseInt(request.getParameter("userid"));
	//	System.out.println(userid);
	    response.setContentType("text/html");
		try
		{
			//while(enumeration.hasMoreElements())
			//{
			//	parameterName=enumeration.nextElement().toString();
			//    value=request.getParameter(parameterName);
			//    userData.add(value);
			//}

	        //create conn with dao
			EAssetDao eAssetDao = new EAssetDaoImpl();
			List<String> assetList = new ArrayList<String>();   
			//	 //assetList is an array of json strings where each string represents one json object//
			//assetList=[{obj1},{obj2},{obj3},{}]
			assetList = eAssetDao.showAssets(userid);
			if(assetList.size() > 0)
			{
				JSONArray json = new JSONArray(assetList);
				//out.println(assetList.size());
			//	out.println(json);
				
			
	System.out.println("returned assets info");
				response.getWriter().println(json);
				

			}
			else
			{
			 	response.getWriter().print("No Assets Available ....");
			 	// out.println("No Assets Available ....");
	         	// request.getRequestDispatcher("home.html").include(request, response);
			}
		}

		catch(Exception exception)
		{
			System.out.println("EXCEPTION:"+exception.getMessage());
			response.getWriter().print(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
		}

	}

}
