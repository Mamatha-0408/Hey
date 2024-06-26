package com.attendance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/login")
public class LoginServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");

            PreparedStatement ps = null;
            if ("student".equals(role)) {
                ps = con.prepareStatement("SELECT * FROM students WHERE username=? AND password=?");
            } else if ("faculty".equals(role)) {
                ps = con.prepareStatement("SELECT * FROM faculty WHERE username=? AND password=?");
            }

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                if ("student".equals(role)) {
                    response.sendRedirect("student.jsp");
                } else if ("faculty".equals(role)) {
                    response.sendRedirect("Faculty.jsp");
                }
            } else {
                response.sendRedirect("Loginjsp.jsp?error=Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
