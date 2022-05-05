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

import com.isi.dao.AdminDAO;
import com.isi.dao.CategoryDAO;
import com.isi.dao.ProductDAO;
import com.isi.data.Admin;
import com.isi.data.Category;
import com.isi.data.Product;


@WebServlet("/BikesControllerServlet")
public class BikesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDAO bikesDbUtil;
	private CategoryDAO categoryDbUtil;
	private AdminDAO adminDAO;
	
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
    		adminDAO = new AdminDAO(dataSource);
    	}
    	catch (Exception e) {
			throw new ServletException(e);
		}
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			String theCommand = request.getParameter("command");
			if (theCommand == null) 
				theCommand = "INDEX";
			switch (theCommand) 
			{
				case "INDEX": index(request, response); break;
				case "CATEGORIES": productsByCategory(request, response); break;
				case "PRODUCT": productById(request, response); break;
				default: index(request, response); 
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			String theCommand = request.getParameter("command");
			if (theCommand == null) 
				theCommand = "INDEX";
			switch (theCommand) 
			{
				case "LOGIN": login(request, response); break;	
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			throw new ServletException(e);
		}
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
	
	private void productsByCategory(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int farmId = Integer.parseInt(request.getParameter("categoryId"));
		List<Product> productsbyCategory = bikesDbUtil.getAllProductsByCategory(farmId);
		
		request.setAttribute("PRODUCT_LIST", productsbyCategory);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/category.jsp");
		dispatcher.forward(request, response);
	}
	private void productById(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int productId = Integer.parseInt(request.getParameter("productId"));
		Product product = bikesDbUtil.getProductById(productId);
		request.setAttribute("product", product);
		System.out.println(product.getId());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product.jsp");
		dispatcher.forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(adminDAO.authenticateAdmin(username, password)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
			request.removeAttribute("username");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			request.setAttribute("error", true);
			request.removeAttribute("password");
			dispatcher.forward(request, response);
		}
	}

}
