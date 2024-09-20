<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<title>내 업무</title>

<head></head>

<body>
<!-- page content -->
<div class="right_col" role="main">
	<!-- 내 작업 바디 시작 -->
	<div class="x_panel">
		<div class="x_title">
			<h2><i class="fa fa-edit"></i> 내 작업</h2>
			<div class="clearfix"></div>
		</div>

		<div class="x_content">
			<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
				<li class="nav-item">
					<a id="home-tab" class="nav-link" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="false" onclick="readMyDashboard('${userVO.userId}', '${userVO.nickname}');">대시보드</a>
				</li>
				<li class="nav-item">
					<a id="task-tab" class="nav-link" data-toggle="tab" href="#task" role="tab" aria-controls="task" aria-selected="false" onclick="readMyTask('${userVO.userId}', '${userVO.nickname}');">업무</a>
				</li>
				<li class="nav-item">
					<a id="issue-tab" class="nav-link" data-toggle="tab" href="#issue" role="tab" aria-controls="issue" aria-selected="false" onclick="readMyMile('${userVO.userId}');readMyIssue('${userVO.userId}');">이슈</a>
				</li>
				<li class="nav-item">
					<a id="mail-tab" class="nav-link" data-toggle="tab" href="#mail" role="tab" aria-controls="mail" aria-selected="false" onclick="receiveMailBox('${userVO.nickname}');">메일</a>
				</li>
				<li class="nav-item">
					<a id="document-tab" class="nav-link" data-toggle="tab" href="#document" role="tab" aria-controls="document" aria-selected="false" onclick="readMyDocument('${userVO.userId}');">문서관리</a>
				</li>
				<li class="nav-item">
					<a id="history-tab" class="nav-link" data-toggle="tab" href="#history" role="tab" aria-controls="history" aria-selected="false" onclick="readMyHistory('${userVO.userId}');">히스토리</a>
				</li>
				<li class="nav-item" style="display:none;" >
					<a id="myTaskDetail-tab" class="nav-link" data-toggle="tab" href="#myTaskDetail" role="tab" aria-controls="myTaskDetail" aria-selected="false">업무상세</a>
				</li>
				<li class="nav-item" style="display:none;" >
					<a id="myIssueDetail-tab" class="nav-link" data-toggle="tab" href="#myIssueDetail" role="tab" aria-controls="myIssueDetail" aria-selected="false">이슈상세</a>
				</li>
			</ul>
   
			<div class="tab-content" id="myTabContent">
				<div id="home" class="tab-pane fade show" role="tabpanel" aria-labelledby="home-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myDashboard.jsp" %>
				</div>
				<div id="task" class="tab-pane fade show" role="tabpanel" aria-labelledby="task-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myTask.jsp" %>
				</div>
				<div id="issue" class="tab-pane fade show" role="tabpanel" aria-labelledby="issue-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myIssue.jsp" %>
				</div>
				<div id="mail" class="tab-pane fade show" role="tabpanel" aria-labelledby="mail-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myMail.jsp" %>
				</div>
				<div id="document" class="tab-pane fade show" role="tabpanel" aria-labelledby="document-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myDocument.jsp" %>
				</div>
				<div id="history" class="tab-pane fade show" role="tabpanel" aria-labelledby="history-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myHistory.jsp" %>
				</div>
				<div id="myTaskDetail" class="tab-pane fade show" role="tabpanel" aria-labelledby="myTaskDetail-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myTaskDetail.jsp" %>
				</div>
				<div id="myIssueDetail" class="tab-pane fade show" role="tabpanel" aria-labelledby="myIssueDetail-tab">
					<%@ include file="/WEB-INF/views/web-app/myWork/myIssueDetail.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<!-- 내 작업 바디 끝 -->
</div>
<!-- /page content -->

</body>
