<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
   href="https://fonts.googleapis.com/css2?family=Dongle&family=Gaegu:wght@700&family=Nanum+Pen+Script&family=Single+Day&display=swap"
   rel="stylesheet">
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
   rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<style type="text/css">
	.img{
		width: 150px;
		height: 150px;
	}
</style>
</head>
<body>
	<h2 class="alert alert-info">구입 자동차 정보</h2>
	<h2>${dto.carname }</h2>
	<img alt="" src="../save/${dto.carphoto }">
	<h2>단가: <fmt:formatNumber value="${dto.carprice }" type="currency"/></h2>
	<b>색상: </b><div style="background-color: ${dto.carcolor}" class="img"></div>
	<h2><fmt:formatDate value="${dto.guipday }" pattern="yyyy-MM-dd HH:mm"/></h2>
	<button type="button" class="btn btn-warning" onclick="location.href=''">수정</button>
	<button type="button" class="btn btn-warning" onclick="location.href=''">삭제</button>
	<button type="button" class="btn btn-warning" onclick="location.href='carform'">등록</button>
	<button type="button" class="btn btn-warning" onclick="location.href='carlist'">목록</button>
</body>
</html>