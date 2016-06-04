package cookMe.dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cookMe.model.UserModelBean;

public class UserDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public UserDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addUser(UserModelBean user) {
		
		java.sql.PreparedStatement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			query = connection.prepareStatement("insert into table_user(firstname, lastname, age, email, login, pwd) values (?,?,?,?,?,?)");
            query.setString(1,user.getFirstName());
            query.setString(2,user.getLastName());
            query.setInt(3,user.getAge());
            query.setString(4,user.getEmail());
            query.setString(5,user.getLogin());
            query.setString(6,user.getPwd());
            query.executeUpdate();
			
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UserModelBean> getAllUser() {
		ArrayList<UserModelBean> userList = new ArrayList<UserModelBean>();
		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			query = connection.createStatement();
        	
        	java.sql.ResultSet rs = query.executeQuery("select firstname, lastname, age, email, login, pwd from table_user");
        	while(rs.next()){
        		userList.add(new UserModelBean(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"), rs.getString("email"), rs.getString("login"), rs.getString("pwd")));
        	}
        	rs.close();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public boolean alreadyExist(String login, String email){
		boolean b=false;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			PreparedStatement query = connection.prepareStatement("select * from table_user where login = ? or email = ?");
            query.setString(1,login);
            query.setString(2,email);
            ResultSet rs = query.executeQuery();
            
            if (rs.next()) {
            	b=true;
            }
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	public UserModelBean checkUser(String login, String pwd) {
		UserModelBean userBean = null;
		
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			PreparedStatement query = connection.prepareStatement("select * from table_user where login = ? and pwd = ?");
            query.setString(1,login);
            query.setString(2,pwd);
            ResultSet rs = query.executeQuery();
            
            if (rs.next()) {
            	userBean = new UserModelBean(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"),
            			 rs.getString("email"), rs.getString("login"), rs.getString("pwd"));
            }
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userBean;
	}
}