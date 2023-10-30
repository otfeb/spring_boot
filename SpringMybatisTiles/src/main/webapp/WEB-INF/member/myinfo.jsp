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
<script type="text/javascript">
	$(function(){
		$(".btnnewphoto").click(function(){
			$("#newphoto").trigger("click");
		});
		
		$("#newphoto").change(function(){
			var num=$(this).attr("num");
			//console.log(num);
			
			var form=new FormData();
			form.append("photo",$(this)[0].files[0]);	//선택한 한개만 추가
			form.append("num",num);
			
			//console.dir(form);
			
			$.ajax({
				type: "post",
				dataType: "html",
				url: "updatephoto",
				processData: false,
				contentType: false,
				data: form,
				success:function(){
					location.reload();
				}
			});
		});
	});
</script>
<style type="text/css">
	#pho{
		width: 300px;
		height: 300px;	
	}
</style>
</head>
<body>
	<table class="table table-bordered" style="width: 800px;">

		<c:if test="${sessionScope.loginok!=null and sessionScope.myid==dto.id }">
		<tr>
			<td rowspan="6" align="center">
				<img src="../membersave/${dto.photo }" id="pho">
				<br>
				<input type="file" style="display: none;" num=${dto.num } id="newphoto">
				<button type="button" class="btn btn-outline-secondary btnnewphoto">사진수정</button>
			</td>
			
			<td>이름: ${dto.name }</td>
		
			<td rowspan="6" align="center" valign="middle">
				<button type="button" class="btn btn-outline-secondary" 
				onclick="location.href='updateform?num=${dto.num}'">수정</button>
				<button type="button" class="btn btn-outline-secondary" 
				onclick="location.href='deletemyinfo?num=${dto.num}'">삭제</button>
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
		</c:if>
	</table>
</body>
</html>