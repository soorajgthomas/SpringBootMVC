<!doctype html>
<%@page isELIgnored="false" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ExampleOne</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
  </head>

  <body>
    <nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin:0px">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle Navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Sample</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li><a href="index.html">Home</a></li>
            <li><a href="index.html">About</a></li>
            <li><a href="index.html">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Dropdown <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Link1</a></li>
                <li><a href="#">Link2</a></li>
                <li><a href="#">Link3</a></li>
                <li class="divider"></li>
                <li><a href="#">Link4</a></li>
                <li class="divider"></li>
                <li><a href="#">Link5</a></li>
              </ul>
            </li>

            <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
                <li><a href="sadmin/page">Super Admin Home</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')">
                <li><a href="admin/page">Admin Home</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN','ROLE_USER')">
                <li><a href="user/page">User Home</a></li>
            </sec:authorize>
          </ul>

          <c:if test="${pageContext.request.userPrincipal.name != null}">
              <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">

                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"> ${pageContext.request.userPrincipal.name} <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="logout">logout</a></li>
                  </ul>
                </li>
              </ul>
           </c:if>

        </div>
      </div>
    <nav>

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>

</html>
