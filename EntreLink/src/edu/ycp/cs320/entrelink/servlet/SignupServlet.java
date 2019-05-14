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
import edu.ycp.cs320.entrelink.model.NewUser;
import edu.ycp.cs320.entrelink.model.User;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		session.getAttribute("loggedInName");
		Boolean sessionExists = session.isNew();
        
		
		System.out.println("Signup Servlet: doGet");
		
		req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Signup Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// decode POSTed form parameters and dispatch to controller
		// there was a try block here but I deleted it
			String newUsername = req.getParameter("newUsername");
			String newEmail = req.getParameter("newEmail");
			String confirmEmail = req.getParameter("confirmEmail");
			String newPassword = req.getParameter("newPassword");
			String confirmPassword = req.getParameter("confirmPassword");
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String accountType = req.getParameter("accountType");
			
			User model = new User(newUsername, newPassword, firstname, lastname, newEmail, accountType);
			SignupController controller = new SignupController();
			controller.setModel(model);

			// check for errors in the form data before using is in a calculation
			boolean doesUserExist = controller.verifyIsNewUser();
			boolean areEmailsSame = newEmail.equals(confirmEmail);
			boolean arePasswordsSame = newPassword.equals(confirmPassword);
			boolean isEmailValid = controller.verifyEmailIsValid();
			if(doesUserExist) {
				System.out.println("User already exists.");
				errorMessage = "The username or email already exists.";
			}
			if(!areEmailsSame) {
				errorMessage = "The emails entered are different.";
				System.out.println("Invalid email input.");
			}
			if(!arePasswordsSame) {
				errorMessage = "The passwords entered are different.";
				System.out.println("Invalid password input.");
			}
			if(!isEmailValid) {
				errorMessage = "The email must be @ycp.edu.";
				System.out.println("Email not YCP.");
			}
			
			// otherwise, sign the user up
			if(!doesUserExist && areEmailsSame && arePasswordsSame && isEmailValid) {
				UserController uController = new UserController();
				uController.createNewUser(model.getNewUsername(), model.getConfirmPassword(),
						model.getFirstname(), model.getLastname(), model.getConfirmEmail(), model.getAccoutType(),
						"N/A", "N/A", "N/A", "N/A", "N/A");
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			}
		
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
