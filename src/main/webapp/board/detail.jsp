<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>Jsp</title>
<%@ include file="/layout/commonLib.jsp" %>

<script>

	$(function(){
		
		$("#regBtn").on("click",function(){
			$('#frm2').submit();
		})

		$('#boardcomment').keyup(
				function() {
					if ($(this).val().length > $(this).attr('maxlength')) {
						alert('댓글은 500자 이하로만 입력 가능합니다.');
						$(this).val($(this).val().substr(0, $(this).attr('maxlength')));
					}
				});

	})
</script>
<style>
	#boardcomment{
		width : 500px;
	}
	
	
	#a3{
		margin-left : 18%;
	}
	
	#div1{
		margin-top : 30px;
	}
	
	#td1{
		width : 350px;
	}
	
	#td2{
		width : 150px;
	}
	
</style>
</head>

<body>
	<%@ include file="/layout/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/layout/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="form-group">
							<a href="${cp }/writeReply?boardno=${param.boardno}" class="btn btn-default" id="a3">답글</a>
			<c:choose>
						<c:when test="${boardVO.userid == USER.userid}">
							<a href="${cp }/boardUpdate?boardno=${param.boardno}" class="btn btn-default" id="a1">수정</a>
							<a href="${cp }/boardDelete?boardno=${param.boardno}" class="btn btn-default" id="a2">삭제</a>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					<br><br>
						<label for="userNm" class="col-sm-2 control-label">글번호</label>
						<div class="col-sm-10">
							<label class="control-label">${boardVO.boardno }</label>
						</div>
					</div>
<br><hr>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">작성일자</label>
						<div class="col-sm-10">
							<label class="control-label"><fmt:formatDate value="${boardVO.boarddate }" pattern="YYYY-MM-dd"/></label>
						</div>
					</div>
					
<br><hr>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">작성자</label>
						<div class="col-sm-10">
							<label class="control-label">${boardVO.userid }</label>
						</div>
					</div>
					
<br><hr>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<label class="control-label">${boardVO.boardtitle }</label>
						</div>
					</div>
<br><hr>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">내용</label>
						<div class="col-sm-10">
							<label class="control-label">${boardVO.boardcontent }</label>
						</div>
					</div>
<br><hr>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-10">
<%-- 							<img src="${cp }/profileImg?userid=${memberVO.userid }"/><br><br> --%>
							<c:forEach items="${fileList }" var="file">
								<form id="frm" class="form-horizontal" role="form"
						  action="${cp }/fileDown" method="POST">
						  		<input type="text" name="fileno" id="fileno" value="${file.fileno }" style="display:none">
								<input type="submit" id="modify" class="btn btn-default" value="${file.filename }">
								</form>
							</c:forEach>
						</div>
					</div>
<br><br>		
			<hr>
			<div class="form-group" id="div1">
					<label for="userNm" class="col-sm-2 control-label">댓글</label>
					<div class="col-sm-10">
						<form id="frm2" class="form-horizontal" role="form"
							action="${cp }/commentWrite" method="POST">
							<input type="text" name="boardno" id="boardno"
								value="${param.boardno }" style="display: none">
							<table>
								<tr>
									<td><input type="text" class="form-control"
										id="boardcomment" name="boardcomment" maxlength="500"></td>
									<td><button id="regBtn" type="button"
											class="btn btn-default">등록</button></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				
			<div class="form-group">
				<c:forEach items="${replyList }" var="reply">
					<form id="frm3" class="form-horizontal" role="form"
						  action="${cp }/commentDelete" method="POST">
					<input type="text" name="replyno" id="replyno" value="${reply.replyno }" style="display:none">
					<input type="text" name="boardno" id="boardno" value="${reply.boardno }" style="display:none">
					<label for="userNm" class="col-sm-2 control-label"></label>
					<div class="col-sm-10">
					<br>
							<table>
								<tr>
									<td id="td1"><strong>${reply.replycontent }</strong></td>
									<td id="td2"><strong>${reply.userid }/<fmt:formatDate value="${reply.replydate }" pattern="YYYY-MM-dd"/></strong></td>
									<c:choose>
										<c:when test="${USER.userid == reply.userid && reply.replydel == 0 }"><td><input type="submit" id="replydel" class="btn btn-default" value="삭제"></td></c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</tr>
							</table>
					</div>
					</form>
				</c:forEach>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>
