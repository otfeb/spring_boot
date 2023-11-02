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
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		
		list();
		
		$("#btnansweradd").click(function(){
			var num=$("#num").val();
			//alert(num);
			var content=$("#content").val();
			
			
			if(content.length==0){
				alert("댓글을 입력해 주세요")
				return;
			}
			
			$.ajax({
				type: "post",
				url: "/mbanswer/ainsert",
				dataType: "html",
				data: {"num":num,"content":content},
				success:function(){
					alert("저장완료");
					// 인서트 성공하면 list로 이동
					list();
				}
			});
		});
		
		//댓글 수정창 띄우기
		
		//댓글 수정
		
		//댓글 삭제
		
	});
	
	function list() {
		
		var num=$("#num").val();
		var loginok="${sessionScope.loginok}";
		var myid="${sessionScope.myid}";
		
		$.ajax({
			type: "get",
			dataType: "json",
			url: "/mbanswer/alist",
			data: {"num":num},
			success:function(res){
				//댓글개수 확인
				$("span.acount").text(res.length);
				
				var s="";
				$.each(res,function(i,dto){
					s+="<b>"+dto.name+"</b>: "+dto.content;
					if(loginok!=null && myid==dto.myid){
						s+="<a href='answerupdate'><i class='bi bi-pencil-square'></i><a/>"
						s+="<a href='answerdelete'><i class='bi bi-trash3'></i></a><br>"
					}
					s+="<span class='day' style='float:right;'><small style='color:gray;'>"
					+dto.writeday+"</small></span><br>";
					
				});
				$(".alist").html(s);
			}
		});
	}
</script>
</head>
<body>
	<div style="margin: 50px 150px;">
		<table class="table table-bordered" style="width: 600px;">
			<tr>
				<td>
					<h4>
						<b>${dto.subject }</b>
						<span style="font-size: 14pt; color: gray; float: right;">
							<fmt:formatDate value="${dto.writeday }" pattern="yyyy-MM-dd HH:mm"/>
						</span>
					</h4>
					<span>작성자: ${dto.name }(${dto.myid })</span>
					
					<c:if test="${dto.uploadfile!='no' }">
						<span style="float: right;"><a href="download?clip=${dto.uploadfile }">
						<i class="bi bi-cloud-download"></i><b>${dto.uploadfile }</b></a></span>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>
				<c:if test="${bupload }">
					<img src="../savefile/${dto.uploadfile }" style="width: 200px;">
				</c:if>
					<br><br>
					<pre>
						${dto.content }
					</pre>
					<br>
					<b>조회: ${dto.readcount }</b> &nbsp;&nbsp;&nbsp;
					<b>댓글: <span class="acount"></span></b>
				</td>
			</tr>
			
			<!-- 댓글 -->
			<tr>
				<td>
					<div class="alist"></div>
					
					<input type="hidden" id="num" value="${dto.num }">
					
					<c:if test="${sessionScope.loginok!=null }">
						<div class="aform">
							<div class="d-inline-flex">
								<input type="text" class="form-control" style="width: 500px;"
								placeholder="댓글을 입력하세요" id="content">
								<button type="button" class="btn btn-info"
								id="btnansweradd">등록</button>
							</div>
						</div>
					</c:if>
				</td>
			</tr>
			
			<!-- 버튼 추가 -->
			<tr>
				<td align="right">
				<c:if test="${sessionScope.loginok!=null }">
					<button type="button" class="btn btn-outline-info" onclick="location.href='form'">글쓰기</button>
				</c:if>
				<c:if test="${sessionScope.loginok!=null and sessionScope.myid==dto.myid}">
					<button type="button" class="btn btn-outline-info" 
					onclick="location.href='updateform?num=${dto.num}&currentPage=${currentPage }'">수정</button>
					<button type="button" class="btn btn-outline-info" 
					onclick="location.href='delete?num=${dto.num}&currentPage=${currentPage }'">삭제</button>
				</c:if>

					<button type="button" class="btn btn-outline-info" 
					onclick="location.href='list?currentPage=${currentPage}'">목록</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>