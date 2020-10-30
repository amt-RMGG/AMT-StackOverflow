<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>

<body>

<%@include file="fragments/navbar.jsp" %>

<!-- Page Content -->
<div class="container mainContent">

    <div class="row">

        <div class="col-md-6">
            <div class="col-md-12">
                <div class="card-body">
                    <h5 class="card-header">Bienvenue chez StackUnderFlow !</h5>
                    <div class="input-group">
                        <img src="https://pbs.twimg.com/media/EXy32bWWkAUQyj-.jpg" />
                    </div>
                    <a href="${pageContext.request.contextPath}/questions" class="btn btn-info">Liste des questions</a>
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

