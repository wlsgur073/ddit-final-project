<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- include -->
    <script src="../test-js/includeHTML.js"></script>
<!-- page content -->
      <div class="right_col" role="main">
        <!-- 프로젝트 바디 시작 -->
        <div class="x_panel">

          <div class="x_title">
            <h2><i class="fa fa-desktop"></i> 프로젝트 <small>* 커스터 마이징 해주세요.</small></h2>
            <div class="clearfix"></div>
          </div>

          <div class="x_content">

            <ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="dash-board-tab" data-toggle="tab" href="#dash-board" role="tab" aria-controls="dash-board" aria-selected="true">대시보드</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="project-list-tab" data-toggle="tab" href="#project-list" role="tab" aria-controls="project-list" aria-selected="false">프로젝트 리스트</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="gantt-tab" data-toggle="tab" href="#gantt" role="tab" aria-controls="gantt" aria-selected="false">간트차트</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="issue-tab" data-toggle="tab" href="#issue" role="tab" aria-controls="issue" aria-selected="false">이슈</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="request-tab" data-toggle="tab" href="#request" role="tab" aria-controls="request" aria-selected="false">리퀘스트</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="history-tab" data-toggle="tab" href="#history" role="tab" aria-controls="history" aria-selected="false">히스토리</a>
              </li>
            </ul>

            <div class="tab-content" id="myTabContent">

              <!-- 프로젝트 대시보드 예시입니다. -->
              <div class="tab-pane fade show active" id="dash-board" role="tabpanel" aria-labelledby="dash-board-tab">
                 <!-- 카드 리스트 -->
  <div class="row mt-5 mb-5">
    <div class="animated flipInY col-lg-3 col-md-3 col-sm-6  ">
      <div class="tile-stats">
        <div class="icon"><i class="fa fa-tasks"></i>
        </div>
        <div class="count">14</div>

        <h3>전체 업무</h3>
        
      </div>
    </div>
    <div class="animated flipInY col-lg-3 col-md-3 col-sm-6  ">
      <div class="tile-stats">
        <div class="icon"><i class="fa fa-check-square-o"></i>
        </div>
        <div class="count">4</div>

        <h3>완료 업무</h3>
        
      </div>
    </div>
    <div class="animated flipInY col-lg-3 col-md-3 col-sm-6  ">
      <div class="tile-stats">
        <div class="icon"><i class="fa fa-star-o"></i>
        </div>
        <div class="count">5</div>

        <h3>이슈</h3>
        
      </div>
    </div>
    <div class="animated flipInY col-lg-3 col-md-3 col-sm-6  ">
      <div class="tile-stats">
        <div class="icon"><i class="fa fa-reply"></i>
        </div>
        <div class="count">2</div>

        <h3>리퀘스트</h3>
        
      </div>
    </div>
  </div>
  <!-- 카드 리스트 끝 -->
  <!-- 그래프 시작 -->
  <div class="row">
    <!-- bar chart -->
    <div class="col-md-6 col-sm-6  ">
      <div class="x_panel">
        <div class="x_title">
          <h2>업무별 상태 <small>봉 그래프</small></h2>
          <div class="clearfix"></div>
        </div>
        <div class="x_content">
          <div id="graph_bar" style="width:100%; height:280px;"></div>
        </div>
      </div>
    </div>
    <!-- /bar charts -->
    <!-- ground cahrs -->
      <div class="col-md-6 col-sm-4" style="padding: 0px;">
        <div class="x_panel">
          <div class="x_title">
            <h2>업무별 상태</h2>
            <div class="clearfix"></div>
          </div>
          <div class="x_content">
    
            <div id="echart_donut" style="height:350px;"></div>
    
          </div>
        </div>
      </div>
    <!-- /ground cahrs -->
  </div>
<!-- 그래프 끝 -->
  
                <div w3-include-html="../project/dash-board-graph.html"></div>
              </div>
              <!-- 프로젝트 대시보드 예시입니다. 끝 -->



              <div class="tab-pane fade" id="project-list" role="tabpanel" aria-labelledby="project-list-tab">
                my work에 있는 폴더 안에 html 파일을 불러와서 div를 붙여넣기 한다.
              </div>
              <div class="tab-pane fade" id="gantt" role="tabpanel" aria-labelledby="gantt-tab">
                my work에 있는 폴더 안에 html 파일을 불러와서 div를 붙여넣기 한다.
              </div>
              <div class="tab-pane fade" id="issue" role="tabpanel" aria-labelledby="issue-tab">
                my work에 있는 폴더 안에 html 파일을 불러와서 div를 붙여넣기 한다.
              </div>
              <div class="tab-pane fade" id="request" role="tabpanel" aria-labelledby="request-tab">
                my work에 있는 폴더 안에 html 파일을 불러와서 div를 붙여넣기 한다.
              </div>
              <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="history-tab">
                my work에 있는 폴더 안에 html 파일을 불러와서 div를 붙여넣기 한다.
              </div>
            </div>

          </div>

        </div>
        <!-- 프로젝트 바디 끝 -->
      </div>
      <!-- /page content -->
      
      <!-- include -->
    <script>includeHTML();</script>