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

function testSetFormat(row, cell, value, columnDef, dataContext) {    
    return value.local_set;
}



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
