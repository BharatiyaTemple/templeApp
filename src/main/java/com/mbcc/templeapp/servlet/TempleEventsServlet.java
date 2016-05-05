package com.mbcc.templeapp.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.troy_temple.ActiveEventsSoapProxy;
import org.troy_temple.Event;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class TempleEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempleEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * ref: https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html?com/google/gson/Gson.html
	 * Call this servlet with this url: http://localhost:8080/TempleSoapWSClient/TempleEvents
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActiveEventsSoapProxy proxy = new ActiveEventsSoapProxy();
		String getTop5ActiveEvents15mtemp = proxy.getTop5ActiveEvents();
		Gson json = new Gson();
		Type typeOfT = new TypeToken<List<Event>>(){}.getType();
		List<Event> events = json.fromJson(getTop5ActiveEvents15mtemp, typeOfT);
		
		request.setAttribute("result", events);
		getServletContext().getRequestDispatcher("/demo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}