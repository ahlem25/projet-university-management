<%@ page import="main.java.org.iteam.javaBeans.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>
            <% User userConnected = (User) request.getSession().getAttribute("user");
              String fullNameSide = "Guest User";
              if (userConnected!=null){
                fullNameSide = userConnected.getFirstName() + " " + userConnected.getLastName();
              }
            %>
            <%= fullNameSide %>
          </p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> En ligne</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">Menu</li>
        <!-- Optionally, you can add icons to the links -->
        <li>
        	<a href="<%=request.getContextPath()%>/home"><i class="fa fa-dashboard"></i> <span>Accueil</span></a>
        </li>
        <li><a href="<%=request.getContextPath()%>/users"><i class="fa fa-users"></i> <span>Gestion d'utilisateurs</span></a></li>
        <li><a href="<%=request.getContextPath()%>/students"><i class="fa fa-list"></i> <span>Gestion des étudiants</span></a></li>
        <li><a href="<%=request.getContextPath()%>/classes"><i class="fa fa-tags"></i> <span>Gestion des classes</span></a></li>
        <li><a href="<%=request.getContextPath()%>/subscriptions"><i class="fa fa-list"></i> <span>Gestion des inscriptions</span></a></li>
        <li><a href="<%=request.getContextPath()%>/payements"><i class="fa fa-credit-card"></i> <span>Gestion des payements</span></a></li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>
