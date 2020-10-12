package com.hsbc.easset.controllers;

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

import org.json.JSONArray;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;

/**
 * Servlet implementation class MessageController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/MessageController" })
public class OverdueMessagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverdueMessagesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int userId=Integer.parseInt(request.getParameter("userId"));
		try
		{
			//create conn with bl
			EAssetBL eAssetBL = new EAssetBLImpl();
			List<String> overdueMessagesList = new ArrayList<String>();
			//overdueMessagesList is an array of json strings where each string represents one json object//
			try
			{
				overdueMessagesList = eAssetBL.getOverdueMessages(userId);
				if(overdueMessagesList.size() > 0)
				{
					JSONArray json = new JSONArray(overdueMessagesList);
					System.out.println("returned overdue messages info");
					out.println(json);
				}
				else
				{
				 	out.print("No Overdue Messages Available ....");
				 	//redirect to employee home page
					request.getRequestDispatcher("employeeHome.html").include(request, response);
				}
			}
			catch(DBConnCreationException exception)
			{
				out.println(exception.getMessage());
				//redirect to employee home page
				request.getRequestDispatcher("employeeHome.html").include(request, response);
			}
		}
		catch(NullPointerException|InputMismatchException exception)
		{
			out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			request.getRequestDispatcher("employeeHome.html").include(request, response);
		}
		catch(Exception exception)
		{
			System.out.println("EXCEPTION:"+exception.getMessage());
			out.print(exception.getMessage());
			request.getRequestDispatcher("employeeHome.html").include(request, response);
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
		}
	}

}
