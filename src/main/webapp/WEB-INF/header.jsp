<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 23.07.2018
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link href='<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" />'
          rel="stylesheet" type="text/css">
    <link href='<c:url value="/css/style.css" />' rel="stylesheet" type="text/css">
    <script src='<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"/>'
            integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous">
    </script>
    <script src='<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.bundle.min.js"/>'
            integrity="sha384-CS0nxkpPy+xUkNGhObAISrkg/xjb3USVCwy+0/NMzd5VxgY4CMCyTkItmy5n0voC" crossorigin="anonymous">
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">***</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home Page <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/user_group"/>'>User Groups</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/panelAdmin"/>'>Admin Panel</a>
            </li>
        </ul>
    </div>
</nav>

