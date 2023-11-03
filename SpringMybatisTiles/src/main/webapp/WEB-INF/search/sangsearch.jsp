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
	#maindiv{
		position: absolute;
		left: 27%;
	}
</style>
<script type="text/javascript">
	$(function(){

		$("#search").keyup(function(){
			
			var search=$(this).val();
			//alert(search);
			
			$.ajax({
				type:"get",
				dataType:"json",
				url:"/search/result",
				data:{"search":search},
				success:function(res){
					var s="";
					
					$.each(res,function(i,dto){
						s+="<b onclick='selectSearch()' class='searchResult'>"+dto+"</b><br>"
					});
					
					if(search==""){
						$("#result").html("");
					}
					else{
						$("#result").html(s);
					}
				}
			});
		});
	});
	
	function selectSearch() {
		$(document).on("click","b.searchResult",function(event){
			var s=$(this).html();
			//alert(s);
			
			$("#search").val(s);
			$("#result").html("");
		});
	}
</script> 
</head>
<body>
	<div align="center" id="maindiv">
		<img src="../upload/google.png">
		<input type="text" class="form-control" style="width: 600px; background-color: lightgray;" id="search">
		<br>
		<div id="result"></div>
	</div>
</body>
</html>