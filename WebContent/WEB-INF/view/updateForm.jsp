<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update.do" method="post">
		<table>
			<tr>
			<th>프로젝트 이름</th>
			<td><input type="text" name="name" value="${spms.name }"></td>
		</tr>
		<tr>
			<th>프로젝트 내용</th>
			<td><textarea name="content">${spms.content }</textarea></td>
		</tr>
		<tr>
			<th>시작날짜</th>
			<td><input type="date" name="startDate" value="${spms.startDate }"></td>
		</tr>
		<tr>
			<th>종료날짜</th>
			<td><input type="date" name="startDate" value="${spms.endDate }"></td>
		</tr>
		<tr>
			<th>상태</th>
			<td>
				<select name="state">
					<option selected="selected">${spms.state }</option>
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</th>
		</tr>
		</table>
	</form>
</body>
</html>
