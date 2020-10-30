<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>

<body>

<%@include file="fragments/navbar.jsp" %>

<% String errors = (String)request.getSession().getAttribute("errors"); %>


<c:if test="${ errors != null}">
    <script>
        alert("${errors}");
    </script>
</c:if>

  <!-- Page Content -->
  <div class="container mainContent">

    <div class="row">

      <div class="col-md-6">
          <div class="col-md-12">
              <div class="card-body">
              <h5 class="card-header">Connexion</h5>
                <div class="input-group">
                    <form name="connexion" method="post" action="${pageContext.request.contextPath}/login.do" class="col-md-12">
                        <input type="text" name="username" class="form-control input-login-username" placeholder="Nom d'utilisateur">
                        <input type="password" name="password" class="form-control input-login-password" placeholder="Mot de passe">
                        <input type="submit"$ class="btn btn-primary btn-login" value="Connexion"/>
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
