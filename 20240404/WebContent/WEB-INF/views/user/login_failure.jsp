<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YBN</title>
</head>
<body>
	<script>
		alert("로그인에 실패했습니다.");
		location.href="${root}user/login?failure=true";
	</script>
</body>
</html>