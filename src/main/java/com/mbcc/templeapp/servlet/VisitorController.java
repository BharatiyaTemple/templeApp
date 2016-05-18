package com.mbcc.templeapp.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mbcc.templeapp.dao.VisitorDao;
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
		
		if(request.getSession().getAttribute("login") == null){
			getServletContext().getRequestDispatcher("/login.html").forward(request, response);
		    return;
		}
			
		
		String method = request.getParameter("method");

		try {
			if (method.equalsIgnoreCase("insert")) {
				Visitor visitor = insertVisitorLog(request, response);
				VisitorDao.insertVisitor(visitor);
				return;
			}

			if (method.equalsIgnoreCase("insertLog")) {
				insertVisitorLog(request, response);
				return;
			}

			if (method.equalsIgnoreCase("list")) {
				List<Visitor> existingVisitors = VisitorDao
						.retrieveVisitorsDataIfExists(request.getParameter("phoneNumber"));
				Gson gson = new Gson();

				if (existingVisitors.size() == 1) {
					response.getWriter().append("- "+ existingVisitors.get(0).getFirstName() + " "+ existingVisitors.get(0).getLastName());
					VisitorDao.insertVisitorLog(existingVisitors.get(0));
				} else {
					response.setContentType("text/x-json;charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().append(gson.toJson(existingVisitors));
				}
				return;
			}

			if (method.equalsIgnoreCase("listAllLogs")) {
				response.setContentType("text/csv");
				String filename = "\"visitorsLog"+ new Date()+".csv\"";
				response.setHeader("Content-Disposition", "attachment; filename="+filename);
				
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				List<VisitorLog> allVisitorsLog = VisitorDao.retrieveAllVisitorsLog(startDate, endDate);
				
				OutputStream outputStream = response.getOutputStream();
				outputStream.write("First Name, Last Name, Date of Visit\n".getBytes());
				for (VisitorLog visitor : allVisitorsLog) {
					String outputResult = visitor.getFirstName() + "," + visitor.getLastName() + ","
							+ visitor.getDateofvisit() + "\n";
					outputStream.write(outputResult.getBytes());
				}
				outputStream.flush();
				outputStream.close();
				return;
			}

			if (method.equalsIgnoreCase("listAllVisitors")) {
				
				List<Visitor> existingVisitors = VisitorDao.retrieveAllVisitors();
				// Gson gson = new Gson();
				// response.setContentType("text/x-json;charset=UTF-8");

				response.setContentType("text/csv");
				String filename = "\"visitors"+ new Date()+".csv\"";
				response.setHeader("Content-Disposition", "attachment; filename="+filename);
				try {
					OutputStream outputStream = response.getOutputStream();
					outputStream.write("First Name, Last Name, Phone Number\n".getBytes());

					for (Visitor visitor : existingVisitors) {
						String outputResult = visitor.getFirstName() + "," + visitor.getLastName() + ","
								+ visitor.getPhoneNumber() + "\n";
						outputStream.write(outputResult.getBytes());
					}
					outputStream.flush();
					outputStream.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}

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
		VisitorDao.insertVisitorLog(visitor);
		response.getWriter()
				.append("Success:" + request.getParameter("firstName") + " " + request.getParameter("lastName"));
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
