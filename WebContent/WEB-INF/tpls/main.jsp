<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Enumeration"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./js/lib/jquery/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="./js/lib/slickgrid/slick.grid.css" />
<link rel="stylesheet" type="text/css" href="./js/lib/slickgrid/slick-default-theme.css" />
<link rel="stylesheet" type="text/css" href="./css/main.css" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>

	<div class="header">
		<form id="login-testing" action="testing" method="post">			
			<select name="user_id">
				<c:forEach items="${users}" var="user">
					<option ${user.id == selectedUser ? 'selected' : ''} value="${user.id}">${user.id}</option>
				</c:forEach>
				<option value="all">All</option>
			</select> 
			<select name="testing_id">
				<c:forEach items="${testings}" var="testing">
					<option ${testing.id == selectedTesting ? 'selected' : ''} value="${testing.id}">${testing.name}</option>
				</c:forEach>
			</select> 
			<input type="submit" name="submit" value="Testing" /> 
			<a href="storage">Storage</a>	
		</form>
	</div>

	<c:if test="${not empty template}">
		<jsp:include page="${template}" />
	</c:if>

</body>
</html>


