package com.hsbc.easset.controllers;
/**
 *
 * @author Sayan
 * @version 1.0
 * @createdOn 04 Oct 2020
 *
 *
 */
import java.io.IOException;
import java.io.PrintWriter;
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
import com.hsbc.easset.exceptions.DBConnCreationException;

/**
 * Servlet implementation class AddCategoryController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddCategoryController" })
public class AddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryController() {
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
		List<String> categoryData=new ArrayList<String>();
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
		try
		{
		while(enumeration.hasMoreElements())
		{
			parameterName=enumeration.nextElement().toString();
		    value=request.getParameter(parameterName);
		    categoryData.add(value);
		}
		String categoryName=categoryData.get(0).toString();
		int lendingPeriod=Integer.parseInt(categoryData.get(1).toString());
		int lateFees=Integer.parseInt(categoryData.get(2).toString());

        //create conn with bl
		EAssetBL eAssetBL= new EAssetBLImpl();


        try
        {
        	if(!eAssetBL.existsCategory(categoryName))
        		try
        		{
        			eAssetBL.addCategory(categoryName,lendingPeriod,lateFees);
        			out.println("Added Successfully...");
        			//direct to addasset page
        			request.getRequestDispatcher("add-asset.html").forward(request, response);
        		}
        		catch(DBConnCreationException exception)
            	{
        			out.println(exception.getMessage());
        			//redirect to addcategory page
        			request.getRequestDispatcher("add-category.html").include(request, response);
            	}
        	else
        	{
        		out.println("<p style='float:right;color:red'>Category already exists</p>");
        		request.getRequestDispatcher("add-category.html").include(request, response);
        	}
        }
        catch(DBConnCreationException exception)
        {
        	out.println(exception.getMessage());
        	request.getRequestDispatcher("add-category.html").include(request, response);
        }

		}
		catch(NullPointerException|InputMismatchException exception)
		{
			out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			request.getRequestDispatcher("add-category.html").include(request, response);
		}
		catch(Exception exception)
		{
			out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			request.getRequestDispatcher("add-category.html").include(request, response);
		}
	}

}