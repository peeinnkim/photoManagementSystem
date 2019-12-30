<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.5.3/handlebars.min.js"></script>
<style>
	.thmub-wrap {
		height: 130px;
	}
	.img-box {
		position: relative;
		float: left;
		margin: 3px;
	}
	.img-box > .delX {
		position: absolute;
		right: 0;
		top: 16px;
	}
	.img-box > .imgDate {
		font-size: 12px;
		overflow: hidden;
	}
</style>

	<h1>PHOTO</h1>
	
	<div class="form-wrap">
		<form id="frm" enctype="multipart/form-data">
			<input type="text" name='pWriter' value="${Auth}">
			<div class="upload-wrap">
				<p><small>버튼을 누르거나 화면에 사진을 드래그 해서 추가해보라고</small></p>
				<p>
					<input type="file" name="files" multiple="multiple">
					<input type="submit" value="추가">
				</p>
				
				<div class="thmub-wrap">
					
				</div>
			</div>
			
			<div class="list-wrap">
				<h2>PHOTO LIST</h2>
				<div class="pic-wrap">
					<c:forEach var="pic" items="${list}">
						<div class="img-box">
		 					<p class="imgDate">${pic.pName}<fmt:formatDate value="${pic.pRegdate}" pattern="yyyy-MM-dd"/></p>
								<img src="${pageContext.request.contextPath}/photo/display?filename=${pic.pName}">
							<span class="delX" data-src="${pic.pName}" data-no="${pic.pNo}">❌</span>
						</div>   
					</c:forEach>          
				</div>
				<ul class="pagination"></ul>
				<div class="big-img">
					
				</div>
			</div>
		</form>
	</div>


	<script id="reply_template" type="text/x-handlebars-template">
		{{#list}}
		<li class="replyLi">
			<div class="item">
				<span class="rno">{{rno}}</span> : <span class="writer">{{replyer}}</span><br>
				<span class="content">{{replytext}}</span>
				<div class="btnWrap">
					<button class="btnMod">수정</button>
					<button class="btnDel">삭제</button>
				</div>
			</div>
		</li>
		{{/list}}
	</script>


	<script>
		/* 사진 리스트 불러오기 */
		getPhotoList();
	
		/* 썸네일 뜨기 */
		function makeThumbnail(file){
			$(".thmub-wrap").empty();
			
			var reader = null;
			
			for(var i=0; i<file.length; i++) {
				reader = new FileReader();
				reader.readAsDataURL(file[i]);
				reader.onload = function(e){ 
					var $img = $("<img>").attr("src", e.target.result).css({"height" : "100px", "margin": "5px 3px"});
					$(".thmub-wrap").append($img);
				}
			}
		}
		
		/* 파일 추가하면 썸네일 뜨게 하기 */
		$("input[type='file']").change(function(){
			var file = $(this)[0].files; 
			makeThumbnail(file);
		})
		
		/* $("body").on("dragenter dragover", function(e){
			console.log("drag event");
			e.preventDefault();
		})//drag event
		
		$("body").on("drop", function(e){
			console.log("drop event");
			e.preventDefault();
			
			var files = e.originalEvent.dataTransfer.files; 
			makeThumbnail(files);
			formData.append("files", files);
		})//drop event */
		
		/* 업로드 하기 */
		$("#frm").submit(function(){
			var formData = new FormData($("#frm")[0]);
			
			$.ajax({
				url: "upload",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				success: function(res){
					console.log(res);
				
					if(res == "success") {
						$(".thmub-wrap").empty();						
						getPhotoList();
						
					} else if (res == "fail") {
						$(".pic-wrap").empty();
						alert("사진 업로드에 실패하였습니다.");
					}
				},
				error: function(e){
					console.log(e);
				}
			})//ajax
			return false;
		})//upload
		
		/* X 누르면 삭제되기 */
		$(document).on("click", ".delX", function(){
			var formData = new FormData();
			formData.append("pName", $(this).attr("data-src"));
			formData.append("pNo", $(this).attr("data-no"));
			
			$.ajax({
				url: "delete",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				success: function(res){
					console.log(res);
				
					if(res == "success") {
						getPhotoList();
						
					} else if (res == "fail") {
						alert("사진 삭제에 실패하였습니다.");
					}
				},
				error: function(e){
					console.log(e);
				}
			})//ajax
			
		})
		
		/* 추가된 리스트 뿌리기 */
		function getPhotoList() {
			var formData = new FormData();
			formData.append("id", $("input[name='pWriter']").val());
			
			$.ajax({
				url: "list",
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				success: function(res){
					console.log(res);
 					$(".pic-wrap").empty();
					
					$(res).each(function(i, obj){
						var $img = $("<img>").attr("src", "${pageContext.request.contextPath}/photo/display?filename=" + obj.pName);
						var $x = $("<span>").addClass("delX").append("❌").attr("data-no", obj.pNo).attr("data-src", obj.pName);
						var $p = $("<p>").addClass("imgDate").append(formatDate(obj.pRegdate));
						var $imgDiv = $("<div>").addClass("img-box").append($p).append($img).append($x);
						$(".pic-wrap").append($imgDiv);
					})                
				},
				error: function(e){
					console.log(e);
				}
			})//ajax
		}//getPhotoList
		
		
		/*----------- PAGINATION -------------*/
		var nPage = 1; //현재 선택한 페이지 번호값을 가짐
		
		//page번호 받아서 리스트 부르는 메소드
		function getPageList(page) {
		 	$.ajax({
				url: "replies/" + $("input[name='bno']").val() + "/" + page,
				type: "get",
				dataType: "json",
				success: function(res){
					console.log(res);
				 	$("#replyList").text("");
				 	
				 	var source = $("#reply_template").html();
				 	var func = Handlebars.compile(source);
				 	var str = func(res);
				 	
				 	$("#replyList").append(str);
				 	
				 	var startPage = res.pageMaker.startPage;
				 	var endPage = res.pageMaker.endPage;
				 	$(".pagination").empty();
				 	
 					for(var i=startPage; i<=endPage; i++) {
 						var $li = $("<li>");
 						
 						if(i == page) {
 							$li.addClass("on");
 						}
 						
 						var $a = $("<a>").attr("href", "#").attr("data-page", i).text(i);
				 		$li.append($a);
					 	
				 		$(".pagination").append($li);
					}           
 					
				 	if(res.pageMaker.prev == true) {
				 		var $li = $("<li>");
				 		var $a = $("<a>").attr("href", "#").attr("data-page", startPage-1).text("◀");
				 		$li.append($a);
				 		$(".pagination").prepend($li);
				 		
				 	} else if(res.pageMaker.next == true) {
				 		var $li = $("<li>");
				 		var $a = $("<a>").attr("href", "#").attr("data-page", endPage+1).text("▶");
				 		$li.append($a);
						$(".pagination").append($li);
				 	}
 					
				},
				error: function(e){
					console.log(e);
				}
			})		
		}//function end
		
		//날짜 포맷 바꾸기
		function formatDate(date) { 
			var d = new Date(date);
			var month = '' + (d.getMonth() + 1);
			var day = '' + d.getDate();
			var year = d.getFullYear(); 
			
			if (month.length < 2) { month = '0' + month; } 
			if (day.length < 2) { day = '0' + day; } 
					
			return [year, month, day].join('-');
		}

		
	</script>

<%@ include file="../include/footer.jsp"%>