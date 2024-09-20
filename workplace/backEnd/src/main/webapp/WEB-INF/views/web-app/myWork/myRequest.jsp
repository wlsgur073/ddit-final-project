<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true"%>

<div id="RequestDiv" class="row">
	<div class="col-md-6">
		<h3>요청받은 리퀘스트</h3>
		<div id="yourRequest"></div>	
	</div>
	<div class="col-md-6">
		<h3>요청한 리퀘스트</h3>	
	    <div id="myRequest"></div>	
	</div>
</div>

<script id="yourRequest-template" type="text/x-kendo-template">
	<div class="row">
		<img class="pt-1 pl-2 pr-2" src="<%=request.getContextPath()%>/user/getPictureById?userId=#:userFrom#" alt="img" style="height:64px;width:64px;border-radius:100%;">
		<div class="col-8 p-0">
			<div class="row" style="margin:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">#:title#</div>
			<div class="row" style="margin:2px;">
				<div class="col-6 p-0">#:projNo#</div>
				<div class="col-6 p-0 text-right">#:regDate#&nbsp;&nbsp;&nbsp;</div>
			</div>
			<div class="row" style="margin:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">#:content#</div>
		</div>
			# if(status == "대기"){ #
				<button type="button" id="process" class="btn btn-sm btn-success mt-2 m-0" data-toggle="modal" data-target=".requestProcessModal#:reqNo#" style="height:48px;width:48px;">대기</button>
			# }else if(status == "승낙"){ #
				<button type="button" id="ok" class="btn btn-sm btn-primary mt-2 m-0" style="height:48px;width:48px;cursor:default;">승낙<br>완료</button>
			# }else if(status == "거절"){ #
				<button type="button" id="no" class="btn btn-sm btn-danger mt-2 m-0" data-toggle="modal" data-target=".requestRejectModal#:reqNo#" style="height:48px;width:48px;">거절<br>완료</button>
			# } #
	</div>

	<div class="modal fade no-modal-sm requestRejectModal#:reqNo#" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel2">#:status#</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<h4>요청자 : #:userFrom#</h4>
					<p class="float-right">#:regDate#</p>
					<br>
					<p>요청 : #:title#</p>
					<p>답변 : #:reqRes#</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<div id="requestModal#:reqNo#" class="modal fade process-modal-lg requestProcessModal#:reqNo#" tabindex="-1" role="dialog" aria-modal="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">#:title#</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row" style="margin:2px;">
						<div class="col-6 p-0">#:projNo#</div>
						<div class="col-6 p-0 text-right">#:regDate#</div>
					</div>
					<div class="row" style="margin:2px;">#:content#</div>
				</div>
				<div class="modal-body">
					<div class="row">
						<button id="reject#:reqNo#" type="button" class="btn btn-danger" onclick="reject('#:reqNo#');">거절</button>
						<button id="accept#:reqNo#" type="button" class="btn btn-primary" onclick="accept('#:reqNo#');">승낙</button>
						<button id="cancel#:reqNo#" type="button" class="btn btn-primary" onclick="cancel('#:reqNo#');" style="display:none;">취소</button>
					</div>
					<div id="taZone#:reqNo#" class="row mr-3" style="display:none;">
						<textarea id="ta#:reqNo#" rows="10" class="form-control" style="width:100%;" placeholder="1~500글자 이내로 거절 사유를 입력해주세요."></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<script id="myRequest-template" type="text/x-kendo-template">
	<div class="row">
		<img class="pt-1 pl-2 pr-2" src="<%=request.getContextPath()%>/user/getPictureById?userId=#:userFrom#" alt="img" style="height:64px;width:64px;border-radius:100%;">
		<div class="col-8 p-0">
			<div class="row" style="margin:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">#:title#</div>
			<div class="row" style="margin:2px;">
				<div class="col-6 p-0">#:projNo#</div>
				<div class="col-6 p-0 text-right">#:regDate#&nbsp;&nbsp;&nbsp;</div>
			</div>
			<div class="row" style="margin:2px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">#:content#</div>
		</div>
			# if(status == "대기"){ #
				<button type="button" class="btn btn-sm btn-secondary mt-2 m-0" style="height:48px;width:48px;cursor:default;">대기</button>
			# }else if(status == "승낙"){ #
				<button type="button" class="btn btn-sm btn-primary mt-2 m-0" style="height:48px;width:48px;cursor:default;">승낙</button>
			# }else if(status == "거절"){ #
				<button type="button" class="btn btn-sm btn-danger mt-2 m-0" style="height:48px;width:48px;" data-toggle="modal" data-target=".requestRejectModal#:reqNo#">거절</button>
			# } #
		</div>

	<div class="modal fade no-modal-sm requestRejectModal#:reqNo#" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel2">#:status#</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<h4>답변자 : #:userTo#</h4>
					<p class="float-right">#:regDate#</p>
					<br>
					<p>요청 : #:title#</p>
					<p>답변 : #:reqRes#</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>	
</script>

<script>
	function accept(reqNo){
		$("#taZone" + reqNo).css("display", "none");
		
		var acceptConfirm = confirm("승낙하시겠습니까?");
		if(acceptConfirm){
			$.ajax({
				type: "post",
				url : "<%=request.getContextPath()%>/app/myWork/changeRequestStatus",
				data: { "reqNo"  : reqNo,
						"status" : "A702",
						"reqRes" : ""
					  },
						
				dataType:'json',
				success: function(msg){
					alert(msg);
					
					$("#requestModal" + reqNo).modal("hide");
					
					$('#yourRequest').data('kendoGrid').dataSource.read();
					$("#yourStatus-category").data("kendoDropDownList").dataSource.read();

					$('#yourRequestDashboard').data('kendoGrid').dataSource.read();
				},
				error: function(error){
					alert(error.status);
				}
			});
		}
	}
	
	function reject(reqNo){
		$("#cancel" + reqNo).css("display", "block");
		$("#taZone" + reqNo).css("display", "block");
		var reqRes = $('#ta' + reqNo).val();
		
		if(reqRes.length < 1 || reqRes.length > 500){
			$("#accept" + reqNo).css("display", "none");
			$("#taZone" + reqNo).css("display", "block");
		}else{
			rejectConfirm = confirm("거절하시겠습니까?");
			if(rejectConfirm){
				$.ajax({
					type: "post",
					url : "<%=request.getContextPath()%>/app/myWork/changeRequestStatus",
					data: { "reqNo"  : reqNo,
							"status" : "A703",
							"reqRes" : reqRes
						  },
					dataType:'json',
					success: function(msg){
						alert(msg);
						
						$("#requestModal" + reqNo).modal("hide");
						
						$('#yourRequest').data('kendoGrid').dataSource.read();
						$("#yourStatus-category").data("kendoDropDownList").dataSource.read();

						$('#yourRequestDashboard').data('kendoGrid').dataSource.read();
					},
					error: function(error){
						alert(error.status);
					}
				});	
			}	
		}
	}
	
	function cancel(reqNo){
		$("#ta" + reqNo).val("");
		$("#taZone" + reqNo).css("display", "none");
		$("#accept" + reqNo).css("display", "block");
		$("#cancel" + reqNo).css("display", "none");
	}
</script>