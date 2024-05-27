<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="jakarta.servlet.*,java.text.*" %>

<%
    Integer id = (Integer) request.getAttribute("id");
    String nom = (String) request.getAttribute("nom");
    String prenom = (String) request.getAttribute("prenom");
    String email = (String) request.getAttribute("email");
    String password = (String) request.getAttribute("password");
%>
<input type="hidden" name="id" value="<%= id != null ? id : "" %>">

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
    <label for="password">Mot de passe:</label>
    <input type="password" name="password" id="password" value="<%= password != null ? password : "" %>" class="form-control"/>
</div>
