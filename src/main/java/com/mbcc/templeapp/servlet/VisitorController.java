package com.mbcc.templeapp.servlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mbcc.templeapp.dao.VisitorDao;
import com.mbcc.templeapp.dto.Visitor;

/**
 * Servlet implementation class VisitorController
 */
public class VisitorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisitorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		try {
			if (method.equalsIgnoreCase("insert")) {
				Visitor visitor = new Visitor();
				visitor.setPhoneNumber(request.getParameter("phoneNumber"));
				visitor.setFirstName(request.getParameter("firstName"));
				visitor.setLastName(request.getParameter("lastName"));
				visitor.setMember(new Boolean(request.getParameter("member")));

				VisitorDao.insertVisitor(visitor);
				response.getWriter().append("Successfully inserted the details");
				return;
			}
			
			if(method.equalsIgnoreCase("list")){
				List<Visitor> existingVisitors = VisitorDao.retrieveVisitorsDataIfExists(request.getParameter("phoneNumber"));
				Gson gson = new Gson();
				response.setContentType("text/x-json;charset=UTF-8");           
		        response.setHeader("Cache-Control", "no-cache");
				response.getWriter().append(gson.toJson(existingVisitors));
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
