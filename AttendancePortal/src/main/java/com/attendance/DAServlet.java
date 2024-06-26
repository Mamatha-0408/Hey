package com.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/downloadAttendance")
public class DAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		  String studentName = request.getParameter("studentName");
	        response.setContentType("application/csv");
	        response.setHeader("Content-Disposition", "attachment; filename=\"attendance.csv\"");
	        PrintWriter out = response.getWriter();
	        out.println("Date,Status");

	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");
	            PreparedStatement ps = con.prepareStatement("SELECT attendance_date, status FROM attendance WHERE student_id=(SELECT id FROM students WHERE username=?)");
	            ps.setString(1, studentName);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                out.println(rs.getDate("attendance_date") + "," + rs.getString("status"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            out.close();
	        }
	
	
	}

}
