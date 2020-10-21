<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i&display=swap"
	rel="stylesheet">
<link rel="icon" href="assets/icons/icon.png" type="image/png"
	sizes="32x32">
<title>Edit Job</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


<!-- Additional CSS Files -->
<link rel="stylesheet" href="assets/css/fontawesome.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/owl.css">
</head>

<body>

	<!-- ***** Preloader Start ***** -->
	<div id="preloader">
		<div class="jumper">
			<div></div>
			<div></div>
			<div></div>
		</div>
	</div>
	<!-- ***** Preloader End ***** -->

	<!-- Header -->
	<header class="">
		<nav class="navbar navbar-expand-lg">
			<div class="container">
				<a class="navbar-brand" href="index.html"><h2>
						Job Agency Website<em>.</em>
					</h2></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="index.html">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="jobs.html">Jobs</a>
						</li>

						<!--  <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">About</a>
                  
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="about.html">About Us</a>
                    <a class="dropdown-item" href="team.html">Team</a>
                    <a class="dropdown-item" href="blog.html">Blog</a>
                    <a class="dropdown-item" href="testimonials.html">Testimonials</a>
                    <a class="dropdown-item" href="terms.html">Terms</a>
                  </div>
              </li> -->
						<li class="nav-item active"><a class="nav-link"
							href="job-add.html">Add Job</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="login.html">Sign Out</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<!-- Page Content -->
	<!-- Banner Starts Here -->
	<div class="heading-page header-text">
		<section class="page-heading">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="text-content">
							<h4>Edit Job</h4>
							<h2>Edit Job's Infomation</h2>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<!-- Banner Ends Here -->


	<section class="contact-us">
		<div class="container">
			<div class="row">

				<div class="col-lg-12">
					<div class="down-contact">
						<div class="row">
							<div class="col-lg-8">
								<div class="sidebar-item contact-form">
									<div class="sidebar-heading">
										<h2>Job Info</h2>
									</div>
									<div class="content">
										<form id="contact" action="update/${job.jobId}.html"
											method="post">
											<div class="row">
												<div class="col-md-6 col-sm-12">
													<fieldset>
														<input name="Title" type="text" id="name"
															value="${job.title}" required="">
													</fieldset>
												</div>
												<div class="col-md-6 col-sm-12">
													<fieldset>
														<input name="email" type="text" id="email"
															placeholder="Contact Email" required="">
													</fieldset>
												</div>
												<div class="col-md-12 col-sm-12">
													<fieldset>
														<input name="subject" type="text" id="subject"
															placeholder="Location">
													</fieldset>
												</div>
												<div class="col-lg-12">
													<fieldset>
														<textarea name="Description" rows="6" id="message"
															 required="">${job.description}</textarea>
													</fieldset>
												</div>
												<div class="col-md-6 col-sm-12">
													<fieldset>
														<input name="Luong" rows="6" id="salary"
															value="${job.luong}" required="">
														</textarea>
													</fieldset>
												</div>
												<div class="col-md-6 col-sm-12">
													<fieldset>
														<input name="Category" rows="6" id="category"
															value="${job.category}" required="">
														</textarea>
													</fieldset>
												</div>
												<div class="col-md-6 col-sm-12">
													<fieldset>
														<input name="EducationLV" rows="6" id=""
															edulv"" value="${job.educationLV}" required="">
														</textarea>
													</fieldset>
												</div>
												<div class="col-md-6 col-sm-12">
													<fieldset>
														<input name="ExpYear" rows="6" id="expyear" value="${job.expYear}"
															placeholder="Exprerience Years" required="">
														</textarea>
													</fieldset>
												</div>
												<div class="col-lg-12">
													<fieldset>
														<button type="submit" id="form-submit" class="main-button">Update
															Job</button>
													</fieldset>
												</div>
											</div>
										</form>

									</div>
									<div class="col-lg-12">
										<fieldset>
											<button type="submit" onclick="location.href='delete/${id}.html'" id="form-submit" class="main-button">Delete
												Job</button>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="sidebar-item contact-information">
									<div class="sidebar-heading">
										<h2>contact information</h2>
									</div>
									<div class="content">
										<ul>
											<li>
												<h5>+1 333 4040 5566</h5> <span>PHONE NUMBER</span>
											</li>
											<li>
												<h5>contact@company.com</h5> <span>EMAIL ADDRESS</span>
											</li>
											<li>
												<h5>212 Barrington Court New York</h5> <span>STREET
													ADDRESS</span>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-12">
					<div id="map">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.5215504034413!2d106.78394331539725!3d10.847879492273055!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752772b245dff1%3A0xb838977f3d419d!2zSOG7jWMgdmnhu4duIEPDtG5nIG5naOG7hyBCxrB1IGNow61uaCBWaeG7hW4gdGjDtG5nIEPGoSBT4bufIFThuqFpIFRQLiBI4buTIENow60gTWluaMK3!5e0!3m2!1svi!2s!4v1602582373139!5m2!1svi!2s"
							width="600" height="450" frameborder="0" style="border: 0;"
							allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
					</div>
				</div>

			</div>
		</div>
	</section>


	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="social-icons">
						<li><a href="#">Facebook</a></li>
						<li><a href="#">Twitter</a></li>
						<li><a href="#">Behance</a></li>
						<li><a href="#">Linkedin</a></li>
					</ul>
				</div>
				<div class="col-lg-12">
					<div class="copyright-text">
						<p>
							Copyright © 2020 Company Name | Template by: <a
								href="https://www.phpjabbers.com/">PHPJabbers.com</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Additional Scripts -->
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/owl.js"></script>
	<script src="assets/js/slick.js"></script>
	<script src="assets/js/isotope.js"></script>
	<script src="assets/js/accordions.js"></script>

	<script language="text/Javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>

</body>
</html>