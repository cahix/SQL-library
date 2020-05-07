<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<body>

<div class="container">
    <form action=${pageContext.request.contextPath}/login method="post">
        <div class="card">
            <div class="card-header text-left font-weight-bold">
                Login
            </div>
            <div class="card-body">

                <div class="form-group">
                    <input type="text" name="username" required class="form-control" placeholder="Enter Username"/>
                </div>
                <div class="form-group">
                    <input type="password" name="password" required class="form-control" placeholder="Enter password"/>
                </div>

                <p style="color:red;">${errorMessage}</p>

            </div>
            <div class="card-footer text-left">
                <input type="submit" value="Login" class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>