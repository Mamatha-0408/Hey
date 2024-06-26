package com.attendance;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
@WebServlet("/view")
/**
 * Servlet implementation class viewStudentatt
 */
public class viewStudentatt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
				
		
		try {
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");
      
        PreparedStatement  pst=conn.prepareStatement(" select * from ATTENDANCE");
        ResultSet rs = pst.executeQuery(); 
            ResultSetMetaData data = rs.getMetaData();
            int n=data.getColumnCount();
            pw.print("<html>  <head >  <style>"
            		+ " table,tr,th,td{"
            		+ " border:2px solid black"
            		+ "}"
            		+ "</style ></head><body bgcolor=lightcyan text=red><center>");

           pw.print("<table>");
           pw.print("<tr>");
           
           for(int i=1;i<=n;i++) 
                    {
          	if(i==1||i==2||i==3||i==4 )
          	    pw.print("<th>"+data.getColumnName(i)+" " +"</th>");
          	   
                     }
           pw.print("</tr>");
                while(rs.next()) {	
              	 pw.print("<tr>");
              	 pw.print("<td>" +rs.getString(1)+"</td>");
              	 pw.print( "<td>"+ rs.getInt(2)+"</td>");
              	 pw.print( "<td>"+ rs.getString(3)+"</td>");
              	 pw.print( "<td>"+  rs.getString(4)+"</td>");
              	 pw.print("</tr>");

                } 
                
         	  pw.print("</tr></table></center></body></html>");
         	  
         	  
        }
		
        catch( Exception e) {
        	e.printStackTrace();
        }
		
		 pw.print("<html><body><a href='Faculty.jsp' > <<< Back </a></body></html>");
         
         
//		RequestDispatcher rd=request.getRequestDispatcher("view.html");
//		rd.include(request, response);
	}
    
}
