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
	<h4 class="alert alert-info" style="width: 1200px;">&nbsp;${totalCount }명의 회원이 존재합니다</h4>
	<button type="button" class="btn btn-outline-info" onclick="location.href='add'">Registarion</button>
	<br><br>
	<table class="table table-striped" style="width: 1200px;">
		<tr align="center">
			<th width="80">번호</th>
			<th width="150">이름</th>
			<th width="150">아이디</th>
			<th width="250">프로필사진</th>
			<th width="250">이메일</th>
			<th width="250">가입일</th>
		</tr>
		<c:forEach var="dto" items="${list }" varStatus="i">
			<tr align="center" valign="middle" class="content">
				<td>${i.count }</td>
				<td>${dto.name }</td>
				<td>${dto.id }</td>
				<td>
					<a href="myinfo?id=${dto.id }"><img alt="" src="../upload/${dto.photo }" style="width: 100px; height: 100px;"></a>
				</td>
				<td>${dto.email }</td>
				<td>
					<fmt:formatDate value="${dto.gaipday }" pattern="yyyy-MM-dd HH:mm"/> 
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>