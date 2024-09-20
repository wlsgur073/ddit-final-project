<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<!-- page content -->
<div class="right_col" role="main">
  <!-- 내 작업 바디 시작 -->
  <div class="x_panel">
    <div class="x_title">
      <h2><i class="fa fa-edit"></i>프로젝트</h2>
      <div class="clearfix"></div>
    </div>

    <div class="x_content">
      <ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
      <c:if test="${param.from != 'search'}">
        <li class="nav-item">
          <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true" onclick="setTimeout(readProjDashboard, 200);">대시보드</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="detail-tab" data-toggle="tab" href="#detail" role="tab" aria-controls="detail" aria-selected="false" onclick="setTimeout(readProjDetail, 200);">프로젝트 개요</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="task-tab" data-toggle="tab" href="#task" role="tab" aria-controls="task" aria-selected="false" onclick="setTimeout(readTask, 300);">업무</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="gantt-tab" data-toggle="tab" href="#ganttchart" role="tab" aria-controls="ganttchart" aria-selected="false" onclick="setTimeout(projGantt, 300);">간트차트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="issue-tab" data-toggle="tab" href="#issue" role="tab" aria-controls="issue" aria-selected="false" onclick="setTimeout(readIssue, 300);setTimeout(readMile, 300);">이슈</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="document-tab" data-toggle="tab" href="#document" role="tab" aria-controls="document" aria-selected="false" onclick="setTimeout(readDocument, 300);">문서관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="history-tab" data-toggle="tab" href="#history" role="tab" aria-controls="history" aria-selected="false">히스토리</a>
        </li>
        <li class="nav-item" style="display:none;" >
          <a class="nav-link" id="taskDetail-tab" data-toggle="tab" href="#taskDetail" role="tab" aria-controls="taskDetail" aria-selected="false">업무상세</a>
        </li>
        <li class="nav-item" style="display:none;" >
          <a class="nav-link" id="issueDetail-tab" data-toggle="tab" href="#issueDetail" role="tab" aria-controls="issueDetail" aria-selected="false">이슈상세</a>
        </li>
      </c:if>
      <c:if test="${param.from == 'search'}">
      	<li class="nav-item">
          <a class="nav-link" id="detail-tab" data-toggle="tab" href="#detail" role="tab" aria-controls="detail" aria-selected="false" onclick="setTimeout(readProjDetail, 200);">프로젝트 개요</a>
        </li>
      </c:if>
      </ul>

      <div class="tab-content" id="myTabContent">
      	 <c:if test="${param.from != 'search'}">
        <div class="tab-pane fade show" id="home" role="tabpanel" aria-labelledby="home-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-dashboard.jsp" %>
        </div>
        <div class="tab-pane fade show" id="detail" role="tabpanel" aria-labelledby="detail-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-detail.jsp" %>
        </div>
        <div class="tab-pane fade show" id="task" role="tabpanel" aria-labelledby="task-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-task.jsp" %>
        </div>
        <div class="tab-pane fade show" id="ganttchart" role="tabpanel" aria-labelledby="gantt-tab">
          <div id="projGantt" style="z-index:0"></div>
        </div>
        <div class="tab-pane fade show" id="issue" role="tabpanel" aria-labelledby="issue-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-issue.jsp" %>
        </div>
         <div class="tab-pane fade show" id="document" role="tabpanel" aria-labelledby="document-tab">
           <%@ include file="/WEB-INF/views/web-app/project/project-document.jsp" %>
        </div>
        <div class="tab-pane fade show" id="history" role="tabpanel" aria-labelledby="history-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-history.jsp" %>
        </div>
        <div class="tab-pane fade show" id="taskDetail" role="tabpanel" aria-labelledby="taskDetail-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-task-detail.jsp" %>
        </div>
        <div class="tab-pane fade show" id="issueDetail" role="tabpanel" aria-labelledby="issueDetail-tab">
          <%@ include file="/WEB-INF/views/web-app/project/project-issue-detail.jsp" %>
        </div>
        	</c:if>
        	<c:if test="${param.from == 'search'}">
        		<div class="tab-pane fade show" id="detail" role="tabpanel" aria-labelledby="detail-tab">
          			<%@ include file="/WEB-INF/views/web-app/project/project-detail.jsp" %>
        		</div>
        	</c:if>
      </div>
    </div>
  </div>
<!-- 내 작업 바디 끝 -->
</div>
<!-- /page content -->

</body>
