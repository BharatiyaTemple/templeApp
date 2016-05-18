package com.mbcc.templeapp.servlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mbcc.templeapp.dao.VisitorDao;

public class DBServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			VisitorDao.initializeDatabase();

			resp.getWriter().print("Succefully created tables");
			return;
		} catch (URISyntaxException e) {
			e.printStackTrace(resp.getWriter());
			return;
		} catch (SQLException e) {
			e.printStackTrace(resp.getWriter());
			return;
		}

	}

}
