


<script type="text/javascript">
	var selectedUser;

	function initUserTable() {
		var table = document.getElementById("usersTable");
		document.getElementById("usersTableBody").innerHTML = "";
	}

	function validatePhoneNumber(inputtxt) {
		var phoneno = /^\d{10}$/;
		if (inputtxt.match(phoneno)) {
			return true;
		} else {
			bootbox.alert("Please enter 10 digit valid Phone Number!");
			return false;
		}
	}

	function signup() {

		if (validatePhoneNumber($('#form-phoneNumber').val())) {
			var name = $('#form-firstName').val() + " "
					+ $('#form-lastName').val()
			$.ajax({
						url : "welcome?method=insert&phoneNumber="
								+ $('#form-phoneNumber').val() + "&firstName="
								+ $('#form-firstName').val() + "&lastName="
								+ $('#form-lastName').val() + "&member="
								+ $('#form-member').val(),
						success : function(result) {
							$("#messageDisplay")
									.html("Thank you <b>"
													+ name
													+ "</b> for visiting us today. There are interesting upcoming Temple events. Please check them out. Your page will refresh with events in a few secs");
							$("#signup")[0].reset();
							$('#modal-login').modal('hide');

						setTimeout('redirect()', 10000);
						}
					});
		}

		return false;
	}

	function redirect() {
		window.location = "events.html";
	}
	function insertLog() {

		$.ajax({
					url : "welcome?method=insertLog&phoneNumber="
							+ selectedUser.phoneNumber + "&firstName="
							+ selectedUser.firstName + "&lastName="
							+ selectedUser.lastName,
					success : function(result) {
						initUserTable();
						$("#messageDisplay")
								.html(
										"Thank you for visiting us today. Your page is being redirected to interesting temple events shortly.");
					setTimeout('redirect()', 2000);
					}
				});
		return false;
	}
	function getList() {
		var phone = $("#list-phoneNumber").val();
		$
				.ajax({
					url : "welcome?method=list&phoneNumber=" + phone,
					success : function(result) {
						console.log(result);
						if (result.toString().startsWith("-")) {
							initUserTable();
						$("#messageDisplay")
									.html(
											"Thank you "
												+ result
													+ " for visiting us today. Your page is being redirected to interesting temple events shortly.");
							setTimeout('redirect()', 2000);
						} else {

							if (result.length == 0) {
								var phoneNumber = document
										.getElementById("list-phoneNumber").value;
								document.getElementById("form-phoneNumber").value = phoneNumber;
								$('#modal-login').modal('show');
								return;
							}

							var table = document.getElementById("usersTable");
							document.getElementById("usersTableBody").innerHTML = "";
							for (var i = 0; i < result.length; i++) {
								var row = table.insertRow(0);
								var firstNameCell = row.insertCell(0);
								var lastNameCell = row.insertCell(1);
								var selection = row.insertCell(2);
								firstNameCell.innerHTML = result[i].firstName;
								lastNameCell.innerHTML = result[i].lastName;
								selectedUser = result[i];
								var htmlString = "<button style='color:black' onclick='insertLog()'>Sign me in</button>";
								selection.innerHTML = htmlString;
							}
						}
					}
				});

		return false;
	}
</script>
