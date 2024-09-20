<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<div class="row">
	<div class="col-sm-12 mail_view border-left-0">
		<div class="col-sm-12">
			<div class="col-md-12 col-sm-12" style="text-align: right;">
				<button type="button" class="btn btn-sm btn-dark"
					onclick="mailRegist_go('temp');">임시저장</button>
				<button id="mailSendButton" type="button"
					class="btn btn-sm btn-primary" onclick="mailRegist_go('send');">전송</button>
			</div>
		</div>
		<div class="col-sm-12">
			<!-- 메일 작성 시작 -->
			<form enctype="multipart/form-data" role="form" method="post"
				action="<%=request.getContextPath()%>/app/myWork/mailRegist"
				name="mailRegistForm" class="form-horizontal form-label-left">
				<div class="inbox-body">
					<input type="hidden" id="userFrom" name="userFrom"
						value="${userVO.nickname}"> <input type="hidden" id="dist"
						name="dist" value=""> <input type="text" id=sendUserTo
						name="userTo" class="form-control form-control-sm mt-3"
						placeholder="받는 사람:"> <br> <input type="text"
						id="sendTitle" name="title" class="form-control form-control-sm"
						placeholder="제목:"> <br>
					<textarea id="sendContent" name="content" rows="10"
						class="form-control content" placeholder="내용을 입력하세요."
						style="display: none;"></textarea>
					<br>
				</div>
				<div class="card">
					<div class="card-header"
						style="background: #f7f7f7; border-bottom: none;">
						<button class="btn btn-sm btn-primary" onclick="addFile_go();"
							type="button" id="addFileBtn">파일첨부</button>
					</div>
					<div class="card-body fileInput"></div>
					<div class="card-footer"></div>
				</div>
			</form>
			<!-- 메일 작성 끝 -->
		</div>
	</div>
</div>

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
		
		$('.fileInput').on('change', 'input[type="file"]', function(event){
			if(this.files[0].size > memoryCapacity * 1024 * 1024){
				alert("파일 용량은 " + memoryCapacity + "MB 이하만 가능합니다.");
				this.value="";
				$(this).click();
				return;
			}
		});
	});

	var dataNum = 0;

	function addFile_go(){
		if($('input[class="sendAttachFile"]').length >= 5){
			alert("파일 추가는 5개 까지만 가능합니다.");
			return;
		}
	
		var div = $('<div>').addClass("inputRow").addClass("mb-1").attr("rData-no", dataNum);
		var input = $('<input>').attr({"type":"file", "name":"attachFile", "class":"sendAttachFile"}).css("display", "inline");
		
		div.append(input).append("<button type='button' class='badge bg-red' onclick='remove_go(" + dataNum + ")' style='border:0;outline:0;'>X</button>");
		$('.fileInput').append(div);
		
		dataNum++;
	}

	function remove_go(dataNum){
		$('div[rData-no="' + dataNum + '"]').remove();
	}

	function mailRegist_go(dist){
		var userFrom = "${userVO.nickname}";
		var userTo = $("#sendUserTo").val();
		
		if(dist == "temp"){
			if(userFrom == userTo){
				$("#dist").val("tempMine");
			}else{
				$("#dist").val("temp");
			}
		}else if(dist == "send"){
			if(userFrom == userTo){
				$("#dist").val("mine");
			}else{
				$("#dist").val("send");
			}
		}
		
		var files = $('input[class="sendAttachFile"]');
		for(var file of files){
			console.log(file.name + " : " + file.value);
			if(file.value == ""){
				alert("파일을 선택해주세요.");
				file.focus();
				file.click();
				return;
			}
		}
	
		if($("input[id='sendTitle']").val() == ""){
			alert("제목을 입력해주세요.");
			$("input[id='sendTitle']").focus();
			return;
		}
		if($("input[id='sendUserTo']").val() == ""){
			alert("수신자 메일을 입력해주세요.");
			$("input[id='sendUserTo']").focus();
			return;
		}
		if($("textarea[id='sendContent']").val() == ""){
			alert("내용을 입력해주세요.");
			$("textarea[id='sendContent']").focus();
			return;
		}
		var result3 = null;
		$.ajax({
			type: "get",
			url : "<%=request.getContextPath()%>/app/myWork/userCheck",
			data: { "userTo"  : userTo },
			async: false,
			success: function(result){
				if(result == "no"){
					alert("존재하지 않는 회원입니다.");
					result3 = result;
				}
			},
			error: function(error){
				/* alert(error.status); */
			}
		});
		
		if(dist == "send"){
			var receiverId = $("#sendUserTo").val();
			var nickname = "${userVO.nickname}";
			mailAlarm(nickname, receiverId);
		}
		
		if(result3 != "no"){
			document.mailRegistForm.submit();			
		}
	}
</script>
