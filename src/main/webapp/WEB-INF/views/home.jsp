<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>

<style>
	#inner-section > #bg-top {
		background: black;
		width: 100%;
		height: 40%;
		position: absolute;
		right: 0;
		top: 0;
	}
	#inner-section > #bg-bottom {
		background: url("${pageContext.request.contextPath}/resources/img/bg1.jpg") no-repeat;
		background-size: cover;
		width: 100%;
		height: 60%;
		display: block;
		position: absolute;
		right: 0;
		bottom: 0;
	}
	#inner-section > h2 {
		position: absolute;
		right: 25%;
		top: 25%;
		text-align: center;
		font-size: 5em;
		color: #F361A6;
		line-height: 1.3em;
		text-shadow: 2px 2px 0px yellow, 3px 3px 0px yellow, 5px 5px 0px yellow;	
	}
</style>

	<span id="bg-top"></span>
	<span id="bg-bottom"></span>
	<h2>
		PHOTO<br>
		MANAGEMENT<br>
		SYSTEM
	</h2>

<%@ include file="include/footer.jsp"%>