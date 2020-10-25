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
                    <h5 class="card-header">User profile</h5>
                    <div class="input-group">
                        <c:choose>
                            <c:when test="${currentUser != null}">
                                <h4>${currentUser.firstname} ${currentUser.lastname}</h4> <br/>
                                <p>Also known as ${currentUser.username}</p>
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

