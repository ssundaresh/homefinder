package com.homefinder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Servlet implementation class TwimlGenerator
 */
@WebServlet(description = "Generates twiml based on input parameters", urlPatterns = { "/TwimlGenerator" })
public class TwimlGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwimlGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String max_price= request.getParameter("max_price");
		    String num_bedrooms = request.getParameter("bedrooms");
		    response.setContentType("text/xml");
		 StringBuffer sb=new StringBuffer();
		 Document doc = Jsoup.connect("http://sfbay.craigslist.org/search/sfc/apa?postedToday=1&max_price="+max_price+"&bedrooms="+num_bedrooms+"&query=-bayview+-portola+-visitacion").get();
		 Element totalCount = doc.select("span.totalcount").first();
		 
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<Response>");
		sb.append("<Say>You have "+totalCount.text()+" listings for a "+num_bedrooms+" property</Say>");
		sb.append("</Response>");
		response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
