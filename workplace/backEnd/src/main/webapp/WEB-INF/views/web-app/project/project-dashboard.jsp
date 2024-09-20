<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-6 col-sm-6" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 업무</h2>
			<a class="more float-right mt-2 text-dark" style="color:#73879c !important" href="javascript:document.getElementById('task-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="DashboardTable" id="projTaskDashboard"></div>
		</div>
	</div>

<div class="col-md-6 col-sm-6" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 이슈</h2>
			<a class="more float-right mt-2 text-dark" style="color:#73879c !important" href="javascript:document.getElementById('issue-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="DashboardTable" id="projIssueDashboard"></div>
		</div>
	</div>
<!-- dashboard-table left end -->

<!-- dashboard-table right start -->

<!-- <div class="col-md-6 col-sm-6 pr-0">
	<div class="rounded rounded-3" style="height: 300px">
		<div class="x_title">
			<h2>최근 이슈</h2>

			<a class="more float-right mt-2" href="javascript:on();">
					더보기
					<i class="fa fa-arrow-right"></i>
				</a>
				<div class="clearfix"></div>
		</div>
		<div id="carouselExampleIndicators"  class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators mb-0">
    			<li data-target="#carouselExampleIndicators" data-slide-to="0" class="bg-secondary active"></li>
    			<li data-target="#carouselExampleIndicators" data-slide-to="1"class="bg-secondary"></li>
    			<li data-target="#carouselExampleIndicators" data-slide-to="2"class="bg-secondary"></li>
  			</ol>

				The slideshow
				<div class="carousel-inner">
					<div class="carousel-item active">
						<div class="mail_list">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>5분 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">overdriven 오류에 관해서...</h3>
								<p class="text-truncate">Servlet-context에서 발생하는 오류인데 아무리 찾아봐도 답이 안나오네요 혹시 아시는분 </p>
							</div>
						</div>
						<div class="mail_list">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>1일 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">자바스크립트가 사라졌어요</h3>
								<p class="text-truncate">오늘 이클립스를 키니깐 자바스크립트가 사라져있습니다. 혹시 방법
									아시는분 있나요?</p>
							</div>
						</div>
						<div class="mail_list border border-white">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>1시간 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">egov project import시 발생하는 에러</h3>
								<p class="text-truncate">부트스트랩 리소스에서 에러가 발생하는데 정확히 어느부분에서 발생하는지 모르겠습니다.</p>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="mail_list">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>5분 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">김기현 "이재명, 제보자 죽음에 대한 도의적 책임져야"</h3>
								<p class="text-truncate">김기현 국민의힘 원내대표가 더불어민주당 이재명 대선후보의
									'변호사비 대납 의혹'을 최초로 제보했던 이 모  씨가 숨진 채 발견된 것과 관련해 "죽음에 대한 도의적 책임은
									이 후보가 져야 한다"고 주장했습니다.</p>
							</div>
						</div>
						<div class="mail_list">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>1일 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">90년생부터 국민연금 못받나…"'고령화·노인빈곤 심화' 韓
									연금개혁 시급"</h3>
								<p class="text-truncate">한국이 주요 5개국(G5)에 비해 고령화 속도는 빠르고 노인빈곤
									문제가 심화하고 있지만 노후소득을 보장하는 연금 제도가 미흡해 개혁이 필요하다는 주장이 나왔다.</p>
							</div>
						</div>
						<div class="mail_list border border-white">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>1시간 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">2021년 베스트 애널리스트는 누구</h3>
								<p class="text-truncate">2021년 국내 증시 키워드는 ‘코스피 3000·코스닥 1000
									돌파’로 요약된다. 2020년 코로나19 발발 이후의 폭등장이 어느 선에서 안착하느냐를 시험하는 시기였다.</p>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="mail_list">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>5분 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">지뢰 100개 이상 찾았다…캄보디아 '국민 쥐' 하늘나라로</h3>
								<p class="text-truncate">세계적으로 가장 인기 많은 쥐하면 단연 미키마우스일텐데요,
									하지만 캄보디아에서는 이에 못지않게 국민적 사랑을 받는 쥐가 있다고 합니다.</p>
							</div>
						</div>
						<div class="mail_list">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>1일 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">도심에 나타난 타조들…야밤중 벌어진 '도주극'</h3>
								<p class="text-truncate">이번엔 중국의 광저우시로 가보시죠. 도심 도로에 웬 타조들이
									뛰어다니고 있는데요. 무려 80마리나 되는 타조가 인근 농장에서 탈출한 거라고 하네요.</p>
							</div>
						</div>
						<div class="mail_list border border-white">
							<div class="left">
								<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
							</div>
							<div class="right">
								<div class="text-right">
									<h3>
										<small>1시간 전</small>
									</h3>
								</div>
								<h3 class="text-truncate">'1경 원' 몰린 LG엔솔 청약…LG화학 주주는 운다</h3>
								<p class="text-truncate">친절한 경제 시간입니다. 오늘(13일)도 한지연 기자와
									함께하겠습니다. 이달 말에 LG에너지솔루션이 이제 상장을 하나 봐요.</p>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
 -->
		<div class="col-md-6 col-sm-6">
			<div class="x_title">
				<h2>
					최근 히스토리
				</h2>
				<a class="more float-right mt-2 text-dark" style="color:#73879c !important" href="javascript:document.getElementById('history-tab').click();">
					더보기
					<i class="fa fa-arrow-right"></i>
				</a>
				<div class="clearfix"></div>
			</div>

			<div class="x_content">
				<div id="projectHistoryDashboard"></div>
			</div>
		</div>

	<div class="col-md-6 col-sm-6" style="min-height:340px;">
		<div class="x_title">
			<h2>최근 문서</h2>
			<a class="more float-right mt-2 text-dark" style="color:#73879c !important" href="javascript:document.getElementById('document-tab').click();">
				더보기<i class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>

		<div class="x_content">
			<div class="DashboardTable" id="projectDocumentDashboard"></div>
		</div>
	</div>

	<!-- 카드 리스트 -->
	<div class="col-md-12 col-sm-12 pl-0">
		<div class="x_title">
			<h2>구성원별 업무 통계</h2>
			<a class="more float-right mt-2" style="color:#73879c !important" href="javascript:on();"> 더보기 <i
				class="fa fa-arrow-right"></i>
			</a>
			<div class="clearfix"></div>
		</div>
		<div class="x_content d-flex justify-content-center">
			<div class="chart-container" style="width: 100%; height: auto;">
				<canvas id="member_achievement_chart"></canvas>
			</div>
		</div>
	</div>


