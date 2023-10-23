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
	<form action="add" method="post" enctype="multipart/form-data">
		<table class="table table-bordered" style="width: 400px;">
			<tr align="center" valign="middle">
				<th>상품명</th>
				<td>
					<input type="text" name="sang" class="form-control" required="required" style="width: 200px;">
				</td>
			</tr>
			
			<tr align="center" valign="middle">
				<th>가격</th>
				<td>
					<input type="text" name="price" class="form-control" required="required" style="width: 200px;">
				</td>
			</tr>
			
			<tr align="center" valign="middle">
				<th>이미지</th>
				<td>
					<input type="file" name="upload" class="form-control" required="required" style="width: 200px;">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<button type="submit" class="btn btn-success">저장</button>
					<button type="button" class="btn btn-success" onclick="location.href='list'">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>