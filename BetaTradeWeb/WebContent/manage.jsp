<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Controller</title>
<script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function createUserAccount() {

		var urlpre = "rest/betatrade/createUserAccount";
		var param = "?" + "username=" + $("#create-username").val()
				+ "&password=" + $("#create-password").val() + "&email="
				+ $("#create-email").val() + "&phone="
				+ $("#create-phone").val() + "&cashBalance="
				+ $("#create-cashBalance").val();
		var url = urlpre + param;

		$.ajax({
			url : url,
			type : "get",
			dataType : "text",
			success : function(message) {

				if (message == null) {
					alert("can not create");
					return;
				}
				alert("success");
			},

			error : function(xhr, message) {
				alert(message);
			}
		});
	}

	function updateUserAccount() {

		var urlpre = "rest/betatrade/updateUserAccount";
		var param = "?" + "username=" + $("#update-username").val()
				+ "&password=" + $("#update-password").val() + "&email="
				+ $("#update-email").val() + "&phone="
				+ $("#update-phone").val() + "&cashBalance="
				+ $("#update-cashBalance").val();
		var url = urlpre + param;

		$.ajax({
			url : url,
			type : "get",
			dataType : "text",
			success : function(message) {

				if (message == null) {
					alert("there is no username");
					return;
				}
				alert("success");
			},

			error : function(xhr, message) {
				alert(message);
			}
		});
	}

	function getUserAccount() {

		var urlpre = "rest/betatrade/getUserAccount";
		var param = "?" + "username=" + $("#get-username").val();
		var url = urlpre + param;

		$.ajax({
			url : url,
			type : "get",
			dataType : "json",
			success : function(message) {
				if (message == null) {
					alert("there is no username");
					return;
				}
				alert("success");
				$("#update-username").val(message.username);
				$("#update-password").val(message.password);
				$("#update-email").val(message.email);
				$("#update-phone").val(message.phone);
				$("#update-cashBalance").val(message.cashBalance);
			},

			error : function(xhr, message) {
				alert(message);
			}
		});
	}

	function deleteUserAccount() {

		var urlpre = "rest/betatrade/deleteUserAccount";
		var param = "?" + "username=" + $("#get-username").val();
		var url = urlpre + param;

		$.ajax({
			url : url,
			type : "get",
			success : function(message) {
				if (message == null) {
					alert("there is no username");
					return;
				}
				alert("success");
			},

			error : function(xhr, message) {
				alert(message);
			}
		});
	}
</script>

</head>
<body>



	<table>
		<tr>
			<td>Create UserAccount<br>
				<table>
					<tr>
						<td>Username</td>
						<td><input type="textbox" id="create-username">
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="textbox" id="create-password">
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="textbox" id="create-email">
					</tr>
					<tr>
						<td>Phone</td>
						<td><input type="textbox" id="create-phone">
					</tr>
					<tr>
						<td>Cash Balance</td>
						<td><input type="textbox" id="create-cashBalance">
					</tr>
					<tr>
						<td><button onclick="createUserAccount()">Create</button></td>

					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br>
	<br>
	<table>
		<tr>
			<td>GET UserAccount<br> Username: <input id="get-username"
				type="textbox">
				<button onclick="getUserAccount()">Get UserAccount</button>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>Update UserAccount<br>
				<table>
					<tr>
						<td>Username</td>
						<td><input type="textbox" id="update-username">
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="textbox" id="update-password">
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="textbox" id="update-email">
					</tr>
					<tr>
						<td>Phone</td>
						<td><input type="textbox" id="update-phone">
					</tr>
					<tr>
						<td>Cash Balance</td>
						<td><input type="textbox" id="update-cashBalance">
					</tr>
					<tr>
						<td><button onclick="updateUserAccount()">Update</button></td>

					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br>
	<br>

	<table>
		<tr>
			<td>Delete UserAccount<br> Username: <input
				id="delete-username" type="textbox">
				<button onclick="deleteUserAccount()">Delete</button>
			</td>
		</tr>
	</table>


</body>
</html>
