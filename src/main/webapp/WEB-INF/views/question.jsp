<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="question" value="${question}" />

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
  <div class="container">

    <div class="row">

      <!-- Blog Entries Column -->
      <div class="col-md-12">
        <div class="card mb-12">
          <div class="card-body">
            <h2 class="card-title">${question.title}</h2>
            <p class="card-text">${question.body}</p>
          </div>
          <div class="card-footer text-muted">
            Posted on January 1, 2020 by
            <a href="#">${question.author}</a>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="card mb-12">
          <div class="card-body">
            <h2 class="card-title">Réponse</h2> by author
            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>
          </div>
        </div>
      </div>
    </div>
      
    <div class="row">
      <div class="col-md-12">
        <div class="card mb-12">
          <textarea id="comment"></textarea>
            <a href="#" class="btn btn-primary">Répondre</a>
        </div>

      </div>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

<%@include file="fragments/footer.jsp" %>

</body>

</html>
