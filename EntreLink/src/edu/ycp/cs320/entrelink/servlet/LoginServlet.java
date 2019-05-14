package edu.ycp.cs320.entrelink.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.entrelink.controller.LoginController;
import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.servlet.ProfileServlet;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession(); 

		System.out.println("Login Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		
		//create User model and login controller
		User model = new User();
		LoginController controller = new LoginController();
		controller.setModel(model);
		
		
		// decode POSTed form parameters and dispatch to controller
		// there was a try block here but I deleted it
			String email = req.getParameter("emailAsUsername");
			String password = req.getParameter("passwordOfUser");
			model.setEmail(email);
			model.setPassword(password);
			System.out.print(model.getEmail() + " - " + model.getPassword());

			// check for errors in the form data before using is in a calculation
			boolean isUser = controller.verifyUser();
			System.out.println(isUser);
			if(!isUser) {
				errorMessage = "The username or password is incorrect.";
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			}
			// otherwise, log the user in
			else {
				req.setAttribute("loggedInName", controller.getModel().getUserFirstName() + " " + controller.getModel().getUserLastName());
				HttpSession session=req.getSession(); 
				session.setAttribute("loggedInName", controller.getModel().getUserFirstName() + " " + controller.getModel().getUserLastName());
				session.setAttribute("loggedInUserName", controller.getModel().getUsername());
				session.setAttribute("loggedInImg", controller.getModel().getProfilePic());
				session.setAttribute("loggedInBio", controller.getModel().getBio());
				session.setAttribute("loggedInMajor", controller.getModel().getMajor());
				session.setAttribute("loggedInSkills", controller.getModel().getSkills());
				session.setAttribute("loggedInStatus", controller.getModel().getStatus());
				session.setAttribute("loggedInInterests", controller.getModel().getInterests());
				session.setAttribute("loggedInWebsite", controller.getModel().getWebsite());
				session.setAttribute("loggedInId", controller.getModel().getUserId());
				session.setAttribute("loggedInType", controller.getModel().getUserType());
				session.setAttribute("errorMessage", "");
		        req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			}
		
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
