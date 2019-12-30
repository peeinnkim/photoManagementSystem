<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
	
	<h1>LOGIN</h1>
	
	<div class="form-wrap">
		<form id="frm" action="loginPost" method="post" onsubmit="return validCheck()">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="mId" value="${id}"></td>
				</tr>			
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="mPw"></td>
					<td><input type="submit" value="로그인"></td>
				</tr>			
				<tr>
					<td colspan="3">
						<c:if test="${error != null}">
							<span class="error" style="display:block;">${error}</span>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
<%@ include file="../include/footer.jsp"%>