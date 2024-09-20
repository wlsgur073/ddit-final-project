<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<div class="x_content row">
	<!-- 카드 리스트 -->	
	<div class="col-md-6 col-sm-6 taskBox" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 업무</h2>
			<a class="more float-right mt-2" style="color:#73879c;" href="javascript:document.getElementById('task-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div id="canvasBox" class="chart-container d-flex justify-content-center" style="padding-top:20px;height:250px;border:1px solid #e0e0e0;">
				<h2 class="text-center" style="color:#73879c;">업무가 존재하지 않습니다.</h2>
			</div>
		</div>
	</div>
	
	<div class="col-md-6 col-sm-6" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 이슈</h2>
			<a class="more float-right mt-2" style="color:#73879c;" href="javascript:document.getElementById('issue-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="myDashboardTable" id="myIssueDashboard"></div>
		</div>
	</div>
	
	<div class="col-md-6 col-sm-6" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 메일</h2>
			<a class="more float-right mt-2" style="color:#73879c;" href="javascript:document.getElementById('mail-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="myDashboardTable" id="myReceiveMailDashboard" style="max-height:247px;"></div>
		</div>
	</div>
	
	<div class="col-md-6 col-sm-6" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 히스토리</h2>
			<a class="more float-right mt-2" style="color:#73879c;" href="javascript:document.getElementById('history-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>

		<div class="x_content">
			<div id="myHistoryDashboard"></div>
		</div>
	</div>

	<div class="col-md-12 col-sm-12" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 문서</h2>
			<a class="more float-right mt-2" style="color:#73879c;" href="javascript:document.getElementById('document-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>

		<div class="x_content">
			<div class="myDashboardTable" id="myDocumentDashboard"></div>
		</div>
	</div>
</div>