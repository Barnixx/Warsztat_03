<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 24.07.2018
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>
<div id="solutions">
    <table border="2">
        <caption>Groups</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersGroups}" var="group">
            <tr>
                <td>${group.id}</td>
                <td>${group.name}</td>
                <td><a href='<c:url value="/users_from_group?group_id=${group.id}"/>'>See Users</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>


</body>
</html>
