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
<link rel="stylesheet" type="text/css" href="../css/custom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
.page-item.active.page-link {
	background-color: gray !important;
	border-color: gray !important;
}

.page-item.active a.page-link {
	color: white !important;
}
</style>
</head>
<body class="board_main">

<!-- 스크립트 부분 -->
<script type="text/javascript">
$(document).ready(function(){
    $('#keyword').on('keypress', function(e) {
        if(e.which === 13) {
            e.preventDefault(); // 폼 제출을 방지합니다.
            $('#searchBtn').click(); // 검색 버튼의 클릭 이벤트를 강제로 실행합니다.
        }
    });

    $('#searchBtn').on('click', function() {
        var keyword = $('#keyword').val().trim(); // 입력된 검색어를 가져옵니다.
        window.location.href = '${root}board/search?board_info_idx=${board_info_idx}&page=1&keyword=' + encodeURIComponent(keyword); // 검색어와 함께 URL을 변경합니다.
    });
});


</script>


	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<!-- 게시글 리스트 -->
	<div class="container" style="margin-top: 100px">
		<div class="card shadow">
			<div class="card-body">
				<h4 class="card-title">${boardName }</h4>


				<div class="row mb-3">
					<div class="col-sm-9">
						<input type="text" id="keyword" class="form-control"
							placeholder="검색어를 입력하세요">
					</div>
					<div class="col-sm-3">
						<button id="searchBtn" class="btn btn-dark btn-block">검색</button>
					</div>
				</div>

				<table class="table table-hover" id='board_list'>
					<thead>
						<tr>
							<th class="text-center d-none d-md-table-cell">글번호</th>
							<th class="w-50 text-dark">제목</th>
							<th class="text-center d-none d-md-table-cell">작성자</th>
							<th class="text-center d-none d-md-table-cell">작성날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="content" items="${contentList }">
							<tr>
								<td class="text-center d-none d-md-table-cell">${content.content_idx }</td>
								<td><a
									href="${root }board/read?board_info_idx=${board_info_idx}&content_idx=${content.content_idx }&page=${page}"
									class='text-dark font-weight-bold'>${content.content_subject }</a></td>
								<td class="text-center d-none d-md-table-cell">${content.content_writer_name }</td>
								<td class="text-center d-none d-md-table-cell">${content.content_date }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- 페이지네이션 -->
				<div class="d-none d-md-block">
					<ul class="pagination justify-content-center">
						<!-- 페이지 번호 -->
						<c:choose>
							<c:when test="${pageBean.prevPage <= 0 }">
								<li class="page-item disabled"><a href="#"
									class="page-link text-dark">이전</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a
									href="${root}board/main?board_info_idx=${board_info_idx}&page=1"
									class="page-link text-dark">이전</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach var="idx" begin="${pageBean.min }"
							end="${pageBean.max }">
							<c:choose>
								<c:when test="${idx == pageBean.currentPage }">
									<li class="page-item active"><a
										href="${root }board/main?board_info_idx=${board_info_idx }&page=${idx}"
										class="page-link text-dark"
										style="background-color: gray; color: white; border-color: gray;">${idx}</a>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pageBean.max >= pageBean.pageCount }">
								<li class="page-item disabled"><a href="#"
									class="page-link text-dark">다음</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a
									href="${root }board/main?board_info_idx=${board_info_idx }&page=${pageBean.nextPage}"
									class="page-link text-dark">다음</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<div class="d-block d-md-none">
					<ul class="pagination justify-content-center">
						<li class="page-item"><a href="#" class="page-link text-dark">이전</a>
						</li>
						<li class="page-item"><a href="#" class="page-link text-dark">다음</a>
						</li>
					</ul>
				</div>
				<!-- 글쓰기 버튼 -->
				<div class="text-right">
					<a
						href="${root }board/write?board_info_idx=${board_info_idx}&page=${page}"
						class="btn btn-dark">글쓰기</a>
				</div>

			</div>
		</div>
	</div>


	<!-- 하단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_menu.jsp" />

</body>
</html>
