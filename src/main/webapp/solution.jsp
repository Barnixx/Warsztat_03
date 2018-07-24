<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 24.07.2018
  Time: 01:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>
<h2>Zadanie nr ${solution.exercise_id}</h2>
<h3>Rozwiązanie nr ${solution.id}</h3>
<p>
<pre>${solution.description}</pre>
</p>
</body>
</html>
