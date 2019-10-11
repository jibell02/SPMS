<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#container{
		width:90%;
		margin:0 auto;
	}
	#div_Insert{
		width:80%;
		text-align: right;
	}
	table{
		margin-top:10px;
		width:80%;
	}
	table, td{
		border:1px solid black;
		border-collapse: collapse;
		padding:10px;
		text-align: center;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="div_Insert">
			<a href="${pageContext.request.contextPath }/insert.do" id="insert">[새 프로젝트 등록]</a>
		</div>
		
		<table>
			<tr>
				<td>프로젝트 이름</td>
				<td>시작 날짜</td>
				<td>종료 날짜</td>
				<td>상태</td>
			</tr>
			<c:forEach var="s" items="${spms }">
				<tr>
					<td><a href="read.do?no=${s.no }">${s.name }</a></td>
					<td>${s.startDate }</td>
					<td>${s.endDate }</td>
					<td>${s.state }</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
</body>
</html>
