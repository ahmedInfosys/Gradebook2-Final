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

/**
 * Servlet implementation class Store_Assignment
 */
@WebServlet("/search_results")
public class Get_results extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_results() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("text/html");
		
		String Store_table = "";
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		
		//creating connection to Oracle database using JDBC
		Connection conn;
		
		PreparedStatement preStatement;
		String sql_command = "select * from Gradebook2";
		int ands = 0;
		if(!(request.getParameter("studentID").equalsIgnoreCase("Please select")) || !(request.getParameter("types").equalsIgnoreCase("Please select"))){
			sql_command += " where " ;
			
			if(!(request.getParameter("studentID").equalsIgnoreCase("Please select"))){
				ands++;
				sql_command += "id = " + request.getParameter("studentID") + "   ";
			}
			
			if(!(request.getParameter("types").equalsIgnoreCase("Please select"))){
				ands++;
				sql_command += "assignment_type = '" + request.getParameter("types") + "'   ";
			}
		}
		if((ands - 1) > 0){
			sql_command = sql_command.replaceFirst("   ", " and ");
		}
		
		System.out.println(sql_command);
		
		Store_table += "<div class=\"container-fluid\"> <table class=\"table table-striped table-bordered\">";
		Store_table += "<thread>";
		Store_table += "<tr>";
		Store_table += "<th> Student ID </th>";   
		Store_table += "<th> Assignment </th>"; 
		Store_table += "<th> Assignment type</th>";  
		Store_table += "<th> Date </th>";   
		Store_table += "<th> Grade </th>"; 
		Store_table += "</tr>";
		Store_table += "</thread>";
		Store_table +=  "<tbody>" ;
		
		double sum = 0,max = 0,min = 100, average = 0;
		int count = 0;
		
		try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(sql_command);
				ResultSet result = preStatement.executeQuery();
			    
			    while(result.next()){
			    	
			    	//Count number of results for determining average
			    	count++;
			    	sum += result.getDouble("Grade");
			    	
			    	if(result.getDouble("Grade") > max){
			    		max = result.getDouble("Grade");
			    	}
			    	
			    	if(result.getDouble("Grade") < min){
			    		min = result.getDouble("Grade");
			    	}
					Store_table +=  "<tr>";
					Store_table +=  "<td>" + result.getInt("ID") + "</td>";
					Store_table +=  "<td>" + result.getString("Assignment") + "</td>";
					Store_table +=  "<td>" + result.getString("Assignment_type") + "</td>";
					Store_table +=  "<td>" + result.getString("Assignment_date") + "</td>";
					Store_table +=  "<td>" + result.getDouble("Grade") + "</td>";
					Store_table +=  "</tr> <br/>";
			    }
			    average = sum/count;
			    
			    Store_table += "</tbody> </table> </div>";
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
		request.setAttribute("table",Store_table);
		request.setAttribute("average",(" " + average + " ").format("%.2f", average));
		request.setAttribute("min"," " + min + " ");
		request.setAttribute("max"," " + max + " ");
		getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
	

	  } 
	  
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
