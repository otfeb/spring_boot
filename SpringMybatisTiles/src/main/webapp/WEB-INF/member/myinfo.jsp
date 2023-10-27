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
	#pho{
		width: 300px;
		height: 300px;	
	}
</style>
</head>
<body>
	<table class="table table-bordered" style="width: 800px;">
		<tr>
			<td rowspan="6" align="center">
				<img src="../upload/${dto.photo }" id="pho">
				<br>
				<button type="button" class="btn btn-outline-secondary">사진수정</button>
			</td>
			
			<td>이름: ${dto.name }</td>
		
			<td rowspan="6" align="center" valign="middle">
				<button type="button" class="btn btn-outline-secondary" onclick="location.href='updateform?num=${dto.num}'">수정</button>
				<button type="button" class="btn btn-outline-secondary" onclick="location.href='delete?num=${dto.num}'">삭제</button>
			</td>
		</tr>
		
		
		
		<tr>
			<td>아이디: ${dto.id }</td>
		</tr>
		
		<tr>
			<td>주소: ${dto.addr }</td>
		</tr>
		
		<tr>
			<td>이메일: ${dto.email }</td>
		</tr>
		
		<tr>
			<td>가입일: <fmt:formatDate value="${dto.gaipday }" pattern="yyyy-MM-dd HH:mm"/></td>
		</tr>
		
	</table>
</body>
</html>