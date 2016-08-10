<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	var dataView = new Slick.Data.DataView();
	var testsets = ${testsets};
	var gridOptions = {
	    autoEdit: true,
	    editable: true,		    
	    enableCellNavigation: true,
	    rowHeight: 25,		    
	    enableColumnReorder: false,		
		enableCellNavigation: true,
		editCommandHandler: queueAndExecuteCommand
	}
	var gridColumns = [             
    	{id: "id", 		name: "Testset ID", 	field: "id", 		width: 200},    
    	{id: "sd_set", 	name: "SD Set name", 	field: "sd_set", 	width: 400, editor: Slick.Editors.Text},
    	{id: "local_set", 	name: "Local Set name", field: "local_set", width: 400, editor: Slick.Editors.Text},
    ];
	
	function queueAndExecuteCommand(item, column, editCommand) {
		console.log(item, column, editCommand)
		var id = item.id;
		var field = column.field;
		var data = editCommand.serializedValue;
		var formData = new FormData();
		formData.append("action", "edit");
		formData.append("id", id);
		formData.append(field, data);
		var opts = {method: 'post', body: formData};		
		fetch('testset', opts)
		.then(editCommand.execute)
		.catch(function(err){
			console.log(err);
		});
		
		//();
	}

	dataView.setItems(testsets);	
	var grid = new Slick.Grid("#main-grid", dataView, gridColumns, gridOptions);	
	

</script>

