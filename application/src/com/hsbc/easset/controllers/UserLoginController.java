/**
 *
 * @author Sayan
 * @version 1.0
 * @createdOn 03 Oct 2020
 *
 *
 */
package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.User;



/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserLoginController" })
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=new User();
		User loggedUser=new User();
		EAssetBL eAssetBL=new EAssetBLImpl();
		PrintWriter out=response.getWriter();
		HttpSession session=null;
		List<String> customerData=new ArrayList<String>();
		Enumeration<String> enumeration=request.getParameterNames();
		String parameterName=null;
		String value=null;
	    response.setContentType("text/html");

		while(enumeration.hasMoreElements())
		{
			parameterName=enumeration.nextElement().toString();
			value=request.getParameter(parameterName);
			customerData.add(value);
		}
		
		try
		{

			System.out.println(customerData);
			//received data from client and mapped to model class
			user.setUsername(customerData.get(0).toString());
			user.setPassword(customerData.get(1).toString());

        try
        {
        	if(eAssetBL.validateLogin(user))
        	{
        		System.out.println("Valid User");
        		try
        		{
        			loggedUser=eAssetBL.getUserInfo(user);
        			JSONObject json = new JSONObject(loggedUser);
        			if(eAssetBL.isAdmin(loggedUser.getUsername())) {
        				json.put("role","ADMIN");
        			}
        			else {
        				json.put("role","BORROWER");
        			}
        			System.out.println("Got user info "+json);
					out.println(json);
        		}
        		catch(DBConnCreationException exception)
        		{
        			//out.println(exception.getMessage());
        			//redirect to login page
        			exception.printStackTrace();
        			//request.getRequestDispatcher("login.html").include(request, response);
        			out.println(exception.getMessage());
        		}
//        		session=request.getSession(true);
        		//out.println("<p style='float:right;color:red'>Login Credentials are valid</p>");
        		//create user session
//        		try
//        		{
//        			System.out.println("Redirecting to home page");
//        			session.setAttribute("userSession", loggedUser);
        			
//					if(eAssetBL.isAdmin(user.getUsername())) //if logged user is admin or burrower
//					{	//direct to admin homepage
//						//request.getRequestDispatcher("adminHome.html").forward(request, response);
//						 
//						JSONObject json = new JSONObject(loggedUser);	
//						out.println(json);
//						out.print("ADMIN");
//					}
//					else
//					{
//						//direct to burrower homepage
//						//request.getRequestDispatcher("employeeHome.html").forward(request, response);
//						out.print("BORROWER");
//					}
//        		}
//        		catch(DBConnCreationException exception)
//        		{
//        			//out.println(exception.getMessage());
//        			session.invalidate(); //close session
//        			//redirect to login page
//
//        			exception.printStackTrace();
//        			out.println(exception.getMessage());
//      			request.getRequestDispatcher("login.html").include(request, response);
//        		}
        	}
        	else
        	{
        		//out.println("<p style='float:right;color:red'>Login Credentials are not valid</p>");
        		System.out.println("INVALID CREDS");
        		out.println("INVALID CREDENTIALS");
//        		request.getRequestDispatcher("login.html").include(request, response);
        	}
        }
        catch(DBConnCreationException exception)
        {
        	//out.println(exception.getMessage());

			exception.printStackTrace();
			out.println(exception.getMessage());
//        	request.getRequestDispatcher("login.html").include(request, response);

        }

		}
		catch(NullPointerException|InputMismatchException exception)
		{
			//out.println(exception.getMessage());
			exception.printStackTrace();
			out.println(exception.getMessage());
//			request.getRequestDispatcher("login.html").include(request, response);
		}
		catch(Exception exception)
		{
			//out.println(exception.getMessage());
			exception.printStackTrace();
			out.println(exception.getMessage());
//			request.getRequestDispatcher("login.html").include(request, response);
		}

	}


}