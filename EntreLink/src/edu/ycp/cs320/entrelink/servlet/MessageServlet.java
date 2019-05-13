package edu.ycp.cs320.entrelink.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.entrelink.controller.PostController;
import edu.ycp.cs320.entrelink.controller.MessageController;
import edu.ycp.cs320.entrelink.model.Message;
import edu.ycp.cs320.entrelink.model.Post;

public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MessageController controller = null;	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		if(session.getAttribute("loggedInName") != null) {
			int userId = (int) session.getAttribute("loggedInId");
			
			System.out.println("Message Servlet: doGet");	
			
			// Loading all the messages for the logged in user...
			ArrayList<Message> messages = null;
			String errorMessage = null;
			controller = new MessageController();
			
			// get list of messages returned from query
			messages = controller.getAllMessagesForLoggedInUser(userId);
			
			// Add result objects as request attributes
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("messages", messages);
		}
		req.getRequestDispatcher("/_view/messages.jsp").forward(req, resp);
	}	
	protected void doOpenProjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/projects.jsp").forward(req, resp);
	}
	protected void doOpenProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	protected void doOpenSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/search.jsp").forward(req, resp);
	}
	protected void doOpenHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
