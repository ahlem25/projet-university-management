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
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Modifier un payement</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <form action="<%=request.getContextPath()%>/EditPayement" method="POST">
                                <%@include file="/WEB-INF/views/common/forms/payementForm.jsp"%>
                                <div class="form-group col-sm-12 float-left">
                                    <a class="btn btn-secondary ml-2" href="<%=request.getContextPath()%>/payements.jsp">
                                        <i class="fa fa-angle-left"></i> Retour
                                    </a>
                                    <button class="btn btn-success ml-2" type="submit">
                                        <i class="fa fa-save"></i> Modifier
                                    </button>
                                </div>
                            </form>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="/WEB-INF/views/common/include/footer.jsp"%>

</div>
<!-- /.wrapper -->

<%@include file="/WEB-INF/views/common/include/script.jsp"%>

</body>
</html>