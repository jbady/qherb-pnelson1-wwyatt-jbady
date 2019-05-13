package edu.ycp.cs320.entrelink.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.entrelink.controller.PostController;
import edu.ycp.cs320.entrelink.model.Post;


public class AllPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PostController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nAllPostsServlet: doGet");

		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");

			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// now we have the user's User object,
		// proceed to handle request...

		System.out.println("   User: <" + user + "> logged in");

		req.getRequestDispatcher("/_view/projects.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nAllPostsServlet: doPost");		

		ArrayList<Post> posts = null;
		String errorMessage       = null;

		controller = new PostController();

		// get list of authors returned from query
		posts = controller.getAllPosts("student");

		// any posts found?
		if (posts == null) {
			errorMessage = "No Posts were found in the Library";
		}

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("posts", posts);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/projects.jsp").forward(req, resp);
	}	
}