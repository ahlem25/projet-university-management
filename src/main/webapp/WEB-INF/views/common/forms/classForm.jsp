<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="jakarta.servlet.*,java.text.*" %>

<%
    // int id = (int) request.getAttribute("id");
    String name = (String) request.getAttribute("name");
    String comment = (String) request.getAttribute("prenom");
    String ofYear = (String) request.getAttribute("email");
%>
<!--input type="hidden" name="id" value="<!%= id %>" -->

<div class="form-group col-sm-6">
    <label for="name">Nom:</label>
    <input type="text" name="name" id="name" value="<%= name != null ? name : "" %>" class="form-control"/>
</div>
<div class="form-group col-sm-6">
    <label for="ofYear">Année:</label>
    <input type="text" name="ofYear" id="ofYear" value="<%= ofYear != null ? ofYear : "" %>" class="form-control"/>
</div>
<div class="form-group col-sm-12">
    <label for="comment">Commentaire:</label>
    <textarea name="comment" id="comment" value="<%= comment != null ? comment : "" %>" class="form-control"></textarea>
</div>


