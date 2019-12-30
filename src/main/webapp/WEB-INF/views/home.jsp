<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>

	<h1>HOME</h1>
	<c:if test="${Auth != null}">
		<p><b>${Auth}</b>님 로그인 중</p>
	</c:if>

<%@ include file="include/footer.jsp"%>