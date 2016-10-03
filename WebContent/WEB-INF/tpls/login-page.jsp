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
	<div class="login-form widget invert-color">
		<header class="mdl-layout__header">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout__title">Welcome Back!</span>				
			</div>				
		</header>
		
		<form action="loginpage" method="post">

			<c:if test="${not empty logged}">
				<div class="row">
					<span class="mdl-textfield">You are logged as <strong class="mdl-textfield">${logged}</strong></span> 
				</div>
				<input type="hidden" name="action" value="Logout" />
				<div class="row">
					<a class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" data-upgraded=",MaterialButton" href="/tracker2/testing">Work</a>
					<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" data-upgraded=",MaterialButton">Logout</button>
				</div>
				
			</c:if>
			
			<c:if test="${empty logged}">
				<div class="row">
					<div class="mdl-textfield floating-label">
					   <input type="text" name="login" id="login" class="mdl-textfield__input" required>
					   <label class="float-palceholder" for="login">ISD login</label>
					   <label class="mdl-textfield__label" for="sample-expandable"></label>
					</div>					
				</div>
				<div class="row">
					<div class="mdl-textfield floating-label">
					   <input type="password" name="passw" id="passw" class="mdl-textfield__input" required>
					   <label class="float-palceholder" for="passw">ISD password</label>
					   <label class="mdl-textfield__label" for="sample-expandable"></label>
					</div>
				</div>
				
				<input type="hidden" name="action" value="Enter" />
				<div class="row">
					<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" data-upgraded=",MaterialButton">Enter</button>
				</div>
			</c:if>			
		</form>
	</div>
</body>
</html>