<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Book</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css"/>

</head>
<body>
<div class = "container">

    <div class="float-right">
        <form action="${pageContext.request.contextPath}/webAppController" method="post">
            <input type="submit" value="Logout" name="action">
        </form>
    </div>

    <h1>Add Book</h1>
    <hr/>

    <div class = "row">
        <div class = "col-md-4">
            <form action = "${pageContext.request.contextPath}/bookCreation" method="post">

                <div class = "form-group">
                    <input type = "text" class = "form-control" name = "name" placeholder = "Name (max. 30 Characters)"/>
                </div>

                <div class = "form-group">
                    <input type = "text" class = "form-control" name = "autor" placeholder = "Autor (max. 30 Characters)"/>
                </div>

                <div class = "form-group">
                    <input type = "text" class = "form-control" name = "published" placeholder = "Release Year (YYYY)"/>
                </div>

                <div class="form-group">
                    <input type = "text" class = "form-control" name = "isbn" placeholder = "ISBN"/>
                </div>

                <p style="color:red;">${errorMessage}</p>

                <button type = "submit" class = "btn btn-primary">Save</button>
            </form>
        </div>
    </div>
    <a href = "${pageContext.request.contextPath}/login">Back to Directory</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>