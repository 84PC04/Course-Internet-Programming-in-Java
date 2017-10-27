package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.User;

@ManagedBean(eager = true)
@SessionScoped
public class UserController {

	private User newUser = new User(); 
	private User currentUser = new User();
	private User loggedUser = new User();
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/ip15?characterEncoding=utf8";
	private String username = "root";
	private String password = "root";
	private Connection connection = null;
	private PreparedStatement ps = null;
	
	private String SELECT_LOGIN = "SELECT * FROM users WHERE username = ? AND password = ?";
	private String INSERT_NEW_USER = "INSERT INTO users (username, password, accountType) VALUES (?, ?, ?)";
	
	public UserController() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url,username,password);
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public String addUser() {
		try {      
			ps = connection.prepareStatement(INSERT_NEW_USER);			
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getAccountType());
			int saveResult = ps.executeUpdate();
			if (saveResult == 1) {
				System.out.println("New user added successfully.");
			}
			connection.close();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		
		return "success.xhtml";
	}
	public String login() throws SQLException {
		String retVal = "login.xhtml";
		
		ps = connection.prepareStatement(SELECT_LOGIN);
		ps.setString(1, currentUser.getUsername());
		ps.setString(2, currentUser.getPassword());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			loggedUser.setId(rs.getInt("id"));
			loggedUser.setUsername(rs.getString("username"));
			loggedUser.setPassword(rs.getString("password"));
			loggedUser.setAccountType(rs.getString("accountType"));
			
			if (loggedUser.getAccountType().equalsIgnoreCase("admin")) {
				retVal = "app-admin/index.xhtml";
			}
			else {
				retVal = "app-user/index.xhtml";
			}
		}
		System.out.println("Stanje korisnika: " + retVal);
		return retVal;
	}
}
