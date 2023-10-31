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
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
				processData: false,		//formData 사용할때 쓰는 2가지
				contentType: false,		//formData:사진수정 ajax로 할때 사용
				data: form,
				success:function(){
					location.reload();
				}
			});
		});
		
		$("#updatesave").click(function(){
			var num=$("#dtonum").val();
			var name=$("#updatename").val();
			var email=$("#updateemail").val();
			var addr=$("#updateaddr").val();
			//alert(num);
			//alert(email+","+addr);
			
			$.ajax({
				type: "post",
				url: "/member/update",
				dataType: "html",
				data: {"num":num,"name":name,"email":email,"addr":addr},
				success:function(res){
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
				<button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editModal">
    				수정
  				</button>
				<button type="button" class="btn btn-outline-primary" 
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
	
<!-- The Modal -->
<div class="modal" id="editModal">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">정보수정</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" align="center">
        	<input type="hidden" name="num" value="${dto.num }" id="dtonum">
			<table class="table table-bordered" style="width: 250px;">
				<tr>
					<td>이름
						<input type="text" name="name" class="form-control" 
						placeholder="이름" required="required" value="${dto.name }" id="updatename">
					</td>
				</tr>
				<tr>
					<td>이메일
						<input type="text" name="email" class="form-control" 
						placeholder="이메일" required="required" value="${dto.email }" id="updateemail">
					</td>
				</tr>
				
				<tr>
					<td>주소
						<input type="text" name="addr" class="form-control" 
						placeholder="주소입력" required="required" value="${dto.addr }" id="updateaddr">
					</td>
				</tr>
			</table>
			<div align="center">
				<button type="button" class="btn btn-success" data-bs-dismiss="modal" id="updatesave">Save</button>
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
			</div>
      </div>
    </div>
  </div>
</div> 
	
</body>
</html>