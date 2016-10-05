<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tools content flex-container-row">

	<div class="widget send-mail">
		<h3>Send mail to managers</h3>
		<form id="send-mail" action="tools" method="post">
			<input type="hidden" name="action" value ="sendmail"/>
			<div>
				<label>Select testing
					<select name="testing_id">
					    <c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>
					</select>
				</label>
			</div>
			<div>
				<label>Enter version
					<input type="text" name="tqc_version" value="1.0" required/>
				</label>
			</div>
			<div>
				<label>Enter official env's
					<input type="text" name="official_env" value="zw10, pt92, QA34_02" required/>
				</label>
			</div>
			<br>
			</br>
			<input type="submit" name="action" value="Send Mail" />
		</form>
	</div>	
	
	<div class="widget testing-manager">
		<h3>Testing management</h3>
		<div class="row invert-color">
			<h4>New testing</h4>
			<form class="flex-container-row" id="gen-testing" action="controlpanel/generate" method="post">				
				<div class="mdl-textfield floating-label">
		   			<input type="text" name="testing_name" class="mdl-textfield__input" required>
		   			<label class="float-palceholder">Testing name</label>
		   			<label class="mdl-textfield__label" for="sample-expandable"></label>
				</div>						
				<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Generate</button>		
			</form>
		</div>
		
		<div class="row">
			<h4>New custom testing</h4>
			<button id="btn-create-custom-testing" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Create</button>
		</div>
		
		<div class="row invert-color">	
			<h4>Edit testing</h4>
			<form class="flex-container-row" action="controlpanel/delete" method="post" onsubmit="return confirm('Do you really want to delete this testing?');">				
				<div class="mdl-select">			
					<select id="testing_id" name="testing_id" required>
						<option selected></option>
						<c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>				
					</select>			
					<label class="float-palceholder" for="testing_id">Select testing</label>
				</div>		
				<div><button id="btn-edit-testing" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Edit</button></div>
				<div><button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Delete</button></div>		
			</form>			
		</div>
		
		
	</div>
	
	
</div>	

<div id="custom-testing-editor" class="custom-testing-editor hide">
	<div class="flex-container-row">
		<div id="left-grid" style="width: 500px; height: 500px"></div>
		<div class="flex-container-col">
			<div><button id="btn-add-all" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">>></button></div>
			<div><button id="btn-add" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">></button></div>
			<div><button id="btn-remove" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"><</button></div>
			<div><button id="btn-remove-all" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"><<</button></div>
		</div>	
		<div id="right-grid" style="width: 550px; height: 500px"></div>
	</div>
	
	<div class="float-l invert-color">	
		<div class="mdl-textfield mdl-textfield--expandable">
			<label class="mdl-button mdl-button--icon" for="search-cte-tc" title="Search"> 
				<i class="material-icons">search</i>
			</label>
			<div class="mdl-textfield__expandable-holder search-field">
				<input class="mdl-textfield__input" type="text" id="search-cte-tc" required>
				<label class="mdl-textfield__label" for="sample-expandable"></label>
				<i id="reset-search">×</i>
			</div>
		</div>		
	</div>

	
	<div class="flex-container-row invert-color float-r">
		<!-- <div><button id="set-runners" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Set runners</button></div>  -->
		<div>
			<div class="mdl-textfield floating-label">
		   		<input type="text" name="cte-name" id="cte-name" class="mdl-textfield__input" required>
		   		<label class="float-palceholder" >Testing name</label>
		   		<label class="mdl-textfield__label" for="sample-expandable"></label>
			</div>		
		</div>
		<div><button id="cte-save" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Save</button></div>
	</div>
	
		
	<form id="cteusers" class="flex-container-row float-r" style="height: 67px;">
		<c:forEach items="${users}" var="user">						   
	        <div><label><input type="checkbox" value="${user.id}" checked>${user.id}</label></div> 
	    </c:forEach>
	</form>
</div>


<script type="text/javascript">	
	var view = {};
	view['data'] = [];	
	view['users'] = ${jusers};
</script>

