package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.RoleType;
import com.hsbc.easset.models.User;

import org.json.*;

/**
 * Servlet implementation class BorrowerShowAssetController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ShowAssetController" })
public class ShowAssetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAssetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Enumeration<String> enumeration=request.getParameterNames();
		String value=null;
		String parameterName=null;
		List<Asset> assetList=new ArrayList<>();
		List<String> userData=new ArrayList<>();
//		Integer userId=Integer.parseInt(request.getParameter("userId"));
		int userId = 0;
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
		try
		{
			while(enumeration.hasMoreElements())
			{
				parameterName=enumeration.nextElement().toString();
				System.out.println(parameterName);
			    value=request.getParameter(parameterName);
			    userData.add(value);
			}
			System.out.println("*****************************"+userData);
			userId=Integer.parseInt(userData.get(0).toString());
//			userId=1;
			
	        //create conn with dao
			EAssetBL eAssetBL=new EAssetBLImpl();
			assetList = eAssetBL.showAvailableAssets(userId);
			
			if(assetList.size()>0)
			{
				JSONArray json = new JSONArray(assetList);	
	
				response.getWriter().println(json);
				

			}
			else
			{
			 	response.getWriter().print("Sorry! You cannot buy more assets");
			 	//out.println("Sorry you have been banned");
			 	System.out.println("Sorry! You cannot buy more assets");
	         	// request.getRequestDispatcher("home.html").include(request, response);
			}
		}

		catch(Exception exception)
		{
			exception.printStackTrace();
			System.out.println("EXCEPTION:"+exception.getMessage());
			response.getWriter().print(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
		}

	}

}