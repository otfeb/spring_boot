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
	.box{
		width: 40px;
		height: 40px;
		border: 1px solid gray;
		border-radius: 100px;
	}
</style>
</head>
<body>
	<div style="margin: 50px 100px;">
	<img alt="" src="../04.png">
      <button type="button" class="btn btn-info"
      onclick="location.href='carform'">자동차정보입력</button>
      <br><br>
      <h3 class="alert alert-info">
      	<b>총${totalCount }개의 자동차 정보가 있습니다</b>
      </h3>
      <table class="table table-bordered" style="width: 1000px;">
      	<tr align="center">
      		<th width="60">번호</th>
      		<th width="100">자동차명</th>
      		<th width="100">색상</th>
      		<th width="160">가격</th>
      		<th width="220">구입일</th>
      		<th width="220">등록일</th>
      		<th width="200">편집</th>
      	</tr>
      		<c:forEach var="dto" items="${list }" varStatus="i">
      			<tr valign="middle">
      				<td align="center">${i.count }</td>
      				<td align="center">${dto.carname }</td>
      				<td align="center"><div style="background-color: ${dto.carcolor}" class="box"></div></td>
      				<td align="center"><fmt:formatNumber value="${dto.carprice }" type="currency"/></td>
      				<td align="center">${dto.carguip }</td>
      				<td align="center"><fmt:formatDate value="${dto.guipday }" pattern="yyyy-MM-dd HH:mm"/></td>
      				<td align="center">
      					<button type="button" class="btn btn-warning btn-sm" 
      					onclick="location.href='updateform?num=${dto.num}'">수정</button>
      					<button type="button" class="btn btn-danger btn-sm" 
      					onclick="location.href='delete?num=${dto.num}'">삭제</button>
      				</td>
      			</tr>
      		</c:forEach>
      </table>
   </div>
</body>
</html>