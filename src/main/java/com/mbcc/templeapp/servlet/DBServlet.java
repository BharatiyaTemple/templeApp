package com.mbcc.templeapp.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Connection connection = getConnection();

			Statement stmt = connection.createStatement();

			String visitorLog = "CREATE TABLE VisitorLog " 
			+ "(id SERIAL, " 
			+ " first VARCHAR(255), "
			+ " last VARCHAR(255), " 
			+ " date timestamp, " 
			+ " PRIMARY KEY ( id ))";
			
			stmt.executeUpdate(visitorLog);

			String visitors = "CREATE TABLE Visitor " 
			+ "(id SERIAL, " 
			+ " phone VARCHAR(8),"
					
			+ " first VARCHAR(255), " 
			+ " last VARCHAR(255), " 
			+ " member boolean, " 
			+ " PRIMARY KEY ( id ))";

			stmt.executeUpdate(visitors);
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

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}
}
