<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="questions" scope="request" type="io.stackunderflow.flow.application.question.QuestionsDTO"/>

<%@include file="fragments/header.jsp"%>

<body>
<%@include file="fragments/navbar.jsp" %>

  <!-- Page Content -->
  <div class="container mainContent">

    <div class="row">
        
      <!-- Blog Entries Column -->
      <div class="col-md-8">

        <h1 class="my-4">Liste des questions</h1>
          
          <c:forEach items="${questions.questions}" var="question">
            <!-- Blog Post -->
            <div class="card mb-4">
              <div class="card-body">
                <h2 class="card-title">${question.title}</h2>
                <p class="card-text">${question.text}</p>
                <a href="${pageContext.request.contextPath}/question?id=${question.id}" class="btn btn-primary">Read More</a>
              </div>
              <div class="card-footer text-muted">
                Posted on ${question.date} by ${question.author}
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
