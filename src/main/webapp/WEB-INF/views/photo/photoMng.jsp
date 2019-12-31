<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<style>
	.form-wrap {
		width: 95%;
		margin: 0 auto;
	}
	.upload-wrap {
		font-size: 13px;
		font-weight: bold;
		background: #FFE08C;
		padding: 20px 30px;
		margin-bottom: 20px;
	}
	.upload-wrap > p {
		margin: 5px 0;
	}
	.thmub-wrap {
		height: 130px;
	}
	.list-wrap {
		width: 74%;
		margin: 0 auto;
	}
	.list-wrap > h2 {
		width: 100%;
		text-align: center;
		margin: 50px 0 20px;
	}
	.pic-wrap {
		overflow: hidden;
		height: 350px;
	}
	.img-box {
		position: relative;
		float: left;
		margin: 5px;
		cursor: pointer;
		overflow: hidden;
		background: #E8D9FF;
		padding: 8px;
		font-family: 'Gothic A1', sans-serif;
	}
	.img-box > img {
		width: 100px;
		height: 100px;
	}
	.img-box > .delX {
		position: absolute;
	    right: 0px;
	    top: 0px;
	}
	.img-box > .imgDate {
		font-size: 12px;
		overflow: hidden;
	}
	.img-box > .imgName {
		font-size: 10px;
		overflow: hidden;
		text-align: center;
	}
	.pagination > li {
		float: left;
		margin: 3px;
	}
	.pagination > li > a {
		color: green;
	}
	.modal-wrap {
		display: none;
		position: fixed;
		z-index: 1; 
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto; 
		background-color: rgba(0,0,0,0.4);
	}
	.modal-wrap > .modal-img {
		position: relative;
		margin: 150px auto;
	}
	.modal-wrap > .modal-img > .modalX {
	    position: absolute;
	    right: 5px;
	    top: 5px;
	    cursor: pointer;
	    width: 30px;
	    height: 30px;
	    line-height: 25px;
	    text-align: center;
	    background: white;
	    border: 4px solid #F361A6;
	    border-radius: 50px 50px 50px 50px;
	    box-shadow: 1px 1px 2px grey;
	}
</style>

	<h1>PHOTO</h1>
	<div class="form-wrap">
		<form id="frm" enctype="multipart/form-data">
			<input type="hidden" name='pWriter' value="${Auth}">
			<div class="upload-wrap">
				<p>버튼을 눌러 사진을 추가하세요</p>
				<p>
					<input type="file" name="files" multiple="multiple">
					<input type="submit" value="추가">
					<input type="reset" value="취소">
				</p>
				
				<!-- 썸네일 영역 -->
				<div class="thmub-wrap"></div>
			</div>
		</form>
		
		<!-- DB에 저장된 사진 리스트 -->
		<div class="list-wrap">
			<h2>PHOTO LIST</h2>

			<div class="pic-wrap"></div>
		
			<ul class="pagination"></ul> <!-- 페이지네이션 -->
		
			<div class="modal-wrap"> <!-- 이미지 클릭시 나오는 모달창 -->
				<div class="modal-img"></div>
			</div>
		</div>
	</div>

	<script>
		var nPage = 1; //pagination -> 현재 선택한 페이지
	
		/* 사진 리스트 불러오기 */
		getPhotoList(nPage);
	
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
		
		$("input[type='reset']").click(function(){
			$(".thmub-wrap").empty();
		})
		
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
			var result = confirm("정말 삭제하시겠습니까?");
			
			if(result == true) {
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
			} else {
				return false;
			}
		})
	
		/* 이미지 클릭 시 큰 이미지 나오게 하기 */
		$(document).on("click", ".img-box > img", function(){
			var fullSrc = $(this).nextAll(".delX").attr("data-src");
			var src1 = fullSrc.substring(0, 12);
			var src2 = fullSrc.substring(14);
			var src = src1 + src2;

			var $img = $("<img>").attr("src", "${pageContext.request.contextPath}/photo/display?filename="+src);
			var $modalX = $("<span>").addClass("modalX").append("❌");
			
			$(".modal-img").empty();
			$(".modal-img").append($img).append($modalX);
			$(".modal-wrap").css("display", "block");
			$img.load(function(){
				$(".modal-img").css({"width":$(this).width(), "height":$(this).height()});
			})
		})
		
		/* 큰 이미지에서 X 누르면 사라지기 */
		$(document).on("click", ".modalX", function(){
			$(".modal-wrap").css("display", "none");
		})
		
		/* 페이지 누르면 넘어가기 */
		$(document).on("click", ".pagination > li", function(){
			nPage = $(this).children().text();
			getPhotoList(nPage);
		})
		
		/* 추가된 리스트 뿌리기 */
		function getPhotoList(page) {
			var formData = new FormData();
			formData.append("id", $("input[name='pWriter']").val());
			
			$.ajax({
				url: "list/" + page,
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				success: function(res){
					console.log(res);
 					$(".pic-wrap").empty();
					
 					/* PHOTO LIST */
					$(res.list).each(function(i, obj){
						var $img = $("<img>").attr("src", "${pageContext.request.contextPath}/photo/display?filename=" + obj.pName);
						var $x = $("<span>").addClass("delX").append("❌").attr("data-no", obj.pNo).attr("data-src", obj.pName);
						var $p1 = $("<p>").addClass("imgDate").append(formatDate(obj.pRegdate));
						var $p2 = $("<p>").addClass("imgName").append(obj.pOriginName);
						var $imgDiv = $("<div>").addClass("img-box").append($p1).append($img).append($p2).append($x);
						
						$(".pic-wrap").append($imgDiv);
					})    
					
					/* PAGINATION */
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
			})//ajax
		}//getPhotoList
		
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