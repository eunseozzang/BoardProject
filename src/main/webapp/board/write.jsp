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
                  <c:when test="${USER != null}">
            <form id="frm" class="form-horizontal" role="form"
                 action="${cp }/boardWrite" method="POST"
                 enctype="multipart/form-data">
                 <br><br>
                 <input type="hidden" value="${param.typeno }" id="typeno" name="typeno">
               <div class="form-group">
                  <label for="boardtitle" class="col-sm-2 control-label">제목</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="boardtitle" name="boardtitle">
                  </div>
               </div>
               <div class="form-group">
                  <label for="boardcontent" class="col-sm-2 control-label">내용</label>
                  <div class="col-sm-10">
                  <textarea class="form-control" id="summernote" name="summernote"></textarea>
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
               
               
               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button id="regBtn" type="button" class="btn btn-default">게시글 등록</button>
                  </div>
               </div>
            </form>
                  </c:when>
                  <c:when test="${USER == null}">
                  <h3>글 작성을 위해서는 로그인을 해주세요</h3>
                  </c:when>
               </c:choose>
         </div>
      </div>
   </div>
</body>
</html>