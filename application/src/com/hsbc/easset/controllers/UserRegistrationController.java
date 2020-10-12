package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
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
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.exceptions.InvalidEmailIdException;
import com.hsbc.easset.exceptions.InvalidTelephoneNumberException;
import com.hsbc.easset.exceptions.PasswordMismatchException;
import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.dao.*;
import com.hsbc.easset.models.RoleType;
import com.hsbc.easset.models.User;

/**
 * Servlet implementation class UserRegistrationController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserRegistrationController" })
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Request received");
		Enumeration<String> enumeration=request.getParameterNames();
	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //can be used later!!
		String parameterName=null;
		String value=null;
		String confirmPassword=null;
		User user=new User();
		List<String> userData=new ArrayList<String>();
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
		try
		{
			while(enumeration.hasMoreElements())
			{
				parameterName=enumeration.nextElement().toString();
			    value=request.getParameter(parameterName);
			    System.out.println(value);
			    userData.add(value);
			}
			user.setName(userData.get(0).toString());
			user.setTelphoneNumber(Long.parseLong(userData.get(1).toString()));
			user.setEmailId(userData.get(2).toString());
			user.setUsername(userData.get(3).toString());
			user.setPassword(userData.get(4).toString());
			confirmPassword=userData.get(5).toString();
			user.setRole(RoleType.BORROWER);
			//user.setDob(LocalDate.parse(customerData.get(2).toString(),formatter));
	        //create conn with bl
			EAssetBL eAssetBL= new EAssetBLImpl();
			try
			{
				eAssetBL.addUser(user,confirmPassword);
	        	//out.println("Registered Successfully...");
	        	//direct to login page
	        	//request.getRequestDispatcher("login.html").forward(request, response);
				out.print("1");
			}
			catch(DBConnCreationException|SQLIntegrityConstraintViolationException|InvalidEmailIdException|InvalidTelephoneNumberException|PasswordMismatchException exception)
			{
				//out.println(exception.getMessage());
				//redirect to registration page
				//request.getRequestDispatcher("index.html").include(request, response);
				out.print(exception.getMessage());
			}

		}
		catch(NullPointerException|InputMismatchException exception)
		{
			//out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			//request.getRequestDispatcher("index.html").include(request, response);]
			out.print(exception.getMessage());
		}
		catch(Exception exception)
		{
			//out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
//			request.getRequestDispatcher("index.html").include(request, response);
			out.print(exception.getMessage());
		}

	}

}