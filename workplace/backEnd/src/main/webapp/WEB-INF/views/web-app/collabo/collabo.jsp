<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>


<body>
	<!-- page content -->
	<div class="right_col" role="main">
		<!-- 프로젝트 바디 시작 -->
		<div class="x_panel">

			<div class="x_title">
				<h2>
					<img src="https://img.icons8.com/ios/30/000000/handshake--v2.png" /></i>
					콜라보 프로젝트
				</h2>
				<div class="clearfix"></div>
			</div>

			<div class="x_content">

				<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
					<li class="nav-item"><a class="nav-link" id="home-tab"
						data-toggle="tab" href="#home" role="tab"
						aria-controls="home" aria-selected="false"
						onclick="setTimeout(readCollaboDashboard, 200);">대시보드</a></li>

					<li class="nav-item"><a class="nav-link active"
						id="outline-board-tab" data-toggle="tab" href="#outline-board"
						role="tab" aria-controls="outline-board" aria-selected="true">개요</a>
					</li>

					<li class="nav-item"><a class="nav-link" id="task-tab"
						data-toggle="tab" href="#task" role="tab" aria-controls="task"
						aria-selected="false" onclick="setTimeout(readCollaboTask, 300);">업무</a></li>

					<li class="nav-item"><a class="nav-link" id="gantt-tab"
						data-toggle="tab" href="#ganttchart" role="tab"
						aria-controls="ganttchart" aria-selected="false"
						onclick="setTimeout(collaboGantt, 300);">간트차트</a></li>

					<li class="nav-item"><a class="nav-link" id="issue-tab"
						data-toggle="tab" href="#issue" role="tab" aria-controls="issue"
						aria-selected="false"
						onclick="setTimeout(readIssue, 300);setTimeout(readMile, 300);">이슈</a></li>

					<li class="nav-item"><a class="nav-link" id="document-tab"
						data-toggle="tab" href="#document" role="tab"
						aria-controls="document" aria-selected="false" onclick="setTimeout(readDocument, 300);">문서관리</a></li>

					<li class="nav-item"><a class="nav-link" id="history-tab"
						data-toggle="tab" href="#history" role="tab"
						aria-controls="history" aria-selected="false"
						>히스토리</a></li>


					<!-- <li class="nav-item"><a class="nav-link" id="spoon-tab" data-toggle="tab" href="#spoon" role="tab" aria-controls="spoon" aria-selected="false">Spoon</a></li> -->

					<li class="nav-item" style="display: none;"><a
						class="nav-link" id="taskDetail-tab" data-toggle="tab"
						href="#taskDetail" role="tab" aria-controls="taskDetail"
						aria-selected="false">업무상세</a></li>

				</ul>

				<div class="tab-content" id="myTabContent">

					<div class="tab-pane fade" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						<%@ include
							file="/WEB-INF/views/web-app/collabo/collabo-dashboard.jsp"%>
					</div>
					
					<div class="tab-pane fade show active" id="outline-board"
						role="tabpanel" aria-labelledby="outline-board-tab">
						<%@ include
							file="/WEB-INF/views/web-app/collabo/collabo-detail.jsp"%>
					</div>

					<div class="tab-pane fade" id="task" role="tabpanel"
						aria-labelledby="task-tab">
						<%@ include file="/WEB-INF/views/web-app/collabo/collabo-task.jsp"%>
					</div>
					<!-- <div class="tab-pane fade" id="collabo-list" role="tabpanel" aria-labelledby="collabo-list-tab">

					</div> -->
					<div class="tab-pane fade" id="ganttchart" role="tabpanel"
						aria-labelledby="gantt-tab">
						<div id="collaboGantt" style="z-index: 0"></div>
					</div>

					<div class="tab-pane fade" id="issue" role="tabpanel"
						aria-labelledby="issue-tab">
						<%@ include
							file="/WEB-INF/views/web-app/collabo/collabo-issue.jsp"%>
					</div>

					<div class="tab-pane fade show" id="document" role="tabpanel" aria-labelledby="document-tab">
          				 <%@ include file="/WEB-INF/views/web-app/collabo/collabo-document.jsp" %>
       				 </div>

					<div class="tab-pane fade" id="history" role="tabpanel"
						aria-labelledby="history-tab">
						<%@ include
							file="/WEB-INF/views/web-app/collabo/collabo-history.jsp"%>
					</div>

					<%-- <div class="tab-pane fade" id="spoon" role="tabpanel"
						aria-labelledby="spoon-tab">
						<%@ include
							file="/WEB-INF/views/web-app/collabo/collabo-spoon.jsp"%>
					</div> --%>
					<div class="tab-pane fade" id="taskDetail" role="tabpanel"
						aria-labelledby="taskDetail-tab">
						<%@ include
							file="/WEB-INF/views/web-app/collabo/collabo-task-detail.jsp"%>
					</div>
				</div>

			</div>

		</div>
		<!-- 프로젝트 바디 끝 -->
	</div>
	<!-- /page content -->

</body>