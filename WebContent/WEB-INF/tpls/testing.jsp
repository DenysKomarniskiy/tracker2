
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

<div id="main-grid" style="height: 850px;"></div>

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
</script>



<script src="./js/main.js"></script>
