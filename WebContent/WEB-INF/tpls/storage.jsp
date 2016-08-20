<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="control-panel">
</div>

<div id="main-grid"></div>

<div class="add">
	<div class="cont circle-b">
		<button id="b-add" class="circle-b">+</button>
	</div>
</div>

<form id="add-testcase-form" class="flex-container" style="display:none" onsubmit="return false;">
	<!-- don't change position of this input -->
	<input type="submit" class="hide" id="f-submit"> 
	
	<div>
		<span>TC ID</span> <input name="edt_tc_id" required>
	</div>

	<div>
		<span>Author</span> <select name="edt_author" required>
			<c:forEach items="${users}" var="user">
				<option value="${user.id}">${user.id}</option>
			</c:forEach>
		</select>
	</div>

	<div>
		<span>Test Set</span> <select name="edt_test_set" required>
			<c:forEach items="${testsets}" var="set">
				<option value="${set.id}">${set.getLocalSet()}</option>
			</c:forEach>
		</select>
	</div>
	
	<div>
		<span>Step Count</span> <input name="edt_step_num" required pattern="[0-9]">
	</div>

	<div>
		<span>Duration</span> <input name="edt_duration" required pattern="[0-9]">
	</div>

	<div>
		<span>Auto IDE</span> <input name="edt_auto_ide" required>
	</div>

	<div>
		<span>Features</span> <input name="edt_features">
	</div>

	<div>
		<span>Run path</span> <input name="edt_run_path">
	</div>

	<div>
		<span>Run param</span> <input name="edt_run_param">
	</div>
	
</form>


<script type="text/javascript">	
	var view = {};
	view['data'] = ${tcs};
	view['users'] = ${jusers};		
</script>