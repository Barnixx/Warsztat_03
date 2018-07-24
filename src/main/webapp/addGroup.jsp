<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 24.07.2018
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>
<span>Add Group</span>
<form method="post" action="/addGroup">
    <label for="name">Group Name</label>
    <input type="text" name="name" id="name">
    <button type="submit">Submit</button>
</form>

</body>
</html>
