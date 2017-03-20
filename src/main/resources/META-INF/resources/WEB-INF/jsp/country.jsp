<!doctype html>
<%@page isELIgnored="false" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ExampleOne</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

    <div>
        <jsp:include page="navigation.jsp" />
    </div>

        <div class="container">
            <h1 class="well">Country Creation</h1>

            <c:if test = "${saveSuccess != '0'}">
                Saved successfully "${saveSuccess}"
            </c:if>

            <div class="col-lg-12 well">
                <div class="row">

                    <form:form method="POST" commandName="country" action="/addCountry">
                        <div class="col-sm-12">
                                    <form:input path="id" id="id" class="hidden" value="0"/>
                                    <form:input path="status" id="status" class="hidden" value="false"/>
                                    <form:input path="createdTime" id="createdTime" class="hidden"/>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Country Name <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="countryName" id="countryName" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Country Short Name <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="countryShortName" id="countryShortName" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Currency Name <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="currencyName" id="currencyName" class="form-control" required="required"/>
                                </div>
                            </div>

                            <input type="submit" value="Submit"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>




        <div class="container">
            <h1 class="well">Country Table</h1>
            <table class="table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Country Name</th>
                  <th class="hidden-xs">Country Short Name</th>
                  <th class="hidden-xs">Currency Code</th>
                  <th>Edit</th>
                  <th>View</th>
                  <th class="hidden-xs">Delete</th>
                </tr>
              </thead>
              <c:forEach var="country" items="${countryList}" >
                <tr row-index='${country.id}'>
                    <th scope="row">${country.id}</th>
                    <td>${country.countryName}</td>
                    <td class="hidden-xs">${country.countryShortName}</td>
                    <td class="hidden-xs">${country.currencyName}</td>
                    <td><button class="btn btn-primary btn-xs editId"><span class="glyphicon glyphicon-pencil"></span></button></td>
                    <td><button class="btn btn-success btn-xs viewId" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-eye-open"></span></button></td>
                    <td><button class="btn btn-danger btn-xs hidden-xs deleteId"><span class="glyphicon glyphicon-trash"></span></button></td>
                </tr>
              </c:forEach>
            </table>
            <c:set var="currentPageNumber" value="${currentPageNumber}" />

            <p align="right" style="margin-right: 15px;">
            <%--For displaying Previous link --%>
            <c:if test="${currentPageNumber gt 1}">
                <a href="/country?p=${1}">
                    <button class="btn btn-white" type="button">
                        <i class="fa fa-chevron-left"></i>
                    </button>
                </a>
            </c:if>

            <c:if test="${currentPageNumber eq 1}">
                <c:out value="${currentPageNumber}" />
                <c:if test="${numberOfPages gt 1}">
                    <a href="/country?p=${2}">2</a>
                    <c:if test="${numberOfPages gt 2}">
                        <a href="/country?p=${3}">3</a>
                    </c:if>
                </c:if>
            </c:if>

            <c:if test="${currentPageNumber gt 1}">
                <c:if test="${currentPageNumber eq numberOfPages}">
                    <c:if test="${currentPageNumber gt 2}">
                        <a href="/country?p=<c:out value="${currentPageNumber-2}"/>"><c:out value="${currentPageNumber-2}" /></a>
                    </c:if>
                </c:if>
                <a href="/country?p=<c:out value="${currentPageNumber-1}"/>"><c:out value="${currentPageNumber-1}" /></a>
                <c:out value="${currentPageNumber}" />
                <c:if test="${numberOfPages gt currentPageNumber}">
                    <a href="/country?p=<c:out value="${currentPageNumber+1}"/>"><c:out value="${currentPageNumber+1}" /></a>
                </c:if>
            </c:if>



            <c:if test="${currentPageNumber lt numberOfPages}">
                <a href="/country?p=${numberOfPages}">
                    <button class="btn btn-white" type="button">
            	        <i class="fa fa-chevron-right"></i>
            		</button>
            	</a>
            </c:if>
        </div>


        <!-- Modal -->
        <div class="container">
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog modal-lg">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Country Details</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>Country Name  : </strong>
                                    <em id= vCountryName><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Country Short Name  : </strong>
                                    <em id= vCountryShortName><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Currency  : </strong>
                                    <em id= vCurrencyName><em/>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.cookie.js"></script>
        <script type="text/javascript">
            var csrfParameter = '${_csrf.parameterName}';
            var csrfToken = '${_csrf.token}';
        </script>
        <script src="js/custom/country.js"></script>

	</body>
</html>