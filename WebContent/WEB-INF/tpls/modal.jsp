<div id="m-modal" class="modal">
	<div id="m-content" class="modal-content">
		<div class="modal-header">
			<span id="m-close" class="close">×</span>
			<h2 id="m-header"></h2>
		</div>
		<div id="m-body" class="modal-body"></div>
		<div id="m-msg" class="modal-msg"></div>
		<div class="m-footer">
			<div id="m-footer"></div>
		</div>
	</div>
</div>

<script type="text/javascript">

	var Modal = {
			
		init: function() {
			this.modal = document.getElementById('m-modal');
			this.closeButton = document.getElementById('m-close');			
			this.header = document.getElementById('m-header');
			this.body = document.getElementById('m-body');
			this.footer = document.getElementById('m-footer');			
			this.msg = document.getElementById('m-msg');
			this.modalContent = document.getElementById('m-content');
			
			this.modalContent.onclick = this.stopProp.bind(this);
			this.closeButton.onclick = this.close.bind(this);
			this.modal.onclick = this.close.bind(this);
		},
		
		setHeader: function(text) {
			this.header.innerText = text;
			
			return this;
		},
		
		printMsg: function(text) {
			this.msg.innerText = text;
			
			return this;
		},
		
		alert: function(text) {
			this.setHeader("Alert")
			this.msg.innerHTML = text;
			this.show();
			
			return this;
		},
		
		setContent: function(node) {
			
			while (this.body.firstChild) {
				this.body.removeChild(this.body.firstChild);
			}
			
			this.body.appendChild(node);
			
			return this;
		},
		
		setFooter: function(node) {
			while (this.footer.firstChild) {
				this.footer.removeChild(this.footer.firstChild);
			}		
			this.footer.appendChild(node);
			
			return this;
		},
		
		show: function() {
			this.modal.style.display = "block";
		},
		
		stopProp: function(e){
			e.cancelBubble = true;
		},
		
		close: function(event){			
			this.modal.style.display = "none";
		},
			
	}	
	
	Modal.init();

</script>