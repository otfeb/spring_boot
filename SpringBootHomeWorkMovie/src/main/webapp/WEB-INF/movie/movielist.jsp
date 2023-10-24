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
	.btn1{
		position: absolute;
		margin-bottom: 50px;
	}
	.box{
		float: left;
		width: 250px;
		height: 250px;
		padding-top: 15px;
		background-color: gray;
	}
</style>
</head>
<body>
<div style="margin-bottom: 100px;">
	<h2 class="alert alert-info">총 ${listSize }개의 영화정보가 있습니다</h2>
	<button type="button" class="btn btn-outline-success btn1" onclick="location.href='writeform'">영화등록</button>
</div>
	<div style="margin: 200px 200px;">
		<c:forEach var="dto" items="${list }" varStatus="i">
			<div class="box" align="center">
				<a href="detail?num=${dto.num }"><img alt="" src="../moviephoto/${dto.poster }" style="width: 200px; height: 200px;"></a><br>
				<b>${dto.title }</b>
			</div>
		</c:forEach>
	</div>
</body>
</html>