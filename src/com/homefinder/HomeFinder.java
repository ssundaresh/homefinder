package com.homefinder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.*;
/**
 * Servlet implementation class HomeFinder
 */
@WebServlet(description = "Find your dream home", urlPatterns = { "/HomeFinder" })
public class HomeFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeFinder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // reading the user input
	    String maxRent= request.getParameter("maxRent");
	    String minBedrooms = request.getParameter("minBedrooms");
	    String phone_num_to = request.getParameter("phone_num_to");
	    String callFrequency = request.getParameter("selection");
	    PrintWriter out = response.getWriter();
	    MakeCall makeCalls = new MakeCall();
	    makeCalls.returnName(phone_num_to, maxRent, minBedrooms, callFrequency);
	    
	    out.println (
	      "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
	      "<html> \n" +
	        "<head> \n" +
	          "<meta http-equiv=\"Content-Type\" content=\"text/html;"+ 
	            "charset=ISO-8859-1\"> \n" +
	          "<title> Home Search Alerter  </title> \n" +
	        "</head> \n" +
	        "<body> \n" +
	          "<font size=\"12px\" color=\"black\">" +
	            "Got it. We'll call you. <br>Click <a href=\"HomeFinder.jsp\">here</a> to return" +
	          "</font> \n" +
	        "</body> \n" +
	      "</html>" 
	    );  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
