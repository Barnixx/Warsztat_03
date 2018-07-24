<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 23.07.2018
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="WEB-INF/header.jsp" %>
<div id="solutions">
    <table border="2">
        <caption>Solutions</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Created</th>
            <th>Updated</th>
            <th>Exercise ID</th>
            <th>User ID</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${solutions}" var="solution">
            <tr>
                <td>${solution.id}</td>
                <td>${solution.created}</td>
                <td>${solution.updated}</td>
                <td>${solution.exercise_id}</td>
                <td>${solution.users_id}</td>
                <td><a href='<c:url value="/exercise_solution?exercise=${solution.id}"/>'>More</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
</body>
<%@ include file="WEB-INF/footer.jsp" %>
</html>
