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
		<div class="row">
			<h4>New testing</h4>
			<form id="gen-testing" action="controlpanel" method="post">
				<label>Testing name
					<input type="text" name="testing_name" value="" required/>
				</label>
				<input type="hidden" name="action" value ="generate"/>
				<input type="submit" name="button" value="Generate" />
			</form>
		</div>
		
		<div class="row">
			<h4>New custom testing</h4>
			<button id="btn-create-custom-testing" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Create</button>
		</div>
		
		<div class="row">	
			<h4>Delete testing</h4>
			<form action="controlpanel" method="post" onsubmit="return confirm('Do you really want to delete this testing?');">
				<label>select testing
					<select name="testing_id">
					    <c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>
					</select>
				</label>
				<input type="hidden" name="action" value ="delete"/>
				<input type="submit" name="button" value="delete" />
			</form>			
		</div>
		
		<div class="row">
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
	<div class="flex-container-row">
		<button id="set-runners" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Set runners</button>
		<div class="mdl-textfield floating-label">
		   <input type="text" name="login" id="login" class="mdl-textfield__input" required>
		   <label class="float-palceholder" for="login">Testing name</label>
		   <label class="mdl-textfield__label" for="sample-expandable"></label>
		</div>		
		<button id="save" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Save</button>
	</div>
</div>


<script type="text/javascript">	
	var view = {};
	view['data'] = [];	
	view['users'] = ${jusers};
</script>

