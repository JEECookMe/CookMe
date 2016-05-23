package step1.db;

import step1.model.UserModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Floe on 5/23/2016.
 */
public class DB {
    private static final String DB_HOST = "db-tp.cpe.fr";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "binome01";
    private static final String DB_USER = "binome01";
    private static final String DB_PWD = "binome01";
    private Connection connection;

    public DB() {
        try {
// Chargement du Driver, puis établissement de la connexion
            Class.forName("com.mysql.jdbc.Driver");
//create connection
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserModel> getData() {
//return value
        ArrayList<UserModel> userList = new ArrayList<UserModel>();
// Création de la requête
        java.sql.Statement query;
        try {
        	
        	query = connection.createStatement();
        	
        	java.sql.ResultSet rs = query.executeQuery("select surname, lastname, age, login, pwd from Users");
        	while(rs.next()){
        		userList.add(new UserModel(rs.getString("lastname"), rs.getString("surname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd")));
        	}
        	rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void addUser(UserModel user) {
        // Création de la requête
        java.sql.PreparedStatement query;
        try {
            //Creation de l'élément de requète
            query = connection.prepareStatement("insert into Users(surname, lastname, age, login, pwd) values ('?','?',?,'?','?')");
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
}
