<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<div id="sendMail" class="row">
	<div class="col-md-4">
		<div id="sendMailList" class="mail_list_column" style="height:auto;"></div>	
	</div>
	<div class="col-md-8">
		<div id="sendMailDetail" class="mail_view border-left-0" style="height:auto;">
		</div>
	</div>
</div>

<script id="sendMailList-template" type="text/x-kendo-template">
	<div class="mail_list m-0 border-bottom-0" onclick="location.href='javascript:sendMailDetail(#:mailNo#);'" style="cursor:pointer;height:40px;">
		<div class="left">
			# if(userFrom == userTo){ #
				<input class="sendCheck" type="checkbox" value="#:mailNo#" dist="mineMail">
			# }else if(userFrom != userTo){ #
				<input class="sendCheck" type="checkbox" value="#:mailNo#" dist="sendMail">
			# } #
			
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
<script type="text/x-handlebars-template"  id="send-mail-detail-template" >
	<div class="inbox-body">
		<div class="mail_heading row">
			<div class="col-md-8">
				<button type="button" class="btn btn-sm btn-dark" onclick="deleteSendMailOne('{{mailNo}}', '{{userFrom}}', '{{userTo}}');"><i class="fa fa-times-circle-o"></i>&nbsp;&nbsp;삭제</button>
			</div>
			<div class="col-md-4 text-right">
				<p class="date">{{regDate}}</p>
			</div>
			<div class="col-md-12">
				<h4 class="text-truncate"> {{title}}</h4>
			</div>
		</div>
		<div class="sender-info">
			<div class="row">
				<div class="col-md-12 text-right">
					<strong>받은 사람 : {{userTo}}</strong>
				</div>
				<div class="col-md-12 text-right">
					<strong>읽음 확인 : {{status}}</strong>
				</div>
			</div>
		</div>
		<br>
		<div class="view-mail" style="word-break:break-all;min-height:280px;">
			{{{content}}}
		</div>
		<div class="attachment">
			{{#if attachList}}
				<p>
					<span><i class="fa fa-paperclip"></i> {{attachList.length}} attachments - </span>
					<a href="javascript:allSendAttachDownload();">all downloads</a>		
				</p>
			{{/if}}
			
			<div class="row">
				{{#each attachList}}
					<a href="<%=request.getContextPath()%>/app/myWork/attachDownload?attachNo={{attachNo}}" class="col-md-2 row m-2 p-1 rounded bg-warning String downloadSendAttaches">
						<div class="col-3 p-0 text-center text-dark font-weight-bold">
							<h2><i class="fa fa-paperclip"></i></h2>						
						</div>
						<div class="col-9 p-0">
							<div class="col-12 m-0 p-0 text-dark" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
								{{fileName}}
							</div>					
							<div class="col-12 m-0 p-0 text-dark">
								{{fileSize}} KB			
							</div>		
						</div>
					</a>
				{{/each}}
			</div>
		</div>
	</div>
</script>

<script>
	function printData(data, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(data);
		target.html('').html(html);
	}
	
	function sendMailDetail(mailNo){
		$.ajax({
			url : "<%=request.getContextPath()%>/app/myWork/getMailDetail?mailNo=" + mailNo,
			type : "get",
			dataType : "json",
			success : function(data){
				if(data.status == "A801"){
					data.status = "안읽음";
				}else{
					data.status = "읽음";
				}
				printData(data, $('#sendMailDetail'), $('#send-mail-detail-template'));
			},
			error : function(error){
				/* alert(error.status); */
			}
		});
	}
	
	function sendAllCheck(){
		all = $("#sendAllCheckButton").is(":checked");
		
		if(all){
			$(".sendCheck").prop("checked", true);
		}else{
			$(".sendCheck").prop("checked", false);
		}
	}
	
	function deleteSendMailOne(mailNo, userFrom, userTo){
		var mailDist = null;
		if(userFrom == userTo){
			mailDist = "mineMail";
		}else{
			mailDist = "sendMail";
		}
		
		deleteOneConfirm = confirm("삭제하시겠습니까?");
		
		if(deleteOneConfirm){
			$.ajax({
				url : "<%=request.getContextPath()%>/app/myWork/deleteMailToTrash",
				type: "get",
				data: {
					"mailNo"   : mailNo,
					"mailDist" : mailDist
				},
				success: function(data){
					alert("삭제를 완료하였습니다.");
					$('#receiveMailList').data("kendoGrid").dataSource.read();
					$('#sendMailList').data("kendoGrid").dataSource.read();
					$('#trashMailList').data("kendoGrid").dataSource.read();
				},
				error: function(error){
					/* alert(error.status); */
				}
			});
		}
	}
	
	function deleteSendMailAll(){
		if($(".sendCheck:checked").length == 0){
			alert("삭제할 메일을 선택해주세요.");
			return;
		}
		
		deleteAllConfirm = confirm("삭제하시겠습니까?");
		var mailNo = "";
		var mailDist = "";
		
		if(deleteAllConfirm){
			
			$(".sendCheck:checked").each(function(){
				mailNo += $(this).val() + ",";
				mailDist += $(this).attr("dist") + ",";
			});
			
			$.ajax({
				url : "<%=request.getContextPath()%>/app/myWork/deleteMailToTrashAll",
				type: "get",
				data: {
					"mailNo"   : mailNo,
					"mailDist" : mailDist	
				},
				success: function(data){
					$("#sendAllCheckButton").prop("checked", false);
					alert("삭제를 완료하였습니다.");
					$('#receiveMailList').data("kendoGrid").dataSource.read();
					$('#sendMailList').data("kendoGrid").dataSource.read();
					$('#trashMailList').data("kendoGrid").dataSource.read();
				},
				error: function(error){
					/* alert(error.status); */
				}
			});
		}
	}
	
	function allSendAttachDownload(){
		var attachFileCount = $('.downloadSendAttaches').length;
		for(var i = 0; i < attachFileCount; i++){
			$('.downloadSendAttaches').get(i).click();
		}
	}
</script>