<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>



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
.btn.btn-gray {
	background-color: #555;
	color: #fff;
}
/* 좋아요 버튼 스타일 */
    #likeButton {
        background-color: #333; /* 연분홍색 */
        color: white; /* 텍스트 색상 */
        border: none; /* 테두리 없음 */
        border-radius: 20px; /* 둥글게 */
        padding: 10px 20px; /* 내부 여백 */
        cursor: pointer; /* 마우스 커서 모양 변경 */
        transition: background-color 0.3s ease; /* 배경색 변화에 애니메이션 효과 적용 */
        text-decoration: none; /* 링크 효과 제거 */
    }

    /* 버튼에 마우스를 올렸을 때의 스타일 */
    #likeButton:hover::after {
      content: '❤️좋아요 '; /* 호버 시 텍스트 변경 */
    }

    /* 버튼에 마우스를 올렸을 때의 스타일 */
    #likeButton:hover span {
        display: none; /* 호버 시 기존 텍스트 숨기기 */
    }



</style>

</head>
<body>

<!-- 상단 메뉴 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp" />

<div class="container" style="margin-top: 100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<div class="form-group">
						<label for="board_writer_name">작성자</label>
						<input type="text" id="board_writer_name" name="board_writer_name" class="form-control"
							value="${readContentBean.content_writer_name }" disabled="disabled" />
					</div>
					<div class="form-group">
						<label for="board_date">작성날짜</label>
						<input type="text" id="board_date" name="board_date" class="form-control"
							value="${readContentBean.content_date }" disabled="disabled" />
					</div>
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" class="form-control"
							value="${readContentBean.content_subject }" disabled="disabled" />
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content" class="form-control" rows="10"
							style="resize: none" disabled="disabled">${readContentBean.content_text }</textarea>
					</div>
					<c:if test="${readContentBean.content_file != null }">
						<div class="form-group">
							<label for="board_file">첨부 이미지</label>
							<img src="${root }upload/${readContentBean.content_file}" width="100%" />
						</div>
					</c:if>
					
					
						<div class="form-group">
							<div class="text-right">
								<!-- 좋아요 버튼 -->
								<a href="${root}board/like?board_info_idx=${board_info_idx}&content_idx=${content_idx}&page=${page}" class="btn" id="likeButton" >
								 <span>🤍좋아요 개수 : ${likeCount }</span></a>
							</div>
						</div>
					
					
					<div class="form-group">
						<div class="text-right">
							<a href="${root }board/main?board_info_idx=${board_info_idx}&page=${page}"
								class="btn btn-gray">목록보기</a>
							<c:if test="${loginUserBean.user_idx == readContentBean.content_writer_idx }">
								<a href="${root }board/modify?board_info_idx=${board_info_idx}&content_idx=${content_idx}&page=${page}"
									class="btn btn-dark">수정하기</a>
								<a href="${root }board/delete?board_info_idx=${board_info_idx}&content_idx=${content_idx}"
									class="btn btn-danger">삭제하기</a>
							</c:if>
						</div>
					</div>

					<form:form action="${root }board/comment_proc" modelAttribute="writeCommentBean" method="post">
						<input type="hidden" name="content_idx" value="${content_idx}">
						<input type="hidden" name="board_info_idx" value="${board_info_idx}">
						<input type="hidden" name="page" value="${page}">
						<div>
							<label for="comment_text">댓글</label>
							<div class="d-flex">
								<form:textarea path="comment_text" class="form-control flex-grow-1 mr-2"
									style="font-size: smaller;"></form:textarea>
								<form:button  class="btn btn-dark square-btn mr-1">작성</form:button>
								<span class="error"><form:errors path="comment_text" /></span>
							</div>
						</div>
					</form:form>

		 <br>
						<label for="content_comment">댓글목록</label>
						<div class="commentList">
    <c:forEach var="comment" items="${comments}">
        <div class="mb-3" style="border: 1px solid lightgray; padding: 10px; position: relative;">
            <p style="font-size: 12px;">ID: ${comment.comment_writer_id}</p>
            <p style="font-size: 12px;">Date: ${comment.comment_date}</p>
            <p style="font-size: 15px; margin-bottom: 5px; max-width: 300px; ">${comment.comment_text}</p> 
            
            <c:if test="${loginUserBean.userLogin == true && loginUserBean.user_id == comment.comment_writer_id}">
                <form:form action="${root}board/comment_delete" method="get" style="position: absolute; bottom: 5px; right: 5px;">
                    <input type="hidden" name="board_info_idx" value="${board_info_idx}">
                    <input type="hidden" name="content_idx" value="${content_idx}">
                    <input type="hidden" name="comment_idx" value="${comment.comment_idx}">
                    <input type="hidden" name="page" value="${page}">
                    <button type="submit" class="btn btn-none square-btn" style="font-size: 12px;">댓글 삭제</button>
                </form:form>
            </c:if>
        </div>
        <br>
    </c:forEach>
</div>



				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_menu.jsp" />

</body>
</html>
