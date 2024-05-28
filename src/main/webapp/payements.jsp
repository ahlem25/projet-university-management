<%@ page import="java.util.ArrayList" %>
<%@ page import="main.java.org.iteam.javaBeans.Payement" %>
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
        Gestion des payements
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Accueil</a></li>
        <li class="active">Gestion des payements</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-xs-12">
                <%
                    ArrayList<Payement> payements = (ArrayList<Payement>) request.getAttribute("payements");
                    if(request.getAttribute("action")!=null)
                    {%>
                <div class="alert alert-success" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4>Félicitation!</h4>
                    <%
                        if(request.getAttribute("action").equals("add"))
                        {%>
                    <p>Le payement a été ajouté avec succès.</p>
                    <%
                        }
                        if(request.getAttribute("action").equals("edit"))
                        {%>
                    <p>Le payement a été modifié avec succès.</p>
                    <%
                        }
                        if(request.getAttribute("action").equals("supprimer"))
                        { %>
                    <p>Le payement a été supprimé avec succès.</p>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Liste des payements</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <a class="btn btn-primary pull-right" href="<%=request.getContextPath()%>/add-payement">
                            <i class="fa fa-plus"></i> Ajouter
                        </a>
                        <table id="datatable" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Montant</th>
                                <th>Etudiant</th>
                                <th>Commentaire</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                if(payements != null)
                                {
                                    for(Payement payement: payements)
                                    {
                            %>
                            <tr>
                                <td><%= payement.getId() %></td>
                                <td><%= payement.getAmount() %></td>
                                <td><%= payement.getStudentFullName() %></td>
                                <td><%= payement.getComment() %></td>
                                <td>
                                    <form action="<%=request.getContextPath()%>/payements" method="POST" onsubmit="return confirmDelete()">
                                        <a type="button" href="<%=request.getContextPath()%>/edit-payement?id=<%= payement.getId() %>" class="btn btn-info">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <input type="hidden" name="id" value="<%= payement.getId() %>" />
                                        <button class="btn btn-danger" type="submit"><i class="fa fa-trash"></i></button>
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