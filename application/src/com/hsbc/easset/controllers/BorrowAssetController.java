package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.models.Asset;

/**
 * Servlet implementation class BorrowAssetController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/BorrowAssetController" })
public class BorrowAssetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowAssetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> enumeration=request.getParameterNames();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //can be used later!!
		boolean status=false;
		String parameterName=null;
		String value=null;
		int userId=0;
		int assetId=0;
		
		List<String> data=new ArrayList<String>();
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");

		while(enumeration.hasMoreElements())
		{
			parameterName=enumeration.nextElement().toString();
		    value=request.getParameter(parameterName);
		    data.add(value);
		}
		
		assetId=Integer.parseInt(data.get(0).toString());
		userId=Integer.parseInt(data.get(1).toString());
        //create conn with bl
		System.out.println("Calling EAssetBL from BorrowAssetController");
		EAssetBL eAssetBL=new EAssetBLImpl();
		try {
			status=eAssetBL.borrowAssets(assetId,userId);
			
			if(status) {
				System.out.println("1");
				out.println("1");
			}
			else {
				System.out.println("0");
				out.println("0");
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
