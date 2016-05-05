package com.mbcc.templeapp.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import com.mbcc.templeapp.dao.VisitorDao;
import com.mbcc.templeapp.dto.Visitor;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			VisitorDao.initializeDatabase();

			resp.getWriter().print("Succesully created tables");
			return;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(resp.getWriter());
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(resp.getWriter());
			return;
		}

	}

	
}
