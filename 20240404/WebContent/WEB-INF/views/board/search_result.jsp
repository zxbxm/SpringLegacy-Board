<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>학원</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/custom.css">
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body class="board_main">

		<!-- 상단 메뉴 부분 -->
		<c:import url="/WEB-INF/views/include/top_menu.jsp" />


		<div class="container" style="margin-top: 100px;">
				<div class="card shadow">
						<div class="card-body">
						<h4 class="card-title">검색 결과</h4>
								<c:if test="${not empty searchResult}">
										<table class="table table-hover" id="board_list">
												<thead>
														<tr>
																<!-- 테이블 헤더 -->
																<th class="text-center d-none d-md-table-cell">글번호</th>
																<th class="w-50 text-dark">제목</th>
																<th class="text-center d-none d-md-table-cell">작성자</th>
																<th class="text-center d-none d-md-table-cell">작성날짜</th>
																<!-- 추가 열이 있다면 여기에 추가 -->
														</tr>
												</thead>
												<tbody>
														<!-- 검색 결과 반복문 -->
														<c:forEach items="${searchResult}" var="content">
																<tr>

																		<!-- 각 열의 데이터 -->
																		<td class="text-center d-none d-md-table-cell">${content.content_idx}</td>
																		<!-- 게시글 제목을 클릭하여 해당 게시글로 이동하는 링크 -->
																		<td><a href="${root}board/read?board_info_idx=${content.content_board_idx}&content_idx=${content.content_idx}&page=${page}"  
																		class='text-dark font-weight-bold'>${content.content_subject}</a></td>
																		<td class="text-center d-none d-md-table-cell">${content.content_writer_name}</td>
																		<td class="text-center d-none d-md-table-cell">${content.content_date}</td>
																		<!-- 추가 열의 데이터가 있다면 여기에 추가 -->
																</tr>
														</c:forEach>
												</tbody>
										</table>
								</c:if>
						</div>
				</div>
		</div>

		<!-- 검색 결과가 없는 경우 -->
		<c:if test="${empty searchResult}">
				<script type="text/javascript">
					alert("결과가 없습니다.");
					window.location.href = "${root}board/main?board_info_idx=${boardInfoIdx}"; // 결과가 없을 때 board/main으로 이동
				</script>
		</c:if>
		<!-- 하단 메뉴 부분 -->
		<c:import url="/WEB-INF/views/include/bottom_menu.jsp" />
</body>
</html>
