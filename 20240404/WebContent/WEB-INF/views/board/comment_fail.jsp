<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="root" value="${pageContext.request.contextPath}/" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YBN</title>
</head>
<body>
	<script>
	alert("댓글이 입력해주세요.");
	location.href="${root}board/read?board_info_idx=${board_info_idx}&content_idx=${content_idx}&page=${page}";
	
	</script>
</body>
</html>