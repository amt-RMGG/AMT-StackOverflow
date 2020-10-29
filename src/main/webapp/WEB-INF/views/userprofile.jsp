<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" value="${currentUser}" />
<%@include file="fragments/header.jsp"%>

<body>

<%@include file="fragments/navbar.jsp" %>

<!-- Page Content -->
<div class="container mainContent">

    <div class="row">

        <div class="col-md-6">
            <div class="col-md-12">
                <div class="card-body">
                    <h5 class="card-header">User profile</h5>
                    <div class="">
                        <c:choose>
                            <c:when test="${user != null}">
                                <h4>${user.firstname} ${user.lastname}</h4>
                                <br/>
                                <p>Username : ${user.username}</p>
                                <p>Email : ${user.email}</p>
                            </c:when>
                            <c:otherwise>
                                Please login or register first
                            </c:otherwise>
                        </c:choose>
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

