<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>Jsp</title>
<%@ include file="/layout/commonLib.jsp" %>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
$(document).ready(function() {

	$('#summernote').summernote({
        width : 800,
        height: 500,
        lang: "ko-KR"
     });

	$('#regBtn').on('click', function() {
		var text=$("#summernote").val(); 
		 $('#frm').submit();
	})

	$('#del1').on('click',function(){
		fileno = $('#fileno1').val();
		$('#delete1').val(fileno)
		$('#filename1').hide();
		$('#del1').hide();
		$('#realfilename1').show();
	})
	
	$('#del2').on('click',function(){
		fileno = $('#fileno2').val();
		$('#delete2').val(fileno)
		$('#filename2').hide();
		$('#del2').hide();
		$('#realfilename2').show();
	})
	
	$('#del3').on('click',function(){
		fileno = $('#fileno3').val();
		$('#delete3').val(fileno)
		$('#filename3').hide();
		$('#del3').hide();
		$('#realfilename3').show();
	})
	
	$('#del4').on('click',function(){
		fileno = $('#fileno4').val();
		$('#delete4').val(fileno)
		$('#filename4').hide();
		$('#del4').hide();
		$('#realfilename4').show();
	})
	$('#del5').on('click',function(){
		fileno = $('#fileno5').val();
		$('#delete5').val(fileno)
		$('#filename5').hide();
		$('#del5').hide();
		$('#realfilename5').show();
	})
	
});
</script>
<style>
	#boardtitle{
		width : 800px;
	}
	
	#main{
		width : 800px;
	}
	
	.col-sm-offset-3 {
	    margin-left: 0%;
	}
	
	#del1{
		width : 35px;
	}
	#del2{
		width : 35px;
	}
	#del3{
		width : 35px;
	}
	#del4{
		width : 35px;
	}
	#del5{
		width : 35px;
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
         <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="main">
               <c:choose>
                  <c:when test="${USER.userid == boardVO.userid}">
            <form id="frm" class="form-horizontal" role="form"
                 action="${cp }/boardUpdate" method="POST"
                 enctype="multipart/form-data">
                 <br><br>
                <input type="text" name="boardno" id="boardno" value="${boardVO.boardno }" style="display:none">
               <div class="form-group">
                  <label for="boardtitle" class="col-sm-2 control-label">제목</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="boardtitle" name="boardtitle" value="${boardVO.boardtitle }">
                  </div>
               </div>
               <div class="form-group">
                  <label for="boardcontent" class="col-sm-2 control-label">내용</label>
                  <div class="col-sm-10">
                  <textarea class="form-control" id="summernote" name="summernote">${boardVO.boardcontent }</textarea>
                  </div>
               </div>
               
					<div class="form-group">
						<div class="col-sm-10">
							<c:forEach items="${fileList }" var="file" varStatus = "status">
								<input type="text" name="fileno${status.count }" id="fileno${status.count }" value="${file.fileno }" style="display:none">
								<label for="file${status.count }" class="col-sm-2 control-label" id="gma">첨부파일${status.count }</label>
								<input id = "filename${status.count }"type="text" class="btn btn-default" value="${file.filename }">
								<input id="del${status.count }" type="button" class="btn btn-default" value="X"><br>
								<input type="file" id="realfilename${status.count }" name="realfilename${status.count }" style="display:none"/>
							</c:forEach>
						</div>
					</div>
					
               <c:forEach var="i" begin="${filecount + 1 }" end="5" step="1" varStatus = "status">
	               <div class="form-group">
	                  <label for="file${i }" class="col-sm-2 control-label">첨부파일${i }</label>
	                  <div class="col-sm-10">
	                     <input type="file" name="realfilename${i }"/>
	                  </div>
	               </div>
               </c:forEach>
               <c:forEach var="i" begin="1" end="5" step="1">
	               <input type="hidden" id="delete${i }" name="delete${i }" value="">
               </c:forEach>
               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button id="regBtn" type="button" class="btn btn-default">게시글 수정</button>
                  </div>
               </div>
            </form>
                  </c:when>
                  <c:otherwise>권한이 없습니다.</c:otherwise>
               </c:choose>
         </div>
      </div>
   </div>
</body>
</html>