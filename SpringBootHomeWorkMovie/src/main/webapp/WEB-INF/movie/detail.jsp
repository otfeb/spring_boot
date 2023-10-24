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
</head>
<body>
	<table class="table table-dark" style="width: 850px;">
		<tr align="center" valign="middle">
			<td rowspan="4">
				<img alt="" src="../moviephoto/${dto.poster }" style="width: 400px; height: 400px;">
			</td>
		</tr>
		
		<tr align="left" valign="middle">
			<td>제목: ${dto.title }</td>
		</tr>
	
		<tr align="left" valign="middle">
			<td>감독: ${dto.director }</td>
		</tr>
		
		<tr align="left" valign="middle">
			<td>개봉일: ${dto.opendate }</td>
		</tr>
		
		<tr>
			<td colspan="2" align="right">
				<button type="button" class="btn btn-outline-info">수정</button>
				<button type="button" class="btn btn-outline-info">삭제</button>
				<button type="button" class="btn btn-outline-info">목록</button>
				<button type="button" class="btn btn-outline-info">영화등록</button>
			</td>
		</tr>
	</table>
</body>
</html>