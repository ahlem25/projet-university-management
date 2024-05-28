<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="jakarta.servlet.*,java.text.*" %>
<%@ page import="main.java.org.iteam.javaBeans.Student" %>
<%@ page import="java.time.LocalDate" %>

<%
    Integer id = (Integer) request.getAttribute("id");
    String amount = (String) request.getAttribute("amount");
    LocalDate date = (LocalDate) request.getAttribute("date");
    Integer student_id = (Integer) request.getAttribute("student_id");
    String comment = (String) request.getAttribute("comment");
    ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
%>
<input type="hidden" name="id" value="<%= id != null ? id : "" %>">

<div class="form-group col-sm-12">
    <label for="amount">Montant:</label>
    <input type="text" name="amount" id="amount" value="<%= amount != null ? amount : "" %>" class="form-control"/>
</div>

<div class="form-group col-sm-6">
    <label for="date">Date:</label>
    <input type="date" name="date" id="date" value="<%= date != null ? date : "" %>" class="form-control"/>
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
        <option value="<%= student.getId() %>">
            <%= student.getFirstName() %> <%= student.getLastName() %>
        </option>
        <%
                }
            }
        %>
    </select>
</div>

<div class="form-group col-sm-12">
    <label for="comment">Commentaire:</label>
    <textarea name="comment" id="comment" class="form-control"><%= comment != null ? comment : "" %></textarea>
</div>
