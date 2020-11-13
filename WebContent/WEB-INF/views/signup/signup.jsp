<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>

<head>
	<title>Sign Up</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/JFCore.js"></script>

	<!-- Set here the key for your domain in order to hide the watermark on the web server -->
	<script type="text/javascript">
		(function () {
			JC.init({
				domainKey: ''
			});
		})();
	</script>
	<link rel="icon" href="assets/icons/icon.png" type="image/png" sizes="32x32">
</head>

<body>
	<div class="wrap">
		<!-- tab style-1 -->
		<div class="row">
			<div class="grid_12 columns">
				<div class="tab style-1">
					<dl>
						<dd class="users">
							<a class="user active" href="#tab1"> </a>
						</dd>
					</dl>
					<ul>
						<li class="active">
							<div class="form">
								<form action="signup.html" method="post">
									<input required type="text" class="active textbox" name="username"
										placeholder="User Name" onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'User Name';}">
									<input required type="text" class="textbox" name="email" placeholder="Email Address"
										onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'Email Address';}">
									<input required type="password" class="textbox" name="pass" placeholder="Password"
										onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'Password';}">
									<div>
										<label> <input id="companyCheck" onclick="checkEvent()" type="checkbox"
												name="isComp"> <span>Company Account</span>
										</label>
									</div>
									<input id="loca" hidden="true" type="text" class="active textbox" name="location"
										placeholder="Location""
										onfocus=" this.value='' ;" onblur="if (this.value == '') {this.value = 'User Name';}" style="display:none;">
									<input id="desc" hidden="true" type="text" class="active textbox" name="description"
										placeholder="Description" onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'User Name';}"
										style="display:none;">

									<input type="submit" value="Register">
								</form>
							</div>
						</li>

					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="wrap">
		<!--footer-->
		<div class="footer">
			<p>
				By clicking Sign Up, you agree to our <a href="#single.html">Terms</a>,<a href="#single.html">Data
					Policy</a> and <a href="#single.html">Cookie
					Policy</a>
			</p>
		</div>
		<div class="clear"></div>
	</div>
</body>
<script>

	function checkEvent() {
		if (document.getElementById('companyCheck').checked) {
			console.log('checked')
			document.getElementById('loca').style.display = "block"
			document.getElementById('loca').required = true
			document.getElementById('desc').style.display = "block"
			document.getElementById('desc').required = true
		} else {
			document.getElementById('loca').style.display = "none"
			document.getElementById('loca').required = false
			document.getElementById('desc').style.display = "none"
			document.getElementById('desc').required = false
		}
	}

</script>

</html>