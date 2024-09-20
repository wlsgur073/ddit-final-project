<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!-- 내 작업 히스토리관리입니다. -->
<div id="example">
    <div id="myHistory"></div>
</div>
<!-- 내 작업 히스토리관리입니다. 끝 -->

<script id="myHistory-template" type="text/x-kendo-template">
	<div class="media event">
		<img src="<%=request.getContextPath()%>/user/getPictureById?userId=#:userId#" alt="img" style="margin:5px 10px 5px 3px;height:50px;width:50px;border-radius:100%;">
		<div class="media-body">
			<p class="mt-1 text-truncate" style="max-width:930px;">#:msg#</p>
			<p>#:regDate#</p>
		</div>
		<button type="button" class="btn btn-sm btn-dark mt-1" style="height:50px;width:50px;" onclick="history_go('#:url#', '#:projNo#');">이동</button>
	</div>	
</script>

<script id="myHistoryDashboard-template" type="text/x-kendo-template">
	<div class="media event row pl-0 pr-0">
		<img src="<%=request.getContextPath()%>/user/getPictureById?userId=#:userId#" alt="img" style="margin:5px 10px 5px 3px;height:50px;width:50px;border-radius:100%;">
		<div class="media-body" style="max-width:345px;">
			<p class="mt-1 text-truncate" style="max-width:320px;">#:msg#</p>
			<p>#:regDate#</p>
		</div>
		<div class="col-md-1">
			<button type="button" class="btn btn-sm btn-dark mt-1" style="height:50px;width:50px;" onclick="history_go('#:url#', '#:projNo#');">이동</button>
		</div>
	</div>	
</script>

<script>
	function history_go(url, projNo){
		if(url == "PROJECT"){
			location.href="<%=request.getContextPath()%>/app/project/main?projNo=" + projNo;
		}else if(url == "COLLABO"){
			location.href="<%=request.getContextPath()%>/app/collabo/main?cprojNo=" + projNo;
		}else if(url == "TASK"){
			document.getElementById('task-tab').click();
		}else if(url == "ISSUE"){
			document.getElementById('issue-tab').click();
		}else if(url == "ISSUE_RES"){
			document.getElementById('issue-tab').click();
		}else if(url == "CHATROOM"){
			getOverlayTemplateChatList('chatTemplate');
		}else if(url == "SENDMAIL"){
			document.getElementById('mail-tab').click();
			document.getElementById('mailSend-tab').click();
		}else if(url == "SENDMINEMAIL"){
			document.getElementById('mail-tab').click();
			document.getElementById('mailReceive-tab').click();
		}else if(url == "TEMPMAIL"){
			document.getElementById('mail-tab').click();
			document.getElementById('mailTemp-tab').click();
		}else if(url == "TEMPMINEMAIL"){
			document.getElementById('mail-tab').click();
			document.getElementById('mailTemp-tab').click();
		}else if(url == "RECEIVEMAIL"){
			document.getElementById('mail-tab').click();
			document.getElementById('mailReceive-tab').click();
		}else if(url == "TRASHMAIL"){
			document.getElementById('mail-tab').click();
			document.getElementById('mailTrash-tab').click();
		}else if(url == "MILE"){
			document.getElementById('issue-tab').click();
		}else if(url == "DOCUMENT"){
			document.getElementById('document-tab').click();
		}
	}
</script>