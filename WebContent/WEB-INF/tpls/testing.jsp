<div class="control-panel">

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
	
	

		<div class="mdl-textfield floating-label">
		   <label class="float-palceholder" for="tqc-ver">tqc ver</label>
		   <input type="text" name="tqcver" id="tqc-ver" class="mdl-textfield__input">
		   <label class="mdl-textfield__label" for="sample-expandable"></label>
		</div>		
						
		
		<span>LAB Ver:</span><input name="labver" id="lab-ver" type="text">
		<span>GENE Ver:</span><input name="genever" id="gene-ver" type="text">
	
</div>



<div id="main-grid"></div>

<script type="text/javascript">
	var view = {};
	view['testings'] = ${jtestings};
	view['users'] = ${jusers};
	view['data'] = [];
	view['appVer'] = {};	
</script>