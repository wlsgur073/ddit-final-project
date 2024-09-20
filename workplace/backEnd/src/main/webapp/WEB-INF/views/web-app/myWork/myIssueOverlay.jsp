<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			#myMileOverlay {
				position: fixed;
				display: none;
				width: 500px;
				height: 100%;
				top: 0;
				right: 0;
				bottom: 0;
				border: 1px solid #ededed;
				background-color: white;
				z-index: 200;
			}
		</style>
	</head>
	<body>
		<!-- 오버레이 시작 -->
		<div id="myMileOverlay"></div>
		<!-- 오버레이 끝 -->

		<script type="text/x-handlebars-template" id="myMileRegistFormTemplate">
			<div class="row" id="fadeInMyIssueContent">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title row d-flex justify-content-between">
							<h5 class="title">
								<i class="fa fa-clone"></i> <span class="task-bold task-sm">마일스톤 등록</span>
							</h5>
							<div class="clearfix">
								<button onclick="cancelMyMilestone();" class="btn btn-primary btn-sm">X</button>
							</div>
						</div>
						<div class="x_content">
							<div class="row">
								<div class="x_content">
									<form id="registMyMileForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
										<!-- 프로젝트명 -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="projTitle">프로젝트명	</label>
											<div class="col-md-6 col-sm-6 ">
												<select class="form-control form-control-sm" id="myProjTitle" name="projNo">
													{{#each .}}
														<option value="{{projNo}}">{{projTitle}}</option>
													{{/each}}
		            							</select>
											</div>
										</div>
										<div class="item form-group">
		          							<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">제기자</label>
											<div class="col-md-6 col-sm-6 ">
												<select class="form-control form-control-sm" id="userNickname" name="userId" reaonly>
		              								<option value="${userVO.userId}">${userVO.nickname}</option>
		            							</select>
											</div>
		        						</div>
										<div class="item form-group">
		          							<label class="col-form-label col-md-3 col-sm-3 label-align for="status">마일스톤 상태</label>
											<div class="col-md-6 col-sm-6 ">
		            							<select class="form-control form-control-sm" id="status" name="status">
		              								<option	value="B301">미해결</option>
		            							</select>
											</div>
		        						</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="title">마일스톤 제목</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="title" class="form-control form-control-sm" name="title">
											</div>
										</div>
										<div class=form-group">
											<textarea class="myMileRegistSummernote" name="content"></textarea>
										</div>
										<div id="MileIssueTagFormGroup" class=form-group">
											<label class=" label-align mt-3">이슈 등록</label>
											<select id="myMileIssueTag" name="myIssueList" multiple="multiple" data-placeholder="추가하실 이슈를 선택해주세요.">
		        							</select>
										</div>
										<div class="col-md-12 col-sm-12 mt-3" style="text-align:right;">
											<button type="button" class="btn btn-success" onclick="registMyMilestone();">등록</button>
											<button type="button" class="btn btn-primary" onclick="cancelMyMilestone();">취소</button>
											<button type="reset" class="btn btn-info">리셋</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>
		
		<script type="text/x-handlebars-template" id="myMileModifyFormTemplate">
			<div class="row" id="fadeInMyIssueContent">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title row d-flex justify-content-between">
							<h5 class="title"><i class="fa fa-clone">
								</i> <span class="task-bold task-sm">마일스톤 수정</span>
							</h5>
							<div class="clearfix">
								<button onclick="cancelMyMilestone();" class="btn btn-primary btn-sm">X</button>
							</div>
						</div>
						<div class="x_content">
							<div class="row">
								<div class="x_content">
									<form id="modifyMyMileForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
										<!-- 프로젝트명 -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="projTitle">프로젝트명</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="projTitle" required="required" class="form-control form-control-sm" name="projTitle" value="{{mileVO.projTitle}}" disabled>
												<input type="hidden" name="mileNo" value="{{mileVO.mileNo}}">
												<input type="hidden" name="projNo" value="{{mileVO.projNo}}">
											</div>
										</div>
										<div class="item form-group">
          									<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">제기자</label>
											<div class="col-md-6 col-sm-6 ">
            									<select class="form-control form-control-sm" id="userNickname" name="userId">
              										<option value="{{mileVO.userId}}">{{mileVO.nickname}}</option>
            									</select>
											</div>
        								</div>
										<div class="item form-group">
          									<label class="col-form-label col-md-3 col-sm-3 label-align for="status">마일스톤 상태</label>
											<div class="col-md-6 col-sm-6 ">
            									<select class="form-control form-control-sm" id="status" name="status">
													<option
														{{#ifCond mileVO.status "==" "B301"}}
															selected
														{{/ifCond}}
													value="B301">미해결</option>
              										<option
														{{#ifCond mileVO.status "==" "B302"}}
              												selected
														{{/ifCond}}
													value="B302">해결</option>
												</select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="title">마일스톤 제목</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="title" class="form-control form-control-sm" name="title" value="{{mileVO.title}}">
											</div>
										</div>
										<div class="form-group">
											<textarea class="myMileModifySummernote" name="content" >{{{mileVO.content}}}</textarea>
										</div>
										<div class="form-group">
											<label class=" label-align mt-3">이슈 등록 </label>
											<select id="myMileModifyIssueTag" name="issueList" multiple="multiple" data-placeholder="추가하실 이슈를 선택해주세요">
												{{#each wholeIssueList}}
												<option value="{{issueNo}}">{{title}}</option>
												{{/each}}
											</select>
										</div>
										<div class="col-md-7 col-sm-7 offset-md-3 mt-3" style="text-align:right;">
											<button type="button" class="btn btn-success" onclick='modifyMyMilestone();'>수정</button>
											<button type="button" class="btn btn-primary" onclick="cancelMyMilestone();">취소</button>
											<button type="reset" class="btn btn-info">리셋</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>
	
		<script type="text/x-handlebars-template" id="myIssueRegistFormTemplate">
			<div class="row" id="fadeInMyIssueContent">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title row d-flex justify-content-between">
							<h5 class="title">
								<i class="fa fa-clone"></i> <span class="task-bold task-sm">이슈 등록</span>
							</h5>
							<div class="clearfix">
								<button onclick="cancelMyMilestone();" class="btn btn-primary btn-sm">X</button>
							</div>
						</div>
						<div class="x_content">
							<div class="row">
								<div class="x_content">
									<form id="registMyIssueForm" method="post" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
										<!-- 프로젝트명 -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="projTitle">프로젝트명</label>
											<div class="col-md-6 col-sm-6 ">
												<select class="form-control form-control-sm" id="myIssueRegistProjTitle" name="projNo">
													{{#each .}}
														<option value="{{projNo}}">{{projTitle}}</option>
													{{/each}}
		            							</select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">제기자</label>
											<div class="col-md-6 col-sm-6 ">
												<select class="form-control form-control-sm" id="myIssueRegistUserNickname" name="userId" reaonly>
		              								<option value="${userVO.userId}">${userVO.nickname}</option>
		            							</select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align for="important">중요도</label>
											<div class="col-md-6 col-sm-6 ">
     											<select class="form-control form-control-sm" id="myIssueRegistImportant" name="important">
       												<option value="B401">낮음</option>
       												<option value="B402">중간</option>
      												<option value="B403">높음</option>
     											</select>
											</div>
 										</div>
										<div class="item form-group">
   											<label class="col-form-label col-md-3 col-sm-3 label-align for="status">진행상태</label>
											<div class="col-md-6 col-sm-6 ">
     											<select class="form-control form-control-sm" id="myIssueRegistStatus" name="status">
       												<option value="B501">진행전</option>
       												<option value="B502">진행중</option>
      												<option value="B503">완료</option>
     											</select>
											</div>
 										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="startdate">시작일</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" id="myIssueRegistStartdate" name="startdate" class="form-control form-control-sm">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="enddate">마감일</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" id="myIssueRegistEnddate" name="enddate" class="form-control form-control-sm">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="title">이슈명</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="myIssueRegistTitle" class="form-control form-control-sm" name="title">
											</div>
										</div>
										<div class=form-group">
											<textarea class="myIssueRegistSummernote" name="content" ></textarea>
										</div>
										<div style="width:100%; float:left">
											<input name="files" id="myIssueRegistUpload" type="file" aria-label="files" />
										</div>
										<div class="col-md-12 col-sm-12 mt-3" style="text-align:right;">
											<button type="button" class="btn btn-success" onclick='registMyIssue()'>등록</button>
											<button type="button" class="btn btn-primary" onclick="cancelMyMilestone();">취소</button>
											<button type="reset" class="btn btn-info">리셋</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>	
		
		<script type="text/x-handlebars-template" id="myIssueModifyFormTemplate">
			<div class="row" id="fadeInMyIssueContent">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title row d-flex justify-content-between">
							<h5 class="title">
								<i class="fa fa-clone"></i> <span class="task-bold task-sm">이슈 수정</span>
							</h5>
							<div class="clearfix">
								<button onclick="cancelMyMilestone();" class="btn btn-primary btn-sm">X</button>
							</div>
						</div>
						<div class="x_content">
							<div class="row">
								<div class="x_content">
									<form id="modifyMyIssueForm" method="post" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
										<!-- 프로젝트명 -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="projTitle">프로젝트명</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="myIssueModifyProjTitle" required="required" class="form-control form-control-sm" name="projTitle" value="{{projTitle}}" readonly>
												<input type="hidden" id="myIssueModifyIssueNo" name="issueNo" value="{{issueVO.issueNo}}">
												<input type="hidden" id="myIssueModifyProjNo" name="projNo" value="{{issueVO.projNo}}">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="userNickname">제기자</label>
											<div class="col-md-6 col-sm-6 ">
												<select class="form-control form-control-sm" id="myIssueModifyUserNickname" name="userId" reaonly>
													<option value="${userVO.userId}">${userVO.nickname}</option>
												</select>
											</div>
										</div>
										<div class="item form-group">
	          								<label class="col-form-label col-md-3 col-sm-3 label-align" for="important">중요도</label>
											<div class="col-md-6 col-sm-6 ">
	            								<select class="form-control form-control-sm" id="myIssueModifyImportant" name="important">
													<option
														{{#ifCond issueVO.important "==" "낮음"}}
			              									selected
														{{/ifCond}}
													value="B401">낮음</option>
													<option
														{{#ifCond issueVO.important "==" "중간"}}
			              									selected
														{{/ifCond}}
													value="B402">중간</option>
													<option
														{{#ifCond issueVO.important "==" "높음"}}
			              									selected
														{{/ifCond}}
													value="B403">높음</option>
	            								</select>
											</div>
	        							</div>
										<div class="item form-group">
          									<label class="col-form-label col-md-3 col-sm-3 label-align" for="status">이슈 상태</label>
											<div class="col-md-6 col-sm-6 ">
            									<select class="form-control form-control-sm" id="myIssueModifyStatus" name="status">
              										<option
														{{#ifCond issueVO.status "==" "진행전"}}
			              									selected
														{{/ifCond}}
													value="B501">진행전</option>
              										<option
														{{#ifCond issueVO.status "==" "진행중"}}
			              									selected
														{{/ifCond}}
													value="B502">진행중</option>
              										<option
														{{#ifCond issueVO.status "==" "완료"}}
			              									selected
														{{/ifCond}}
													value="B503">완료</option>
            									</select>
											</div>
        								</div>
        								<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="startdate">시작일</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" id="myIssueModifyStartdate" value="{{formatTime issueVO.startdate "yyyy-MM-DD"}}" name="startdate" class="form-control form-control-sm">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="enddate">마감일</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" id="myIssueModifyEnddate" value="{{formatTime issueVO.enddate "yyyy-MM-DD"}}" name="enddate" class="form-control form-control-sm">
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="title">이슈명</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="myIssueModifyTitle" class="form-control form-control-sm" name="title" value="{{issueVO.title}}">
											</div>
										</div>
										<div class="form-group">
											<textarea class="myIssueModifySummernote" id="myIssueModifyContent" name="content">{{{issueVO.content}}}</textarea>
										</div>
										<div style="width:100%; float:left">
											<input name="files" id="myIssueModifyUpload" type="file" aria-label="files" />
										</div>
										<div class="col-md-12 col-sm-12 mt-3" style="text-align:right;">
											<button type="button" class="btn btn-success" onclick='modifyMyIssue();'>수정</button>
											<button type="button" class="btn btn-primary" onclick="cancelMyMilestone();">취소</button>
											<button type="reset" class="btn btn-info">리셋</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>
		
		<script>
			function printData(data, target, templateObject){
				var template = Handlebars.compile(templateObject.html());
				var html = template(data);
				target.html('').html(html);
			}
			
			function myIssueModifyForm(data){
				printData(data, $('#myMileOverlay'), $('#myIssueModifyFormTemplate'));
			}
			
			function myIssueRegistForm(data){
				printData(data, $('#myMileOverlay'), $('#myIssueRegistFormTemplate'));
			}
			
			function myMileRegistForm(data){
				printData(data, $('#myMileOverlay'), $('#myMileRegistFormTemplate'));
			}

			function myMileModifyForm(data){
				printData(data, $('#myMileOverlay'), $('#myMileModifyFormTemplate'));
			}
			
			function cancelMyMilestone(){
				myOverlayOff('#myMileOverlay');
			}
			
			Handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {
			    switch (operator) {
			        case '==':
			            return (v1 == v2) ? options.fn(this) : options.inverse(this);
			        case '===':
			            return (v1 === v2) ? options.fn(this) : options.inverse(this);
			        case '!=':
			            return (v1 != v2) ? options.fn(this) : options.inverse(this);
			        case '!==':
			            return (v1 !== v2) ? options.fn(this) : options.inverse(this);
			        case '<':
			            return (v1 < v2) ? options.fn(this) : options.inverse(this);
			        case '<=':
			            return (v1 <= v2) ? options.fn(this) : options.inverse(this);
			        case '>':
			            return (v1 > v2) ? options.fn(this) : options.inverse(this);
			        case '>=':
			            return (v1 >= v2) ? options.fn(this) : options.inverse(this);
			        case '&&':
			            return (v1 && v2) ? options.fn(this) : options.inverse(this);
			        case '||':
			            return (v1 || v2) ? options.fn(this) : options.inverse(this);
			        default:
			            return options.inverse(this);
			    }
			});
		</script>
	</body>
</html>
	