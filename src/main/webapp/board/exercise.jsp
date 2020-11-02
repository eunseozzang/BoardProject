<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<c:forEach var="i" begin="1" end="5" step="1" varStatus="status">
		<div class="form-group">
			<label for="file${i }" class="col-sm-2 control-label">첨부파일${i }</label>
			<div class="col-sm-10">
				<input type="file" name="realfilename${i }" />
			</div>
		</div>
	</c:forEach>
	
	
</body>
</html>