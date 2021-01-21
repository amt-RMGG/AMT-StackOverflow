<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="questions" scope="request" type="io.stackunderflow.flow.application.question.QuestionsDTO"/>

<%@include file="fragments/header.jsp"%>

<body>
<%@include file="fragments/navbar.jsp" %>
<% pageContext.setAttribute("awardedBadge", request.getParameter("awardedBadge"));%>

  <!-- Page Content -->
  <div class="container mainContent">

    <div class="row">
      <c:if test="${awardedBadge.length() > 0}">
        <div class="col-md-12 question">
          <div class="card mb-12">
            <div class="card-body">
              <h4>
                Vous avez gagné un badge : ${awardedBadge} ! <br>
                Vos badges sont visibles <a href="${pageContext.request.contextPath}/badges">ici</a>
              </h4>
            </div>
          </div>
        </div>
      </c:if>
      <!-- Blog Entries Column -->
      <div class="col-md-8">

        <h1 class="my-4">Liste des questions</h1>
          
          <c:forEach items="${questions.questions}" var="question">
            <!-- Blog Post -->
            <div class="card mb-4">
              <div class="card-body">
                <h2 class="card-title">${question.title}</h2>
                <p class="card-text">${question.text}</p>
                <a href="${pageContext.request.contextPath}/question?id=${question.id}" class="btn btn-primary">Voir plus</a>
              </div>
              <div class="card-footer text-muted">
                Posté le ${question.date} par <a href="${pageContext.request.contextPath}/user?username=${question.author}">${question.author}</a>
              </div>
            </div>
          </c:forEach>
          
      </div>
    </div>
  </div>
  <!-- /.container -->

<%@include file="fragments/footer.jsp" %>

</body>

</html>
