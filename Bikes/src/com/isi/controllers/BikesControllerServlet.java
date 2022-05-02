package com.isi.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.isi.dao.CategoryDAO;
import com.isi.dao.ProductDAO;
import com.isi.data.Category;
import com.isi.data.Product;

/**
 * Servlet implementation class BikesControllerServlet
 */
@WebServlet("/BikesControllerServlet")
public class BikesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDAO bikesDbUtil;
	private CategoryDAO categoryDbUtil;
	
	@Resource(name="jdbc/bikes")
    private DataSource dataSource;
	
	@Override
	
    public void init() throws ServletException
    {
    	super.init();
    	try
    	{
    		bikesDbUtil = new ProductDAO(dataSource);
    		categoryDbUtil = new CategoryDAO(dataSource);
    	}
    	catch (Exception e) {
			throw new ServletException(e);
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			String theCommand = request.getParameter("command");
			if (theCommand == null) 
				theCommand = "INDEX";
			switch (theCommand) 
			{
				case "INDEX": index(request, response); break;
				case "LOGIN": login(request, response); break;			
				default: index(request, response);
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void index(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<Product> productsMostSold = bikesDbUtil.getMostSoldProductsList();	
		List<Category> categoriesList = categoryDbUtil.getAllCategoriesList();
		
		request.setAttribute("TOP3_LIST", productsMostSold);
		request.setAttribute("CATEGORIES_LIST", categoriesList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
		dispatcher.forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	}

}
