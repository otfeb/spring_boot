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
<style type="text/css">
	ul.menu{
		list-style: none;
	}
	ul.menu li{
		width: 80px;
		height: 30px;
		float: left;
		line-height: 60px;
		text-align: center;
		margin-right: 10px;
		font-size: 22px;
		font-family: 'Dongle';
		cursor: pointer;
	}
	ul.menu li a{
		text-decoration: none;
		color: black;
	}
</style>
</head>
<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>
	<ul class="menu">
	
		<li>
			<a href="${root }/search/sangsearch"><i class="bi bi-search"></i></a>
		</li>
	
		<li>
			<c:if test="${sessionScope.loginok!=null }">
				<a href="${root }/member/myinfo?id=${sessionScope.myid}">MYINFO</a>
			</c:if>
			<c:if test="${sessionScope.loginok==null }">
				<a href="${root }/login/main">MYINFO</a>
			</c:if>
		</li>
		
		<li>
			<a href="${root }/member/list">MEMBER</a>
		</li>
		
		<li>
			<a href="${root }/member/add">SIGN UP</a>
		</li>
		
		<li>
			<a href="${root }/login/main">SIGN IN</a>
		</li>

	</ul>
</body>
</html>