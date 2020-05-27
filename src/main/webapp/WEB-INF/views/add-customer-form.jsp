<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Customer</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" align="center">

				<form:form action="saveCustomer" modelAttribute="customerModel"
					method="POST">
					<form:hidden path="id"/>
					<table class="table">

						<tbody>
							<tr>
								<td><label>First Name</label></td>
								<td><form:input path="firstName" />
								<form:errors path="firstName" /></td>
								
							</tr>

							<tr>
								<td><label>Last Name</label></td>
								<td><form:input path="lastName" />
								<form:errors path="lastName" /></td>
							</tr>

							<tr>
								<td><label>Email</label></td>
								<td><form:input path="email" />
								<form:errors path="email" /></td>
							</tr>

							<tr>
								<td><label></label></td>
								<td><input type="submit" value="save" /></td>
								<td><a href="${pageContext.request.contextPath}/customer/list">Go Back</a></td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>