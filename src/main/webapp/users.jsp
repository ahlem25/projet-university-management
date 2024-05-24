<%@ page import="java.util.ArrayList" %>
<%@ page import="org.iteam.javaBeans.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/common/include/meta.jsp"%>
<title>Home</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- .wrapper -->
<div class="wrapper">

	<%@include file="/WEB-INF/views/common/include/header.jsp"%>
	<%@include file="/WEB-INF/views/common/include/aside.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Gestion d'utilisateurs
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Accueil</a></li>
        <li class="active">Gestion d'utilisateurs</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-xs-12">
                <%
                    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
                    if(request.getAttribute("action")!=null)
                    {%>
                        <div class="alert alert-success" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4>Félicitation!</h4>
                        <%
                        if(request.getAttribute("action").equals("add"))
                        {%>
                                <p>L'utilisateur a été ajouté avec succès.</p>
                        <%
                        }
                        if(request.getAttribute("action").equals("edit"))
                        {%>
                            <p>L'utilisateur a été ajouté avec succès.</p>
                        <%
                        }
                        if(request.getAttribute("action").equals("supprimer"))
                        { %>
                            <p>L'utilisateur a été ajouté avec succès.</p>
                            <%
                        }
                        %>
                        </div>
                        <%
                    }
                %>

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Liste des utilisateurs</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <a class="btn btn-primary pull-right" href="AddUser.jsp">
                            <i class="fa fa-plus"></i> Ajouter
                        </a>
                        <table id="datatable" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>E-mail</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                if(users != null)
                                {
                                    for(User user: users)
                                    {
                            %>
                            <tr>
                                <td><%= user.getFirstName() %></td>
                                <td><%= user.getLastName() %></td>
                                <td><%= user.getEmail() %></td>
                                <td>
                                    <form action="/projet-university-management/Users" method="POST">
                                        <a type="button" href="/projet-university-management/EditUser?id=<%= user.getId() %>" class="btn btn-success">
                                            <i class="fas fa-user-edit"></i>
                                        </a>
                                        <input type="hidden" name="id" value="<%= user.getId() %>" />
                                        <button class="btn btn-danger" type="submit"><i class="fas fa-user-times"></i></button>
                                    </form>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

	<%@include file="/WEB-INF/views/common/include/footer.jsp"%>

</div>
<!-- /.wrapper -->

	<%@include file="/WEB-INF/views/common/include/script.jsp"%>
    <!-- DataTables -->
    <script src="resources/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- page script -->
    <script>
        $(function () {
            $('#datatable').DataTable({
                "language": {
                    "url": "https://cdn.datatables.net/plug-ins/1.11.3/i18n/fr_fr.json",
                },
                "paging": true,
                "lengthChange": false,
                "searching": true,
                "ordering": true,
                "info": true,
                "autoWidth": false
            });
        });
    </script>

</body>
</html>