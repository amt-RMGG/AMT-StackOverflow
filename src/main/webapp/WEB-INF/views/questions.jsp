<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="questions" scope="request" type="io.stackunderflow.flow.application.question.QuestionsDTO"/>

<html lang="fr">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Stack Underflow</title>

  <!-- Bootstrap core CSS -->
  <link href="/stackunderflow/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/stackunderflow//assets/css/blog-home.css" rel="stylesheet">

</head>

<body>
<%@include file="fragments/navbar.jsp" %>

  <!-- Page Content -->
  <div class="container">

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
                <a href="stackunderflow/" class="btn btn-primary disabled">Read More &rarr;</a>
              </div>
              <div class="card-footer text-muted">
                Posted on January 1, 2020 by
                <a href="#">${question.author}</a>
              </div>
            </div>
          </c:forEach>
          
      </div>

  </div>
  <!-- /.container -->

<%@include file="fragments/footer.jsp" %>

  <!-- Bootstrap core JavaScript -->
  <script src="/stackunderflow/assets/vendor/jquery/jquery.min.js"></script>
  <script src="/stackunderflow/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
