<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<style>
	.greeting {
		width: 100%;
		text-align: center;
		margin: 50px 0;
	} 
	.form-wrap {
		width: 95%;
		margin: 0 auto;
		font-family: 'Gothic A1', sans-serif;
		font-size: 13px;
		font-weight: bold;
	}
	.form-wrap table {
		width: 50%;
		margin: 0 auto;
	}
	input[type='submit'] {
		width: 50px;
		height: 50px;
		border: 3px solid yellow; 
		background: #F361A6;
		box-shadow: 2px 2px 2px grey;
		color: white;
		font-weight: bold;
		cursor: pointer;
	}
	.tb-label {
		color: #212121;
	}
	.tb-input > input {
		width: 95%;
	}
</style>
	
	<h1>JOIN</h1>   

	<div class="greeting">
		<h1>WELCOME TO PHOTO MANAGEMENT SYSTEM!</h1><br>
		<p>PLEASE, JOIN US!</p>
	</div>
	   
	<div class="form-wrap">
		<form id="frm" action="join" method="post" onsubmit="return validCheck()">
			<table>
				<tr>
					<td class="tb-label">아이디</td>
					<td class="tb-input"><input type="text" name="mId" value="${vo.mId}"></td>
					<td>
						<button type="button" onclick="duplicateChk()">중복확인</button>
						<span class="regInfo">영어, 숫자 6~15자</span>
						<span class="error">아이디 입력.. 제대로..</span>
					</td>
				</tr>
				<tr>
					<td class="tb-label">비밀번호</td>
					<td class="tb-input"><input type="password" name="mPw"></td>
					<td>
						<span class="regInfo">영어, 숫자, 특수문자(!@#$%^&*-_) 8~20자</span>
						<span class="error">비밀번호 입력.. 제대로..</span>
					</td>
				</tr>
				<tr>
					<td class="tb-label">비밀번호 확인</td>
					<td class="tb-input"><input type="password" name="mPw2"></td>
					<td><span class="error">안맞잖아...</span></td>
				</tr>
				<tr>
					<td class="tb-label">이름</td>
					<td class="tb-input"><input type="text" name="mName" value="${vo.mName}" maxlength="10"></td>
					<td><span class="error">이름...제대로...입력...</span></td>
				</tr>
				<tr>
					<td class="tb-label">이메일</td>
					<td class="tb-input"><input type="email" name="mMail" value="${vo.mMail}"></td>
					<td><span class="error">이메일....제대로입력....</span></td>
				</tr>
				<tr>
					<td class="tb-label">전화번호</td>
					<td class="tb-input">
						<input type="tel" name="mTel" value="${vo.mTel}" maxlength="14" placeholder="010-0000-0000">
					</td>
					<td><span class="error">전화번호... 제대로....입력..</span></td>
				</tr>
				<tr>
					<td></td>
					<td><span class="error"></span></td>
					<td><input type="submit" value="가입"></td>
				</tr>
			</table>
		</form>
	</div>
	
	<script>
		function duplicateChk(){
			$.ajax({
				url: "dupChk",
				type: "post",
				data: {"mId": $("input[name='mId']").val()},
				dataType: "text",
				success: function(res){
					console.log(res);
					
					if(res == "available") {
						alert("사용가능한 아이디입니다.");
						$("button[type='button']").css("display", "none");
						
					} else if(res == "duplicated"){
						alert("중복된 아이디입니다. 다른 아이디를 입력하세요.");
					}
				},
				error: function(e){
					console.log(e);
				}
				
			})
			return false;
		}
	
		//유효성 검사
		function validCheck(){
			if(frm.mId.value == "") {
				alert("아이디를 입력하세요");
				frm.mId.focus();
				return false;
			}
			if(frm.mPw.value == "") {
				alert("비밀번호를 입력하세요");
				frm.mPw.focus();
				return false;
			}
			if(frm.mPw2.value == "") {
				alert("비밀번호 확인을 입력하세요");
				frm.mPw2.focus();
				return false;
			}
			if(frm.mName.value == "") {
				alert("이름를 입력하세요");
				frm.mName.focus();
				return false;
			}
			if(frm.mMail.value == "") {
				alert("메일주소를 입력하세요");
				frm.mMail.focus();
				return false;
			}
 			if(frm.mTel.value == "") {
				alert("전화번호를 입력하세요");
				frm.mTel.focus();
				return false;
			}
 			if($("button[type='button']").css("display") != "none") {
 				alert("아이디 중복확인을 해주세요");
 				return false;
 			}
 			
		}
		
		//id 유효성 검사
		$("input[name='mId']").change(function(){
			var idReg = /^[a-z0-9]{6,15}$/;
			regTest($(this), idReg);
		})
		
		$("input[name='mId']").focus(function(){
			getFocus($(this));
			$("button[type='button']").css("display", "inline-block");
		})
	
		//pw 유효성 검사
 		$("input[name='mPw']").change(function(){
 			var pwReg = /^[a-z0-9!@#$%^&*-_]{8,20}$/i;
 			regTest($(this), pwReg);
		}) 
		$("input[name='mPw']").focus(function(){
			getFocus($(this));
		})
		
		//name 유효성 검사
 		$("input[name='mName']").change(function(){
 			var nmReg = /^[기-힣]{2,5}$/;
 			regTest($(this), nmReg);
		}) 
		$("input[name='mName']").focus(function(){
			getFocus($(this));
		})
		
		//mail 유효성 검사
 		$("input[name='mMail']").change(function(){
 			var mailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
 			regTest($(this), mailReg);
		}) 
		$("input[name='mMail']").focus(function(){
			getFocus($(this));
		})
		
		//tel 유효성 검사
 		$("input[name='mTel']").change(function(){
 			var telReg = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
 			regTest($(this), telReg);
		}) 
		$("input[name='mTel']").focus(function(){
			getFocus($(this));
		})
		
		//유효성 검사 메소드
		function regTest(obj, reg){
			if(reg.test(obj.val()) == false){
				$(obj).parent().next().children(".error").css("display", "inline-block");
				$(obj).parent().next().children(".regInfo").css("display", "none");
			}
		}
		
		//포커스 메소드
		function getFocus(obj){
			$(obj).parent().next().children(".error").css("display", "none");
			$(obj).parent().next().children(".regInfo").css("display", "inline-block");
		}
		
	</script>
	
<%@ include file="../include/footer.jsp"%>
