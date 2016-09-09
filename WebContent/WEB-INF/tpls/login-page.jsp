<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
<title>Please Login</title>
</head>

<body>
	<div class="login-form widget">
		<h3>Please login</h3>
		<form action="loginpage" method="post">

			<c:if test="${not empty logged}">
				You are logged as <strong>${logged}</strong> 
				<input type="submit" name="action" value="Logout" />
			</c:if>
			
			<c:if test="${empty logged}">
				<div class="row">
					<label>ISD login</label><input type="text" name="login" required />
				</div>
				<div class="row">
					<label>ISD password</label><input type="password" name="passw" required />
				</div>
				<input type="submit" name="action" value="Enter" />
			</c:if>			
		</form>
	</div>
</body>
</html>