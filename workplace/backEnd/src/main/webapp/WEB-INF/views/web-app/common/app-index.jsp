<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 변수 선언 시작 -->

<!-- 변수 선언 끝 -->

<!-- page content -->
<div class="right_col" role="main">

  <div class="clearfix"></div>

  <div class="row dash-board-boxes">
    <div class="col-md-12 col-sm-12">

			<!-- dashboard-stat start -->
			<div class="row">

				<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12"
					style="max-width: 20%; flex: 20%;">

					<div class="dashboard-stat bordered grey-cararra fadeInDown animated">
						<div class="visual">
							<i class="fa fa-tasks"></i>
						</div>
						<div class="details">
							<h3 style="color: #5C9BD1;">
								<span title="활동중인 업무" class="taskTotalCount">0</span>
							</h3>
							<div class="noti-desc">내 작업</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/app/myWork?#task-tab">
							더보기 <i class="fa fa-arrow-right"></i>
						</a>
					</div>

				</div>

				<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12"
					style="max-width: 20%; flex: 20%;">

					<div
						class="dashboard-stat bordered grey-cararra fadeInDown animated">
						<div class="visual">
							<i class="fa fa-thumb-tack"></i>
						</div>
						<div class="details">
							<h3 style="color: #8877a9;">
								<span title="받은 요청" class="issueTotalCount">0</span>
							</h3>
							<div class="noti-desc">이슈</div>
						</div>
						<a class="more"
							href="<%=request.getContextPath()%>/app/myWork?#issue-tab">
							더보기 <i class="fa fa-arrow-right"></i>
						</a>
					</div>

				</div>

				<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12"
					style="max-width: 20%; flex: 20%;">

					<div
						class="dashboard-stat bordered grey-cararra fadeInDown animated">
						<div class="visual">
							<i class="fa fa-laptop"></i>
						</div>
						<div class="details">
							<h3 style="color: #5e738b;">
								<span title="참여중인 프로젝트" class="projectTotalCount">0</span>
							</h3>
							<div class="noti-desc">프로젝트</div>
						</div>
						<a class="more"
							href="<%=request.getContextPath()%>/app/project-list"> 더보기 <i
							class="fa fa-arrow-right"></i>
						</a>
					</div>

				</div>

				<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12"
					style="max-width: 20%; flex: 20%;">

					<div
						class="dashboard-stat bordered grey-cararra fadeInDown animated">
						<div class="visual">
							<i class="fa fa-group"></i>
						</div>
						<div class="details">
							<h3 style="color: #bfd042;">
								<span title="참여중인 콜라보" class="collaboTotalCount">0</span>
							</h3>
							<div class="noti-desc">콜라보</div>
						</div>
						<a id="OwnCollabo-tab" class="more" href=""> 더보기 <i
							class="fa fa-arrow-right"></i>
						</a>
					</div>

				</div>

				<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12"
					style="max-width: 20%; flex: 20%;">

					<div
						class="dashboard-stat bordered grey-cararra fadeInDown animated">
						<div class="visual">
							<i class="fa fa-send"></i>
						</div>
						<div class="details">
							<h3 style="color: #f36a5a;">
								<span title="받은 메일" class="mailTotalCount">0</span>
							</h3>
							<div class="noti-desc">메일</div>
						</div>
						<a class="more"
							href="<%=request.getContextPath()%>/app/myWork?mail=mail">
							더보기 <i class="fa fa-arrow-right"></i>
						</a>
					</div>

				</div>






			</div>
			<!-- dashboard-stat end -->

      <!-- dashboard-table start -->
      <div class="row">
        <!-- dashboard-table left start -->
        <div class="col-md-6 col-sm-6">
          <div class="x_panel_origin">
            <div class="x_title">
              <h2>내 업무 </h2>
              <a class="more float-right mt-2" style="color:#73879c" href="<%=request.getContextPath()%>/app/myWork?#task-tab">
                더보기
                <i class="fa fa-arrow-right"></i>
              </a>
              <div class="clearfix"></div>
            </div>
            <div class="x_content">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>업무 명</th>
                    <th>프로젝트 명</th>
                    <th>상태</th>
                  </tr>
                </thead>
                <tbody class="task_list_for_index_page">
                  <!-- <tr>
                    <th scope="row">러시아 고공낙하 장비 설계</th>
                    <td>한미 지상 훈련 프로젝트</td>
                    <td>진행중</td>
                  </tr> -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <!-- dashboard-table left end -->
      
        <!-- dashboard-table right start -->
        <div class="col-md-6 col-sm-6">
          <div class="x_panel_origin">
            <div class="x_title">
              <h2>최근 작성한 문서</h2>
              <a class="more float-right mt-2" style="color:#73879c" href="<%=request.getContextPath()%>/app/myWork?#task-tab">
                더보기
                <i class="fa fa-arrow-right"></i>
              </a>
              <div class="clearfix"></div>
            </div>
            <div class="x_content">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>업무 명</th>
                    <th>프로젝트 명</th>
                    <th>상태</th>
                  </tr>
                </thead>
                <tbody class="recent_task_list_for_index_page">
                <!-- alertIndex.js의 recent_task_list_for_index_page를 참고하세요 -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <!-- dashboard-table right end -->
      </div>
      <!-- 그래프 row start -->
      <div class="row" style="flex-wrap: nowrap;">
        <div class="col-md-3 widget widget_tally_box x_panel_origin widget_data">
          <div class="ui-ribbon-container" style="height: 440px;">
            <div class="x_title">
              <h2>데이터</h2>
              <div class="clearfix"></div>
            </div>
            <div class="x_content">
      
              <div style="text-align: center; margin-bottom: 17px">
                <span class="chart" id="dataUsageChart" data-percent="">
                  <span class="percent"></span>
                </span>
              </div>
      
              <h3 class="name_title">데이터 사용량</h3>
              <div class="divider"></div>
      
              <p id="showUserDataUsage">
              </p>
      
            </div>
          </div>
        </div>
        <div class="col-md-9 col-sm-8">
          <!-- 그래프 작업 바디 시작 -->
          <div class="x_panel_origin">
            <div class="x_title">
              <h2> 월별 프로젝트 진행상태 그래프</h2>
              <div class="clearfix"></div>
            </div>
            <div class="x_content">
              <!-- STACKED BAR CHART -->
              <div>
                <canvas id="custom_monthBarChart"></canvas>
              </div>
              <!-- /.card -->
            </div>
          </div>
          <!-- 그래프 작업 바디 끝 -->
        </div>
      </div>
      <!-- 그래프 row end -->

      <!-- dashboard-table center start -->
      <div class="col-md-12 col-sm-12 p-0">
        <div class="x_panel_origin">
          <div class="x_title">
            <h2>참여 프로젝트</h2>
            <a class="more float-right mt-2" style="color:#73879c" href="<%=request.getContextPath() %>/app/project-list">
              더보기
              <i class="fa fa-arrow-right"></i>
            </a>
            <div class="clearfix"></div>
          </div>
          <div class="x_content">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>프로젝트 명</th>
                  <th>진행상태</th>
                  <th>시작일</th>
                  <th>종료일</th>
                  <th>진척율(%)</th>
                </tr>
              </thead>
              <tbody class="project_list_for_index_page">
                <!-- <tr>
                  <th scope="row">1</th>
                  <td>Mark</td>
                  <td>Otto</td>
                </tr> -->
              </tbody>
            </table>

          </div>
        </div>
      </div>
      <!-- dashboard-table center end -->
    </div>
  </div>

</div>
<!-- /page content -->