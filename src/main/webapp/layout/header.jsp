<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${cp }/main.jsp">게시판
			<c:choose>
				<c:when test="${USER == null}"></c:when>
				<c:when test="${USER != null}">[${USER.username }]</c:when>
			</c:choose>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<c:choose>
						<c:when test="${USER == null}"><a href="${cp }/login">Login</a></c:when>
						<c:when test="${USER != null}"><a href="${cp }/logout">Logout</a></c:when>
					</c:choose>
				</li>
			</ul>
		</div>
	</div>
</nav>