<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>UPDATE</h1>
	
	<form action="updateres.do" method="post">
	<table border="1">
		<tr>
			<th>번호</th>
			<td><input type="text" name="myno" readonly="readonly" value="${dto.myno }" /></td>
		
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.myname }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="mytitle" value="${dto.mytitle }"> </td>
		</tr>
		<tr>
			<th>번호</th>
			<td><textarea cols="60" rows="10" name="mycontent" >${dto.mycontent }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정하기">
				<input type="button" value="돌아가기" onclick="location.href='select.do?myno=${dto.myno }'">
			</td>
		</tr>
	</table>
	</form>

</body>
</html>