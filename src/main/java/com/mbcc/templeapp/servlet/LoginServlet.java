package com.mbcc.templeapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("login") != null) {
			getServletContext().getRequestDispatcher("/WEB-INF/index.html").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("user");
		String password = request.getParameter("password");

		if (username == null || password == null) {
			getServletContext().getRequestDispatcher("/login.html").forward(request, response);
			return;
		}

		if (username.equals(System.getenv("username")) && password.equals(System.getenv("password"))) {
			request.getSession().setAttribute("login", "success");
			getServletContext().getRequestDispatcher("/WEB-INF/index.html").forward(request, response);
			return;
		}

		getServletContext().getRequestDispatcher("/login.html").forward(request, response);
	}
}
