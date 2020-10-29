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

  <!-- Page Content -->
  <div class="container mainContent">

    <div class="row">

      <!-- Blog Entries Column -->
      <div class="col-md-12">
        <div class="card mb-12">
          <div class="card-body">
            <h2 class="card-title">${question.title}</h2>
            <p class="card-text">${question.text}</p>
          </div>
          <div class="card-footer text-muted">
            Posted on ${question.date} by
            <a href="${pageContext.request.contextPath}/user?username=${question.author}">${question.author}</a>
            <p>votes</p>
            <a href="#">[+]</a>
            <a href="#">[-]</a>
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
              <div class="col-md-12">
                <div class="card mb-12">
                  <div class="card-body">
                    <p class="card-text">${answer.text}</p>
                  </div>
                  <div class="card-footer text-muted">
                    Posted on ${answer.date} by
                    <a href="${pageContext.request.contextPath}/user?username=${answer.author}">${answer.author}</a>
                    <p>votes</p>
                    <form name="upvote" method="post" action="${pageContext.request.contextPath}/submitVote.do" class="col-md-12">
                      <input type="hidden" name="type" value="UPVOTE" />
                      <input type="submit" value="+" />
                    </form>
                    <form name="downvote" method="post" action="${pageContext.request.contextPath}/submitVote.do" class="col-md-12">
                      <input type="hidden" name="type" value="DOWNVOTE" />
                      <input type="submit" value="-" />
                    </form>
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
        <div class="card mb-12">
          <form id="newAnswer" class="shortForm" method="POST" action="submitAnswer.do">
            <h3>Votre réponse</h3>
            <textarea id="tfText" name="text" form="newAnswer"> </textarea>
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
