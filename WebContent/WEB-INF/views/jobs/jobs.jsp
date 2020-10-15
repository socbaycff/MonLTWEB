<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i&display=swap"
	rel="stylesheet">

<title>JOBS</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


<!-- Additional CSS Files -->
<link rel="stylesheet" href="assets/css/fontawesome.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/owl.css">
<link rel="icon" href="assets/icons/icon.png" type="image/png"
	sizes="32x32">
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
						<li class="nav-item active"><a class="nav-link"
							href="jobs.html">Jobs</a></li>
						<!-- phan nay dung the if core de hien thi chi khi login voi quyen company -->
						<li class="nav-item"><a class="nav-link" href="job-add.html">Add
								Job</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="login.html">Sign Out</a></li>
						<!--               <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">About</a>
                  
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="about.html">About Us</a>
                    <a class="dropdown-item" href="team.html">Team</a>
                    <a class="dropdown-item" href="blog.html">Blog</a>
                    <a class="dropdown-item" href="testimonials.html">Testimonials</a>
                    <a class="dropdown-item" href="terms.html">Terms</a>
                  </div>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="contact.html">Contact Us</a>
              </li> -->
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
							<h4>Jobs</h4>
							<h2>Choose the perfect job!</h2>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<!-- Banner Ends Here -->

	<section class="blog-posts grid-system">
		<div class="container">
			<div class="all-blog-posts">
				<div class="row">
					<div class="col-md-4 col-xs-12">
						<form action="jobs.html" method="post">
							<h4 style="margin-bottom: 15px">Type</h4>

							<div>
								<label> <input type="checkbox"> <span>Contract
										(5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Full
										time (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Internship
										(5)</span>
								</label>
							</div>

							<br>

							<h4 style="margin-bottom: 15px">Category</h4>

							<div>
								<label> <input type="checkbox"> <span>Accounting
										/ Finance / Insurance Jobs (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Accounting
										/ Finance / Insurance Jobs (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Accounting
										/ Finance / Insurance Jobs (5)</span>
								</label>
							</div>

							<br>

							<h4 style="margin-bottom: 15px">Career levels</h4>

							<div>
								<label> <input type="checkbox"> <span>Entry
										Level (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Entry
										Level (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Entry
										Level (5)</span>
								</label>
							</div>

							<br>

							<h4 style="margin-bottom: 15px">Education levels</h4>

							<div>
								<label> <input type="checkbox"> <span>Associate
										Degree (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Associate
										Degree (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>Associate
										Degree (5)</span>
								</label>
							</div>

							<br>


							<h4 style="margin-bottom: 15px">Years of experience</h4>

							<div>
								<label> <input type="checkbox"> <span>&lt;
										1 (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>&lt;
										1 (5)</span>
								</label>
							</div>

							<div>
								<label> <input type="checkbox"> <span>&lt;
										1 (5)</span>
								</label>
							</div>
							   
                              <button type="submit" id="form-submit" class="main-button">Fill</button>
						</form>
					</div>

					<div class="col-md-8 col-xs-12">
						<div class="row">
							<!-- item bat dau -->
							<c:forEach var="job" items="${jobs}">
							
							
							<div class="col-sm-6">
								<div class="blog-post">
									<div class="blog-thumb">
										<img src="assets/images/product-1-720x480.jpg" alt="">
									</div>
									<div class="down-content">
										<span>${job.luong} VND</span> <a href="job-details/${job.jobId}.html"><h4>${job.title}</h4></a>
										<p>${job.description}</p>
										<div class="post-options">
											<div class="row">
												<div class="col-lg-12">
													<ul class="post-tags">
														<li><i class="fa fa-bullseye"></i></li>
														<li><a href="job-details/${job.jobId}.html">View Job</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
							
							<!-- item ket thuc -->
						</div>
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