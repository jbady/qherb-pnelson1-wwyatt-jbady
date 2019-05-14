package edu.ycp.cs320.entrelink.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.entrelink.controller.LoginController;
import edu.ycp.cs320.entrelink.controller.SignupController;
import edu.ycp.cs320.entrelink.controller.UserController;
import edu.ycp.cs320.entrelink.model.User;

public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		session.getAttribute("loggedInName");
		Boolean sessionExists = session.isNew();
        
		
		System.out.println("Edit User Servlet: doGet");
		
		req.getRequestDispatcher("/_view/editUser.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Edit User Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// decode POSTed form parameters and dispatch to controller
		// there was a try block here but I deleted it
			String major = req.getParameter("usermajor");
			String bio = req.getParameter("userbio");
			String status = req.getParameter("userstatus");
			String interests = req.getParameter("userinterests");
			String skills = req.getParameter("userskills");
			String website = req.getParameter("userwebsite");
			String picture = req.getParameter("userpicture");
			
			UserController controller = new UserController();
			if(!major.isEmpty()) {
				System.out.println(major+"\n\n");
				controller.editUserMajor(req.getSession().getAttribute("loggedInUserName").toString(), major);
				req.getSession().setAttribute("loggedInMajor", major);
			}
			if(!bio.isEmpty()) {
				System.out.println(bio+"\n\n");
				controller.editUserBio(req.getSession().getAttribute("loggedInUserName").toString(), bio);
				req.getSession().setAttribute("loggedInBio", bio);
			}
			if(!status.isEmpty()) {
				System.out.println(status+"\n\n");
				controller.editUserStatus(req.getSession().getAttribute("loggedInUserName").toString(), status);
				req.getSession().setAttribute("loggedInStatus", status);
			}
			if(!interests.isEmpty()) {
				System.out.println(interests+"\n\n");
				controller.editUserInterests(req.getSession().getAttribute("loggedInUserName").toString(), interests);
				req.getSession().setAttribute("loggedInInterests", interests);
			}
			if(!skills.isEmpty()) {
				System.out.println(skills+"\n\n");
				controller.editUserSkills(req.getSession().getAttribute("loggedInUserName").toString(), skills);
				req.getSession().setAttribute("loggedInSkills", skills);
			}
			if(!website.isEmpty()) {
				System.out.println(website+"\n\n");
				controller.editUserWebsite(req.getSession().getAttribute("loggedInUserName").toString(), website);
				req.getSession().setAttribute("loggedInWebsite", website);
			}
			if(!picture.isEmpty()) {
				System.out.println("this is it.." +req.getParameter("userpicture")+"..\n");
				controller.editUserPic(req.getSession().getAttribute("loggedInUserName").toString(), picture);
				req.getSession().setAttribute("loggedInImg", picture);
			}
			
			doOpenProfile(req, resp);
		
	}
	
	protected void doOpenProjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/projects.jsp").forward(req, resp);
	}
	protected void doOpenProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	protected void doOpenHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
