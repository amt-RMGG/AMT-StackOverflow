<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="question" value="${question}" />
<c:set var="answers" value="${answers}" />

<%@include file="fragments/header.jsp"%>
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
  <script>
      tinymce.init({
                        selector:'textarea',
                        height: 450,
                        toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent'
                   });
  </script>

<body>

<%@include file="fragments/navbar.jsp" %>

  <style>
    .votes input[type="submit"] {
      width: 100%;
      background: #007bff;
      color: #ffffff;
      border: none;
      border-radius: 4px;
    }
  </style>

  <!-- Page Content -->
  <div class="container mainContent">

    <div class="row">

      <!-- Blog Entries Column -->
      <div class="col-md-12 question">
        <div class="card mb-12">
          <div class="card-body">
            <h2 class="card-title">${question.title}</h2>
            <p class="card-text">${question.text}</p>
          </div>
          <div class="card-footer text-muted">
            Posted on ${question.date} by
            <a href="${pageContext.request.contextPath}/user?username=${question.author}">${question.author}</a>
            <div class="row">
              <p class="col-md-12">${question.votes} points</p>
            </div>
            <div class="row">
              <form name="upvote" method="post" action="${pageContext.request.contextPath}/submitVote.do" class="votes col-md-1">
                <input type="hidden" name="objectType" value="question" />
                <input type="hidden" name="username" value="${question.author}" />
                <input type="hidden" name="idQuestion" value="${question.id}" />
                <input type="hidden" name="type" value="UPVOTE" />
                <input class="upvoteButton" type="submit" value="+" />
              </form>
              <form name="downvote" method="post" action="${pageContext.request.contextPath}/submitVote.do" class="votes col-md-1">
                <input type="hidden" name="objectType" value="question" />
                <input type="hidden" name="username" value="${question.author}" />
                <input type="hidden" name="idQuestion" value="${question.id}" />
                <input type="hidden" name="type" value="DOWNVOTE" />
                <input class="downvoteButton" type="submit" value="-" />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="card mb-12">
          <div class="card-body">
            <h2 class="card-title">Réponse(s)</h2>
            <c:forEach items="${answers}" var="answer">
              <!-- Blog Entries Column -->
              <div class="col-md-12 answer">
                <div class="card mb-12">
                  <div class="card-body">
                    <p class="card-text">${answer.text}</p>
                  </div>
                  <div class="card-footer text-muted">
                    Posté le ${answer.date} par
                    <a href="${pageContext.request.contextPath}/user?username=${answer.author}">${answer.author}</a>
                    <div class="row">
                      <p class="col-md-12">${answer.votes} points</p>
                    </div>
                    <div class="row">
                      <form name="upvote" method="post" action="${pageContext.request.contextPath}/submitVote.do" class="votes col-md-1">
                        <input type="hidden" name="objectType" value="answer" />
                        <input type="hidden" name="username" value="${answer.author}" />
                        <input type="hidden" name="idAnswer" value="${answer.id}" />
                        <input type="hidden" name="idQuestion" value="${question.id}" />
                        <input type="hidden" name="type" value="UPVOTE" />
                        <input class="upvoteButton" type="submit" value="+" />
                      </form>
                      <form name="downvote" method="post" action="${pageContext.request.contextPath}/submitVote.do" class="votes col-md-1">
                        <input type="hidden" name="objectType" value="answer" />
                        <input type="hidden" name="username" value="${answer.author}" />
                        <input type="hidden" name="idAnswer" value="${answer.id}" />
                        <input type="hidden" name="idQuestion" value="${question.id}" />
                        <input type="hidden" name="type" value="DOWNVOTE" />
                        <input class="downvoteButton" type="submit" value="-" />
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
      
    <div class="row">
      <div class="col-md-12">
        <div class="card col-md-12">
          <form id="newAnswer" class="shortForm" method="POST" action="submitAnswer.do">
            <h3>Votre réponse</h3>
            <textarea id="tfText" name="text" form="newAnswer" class="col-md-12"> </textarea>
            <input type="hidden" name="questionId" value="${question.id}">
            <button id="bSubmitQuestion" class="btn btn-primary btn-small" type="submit">Répondre</button>
          </form>
        </div>

      </div>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

<%@include file="fragments/footer.jsp" %>

</body>

</html>
