<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Faculty Dashboard</title>
     <link rel="stylesheet" type="text/css" href="styles.css">
    
</head>
<body bgcolor=cyan text=red>
 
<pre>
    <h2>Welcome, ${sessionScope.username}</h2>
    <h3>Student Attendance</h3>
    <form action="viewAttendance" method="post">
        
 
       Student Id  <select   name="select">
          <option  > Select Student id </option>
          <option values="1" >1</option>
          <option values="2">2</option>
          <option values="3">3</option>
          <option values="4">4</option>
          <option values="5">5</option>
          <option values="6">6</option>
          <option values="7">7</option>
          <option values="8">8</option>
          <option values="9">9</option>
       
       </select>    <input type="datetime-local" name="date">    <b>Attendance Status :  </b>   <input  type="radio" value="Present" name="att" > Present <input type="radio" value="Absent" name="att"> Absent 
   
       
       
       
        <button type="submit">  Attendance </button>    

    </form>   <a href='view.html' > Download Attendance </a> 
  
       </pre>
     
</body>
</html>
