package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.easset.dao.EAssetDao;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Request received");
		Enumeration<String> enumeration=request.getParameterNames();
	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //can be used later!!
		String parameterName=null;
		String value=null;
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
			    userData.add(value);
			}
			user.setName(userData.get(0).toString());
			user.setTelphoneNumber(Long.parseLong(userData.get(1).toString()));
			user.setEmailId(userData.get(2).toString());
			user.setUsername(userData.get(3).toString());
			user.setPassword(userData.get(4).toString());
			user.setRole(RoleType.BORROWER);
	
	     //   user.setDob(LocalDate.parse(customerData.get(2).toString(),
	       // 		formatter));
	
	        //out.println("Registered Successfully...");
	        //create conn with dao
			EAssetDao eAssetDao= new EAssetDaoImpl();
	
	
	        if(eAssetDao.addUser(user))
	        {
	        	out.println("Registered Successfully...");
	        	request.getRequestDispatcher("login.html").forward(request, response);
			}
        //System.out.println(user.getName());

    //    else
      //  	request.getRequestDispatcher("index.html").include(request, response);

		}

		catch(Exception exception)
		{
			out.println(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
		}

	}

}
