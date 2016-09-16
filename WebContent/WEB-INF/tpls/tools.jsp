<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tools content flex-container-row">

	<div class="widget send-mail">
		<h3>Send mail to managers</h3>
		<form id="send-mail" action="tools" method="post">
			<input type="hidden" name="action" value ="sendmail"/>
			<div>
				<label>Select testing
					<select name="testing_id">
					    <c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>
					</select>
				</label>
			</div>
			<div>
				<label>Enter version
					<input type="text" name="tqc_version" value="1.0" required/>
				</label>
			</div>
			<div>
				<label>Enter official env's
					<input type="text" name="official_env" value="zw10, pt92, QA34_02" required/>
				</label>
			</div>
			<br>
			</br>
			<input type="submit" name="action" value="Send Mail" />
		</form>
	</div>	
	
	<div class="widget testing-manager">
		<h3>Testing management</h3>
		<div>
			<h4>New testing</h4>
			<form id="gen-testing" action="controlpanel" method="post">
				<label>Testing name
					<input type="text" name="testing_name" value="" required/>
				</label>
				<input type="hidden" name="action" value ="generate"/>
				<input type="submit" name="button" value="Generate" />
			</form>
		</div>
		
		<div>	
			<h4>Delete testing</h4>
			<form action="controlpanel" method="post" onsubmit="return confirm('Do you really want to delete this testing?');">
				<label>select testing
					<select name="testing_id">
					    <c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>
					</select>
				</label>
				<input type="hidden" name="action" value ="delete"/>
				<input type="submit" name="button" value="delete" />
			</form>			
		</div>
	</div>
	
	
</div>	

<script type="text/javascript">	
	var view = {};
	view['data'] = [];	
</script>