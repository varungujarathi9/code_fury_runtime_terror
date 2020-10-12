/**
 *
 * @author Sayan, Madhura
 * @version 1.0
 * @createdOn 03 Oct 2020
 *
 *
 */
package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.Asset;


/**
 * Servlet implementation class AddAssetController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddAssetController" })
public class AddAssetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAssetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> enumeration=request.getParameterNames();
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //can be used later!!
			String parameterName=null;
			String value=null;
			Asset asset=new Asset();
			List<String> assetData=new ArrayList<String>();
			PrintWriter out=response.getWriter();
		    response.setContentType("text/html");
			try
			{
			while(enumeration.hasMoreElements())
			{
				parameterName=enumeration.nextElement().toString();
			    value=request.getParameter(parameterName);
			    System.out.println(value);
			    assetData.add(value);
			}
			asset.setName(assetData.get(0).toString());
			asset.setAssetType(assetData.get(1).toString());
			asset.setDescription(assetData.get(2).toString());

	        //create conn with bl
			System.out.println("Calling easset BL");
			EAssetBL eAssetBL= new EAssetBLImpl();
			

	        try
	        {
	        	System.out.println("eassetBL.Addasset(asset)");
	        	eAssetBL.addAsset(asset);
	        	System.out.println("eassetBL.Addasset(asset) RETURN");
	        	out.print("ASSET ADDED!!!");
	        	//out.println("Added Successfully..."+assetData.get(0)+assetData.get(1));
	        }
	        catch(DBConnCreationException exception)
	        {
	        	out.print(exception.getMessage());
//	        	out.println(exception.getMessage());
	        	//redirect to addasset page
	        	//request.getRequestDispatcher("addAsset.html").include(request, response);
	        }

			}
			catch(NullPointerException|InputMismatchException exception)
			{
				out.print(exception.getMessage());
				//out.println(exception.getMessage());
				//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
				//request.getRequestDispatcher("addAsset.html").include(request, response);
			}
			catch(Exception exception)
			{
				out.print(exception.getMessage());
//				out.println(exception.getMessage());
				//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
				//request.getRequestDispatcher("addAsset.html").include(request, response);
			}

	}

}