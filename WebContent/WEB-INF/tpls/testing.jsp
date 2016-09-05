<div class="control-panel flex-container-row">

	<div class="testing-selector">
		<form id="login-testing" action="testing" method="post">
			<select name="user_id">
				<option value="all">All</option>
			</select> 
			<select name="testing_id">
			</select> 
			<input type="submit" name="submit" value="Go" />
		</form>
	</div>	

	<div class="app-ver flex-container-row">
		<div class="mdl-textfield floating-label">
		   <input type="text" name="tqcver" id="tqc-ver" class="mdl-textfield__input" required>
		   <label class="float-palceholder" for="tqc-ver">tqc ver</label>
		   <label class="mdl-textfield__label" for="sample-expandable"></label>
		</div>	
		<div class="mdl-textfield floating-label app-ver">
		   <input type="text" name="labver" id="lab-ver" class="mdl-textfield__input" required>
		   <label class="float-palceholder" for="tqc-ver">lab ver</label>
		   <label class="mdl-textfield__label" for="sample-expandable"></label>
		</div>	
		<div class="mdl-textfield floating-label app-ver">
		   <input type="text" name="genever" id="gene-ver" class="mdl-textfield__input" required>
		   <label class="float-palceholder" for="tqc-ver">gene ver</label>
		   <label class="mdl-textfield__label" for="sample-expandable"></label>
		</div>
		
		<select name="env_id">				
		</select> 	
	</div>
	
</div>



<div id="main-grid"></div>

<script type="text/javascript">
	var view = {};
	view['testings'] = ${jtestings};
	view['users'] = ${jusers};
	view['envs'] = ${jenvs};
	view['data'] = [];
	view['appVer'] = {};	
</script>