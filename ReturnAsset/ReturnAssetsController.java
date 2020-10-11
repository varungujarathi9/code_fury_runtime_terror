package com.hsbc.easset.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * Servlet implementation class ReturnAssetsController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ReturnAssetsController" })

public class ReturnAssetsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnAssetsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
			
		    response.setContentType("text/html");
		    PrintWriter out=response.getWriter();
			try
			{
			
		        //create conn with dao
				EAssetDao eAssetDao = new EAssetDaoImpl();	
				String assetSelected = request.getParameter("returnAsset");                 //getting the selected items to be return
				String assetSelectedToArray[] = assetSelected.split(",");                     //converting to arraylist
				List<String> assetToReturn = new ArrayList<String>();
				assetToReturn = Arrays.asList(assetSelectedToArray);
				out.println("Assets ready to return...");
				eAssetDao.returnAssets(assetToReturn);
				for(String str: assetToReturn)
				{
					   System.out.println(str);
				}
				if(assetToReturn.size() > 0)
				{
					JSONArray json = new JSONArray(assetToReturn);
					response.getWriter().println(json);
					

				}
				else
				{
				 	response.getWriter().print("No Assets to Return !!!");
		         	// request.getRequestDispatcher("home.html").include(request, response);
				}
			}

			catch(Exception exception)
			{
				System.out.println("EXCEPTION:"+exception.getMessage());
				response.getWriter().print(exception.getMessage());
				//response.sendError(response.SC_EXPECTATION_FAILED,"Data Error");
			}

		
	}


}
