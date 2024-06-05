<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>YBN</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body class="index">

	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<!-- 게시판 미리보기 부분 -->
	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-lg-3" style="margin-top: 20px">
				<div class="card">
					<div class="card-body">
						<nav>
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="${root}board/main?board_info_idx=1">
										<img src="img/topic.png"> <span>topic</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<div class="col-lg-3" style="margin-top: 20px">
				<div class="card">
					<div class="card-body">
						<nav>
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="${root}board/main?board_info_idx=2">
										<img src="img/toeic_toefl.png" alt="TOEIC/TOEFL"> <span>toeic
											/ toefl</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>

			<div class="col-lg-3" style="margin-top: 20px">
				<div class="card">
					<div class="card-body">
						<nav>
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="${root}board/main?board_info_idx=3">
										<img src="img/jlpt.png"> <span>jlpt</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<div class="col-lg-3" style="margin-top: 20px">
				<div class="card">
					<div class="card-body">
						<nav>
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="${root}board/main?board_info_idx=4">
										<img src="img/hsk.png"> <span>hsk</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<c:import url="/WEB-INF/views/include/bottom_menu.jsp" />
</body>
</html>









