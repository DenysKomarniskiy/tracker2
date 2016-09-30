
var APP = {
	
	gridClickHandlers: [],
	gridPlugins: [],
		
	init: function(){
		
		this.resolveRoute();
		
		if (typeof this.initFace[this.faceName] === 'function') {
			this.initFace[this.faceName].apply(this);
		}	
		
		$("#search-tc").keyup(this.search.bind(this));	
		$('#reset-search').on('click', () => {$("#search-tc").val('').trigger('keyup')});
		
		this.gridPlugins.push(new Slick.AutoTooltips({}));
		
		if (view.data) {
			this.dataView = new Slick.Data.DataView();		
			this.dataView.setItems(view.data);
			this.dataView.getItemMetadata = this.metaDataFormatter.bind(this);
			this.dataView.setFilter(this.dataViewFilter);		
		}
				
		if (this.SETTINGS[this.faceName]){
			this.SETTINGS[this.faceName].options.editCommandHandler = this.editCommandHandler.bind(this);
			
			this.grid = new Slick.Grid("#main-grid", this.dataView, this.SETTINGS[this.faceName].columns, this.SETTINGS[this.faceName].options);    
			this.gridPlugins.forEach(plugin => this.grid.registerPlugin(plugin));
			this.grid.onClick.subscribe(this.gridClickHandler.bind(this));	
			this.grid.onSort.subscribe(this.gridSortHandler.bind(this));
		}		
		
	},
	
	validateData: function(data){
		data.forEach((item) => {
			if (item.storageTC){
				item.storageTC.apps 	= item.storageTC.apps || ""; 
				item.storageTC.features = item.storageTC.features || ""; 
			}
		});
		
		return data;
	},
	
	updateStats: function(){
		
		var data = this.dataView.getItems();
		
		var stats = {
				"stats-total-pcs": data.length, 
				"stats-total-min": 0,
				"stats-processed-pcs": 0,
				"stats-processed-min": 0,
				"stats-processed-proc": 0,
				"stats-passed-pcs": 0,
				"stats-failed-pcs": 0,
				"stats-wait-pcs": 0,
				"stats-empty-pcs": 0,
				"stats-correction-pcs": 0,
		}
		
		data.forEach((tc) => {
			stats["stats-total-min"] += tc.tduration;
			
			if (tc.tcStatus.includes("P")) {
				stats["stats-processed-pcs"] += 1;
				stats["stats-processed-min"] += tc.tduration;
				stats["stats-passed-pcs"] += 1;
			} else if (tc.tcStatus.includes("F")) {
				stats["stats-processed-pcs"] += 1;
				stats["stats-processed-min"] += tc.tduration;
				stats["stats-failed-pcs"] += 1;
			} else if (tc.tcStatus.includes("W")) {
				stats["stats-wait-pcs"] += 1;
			} else if (tc.tcStatus.includes("C")) {
				stats["stats-correction-pcs"] += 1;
			}			
		});
		stats["stats-processed-proc-pcs"] = Math.ceil((stats["stats-processed-pcs"] / stats["stats-total-pcs"]) * 100);
		stats["stats-processed-proc-min"] = Math.ceil((stats["stats-processed-min"] / stats["stats-total-min"]) * 100);
		stats["stats-empty-pcs"] = stats["stats-total-pcs"] - stats["stats-processed-pcs"] - stats["stats-wait-pcs"] - stats["stats-correction-pcs"];
		
		$.each(this.$statsHeaderSpans, (i, spanEl) => {			
			spanEl.innerText = stats[spanEl.id];
		})
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
				
			case !!~path.indexOf('tools'):
				this.dataUrl = 'tools';
				this.faceName = 'tools';
				break;	
				
			case !!~path.indexOf('settings'):
				this.dataUrl = 'settings';
				this.faceName = 'settings';
				break;	
			
			default:
				this.faceName = 'default';
		
		}
	},
	
	initFace: {
		
		"settings": function() {
			console.log('init settings..');
			var excludedFields = ['id', 'active', 'rights'];
			
			var data = [], id = 0;
			$.each(view.userSettings, (key, val) => {
				if (!excludedFields.includes(key))
					data.push({	id: key, val: val});
			});
			
			console.log(data);
			
			$('#user-settings-grid').css({				
				width: 301,
				height: data.length*25 + 30
			});
			
			this.dataView = new Slick.Data.DataView();		
			this.dataView.setItems(data);
			
			var cols = [
	            {id: "id",  name: "Setting", field: "id",  width: 100,},
                {id: "val", name: "Value", 	 field: "val", width: 200, editor: Slick.Editors.Text}
           	];
			
			var opts = {
				autoEdit: true,
    		    editable: true,
				editCommandHandler: this.editCommandHandler.bind(this)
			}
			
			this.grid = new Slick.Grid("#user-settings-grid", this.dataView, cols, opts); 
		},
		
		"tools": function() {
			
			console.log('init tools..');
			
			$('input[name="tqc_version"]').val(localStorage.getItem('tqcver'))
			$sendMailForm = $('#send-mail');
			$sendButton = $sendMailForm.find('input[type="submit"]');
			$sendButton.on('click', (e) => {				
				e.preventDefault();
				
				$sendButton[0].disabled = true;
				
				this.SETTINGS.fetchOpts.body = $sendMailForm.serialize();
				
				fetch(
					$sendMailForm.attr("action"), this.SETTINGS.fetchOpts
				).then(
					resp => resp.text()	
				).then(
					respText => {	
						console.log(respText)
						$sendButton[0].disabled = false;
					}
				).catch(
					err => {
						Modal.alert(err);
					}	
				);			
				
			});
			
			$genTestingForm = $('#gen-testing');
			$genTestingForm.on('submit', (e) => {
				e.preventDefault();
				
				this.SETTINGS.fetchOpts.body = $genTestingForm.serialize();				

				console.log('request ->',  this.SETTINGS.fetchOpts);
				
				$genTestingForm.find('input[type="submit"]')[0].disabled = true;
				
				
				fetch(
					$genTestingForm.attr("action"), this.SETTINGS.fetchOpts
				).then(
					resp => resp.text()			
				).then(
					respText => {						
						console.log(respText);					
						
						$table = $('<table />');
						$table.append('<tr><th>User</th><th>TC count</th><th>Total duration</th></tr>')
						$.each(JSON.parse(respText), (k, v) => {
							$table.append('<tr><td>' + k + '</td><td>' + v[0] + '</td><td>' + v[1] + '</td></tr>');
						});
						
						$genTestingForm.find('input[type="submit"]')[0].disabled = false;
						
						Modal
						.setHeader('New Testing was generated')
						.setContent($table[0])
						.show();						
					}
				).catch(
					function(err){
						Modal.alert(err);
					}
				);				
			});
			
			
			//init custom testing creation grids
			this.SETTINGS.fetchOpts.body = "action=get";
			var leftGridDataView = new Slick.Data.DataView();
			var rightGridDataView = new Slick.Data.DataView();
			
			var leftCols = [
	        	{id: "tc_id", 		 name: "TC ID", 	 field: "tc_id", 		width: 150, 	sortable: true	},    
	        	{id: "edt_testSetId",name: "Set Name", 	 field: "testSet", 		width: 150,	sortable: true, 	formatter: (a, b, c) => c.local_set, },
                {id: "edt_author", 	 name: "Author", 	 field: "author", 		width: 50, 		},
			    {id: "edt_step_num", name: "Step Count", field: "step_num", 	width: 65,		},
			    {id: "edt_duration", name: "Duration", 	 field: "duration", 	width: 65,  sortable: true, 	},
           	];
			
			var rightCols = leftCols.slice();
			rightCols.push({id: "edt_runner", name: "Runner", field: "runner", width: 50, editor: Slick.Editors.Select, options: view.users ? view.users.reduce((prev, curr) => {prev.push(curr.id); return prev;}, []) : []});
								
			var leftGrid = new Slick.Grid("#left-grid", leftGridDataView, leftCols, {explicitInitialization: true});
			var rightGrid = new Slick.Grid("#right-grid", rightGridDataView, rightCols,  {explicitInitialization: true});
			
			leftGrid.setSelectionModel(new Slick.RowSelectionModel());
			rightGrid.setSelectionModel(new Slick.RowSelectionModel());
			
			function rerenderGrids(){
				leftGrid.invalidate();
				leftGrid.render();
				rightGrid.invalidate();
				rightGrid.render();
			}
			
			var $customTestingEditor = $('#custom-testing-editor');
			$customTestingEditor.find('#btn-add').click((e) => {
				leftGrid.getSelectedRows().forEach(idx => {
					var item = leftGridDataView.getItemByIdx(idx);
					rightGridDataView.addItem(item);
					leftGridDataView.deleteItem(item.id);
				});
				
				rerenderGrids();
				
			});
			
			$customTestingEditor.find('#btn-remove').click((e) => {
				rightGrid.getSelectedRows().forEach(idx => {
					var item = rightGridDataView.getItemByIdx(idx);
					leftGridDataView.addItem(item);
					rightGridDataView.deleteItem(item.id);
				});
				
				rerenderGrids();
				
			});						
			
			$('#btn-create-custom-testing').click((e) => {
				$customTestingEditor.removeClass('hide');
				leftGrid.init();
				rightGrid.init();
				
				leftGridDataView.setItems([]);
				rightGridDataView.setItems([]);						
				
				fetch('storage', this.SETTINGS.fetchOpts)
				.then(resp => resp.json())
				.then(respJson => {
					leftGridDataView.setItems(respJson);
					rerenderGrids();
				})
				.catch(Modal.alert.bind(Modal));	
				
				Modal
				.setHeader('New Custom Testing')
				.setContent($customTestingEditor[0])
				.show();	
			});

			
		},
		
		"testing": function() {
			//init vars
			this.dataUrl = 'testing';
			this.searchPath = ['storageTC','tc_id'];
			
			//load ver. stored values
			var lastSelectedUser = localStorage.getItem('lastSelectedUser');
			var lastSelectedTesting = localStorage.getItem('lastSelectedTesting');
			var lastSelectedEnv = localStorage.getItem('lastSelectedEnv');
			
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
			
			//init status-tc menu
            this.$statusMenu = $("#status-menu");
            this.$statusMenu.on('click', this.setTcStatus.bind(this));
            
            //init stats header
            this.$statsHeaderSpans = $(".stats span[id^='stats']");
						
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
			
			var $envSelect = $('select[name="env_id"]');					
			$.each(view['envs'], (i, item) => { 
				var $opt = $('<option></option>').val(item.id).html(item.name);
				if (item.id == lastSelectedEnv) {
					$opt.attr('selected', true);
				} 
				$envSelect.append($opt); 
			});
			$envSelect.on('blur', function() {localStorage.setItem('lastSelectedEnv', this.value);})
			
			$('form#login-testing')
			.on('submit', this.loadData.bind(this))
			.trigger('submit');
			
			$userSelect.on('change', this.loadData.bind(this));
			
			$('#get-testplan').on('click', this.getSTTestPlan.bind(this));	
			
			
			//add grid plugin
		    var headerMenuPlugin = new Slick.Plugins.HeaderMenu({});		   
		    headerMenuPlugin.onCommand.subscribe((e, args) => {
		    	Slick.GlobalEditorLock.cancelCurrentEdit();
			    
		    	var searchPath = ["tcStatus"];
		    	var filterValue = args.command === "A" ? "" : args.command;		    	
			    
			    this.dataView.setFilterArgs({q: filterValue, path: searchPath});
			    this.dataView.refresh();
			    this.grid.invalidate();
			    this.grid.render();
		    });
		    this.gridPlugins.push(headerMenuPlugin);

		},
		
		"storage": function() {
			//init vars
			this.dataUrl = 'storage';
			this.searchPath = ['tc_id'];		
			
			//build interface	
			$('button#b-add').click(() => {
				var form = document.getElementById("add-testcase-form").cloneNode(true);
				
				$(form).find('button#btn-add-tc').click( (e) => {		
					
					if (!form.checkValidity()){					
						return;
					}
					
					e.preventDefault();
					
					this.SETTINGS.fetchOpts.body = $(form).serialize();
					
					console.log('request ->', this.SETTINGS.fetchOpts);
					
					fetch(APP.dataUrl, this.SETTINGS.fetchOpts)
					.then(resp => resp.text())
					.then(respText => {						
						try {
							var jresp = JSON.parse(respText);
							console.log('response <-', jresp);	
							this.dataView.insertItem(0, jresp);
							this.grid.invalidate();
							this.grid.render();						
							this.grid.scrollRowToTop(0);
							Modal.close();							
						} catch (e){
							Modal.alert(respText);		
						}						
					})
					.catch(Modal.alert.bind(Modal));					
				});				
				
				form.classList.remove('hide');					
				
				Modal
				.setHeader('Add Testcase')
				.setContent(form)
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
		
		var $form = $('form#login-testing');
		
		localStorage.setItem('lastSelectedUser', $form.find('select[name="user_id"]').val());
		localStorage.setItem('lastSelectedTesting', $form.find('select[name="testing_id"]').val());
		
		this.SETTINGS.fetchOpts.body = $form.serialize();
		
		console.log('request ->', this.SETTINGS.fetchOpts);
		
		fetch(APP.dataUrl, this.SETTINGS.fetchOpts)
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

        this.updateStats();
	},
	
	metaDataFormatter: function(index)	{
		var item = this.dataView.getItem(index);
		if (item.tcStatus){
		    return {
		        cssClasses: 'status-' + item.tcStatus
		    };		
	    }
	},
	
	dataViewFilter: function (item, args) {
		if (!args || args.q == "")
			return true;		
		
		var data = args.path.reduce((prev, curr) => prev[curr], item);		 
			 
		//костыль, потому что решили оставить пустые статусы
		if (args.q === "N") {
			return data === "";
		}

		return data.toLowerCase().includes(args.q.toLowerCase());
	},
	
	editCommandHandler: function(item, column, editCommand) {
		
		var id = item.id;
		var field = column.id;
		var data = editCommand.serializedValue;
		this.SETTINGS.fetchOpts.body = "action=edit&id=" + id + "&" + field + "=" + data;				

		console.log('request ->', this.SETTINGS.fetchOpts);
		
		fetch(
			this.dataUrl, this.SETTINGS.fetchOpts
		).then(
			resp => resp.text()			
		).then(
			respText => {
				
				try {
					var jresp = JSON.parse(respText);
					console.log('response <-', jresp);
					editCommand.execute();
					this.grid.invalidateRow(editCommand.row);
					this.grid.render();
					
				} catch (e){
					console.log(e);
					Modal.alert(respText);		
				}				
				
			}
		).catch(
			function(err){
				Modal.alert(err);
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
			Modal.alert('You have to pass or fail TC before post to SoftDev');			
			return;
		}	
		
		if (!confirm('post result?')) 
			return;		
		
		if (rowData.softdev == 1) 
			if (!confirm('RE-post result?')) 
				return;		
		
		if (rowData.softdev == 'wait') {
			Modal.alert('Please wait...');
			return;	
		}
		
		rowData.softdev = 'wait';		
		this.dataView.updateItem(rowData.id, rowData);
		this.grid.invalidateRow(cell.row);
		this.grid.render();
		
		this.SETTINGS.fetchOpts.body = "action=sdpost&sheetentityid=" + rowData.id;
		
		console.log('request ->', this.SETTINGS.fetchOpts);
						
		fetch(
			this.dataUrl, this.SETTINGS.fetchOpts
		).then(
			resp => resp.text()			
		).then(
			resp => {				
				try {
					var jresp = JSON.parse(resp);
					console.log('response <-', jresp);
					if (jresp.status == "Passed" || jresp.status == "Failed"){
						rowData.softdev = 1;
						Notify.send('Testcase ' + rowData.storageTC.tc_id + ' posted to SoftDev. ' + jresp.name);
					} else {
						Modal.alert(resp);
						rowData.softdev = 0;	
					}
					
				} catch (e){
					Modal.alert(resp);					
					rowData.softdev = 0;
				}
				
				this.dataView.updateItem(rowData.id, rowData);
				this.grid.invalidateRow(cell.row);
				this.grid.render();
			}
		).catch(
			function(err){
				Modal.alert(err);
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
		
		var envId = $('select[name="env_id"]').val();
		if (envId == ""){
			Modal.alert('Select env!')
			return;
		}

		this.SETTINGS.fetchOpts.body = 
			"action=edit&id=" + rowData.id
			+ "&edt_env_id=" + envId
			+ "&edt_status=" + newStatus
			+ "&edt_tqc_ver=" + view.appVer.tqcver 
			+ ((rowData.storageTC.isLab) ? "&edt_lab_ver=" + view.appVer.labver : '')
			+ ((rowData.storageTC.isGene) ? "&edt_gene_ver=" + view.appVer.genever : '');   		

		console.log('request ->', this.SETTINGS.fetchOpts);	
		
		fetch(
			this.dataUrl, this.SETTINGS.fetchOpts
		).then(
			resp => resp.text()
		).then(
			respText => {				
				try {
					var jresp = JSON.parse(respText);
					console.log('response <-', jresp);				
					this.dataView.updateItem(rowData.id, jresp);
					this.grid.invalidate();
					this.grid.render();
					this.updateStats();
					
				} catch (e){
					Modal.alert(respText);		
				}					
			}
		).catch(
			Modal.alert.bind(Modal)
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

	getSTTestPlan: function (){
		
		var testCases = this.dataView.getItems();
		var testPlan = '';
		
		var getSpaces = function (string){
			var count = 30 - string.length;
			return (new Array(count)).join(' ');
		}
		
		testCases.forEach((testCase) => {
			
			if (testCase.storageTC.auto_ide !== "ST")
				return;
			
			if (testCase.tcStatus !== "")
				return;
			
			if (testCase.storageTC.features.toLowerCase().includes("critical"))
				return;
			
			if (testCase.storageTC.duration === 0)
				return;			
			
			testPlan += '[+] ' + testCase.storageTC.tc_id + getSpaces(testCase.storageTC.tc_id) + '//' + '\t' + testCase.storageTC.run_path + '\n';
			testPlan += '\t[ ] ' + 'script: ' + testCase.storageTC.run_path + '\n';
			testPlan += '\t[ ] ' + 'testcase: ' + testCase.storageTC.run_param + '\n';			
		});
		
		copyToClipboard(testPlan);		
		
		Modal
		.setHeader('Test Plan')
		.printMsg('Test Plan generated and copied to clipboard.')
		.show();
	},
	
	SETTINGS: {
		"storage": {
			columns: [
               {id: "tc_id", 		name: "TC ID", 		field: "tc_id", 		width: 180, 								sortable: true									},    
               {id: "edt_author", 	name: "Author", 	field: "author", 		width: 50, 	editor: Slick.Editors.Select, 	options: view.users ? view.users.reduce((prev, curr) => {prev.push(curr.id); return prev;}, []) : [], 	},
               {id: "edt_step_num", name: "Step Count", field: "step_num", 		width: 65,	editor: Slick.Editors.Integer													},
               {id: "edt_duration", name: "Duration", 	field: "duration", 		width: 65,  editor: Slick.Editors.Integer,	sortable: true, 								},
               {id: "auto_ide", 	name: "Auto Ide", 	field: "auto_ide",		width: 65,  								sortable: true									},
               {id: "apps",			name: "Apps", 		field: "apps", 			width: 65,  editor: Slick.Editors.Text														},
               {id: "tags",			name: "Tags", 		field: "tags", 			width: 100, editor: Slick.Editors.Text														},
               {id: "edt_testSetId",name: "Set Name", 	field: "testSetId", 	width: 150,	editor: Slick.Editors.Select, 	options: view.testsets,		sortable: true, 	formatter: (a, b, c) => view.testsets[c.toString()],},
               {id: "edt_features", name: "Features", 	field: "features", 		width: 200, editor: Slick.Editors.LongText													},    
               {id: "edt_run_path", name: "Run path", 	field: "run_path", 		width: 200, editor: Slick.Editors.Text,										},
               {id: "edt_run_param",name: "Run param", 	field: "run_param", 	width: 100, editor: Slick.Editors.Text,										},
               {id: "edt_is_lab",	name: "Is Lab", 	field: "isLab", 		width: 50,  editor: Slick.Editors.Text,										},
               {id: "edt_is_gene",	name: "Is Gene", 	field: "isGene", 		width: 50,  editor: Slick.Editors.Text,										},
           ],
           	options: {
    		    autoEdit: true,
    		    editable: true,
    		    enableAddRow: false,
    		    enableCellNavigation: true,    		    
    		    rowHeight: 25,
    		    cellFlashingCssClass: 'flash-cell',
    		    enableColumnReorder: false,
    		    multiColumnSort: true,
       		}
		},
		"testing": {
			columns: [
          	    {id: "tc_id", 			name: "TC ID", 			field: "storageTC", 	width: 140, sortable: true, formatter: (a, b, c) => c.tc_id },    
          	    {id: "author", 			name: "Author", 		field: "storageTC", 	width: 50, 	formatter: (a, b, c) => c.author},
          	 	{id: "edt_runner", 		name: "Runner", 		field: "runner", 		width: 50, editor: Slick.Editors.Select, options: view.users ? view.users.reduce((prev, curr) => {prev.push(curr.id); return prev;}, []) : []},
          	    {id: "step_num", 		name: "Step Count", 	field: "storageTC", 	width: 65, formatter: (a, b, c) => c.step_num	},
          	    {id: "edt_tduration", 	name: "Duration", 		field: "tduration", 	width: 65, sortable: true, editor: Slick.Editors.Integer},
          	 	{id: "local_set", 		name: "Set Name", 		field: "storageTC", 	width: 150,	formatter: true, formatter: (a, b, c) => c.testSet.local_set, sortable: true},
          	 	{id: "edt_status", 		name: "Status TC", 		field: "tcStatus", 		width: 70,  header: {menu: {items: [{title: "All", command: "A"}, {title: "N", command: "N"}, {title: "P", command: "P"}, {title: "F", command: "F"}, {title: "W", command: "W"}, {title: "C", command: "C"}, {title: "I", command: "I"}]}} },
          	 	{id: "apps", 			name: "Application", 	field: "storageTC", 	width: 80, 	formatter: (a, b, c) => c.apps},   	    
          	 	{id: "edt_comment", 	name: "Comment", 		field: "comment", 		width: 200, editor: Slick.Editors.LongText},
          	    {id: "features", 		name: "Features", 		field: "storageTC", 	width: 200, formatter: (a, b, c) => c.features},
          	    {id: "softdev", 		name: "SoftDev", 		field: "softdev", 		width: 50, formatter: sdFormatter},
          	 	{id: "edt_tqc_ver", 	name: "TQC ver", 		field: "tqcVer", 		width: 60, editor: Slick.Editors.Text}, 
          		{id: "edt_lab_ver", 	name: "LAB ver", 		field: "labVer", 		width: 60, editor: Slick.Editors.Text}, 
          		{id: "edt_gene_ver", 	name: "GENE ver", 		field: "geneVer", 		width: 60, editor: Slick.Editors.Text},
          		{id: "edt_env_id", 		name: "Env", 			field: "env", 			width: 50, formatter: (a, b, c) =>  c ? c.name : ''},
          		{id: "edt_fail_info", 	name: "Fail Info", 		field: "failInfo", 		width: 60, editor: Slick.Editors.FailInfo},          		
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
		},
		fetchOpts: {
			method: 'post',  
			credentials: 'include',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
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
function sdFormatter (row, cell, value) {
    switch (value) {        
        case 1      : return '&#10004';
        case 'wait' : return '<div class="loader"></div>';
        default : return '<a href="">post</a>';
    }
}

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