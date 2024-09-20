<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			#myPopOverlay {
				position: fixed;
				display: none;
				width: 500px;
				height: 100%;
				top: 0;
				right: 0;
				bottom: 0;
				border: 1px solid #ededed;
				background-color: white;
				z-index: 200;
			}
		</style>
	</head>
	<body>
		<!-- 오버레이 시작 -->
		<div id="myPopOverlay">
			<div class="row" id="fadeInContent">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title row d-flex justify-content-between">
							<h5 class="title">
									<i class="fa fa-clone"></i> <span class="task-bold task-sm">메일 전송</span>
							</h5>
							<div class="clearfix">
								<button onclick="myOverlayOff('#myPopOverlay')" class="btn btn-primary btn-sm">X</button>
							</div>
						</div>
						<div class="x_content">
							<div class="row">
								<!-- 내용저장 -->
								<div class="x_content row">
									<div class="col-sm-12">
										<form enctype="multipart/form-data" role="form" method="post" action="<%=request.getContextPath()%>/app/myWork/mailRegist" name="overlayMailRegistForm" class="form-horizontal form-label-left">
											<div class="inbox-body">
												<input type="hidden" id="userFrom" name="userFrom" value="${userVO.nickname}">
												<input type="hidden" id="overlayDist" name="dist" value="">
												<input type="text" id="userTo" name="userTo" class="form-control form-control-sm mt-3 sendOverlayUserTo" placeholder="받는 사람:" value="" readonly>
												<br>
												<input type="text" id="sendOverlayTitle" name="title" class="form-control form-control-sm sendOverlayTitle" placeholder="제목:">
												<br>
												<textarea id="sendOverlayContent" name="content" rows="10" class="form-control content sendOverlayContent overlaySendContent" placeholder="내용을 입력하세요." style="display:none;"></textarea>
												<br>
											</div>
											<div class="card">
												<div class="card-header" style="background:#f7f7f7;border-bottom:none;">
													<button class="btn btn-sm btn-primary" onclick="addOverlayFile_go();" type="button" id="addFileBtn">파일첨부</button>
												</div>
												<div class="card-body overlayFileInput"></div>
												<div class="card-footer"></div>
											</div>
										</form>
									</div>
									<div class="col-sm-12 mt-3 pr-1" style="text-align:right;">
										<button id="mailSendButton" type="button" class="btn btn-sm btn-primary" onclick="mailOverlayRegist_go('send');return false;">전송</button>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 오버레이 끝 -->
		
		<script>
			window.addEventListener('load', function() {
				var memoryCapacity = null;
				var userId = $("#userFrom").val();
				$.ajax({
					url: "<%=request.getContextPath()%>/app/myWork/getMemoryCapacity",
					type: "get",
					async: false,
					data: {
						"userId" : userId
					},
					success: function(data){
						memoryCapacity = data;
					},
					error: function(error){
						/* alert(error.status); */
					}
				});
				
				$('.overlayFileInput').on('change', 'input[type="file"]', function(event){
					if(this.files[0].size > memoryCapacity * 1024 * 1024){
						alert("파일 용량은 " + memoryCapacity + "MB 이하만 가능합니다.");
						this.value="";
						$(this).click();
						return;
					}
				});
			});
		
			var dataNum = 0;
		
			function addOverlayFile_go(){
				if($('input[class="sendOverlayAttachFile"]').length >= 5){
					alert("파일 추가는 5개 까지만 가능합니다.");
					return;
				}
			
				var div = $('<div>').addClass("inputRow").addClass("mb-1").attr("data-no", dataNum);
				var input = $('<input>').attr({"type":"file", "name":"attachFile", "class":"sendOverlayAttachFile"}).css("display", "inline");
				
				div.append(input).append("<button type='button' class='badge bg-red' onclick='removeOverlayAttachFile_go(" + dataNum + ")' style='border:0;outline:0;'>X</button>");
				$('.overlayFileInput').append(div);
				
				dataNum++;
			}
		
			function removeOverlayAttachFile_go(dataNum){
				$('div[data-no="' + dataNum + '"]').remove();
			}
		
			function mailOverlayRegist_go(dist){
				
				if(dist == "temp"){
					$("#overlayDist").val("temp");
				}else if(dist == "send"){
					$("#overlayDist").val("send");
				}
				
				var files = $('input[class="sendOverlayAttachFile"]');
				for(var file of files){
					console.log(file.name + " : " + file.value);
					if(file.value == ""){
						alert("파일을 선택해주세요.");
						file.focus();
						file.click();
						return;
					}
				}
			
				if($("input[id='sendOverlayTitle']").val() == ""){
					alert("제목을 입력해주세요.");
					$("input[id='sendOverlayTitle']").focus();
					return;
				}
				if($("textarea[id='sendOverlayContent']").val() == ""){
					alert("내용을 입력해주세요.");
					$("textarea[id='sendOverlayContent']").focus();
					return;
				}
				
				if(dist == "send"){
					var receiverId = $(".sendOverlayUserTo").val();
					var nickname = "${userVO.nickname}";
					mailAlarm(nickname, receiverId);
				}
				
				document.overlayMailRegistForm.submit();
			}
		</script>
	</body>
</html>
	