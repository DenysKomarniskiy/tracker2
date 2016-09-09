<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tools content flex-container-row">

	<div class="widget send-mail">
		<h3>Send mail</h3>
		<form id="SendingMail" action="tools" method="post">
				<label>select testing
					<select name="testing_id">
					    <c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>
					</select>
				</label>
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