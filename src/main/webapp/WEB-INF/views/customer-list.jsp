<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Customer Relationship Management System</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" align="center">
				<button " class="btn btn-primary"
					onclick="window.location.href='addCustomerForm'">Add
					Customer</button>

				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

						<core:forEach var="var" items="${customerModel}">
							<core:url var="delete" value="deleteCustomer">
								<core:param name="customerId" value="${var.id}"></core:param>
							</core:url>

							<core:url var="edit" value="editCustomer">
								<core:param name="customerId" value="${var.id}"></core:param>
							</core:url>

							<tr>
								<td>${var.id}</td>
								<td>${var.firstName}</td>
								<td>${var.lastName}</td>
								<td>${var.email}</td>
								<td><a href="${edit}">Edit</a>|<a href="${delete}"
									onClick="if(!(confirm('Are you sure'))) return false">Delete</a></td>
							</tr>
						</core:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>