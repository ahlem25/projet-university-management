<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="jakarta.servlet.*,java.text.*" %>

<%
    // int id = (int) request.getAttribute("id");
    String nom = (String) request.getAttribute("nom");
    String prenom = (String) request.getAttribute("prenom");
    String email = (String) request.getAttribute("email");
    String cin = (String) request.getAttribute("cin");
    String level = (String) request.getAttribute("level");
%>
<!--input type="hidden" name="id" value="<!%= id %>" -->

<div class="form-group col-sm-6">
    <label for="nom">Nom:</label>
    <input type="text" name="nom" id="nom" value="<%= nom != null ? nom : "" %>" class="form-control"/>
</div>

<div class="form-group col-sm-6">
    <label for="prenom">Prénom:</label>
    <input type="text" name="prenom" id="prenom" value="<%= prenom != null ? prenom : "" %>" class="form-control"/>
</div>

<div class="form-group col-sm-6">
    <label for="email">E-mail:</label>
    <input type="email" name="email" id="email" value="<%= email != null ? email : "" %>" class="form-control"/>
</div>

<div class="form-group col-sm-6">
    <label for="cin">CIN:</label>
    <input type="text" name="cin" id="cin" value="<%= cin != null ? cin : "" %>" class="form-control"/>
</div>

<div class="form-group col-sm-6">
    <label for="level">Niveau d'étude:</label>
    <input type="text" name="level" id="level" value="<%= level != null ? level : "" %>" class="form-control"/>
</div>