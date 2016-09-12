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
	
	<div class="stats flex-container-row" style="width: 200px;">
		<div>total: <span id="stats-total-pcs">0</span> pcs. <span id="stats-total-min">0</span> min.</div>
		<div>processed: <span id="stats-processed-pcs">0</span> pcs.:<span id="stats-processed-proc-pcs">0</span>% <span id="stats-processed-min">0</span> min.:<span id="stats-processed-proc-min">0</span>%</div>
		<div>passed: <span id="stats-passed-pcs"></span> pcs.</div>
		<div>failed: <span id="stats-failed-pcs"></span> pcs.</div>
		<div>wait: <span id="stats-wait-pcs"></span> pcs.</div>
		<div>empty: <span id="stats-empty-pcs"></span> pcs.</div>
		<div>correction: <span id="stats-correction-pcs"></span> pcs.</div>
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

<ul id="status-menu" style="display: none; position: absolute">
	<li class="n" data-status="">No run</li>
	<li class="p" data-status="P">Passed</li>
	<li class="f" data-status="F">Failed</li>
	<li class="w" data-status="W">Waiting</li>
	<li class="c" data-status="C">Correction</li>
	<li class="i" data-status="I">Investigate</li>
</ul>	

<script type="text/javascript">
	var view = {};
	view['testings'] = ${jtestings};
	view['users'] = ${jusers};
	view['envs'] = ${jenvs};
	view['data'] = [];
	view['appVer'] = {};	
</script>