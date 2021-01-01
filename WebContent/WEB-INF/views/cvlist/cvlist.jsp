<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<title>Danh sách CV ứng tuyển</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


<!-- Additional CSS Files -->
<link rel="stylesheet" href="assets/css/fontawesome.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/owl.css">
<link rel="icon" href="assets/icons/icon.png" type="image/png"
	sizes="32x32">

</head>
<style>
img {
    display: block;
    max-width:350px;
    max-height:320px;
    width: auto;
    height: auto;
}


</style>
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
						<c:if test="${role == 'Company'}" >
							<li class="nav-item"><a class="nav-link" href="job-add.html">Add Job</a></li>		
						</c:if>
						<li class="nav-item active">${username}</li>
						<li class="nav-item active"><a class="nav-link"
							href="signout.html">Sign Out</a></li>
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
							<h4>Danh sách CV Job</h4>
							<h2>Danh sách CV các ứng viên!</h2>
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
					<%-- <div class="col-md-4 col-xs-12">
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
						 	<c:forEach var="cat" items="${cats}">
								<div>
									<label> <input type="checkbox" name="cats" value="${cat.name}" > <span>${cat.name} (${cat.count})</span>
									</label>
								</div>
							</c:forEach> 
						
							<br>


							<h4 style="margin-bottom: 15px">Education levels</h4>
						<c:forEach var="edulv" items="${edulvs}">
								<div>
									<label> <input type="checkbox" name="edulvs" value="${edulv.name}"> <span>${edulv.name} (${edulv.count})</span>
									</label>
								</div>
							</c:forEach> 
							

							<br>


							<h4 style="margin-bottom: 15px">Years of experience</h4>
							<c:forEach var="exp" items="${expYear}">
								<div>
									<label> <input type="checkbox" name="expYear" value="${exp.name}"> <span>&lt;
											${exp.name} (${exp.count})</span>
									</label>
								</div>
							</c:forEach> 
							

							<button type="submit" id="form-submit" class="main-button">Fill</button>
						</form>
					</div> --%>

					<div class="col-md-8 col-xs-12">
						<div class="row">
							<!-- item bat dau -->
 							<c:forEach var="cv" items="${cvs}">


								<div class="col-sm-6">
									<div class="blog-post">
										<%-- <div class="blog-thumb">
											<img width="800" height="600" src="uploads/${cv.jobId}" alt="">
										</div> --%>
										<div class="down-content">
											<span>Email: ${cv[1]}</span> <a
												href="cv-viewer/${cv[3] }/${cv[0]}.html"><h4>Ngày đăng: ${cv[2]}</h4></a>
										<%-- 	<p>${job.description}</p> --%>
											<div class="post-options">
												<div class="row">
													<div class="col-lg-12">
														<ul class="post-tags">
															<li><i class="fa fa-bullseye"></i></li>
															<li><a href="cvs/${cv[3] }/${cv[0]}" download= "${cv[1]}_cv.pdf">Tải xuống</a></li>
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