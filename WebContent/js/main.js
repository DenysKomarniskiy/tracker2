if (view.users) {
	view.usersString = view.users.reduce((prev, curr) => prev+curr.id+',', '').slice(0, -1);
}

var APP = {
	
	gridClickHandlers: [],
		
	init: function(){
		
		this.resolveRoute();
		
		if (typeof this.initFace[this.faceName] === 'function') {
			this.initFace[this.faceName].apply(this);
		}	
		
		$("#search-tc").keyup(this.search.bind(this));		
		
		this.dataView = new Slick.Data.DataView();		
		this.dataView.setItems(view.data);
		this.dataView.getItemMetadata = this.metaDataFormatter.bind(this);
		this.dataView.setFilter(this.dataViewFilter);		
		
		this.SETTINGS[this.faceName].options.editCommandHandler = this.editCommandHandler.bind(this);
		
		this.grid = new Slick.Grid("#main-grid", this.dataView, this.SETTINGS[this.faceName].columns, this.SETTINGS[this.faceName].options);    
		this.grid.registerPlugin(new Slick.AutoTooltips({}));
		this.grid.onClick.subscribe(this.gridClickHandler.bind(this));	
		this.grid.onSort.subscribe(this.gridSortHandler.bind(this));		
		
	},
	
	validateData: function(data){		
		data.forEach((item) => {
			if (item.storageTC){
				item.storageTC.apps 	= item.storageTC.apps || ""; 
				item.storageTC.features = item.storageTC.features || ""; 
			}
			item.tcStatus = item.tcStatus || "";
		});
		
		return data;
	},
	
	initFace: {
		"testing": function() {				
			//init vars
			this.dataUrl = 'testing';
			this.searchPath = ['storageTC','tc_id'];

			//load ver. stored values
			var lastSelectedUser = localStorage.getItem('lastSelectedUser');
			var lastSelectedTesting = localStorage.getItem('lastSelectedTesting');
			
			$.each($('div.app-ver input'), (i, item) => {		
				view.appVer[item.name] = item.value = localStorage.getItem(item.name);
				item.onblur = (e) => { 
					localStorage.setItem(e.target.name, e.target.value);
					view.appVer[e.target.name] = e.target.value;
				};
			});
			
			//init grid click handlers
			this.gridClickHandlers.push(this.idRowClickHandler);
			this.gridClickHandlers.push(this.statusRowClickHandler);
			this.gridClickHandlers.push(this.softdevRowClickHandler);
						
			//build interface			
			var $testingSelect = $('select[name="testing_id"]');					
			$.each(view['testings'], (i, item) => {	
				var $opt = $('<option></option>').val(item[0]).html(item[1]);
				if (item[0] == lastSelectedTesting) {
					$opt.attr('selected', true);
				} 
				$testingSelect.append($opt);	
			});
			
			var $userSelect = $('select[name="user_id"]');					
			$.each(view['users'], (i, item) => { 
				var $opt = $('<option></option>').val(item.id).html(item.id);
				if (item.id == lastSelectedUser) {
					$opt.attr('selected', true);
				} 
				$userSelect.append($opt); 
			});
			
			$('form#login-testing')
			.on('submit', this.loadData.bind(this))
			.trigger('submit');
			
            this.$statusMenu = $("#status-menu");
            this.$statusMenu.on('click', this.setTcStatus.bind(this));
		},
		
		"storage": function() {
			//init vars
			this.dataUrl = 'storage';
			this.searchPath = ['tc_id'];		
			
			//build interface
			$('button#b-add').click(() => {
				var textnode = document.createTextNode("Testcase"); 
				var form = document.getElementById("add-testcase-form").cloneNode(true);
				form.style.display = "flex";				
				
				var submit = document.createElement('button');
				submit.innerText="ADD";
				submit.onclick = () => {
					
					if (!form.checkValidity()){
						form[0].click();
						return;
					}
					
					var opts = {
					    method: 'post',
					    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					    body: $(form).serialize() + '&action=add'					    
					};
					
					console.log('request ->', opts);
					
					fetch(APP.dataUrl, opts)
					.then(resp => resp.json())
					.then(resp => {
						console.log('response <-', resp);
						this.dataView.insertItem(0, resp);
						this.grid.invalidate();
						this.grid.render();						
						Modal.close();
						this.grid.scrollRowToTop(0);
					})
					.catch(Modal.printMsg.bind(Modal));					
				};
				
				Modal
				.setHeader('Add Testcase')
				.setContent(form)
				.setFooter(submit)
				.show();
			});
		}
	
	},
	
	search: function(e) {
		Slick.GlobalEditorLock.cancelCurrentEdit();
	    
		// clear on Esc
	    if (e.which == 27) {
	    	e.target.value = "";
	    }
	    
	    this.dataView.setFilterArgs({q: e.target.value, path: this.searchPath});
	    this.dataView.refresh();
	    this.grid.invalidate();
	    this.grid.render();
	},
	
	loadData: function(e) {
		
		e.preventDefault();
		
		var $form = $(e.target);
		
		localStorage.setItem('lastSelectedUser', $form.find('select[name="user_id"]').val());
		localStorage.setItem('lastSelectedTesting', $form.find('select[name="testing_id"]').val());
		
		var opts = {
		    method: 'post',
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    body: $form.serialize() + '&action=get'
		    
		};
		
		console.log('request ->', opts);
		
		fetch(APP.dataUrl, opts)
		.then(function(response) {
		    return response.json();
		})
		.then(this.updateGrid.bind(this))
		.catch(function(error) {
		    console.log('Request failed', error);
		});
	},	
	
	updateGrid: function(data) {
		
		console.log('response <-');
		console.dir(data);
	    
		this.dataView.setItems(this.validateData(data));
		this.grid.invalidate();
		this.grid.render();		
	},
	
	metaDataFormatter: function(index)	{
		var item = this.dataView.getItem(index);
	    return {
	        cssClasses: 'status-' + item.tcStatus
	    };		
	},
	
	dataViewFilter: function (item, args) {							
		if (!args || args.q == "")
			return true;		
		
		var data = args.path.reduce((prev, curr) => prev[curr], item);		 
			 
		return data.includes(args.q);
	},
	
	resolveRoute: function() {
		
		var path = window.location.pathname;
		
		switch (true) {
			case !!~path.indexOf('testing'):				
				this.faceName = 'testing';
				break;
				
			case !!~path.indexOf('storage'):
				this.faceName = 'storage';
				break;
				
			case !!~path.indexOf('testset'):
				this.dataUrl = 'testset';
				this.faceName = 'testset';
				break;				
			
			default:
				this.faceName = 'default';
		
		}
	},
	
	editCommandHandler: function(item, column, editCommand) {
		
		var id = item.id;
		var field = column.id;
		var data = editCommand.serializedValue;
		var opts = {
			method: 'post',  
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},       
    		body: "action=edit&id="+id+"&"+field+"="+data
   		};				

		console.log('request ->', opts);
		
		fetch(
			this.dataUrl, opts
		).then(
			resp => resp.text()			
		).then(
			respText => {
				console.log('response <-', respText);
				editCommand.execute();
				this.grid.invalidateRow(editCommand.row);
				this.grid.render();
			}
		).catch(
			function(err){
				console.log(err);
			}
		);
	},
	
	gridClickHandler: function(gridEvent) {		
		var cell = this.grid.getCellFromEvent(gridEvent);
		var columns = this.grid.getColumns();
		
		this.gridClickHandlers.forEach((handler) => {
			if (typeof handler === 'function') {
				handler.call(this, gridEvent, cell, columns);
			}	
		});
	},
	
	idRowClickHandler: function(e, cell, columns) {

	    if (columns[cell.cell].id === "tc_id") {
	    	this.grid.flashCell(cell.row, cell.cell, 200);
		    copyToClipboard(this.grid.getCellNode(cell.row, cell.cell).innerText);
		}
	    
	},
	
	statusRowClickHandler: function(e, cell, columns) {
		
		if (columns[cell.cell].id === "edt_status") {
			e.stopPropagation();

			this.$statusMenu.css({"top": e.pageY - 10,"left": e.pageX}).show();
			this.$statusMenu.data(cell)
		
		    $("body").one("click", () => {this.$statusMenu.hide()});
		}
	    
	},
	
	softdevRowClickHandler: function(e, cell, columns) {		
		
		if (columns[cell.cell].id !== "softdev")
			return;
		
		e.stopPropagation();
		var rowData = this.grid.getDataItem(cell.row);
		
		if (!(rowData.tcStatus == 'F' || rowData.tcStatus == 'P')) {
			alert('You have to pass or fail TC before post to SoftDev');
			return;
		}
		
		var opts = {
			method: 'post',  
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},       
    		body: "action=sdpost&id="+rowData.id
   		};		
		
		console.log('request:', opts);
		
		fetch(
			this.dataUrl, opts
		).then(
			resp => resp.json()			
		).then(
			resp => {
				console.log('response <-', resp);				
			}
		).catch(
			function(err){
				console.log(err);
			}
		);
		
	    
	},	
	
	setTcStatus: function(e) {
		var data = this.$statusMenu.data();
		var rowData = this.grid.getDataItem(data.row);
		var newStatus = e.target.dataset.status;
				
		if (rowData.tcStatus == newStatus)
			return;
		
		if ((newStatus=='P' || newStatus=='F') && !view.appVer.tqcver)
			if (!confirm("Want to set status without TQC version?"))
				return;

		var opts = {
			method: 'post',  
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},       
    		body: "action=edit&id="+rowData.id
    				+ "&edt_status=" + newStatus
    				+ ((newStatus=='P' || newStatus=='F') ? "&edt_tqc_ver=" + view.appVer.tqcver : '')
    				+ ((newStatus=='P' || newStatus=='F') ? "&edt_lab_ver=" + view.appVer.labver : '')
    				+ ((newStatus=='P' || newStatus=='F') ? "&edt_gene_ver=" + view.appVer.genever : '')
   		};				

		console.log('request ->', opts);	
		
		fetch(
			this.dataUrl, opts
		).then(
			resp => resp.json()			
		).then(
			resp => {
				console.log('response <-', resp);				
				this.dataView.updateItem(rowData.id, resp);
				this.grid.invalidate();
				this.grid.render();
			}
		).catch(
			function(err){
				console.log(err);
			}
		);
	},
	
	gridSortHandler: function (e, args) {
		this.dataView.sort(function (row1, row2) {
	        for (var i = 0, l = args.sortCols.length; i < l; i++) {
	            var field = args.sortCols[i].sortCol.field;	        	
	            var sign = args.sortCols[i].sortAsc ? 1 : -1;
	            var field_id = args.sortCols[i].sortCol.id;
	            
	            var x = row1[field], y = row2[field];	
	            
	            if (field === 'testSet' && field_id === 'local_set') {
	            	x = row1[field][field_id];
	            	y = row2[field][field_id];
	            }
	            
	            if (field === 'storageTC' && field_id === 'local_set') {
	            	x = row1[field].testSet[field_id];
	            	y = row2[field].testSet[field_id];
	            }
	            
	            if (field === 'storageTC' && field_id === 'tc_id') {
	            	x = row1[field][field_id];
	            	y = row2[field][field_id];
	            }
	            
	            var result = (x < y ? -1 : (x > y ? 1 : 0)) * sign;
	            if (result != 0) {
	                return result;
	            }
	        }
	        return 0;
	    }, true);

	    this.grid.invalidate();
	    this.grid.render();
	},
	

	SETTINGS: {
		"storage": {
			columns: [
               {id: "tc_id", 		name: "TC ID", 		field: "tc_id", 		width: 200, 								sortable: true									},    
               {id: "edt_author", 	name: "Author", 	field: "author", 		width: 50, 	editor: Slick.Editors.Select, 						options: view.usersString, 	},
               {id: "edt_step_num", name: "Step Count", field: "step_num", 		width: 65,	editor: Slick.Editors.Integer													},
               {id: "edt_duration", name: "Duration", 	field: "duration", 		width: 65,  editor: Slick.Editors.Integer,	sortable: true, 								},
               {id: "auto_ide", 	name: "Auto Ide", 	field: "auto_ide",		width: 65,  								sortable: true									},
               {id: "local_set", 	name: "Set Name", 	field: "testSet", 		width: 150,									sortable: true, 	formatter: (a, b, c) => c.local_set,},
               {id: "edt_features", name: "Features", 	field: "features", 		width: 200, editor: Slick.Editors.LongText													},    
               {id: "edt_run_path", name: "Run path", 	field: "run_path", 		width: 200, editor: Slick.Editors.Text														},
               {id: "edt_run_param",name: "Run param", 	field: "run_param", 	width: 100, editor: Slick.Editors.Text														},
           ],
           	options: {
    		    autoEdit: true,
    		    editable: true,
    		    enableAddRow: false,
    		    enableCellNavigation: true,
    		    asyncEditorLoading: false,
    		    rowHeight: 25,
    		    cellFlashingCssClass: 'flash-cell',
    		    enableColumnReorder: false,
    		    multiColumnSort: true
       		}
		},
		"testing": {
			columns: [
          	    {id: "tc_id", 			name: "TC ID", 			field: "storageTC", 	width: 140, sortable: true, formatter: (a, b, c) => c.tc_id },    
          	    {id: "author", 			name: "Author", 		field: "storageTC", 	width: 50, 	formatter: (a, b, c) => c.author},
          	 	{id: "edt_runner", 		name: "Runner", 		field: "runner", 		width: 50, editor: Slick.Editors.Select, options: view.usersString},
          	    {id: "step_num", 		name: "Step Count", 	field: "storageTC", 	width: 65, formatter: (a, b, c) => c.step_num	},
          	    {id: "edt_tduration", 	name: "Duration", 		field: "tduration", 	width: 65, sortable: true, editor: Slick.Editors.Integer},
          	 	{id: "local_set", 		name: "Set Name", 		field: "storageTC", 	width: 150,	formatter: true, formatter: (a, b, c) => c.testSet.local_set, sortable: true},
          	 	{id: "edt_status", 		name: "Status TC", 		field: "tcStatus", 		width: 50,  },
          	 	{id: "apps", 			name: "Application", 	field: "storageTC", 	width: 80, 	formatter: (a, b, c) => c.apps},   	    
          	 	{id: "edt_comment", 	name: "Comment", 		field: "comment", 		width: 200, editor: Slick.Editors.LongText},
          	    {id: "features", 		name: "Features", 		field: "storageTC", 	width: 200, formatter: (a, b, c) => c.features},
          	    {id: "softdev", 		name: "SoftDev", 		field: "softdev", 		width: 50},
          	 	{id: "edt_tqc_ver", 	name: "TQC ver", 		field: "tqcVer", 		width: 60, editor: Slick.Editors.Text}, 
          		{id: "edt_lab_ver", 	name: "LAB ver", 		field: "labVer", 		width: 60, editor: Slick.Editors.Text}, 
          		{id: "edt_gene_ver", 	name: "GENE ver", 		field: "geneVer", 		width: 60, editor: Slick.Editors.Text}, 
          	],
          	options: {
      	   	    autoEdit: true,
      	   	    editable: true,
      	   	    enableAddRow: false,
      	   	    enableCellNavigation: true,
      	   	    asyncEditorLoading: false,
      	   	    rowHeight: 25,
      	   	    cellFlashingCssClass: 'flash-cell',
      	   	    enableColumnReorder: false,
      	   	    multiColumnSort: true
      	   	}
		},
		"testset": {
			columns: [
	          	{id: "id", 				name: "Testset ID", 	field: "id", 		width: 200},    
				{id: "edt_local_set", 	name: "Local Set name", field: "local_set", width: 300, editor: Slick.Editors.Text},
				{id: "edt_sd_set", 		name: "SD Set name", 	field: "sd_set", 	width: 500, editor: Slick.Editors.Text},
          	],
          	options: {
          		autoEdit: true,
        	    editable: true,		    
        	    enableCellNavigation: true,
        	    rowHeight: 25,		    
        	    enableColumnReorder: false,		
        		enableCellNavigation: true,
        		editCommandHandler: this.editCommandHandler
      	   	}
		}
	},
	
}
		
APP.init();



/* main interface*/

var init = function() {

    var mdlDrawer = document.getElementById('mdl-drawer');
    var mdlObfuscator = document.getElementById('mdl-obfuscator');
    var menuBtn = document.getElementById('mdl-menu-btn');

    menuBtn.onclick = function () {
        mdlDrawer.classList.add('is-visible');
        mdlObfuscator.classList.add('is-visible');
    };

    mdlObfuscator.onclick = function () {
        mdlDrawer.classList.remove('is-visible');
        mdlObfuscator.classList.remove('is-visible');
    }

};

init();


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