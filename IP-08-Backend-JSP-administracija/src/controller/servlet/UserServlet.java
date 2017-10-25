package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.UserDB;
import model.dto.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({"/UserServlet" , "/login", "/obrisiKorisnika", "/kreirajKorisnika" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getRequestURI().contains("/kreirajKorisnika")) {
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			
			UserDB userDB = (UserDB) request.getServletContext().getAttribute("userDB");
			if (userDB == null) {
				userDB = new UserDB();
			}
			
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			
			userDB.getUsers().add(newUser);
			
			request.getServletContext().setAttribute("userDB", userDB);
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getRequestURI().contains("/obrisiKorisnika")) {
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			
			UserDB userDB = (UserDB) request.getServletContext().getAttribute("userDB");
			if (userDB == null) {
				userDB = new UserDB();
			}
			
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			
			userDB.getUsers().add(newUser);
			
			request.getServletContext().setAttribute("userDB", userDB);
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
		if (request.getRequestURI().contains("/login")) {
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			System.out.println("Login: " + username + " : " + password);
			
			UserDB userDB = (UserDB) request.getServletContext().getAttribute("userDB");
			if (userDB == null) {
				userDB = new UserDB();
			}
			
			// import db
			request.getServletContext().setAttribute("userDB", userDB);
			
			// redirect
			RequestDispatcher dispatcher = null;
			for (User user : userDB.getUsers()) {
				if (username.equals(user.getUsername()) && password.equalsIgnoreCase(user.getPassword())) {
					if (username.equalsIgnoreCase("admin")) {
						dispatcher = request.getRequestDispatcher("admin.jsp");
					}
					else {
						dispatcher = request.getRequestDispatcher("user.jsp");
					}
					break;
				}
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			dispatcher.forward(request, response);
		}
	}

}
