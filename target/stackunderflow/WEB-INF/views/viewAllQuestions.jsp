<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Questions</title>
  </head>
  <body>
    <h2>Welcome to FlowOverStack!</h2>

    <table>
      <tr>
        <td>Title</td>
        <td>Author</td>
        <td>Id</td>
      </tr>
      <c:forEach items="${questions}" var="question">
      <tr>
        <td><a href="/mvc-simple/question?id=${question.id}">${question.title}</a></td>
        <td>${question.author}</td>
        <td>${question.id}</td>
      </tr>
      </c:forEach>
    </table>
  </body>
</html>
