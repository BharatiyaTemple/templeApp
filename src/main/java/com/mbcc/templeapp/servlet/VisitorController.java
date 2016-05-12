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
import com.mbcc.templeapp.dao.VisitorMySqlDao;
import com.mbcc.templeapp.dao.VisitorLog;
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
				Visitor visitor = insertVisitorLog(request, response);
				VisitorMySqlDao.insertVisitor(visitor);
				return;
			}
			
			if (method.equalsIgnoreCase("insertLog")) {
				insertVisitorLog(request, response);
				return;
			}
			
			if(method.equalsIgnoreCase("list")){
				List<Visitor> existingVisitors = VisitorMySqlDao.retrieveVisitorsDataIfExists(request.getParameter("phoneNumber"));
				Gson gson = new Gson();
				
		        if(existingVisitors.size() == 1){
		        	response.getWriter().append("Logged user");
		        	VisitorMySqlDao.insertVisitorLog(existingVisitors.get(0));
		        }
		        else {
		        	response.setContentType("text/x-json;charset=UTF-8");           
			        response.setHeader("Cache-Control", "no-cache");
		        	response.getWriter().append(gson.toJson(existingVisitors));
		        }
				return;
			}
			
			if(method.equalsIgnoreCase("listAllLogs")){
				List<VisitorLog> allVisitorsLog = VisitorMySqlDao.retrieveAllVisitorsLog();
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(allVisitorsLog));
				return;
			}
			
			if(method.equalsIgnoreCase("listAllVisitors")){
				List<Visitor> existingVisitors = VisitorMySqlDao.retrieveAllVisitors();
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

	private Visitor insertVisitorLog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, URISyntaxException, IOException {
		Visitor visitor = new Visitor();
		visitor.setPhoneNumber(request.getParameter("phoneNumber"));
		visitor.setFirstName(request.getParameter("firstName"));
		visitor.setLastName(request.getParameter("lastName"));
		visitor.setMember(new Boolean(request.getParameter("member")));
		VisitorMySqlDao.insertVisitorLog(visitor);
		response.getWriter().append("Success:" + request.getParameter("firstName")+" " + request.getParameter("lastName"));
		return visitor;
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
