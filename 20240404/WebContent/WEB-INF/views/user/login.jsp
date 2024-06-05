<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>YBN</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/custom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<style>
	span.error{
		color:red; font-size:smaller; font-weight:bold;
	}
\
</style>

</head>
<body>

<!-- 상단 메뉴 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<c:if test="${failure == true }">
					<div class="alert alert-danger text-center">
						<h4>로그인 실패</h4>
						<p style="margin-bottom:0; font-size:18px;">아이디와 비밀번호를 확인해주세요</p>
					</div>
					</c:if>
					<form:form action="${root}user/login_proc" modelAttribute="tempLoginUserBean" method="post">
						<div class="form-group">
							<form:label path="user_id">아이디</form:label>
							<form:input path="user_id" class="form-control"/>
							<span class="error"><form:errors path="user_id"/></span>
						</div>
						<div class="form-group">
							<form:label path="user_pw">비밀번호</form:label>
							<form:password path="user_pw" class="form-control"/>
							<span class="error"><form:errors path="user_pw"/></span>
						</div>
						<div class="form-group text-right">
							<form:button class="btn btn-dark">로그인</form:button>
							<a href="${root}user/join" class="btn btn-danger">회원가입</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_menu.jsp"/>

</body>
</html>








