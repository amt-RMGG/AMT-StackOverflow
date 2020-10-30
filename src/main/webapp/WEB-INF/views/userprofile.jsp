<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" value="${user}" />
<%@include file="fragments/header.jsp"%>

<body>

<%@include file="fragments/navbar.jsp" %>

<!-- Page Content -->
<div class="container mainContent">

    <div class="row">

        <div class="col-md-6">
            <div class="col-md-12">
                <div class="card-body">
                    <h5 class="card-header">User profile</h5>
                    <div class="">
                        <c:choose>
                            <c:when test="${user != null}">
                                <p class="text-name">${user.firstname} ${user.lastname}</p>
                                <p>Username : ${user.username}</p>
                                <p>Email : ${user.email}</p>
                                <c:choose>
                                    <c:when test="${user.username == currentUser.username}">
                                        <a class="text-small-link" onclick="document.getElementById('prompt').style.display='block'" href="#">edit</a>
                                    </c:when>
                                </c:choose>

                            </c:when>
                            <c:otherwise>
                                User not found
                            </c:otherwise>
                        </c:choose>
                    </div>


                    <!-- The Modal -->
                    <div id="prompt" class="modal">
                        <span onclick="document.getElementById('prompt').style.display='none'"
                        class="close" title="Close">&times;</span>

                        <!-- Modal Content -->
                        <form class="modal-content animate" action="updateUser.do" method="POST">

                            <div class="container">

                                <input style="display:none" id="currentuname" type="text" name="currentuname" value="${user.username}" required>

                                <label for="fname"><b>First name</b></label>
                                <input id="fname" type="text" placeholder="New first name" name="fname" value="${user.firstname}" required>

                                <label for="lname"><b>Last name</b></label>
                                <input id="lname" type="text" placeholder="New last name" name="lname" value="${user.lastname}" required>

                                <label for="email"><b>Email</b></label>
                                <input id="email" type="text" placeholder="New email" name="email" value="${user.email}" required>

                                <button type="submit">Confirm</button>
                            </div>

                        </form>
                    </div>



                </div>
            </div>
        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<%@include file="fragments/footer.jsp" %>

</body>

<script>
    // Get the modal
    var modal = document.getElementById('prompt');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</html>

