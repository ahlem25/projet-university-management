<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>IT</b>eam</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Iteam</b>University</span>
    </a>
    <% User connectedUser = (User) request.getSession().getAttribute("user");
      String fullName = "Guest User";
      String connectedEmail = "";
      if (connectedUser!=null){
        fullName = connectedUser.getFirstName() + " " + connectedUser.getLastName();
        connectedEmail = connectedUser.getEmail();
      }
    %>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><%= fullName %></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                <p>
                  <%= fullName %>
                  <small><%= connectedEmail %></small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="<%=request.getContextPath()%>/profile" class="btn btn-default btn-flat">Mon Profil</a>
                </div>
                <div class="pull-right">
                  <a href="<%=request.getContextPath()%>/logout" class="btn btn-default btn-flat">Déconnexion</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
