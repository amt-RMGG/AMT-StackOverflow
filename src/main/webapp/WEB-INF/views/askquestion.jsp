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
    <div class="container mainContent">

        <div class="row">

            <!-- Page Content -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                            <form id="newQuestion" class="shortForm" method="POST" action="submitQuestion.do">
                                <h3>Title</h3>
                                <input type="text" class="short-text-input" id="title" name="title">
                                <h3>Text</h3>
                                <textarea id="tfText" name="text" form="newQuestion"> </textarea>
                                <button id="bSubmitQuestion" class="btn btn-primary btn-small" type="submit">Submit question</button>
                            </form>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
    </div>

    <%@include file="fragments/footer.jsp" %>

</body>

</html>
