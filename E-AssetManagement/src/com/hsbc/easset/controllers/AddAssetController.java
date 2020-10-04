/**
 * 
 * @author Sayan
 * @version 1.0
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //can be used later!!
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
			    assetData.add(value);
			}		
			asset.setName(assetData.get(0).toString());
			asset.setAssetType(assetData.get(1).toString());
			asset.setDescription(assetData.get(2).toString());
			asset.setDateAdded(LocalDate.parse(assetData.get(3).toString(),formatter));
			asset.setAvailable(Boolean.parseBoolean(assetData.get(4).toString()));
	       
	        //create conn with dao
			EAssetDao eAssetDao= new EAssetDaoImpl();
			
	   
	        try
	        {
	        	if(eAssetDao.addAsset(asset))
	        		out.println("Added Successfully...");
	        	else
	        	{
	        		//redirect to addasset page
	        	}
	        }
	        catch(DBConnCreationException exception)
	        {
	        	out.println(exception.getMessage());
	        	//redirect to addasset page
	        }	
			
			}
			catch(NullPointerException|InputMismatchException exception)
			{
				out.println(exception.getMessage());
				//out.println(exception.getMessage());
				//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			}
			catch(Exception exception)
			{
				out.println(exception.getMessage());
				//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			}
			
	}

}
