package com.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewAttendance")
public class Viewatt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PrepareStatement = null;

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attendance=null;
		int k=0;
		PrintWriter pw=response.getWriter();
		 
	           int s1= Integer.parseInt(request.getParameter("select"));
	          
	           String s2=request.getParameter("date");
	           String s3=request.getParameter("att");
	           if(s3.equals("Present"))
	           {
	        	  attendance=s3;  
	           }
	        	   
	           if(s3.equals("Absent")) {
	        	    attendance=s3; 
	           }
		
		         try {
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");
	            String sql=" insert into attendance (STUDENT_ID,ATTENDANCE_DATE ,STATUS )values(?,?,?)";
                PreparedStatement  pst=con.prepareStatement(sql);
                pst.setInt(1,s1);
                pst.setString(2, s2);
                pst.setString(3, attendance);
                k=pst.executeUpdate();
                if(k>0) {
                	
                	 pw.print("<html><body><a href='view.html' > Download Attendance </a></body></html>");
                
            
                }
                
                else {
                	pw.print("not insert ");
                }
                
                
                
	        	
	        }
		         
		            
	        catch(Exception  e) {
	        	e.printStackTrace();
	        	
	        }
	        
	        
	        
	        
	        
	        
	        request.getRequestDispatcher("Faculty.jsp").forward(request, response);

	
	
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		this.doPost(req, resp);
//	}

}
