<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Sign Up</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/JFCore.js"></script>

<!-- Set here the key for your domain in order to hide the watermark on the web server -->
<script type="text/javascript">
	(function() {
		JC.init({
			domainKey : ''
		});
	})();
</script>
<link rel="icon" href="assets/icons/icon.png" type="image/png"
	sizes="32x32">
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
									<input type="text" class="active textbox" name="username"
										value="User Name" onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'User Name';}">
									<input type="text" class="textbox" name="email"
										value="Email Address" onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'Email Address';}">
									<input type="password" class="textbox" name="pass"
										value="Password" onfocus="this.value = '';"
										onblur="if (this.value == '') {this.value = 'Password';}">
									<div>
										<label> <input type="checkbox" name="isComp"> <span>Company Account
												(5)</span>
										</label>
									</div>
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
				By clicking Sign Up, you agree to our <a href="#single.html">Terms</a>,<a
					href="#single.html">Data Policy</a> and <a href="#single.html">Cookie
					Policy</a>
			</p>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>