package com.hsbc.easset.controllers;
import org.json.simple.*;
import org.json.simple.parser.*;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;

/**
 * Servlet implementation class OverdueAssetsManaqgement
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/OverdueAssetsManaqgement" })
public class OverdueAssetsManaqgement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverdueAssetsManaqgement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EAssetBL eAssetBL=new EAssetBLImpl();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		JSONArray jsonarray = new JSONArray();
		try
		{
			jsonarray=eAssetBL.getOverdueAssets();
			out.println(jsonarray);
		}
		catch(DBConnCreationException|NullPointerException|InputMismatchException exception)
		{
			out.println(exception.getMessage());
			//redirect to admin home page
			request.getRequestDispatcher("adminHome.html").forward(request, response);
		}
		catch(Exception exception)
		{
			out.println(exception.getMessage());
			request.getRequestDispatcher("adminHome.html").forward(request, response);
		}
	}

}
