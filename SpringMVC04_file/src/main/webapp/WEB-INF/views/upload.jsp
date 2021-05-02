<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- enctype : 데이터가 인코딩 되는 방법 메소드가 post일때만 사용가능 
		"multipart/form-data"로 해놓아야 MultipartResolver가 MultipartFile객체를 컨트롤러에 전달 할 수 있다.
		 modelAttribute : 노출되는 속성의 이름? -->
	<form:form method="post" enctype="multipart/form-data" modelAttribute="uploadFile" action="upload">
		<h3>uploadForm</h3>
		file<br/>
		<input type="file" name="mpfile" /><br/>
		<p style="color:red; font-weight:bold;">
			<form:errors path="mpfile"></form:errors>
		</p><br/>
		<textarea rows="10" cols="40" name="desc"></textarea><br/>
		<input type="submit" value="send" />
	</form:form>
	
	<!-- 
		enctype
		1. application/www-form-urlencoded : (default) 문자들을 encoding
		2. mulitpart/form-data : file upload -> post만 
		3. text/plain : encoding 하지 않음
		
		spring form tag
			form:form, form:errors, form:input, ...
			form:errors -> Errors, BindingResult를 사용할 때, command 객체의 특징 field에서 발생하는 에레메시지 출력
		
	
	 -->

</body>
</html>