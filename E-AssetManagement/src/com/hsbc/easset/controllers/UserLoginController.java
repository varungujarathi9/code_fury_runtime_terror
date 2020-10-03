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
import java.util.InputMismatchException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.User;



/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginController" })
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
		EAssetDao eAssetDao=new EAssetDaoImpl();
		PrintWriter out=response.getWriter();
		HttpSession session=null;
	    response.setContentType("text/html");
		try
		{
		user.setName(request.getParameter("userName").toString());
		user.setPassword(request.getParameter("password").toString());

        try
        {
        	if(eAssetDao.validateLogin(user))
        	{
        		session=request.getSession(true);
        		session.setAttribute("userSession", user); //create user session
        		try
        		{
        			if(eAssetDao.isAdmin(user)) //if logged user is admin or burrower
        			{	//redirect to admin homepage
        			}
        			else
        			{
        				//redirect to burrower homepage
        			}
        		}
        		catch(DBConnCreationException exception)
        		{
        			out.println(exception.getMessage());
        			session.invalidate(); //close session
        			//redirect to login page
        			request.getRequestDispatcher("index.html").include(request, response);
        		}
        		catch(Exception exception)
        		{
        			out.println(exception.getMessage());
        			session.invalidate();
        			request.getRequestDispatcher("index.html").include(request, response);
        		}
        	}
        	else
        	{
        		out.println("<p style='float:right;color:red'>Login Credentials are not valid</p>");
        		request.getRequestDispatcher("index.html").include(request, response);
        	}
        }
        catch(DBConnCreationException exception)
        {
        	out.println(exception.getMessage());
        	request.getRequestDispatcher("index.html").include(request, response);

        }

		}
		catch(NullPointerException|InputMismatchException exception)
		{
			out.println(exception.getMessage());
		}
		catch(Exception exception)
		{
			out.println(exception.getMessage());
		}

	}


}
