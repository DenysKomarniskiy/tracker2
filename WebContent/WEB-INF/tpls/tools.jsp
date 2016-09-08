<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="models.entities.Testing" %>

<div class="content flex-container-row">


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
			<form action="controlpanel" method="post">
				<label>Testing name
					<input type="text" name="name" value="" required/>
				</label>
				<input type="submit" name="action" value="generate" />
			</form>
		</div>
		
		<div>	
			<h4>Delete testing</h4>
			<form action="controlpanel" method="post">
				<label>select testing
					<select name="testing_id">
					    <c:forEach items="${testings}" var="testing">						   
					        <option value="${testing.id}">${testing.name}</option>
					    </c:forEach>
					</select>
				</label>
				<input type="submit" name="action" value="delete" />
			</form>			
		</div>
	</div>
	
	
</div>	

<script type="text/javascript">	
	var view = {};
	view['data'] = [];
	
</script>