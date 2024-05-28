<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="jakarta.servlet.*,java.text.*" %>
<%@ page import="main.java.org.iteam.javaBeans.Student" %>
<%@ page import="main.java.org.iteam.javaBeans.Classe" %>

<%
    Integer id = (Integer) request.getAttribute("id");
    String year = (String) request.getAttribute("year");
    Integer student_id = (Integer) request.getAttribute("student_id");
    Integer class_id = (Integer) request.getAttribute("class_id");
    ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
    ArrayList<Classe> classes = (ArrayList<Classe>) request.getAttribute("classes");
%>
<input type="hidden" name="id" value="<%= id != null ? id : "" %>">

<div class="form-group col-sm-6">
    <label for="year">Année:</label>
    <input type="text" name="year" id="year" value="<%= year != null ? year : "" %>" class="form-control"/>
</div>

<div class="form-group col-sm-6">
    <label for="student_id">Etudiant:</label>
    <select name="student_id" id="student_id" value="<%= student_id != null ? student_id : "" %>" class="form-control">
        <%
            if(students != null)
            {
                for(Student student: students)
                {
        %>
        <option value="<%= student.getId() %>"><%= student.getFirstName() %> <%= student.getLastName() %></option>
        <%
                }
            }
        %>
    </select>
</div>

<div class="form-group col-sm-12">
    <label for="class_id">Class:</label>
    <select name="class_id" id="class_id" value="<%= class_id != null ? class_id : "" %>" class="form-control">
        <%
            if(classes != null)
            {
                for(Classe singleClass: classes)
                {
        %>
        <option value="<%= singleClass.getId() %>"><%= singleClass.getName() %></option>
        <%
                }
            }
        %>

    </select>
</div>


