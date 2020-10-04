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
import javax.servlet.http.HttpSession;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.models.User;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
   EAssetDao d1=new EAssetDaoImpl(); 
		Enumeration<String> enumeration=request.getParameterNames();
	String parameterName=null;
	String value=null;
	DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	User user=new User();
	List<String> customerData=new ArrayList();
	//response sending back to client page
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			//creation of session
			HttpSession session=null;
			
			
			while(enumeration.hasMoreElements())
			{
				parameterName=enumeration.nextElement().toString();
				value=request.getParameter(parameterName);
				customerData.add(value);
				
				
			}
				//System.out.println(customerData);
				
				user.setUsername(customerData.get(0).toString());
				user.setPassword(customerData.get(1).toString());
				if(d1.validateLogin(user))
				{
					System.out.println("Successfull Login for the user has been established");
					out.println("Successfull Login for the user has been established");
					//from here it hovers to the Home Page (User)//
					
					
				
				}
				else
				{
					System.out.println("Credentials entered were wrong--Check your username and  password--");
				}
	}

}
