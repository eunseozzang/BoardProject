<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<%@ include file="/layout/commonLib.jsp" %>
<script>

	$(document).ready(function(){
		$("#create").on("click",function(){
			
			$('#frm1').submit();
		})
		
		
	})

</script>
</head>
<body>
<%@ include file="/layout/header.jsp" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@ include file="/layout/left.jsp" %>
			
		</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<c:choose>
				<c:when test="${USER != null}">
				<div class="blog-header">
					<h1 class="blog-title">게시판 관리</h1>
				</div>

			<div class="row">
				<div class="col-sm-8 blog-main">
					<div class="blog-post">
						<form id="frm1" class="form-horizontal" role="form"
					  action="${cp }/boardCreate" method="POST">
						게시판 이름
						<input type="text" name="boardname" id="boardname">
						<select name="boarduse" id="boarduse">
							<option value="usable">사용</option>
							<option value="unusable">미사용</option>
						</select>
						<button type="button" id="create" class="btn btn-default">생성</button><br><br>
						</form>
						
						<hr>
						
						<br>
						<c:forEach items="${typeList2 }" var="type2">
							<form id="frm" class="form-horizontal" role="form"
						  action="${cp }/manageBoard" method="POST">
							게시판 이름
							<input type="text" name="typeno" id="typeno" value="${type2.typeno }" style="display:none">
							<input type="text" name="boardname" id="boardname" value="${type2.boardname }" readonly>
							<select name="boarduse" id="boarduse">
								<c:choose>
									<c:when test="${type2.boarduse == 0 }">
										<option value="usable" selected>사용</option>
										<option value="unusable">미사용</option>
									</c:when>
									<c:when test="${type2.boarduse == 1 }">
										<option value="usable">사용</option>
										<option value="unusable" selected>미사용</option>
									</c:when>
								</c:choose>
							</select>
							<input type="submit" id="modify" class="btn btn-default" value="수정">
							<br><br>
							</form>
						</c:forEach>
					</div>
				</div>
			</div>
				</c:when>
				<c:when test="${USER == null}"></c:when>
			</c:choose>
		</div>
	</div>
</div>
</body>
</html>