fetch(
	'storage?to=json'
).then(	
	response => response.json()
).then(
	gridInit	
).catch(
	function(error) {
		console.log('Request failed', error);
    }
);

var users = {'0': 'opya', '1': 'rovo', '2': 'deko', '3': 'mmal', '4': 'myut'};

var usersString = Object.keys(users).map(function(k) {return users[k]}).join(",");
var grid;


var testSets = new Map;
loadTestSets.status = 'empty';
function loadTestSets() {

	loadTestSets.status = 'pending';
	fetch(
		'testset'
	).then(
		function(response) {
			return response.json();
	    }
	).then(
		function(data) {			
			data.forEach(function(item){
				testSets.set(item.id, item);
			});		
			loadTestSets.status = 'loaded';
		}	
	).catch(
		function(error) {
			console.log('loadTestSets: Request failed', error);
	    }
	);
}

function renderTestSet(cellNode, row, dataContext, colDef){
	
	if (testSets.size === 0) {
		if (loadTestSets.status === 'empty'){
			loadTestSets();
		} 
		setTimeout(renderTestSet.bind(null, cellNode, row, dataContext, colDef), 110);
	} else {
		cellNode.innerHTML = testSets.get(dataContext.test_set_id).local_set;		
	}
	
}

var columns = [
    {id: "tc_id", 		name: "TC ID", 		field: "tc_id", 		width: 200, sortable: true},    
    {id: "author", 		name: "Author", 	field: "author", 		width: 50, 	options: usersString, editor: Slick.Editors.Select},
    {id: "step_num", 	name: "Step Count", field: "step_num", 		width: 65	},
    {id: "duration", 	name: "Duration", 	field: "duration", 		width: 65, sortable: true, editor: Slick.Editors.Integer},
    {id: "auto_ide", 	name: "Auto Ide", 	field: "auto_ide",		width: 65, selectable: false, sortable: true},
    {id: "test_set_id", name: "Set Name", 	field: "test_set_id", 	width: 150,	 rerenderOnResize: false,   asyncPostRender: renderTestSet, sortable: true},
    {id: "features", 	name: "Features", 	field: "features", 		width: 200, editor: Slick.Editors.LongText},    
];
var options = {
    autoEdit: true,
    editable: true,
    enableAddRow: false,
    enableCellNavigation: true,
    asyncEditorLoading: false,
    enableAsyncPostRender: true,
    asyncPostRenderDelay: 0,
    rowHeight: 25,
    cellFlashingCssClass: 'flash-cell',
    enableColumnReorder: false,
    multiColumnSort: true
};

var myDataView = new Slick.Data.DataView();
myDataView.getItemMetadata = function (index) {
    var item = myDataView.getItem(index);
    return {
        cssClasses: 'status-' + item.tcstatus
    };
};


function gridInit(data) {
	
	data.forEach(function(item, idx){
		item['id'] = idx + 1;
	});
	
	console.dir(data);
	
	myDataView.setItems(data);
	grid = new Slick.Grid("#main-grid", myDataView, columns, options);    
	grid.registerPlugin( new Slick.AutoTooltips({}) );

	grid.onSort.subscribe(function (e, args) {
	    myDataView.sort(function (row1, row2) {
	        for (var i = 0, l = args.sortCols.length; i < l; i++) {
	            var field = args.sortCols[i].sortCol.field;
	            var sign = args.sortCols[i].sortAsc ? 1 : -1;
	            var x = row1[field], y = row2[field];
	            var result = (x < y ? -1 : (x > y ? 1 : 0)) * sign;
	            if (result != 0) {
	                return result;
	            }
	        }
	        return   0;
	    }, true);

	    grid.invalidate();
	    grid.render();
	});

	grid.onClick.subscribe(function (e) {
	    var cell = grid.getCellFromEvent(e);
	    var columns = grid.getColumns();

	    if (columns[cell.cell].id == "tc_id") {
	        grid.flashCell(cell.row, cell.cell, 222);
	        copyToClipboard(data[cell.row].tc_id);
	    }
	    
	});
}





/* helpers */
function copyToClipboard(text) {
    var textArea = document.createElement("textarea");
    textArea.style = {
        position: 'fixed', top: 0,
        left: 0,
        width: '2em',
        height: '2em',
        padding: 0,
        border: 'none',
        outline: 'none',
        boxShadow: 'none',
        background: 'transparent'
    };

    textArea.value = text;
    document.body.appendChild(textArea);
    textArea.select();

    try {
        var successful = document.execCommand('copy');
        successful ? console.log(text, 'copied to clipboard') : console.log('unable to copy');

    } catch (err) {
        console.log('Oops, unable to copy');
    }

    document.body.removeChild(textArea);

}
