<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

${users}
<select name="user">
	<c:forEach items="${users}" var="user">
		<option value="${user.id}">${user.id}</option>
	</c:forEach>
</select>




