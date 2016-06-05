package cookMe.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import cookMe.model.RecipeModel;
import cookMe.model.UserModelBean;

public class RecipesDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	
	public RecipesDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addRecipe(RecipeModel recipe) {
		java.sql.PreparedStatement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			query = connection.prepareStatement("insert into table_user(title, description, expertise, nbpeople, duration, type) values (?,?,?,?,?)");
            query.setString(1,recipe.getTitle());
            query.setString(2,recipe.getDescription());
            query.setInt(3,recipe.getExpertise());
            query.setInt(4,recipe.getNbpeople());
            query.setInt(5,recipe.getDuration());
            query.setString(2,recipe.getType());
            query.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RecipeModel> getAllRecipes() {
		ArrayList<RecipeModel> recipeList = new ArrayList<RecipeModel>();
		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			query = connection.createStatement();
        	
        	java.sql.ResultSet rs = query.executeQuery("select title, description, expertise, nbpeople, duration, type from table_recipe");
        	while(rs.next()){
        		recipeList.add(new RecipeModel(rs.getString("title"), rs.getString("description"), rs.getInt("expertise"), rs.getInt("nbpeople"), rs.getInt("duration"), rs.getString("type")));
        	}
        	rs.close();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}
}