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

var users = {'0': 'opya', '1': 'rovo', '2': 'deko', '3': 'mmal', '4': 'myut'};

var usersString = Object.keys(users).map(function(k) {return users[k]}).join(",");
var grid;

function sdFormatter(row, cell, value) {
        switch (value){
            case true   : return '&#10004';
            case false  : return '<a href="#">post</a>';
            case 'wait' : return '<div class="loader"></div>';
            default : return value;
        }
}

var columns = [
    {id: "id", name: "TC ID", field: "id", width: 200, sortable: true},
    {id: "tcauthor", name: "Author", field: "tcauthor", width: 50},
    {id: "tcrunner", name: "Runner", field: "tcrunner", width: 50, options: usersString, editor: Slick.Editors.Select},
    {id: "tcstepcount", name: "Step Count", field: "tcstepcount", width: 55},
    {id: "tcduration", name: "Duration", field: "tcduration", width: 65, sortable: true, editor: Slick.Editors.Integer},
    {id: "tcset", name: "Set Name", field: "tcset", width: 50},
    {id: "tcstatus", name: "Status", field: "tcstatus", width: 55, selectable: false, resizable: false, sortable: true},
    {id: "tcset", name: "Applications", field: "tcset", width: 50},
    {id: "tccomment", name: "Comment", field: "tccomment", width: 100, editor: Slick.Editors.LongText},
    {id: "tcfeature", name: "Features", field: "tcfeature", width: 50},
    {id: "softdev", name: "SoftDev", field: "softdev", width: 60, formatter: sdFormatter, sortable: true},
    {id: "tqcver", name: "TQC ver", field: "tqcver", width: 50},
    {id: "labver", name: "LAB ver", field: "labver", width: 50},
    {id: "genever", name: "GENE ver", field: "genever", width: 50},
];
var options = {
    autoEdit: true,
    editable: true,
    enableAddRow: false,
    enableCellNavigation: true,
    asyncEditorLoading: false,
    rowHeight: 25,
    cellFlashingCssClass: 'flash-cell',
    enableColumnReorder: false,
    multiColumnSort: true
};
var data = [];
var states = {
    "N": "P", "P": "F", "F": "W", "W": "C", "C": "I", "I": "N",
};
//for (var i = 0; i < 100; i++) {
//    data[i] = {};
//    data[i]["id"] = i;
//    data[i]["tcauthor"] = users[Math.ceil(Math.random() * 10)%5];
//    data[i]["tcrunner"] = users[Math.ceil(Math.random() * 10)%5];
//    data[i]["tcduration"] = Math.ceil(Math.random() * 100);
//    data[i]["tcid"] = i + 'IS';
//    data[i]["tcstatus"] = "N";
//    data[i]["softdev"] = !!Math.round(Math.random());
//}

var myDataView = new Slick.Data.DataView();
myDataView.getItemMetadata = function (index) {
    var item = myDataView.getItem(index);
    return {
        cssClasses: 'status-' + item.tcstatus
    };
};

var url = 'http://pc-opya:8080/ServletDBLog4j/test';
var body = '';
fetch(url, {
    method: 'get',
//    headers: {
//        'Content-Type': 'application/soap+xml'
//    },
    //body: body
}).then(function(response) {
    return response.json();
}).then(gridInit).catch(function(error) {
    console.log('Request failed', error);
});


function gridInit(data) {
	
	myDataView.setItems(data);
	grid = new Slick.Grid("#main-grid", myDataView, columns, options);    
	grid.registerPlugin( new Slick.AutoTooltips({}) );

	grid.onContextMenu.subscribe(function (e) {
	    e.preventDefault();
	    var cell = grid.getCellFromEvent(e);
	    var $contextMenu = $("#contextMenu");
	    $contextMenu.find('b').text(data[cell.row].tcid + ' Set status:');
	    $contextMenu.data("row", cell.row).css("top", e.pageY).css("left", e.pageX).show();
	    $("body").one("click", function () {
	        $("#contextMenu").hide();
	    });
	});

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
	    if (columns[cell.cell].id == "tcstatus") {
	        if (!grid.getEditorLock().commitCurrentEdit()) {
	            return;
	        }

	        data[cell.row].tcstatus = states[data[cell.row].tcstatus];

	        grid.invalidateRow(cell.row);
	        grid.render();

	        // grid.updateRow(cell.row);
	        e.stopPropagation();
	    }
	    if (columns[cell.cell].id == "tcid") {
	        grid.flashCell(cell.row, cell.cell, 222);
	        copyToClipboard(data[cell.row].tcid);
	    }
	    if (columns[cell.cell].id == "softdev") {
	        var status = myDataView.getItem(cell.row).tcstatus;
	        if (~'NWCI'.indexOf(status)){
	            alert('Status must be "P" or "F"');
	            return;
	        }

	        if (data[cell.row].softdev === true) {
	            if (confirm('re-post result?')) {
	                post(cell.row);
	            }
	        } else if (
	            (data[cell.row].softdev === false) ||
	            (~data[cell.row].softdev.indexOf('failed'))
	        ) {
	            post(cell.row);
	        }
	    }
	});
}


function post(row) {
    data[row].softdev ='wait';
    grid.invalidateRow(row);
    grid.render();

    setTimeout(function () {
        var res = !!Math.round(Math.random());
        if (res === true) {
            data[row].softdev = true;
        } else {
            data[row].softdev ='failed, wrong test-set';
        }
        grid.invalidateRow(row);
        grid.render();
    }, 3000)
}


$("#contextMenu").click(function (e) {
    if (!$(e.target).is("li")) {
        return;
    }
    if (!grid.getEditorLock().commitCurrentEdit()) {
        return;
    }
    var row = $(this).data("row");

    data[row].tcstatus = $(e.target).attr("data");

    grid.invalidateRow(row);
    grid.render();

    // grid.updateRow(row);
});
