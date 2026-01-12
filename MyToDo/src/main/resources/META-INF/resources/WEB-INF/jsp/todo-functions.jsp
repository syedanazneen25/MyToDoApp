<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<h1> Enter ToDo Details </h1>
    <form:form method="post" modelAttribute="toDo">
                    <fieldset class="mb-3">
                        <form:label path="taskName"> Task Name</form:label>
                        <form:input type="text" path="taskName" required="required"/>
                    </fieldset>
                    <fieldset class="mb-3">
                        <form:label path="taskDescription"> Description</form:label>
                        <form:input type="text" path="taskDescription" required="required"/>
                        <form:errors path="taskDescription" cssClass="text-warning"/>
    				</fieldset>
    				<fieldset class="mb-3">
                        <form:label path="dueDate"> Target Date </form:label>
                        <form:input type="text" path="dueDate" required="required"/>
                        <form:errors path="dueDate" cssClass="text-warning"/>
                    </fieldset>
    				<form:input type="hidden" path="id"/>
                    <form:input type="hidden" path="done"/>
    				<input type="submit" class="btn btn-success"/>

    </form:form>

</div>
<%@ include file="common/footer.jspf" %>
        <script type="text/javascript">
    	$('#dueDate').datepicker({
    	    format: 'yyyy-mm-dd'
    	});
    </script>


