<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="question" value="${question}" />

<%@include file="fragments/header.jsp"%>

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

                            <form id="newQuestion" method="POST" action="submitQuestion.do">
                                <h3>Title</h3>
                                <textarea id="tfTitle" name="title" form="newQuestion"> </textarea>
                                <h3>Text</h3>
                                <textarea id="tfText" name="text" form="newQuestion"> </textarea>
                                <button id="bSubmitQuestion" type="submit">Submit question</button>
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
