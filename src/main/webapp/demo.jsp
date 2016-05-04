<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="org.troy_temple.Event"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Temple WS Soap Client</title>
<link rel="stylesheet" href="css/formstyle1.css" type="text/css">
<!-- Ref: http://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_lists_avatar -->
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<div class="form-style-10">
		<h1>
			Welcome to Bharatiya Temple Library!<span>Events</span>
		</h1>
		<%-- <%= request.getAttribute("result") %> --%>
		<ul class="w3-ul w3-card-4">
		<%
			List<Event> results = (List<Event>) request.getAttribute("result");
			for (Event evt : results) {	
				String description = evt.getFull_desc();
				/* description = description.replace('\n', ' '); */
				
		%>		
		  <li class="w3-padding-hor-16">
		    <span onclick="this.parentElement.style.display='none'" 
		    class="w3-closebtn w3-padding w3-margin-right w3-medium">x</span>
		    <img src="http://www.troy-temple.org/<%=evt.getEvent_img() %>" class="w3-left w3-circle w3-margin-right" style="width:60px">
		    <span class="w3-xlarge"><%=evt.getEvent_name()%> <nbsp> <%=evt.getEvent_venue()%></span><br>
		    <span><%=evt.getEvent_date()%></span><br>		 
		    <span><%=evt.getEvent_recipient_emails()%></span><br>
		    <span>Type of Event:<%=evt.getEvent_type()%></span><br>		    	    			    	  
		   <span id='desc'>Description:<br></span><br>		  
		  </li>			  
		<%
			}
		%>
		</ul>				
	</div>	
</body>
</html>