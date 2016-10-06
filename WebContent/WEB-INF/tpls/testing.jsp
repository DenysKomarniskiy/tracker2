<link rel="stylesheet" type="text/css" href="./js/lib/slickgrid/plugins/slick.headermenu.css" />
<style>
 .slick-header-menu {      
      background: #f0f0f0;
      padding: 2px;
      -moz-box-shadow: 2px 2px 2px silver;
      -webkit-box-shadow: 2px 2px 2px silver;
      min-width: 20px;
      z-index: 14;
    }
    .slick-header-menuitem {
      padding: 2px 4px;
      border: 1px solid transparent;
      border-radius: 3px;
    }
    .slick-header-menuitem:hover {
      border-color: silver;
      background: white;
    }
    .slick-header-menuitem-disabled {
      border-color: transparent !important;
      background: inherit !important;
    }
</style>

<div class="control-panel flex-container-row">

	<div class="testing-selector">
		<form id="login-testing" action="testing" method="post" class="flex-container-row">
			<div class="mdl-select">			
				<select id="testing_id" name="testing_id">
				</select> 		
				<label class="float-palceholder" for="testing_id">testing</label>
			</div>
			<div class="mdl-select">	
				<select id="user_id" name="user_id">
					<option value="all">All</option>
				</select>				
				<label class="float-palceholder" for="user_id">runner</label>
			</div>
			<input type="hidden" name="action" value="get">			 
			<input type="submit" name="submit" value="autorenew" class="material-icons mdl-button mdl-button--icon" style="margin: 14px 0;" title="Refresh list">
		</form>
	</div>	
	
	<div class="actions flex-container-row">
		<div class="button">
		   <button id="get-testplan" class="material-icons mdl-button mdl-button--icon" style="margin: 14px 0;" title="Generate SilkTest testplan">assignment</button>
		</div>
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
		
		<div class="mdl-select">			
			<select id="env_id" name="env_id" required>
				<option value=""></option>				
			</select>			
			<label class="float-palceholder" for="env_id">env</label>
		</div>
			
	</div>
	
	
	
</div>


<div id="main-grid"></div>

<ul id="status-menu" style="display: none; position: absolute">
</ul>	

<script type="text/javascript">
	var view = {};
	view['testings'] = ${jtestings};
	view['users'] = ${jusers};
	view['envs'] = ${jenvs};
	view['data'] = [];
	view['appVer'] = {};	
	view['statuses'] = [
		{id:"E", desc:"No run", val: ""},
		{id:"P", desc:"Passed", val: "P"},
		{id:"F", desc:"Failed", val: "F"},
		{id:"W", desc:"Waiting", val: "W"},
		{id:"C", desc:"Correction", val: "C"},
		{id:"I", desc:"Investigation", val: "I"},
		{id:"N", desc:"N\A", val: "N"},
	];
</script>