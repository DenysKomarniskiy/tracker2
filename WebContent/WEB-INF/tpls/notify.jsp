<style>
	.notify-area {
	    display: flex;
	    flex-direction: column-reverse;
	    justify-content: flex-start;    
		z-index: 3;
	    position: absolute;
	    width: 300px;   
	    height: 100vh;
	    right: 20px;
	    pointer-events: none;
	}
	
	.notify-box {
	    pointer-events: all;
	    background: rgba(0,0,0,0.7);
	    margin: 10px 0;
	    border-radius: 5px;
	    transition: all .3s ease;
	    transform: translate(0, 0);
	    opacity: 1;
	}
	
	.notify-box.hidden {
		transform: translate(0, -100px);
		opacity: 0;
	}
	
	.notify-header span.n-header {
		display: inline-block;
		width: 90%;
		color: white;
	    font-size: 15px;
	}
	
	.notify-body {
	    padding: 10px;
	    color: #fff;
	    font-size: 14px;
	}
	
	.notify-header span.n-close {
		display: inline-block;
	    color: white;
	    font-size: 15px;
	    cursor:pointer;
	    float:right;
	    padding-right: 5px;
	}
</style>

<div id="n-area" class="notify-area">

</div>


<div id="n-box" class="notify-box hide">
	<div class="notify-header">
		<span class="n-header"></span>
		<span class="n-close">×</span>
	</div>
	<div class="notify-body"></div>
	<div class="notify-msg"></div>
	<div class="notify-footer"></div>
</div>

<script type="text/javascript">

	var Notify = {
			
		init: function() {
			this.nArea = document.getElementById('n-area');
			this.nBox = document.getElementById('n-box');
			
		},
		
		send: function(text) {
			var nbox = this.nBox.cloneNode(true);
			nbox.id = "";
			nbox.classList.remove('hide');
			nbox.classList.add('hidden');
			
			var closeEl = nbox.getElementsByClassName('n-close')[0];
			var bodyEl = nbox.getElementsByClassName('notify-body')[0];
			bodyEl.innerText = text;
			closeEl.onclick = () => {nbox.remove()};			
			
			this.nArea.appendChild(nbox);
			setTimeout(() => {nbox.classList.remove('hidden');}, 100);
				
		}
			
	}	
	
	Notify.init();

</script>