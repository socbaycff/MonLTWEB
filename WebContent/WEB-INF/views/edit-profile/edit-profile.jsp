<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!--  This file has been downloaded from bootdey.com    @bootdey on twitter -->
<!--  All snippets are MIT license http://bootdey.com/license -->
<title>Edit profile page - Bootdey.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css" />
<style type="text/css">
body {
	margin-top: 20px;
	background: #f5f5f5;
}
/**
 * Panels
 */
/*** General styles ***/
.panel {
	box-shadow: none;
}

.panel-heading {
	border-bottom: 0;
}

.panel-title {
	font-size: 17px;
}

.panel-title>small {
	font-size: .75em;
	color: #999999;
}

.panel-body *:first-child {
	margin-top: 0;
}

.panel-footer {
	border-top: 0;
}

.panel-default>.panel-heading {
	color: #333333;
	background-color: transparent;
	border-color: rgba(0, 0, 0, 0.07);
}

form label {
	color: #999999;
	font-weight: 400;
}

.form-horizontal .form-group {
	margin-left: -15px;
	margin-right: -15px;
}

@media ( min-width : 768px) {
	.form-horizontal .control-label {
		text-align: right;
		margin-bottom: 0;
		padding-top: 7px;
	}
}

.profile__contact-info-icon {
	float: left;
	font-size: 18px;
	color: #999999;
}

.profile__contact-info-body {
	overflow: hidden;
	padding-left: 20px;
	color: #999999;
}

.profile-avatar {
	width: 200px;
	position: relative;
	margin: 0px auto;
	margin-top: 196px;
	border: 4px solid #f3f3f3;
}
</style>
<link rel="icon" href="assets/icons/icon.png" type="image/png" sizes="32x32">
</head>
<body>

	<link
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
		rel="stylesheet">
	<div class="container bootstrap snippets bootdeys">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<form action="edit.html" class="form-horizontal" method="POST">
					<div class="panel panel-default">
						<div class="panel-body text-center">
							<img src="https://bootdey.com/img/Content/avatar/avatar6.png"
								class="img-circle profile-avatar" alt="User avatar">
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">User info</h4>
						</div>
						<div class="panel-body">

							<div class="form-group">
								<label class="col-sm-2 control-label">Username</label>
								<div class="col-sm-10">
									<input value="${username}" name="username" type="text"
										class="form-control">
								</div>
							</div>
							<input value="${compId}" name="compId" type="text" style="display: none;">
							<c:if test="${compId!=0}">

								<div class="form-group">
									<label class="col-sm-2 control-label">Company name</label>
									<div class="col-sm-10">
										<input value="${comp_name}" name="comp_name" type="text"
											class="form-control">
									</div>
								</div>
							</c:if>


						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Contact info</h4>
						</div>
						<div class="panel-body">

							<div class="form-group">
								<label class="col-sm-2 control-label">E-mail address</label>
								<div class="col-sm-10">
									<input value="${email}" name="email" type="email"
										class="form-control">
								</div>
							</div>
							<c:if test="${compId!=0}">
								<div class="form-group">
									<label class="col-sm-2 control-label">Mobile number</label>
									<div class="col-sm-10">
										<input name="phone" value="${phone}" type="tel"
											class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">Description</label>
									<div class="col-sm-10">
										<textarea name="description" rows="3" class="form-control">${description}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">Location</label>
									<div class="col-sm-10">
										<textarea name="location" rows="3" class="form-control">${location}</textarea>
									</div>
								</div>


							</c:if>

						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Security</h4>
						</div>
						<div class="panel-body">

							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-2">
									<button type="submit" class="btn btn-primary">Submit</button>
									<button type="reset" class="btn btn-default">Cancel</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>