<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="control-panel">
</div>

<div id="main-grid"></div>

<div class="add">
	<div class="cont circle-b">
		<button id="b-add" class="circle-b">+</button>
	</div>
</div>

<form id="add-testcase-form" class="flex-container-col hide invert-color" onsubmit="return false;">
	<!-- don't change position of this input -->
		
	<input type="hidden" name="action" value="add">
	
	<div class="row flex-container-row">
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_tc_id" class="mdl-textfield__input" required>
   			<label class="float-palceholder">TC ID</label>
   			<label class="mdl-textfield__label"></label>
		</div>	
		
		<div class="mdl-select">			
			<select name="edt_author" required>
				<option selected></option>
				<c:forEach items="${users}" var="user">
					<option value="${user.id}">${user.id}</option>
				</c:forEach>				
			</select>			
			<label class="float-palceholder">Author</label>
		</div>	
		
		<div class="mdl-select">			
			<select name="edt_test_set" required>
				<option selected></option>
				<c:forEach items="${testsets}" var="set">
					<option value="${set.id}">${set.getLocalSet()}</option>
				</c:forEach>			
			</select>			
			<label class="float-palceholder">Test Set</label>
		</div>	
	</div>


	<div class="row flex-container-row">
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_step_num" class="mdl-textfield__input" required pattern="[0-9]+">
   			<label class="float-palceholder">Step Count</label>
   			<label class="mdl-textfield__label"></label>
		</div>	
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_duration" class="mdl-textfield__input" required pattern="[0-9]+">
   			<label class="float-palceholder">Duration</label>
   			<label class="mdl-textfield__label"></label>
		</div>			
	</div>	
	
	<div class="row flex-container-row">
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_auto_ide" class="mdl-textfield__input" required>
   			<label class="float-palceholder">Auto IDE</label>
   			<label class="mdl-textfield__label"></label>
		</div>	
		<div class="mdl-textfield floating-label">
   			<input type="text" name="apps" class="mdl-textfield__input" required>
   			<label class="float-palceholder">Apps</label>
   			<label class="mdl-textfield__label"></label>
		</div>			
	</div>
	
	<div class="row flex-container-row">
		<div class="mdl-textfield floating-label">
   			<input type="text" name="tags" class="mdl-textfield__input" required>
   			<label class="float-palceholder">Tags</label>
   			<label class="mdl-textfield__label"></label>
		</div>	
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_features" class="mdl-textfield__input" required>
   			<label class="float-palceholder">Features</label>
   			<label class="mdl-textfield__label"></label>
		</div>			
	</div>
	
	<div class="row flex-container-row">
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_run_path" class="mdl-textfield__input" required>
   			<label class="float-palceholder">Run path</label>
   			<label class="mdl-textfield__label"></label>
		</div>	
		<div class="mdl-textfield floating-label">
   			<input type="text" name="edt_run_param" class="mdl-textfield__input" value="main()" required>
   			<label class="float-palceholder">Run param</label>
   			<label class="mdl-textfield__label"></label>
		</div>			
	</div>	
	
	<div class="row">
		<button id="btn-add-tc" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">ADD</button>
	</div>
	
</form>


<script type="text/javascript">	
	var view = {};
	view['data'] = ${tcs};
	view['users'] = ${jusers};	
	view['testsets'] = {};
	
	<c:forEach items="${testsets}" var="set">
		view['testsets']['${set.id}'] = '${set.getLocalSet()}';
	</c:forEach>
	
</script>