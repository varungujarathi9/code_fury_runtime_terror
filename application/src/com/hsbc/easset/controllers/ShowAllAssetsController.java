package com.hsbc.easset.controllers;

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

import com.hsbc.easset.bl.EAssetBL;
import com.hsbc.easset.bl.EAssetBLImpl;
import com.hsbc.easset.models.Asset;

/**
 * Servlet implementation class ShowAllAssets
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ShowAllAssetsController" })
public class ShowAllAssetsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllAssetsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
		try
		{		
	        //create conn with dao
			EAssetBL eAssetBL=new EAssetBLImpl();
			List<Asset> assetList=new ArrayList<>();
			assetList = eAssetBL.showAllAssets();
			JSONArray json = new JSONArray(assetList);
			out.print(json);
		}

		catch(Exception exception)
		{
			exception.printStackTrace();
			System.out.println("EXCEPTION:"+exception.getMessage());
			out.print(exception.getMessage());
			//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
		}
	}

}
