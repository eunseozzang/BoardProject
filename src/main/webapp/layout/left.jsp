<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.service.BoardService"%>
<%@page import="kr.or.ddit.board.service.BoardServiceI"%>
<%@page import="kr.or.ddit.board.model.BoardTypeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
<ul class="nav nav-sidebar">
	<li class="active"><a href="${cp }/main.jsp">MAIN<span class="sr-only">(current)</span></a></li>
	<c:choose>
		<c:when test="${USER !=null }">
			<li class="active"><a href="${cp }/board/manage.jsp">게시판관리 <span class="sr-only">(current)</span></a></li>
		</c:when>
	</c:choose>
	<c:forEach items="${typeList }" var="type">
		<li class="active"><a href="${cp }/boardList?typeno=${type.typeno }&page=1">${type.boardname } <span class="sr-only">(current)</span></a></li>
	</c:forEach>
</ul>

