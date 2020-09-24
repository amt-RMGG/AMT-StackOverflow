<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="question" value="${question}" />
<html>
  <head>
    <title>Questions</title>
    <style>
      td {
        border:1px solid black;
      }
    </style>
  </head>
  <body>
    <h2>Detail question</h2>

    <table>
      <tr>
        <td>Title</td>
        <td>Author</td>
        <td>Description</td>
      </tr>
      <tr>
        <td>${question.title}</td>
        <td>${question.author}</td>
        <td>${question.body}</td>
      </tr>
    </table>
  </body>
</html>
