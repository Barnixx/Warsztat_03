<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 24.07.2018
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>

<span>Edit Group</span>

<form method="post" action="/editGroup">
    <label for="name">Name</label>
    <input id="name" type="text" name="name" placeholder="${groupName}">
    <button type="submit">Submit</button>
</form>

</body>
</html>
