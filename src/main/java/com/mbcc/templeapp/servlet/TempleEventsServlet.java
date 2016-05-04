package com.mbcc.templeapp.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		// TODO Auto-generated method stub
		ActiveEventsSoapProxy proxy = new ActiveEventsSoapProxy();
		String getTop5ActiveEvents15mtemp = proxy.getTop5ActiveEvents();
		Gson json = new Gson();
		Type typeOfT = new TypeToken<List<Event>>(){}.getType();
		List<Event> events = json.fromJson(getTop5ActiveEvents15mtemp, typeOfT);
		
		//debug for loop
//		String jsonStr = json.toJson(events, typeOfT);		
//		for(Event evt: events){
//			System.out.println(evt.getEvent_name());
//			System.out.println(evt.getEvent_venue());
//			System.out.println(evt.getEvent_type());
//			System.out.println(evt.getEvent_date());
//			System.out.println(evt.getEvent_recipient_emails());
//			System.out.println(evt.getEvent_img());
//			System.out.println("////////////////////////////////////////////\n");			
//		}		
//		request.setAttribute("jsonresult", jsonStr);//if you want to return a json String
		request.setAttribute("result", events);
		getServletContext().getRequestDispatcher("/sampleActiveEventsSoapProxy/Demo.jsp").forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}