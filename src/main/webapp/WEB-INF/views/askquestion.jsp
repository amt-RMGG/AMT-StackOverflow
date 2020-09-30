<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="question" value="${question}" />

<!DOCTYPE html>
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
    <link href="/stackunderflow/assets/css/blog-home.css" rel="stylesheet">
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script>
        tinymce.init({
            selector:'textarea',
            height: 450,
            toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent'
        });
    </script>
</head>

<body>

    <%@include file="fragments/navbar.jsp" %>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Page Content -->
            <div class="container">

                <div class="row">
                    <div class="col-md-12">
                        <div class="card mb-12">
                            <form name="ask" method="post" action="${pageContext.request.contextPath}/askService"></form>
                                <input type="text" class="form-control" name="title" placeholder="Titre de votre question">
                                <textarea name="question"></textarea>
                                <input type="submit" class="btn btn-primary" value="Posez votre question"/>
                            </form>
                        </div>

                    </div>

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

    <%@include file="fragments/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="/stackunderflow/assets/vendor/jquery/jquery.min.js"></script>
<script src="/stackunderflow/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
