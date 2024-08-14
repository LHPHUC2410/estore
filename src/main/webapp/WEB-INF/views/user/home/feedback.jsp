<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h2>FEEDBACK</h2>
<h4>${message}</h4>
<form:form action="/home/feedback" modelAttribute="form" method="post">
	<form:errors path="*" element="ul" cssClass="errors"/>
    <form:hidden path="subject" value="Feedback" />
	<div class="form-group">
		<label>Email Address</label>
		<form:input path="from" class="form-control" />
	</div>
	<div class="form-group">
		<label>Comment</label>
		<form:textarea path="body" class="form-control" rows="3"/>
	</div>

	<div class="form-group">
		<button class="btn btn-default">Send</button>
	</div>
</form:form>


