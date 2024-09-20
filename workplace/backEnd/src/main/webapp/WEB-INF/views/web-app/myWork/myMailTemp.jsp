<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<div id="tempMail" class="row">
	<div class="col-md-4">
		<div id="tempMailList" class="mail_list_column" style="height:auto;"></div>	
	</div>
	<div class="col-md-8">
		<div id="tempMailDetail" class="mail_view border-left-0" style="height:auto;">
		</div>
	</div>
</div>

<script id="tempMailList-template" type="text/x-kendo-template">
	<div class="mail_list m-0 border-bottom-0" onclick="location.href='javascript:tempMailDetail(#:mailNo#);'" style="cursor:pointer;height:40px;">
		<div class="left">
			<input class="tempCheck" type="checkbox" value="#:mailNo#">
			# if(attachList.length != 0){ #
				<i class="fa fa-paperclip"></i>
			# } #
		</div>
		<div class="right" style="height:40px;">
			<h3 class="m-0"><p class="m-0 d-inline-block text-truncate" style="padding-bottom:6px;max-width:200px;">#:userTo#</p><small>#:regDate#</small></h3>
			<p class="d-inline-block text-truncate" style="max-width:300px;">#:title#</p>
		</div>
	</div>
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template"  id="temp-mail-detail-template" >
	<div class="inbox-body">
		<form enctype="multipart/form-data" role="form" method="post" action="<%=request.getContextPath()%>/app/myWork/tempMailRegist" name="tempMailRegistForm" class="form-horizontal form-label-left">
		<div class="mail_heading row">
			<div class="col-md-8">
				<div class="btn-group">
					<button type="button" class="btn btn-sm btn-dark" onclick="tempMailRegist_go();"><i class="fa fa-reply"></i>&nbsp;&nbsp;전송</button>	
					<button type="button" class="btn btn-sm btn-dark btn-append" onclick="deleteTempMailOne({{mailNo}});"><i class="fa fa-times-circle-o"></i>&nbsp;&nbsp;삭제</button>
				</div>
			</div>
			<input type="hidden" id="tempUserFrom" name="userFrom" value="${userVO.nickname}">
			<input type="hidden" id="mailNo" name="mailNo" value="{{mailNo}}">
			<input type="hidden" id="tempDist" name="dist" value="">
			<div class="col-md-4 text-right">
				<p class="date">{{regDate}}</p>
			</div>
			<div class="col-md-12">
				<input type="text" id="tempTitle" name="title" class="form-control form-control-sm" placeholder="제목:" value="{{title}}">
			</div>
		</div>
		<div class="sender-info">
			<div class="row">
				<div class="col-md-12 text-right">
					<input type="text" id="tempUserTo" name="userTo" class="form-control form-control-sm mt-3" placeholder="받는 사람:" value="{{userTo}}" readonly>
				</div>
			</div>
		</div>
		<br>
		<div class="view-mail" style="min-height:280px;">
			<textarea id="tempContent" name="content" rows="10" class="form-control content" placeholder="내용을 입력하세요." style="display:none;">{{{content}}}</textarea>
		</div>
		<div class="card">
			<div class="card-header" style="background:#f7f7f7;border-bottom:none;">
				<button class="btn btn-sm btn-primary" onclick="addTempFile_go();" type="button" id="addFileBtn">파일첨부</button>
			</div>
			<div class="card-body tempFileInput">
				<ul class="mailbox-attachments p-0 m-0 align-items-stretch clearfix">
				{{#each attachList}}
					<li class="col-md-12 row ml-0 mb-2 p-1 rounded bg-warning String attach-item thumb{{attachNo}}" file-name="{{fileName}}" target-attachNo="{{attachNo}}" style="width:281px;">
						<div class="col-10 p-0" style="padding-top:1px;">
							<a name="attachedFile" attach-fileName="{{fileName}}" attach-no="{{attachNo}}" href="<%=request.getContextPath()%>/app/myWork/attachDownload?attachNo={{attachNo}}" class="col-12 m-0 p-0 text-dark font-weight-bold downloadTempAttaches" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
								{{fileName}}&nbsp;-&nbsp;{{fileSize}} KB
							</a>
						</div>
						<div class="col-2 p-0 text-center font-weight-bold" style="padding-top:1px;">
							<a href="javascript:removeTempFiles('thumb{{attachNo}}');" class="bg-danger rounded text-white" style="padding:1px 5px 2px 5px;">x</a>						
						</div>
					</li><br><br>
				{{/each}}
				</ul>
			</div>
			<div class="card-footer"></div>
		</div>
		</form>
	</div>
</script>

<script>
	window.addEventListener('load', function() {
		$('.tempFileInput').on('change', 'input[type="file"]', function(event){
			if(this.files[0].size > 1024 * 1024 * 10){
				alert("파일 용량은 10MB 이하만 가능합니다.");
				this.value="";
				$(this).click();
				return;
			}
		});
	});

	function removeTempFiles(className){
		var li = $('li.' + className);
		if(!confirm("" + " 파일을 정말 삭제하시겠습니까?")){
			return;
		}
		li.remove();
		
		var input = $('<input>').attr({
			"type" : "hidden",
			"name" : "deleteFile",
			"value" : li.attr("target-attachNo")
		});
		
		$('form[role="form"]').prepend(input);
	}
	
	var tempDataNum = 0;
	
	function addTempFile_go(){
		
		var attachedFile = $('a[name="attachedFile"]').length;		//기존파일 개수
		var inputFiles = $('input[class="tempAttachFile"]').length;	//추가파일 개수
		var attachCount = attachedFile + inputFiles;				//전체파일 개수
		if(attachCount >= 5){
			alert("파일 추가는 5개 까지만 가능합니다.");
			return;
		}
		
		
		if($('input[class="tempAttachFile"]').length >= 5){
			alert("파일 추가는 5개 까지만 가능합니다.");
			return;
		}
	
		var div = $('<div>').addClass("inputRow").addClass("mb-1").attr("tData-no", tempDataNum);
		var input = $('<input>').attr({"type":"file", "name":"attachFile", "class":"tempAttachFile"}).css("display", "inline");
		
		div.append(input).append("<button type='button' class='badge bg-red' onclick='removeTempFile_go(" + tempDataNum + ")' style='border:0;outline:0;'>X</button>");
		$('.tempFileInput').append(div);
		
		tempDataNum++;
	}
	
	function removeTempFile_go(tempDataNum){
		$('div[tData-no="' + tempDataNum + '"]').remove();
	}

	function printData(data, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(data);
		target.html('').html(html);
	}
	
	function tempMailDetail(mailNo){
		$.ajax({
			url : "<%=request.getContextPath()%>/app/myWork/getMailDetail?mailNo=" + mailNo,
			type : "get",
			dataType : "json",
			success : function(data){
				printData(data, $('#tempMailDetail'), $('#temp-mail-detail-template'));
				summernote_go($('textarea.content'));
			},
			error : function(error){
				/* alert(error.status); */
			}
		});
	}
	
	function tempAllCheck(){
		all = $("#tempAllCheckButton").is(":checked");
		
		if(all){
			$(".tempCheck").prop("checked", true);
		}else{
			$(".tempCheck").prop("checked", false);
		}
	}
	
	function deleteTempMailOne(mailNo){
		deleteOneConfirm = confirm("임시저장된 메일은 완전히 삭제됩니다.\n삭제하시겠습니까?");
		
		if(deleteOneConfirm){
			$.ajax({
				url : "<%=request.getContextPath()%>/app/myWork/deleteMail",
				type: "get",
				data: {
					"mailNo"  : mailNo,
					"mailDist": "tempMail"
				},
				success: function(data){
					alert("삭제를 완료하였습니다.");
					$('#tempMailList').data("kendoGrid").dataSource.read();
				},
				error: function(error){
					/* alert(error.status); */
				}
			});
		}
	}
	
	function deleteTempMailAll(){
		if($(".tempCheck:checked").length == 0){
			alert("삭제할 메일을 선택해주세요.");
			return;
		}
		
		deleteAllConfirm = confirm("임시저장된 메일은 완전히 삭제됩니다.\n삭제하시겠습니까?");
		var mailNo = "";
		var mailDist = "";
		
		if(deleteAllConfirm){
			$(".tempCheck:checked").each(function(){
				mailNo += $(this).val() + ",";
				mailDist += "tempMail" + ",";
			});
			
			$.ajax({
				url : "<%=request.getContextPath()%>/app/myWork/deleteMailAll",
				type: "get",
				data: {
					"mailNo"  : mailNo,
					"mailDist": mailDist
				},
				success: function(data){
					$("#tempAllCheckButton").prop("checked", false);
					alert("삭제를 완료하였습니다.");
					
					$('#tempMailList').data("kendoGrid").dataSource.read();
				},
				error: function(error){
					/* alert(error.status); */
				}
			});
		}
	}
	
	function allTempAttachDownload(){
		var attachFileCount = $('.downloadTempAttaches').length;
		for(var i = 0; i < attachFileCount; i++){
			$('.downloadTempAttaches').get(i).click();
		}
	}
	
	function tempMailRegist_go(){
		var tempUserFrom = $("#tempUserFrom").val();
		var tempUserTo = $("#tempUserTo").val();
		
		if(tempUserFrom == tempUserTo){
			$("#tempDist").val("mine");
		}else if(tempUserFrom != tempUserTo){
			$("#tempDist").val("send");
		}
		
		var files = $('input[class="tempAttachFile"]');
		for(var file of files){
			console.log(file.name + " : " + file.value);
			if(file.value == ""){
				alert("파일을 선택해주세요.");
				file.focus();
				file.click();
				return;
			}
		}
		if($("input[id='tempTitle']").val() == ""){
			alert("제목을 입력해주세요.");
			$("input[id='tempTitle']").focus();
			return;
		}
		if($("input[id='tempUserTo']").val() == ""){
			alert("수신자 메일을 입력해주세요.");
			$("input[id='tempUserTo']").focus();
			return;
		}
		if($("textarea[id='tempContent']").val() == ""){
			alert("내용을 입력해주세요.");
			$("textarea[id='tempContent']").focus();
			return;
		}
		
		var receiverId = $("#tempUserTo").val();
		var nickname = "${userVO.nickname}";
		mailAlarm(nickname, receiverId);
		
		document.tempMailRegistForm.submit();
	}
</script>