package step3.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step1.model.UserModel;
import step3.model.UserModelBean;

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
			
			query = connection.prepareStatement("insert into table_user(surname, lastname, age, login, pwd) values (?,?,?,?,?)");
            query.setString(1,user.getSurname());
            query.setString(2,user.getLastname());
            query.setInt(3,user.getAge());
            query.setString(4,user.getLogin());
            query.setString(5,user.getPwd());
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
        	
        	java.sql.ResultSet rs = query.executeQuery("select surname, lastname, age, login, pwd from table_user");
        	while(rs.next()){
        		userList.add(new UserModelBean(rs.getString("lastname"), rs.getString("surname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd")));
        	}
        	rs.close();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}