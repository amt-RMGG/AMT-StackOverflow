<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
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
                    <a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Profil</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="btn btn-primary">Poser une question</a>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${currentUser != null}">
                            <li style="color:white">${currentUser.firstname}</li>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login</a>
                        </c:otherwise>
                        
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>
</html>
