
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

<div class="search">
	<span>Search TC</span><input id="search-tc" type="text">
</div>

<div class="app-ver">
	<span>TQC Ver:</span><input name="tqcver" id="tqc-ver" type="text">
	<span>LAB Ver:</span><input name="labver" id="lab-ver" type="text">
	<span>GENE Ver:</span><input name="genever" id="gene-ver" type="text">
</div>


<div id="main-grid" style="height: calc(100vh - 80px)"></div>

<script src="./js/lib/jquery/jquery-3.1.0.min.js"></script>
<script src="./js/lib/jquery/jquery-ui.min.js"></script>
<script src="./js/lib/jquery/jquery.event.drop-2.2.js"></script>
<script src="./js/lib/jquery/jquery.event.drag-2.2.js"></script>

<script src="./js/lib/slickgrid/slick.core.js"></script>
<script src="./js/lib/slickgrid/slick.editors.js"></script>
<script src="./js/lib/slickgrid/slick.grid.js"></script>
<script src="./js/lib/slickgrid/slick.dataview.js"></script>
<script src="./js/lib/slickgrid/plugins/slick.autotooltips.js"></script>

<script type="text/javascript">
	var view = {};
	view['testings'] = ${jtestings};
	view['users'] = ${jusers};
	view['data'] = [];
	view['appVer'] = {};	
</script>



<script src="./js/main.js"></script>
