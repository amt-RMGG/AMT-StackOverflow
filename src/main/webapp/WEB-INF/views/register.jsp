<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Stack Underflow - register</title>

    <!-- Bootstrap core CSS -->
    <link href="../../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../assets/css/blog-home.css" rel="stylesheet">

</head>

<body>

<%@include file="fragments/navbar.jsp" %>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-6">
            <div class="col-md-12">
                <div class="card-body">
                    <h5 class="card-header">S'enregistrer</h5>
                    <div class="input-group">
                        <form name="register" method="post" action="${pageContext.request.contextPath}/registerService" class="col-md-12">
                            <input type="text" name = "username" class="form-control input-register-username" placeholder="Nom d'utilisateur">
                            <input type="password" name = "pswd" class="form-control input-register-password" placeholder="Mot de passe">
                            <input type="password" name = "confirm" class="form-control input-register-confirm" placeholder="Confirmer le mot de passe">
                            <input type="submit"$ class="btn btn-primary btn-register" value="S'enregistrer"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<%@include file="fragments/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

