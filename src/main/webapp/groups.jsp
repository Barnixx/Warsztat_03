<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 24.07.2018
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>
<div>
    <span><a href='<c:url value="/addGroup"/>'>Add Group</a></span>
</div>
<div id="Groups">
    <table border="2">
        <caption>User Groups</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userGroups}" var="group">
            <tr>
                <td>${group.id}</td>
                <td>${group.name}</td>
                <td><a href='<c:url value="/editGroup?groupId=${group.id}&groupName=${group.name}"/>'>Edit</a> <a
                        href='<c:url value="/deleteGroup?groupId=${group.id}"/>'>Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>

</body>
</html>
