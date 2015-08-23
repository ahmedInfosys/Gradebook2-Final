//Storing students informations into database

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Assignment_into_db")
public class Store_Assignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Store_Assignment() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		
		//creating connection to Oracle database using JDBC
		Connection conn;
		
		PreparedStatement preStatement;
		String sql_command = "insert into Gradebook2 values (?,?,?,?,?)";
		try{
			conn = DriverManager.getConnection(url,props);
			preStatement = conn.prepareStatement(sql_command);
			
			preStatement.setString(1,request.getParameter("ID"));
			preStatement.setString(2,request.getParameter("Assignment"));
			preStatement.setString(3,request.getParameter("type"));
			preStatement.setString(4,request.getParameter("datepicker"));
			preStatement.setString(5,request.getParameter("Grade"));
			
			//System.out.println(request.getParameter("datepicker"));
			ResultSet result = preStatement.executeQuery();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
