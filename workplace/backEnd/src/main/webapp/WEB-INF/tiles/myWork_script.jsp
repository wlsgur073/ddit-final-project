<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myDashboard.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myTask.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myOverlay.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myMailAlert.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myMailReceive.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myMailSend.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myMailTemp.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myMailTrash.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myHistory.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myDocument.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myRequest.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myIssue.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myIssueReply.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myMile.js"></script>
		<script src="<%=request.getContextPath()%>/resources/asserts/js/myWork/myUpload.js"></script>
		<input type="hidden" id="sessionId" value="${userVO.userId}">
		
		<script>
			window.onload = function(){
				summernote_go($('textarea.content'));
				
				if(getCookie('tab')){
					var curTab = getCookie('tab');
					
					if(curTab == "myIssueDetail-tab"){
						document.getElementById('issue-tab').click();
					}else if(curTab == "myTaskDetail-tab"){
						document.getElementById('task-tab').click();
					}else if(curTab == "mail-tab"){
						if("${mail}" == "receiveMail"){
							document.getElementById('mail-tab').click();
							var link = document.location.href.split("?");
							var newLink = link[0] + "#mailReceive-tab";
							history.pushState(null, null, newLink);
						}else if("${mail}" == "sendMail"){
							document.getElementById('mail-tab').click();
							var link = document.location.href.split("?");
							var newLink = link[0] + "#mailSend-tab";
							history.pushState(null, null, newLink);
						}else{
							document.getElementById('mail-tab').click();
							document.getElementById('mailReceive-tab').click();
						}
					}else if(curTab == "mailReceive-tab"){
						document.getElementById('mail-tab').click();
						document.getElementById('mailReceive-tab').click();
					}else{
						if("${mail}" == "receiveMail"){
							document.getElementById('mail-tab').click();
							var link = document.location.href.split("?");
							var newLink = link[0] + "#mailReceive-tab";
							history.pushState(null, null, newLink);
						}else if("${mail}" == "sendMail"){
							document.getElementById('mail-tab').click();
							var link = document.location.href.split("?");
							var newLink = link[0] + "#mailSend-tab";
							history.pushState(null, null, newLink);
						}else{
							document.getElementById(curTab).click();
						}
					}
				} else {
					if("${mail}" == "receiveMail"){
						document.getElementById('mail-tab').click();
						var link = document.location.href.split("?");
						var newLink = link[0] + "#mailReceive-tab";
						history.pushState(null, null, newLink);
					}else if("${mail}" == "sendMail"){
						document.getElementById('mail-tab').click();
						var link = document.location.href.split("?");
						var newLink = link[0] + "#mailSend-tab";
						history.pushState(null, null, newLink);
					}else{
						document.getElementById('home-tab').click();
					}
				}

				$('a[role="tab"]').on('click', function() {
			        var id = this.id;
			        document.cookie = "tab="+id;
			        
			        if(id != 'mail-tab'){
				        var link = document.location.href.split("#");
						var newLink = link[0];
						
						history.pushState(null, null, newLink);
			        }
			    });
				
				if(${from eq 'deniedRegistNoUser'}) {
					alert("존재하지 않는 유저입니다.");
					
					document.getElementById('mailWrite-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailWrite-tab";
					
					history.pushState(null, null, newLink);
				}

				if(${from eq 'deleteMyTaskByTaskNo'}) {
					alert("업무 삭제가 완료되었습니다.");
					
					document.getElementById('task-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#task-tab";
					
					history.pushState(null, null, newLink);
				}
				
				if(${from eq 'deniedRegistFileSize'}) {
					alert("파일용량을 초과하였습니다. 메일 전송이 취소되었습니다.");
					
					document.getElementById('mailWrite-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailWrite-tab";
					
					history.pushState(null, null, newLink);
				}
				
				if(${from eq 'deniedTempRegistFileSize'}) {
					alert("파일용량을 초과하였습니다. 메일 전송이 취소되었습니다.");
					
					document.getElementById('mailTemp-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailTemp-tab";
					
					history.pushState(null, null, newLink);
				}

				if(${from eq 'tempRegistAfter'}) {
					document.getElementById('mailTemp-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailTemp-tab";
					
					history.pushState(null, null, newLink);
				}
				
				if(${from eq 'sendRegistAfter'}) {
					document.getElementById('mailSend-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailSend-tab";
					
					history.pushState(null, null, newLink);
				}

				if(${from eq 'receiveRegistAfter'}) {
					document.getElementById('mailReceive-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailReceive-tab";
					
					history.pushState(null, null, newLink);
				}
				
				if(${from eq 'tempToSendAfter'}) {
					document.getElementById('mailSend-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailSend-tab";
					
					history.pushState(null, null, newLink);
				}
				
				if(${from eq 'tempToMineAfter'}) {
					document.getElementById('mailReceive-tab').click();
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#mailReceive-tab";
					
					history.pushState(null, null, newLink);
				}
				
				$('.mail-tab').on('click', function(){
					var id = this.id;
					
					var link = document.location.href.split("#");
					var newLink = link[0] + "#" + id;
					
					history.pushState(null, null, newLink);
				});
				
				var link = document.location.href.split("#");
				if(link[1]){
					document.getElementById(link[1]).click();
				}
				
				$("#myWorkMenu").on("click", function(){
					document.getElementById('home-tab').click();
				});
				
				$("#mail-tab").on("click", function(){
					document.getElementById('mailReceive-tab').click();
				});
		   	}
			
			function getCookie(name) {
				let matches = document.cookie.match(new RegExp(
					"(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
				));
				return matches ? decodeURIComponent(matches[1]) : undefined;
			}
		</script>
	</body>
</html>