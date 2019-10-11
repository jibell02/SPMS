<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		margin-top:10px;
		width:80%;
	}
	table, td, th{
		border:1px solid black;
		border-collapse: collapse;
		padding:10px;
	}
	th{
		text-align: center;
	}
	th:last-child {
		font-weight: normal;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>프로젝트 이름</th>
			<td>${spms.name }</td>
		</tr>
		<tr>
			<th>프로젝트 내용</th>
			<td>${spms.content }</td>
		</tr>
		<tr>
			<th>시작날짜</th>
			<td>${spms.startDate }</td>
		</tr>
		<tr>
			<th>종료날짜</th>
			<td>${spms.endDate }</td>
		</tr>
		<tr>
			<th>상태</th>
			<td>${spms.state }</td>
		</tr>
		<tr>
			<th colspan="2">
				<a href="${pageContext.request.contextPath }/update.do?no=${spms.no}">[수정]</a>
				<a href="${pageContext.request.contextPath }/delete.do?no=${spms.no}">[삭제]</a>
				<a href="${pageContext.request.contextPath }/list.do">[돌아가기]</a>
			</th>
		</tr>
	</table>
</body>
</html>
