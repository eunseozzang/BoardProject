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
	$(document).ready(function(){
		$("#boardList tr").on("click",function(){
			var del = $(this).data("boarddel");
			if(del == 0){
				var boardno = $(this).data("boardno");
				document.location = "${cp }/board?boardno=" + boardno;
			}
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
</div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${boardname }</h2>
		<div class="table-responsive">
      <table class="table table-striped">
         <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>작성자</th>
            <th>작성일</th>
         </tr>
       <tbody id="boardList">
       <c:forEach items="${boardList }" var="list">
       <tr data-boardno="${list.boardno }" data-boarddel="${list.boarddel }">
       		<td>${list.boardno }</td>
       		<td>${list.boardtitle }</td>
       		<td>${list.userid }</td>
       		<td><fmt:formatDate value="${list.boarddate }" pattern="YYYY-MM-dd"/></td>
       	</tr>
       </c:forEach>
       </tbody>
         
   </table>
		</div>

	<div class="text-center">
			<ul class="pagination">
			<c:choose>
				<c:when test="${pages == 0 }"></c:when>
				<c:when test="${param.page ==1 }"></c:when>
				<c:otherwise>
					<li><a href="${cp }/boardList?typeno=${param.typeno }&page=1"><<</a></li>
					<li><a href="${cp }/boardList?typeno=${param.typeno }&page=${param.page-1}"><</a></li>
				</c:otherwise>
			</c:choose>
				<c:forEach var="i" begin="1" end="${pages }">
					<c:choose>
						<c:when test="${i == page }">
							<li class="active"><span>${i }</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${cp }/boardList?typeno=${param.typeno }&page=${i }">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			<c:choose>
				<c:when test="${param.page == pages }"></c:when>
				<c:when test="${pages == 0 }"></c:when>
				<c:otherwise>
					<li><a href="${cp }/boardList?typeno=${param.typeno }&page=${param.page+1}">></a></li>
					<li><a href="${cp }/boardList?typeno=${param.typeno }&page=${pages }">>></a></li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
	</div>
</div>
	<a href="${cp }/boardWrite?typeno=${param.typeno }" class="btn btn-default">글작성</a>
	
	</div>
		</div>
	</div>
</body>
</html>
