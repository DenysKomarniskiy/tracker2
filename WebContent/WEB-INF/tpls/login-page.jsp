<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<form action="loginpage" method="post">
	<select name="user_id">
		<c:forEach items="${users}" var="user">
			<option value="${user.id}">${user.id}</option>
		</c:forEach>
	</select> 
	<select name="testing_id">
		<c:forEach items="${testings}" var="testing">
			<option value="${testing.id}">${testing.name}</option>
		</c:forEach>
	</select> 
	<input type="submit" name="testing" value="Testing" /> 
	<a href="storage">Storage</a>	
</form>