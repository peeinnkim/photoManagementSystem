<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PHOTO MANAGEMENT SYSTEM</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<div id="inner-header">
				<div class="header-title">
					PHOTO<br>MANAGEMENT<br>SYSTEM
				</div>
				<div class="header-top">
					<ul>
					<c:if test="${Auth != null}">
						<li><small>WELCOME!</small> <b>${Auth}</b></li>
					</c:if>
					<c:if test="${Auth == null}">
						<li><a href="${pageContext.request.contextPath}/member/join">JOIN</a></li>
						<li><a href="${pageContext.request.contextPath}/member/login">LOGIN</a></li>
					</c:if>
					</ul>
				</div>
				<div class="header-bottom">
					<ul>
						<li><a href="${pageContext.request.contextPath}">HOME</a></li>
						<li><a href="${pageContext.request.contextPath}/photo/photoMng">PHOTO</a></li>
					</ul>
				</div>
			</div>
		</header>
		
		<section>
			<div id="inner-section">
