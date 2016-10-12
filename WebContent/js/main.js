/* main interface*/
function interfaceInit() {

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
    };
    
    var loadBar = document.getElementById('load-bar');
    loadBar.style.display = "none";
    
    Loader = {
		show: function() {
			loadBar.style.display = "block";
		},
		hide: function() {
			loadBar.style.display = "none";
		}
    };

};

var APP = {
	
	gridClickHandlers: [],
	gridPlugins: [],
		
	init: function(){
		
		this.resolveRoute();
		
		if (typeof this.initFace[this.faceName] === 'function') {
			this.initFace[this.faceName].apply(this);
		}	
		
		$("#search-tc").keyup(this.search.bind(this));	
		$('#reset-search').on('click', (e) => {$("#search-tc").val('').trigger('keyup')});
		
		this.gridPlugins.push(new Slick.AutoTooltips({}));
		
		this.dataView = new Slick.Data.DataView();	
		this.dataView.getItemMetadata = this.metaDataFormatter.bind(this);
		this.dataView.setFilter(this.dataViewFilter); // for search
		
		if (view.data) {				
			this.dataView.setItems(view.data);				
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
			item.softdev = item.softdev || 0;
			item.tqcVer = item.tqcVer || "";
			item.labVer = item.labVer || "";
			item.geneVer = item.geneVer || "";
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
		stats["stats-processed-proc-pcs"] = Math.ceil((stats["stats-processed-pcs"] / stats["stats-total-pcs"]) * 100) || 0;
		stats["stats-processed-proc-min"] = Math.ceil((stats["stats-processed-min"] / stats["stats-total-min"]) * 100) || 0;
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
			var excludedFields = ['id', 'active', 'rights'];
			
			var data = [], id = 0;
			$.each(view.userSettings, (key, val) => {
				if (!excludedFields.includes(key))
					data.push({	id: key, val: val});
			});			
			
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
			var initSendMail = () => {
				$('input[name="tqc_version"]').val(localStorage.getItem('tqcver'))
				var $sendMailForm = $('#send-mail');
				var $sendButton = $sendMailForm.find('#btn-send-mail');
				$sendButton.on('click', (e) => {
					if (!$sendMailForm[0].checkValidity())
						return;
					e.preventDefault();	
					$sendButton[0].disabled = true;
					this.SETTINGS.fetchOpts.body = $sendMailForm.serialize();

					fetch($sendMailForm.attr("action"), this.SETTINGS.fetchOpts)
					.then(resp => resp.text())
					.then(respText => {$sendButton[0].disabled = false;})
					.catch(Modal.alert.bind(Modal));	
				});
			}
			var initGenTesting = () => {
				$genTestingForm = $('#gen-testing');
				$genTestingForm.on('submit', (e) => {
					e.preventDefault();					
					this.SETTINGS.fetchOpts.body = $genTestingForm.serialize();
					
					$genTestingForm.find('button')[0].disabled = true;					
					
					fetch($genTestingForm.attr("action"), this.SETTINGS.fetchOpts)
					.then(resp => resp.text())
					.then(respText => {	
						showGeneratedTesting(respText)	
						$genTestingForm.find('button')[0].disabled = false;					
					})
					.catch(Modal.alert.bind(Modal));				
				});
			}			
			function showGeneratedTesting(respText) {
				try {
					var jResp = JSON.parse(respText);					
					var $table = $('<table />');
					
					$table.append('<tr><th>User</th><th>TC count</th><th>Total duration</th></tr>')
					$.each(jResp, (k, v) => {$table.append('<tr><td>' + k + '</td><td>' + v[0] + '</td><td>' + v[1] + '</td></tr>')});
					
					Modal.setHeader('New Testing was generated')
					.setContent($table[0])
					.show();
					
				} catch(e) {
					Modal.alert(respText);
				}				
			}
			function rerenderGrids(which){
				switch (which) {        
			        case 'left': 
			        	leftGrid.invalidate();
						leftGrid.render();
						break;
			        case 'right': 
			        	rightGrid.invalidate();
						rightGrid.render();
						break;
			        default : 
			        	leftGrid.invalidate();
						leftGrid.render();
						rightGrid.invalidate();
						rightGrid.render();
				}
			}
			function moveData(fromGrid, toGrid){
				var idsToDel = [];
				fromGrid.getSelectedRows().forEach(idx => {
					var item = fromGrid.getDataItem(idx);
					if (item) {
						toGrid.getData().addItem(item);
						idsToDel.push(item.id);
					}
				});
				idsToDel.forEach(id => fromGrid.getData().deleteItem(id));
				rerenderGrids();
			}
			function initGrids() {
				$customTestingEditor.removeClass('hide');
				leftGridDataView.setItems([]);
				rightGridDataView.setItems([]);		
				leftGrid.init();
				rightGrid.init();
				rerenderGrids();
			}			
			// init custom testing creation
			var $btnCreateTesting = $('#btn-create-custom-testing');
			var $btnEditTesting = $('#btn-edit-testing');
			var leftGridDataView = new Slick.Data.DataView();
			var rightGridDataView = new Slick.Data.DataView();			
			var leftCols = [
	        	{id: "tc_id", 		 name: "TC ID", 	 field: "tc_id", 		width: 150, sortable: true	},    
	        	{id: "local_set",	 name: "Set Name", 	 field: "testSet", 		width: 150,	sortable: true, 	formatter: (a, b, c) => c.local_set, },
                {id: "edt_author", 	 name: "Author", 	 field: "author", 		width: 50, 	},
			    {id: "edt_step_num", name: "Step Count", field: "step_num", 	width: 65,	},
			    {id: "edt_duration", name: "Duration", 	 field: "duration", 	width: 65,  sortable: true, },
           	];
			var rightCols = leftCols.slice();
			rightCols.push({id: "edt_runner", name: "Runner", field: "runner", width: 50, editor: Slick.Editors.Select, options: view.users ? view.users.reduce((prev, curr) => {prev.push(curr.id); return prev;}, []) : []});
			var leftGrid = new Slick.Grid("#left-grid", leftGridDataView, leftCols, {explicitInitialization: true, multiColumnSort: true});
			var rightGrid = new Slick.Grid("#right-grid", rightGridDataView, rightCols,  {explicitInitialization: true, multiColumnSort: true, editable: true});

			leftGridDataView.setFilter(this.dataViewFilter);
			leftGrid.setSelectionModel(new Slick.RowSelectionModel());
			rightGrid.setSelectionModel(new Slick.RowSelectionModel());
			leftGrid.onSort.subscribe(this.gridSortHandler.bind({dataView: leftGridDataView, grid: leftGrid}));
			rightGrid.onSort.subscribe(this.gridSortHandler.bind({dataView: rightGridDataView, grid: rightGrid}));
			
			
			var saveTesting = (testing) => {					
				this.SETTINGS.fetchOpts.body = JSON.stringify(testing);
				fetch('controlpanel/save', this.SETTINGS.fetchOpts)
				.then(resp => resp.text())
				.then(showGeneratedTesting)
				.catch(Modal.alert.bind(Modal));	
			}
			
			var $customTestingEditor = $('#custom-testing-editor');
			var $saveBtn = $customTestingEditor.find('#cte-save');
			
			$customTestingEditor.find("#search-cte-tc").keyup(this.search.bind({dataView: leftGridDataView, grid: leftGrid, searchPath: ['tc_id']}));
			$customTestingEditor.find('#reset-search').on('click', (e) => {$customTestingEditor.find("#search-cte-tc").val('').trigger('keyup')});			
			$customTestingEditor.find('#btn-add').click(() => {moveData(leftGrid, rightGrid)});			
			$customTestingEditor.find('#btn-remove').click(() => {moveData(rightGrid, leftGrid)});						
			
			$customTestingEditor.find('#btn-add-all').click(() => {								
				leftGridDataView.getItems().forEach(item => rightGridDataView.addItem(item));
				leftGridDataView.setItems([]);
				rerenderGrids();				
			});	
			
			$customTestingEditor.find('#btn-remove-all').click(() => {								
				rightGridDataView.getItems().forEach(item => leftGridDataView.addItem(item));
				rightGridDataView.setItems([]);
				rerenderGrids();				
			});	
			$btnCreateTesting.click(initGrids);			
			
			$btnCreateTesting.click((e) => {				
								
				this.SETTINGS.fetchOpts.body = "action=get";
				fetch('storage', this.SETTINGS.fetchOpts)
				.then(resp => resp.json())
				.then(respJson => {
					leftGridDataView.setItems(respJson);
					rerenderGrids();
				})
				.catch(Modal.alert.bind(Modal));
				
				$saveBtn.attr('disabled', false);				
				$saveBtn.unbind('click').click(() => {
					$saveBtn.attr('disabled', true);
					saveTesting({
						id: 0,
						name: $("#cte-name").val(), 
						list: rightGridDataView.getItems().map(item => ({id: item.id, runner: item.runner})),
						users: Array.prototype.map.call(document.querySelectorAll('#cteusers input:checked'), el => el.value)
					});
				});
				
				Modal
				.setHeader('New Custom Testing')
				.setContent($customTestingEditor[0])
				.show();	
			});
			
			$btnEditTesting.click((e) => {				
				var testingId = $('#testing_id').val();	
				var oldTestingData;
				
				if (testingId === ""){
					return;
				}				
				
				initGrids();
				
				e.preventDefault();							
				
				$.when(
					$.ajax({url: "storage", method: "POST", data: {action: "get"}, dataType: "json"}), 
					$.ajax({url: "testing", method: "POST", data: {testing_id: testingId, user_id: "all", action: "get"}, dataType: "json"})						
				).done((storageData, testingData) => {
					
					testingData = testingData[0].map(tItem => {
						tItem.storageTC['runner'] = tItem.runner;
						return tItem.storageTC;
					});					
					
					testingData.forEach(tItem => {
						storageData[0] = storageData[0].filter(sItem => sItem.id !== tItem.id)
					});
					
					leftGridDataView.setItems(storageData[0]);
					rightGridDataView.setItems(testingData);	
					rerenderGrids();
					oldTestingData = rightGridDataView.getItems().slice();
					
				});			
				
				$customTestingEditor.find("#cte-name").val($('#testing_id option:selected').text());
				$saveBtn.attr('disabled', false);
				$saveBtn.unbind('click').click(() => {
							
					var newTestingData = rightGridDataView.getItems();
					var list  = [];
					
					oldTestingData.map(oldItem => {						
						if (!newTestingData.find(newItem => oldItem.id === newItem.id))
							list.push({id: oldItem.id, sign: -1});						
					});
					newTestingData.map(newItem => {
						if (!oldTestingData.find(oldItem => oldItem.id === newItem.id))
							list.push({id: newItem.id, runner: newItem.runner, sign: 1});						
					});				
					
					$saveBtn.attr('disabled', true);
					
					saveTesting({
						id:  testingId,
						name: $("#cte-name").val(), 
						list,
						users: Array.prototype.map.call(document.querySelectorAll('#cteusers input:checked'), el => el.value)
					});
				});

				Modal
				.setHeader('Edit Custom Testing')
				.setContent($customTestingEditor[0])
				.show();
			});

			initSendMail();
			initGenTesting();
		},
		
		"testing": function() {
			// init vars
			this.dataUrl = 'testing';
			this.searchPath = ['storageTC','tc_id'];
			
			// load ver. stored values
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
			
			// init grid click handlers
			this.gridClickHandlers.push(this.statusRowClickHandler);
			this.gridClickHandlers.push(this.softdevRowClickHandler);
			
			// init status-tc menu
            this.$statusMenu = $("#status-menu");
            view.statuses.forEach(status => $('<li class="'+status.id.toLowerCase()+'" data-status="'+status.val+'">'+status.desc+'</li>').appendTo(this.$statusMenu));                        
            this.$statusMenu.on('click', this.setTcStatus.bind(this));
            
            // init stats header
            this.$statsHeaderSpans = $(".stats span[id^='stats']");
						
			// build interface
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
			
			var $testingSelector = $('form#login-testing');
			$testingSelector.on('submit', this.loadData.bind(this));			
			
			$(() => $testingSelector.trigger('submit'));
			
			$userSelect.on('change', this.loadData.bind(this));
			
			$('#get-testplan').on('click', this.getSTTestPlan.bind(this));	
			
			
			// add grid plugin
		    var headerMenuPlugin = new Slick.Plugins.HeaderMenu({});		   
		    headerMenuPlugin.onCommand.subscribe((e, args) => {
		    	Slick.GlobalEditorLock.cancelCurrentEdit();		    	
		    	
		    	var $tcStatusCol = $('.tc-status');
		    	
		    	view.statuses.forEach(status => {
		    		$tcStatusCol.removeClass(status.id);
		    	});
//		    	
		    	$tcStatusCol.addClass(args.command);
			    
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
			// init vars
			this.dataUrl = 'storage';
			this.searchPath = ['tc_id'];		
			var addForm = document.getElementById("add-testcase-form");		
			
			// build interface
			$('button#b-add').click(() => {
				var form = addForm.cloneNode(true);				
				
				$(form).find('input[name="edt_tc_id"]').on('blur', this.runPathFormatter);
				$(form).find('select[name="edt_test_set"]').on('change', this.runPathFormatter);
				
				$(form).find('button#btn-add-tc').click( (e) => {
					if (!form.checkValidity())			
						return;
					e.preventDefault();
					
					this.SETTINGS.fetchOpts.body = $(form).serialize();					
					fetch(APP.dataUrl, this.SETTINGS.fetchOpts)
					.then(resp => resp.text())
					.then(respText => {						
						try {
							var jresp = JSON.parse(respText);
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
		Loader.show();
		
		this.dataView.setItems([]);
		this.updateStats();
		this.grid.invalidate();
		this.grid.render();		
		
		var $form = $('form#login-testing');
		
		localStorage.setItem('lastSelectedUser', $form.find('select[name="user_id"]').val());
		localStorage.setItem('lastSelectedTesting', $form.find('select[name="testing_id"]').val());
		
		this.SETTINGS.fetchOpts.body = $form.serialize();
		fetch(this.dataUrl, this.SETTINGS.fetchOpts)
		.then(resp => resp.json())
		.then(this.updateGrid.bind(this))
		.catch(Modal.alert.bind(Modal));
	},	
	
	updateGrid: function(data) {
	    
		this.dataView.setItems(this.validateData(data));
		this.updateStats();
		this.grid.invalidate();
		this.grid.render();

        Loader.hide();
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
			 
		// костыль, потому что решили оставить пустые статусы
		if (args.q === "E") {
			return data === "";
		}

		return data.toLowerCase().includes(args.q.toLowerCase());
	},
	
	editCommandHandler: function(item, column, editCommand) {
		
		this.SETTINGS.fetchOpts.body = "action=edit&id=" + item.id + "&" + column.id + "=" + editCommand.serializedValue;
		fetch(this.dataUrl, this.SETTINGS.fetchOpts)
		.then(resp => resp.text())
		.then(respText => {				
			try {
				var jresp = JSON.parse(respText);
				editCommand.execute();
				this.grid.invalidateRow(editCommand.row);
				this.grid.render();				
			} catch (e){
				Modal.alert(respText);		
			}
		})
		.catch(Modal.alert.bind(Modal));
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
		fetch(this.dataUrl, this.SETTINGS.fetchOpts)
		.then(resp => resp.text())
		.then(resp => {				
			try {
				var jresp = JSON.parse(resp);
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
		})
		.catch(Modal.alert.bind(Modal));
		
	    
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
			Modal.alert('Select env!');
			return;
		}

		this.SETTINGS.fetchOpts.body = 
			"action=edit&id=" + rowData.id
			+ "&edt_env_id=" + envId
			+ "&edt_status=" + newStatus
			+ "&edt_tqc_ver=" + view.appVer.tqcver 
			+ ((rowData.storageTC.isLab) ? "&edt_lab_ver=" + view.appVer.labver : '')
			+ ((rowData.storageTC.isGene) ? "&edt_gene_ver=" + view.appVer.genever : '');
		
		fetch(this.dataUrl, this.SETTINGS.fetchOpts)
		.then(resp => resp.text())
		.then(respText => {				
			try {
				var jresp = JSON.parse(respText);	
				this.dataView.updateItem(rowData.id, jresp);
				this.grid.invalidate();
				this.grid.render();
				this.updateStats();				
			} catch (e){
				Modal.alert(respText);		
			}					
		})
		.catch(Modal.alert.bind(Modal));
	},
	
	gridSortHandler: function (e, args) {
		this.dataView.sort(function (row1, row2) {
			
//			console.log(args, row1, row2)
			
	        for (var i = 0, l = args.sortCols.length; i < l; i++) {
	            var field = args.sortCols[i].sortCol.field;	        	
	            var sign = args.sortCols[i].sortAsc ? 1 : -1;
	            var field_id = args.sortCols[i].sortCol.id;
	            
	            var x = row1[field], y = row2[field];	
	            
	            if (field === 'testSet') {
	            	x = row1[field][field_id];
	            	y = row2[field][field_id];
	            }	            
	            if (field === 'storageTC' && field_id === 'local_set') {
	            	x = row1[field].testSet[field_id];
	            	y = row2[field].testSet[field_id];
	            } else if (field === 'storageTC') {
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
	
	runPathFormatter: function() {
		var form = $(this).parents('form');
		var fullId = form.find('input[name="edt_tc_id"]').val();
		var testSet = form.find('select[name="edt_test_set"] > option:selected').text();
		var idParts = fullId.split(":");
		var $runPathInput = form.find('input[name="edt_run_path"]')
		
		if (!testSet || !fullId){
			$runPathInput.val('');
			return;
		}
		
		var runPath = testSet + '\\';
		
		if (idParts[1]) {
			runPath += idParts[0] + '\\' + idParts[1] + '\\' + idParts[1] + '.t';
		} else {
			runPath +=  idParts[0] + '\\' + idParts[0] + '.t';
		}
		
		$runPathInput.val(runPath);		
	},
	
	SETTINGS: {
		"storage": {
			columns: [
               {id: "tc_id", 		name: "TC ID", 		field: "tc_id", 		width: 180,	sortable: true,									formatter: (a, b, c) => ('<a target="_blank" href="' + APP.SETTINGS.softDevLink + (c.split(":")[1] || c) + '">' + c + '</a>') },    
               {id: "edt_author", 	name: "Author", 	field: "author", 		width: 50,	sortable: true,	editor: Slick.Editors.Select, 	options: view.users ? view.users.reduce((prev, curr) => {prev.push(curr.id); return prev;}, []) : [], 	},
               {id: "edt_step_num", name: "Step Count", field: "step_num", 		width: 65,	sortable: true,	editor: Slick.Editors.Integer},
               {id: "edt_duration", name: "Duration", 	field: "duration", 		width: 65,	sortable: true, editor: Slick.Editors.Integer},
               {id: "auto_ide", 	name: "Auto Ide", 	field: "auto_ide",		width: 65,  sortable: true},
               {id: "apps",			name: "Apps", 		field: "apps", 			width: 65, 					editor: Slick.Editors.Text},
               {id: "tags",			name: "Tags", 		field: "tags", 			width: 100, 				editor: Slick.Editors.Text},
               {id: "edt_testSetId",name: "Set Name", 	field: "testSetId", 	width: 150,	sortable: true,	editor: Slick.Editors.Select, 	options: view.testsets,		 	formatter: (a, b, c) => view.testsets[c.toString()],},
               {id: "edt_features", name: "Features", 	field: "features", 		width: 200, 				editor: Slick.Editors.LongText},    
               {id: "edt_run_path", name: "Run path", 	field: "run_path", 		width: 200, 				editor: Slick.Editors.Text},
               {id: "edt_run_param",name: "Run param", 	field: "run_param", 	width: 100, 				editor: Slick.Editors.Text},
               {id: "edt_is_lab",	name: "Is Lab", 	field: "isLab", 		width: 50,	sortable: true,	editor: Slick.Editors.Text},
               {id: "edt_is_gene",	name: "Is Gene", 	field: "isGene", 		width: 50,	sortable: true,	editor: Slick.Editors.Text},
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
          	    {id: "tc_id", 			name: "TC ID", 			field: "storageTC", 	width: 140, sortable: true, formatter: (a, b, c) => ('<a target="_blank" href="' + APP.SETTINGS.softDevLink + (c.tc_id.split(":")[1] || c.tc_id) + '">' + c.tc_id + '</a>') },    
          	    {id: "author", 			name: "Author", 		field: "storageTC", 	width: 50, 					formatter: (a, b, c) => c.author},
          	 	{id: "edt_runner", 		name: "Runner", 		field: "runner", 		width: 50,					editor: Slick.Editors.Select, options: view.users ? view.users.reduce((prev, curr) => {prev.push(curr.id); return prev;}, []) : []},
          	    {id: "step_num", 		name: "Step Count", 	field: "storageTC", 	width: 65,					formatter: (a, b, c) => c.step_num	},
          	    {id: "edt_tduration", 	name: "Duration", 		field: "tduration", 	width: 65,	sortable: true, editor: Slick.Editors.Integer},
          	 	{id: "local_set", 		name: "Set Name", 		field: "storageTC", 	width: 150,	sortable: true, formatter: (a, b, c) => c.testSet.local_set, sortable: true},
          	 	{id: "edt_status", 		name: "Status TC", 		field: "tcStatus", 		width: 70,  sortable: true, headerCssClass: "tc-status", header: {menu: {items: view.statuses ? view.statuses.slice().reduce((prev, status) => {prev.push({title: status.desc, command: status.id}); return prev;}, [{title: "All", command: "A"}]) : []}} },
          	 	{id: "apps", 			name: "Application", 	field: "storageTC", 	width: 80,	sortable: true, formatter: (a, b, c) => c.apps},   	    
          	 	{id: "edt_comment", 	name: "Comment", 		field: "comment", 		width: 200,					editor: Slick.Editors.LongText},
          	    {id: "features", 		name: "Features", 		field: "storageTC", 	width: 200,					formatter: (a, b, c) => c.features},
          	    {id: "softdev", 		name: "SoftDev", 		field: "softdev", 		width: 50,	sortable: true, formatter: sdFormatter},
          	 	{id: "edt_tqc_ver", 	name: "TQC ver", 		field: "tqcVer", 		width: 60,	sortable: true,	editor: Slick.Editors.Text}, 
          		{id: "edt_lab_ver", 	name: "LAB ver", 		field: "labVer", 		width: 60,	sortable: true,	editor: Slick.Editors.Text}, 
          		{id: "edt_gene_ver", 	name: "GENE ver", 		field: "geneVer", 		width: 60,	sortable: true,	editor: Slick.Editors.Text},
          		{id: "edt_env_id", 		name: "Env", 			field: "env", 			width: 50,					formatter: (a, b, c) =>  c ? c.name : ''},
          		{id: "edt_fail_info", 	name: "Fail Info", 		field: "failInfo", 		width: 60,					editor: Slick.Editors.FailInfo},          		
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
		},
		softDevLink: 'http://sd01srv.softsystem.pl:8080/SoftDev/View?ufi=STC-TQC-'		
	},
	
}
	
interfaceInit();
APP.init();

/* helpers */
function sdFormatter (row, cell, value) {
    switch (value) {        
        case 1      : return '&#10004';
        case 'wait' : return '<div class="loader"></div>';
        default 	: return '<a class="no-action" href="">post</a>';
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
        console.log('Oops, unable to copy', err);
    }

    document.body.removeChild(textArea);

}