<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>

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
                        <form name="register" method="post" action="${pageContext.request.contextPath}/register.do" class="col-md-12">
                            <input type="text" name = "username" class="form-control input-register-username" placeholder="Nom d'utilisateur">
                            <input type="text" name = "lastname" class="form-control input-register-username" placeholder="Nom">
                            <input type="text" name = "firstname" class="form-control input-register-username" placeholder="Prénom">
                            <input type="text" name = "email" class="form-control input-register-username" placeholder="Email">
                            <input type="password" name = "password" class="form-control input-register-password" placeholder="Mot de passe">
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

</body>

</html>

