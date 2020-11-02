<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/summernote/summernote-lite.js"></script>
<script src="/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/summernote/summernote-lite.css">
<title>Insert title here</title>
<script>
$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote();
});
</script>
</head>
<body>

<form method="post">
  <textarea id="summernote" name="summernote"></textarea>
</form>

</body>
</html>