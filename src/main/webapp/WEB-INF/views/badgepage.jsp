
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:set var="badges" value="${badges}" />--%>

<%@include file="fragments/header.jsp"%>
<body>
<%@include file="fragments/navbar.jsp" %>
<!-- Page Content -->
<div class="container mainContent">
    <div class="row">
        <div class="col-md-6">
            <div class="col-md-12">
                <div class="card-body">
                    <h5 class="card-header">Badges optenus</h5>
                    <table style="width:100%">
                        <tr style="font-size: large">
                            <td>Id</td>
                            <td>Nom</td>
                            <td>Experience</td>
                        </tr>
                        <c:forEach items="${userBadges}" var="u_badge">
                            <tr>
                                <td>${u_badge.id}</td>
                                <td>${u_badge.name}</td>
                                <td>${u_badge.experienceValue}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <h5 class="card-header">Badges disponibles</h5>
                        <table style="width:100%">
                            <tr style="font-size: large">
                                <td>Id</td>
                                <td>Nom</td>
                                <td>Experience</td>
                            </tr>
                        <c:forEach items="${availaibleBadges}" var="badge">
                            <tr>
                                <td>${badge.id}</td>
                                <td>${badge.name}</td>
                                <td>${badge.experienceValue}</td>
                            </tr>
                        </c:forEach>
                        </table>
                </div>

            </div>
        </div>
        <div class="col-md-6">
            <div class="col-md-12">
                <div class="card-body">
                    <h5 class="card-header">Top 10 utilisateurs</h5>
                    <table style="width:100%">
                        <tr style="font-size: large">
                            <td>No.</td>
                            <td>Username</td>
                            <td>Experience value</td>
                        </tr>
                    <c:forEach begin="0" items="${topUsers}" var="user" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${user.username}</td>
                            <td>${user.experienceValue}</td>
                        </tr>
                    </c:forEach>
                    </table>
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