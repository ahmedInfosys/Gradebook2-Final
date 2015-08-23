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


@WebServlet("/Search")
public class filter_search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public filter_search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("text/html");
		
		String id_options = "", types_options = "";
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		
		//creating connection to Oracle database using JDBC
		Connection conn;
		
		PreparedStatement preStatement;
		String sql_command = "select distinct id from Gradebook2";
		String sql_command2 = "select distinct Assignment_type from Gradebook2";

		try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(sql_command);
				ResultSet result = preStatement.executeQuery();
			    
				id_options +=  "<option>Please select</option>";
			    while(result.next()){
					id_options +=  "<option>" + result.getInt("ID") + "</option>";
			    }
			    preStatement = conn.prepareStatement(sql_command2);
				result = preStatement.executeQuery();
				
				types_options +=  "<option>Please select</option>";
			    while(result.next()){
			    	types_options +=  "<option>" + result.getString("Assignment_type") + "</option>";
			    }
				
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("IDs",id_options);
		request.setAttribute("types", types_options);
		getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
	

	  } 
	  
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
