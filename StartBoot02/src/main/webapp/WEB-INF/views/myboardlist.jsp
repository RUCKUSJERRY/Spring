<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>LIST</h1>
	
	<table border="1">
		
		<col width="50" />
		<col width="100" />
		<col width="500" />
		<col width="100" />
		
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<C:choose>
			<C:when test="${empty list }">
				<tr>
					<th colspan="4">----------작성된 글이 없습니다.----------</th>
				</tr>
			</C:when>
			<C:otherwise>
				<C:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.myno}</td>
						<td>${dto.myname}</td>
						<td><a href="/select?myno=${dto.myno} }">${dto.mytitle}</a></td>
						<td>${dto.mydate}</td>
					</tr>

				</C:forEach>
			</C:otherwise>
		</C:choose>
		<tr>
			<td colspan="4" align="right">
				<input type="button" value="글작성" onclick="/insertform">
			</td>
		</tr>
		
		
		
		
	</table>

</body>
</html>