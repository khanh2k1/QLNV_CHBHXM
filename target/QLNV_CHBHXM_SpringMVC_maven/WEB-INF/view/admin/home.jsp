<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Spring MVC tutorial - Home JSP</title>
  <link rel="stylesheet" href="/khanh/build/css/custom.css/">
</head>
<body>
<h1>Hello World!</h1>
<h4>Message- </h4><span>${message}</span>
<form:form action="showUser" modelAttribute="user" method="post">
  <tr>
    <td>
      <form:label path="firstName">First Name</form:label>
    </td>
    <td>
      <form:input path="firstName" id="firstname" />
    </td>
  </tr>
  <tr>
    <td>
      <form:label path="lastName">Last Name</form:label>
    </td>
    <td>
      <form:input path="lastName" id="lastname" />
    </td>
  </tr>
  <input type="submit" value="Submit">
</form:form>
</body>
</html>