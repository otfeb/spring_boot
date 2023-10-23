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
	<c:if test="${totalCount==0 }">
		<div class="alert alert-info">
			<b>저장된 상품정보가 없습니다</b>
		</div>
	</c:if>
	
	<c:if test="${totalCount>0 }">
		<div class="alert alert-info">
			<b>총${totalCount }개의 상품정보가 있습니다</b>
		</div>
	</c:if>
	
	<div style="margin: 50px 100px;">
		<button type="button" class="btn btn-info" onclick="location.href='writeform'">글쓰기</button>
	</div>
	
	<table class="table table-bordered" style="width: 800px;"> 
			<tr align="center" valign="middle">
				<th width="80">번호</th>
				<th width="250">상품명</th>
				<th width="150">가격</th>
				<th width="200">입고일자</th>
				<th width="150">편집</th>
			</tr>
			<c:forEach var="dto" items="${list }" varStatus="i">
				<tr align="center" valign="middle">
					<td>${i.count }</td>
					<td>
						<img alt="" src="../save/${dto.photoname }">
						${dto.sang }
					</td>
					<td>${dto.price }</td>
					<td><fmt:formatDate value="${dto.ipgoday }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>
						<input type="button" class="btn btn-info" onclick="location.href=''" value="수정">
						<input type="button" class="btn btn-info" onclick="location.href=''" value="삭제">
					</td>
				</tr>
			</c:forEach>
		</table>
	
</body>
</html>