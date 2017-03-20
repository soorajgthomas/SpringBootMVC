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
            <h1 class="well">Employee Registration Form</h1>

            <c:if test = "${saveSuccess != '0'}">
                Saved successfully "${saveSuccess}"
            </c:if>

            <div class="col-lg-12 well">
                <div class="row">

                    <form:form method="POST" commandName="employee" action="/addEmployee">
                        <div class="col-sm-12">
                            <form:input path="id" id="id" class="hidden" value="0"/>
                            <form:input path="status" id="status" class="hidden" value="false"/>
                            <form:input path="createdTime" id="createdTime" class="hidden"/>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>First Name <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="firstName" id="firstName" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Last Name <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="lastName" id="lastName" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Country <span style="color: rgb(255, 0, 0)">*</span></label>


                                     <form:select path="nationality" class="form-control" >
                                         <c:forEach var="country" items="${countries}">
                                            <option value="${country.id}">${country.countryName}</option>
                                         </c:forEach>
                                     </form:select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>District <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="district" id="district" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Emirates Id <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="emiratesId" id="emiratesId" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Emirates Id Expiry Date <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input type="date" path="emiratesIdExpiry" id="emiratesIdExpiry" class="form-control"  required="required"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Status <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="employeeStatus" id="employeeStatus" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Passport No <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="passportNo" id="passportNo" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Passport Expiry Date <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input type="date" path="passportExpiry" id="passportExpiry" class="form-control" required="required"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Health Card <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="healthCard" id="healthCard" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Health Card Expiry Date <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input type="date" path="healthCardExpiry" id="healthCardExpiry" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Date of Birth <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input type="date" path="dateOfBirth" id="dateOfBirth" class="form-control" required="required"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Phone <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="phone" id="phone" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Date of Join <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input type="date" path="dateOfJoin" id="dateOfJoin" class="form-control" required="required"/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Profession <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:input path="profession" id="profession" class="form-control" required="required"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Gender <span style="color: rgb(255, 0, 0)">*</span></label>
                                    <form:select path="gender" id="gender" items="${gender}" class="form-control" required="required"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Address <span style="color: rgb(255, 0, 0)">*</span></label>
                                <form:textarea path="address" id="address" rows="3" class="form-control" required="required"/>
                            </div>

                            <div class="form-group">
                                <label>Address 2</label>
                                <form:textarea path="address2" id="address2" rows="3" class="form-control"/>
                            </div>



                            <input type="submit" value="Submit"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>




        <div class="container">
            <h1 class="well">Employee Table</h1>
            <table class="table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>First Name</th>
                  <th class="hidden-xs">Last Name</th>
                  <th class="hidden-xs">Profession</th>
                  <th>Edit</th>
                  <th>View</th>
                  <th class="hidden-xs">Delete</th>
                </tr>
              </thead>
              <c:forEach var="employee" items="${employeeList}" >
                <tr row-index='${employee.id}'>
                    <th scope="row">${employee.id}</th>
                    <td>${employee.firstName}</td>
                    <td class="hidden-xs">${employee.lastName}</td>
                    <td class="hidden-xs">${employee.profession}</td>
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
                <a href="/employee?p=${1}">
                    <button class="btn btn-white" type="button">
                        <i class="fa fa-chevron-left"></i>
                    </button>
                </a>
            </c:if>

            <c:if test="${currentPageNumber eq 1}">
                <c:out value="${currentPageNumber}" />
                <c:if test="${numberOfPages gt 1}">
                    <a href="/employee?p=${2}">2</a>
                    <c:if test="${numberOfPages gt 2}">
                        <a href="/employee?p=${3}">3</a>
                    </c:if>
                </c:if>
            </c:if>

            <c:if test="${currentPageNumber gt 1}">
                <c:if test="${currentPageNumber eq numberOfPages}">
                    <a href="/employee?p=<c:out value="${currentPageNumber-2}"/>"><c:out value="${currentPageNumber-2}" /></a>
                </c:if>
                <a href="/employee?p=<c:out value="${currentPageNumber-1}"/>"><c:out value="${currentPageNumber-1}" /></a>
                <c:out value="${currentPageNumber}" />
                <c:if test="${numberOfPages gt currentPageNumber}">
                    <a href="/employee?p=<c:out value="${currentPageNumber+1}"/>"><c:out value="${currentPageNumber+1}" /></a>
                </c:if>
            </c:if>



            <c:if test="${currentPageNumber lt numberOfPages}">
                <a href="/employee?p=${numberOfPages}">
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
                            <h4 class="modal-title">Employee Details</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>First Name  : </strong>
                                    <em id= vFirstName><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Last Name  : </strong>
                                    <em id= vLastName><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Nationality  : </strong>
                                    <em id= vNationality><em/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>District  : </strong>
                                    <em id= vDistrict><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>EmiratesId  : </strong>
                                    <em id=vEmiratesId><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>EmiratesId Expiry  : </strong>
                                    <em id = vEmiratesIdExpiry><em/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>Status  : </strong>
                                    <em id = vEmployeeStatus><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>PassportNo  : </strong>
                                    <em id = vPassportNo><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Passport Expiry  : </strong>
                                    <em id = vPassportExpiry><em/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>HealthCard  : </strong>
                                    <em id = vHealthCard><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>HealthCard Expiry  : </strong>
                                    <em id = vHealthCardExpiry><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Date Of Birth  : </strong>
                                    <em id = vDateOfBirth><em/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>Phone  : </strong>
                                    <em id = vPhone><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>vDateOfJoin : </strong>
                                    <em id = vDateOfJoin><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>vProfession : </strong>
                                    <em id = vProfession><em/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <strong>Gender  : </strong>
                                    <em id = vGender><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Address : </strong>
                                    <em id = vAddress><em/>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <strong>Address2 : </strong>
                                    <em id = vAddress2><em/>
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
        <script src="js/custom/employee.js"></script>

	</body>
</html>