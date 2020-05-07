<%@ page import="control.WebAppController" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Book Directory</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css"/>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body>

<%
    boolean loggedIn = WebAppController.getInstance().userIsLoggedIn();
    //redirect user to login page if not logged in
    if(!loggedIn){
        response.sendRedirect("login.jsp");
    }
%>

<div class = "container">

    <div class="float-right">
        <form action="${pageContext.request.contextPath}/webAppController" method="post">
            <input type="submit" value="Logout" name="action">
        </form>
    </div>

    <h1>Book Directory</h1>
    <hr/>

    <p>
        <button class = "btn btn-primary" onclick="window.location.href = '${pageContext.request.contextPath}/book_creation.jsp'">Add Book</button>
    </p>

    <table class = "table table-striped table-bordered" id="booktable">
        <thead>
        <tr class = "thead-dark">
            <th>Status</th>
            <th>Name</th>
            <th>Autor</th>
            <th>Published</th>
            <th>ISBN</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td <c:choose>
                    <c:when test="${book.getStatus() eq 'Borrowed'}">
                        style="color:#FF0000"
                    </c:when>
                    <c:otherwise>
                        style="color:#008000"
                    </c:otherwise>
                </c:choose>>
                        ${book.getStatus()}</td>
                <td>${book.getName()}</td>
                <td>${book.getAutor()}</td>
                <td>${book.getPublished()}</td>
                <td>${book.getIsbn()}</td>
                <td>
                    <a href = "${pageContext.request.contextPath}/directoryController?action=LOAN&isbn=${book.isbn}"
                       <c:if test = "${book.getStatus() eq 'Borrowed'}"> class="disabled" </c:if>>
                        Loan</a>
                    |
                    <a href = "${pageContext.request.contextPath}/directoryController?action=RETURN&isbn=${book.isbn}"
                            <c:if test = "${book.getStatus() eq 'Available'}"> class="disabled" </c:if>>
                        Return</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.js"></script>
<script>
    $(document).ready(function(){
        $("#booktable").DataTable( {
            "order": [[ 1, "desc" ]]
        } );
    })
</script>
</body>
</html>