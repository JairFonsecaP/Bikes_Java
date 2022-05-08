package com.isi.controllers;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import javax.sql.DataSource;

import com.isi.dao.AdminDAO;
import com.isi.dao.BrandDAO;
import com.isi.dao.CategoryDAO;
import com.isi.dao.OrderDAO;
import com.isi.dao.ProductDAO;
import com.isi.data.Brand;
import com.isi.data.Category;
import com.isi.data.Product;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO adminDAO;
	private ProductDAO productDAO;
	private BrandDAO brandDAO;
	private CategoryDAO categoryDAO;
	private OrderDAO orderDAO;
	private String logged;
	RequestDispatcher homeDispatcher;

	@Resource(name = "jdbc/bikes")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		try {
			adminDAO = new AdminDAO(dataSource);
			productDAO = new ProductDAO(dataSource);
			brandDAO = new BrandDAO(dataSource);
			categoryDAO = new CategoryDAO(dataSource);
			orderDAO = new OrderDAO(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie tempCookie : cookies) {
				System.out.println("cookie:" + tempCookie.getName());
				if ("logged".equals(tempCookie.getName())) {
					logged = tempCookie.getValue();
				}
			}
		}
		System.out.println("final logged value: " + logged);
		try {
			if (logged != null) {
				orchestAction(request, response);

			} else {
				if (adminDAO.authenticateAdmin(username, password)) {

					Cookie isLogged = new Cookie("logged", "true");
					isLogged.setMaxAge(60 * 60 * 24);
					response.addCookie(isLogged);

					RequestDispatcher homeDispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
					String nombre = adminDAO.getAdminByUsername(username).getName();
					request.setAttribute("name", nombre);
					homeDispatcher.forward(request, response);

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
					request.setAttribute("error", true);
					dispatcher.forward(request, response);
				}
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void orchestAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		homeDispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("logged", logged);

		String command = request.getParameter("command");
		if (command == null)
			command = "HOME";
		switch (command) {
		case "HOME":
			homeDispatcher.forward(request, response);
			break;
		case "LIST_PRODUCTS":
			listProducts(request, response);
			break;
		case "UPDATE":
			redirectToUpdate(request, response);
			break;
		case "UPDATE_PRODUCT":
			updateProduct(request, response);
			break;
		case "ADD_FORM":
			redirectToAddProduct(request, response);
			break;
		case "ADD_PRODUCT":
			createProduct(request, response);
			break;
		case "LIST_ORDERS":
			listOrders(request, response);
			break;
		case "DELETE":
			deleteProduct(request, response);
			break;
		case "LOGOUT":
			logout(request, response);
			break;
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie isLogged = new Cookie("logged", "false");
			isLogged.setMaxAge(0);
			response.addCookie(isLogged);
			request.setAttribute("command", "INDEX");
			request.setAttribute("logged", logged);
			response.sendRedirect("/Bikes/BikesControllerServlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listOrders(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher productsDispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
			request.setAttribute("command", "List_Orders");
			request.setAttribute("ordersList", orderDAO.getOrdersList());
			int totalSales = orderDAO.getTotalSales();
			request.setAttribute("totalSales", (totalSales >= 0 ? totalSales : "Error getting total sales") );
			request.setAttribute("name", request.getParameter("name"));	
			productsDispatcher.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void createProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		try {

			request.setAttribute("name", request.getParameter("name"));	
			String productName = request.getParameter("nameProduct");
			String productDesciption = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String image = request.getParameter("image");
			int brandId = Integer.parseInt(request.getParameter("brandSelected"));
			int categoryId = Integer.parseInt(request.getParameter("categorySelected"));

			Product product = new Product(productName, productDesciption, price, stock, image,
					new Brand(brandId), new Category(categoryId));

			productDAO.addProduct(product);
			listProducts(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void redirectToAddProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher productsDispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
			request.setAttribute("command", "add_Product");
			request.setAttribute("listBrands", brandDAO.getBrands());
			request.setAttribute("listCategories", categoryDAO.getAllCategoriesList());
			request.setAttribute("name", request.getParameter("name"));
			productsDispatcher.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			request.setAttribute("name", request.getParameter("name"));
			productDAO.deleteProduct(productId);
			listProducts(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		try {

			request.setAttribute("name", request.getParameter("name"));	
			int productId = Integer.parseInt(request.getParameter("productId"));
			String productName = request.getParameter("nameProduct");
			String productDesciption = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String image = request.getParameter("image");
			int brandId = Integer.parseInt(request.getParameter("brandSelected"));
			int categoryId = Integer.parseInt(request.getParameter("categorySelected"));

			Product product = new Product(productId, productName, productDesciption, price, stock, 0, image,
					new Brand(brandId), new Category(categoryId));

			productDAO.updateProduct(product);
			listProducts(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void redirectToUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher productsDispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
			request.setAttribute("command", "update_Product");
			int productId = Integer.parseInt(request.getParameter("productId"));
			request.setAttribute("product", productDAO.getProductById(productId));
			request.setAttribute("listBrands", brandDAO.getBrands());
			request.setAttribute("listCategories", categoryDAO.getAllCategoriesList());
			request.setAttribute("name", request.getParameter("name"));
			productsDispatcher.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) {

		try {
			RequestDispatcher productsDispatcher = request.getRequestDispatcher("/views/adminHome.jsp");
			request.setAttribute("command", "List_Products");
			request.setAttribute("productsList", productDAO.getAllProductsList());
			request.setAttribute("name", request.getParameter("name"));	
			productsDispatcher.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
