<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./js/lib/jquery/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="./js/lib/slickgrid/slick.grid.css" />
<link rel="stylesheet" type="text/css" href="./js/lib/slickgrid/slick-default-theme.css" />
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/modal.css" />
<link href="./favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>
	<div class="mdl-layout__container">
		<div class="mdl-layout">
			<header class="mdl-layout__header">
				<div id="load-bar" class="load-bar">
				  <div class="cs-loader-inner">
				    <label>	●</label>
				    <label>	●</label>
				    <label>	●</label>
				    <label>	●</label>
				    <label>	●</label>
				    <label>	●</label>
				  </div>
				</div>
				<div id="mdl-menu-btn" class="mdl-layout__drawer-button">					
					<i class="material-icons">menu</i>					
				</div>
				
				<div class="mdl-layout__header-row">
					<!-- Title -->
					<span class="mdl-layout__title">${title}</span>
					
					<div class="mdl-layout__header-item">
						<div class="mdl-textfield mdl-textfield--expandable">
							<label class="mdl-button mdl-button--icon" for="search-tc" title="Search"> 
								<i class="material-icons">search</i>
							</label>
							<div class="mdl-textfield__expandable-holder search-field">
								<input class="mdl-textfield__input" type="text" id="search-tc" required>
								<label class="mdl-textfield__label" for="sample-expandable"></label>
								<i id="reset-search">×</i>
							</div>
						</div>					
					</div>					
				</div>
				
				<div class="user-data">
					<span class="mdl-layout__title">${sessionScope.user}</span>
				</div>
				
			</header>
			<div id="mdl-drawer" class="mdl-layout__drawer" aria-hidden="true">
				<span class="mdl-layout__title">Tracker</span>
				<nav class="mdl-navigation">
					<a class="mdl-navigation__link" href="testset"><i class="material-icons">class</i> Test Sets</a> 
					<a class="mdl-navigation__link" href="storage"><i class="material-icons">storage</i> Storage</a> 
					<a class="mdl-navigation__link" href="testing"><i class="material-icons">accessible<!-- bug_report --></i> Testing</a> 
					<a class="mdl-navigation__link" href="settings"><i class="material-icons">settings</i> Settings</a>
					<a class="mdl-navigation__link" href="tools"><i class="material-icons">restaurant</i> Tools</a>
				</nav>
			</div>
			<main class="mdl-layout__content">
			
			
				<div class="page-content">
	
					<!-- content goes here -->
	
					<c:if test="${not empty template}">
						<jsp:include page="${template}" />
					</c:if>
	
				</div>
			
			
			</main>
			<div id="mdl-obfuscator" class="mdl-layout__obfuscator"></div>
		</div>
	</div>

	<jsp:include page="modal.jsp" />
	<jsp:include page="notify.jsp" />
	
	<script src="./js/lib/jquery/jquery-2.2.4.min.js"></script>
	<script src="./js/lib/jquery/jquery-ui.min.js"></script>
	<script src="./js/lib/jquery/jquery.event.drop-2.2.js"></script>
	<script src="./js/lib/jquery/jquery.event.drag-2.2.js"></script>
	
	<script src="./js/lib/slickgrid/slick.core.js"></script>
	<script src="./js/lib/slickgrid/slick.editors.js"></script>
	<script src="./js/lib/slickgrid/slick.grid.js"></script>
	<script src="./js/lib/slickgrid/slick.dataview.js"></script>
	<script src="./js/lib/slickgrid/plugins/slick.autotooltips.js"></script>
	<script src="./js/lib/slickgrid/plugins/slick.headermenu.js"></script>
	<script src="./js/lib/slickgrid/plugins/slick.rowselectionmodel.js"></script>
	<script src="./js/main.js"></script>


</body>
</html>


