<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">Stack Underflow</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="input-group" style="max-width: 500px;">
            <input type="text" class="form-control" placeholder="Search for...">
            <span class="input-group-append">
            <button class="btn btn-secondary" type="button">Go!</button>
          </span>
        </div>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <c:choose>
                <c:when test="${currentUser != null}">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/askQuestion" class="btn btn-primary">Poser une question</a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/user" class="btn btn-info">${currentUser.firstname} ${currentUser.lastname}</a>
                </li>
                <li class="nav-item">
                    <form method="POST" action="${pageContext.request.contextPath}/logout.do">
                        <input class="btn btn-outline-light" type="submit" value="Déconnexion">
                    </form>
                </li>
                </c:when>
                <c:otherwise>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login</a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-primary">Sign in</a>
                </li>
                </c:otherwise>

                </c:choose>
            </ul>
        </div>
</nav>
</html>
