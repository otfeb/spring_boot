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
		$("div.likes").click(function(){
			var thum=$(this).find("i").attr("class");
			//alert(thum);
			if(thum=='bi bi-hand-thumbs-up-fill'){
				$(this).find("i").attr("class","bi bi-hand-thumbs-up-fill a").css("color","red");
			}
			else{
				$(this).find("i").attr("class","bi bi-hand-thumbs-up-fill").css("color","black");
			}
			
			var num=${dto.num};
			//alert(num);
			$.ajax({
				type:"get",
				url:"likes",
				dataType:"json",
				data:{"num":num},
				success:function(res){
					$("div.likes").find("b").text(res.likes);
				}
			});
		});
	});
</script>
</head>
<body>
	<div style="width: 600px; margin: 50px 100px;">
		<table class="table table-bordered">
			<tr>
				<td>
					<h2>${dto.subject }</h2>
					<b>${dto.name }(${dto.id })</b><br>
					<span style="color: gray; font-size: 0.8em;">
						<fmt:formatDate value="${dto.writeday }" pattern="yyyy-MM-dd HH:mm"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						조회:${dto.readcount }
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<pre>
						${dto.content }
					</pre><br><br>
					<c:if test="${dto.photo!='no' }">
						<c:forEach var="photo" items="${dto.photo }">
							<img alt="" src="../rephoto/${photo }" width="100" class="img-thumbnail">
						</c:forEach>
					</c:if>
					<br><br>
					<div class="likes">
						<i class="bi bi-hand-thumbs-up-fill"></i>&nbsp;&nbsp;
						좋아요<b>${dto.likes }</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<button type="button" class="btn btn-outline-success" onclick="location.href='form?
					num=${dto.num}&regroup=${dto.regroup }&relevel=${dto.relevel }&currentPage=${currentPage }'">답글</button>
					
					<c:if test="${sessionScope.loginok!=null && sessionScope.myid==dto.id}">
						<button type="button" class="btn btn-outline-success" 
						onclick="location.href=''">수정</button>
						<button type="button" class="btn btn-outline-success" 
						onclick="location.href='delete?num=${dto.num}&currentPage=${currentPage }'">삭제</button>
					</c:if>
					<button type="button" class="btn btn-outline-success" onclick="location.href='list?currentPage=${currentPage}'">목록</button>
					<c:if test="${sessionScope.loginok!=null }"> 
						<button type="button" class="btn btn-outline-success" 
						onclick="location.href='form'">글쓰기</button>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>