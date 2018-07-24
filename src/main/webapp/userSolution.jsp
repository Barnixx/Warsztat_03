<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 24.07.2018
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>

<table border="2">
    <caption>Solutions</caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>UserName</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td><a href='<c:url value="/exercise_solution?exercise=${user.id}"/>'>More</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
