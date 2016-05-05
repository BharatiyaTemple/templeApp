package com.mbcc.templeapp.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mbcc.templeapp.dto.Visitor;

public class VisitorDao {

	private static PreparedStatement retrieveVisitorsStatement;
	private static PreparedStatement insertStatement;

	public static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}

	public static List<Visitor> retrieveVisitorsDataIfExists(String phoneNumber)
			throws SQLException, URISyntaxException {
		if (retrieveVisitorsStatement == null) {
			retrieveVisitorsStatement = getConnection().prepareStatement("SELECT * FROM Visitor where phone=?");
		}
		retrieveVisitorsStatement.setString(1, phoneNumber);
		ArrayList<Visitor> visitors = getVisitors();
		return visitors;
	}
	
	public static List<Visitor> retrieveVisitorsDataForDateRange(Date from, Date to)
			throws SQLException, URISyntaxException {
		
		
		if (retrieveVisitorsStatement == null) {
			retrieveVisitorsStatement = getConnection().prepareStatement("SELECT * FROM VisitorLog where dateofvisit BETWEEN ? AND ?");
		}
		retrieveVisitorsStatement.setTimestamp(1, new Timestamp(from.getTime()));
		retrieveVisitorsStatement.setTimestamp(2, new Timestamp(to.getTime()));
		
		ArrayList<Visitor> visitors = getVisitors();
		return visitors;
	}

	private static ArrayList<Visitor> getVisitors() throws SQLException {
		ResultSet resultSet = retrieveVisitorsStatement.executeQuery();
		ArrayList<Visitor> visitors = new ArrayList<Visitor>();

		while (resultSet.next()) {
			Visitor visitor = new Visitor();
			visitor.setFirstName(resultSet.getString(2));
			visitor.setLastName(resultSet.getString(3));
			visitors.add(visitor);
		}
		return visitors;
	}

	public static void initializeDatabase() throws SQLException, URISyntaxException {
		Statement stmt = getConnection().createStatement();

		String dropVisitorLog = "DROP TABLE VisitorLog IF EXISTS";
		stmt.executeUpdate(dropVisitorLog);
		
		String visitorLog = "CREATE TABLE VisitorLog " + "(id SERIAL, " + " first VARCHAR(255), "
				+ " last VARCHAR(255), " + " dateofvisit timestamp, " + " PRIMARY KEY ( id ))";

		stmt.executeUpdate(visitorLog);
		
		String dropVisitor = "DROP TABLE Visitor IF EXISTS";
		stmt.executeUpdate(dropVisitor);

		String visitors = "CREATE TABLE Visitor " + "(id SERIAL, " + " phone VARCHAR(8),"
				+ " first VARCHAR(255), " + " last VARCHAR(255), " + " member boolean, " + " PRIMARY KEY ( id ))";

		stmt.executeUpdate(visitors);
	}

	public static void insertVisitor(Visitor visitor) throws SQLException, URISyntaxException {
		if (insertStatement == null) {
			insertStatement = getConnection().prepareStatement("INSERT INTO Visitor(phone,first,last,member) values(?,?,?,?)");
		}
		insertStatement.setString(1, visitor.getPhoneNumber());
		insertStatement.setString(2, visitor.getFirstName());
		insertStatement.setString(3, visitor.getLastName());
		insertStatement.setBoolean(4, visitor.isMember());
		insertStatement.executeUpdate();
	}

}
