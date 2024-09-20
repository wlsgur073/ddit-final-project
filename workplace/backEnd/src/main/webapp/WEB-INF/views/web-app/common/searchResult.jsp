<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





<body>
	<link href="<%=request.getContextPath()%>/resources/seok/seok.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/resources/seok/products.js"></script>
	<script src="<%=request.getContextPath()%>/resources/seok/nickname.js"></script>

  <!-- include -->
    
    <script src="../test-js/includeHTML.js"></script>

<div class="right_col" role="main">
	<!-- 내 작업 바디 시작 -->
	<div class="x_panel">

		<div class="x_title">
			<h2>

				<a href="#/user"><i class="fa fa-user fa-2x"></i></a> 통합검색 결과
			</h2>

			<div class="clearfix"></div>
		</div>

		<div class="x_content"></div>

		<div class="tab-content" id="myTabContent">

			<!-- 내 작업 대시보드 예시입니다. -->

			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">


				<div class="col-md-6 col-sm-6  ">
					<h3>
						<i class="fa fa-search"></i>&nbsp;프로젝트 검색 결과
					</h3>

					<div id="projectTable"></div>

				</div>



				<div class="col-md-6 col-sm-6  ">
					<h3>
						<i class="fa fa-search"></i>&nbsp;닉네임 검색 결과
					</h3>
					<div id="example">
						<div id="NickName"></div>
					</div>
				</div>








				<div class="col-md-6 col-sm-6  ">
					<h3>
						<i class="fa fa-trophy "></i>&nbsp;인기 프로젝트
					</h3>
					<div id="carouselExampleCaptions" class="carousel slide"
						data-ride="carousel" data-interval="false">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleCaptions" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
							<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">


								<div class="well profile_view d-block w-100"
									style="height: 230px;">
									<div class="col-sm-12">
										<h4 class="brief">
											<i>프로젝트 이름</i>
										</h4>
										<div class="left col-md-7 col-sm-7">
											<h2>PL석기현</h2>
											<p>글ㅀㅇㅀㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㅇㅀㅇㅀㄹㅇㅎㅇㅀㄹㅇㅎ</p>
											<ul class="list-unstyled">

											</ul>
										</div>
										<div class="right col-md-5 col-sm-5 text-center">
											<img src="<%=request.getContextPath() %>/resources/asserts/images/img.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
									<div class=" row">
										<div class=" col-sm-6 ">

											<div class=" row ml-3">
												<i class="fa fa-heart fa-2x">30</i>
												<button type="button" class="btn btn-primary btn-sm ml-4"
													style="">
													<i class="fa fa-desktop"> </i> 상세보기
												</button>
											</div>
										</div>

									</div>
								</div>

								<div class="carousel-caption d-none d-md-block"></div>
							</div>
							<div class="carousel-item">
								<div class="well profile_view d-block w-100"
									style="height: 230px;">
									<div class="col-sm-12">
										<h4 class="brief">
											<i>프로젝트 이름</i>
										</h4>
										<div class="left col-md-7 col-sm-7">
											<h2>PL석기현</h2>
											<p>글ㅀㅇㅀㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㅇㅀㅇㅀㄹㅇㅎㅇㅀㄹㅇㅎ</p>
											<ul class="list-unstyled">

											</ul>
										</div>
										<div class="right col-md-5 col-sm-5 text-center">
											<img src="<%=request.getContextPath()%>/resources/asserts/images/img.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
									<div class=" row">
										<div class=" col-sm-6 ">

											<div class=" row ml-3">
												<i class="fa fa-heart fa-2x">30</i>
												<button type="button" class="btn btn-primary btn-sm ml-4"
													style="">
													<i class="fa fa-desktop"> </i> 상세보기
												</button>
											</div>
										</div>

									</div>
								</div>
								<div class="carousel-caption d-none d-md-block"></div>
							</div>
							<div class="carousel-item">

								<div class="well profile_view d-block w-100"
									style="height: 230px;">
									<div class="col-sm-12">
										<h4 class="brief">
											<i>프로젝트 이름</i>
										</h4>
										<div class="left col-md-7 col-sm-7">
											<h2>PL석기현</h2>
											<p>글ㅀㅇㅀㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㅇㅀㅇㅀㄹㅇㅎㅇㅀㄹㅇㅎ</p>
											<ul class="list-unstyled">

											</ul>
										</div>
										<div class="right col-md-5 col-sm-5 text-center">
											<img src="<%=request.getContextPath() %>/resources/asserts/images/img.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
									<div class=" row">
										<div class=" col-sm-6 ">

											<div class=" row ml-3">
												<i class="fa fa-heart fa-2x">30</i>
												<button type="button" class="btn btn-primary btn-sm ml-4"
													style="">
													<i class="fa fa-desktop"> </i> 상세보기
												</button>
											</div>
										</div>

									</div>
								</div>


								<div class="carousel-caption d-none d-md-block"></div>
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleCaptions"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>




				<div class="col-md-6 col-sm-6  ">
					<h3>
						<i class="fa fa-trophy "></i>&nbsp;인기인
					</h3>
					<div id="carouselExampleCaptionsHuman" class="carousel slide"
						data-ride="carousel" data-interval="false">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleCaptionsHuman" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleCaptionsHuman" data-slide-to="1"></li>
							<li data-target="#carouselExampleCaptionsHuman" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">


								<div class="well profile_view d-block w-100"
									style="height: 230px;">
									<div class="col-sm-12">
										<h4 class="brief">
											<i>pooh_00@naver.com</i>
										</h4>
										<div class="left col-md-7 col-sm-7">
											<h2>석기현</h2>
											<p>50자 이내로 작성한 소개글ㅀㅇㅀㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㅇㅀㅇㅀㄹㅇㅎㅇㅀㄹㅇㅎ</p>
											<ul class="list-unstyled">

											</ul>
										</div>
										<div class="right col-md-5 col-sm-5 text-center">
											<img src="<%=request.getContextPath() %>/resources/asserts/images/img.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
									<div class=" row">
										<div class=" col-sm-6 ">

											<div class=" row ml-3">
												<i class="fa fa-heart fa-2x">30</i>
												<button type="button" class="btn btn-primary btn-sm ml-4"
													style="">
													<i class="fa fa-user"> </i> 상세보기
												</button>
											</div>
										</div>

									</div>
								</div>

								<div class="carousel-caption d-none d-md-block"></div>
							</div>
							<div class="carousel-item">
								<div class="well profile_view d-block w-100"
									style="height: 230px;">
									<div class="col-sm-12">
										<h4 class="brief">
											<i>pooh_00@naver.com</i>
										</h4>
										<div class="left col-md-7 col-sm-7">
											<h2>석기현</h2>
											<p>50자 이내로 작성한 소개글ㅀㅇㅀㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㅇㅀㅇㅀㄹㅇㅎㅇㅀㄹㅇㅎ</p>
											<ul class="list-unstyled">

											</ul>
										</div>
										<div class="right col-md-5 col-sm-5 text-center">
											<img src="<%=request.getContextPath() %>/resources/asserts/images/img.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
									<div class=" row">
										<div class=" col-sm-6 ">

											<div class=" row ml-3">
												<i class="fa fa-heart fa-2x">30</i>
												<button type="button" class="btn btn-primary btn-sm ml-4"
													style="">
													<i class="fa fa-user"> </i> 상세보기
												</button>
											</div>
										</div>

									</div>
								</div>



								<div class="carousel-caption d-none d-md-block"></div>
							</div>
							<div class="carousel-item">

								<div class="well profile_view d-block w-100"
									style="height: 230px;">
									<div class="col-sm-12">
										<h4 class="brief">
											<i>pooh_00@naver.com</i>
										</h4>
										<div class="left col-md-7 col-sm-7">
											<h2>석기현</h2>
											<p>50자 이내로 작성한 소개글ㅀㅇㅀㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㄹㅇㅎㅇㅀㅇㅀㄹㅇㅎㅇㅀㄹㅇㅎ</p>
											<ul class="list-unstyled">

											</ul>
										</div>
										<div class="right col-md-5 col-sm-5 text-center">
											<img src="<%=request.getContextPath() %>/resources/asserts/images/img.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
									<div class=" row">
										<div class=" col-sm-6 ">

											<div class=" row ml-3">
												<i class="fa fa-heart fa-2x">30</i>
												<button type="button" class="btn btn-primary btn-sm ml-4"
													style="">
													<i class="fa fa-user"> </i> 상세보기
												</button>
											</div>
										</div>

									</div>
								</div>



								<div class="carousel-caption d-none d-md-block"></div>
							</div>
						</div>
						<a class="carousel-control-prev"
							href="#carouselExampleCaptionsHuman" role="button"
							data-slide="prev"> <span class="carousel-control-prev-icon"
							aria-hidden="true"></span> <span class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleCaptionsHuman" role="button"
							data-slide="next"> <span class="carousel-control-next-icon"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>



		</div>
	</div>
</div>


<script>
	window.onload = function() {

		$("#projectTable").kendoGrid({

			dataSource : {
				data : products,
				schema : {
					model : {
						fields : {
							projectName : {
								type : "string"
							},
							PLName : {
								type : "string"
							},
							open : {
								type : "string"
							},

						}
					}
				},
				pageSize : 9
			},
			columnMenu : {
				filterable : false
			},
			batch : true,
			pageSize : 9,
			autoSync : true,
			height : 400,

			pageable : true,
			sortable : true,

			navigatable : true,
			resizable : true,
			reorderable : true,
			groupable : true,

			columns : [ {
				field : "projectName",
				title : "프로젝트 이름",
				width : 150,
				encoded : false
			}, {
				field : "PLName",
				title : "PL닉네임",
				width : 150,
				encoded : false
			}, {
				field : "open",
				title : "공개/비공개",
				width : 100
			} ]
		});

		$("#NickName").kendoGrid({
			dataSource : {
				data : nickName,
				schema : {
					model : {
						fields : {
							nickName : {
								type : "string"
							},
							Email : {
								type : "string"
							},
							open : {
								type : "string"
							},

						}
					}
				},
				pageSize : 9,

			},
			columnMenu : {
				filterable : false
			},
			batch : true,
			pageSize : 2,
			autoSync : true,
			height : 400,
			pageable : true,
			sortable : true,
			navigatable : true,
			resizable : true,
			reorderable : true,
			groupable : true,

			columns : [ {
				field : "nickName",
				title : "닉네임",
				width : 100,
				encoded : false
			}, {
				field : "Email",
				title : "이메일",
				width : 200,
				encoded : false
			}, {
				field : "open",
				title : "공개/비공개",
				width : 125,
				encoded : false
			} ]
		});
	}
</script>
</body>
