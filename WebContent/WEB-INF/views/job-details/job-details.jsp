<%@ page pageEncoding="utf-8"%>
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

<title>JOB Detail</title>

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
						<li class="nav-item"><a class="nav-link" href="job-add.html">Add
								Job</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="login.html">Sign Out</a></li>
						<!--             <li class="nav-item dropdown">
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
							<h4>${job.luong}VND</h4>
							<h2>${job.title}</h2>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<!-- Banner Ends Here -->

	<section class="blog-posts grid-system">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div>
						<img src="assets/images/product-1-720x480.jpg" alt=""
							class="img-fluid wc-image">
					</div>

					<br>
				</div>

				<div class="col-md-8">
					<div class="sidebar-item recent-posts">
						<div class="sidebar-heading">
							<h2>
								<i class="fa fa-map-marker"></i> London &nbsp;&nbsp; <i
									class="fa fa-calendar"></i> 20-06-2020 &nbsp;&nbsp; <i
									class="fa fa-file"></i> Contract
							</h2>
						</div>

						<div class="content">
							<p>${job.description}</p>
						</div>
					</div>

					<br>

					<div class="row">
						<div class="col-sm-4">
							<div class="main-button">
								<a href="update/${job.jobId}.html">Edit Job</a>
							</div>
						</div>
					</div>

					<br>
				</div>
			</div>
		</div>
	</section>

	<div class="section contact-us">
		<div class="container">
			<div class="sidebar-item recent-posts">
				<div class="sidebar-heading">
					<h2>Description</h2>
				</div>

				<div class="content">
					<p>Thể loại: ${job.category}</p>

					<br>
					<p>Mô tả: ${job.description}</p>

					<br>

					<p>Số năm kinh nghiệm cần: ${job.expYear}</p>

					<br>

					<p>Trình độ học vấn yêu cầu: ${job.educationLV}</p>
				</div>

				<br> <br>
			</div>

			<div class="row">
				<div class="col-lg-8">
					<div class="sidebar-item recent-posts">
						<div class="sidebar-heading">
							<h2>About Cannon Guards Security ltd</h2>
						</div>

						<div class="content">
							<p class="lead">
								<i class="fa fa-map-marker"></i> London
							</p>

							<br>

							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Suscipit rerum obcaecati aspernatur qui molestias voluptatibus
								odit placeat. Ipsa, nisi, quod?</p>

							<br>

							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Molestias a, minus fugiat corporis, dolorem laborum nemo. Fugiat
								dolores, asperiores deserunt ex porro provident similique eius
								repellat error modi dignissimos doloribus.</p>

							<br>

							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Corporis incidunt, quibusibus molestiae! Porro dicta fugit
								magnam quod atque soluta voluptate voluptatum, mollitia
								praesentium provident. Quibusdam quisquam minima, accusantium
								nulla, deserunt recusandae doloribus quam illum, ex eaque ipsam!</p>

							<br>
						</div>
					</div>
				</div>

				<div class="col-lg-4">
					<div class="sidebar-item recent-posts">
						<div class="sidebar-heading">
							<h2>Contact Details</h2>
						</div>

						<div class="content">
							<p>
								<span>Name</span> <br> <strong>John Smith</strong>
							</p>

							<p>
								<span>Phone</span> <br> <strong> <a
									href="tel:123-456-789">123-456-789</a>
								</strong>
							</p>

							<p>
								<span>Mobile phone</span> <br> <strong> <a
									href="tel:456789123">456789123</a>
								</strong>
							</p>

							<p>
								<span>Email</span> <br> <strong> <a
									href="mailto:john@carsales.com">john@carsales.com</a>
								</strong>
							</p>

							<p>
								<span>Website</span> <br> <strong> <a
									href="http://www.cannonguards.com/">http://www.cannonguards.com/</a>
								</strong>
							</p>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>

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
							Copyright Â© 2020 Company Name | Template by: <a
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