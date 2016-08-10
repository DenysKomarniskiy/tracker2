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

	<script type="text/javascript">
		var users = ${users};
		var testings = ${testings};
	</script>

	<c:if test="${not empty template}">
		<jsp:include page="${template}" />
	</c:if>

</body>
</html>


