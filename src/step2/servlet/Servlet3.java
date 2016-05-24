package step2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step1.db.DB;
import step1.model.UserModel;
import step2.model.UserModelBean;

/**
 * 
 Servlet implementation class Servlet3
 */
@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db;

	/**
	 * 
	 @see HttpServlet#HttpServlet()
	 */
	public Servlet3() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		if (getServletContext().getAttribute("BD") != null) {
			db = (DB) getServletContext().getAttribute("BD");
		} else {
			db = new DB();
			getServletContext().setAttribute("BD", db);
		}
	}

	/**
	 * 
	 @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Nothing to do
	}

	/**
	 * 
	 @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModelBean user = (UserModelBean) request.getSession().getAttribute(
				"myUser");
		
		System.out.println(user);
		
		db.addUser(new UserModel(user.getLastname(),user.getSurname(),user.getAge(),user.getLogin(),user.getPwd()));
		response.sendRedirect("step2/display.jsp");
	}
}