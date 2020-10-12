package com.hsbc.easset.controllers;
import org.json.*;
import org.json.simple.parser.*;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * Servlet implementation class GetCategoryListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ShowCategoriesController" })
public class ShowCategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCategoriesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			//create conn with bl
			EAssetBL eAssetBL = new EAssetBLImpl();
			List<String> categoryList = new ArrayList<String>();
			//categoryList is an array of json strings where each string represents one json object//
			try
			{
				categoryList = eAssetBL.getCategoryList();
				if(categoryList.size() > 0)
				{
					JSONArray json = new JSONArray(categoryList);
					System.out.println("returned category list");
					out.print(json);
				}
				else
				{
				 	out.print("No Category Available ....");
					//request.getRequestDispatcher("adminHome.html").forward(request, response);
				}
			}
			catch(DBConnCreationException exception)
			{
				out.println(exception.getMessage());
				//request.getRequestDispatcher("adminHome.html").forward(request, response);
			}
		}
		catch(NullPointerException|InputMismatchException exception)
		{
			out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			//request.getRequestDispatcher("adminHome.html").forward(request, response);
		}
		catch(Exception exception)
		{
			System.out.println("EXCEPTION:"+exception.getMessage());
			out.println(exception.getMessage());
			//request.getRequestDispatcher("adminHome.html").forward(request, response);
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
		}
	}

}
