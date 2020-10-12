package com.hsbc.easset.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.Borrower;

/**
* Servlet implementation class BorrowerShowAssetsController
*/
@WebServlet(asyncSupported = true, urlPatterns = { "/BorrowerShowAssetsController" })
public class BorrowerShowAssetsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#HttpServlet()
	*/
	public BorrowerShowAssetsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub





		Enumeration<String>  enumeration=request.getParameterNames();
		//  DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//can be used later!!
		String parameterName=null;
		System.out.println("in controller");
		PrintWriter out=response.getWriter();
//		String id=request.getParameter("userId"); 
		String id = "1";
		String value=null;
		List<String> userData=new ArrayList<String>();
		response.setContentType("text/html");
		try {
			while(enumeration.hasMoreElements())
			{
				parameterName=enumeration.nextElement().toString();

				value=request.getParameter(parameterName);
				userData.add(value);
			}
			
			id=userData.get(0).toString();

			//create conn with dao
			EAssetDao eAssetDao = new EAssetDaoImpl();
			List<Borrower> assetList = new ArrayList<Borrower>();
			// //assetList is an arrayof json strings where each string represents one json object//
			//assetList=[{obj1},{obj2},{obj3},{}]
			assetList = eAssetDao.showBorrowedAssets(id);
			if(assetList.size() > 0)
			{
				JSONArray json = new JSONArray(assetList);
				//out.println(assetList.size()); // out.println(json);


				System.out.println("returned assets info");
				out.println(json);


			}
			else {
				out.println("<div class=\"card bg-danger text-white\"><div class=\"card-body\">No assets borrowed</div></div>"); //
				//out.println("No Assets Available ...."); //
				//request.getRequestDispatcher("home.html").include(request, response);
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
			System.out.println("EXCEPTION:"+exception.getMessage());
			out.print("<div class=\"card bg-danger text-white\"><div class=\"card-body\">"+exception.getMessage()+"</div></div>");
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");

		}
				//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
