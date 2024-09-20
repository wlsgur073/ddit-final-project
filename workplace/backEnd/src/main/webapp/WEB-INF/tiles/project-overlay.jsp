<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#popoverlay {
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
	<div id="popoverlay"></div>
	<!-- 오버레이 끝 -->


<!------------------------------- 마일스톤 등록 ----------------------------->
<script type="text/x-handlebars-template" id="mileStoneRegistFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">마일스톤 등록</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
								<form id="registMileForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="projTitle" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{projTitle}}" disabled>
								</div>
							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											제기자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="userNickname" name="userId">
              									<option value="{{userVO.userId}}">{{userVO.nickname}}</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="status">
											마일스톤 상태
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option	value="B301">미완료</option>
              									<option	value="B302">완료</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="title">마일스톤 제목
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="title" class="form-control form-control-sm" name="title">
										</div>
									</div>
									<div class=form-group">
										<textarea class="projSummnote" name="content" ></textarea>
									</div>
									<div class=form-group">
										<label class=" label-align mt-3">이슈 등록 </label>
										<select id="mileIssueTag" name="issueList" multiple="multiple" data-placeholder="추가하실 이슈를 선택해주세요">
										{{#each wholeIssueList}}
											<option value="{{issueNo}}">{{title}}</option>
										{{/each}}
        								</select>
									</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='registMileDetail()'>등록</button>
										<button class="btn btn-primary" type="button" onclick="off()">취소</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!------------------------------- 마일스톤 수정 ----------------------------->
<script type="text/x-handlebars-template" id="mileStoneModifyFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">마일스톤 수정</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
								<form id="modifyMileForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="projTitle" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{mileVO.projTitle}}" disabled>
									<input type="hidden" name="mileNo" value="{{mileVO.mileNo}}">
								</div>
							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											제기자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="userNickname" name="userId">
              									<option value="{{mileVO.userId}}">{{mileVO.nickname}}</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="status">
											마일스톤 상태
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option
												{{#ifCond mileVO.status "==" "B301"}}
              									selected
												{{/ifCond}}
												value="B301">미완료</option>
              									<option
												{{#ifCond mileVO.status "==" "B302"}}
              									selected
												{{/ifCond}}
												value="B302">완료</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="title">마일스톤 제목
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="title" class="form-control form-control-sm" name="title" value="{{mileVO.title}}">
										</div>
									</div>
									<div class=form-group">
										<textarea class="projSummnote" name="content" >{{{mileVO.content}}}</textarea>
									</div>
									<div class=form-group">
										<label class=" label-align mt-3">이슈 등록 </label>
										<select id="mileIssueTag" name="issueList" multiple="multiple" data-placeholder="추가하실 이슈를 선택해주세요">
										{{#each wholeIssueList}}
											<option value="{{issueNo}}">{{title}}</option>
										{{/each}}
        								</select>
									</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='modifyMileDetail()'>수정</button>
										<button class="btn btn-primary" type="button" onclick="off()">취소</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!------------------------------- 이슈 등록 ----------------------------->
<script type="text/x-handlebars-template" id="issueRegistFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">이슈 등록</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
								<form id="registIssueForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="projTitle" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{projTitle}}" readonly>
								</div>
							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											제기자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="userNickname" name="userId">
              									<option value="{{member.userId}}">{{member.nickname}}</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="important">
											중요도
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="important" name="important">
												<option value="B401">낮음</option>
              									<option value="B402">보통</option>
             									<option value="B403">높음</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="status">
											이슈 상태
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option value="B501">진행전</option>
              									<option value="B502">진행중</option>
             									<option value="B503">완료</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="startdate">시작일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="startdate" name="startdate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="enddate">종료일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="enddate" name="enddate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="title">이슈 제목
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="title" class="form-control form-control-sm" name="title">
										</div>
									</div>
									<div class=form-group">
										<textarea class="projSummnote" name="content" ></textarea>
									</div>
									<div style="width:100%; float:left">
											<input name="files" id="projectIssueUpload" type="file" aria-label="files" />
										</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='registIssue()'>등록</button>
										<button class="btn btn-primary" type="button" onclick="off()">취소</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!------------------------------- 이슈 수정 ----------------------------->
<script type="text/x-handlebars-template" id="issueModifyFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">이슈 수정</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
								<form id="modifyIssueForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="projTitle" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{projTitle}}" readonly>
									<input type="hidden" name="issueNo" value="{{issueVO.issueNo}}">
								</div>
							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											제기자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="userNickname" name="userId">
              									<option value="{{issueVO.userId}}">{{issueVO.nickname}}</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="important">
											중요도
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="important" name="important">
												<option value="B401"
											{{#ifCond issueVO.important "==" "B401"}}
              									selected
											{{/ifCond}}
												>낮음</option>
												<option value="B402"
											{{#ifCond issueVO.important "==" "B402"}}
              									selected
											{{/ifCond}}
												>보통</option>
												<option value="B403"
											{{#ifCond issueVO.important "==" "B403"}}
              									selected
											{{/ifCond}}
												>높음</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="status">
											이슈 상태
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option value="B501"
											{{#ifCond issueVO.status "==" "B501"}}
              									selected
											{{/ifCond}}
												>진행전</option>
              									<option value="B502"
											{{#ifCond issueVO.status "==" "B502"}}
              									selected
											{{/ifCond}}
												>진행중</option>
              									<option value="B503"
											{{#ifCond issueVO.status "==" "B503"}}
              									selected
											{{/ifCond}}
												>완료</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="startdate">시작일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="startdate" value="{{formatTime issueVO.startdate "yyyy-MM-DD"}}" name="startdate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="enddate">종료일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="enddate" value="{{formatTime issueVO.enddate "yyyy-MM-DD"}}" name="enddate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="title">이슈 제목
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="title" class="form-control form-control-sm" name="title" value="{{issueVO.title}}">
										</div>
									</div>
									<div class=form-group">
										<textarea class="projSummnote" name="content" >{{{issueVO.content}}}</textarea>
									</div>
									<div style="width:100%; float:left">
											<input name="files" id="projectIssueUpload" type="file" aria-label="files" />
										</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='modifyIssueDetail()'>수정</button>
										<button class="btn btn-primary" type="button" onclick="off()">취소</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!------------------------------- 업무 등록 ----------------------------->
<script type="text/x-handlebars-template" id="taskRegistFormTemplate">
		<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">업무 등록</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
								<form id="registTaskForm" method="post" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="projTitle" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{projTitle}}" readonly>
									<input type="hidden" value="{{projNo}}">
								</div>
							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											담당자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="userNickname" name="userId">
												{{#getRoleCheck 'A303'}}
												{{#each userList}}
              									<option value="{{this.userId}}">{{this.nickname}}</option>
												{{/each}}
												{{/getRoleCheck}}
												{{#getRoleCheck 'A302'}}
												<option value="${userVO.userId}">${userVO.nickname}</option>
												{{/getRoleCheck}}
            								</select>
										</div>
        							</div>
									<div class="row d-flex justify-content-center">
									<div class="item form-group" style="margin-right:10px;">
          								<label class="col-form-label mr-3 label-align for="important">
											중요도
										</label>
										<div class="">
            								<select class="form-control form-control-sm" id="important" name="important">
              									<option value="B101">낮음</option>
              									<option value="B102">보통</option>
             									<option value="B103">높음</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group" style="margin-right:55px;">
          								<label class="col-form-label mr-3 label-align for="status">
											진행상태
										</label>
										<div class="">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option value="B201">예정</option>
              									<option value="B202">진행중</option>
             									<option value="B203">지연중</option>
             									<option value="B204">완료</option>
            								</select>
										</div>
        							</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="startdate">시작일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="startdate" name="startdate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="enddate">마감일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="enddate" name="enddate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="title">업무 제목
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="title" class="form-control form-control-sm" name="title">
										</div>
									</div>
									<div class=form-group">
										<textarea class="projSummnote" name="content" ></textarea>
									</div>
									 <div style="width:100%; float:left">
           								 <input name="files" id="projectTaskUpload" type="file" aria-label="files" />
        							</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='registTask()'>등록</button>
										<button class="btn btn-primary" type="button" onclick="off()">취소</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!------------------------------- 업무 수정 ----------------------------->
<script type="text/x-handlebars-template" id="taskModifyFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">업무 수정</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
								<form id="modifyTaskForm" method="post" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="title">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="title" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{projTitle}}" readonly>
									<input type="hidden" name="taskNo" value="{{taskNo}}">
								</div>
							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											담당자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="userNickname" name="userId">
												{{#each userList}}
              									<option value="{{this.userId}}">{{this.nickname}}</option>
												{{/each}}
            								</select>
										</div>
        							</div>
									<div class="row d-flex justify-content-center">
									<div class="item form-group" style="margin-right:65px;">
          								<label class="col-form-label mr-3 label-align for="important">
											중요도
										</label>
										<div class="">
            								<select class="form-control form-control-sm" id="important" name="important">
												<option value="B101"
											{{#ifCond important "==" "B101"}}
              									selected
											{{/ifCond}}
												>낮음</option>
												<option value="B102"
											{{#ifCond important "==" "B102"}}
              									selected
											{{/ifCond}}
												>보통</option>
												<option value="B103"
											{{#ifCond important "==" "B103"}}
              									selected
											{{/ifCond}}
												>높음</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label mr-3 label-align for="status">
											진행상태
										</label>
										<div class="">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option value="B201"
											{{#ifCond status "==" "B201"}}
              									selected
											{{/ifCond}}
												>예정</option>
              									<option value="B202"
											{{#ifCond status "==" "B202"}}
              									selected
											{{/ifCond}}
												>진행중</option>
              									<option value="B203"
											{{#ifCond status "==" "B203"}}
              									selected
											{{/ifCond}}
												>지연중</option>
              									<option value="B204"
											{{#ifCond status "==" "B204"}}
              									selected
											{{/ifCond}}
												>완료</option>
            								</select>
										</div>
        							</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="startdate">시작일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="startdate" value="{{formatTime startdate "yyyy-MM-DD"}}" name="startdate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="enddate">마감일
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="date" id="enddate" value="{{formatTime enddate "yyyy-MM-DD"}}" name="enddate" class="form-control form-control-sm">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="title">업무 제목
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="title" value="{{title}}" class="form-control form-control-sm" name="title">
										</div>
									</div>
									<div class=form-group">
										<textarea class="projSummnote" name="content" >{{{content}}}</textarea>
									</div>
									<div style="width:100%; float:left">
           								 <input name="files" id="projectTaskUpload" type="file" aria-label="files" />
        							</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='modifyTaskDetail()'>수정</button>
										<button class="btn btn-primary" type="button" onclick="off()">취소</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>




<!----------------------------프로젝트 선택 ----------------------------->
<script type="text/x-handlebars-template" id="projRegistTypeTemplate">
<div class="row" style="display:none;" id="fadeInContent">
  <div class="col-md-12">
    <div class="x_panel">
      <div class="x_title row d-flex justify-content-between">
        <h5 class="title">
          <i class="fa fa-clone"></i>
          <span class="task-bold task-sm">프로젝트 등록</span>
        </h5>
        <div class="clearfix">
          <button onclick="off()" class="btn btn-primary btn-sm">X</button>
        </div>
      </div>
      <div class="x_content">
        <div class="row">
          <div class="x_content">
			<div class="projRegistPicture rounded rounded-3" style="background-image: url('/resources/asserts/images/project-personal.png');"
				onclick="getOverlayTemplate('projRegistFormTemplate');">
			</div>
				<p class="text-center">개인 프로젝트 생성</p>
			<div class="projRegistPicture rounded rounded-3" style="background-image: url('/resources/asserts/images/project-team.png');"
				onclick="getOverlayTemplate('projInviteFormTemplate');">
			</div>
				<p class="text-center">구성원을 초대해 프로젝트 생성</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</script>

<script type="text/x-handlebars-template" id="projInviteFormTemplate">
        <div class="row" style="display:none;" id="fadeInContent">
  <div class="col-md-12">
    <div class="x_panel">
      <div class="x_title row d-flex justify-content-between">
        <h5 class="title">
          <i class="fa fa-clone"></i>
          <span class="task-bold task-sm">프로젝트 등록</span>
        </h5>
        <div class="clearfix">
          	<button onclick="off()" class="btn btn-primary btn-sm">X</button>
        </div>
      </div>
      <div class="x_content">
        <div class="row">
          <!-- 내용저장 -->
          <div class="x_content">
            <form>
			<div class="p-4">
              <p>다른 구성원들과 함께 프로젝트를 만들어나가보세요</p>
              <p>다른 팀과 협업 공간을 만들고 함께 일해보세요.</p>
			</div>
              <div class="card-body">
                <div class="form-group">
                  <label for="exampleInputEmail1">이메일로 초대</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" placeholder="상대방의 이메일을 입력하세요.">
                </div>
                <!-- select -->
                <div class="form-group">
                  <label>접근 권한</label>
                  <select class="form-control">
                    <option value="" disabled selected hidden>접근 권한을 설정해 주세요.</option>
                    <option>Observer</option>
                    <option>Cooperator</option>
                    <option>Partner</option>
                    <option>Collaborator</option>
                  </select>
                </div>
                <!-- textarea -->
                <div class="form-group">
                  <label>보낼 메세지</label>
                  <textarea class="form-control" rows="3" placeholder="Enter ..."></textarea>
                </div>
              </div>
              <!-- /.card-body -->
              <button type="submit" class="btn  btn-outline-primary collabo-submit-btn">보내기</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</script>

<!---------------------------- 프로젝트 등록 ----------------------------->
<script type="text/x-handlebars-template" id="projRegistFormTemplate">
<div class="row" style="display:none;" id="fadeInContent">
<div class="col-md-12">
  <div class="x_panel">
    <div class="x_title row d-flex justify-content-between">
      <h5 class="title">
        <i class="fa fa-clone"></i>
        <span class="task-bold task-sm">프로젝트 등록</span>
      </h5>
      <div class="clearfix">
        <button onclick="off()" class="btn btn-primary btn-sm">X</button>
      </div>
    </div>
    <div class="x_content">
      <div class="row">
        <div class="x_content">
			<form id="registProjectForm" method="post" data-parsley-validate=""
				class="form-horizontal form-label-left" novalidate="">
			<!-- 프로젝트명 -->
					<div class="item form-group">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="title">프로젝트명
						</label>
						<div class="col-md-6 col-sm-6 ">
							<input type="text" id="title" required="required"
							placeholder="프로젝트명을 입력해주세요." class="form-control form-control-sm" name="title">
						</div>
					</div>
			<!-- 공개범위 -->
			<div class="item form-group">
          		<label class="col-form-label col-md-3 col-sm-3 label-align for="privacy">
					공개범위
				</label>
					<div class="col-md-6 col-sm-6 ">
            			<select class="form-control form-control-sm" required="required" name="privacy" id="privacy">
							<option value="A401" selected>공개</option>
							<option value="A402">제한</option>
							<option value="A403">비공개</option>
            			</select>
					</div>
        		</div>
			<!-- 시작일 -->
			<div class="item form-group">
				<label class="col-form-label col-md-3 col-sm-3 label-align"
					for="startdate">시작일
				</label>
				<div class="col-md-6 col-sm-6 ">
					<input type="date" id="startdate" required="required" class="form-control form-control-sm" name="startdate">
				</div>
			</div>
			<!-- 종료일 -->
			<div class="item form-group">
				<label class="col-form-label col-md-3 col-sm-3 label-align"
					for="enddate">마감일</span>
				</label>
				<div class="col-md-6 col-sm-6 ">
					<input type="date" id="enddate" class="form-control form-control-sm" name="enddate">
				</div>
			</div>
			<!-- 프로젝트 소개 -->
			<div class="form-group">
				<label class=" label-align"> 프로젝트 소개 </label>
				<textarea class="projSummnote" name="intro" required="required"></textarea>
			</div>
			 <div class="item form-group">
				<label class="control-label col-md-3 col-sm-3 label-align">태그 추가</label>
                  			<div class="col-md-9 col-sm-9 ">
								<input type="text" data-role="tagsinput" id="projectTag"
									name="projectTag" class="form-control form-control-sm" name="projectTag">
							</div>
             </div>
			<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
				<button type="button" class="btn btn-success" onclick='registProject()'>등록</button>
				<button class="btn btn-primary" type="button" onclick='off()'>취소</button>
			</div>
		</form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</script>


<!---------------------------- 프로젝트 수정 ----------------------------->
<script type="text/x-handlebars-template" id="projModifyFormTemplate">
<div class="row" style="display:none;" id="fadeInContent">
<div class="col-md-12">
  <div class="x_panel">
    <div class="x_title row d-flex justify-content-between">
      <h5 class="title">
        <i class="fa fa-clone"></i>
        <span class="task-bold task-sm">프로젝트 수정</span>
      </h5>
      <div class="clearfix">
        <button onclick="off()" class="btn btn-primary btn-sm">X</button>
      </div>
    </div>
    <div class="x_content">
      <div class="row">
        <div class="x_content">
			<form id="modifyProjectForm" method="post" data-parsley-validate=""
				class="form-horizontal form-label-left" novalidate="">
			<!-- 프로젝트명 -->
					<div class="item form-group">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="title">프로젝트명 <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 ">
							<input type="text" id="title" required="required" class="form-control form-control-sm" name="title" value="{{title}}">
						</div>
					</div>
			<!-- 공개범위 -->
			<div class="item form-group">
          		<label class="col-form-label col-md-3 col-sm-3 label-align for="privacy">
					공개범위
				</label>
					<div class="col-md-6 col-sm-6 ">
            			<select class="form-control form-control-sm" required="required" name="privacy" id="privacy">
							<option value="A401"
							{{#ifCond privacy '==' "공개"}}
              					selected
							{{/ifCond}}
							>공개</option>
							<option value="A402"
							{{#ifCond privacy '==' "제한"}}
              					selected
							{{/ifCond}}
							>제한</option>
							<option value="A403"
							{{#ifCond privacy '==' "비공개"}}
              					selected
							{{/ifCond}}
							>비공개</option>
            			</select>
					</div>
        		</div>
			<!-- 시작일 -->
			<div class="item form-group">
				<label class="col-form-label col-md-3 col-sm-3 label-align"
					for="startdate">시작일 <span class="required">*</span>
				</label>
				<div class="col-md-6 col-sm-6 ">
					<input type="date" id="startdate" required="required" class="form-control form-control-sm" name="startdate" value={{startdate}}>
				</div>
			</div>
			<!-- 종료일 -->
			<div class="item form-group">
				<label class="col-form-label col-md-3 col-sm-3 label-align"
					for="enddate">마감일</span>
				</label>
				<div class="col-md-6 col-sm-6 ">
					<input type="date" id="enddate" class="form-control form-control-sm" name="enddate" value={{enddate}}>
				</div>
			</div>
			<!-- 프로젝트 소개 -->
			<div class=form-group">
				<label class=" label-align"> 프로젝트 소개 </label>
				<textarea class="projSummnote" name="intro" required="required">{{intro}}</textarea>
			</div>
			<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
				<button type="button" class="btn btn-success" onclick='modifyProjectDetail()'>수정</button>
				<button class="btn btn-primary" type="button" onclick="off()">취소</button>
			</div>
		</form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</script>

<!--------------------------- 프로젝트 공지 등록 ----------------------------->
<script type="text/x-handlebars-template" id="noticeRegistFormTemplate">
<div class="row" style="display:none;" id="fadeInContent">
<div class="col-md-12">
  <div class="x_panel">
    <div class="x_title row d-flex justify-content-between">
      <h5 class="title">
        <i class="fa fa-clone"></i>
        <span class="task-bold task-sm">공지사항 등록</span>
      </h5>
      <div class="clearfix">
        <button onclick="off()" class="btn btn-primary btn-sm">X</button>
      </div>
    </div>
    <div class="x_content">
      <div class="row">
        <div class="x_content">
			<form id="modifyProjectNotice" method="post" class="form-horizontal form-label-left">
			<!-- 공지 제목 -->
					 <div class="item form-group custom-validate">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="notice">공지 제목 <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 ">
							<input type="text" id="notice" class="form-control form-control-sm" name="notice" cv="false">
								<small>제목은 필수입력사항입니다.</small>
						</div>

					</div>

			<div class="form-group custom-validate">
				<label class=" label-align">공지 내용 </label>
				<textarea class="projSummnote" name="noticeCont"></textarea>
			</div>
			<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
				<button type="button" class="btn btn-success" onclick='modifyProjectNotice()'>등록</button>
				<button class="btn btn-primary" type="button" onclick="off()">취소</button>
			</div>

		</form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</script>

<!--------------------------- 프로젝트 공지 수정 ----------------------------->
<script type="text/x-handlebars-template" id="noticeModifyFormTemplate">


<div class="row" style="display:none;" id="fadeInContent">
<div class="col-md-12">
  <div class="x_panel">
    <div class="x_title row d-flex justify-content-between">
      <h5 class="title">
        <i class="fa fa-clone"></i>
        <span class="task-bold task-sm">공지사항 수정</span>
      </h5>
      <div class="clearfix">
        <button onclick="off()" class="btn btn-primary btn-sm">X</button>
      </div>
    </div>
    <div class="x_content">
      <div class="row">
        <div class="x_content">
			<form id="modifyProjectNotice" method="post" data-parsley-validate=""
				class="form-horizontal form-label-left" novalidate="">
			<!-- 공지 제목 -->
					<div class="item form-group custom-validate">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="notice">공지 제목 <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 ">
							<input type="text" id="notice" class="form-control form-control-sm" name="notice" cv="false" value={{{notice}}}>
								<small>제목은 필수입력사항입니다.</small>
						</div>
					</div>
			<!-- 공지 내용 -->
			<div class=form-group">
				<label class=" label-align">공지 내용 </label>
				<textarea class="projSummnote" name="noticeCont" required="required">{{{noticeCont}}}</textarea>
			</div>
			<div class="col-md-7 col-sm-7 offset-md-3 mt-3">
				<button type="button" class="btn btn-success" onclick='modifyProjectNotice()'>수정</button>
				<button class="btn btn-primary" type="button" onclick='deleteProjectNotice()'>삭제</button>
				<button class="btn btn-primary" type="button" onclick="off()">취소</button>
			</div>

		</form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</script>


<!--------------------------- 중요파일 첨부 ----------------------------->
<script type="text/x-handlebars-template" id="uploadProjectDocumentForm">
<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">중요첨부파일 등록</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
						<div class="x_content">
							<form id="uploadProjectDocument" method="post" data-parsley-validate=""
								class="form-horizontal form-label-left" novalidate="">
							<div style="width:100%; float:left">
           						<input name="files" id="projectDocument" type="file" aria-label="files" />
        					</div>
						</div>
						<div class="col-md-4 col-sm-4 offset-md-4 mt-3">
							<button type="button" class="btn btn-success" onclick='uploadProjectDoc()'>등록</button>
							<button class="btn btn-primary" type="button" onclick="off()">취소</button>
						</div>
							</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!--------------------------- 유저 권한 변경 ----------------------------->
<script type="text/x-handlebars-template" id="modifyUserRoleForm">
<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">구성원 권한부여</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
						<p>프로젝트 구성원의 권한을 변경할 수 있습니다.</p>
						<!-- 팀장 설정시 visible로 -->
						<p>팀장을 양도할 경우 현 프로젝트에서 팀장으로써의 모든 권한을 잃게 됩니다.</p>
				<form id="modifyUserRole" method="post" data-parsley-validate=""
				class="form-horizontal form-label-left mt-5" novalidate="">
				<div class="item form-group col-md-12">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="userId">구성원 이름
						</label>
						<div class="col-md-6 col-sm-6">
							<select class="form-control form-control-sm" required="required" name="userId" id="userId">
							{{#each userList}}
								<option {{#ifCond ../clickName "==" nickname}} selected {{/ifCond}} value="{{userId}}">{{nickname}}</option>
							{{/each}}
							</select>
						</div>
				</div>
				<div class="item form-group col-md-12">
          		<label class="col-form-label col-md-3 col-sm-3 label-align for="role">
					권한
				</label>
					<div class="col-md-6 col-sm-6 ">
            			<select class="form-control form-control-sm" required="required" name="role" id="role">
						<option value="" disabled selected hidden>변경할 권한을
							설정해주세요</option>
						<option value="A301">방문자</option>
						<option value="A302" selected>팀원</option>
						<option value="A303">팀장</option>
					</select>
				</div>
			</div>
			<div class="col-md-6 col-sm-6 offset-md-4 mt-3">
				<button type="button" class="btn btn-success" onclick="modifyUserRoleSubmit()">수정</button>
				<button class="btn btn-primary" type="button" onclick="off()">취소</button>
			</div>
		</form>
        </div>
      </div>
	</div>
  </div>
</script>

<!---------------------------- 유저 제명 ---------------------------->
<script type="text/x-handlebars-template" id="removeUserProjectForm">
<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">구성원 제명</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
						<span class="mt-3">프로젝트에서 해당 구성원을 제명합니다.</span>
						<p>*해당 구성원이 작성한 업무와 문서는 삭제되지 않습니다.</p>
						<form id="removeUserProject" method="post" data-parsley-validate=""
							class="form-horizontal form-label-left mt-3" novalidate="">
							<div class="item form-group col-md-12">
						<label class="col-form-label col-md-4 col-sm-4 label-align"
							for="userId">구성원 이름
						</label>
						<div class="col-md-6 col-sm-6">
							<select class="form-control form-control-sm" required="required" name="userId" id="userId">
							{{#each userList}}
								<option {{#ifCond ../clickName "==" nickname}} selected {{/ifCond}} value="{{userId}}">{{nickname}}</option>
							{{/each}}
							</select>
							</div>
						</div>
							<!-- textarea -->
							<div class="form-group">
								<textarea class="form-control" rows="10"
									placeholder="제명 사유를 적어주세요." name="content"></textarea>
							</div>
							<!-- textarea -->
							<div class="form-group">
								<label for="project-member-delete">확인을 위해 "<strong
									class="text-danger">프로젝트 구성원 제명</strong>" 를 입력해주세요.
								</label> <input type="text" class="form-control"
									id="project-member-delete" name="delete_validate"/>
							</div>
					<div class="col-md-6 col-sm-6 offset-md-4 mt-3">
						<button type="button" class="btn btn-danger" onclick="removeUserRoleSubmit();">제명</button>
						<button class="btn btn-primary" type="button" onclick="off()">취소</button>
					</div>
					</form>
				</div>
      </div>
	</div>
  </div>
</script>

<!---------------------------- 유저 탈퇴 ---------------------------->
<script type="text/x-handlebars-template" id="quitUserProjectForm">
<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">구성원 제명</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
						<span class="mt-3">해당 프로젝트에서 탈퇴합니다.</span>
						<p>*자신이 작성한 업무와 문서는 탈퇴해도 삭제되지 않습니다.</p>
						<form id="quitUserProject" method="post" data-parsley-validate=""
							class="form-horizontal form-label-left mt-3" novalidate="">
						<div class="item form-group col-md-12">
							<label class="col-form-label col-md-4 col-sm-4 label-align"
								for="userId">구성원 이름
							</label>
							<div class="col-md-6 col-sm-6">
								<select class="form-control form-control-sm" required="required" name="userId" id="userId">
									<option selected value="${userVO.userId}">${userVO.nickname}</option>
								</select>
							</div>
						</div>
							<!-- textarea -->
							<div class="form-group">
								<textarea class="form-control" rows="10"
									placeholder="탈퇴 사유를 적어주세요." name="content"></textarea>
							</div>
							<!-- textarea -->
							<div class="form-group">
								<label for="project-member-delete">확인을 위해 "<strong
									class="text-danger">해당 프로젝트 탈퇴</strong>" 를 입력해주세요.
								</label> <input type="text" class="form-control"
									id="project-member-delete" name="delete_validate"/>
							</div>
					<div class="col-md-6 col-sm-6 offset-md-4 mt-3">
						<button type="button" class="btn btn-danger" onclick="quitUserRoleSubmit();">탈퇴</button>
						<button class="btn btn-primary" type="button" onclick="off()">취소</button>
					</div>
					</form>
				</div>
      </div>
	</div>
  </div>
</script>

<!---------------------------- 멤버 초대 ---------------------------->
<script type="text/x-handlebars-template" id="inviteMemberForm">
<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">구성원 초대</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
						<p>프로젝트를 함께 할 구성원을 초대해보세요.</p>
						<form id="inviteMemberProjectForm" method="post" data-parsley-validate=""
							class="form-horizontal form-label-left mt-3" novalidate="">

						<input type="hidden" name="userNicknameFrom" value="${userVO.nickname}">
						<input type="hidden" name="userIdFrom" value="${userVO.userId}">
					<!-- 프로젝트명 -->
					<div class="item form-group">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="title">프로젝트명
						</label>
						<div class="col-md-6 col-sm-6 ">
							<input type="text" id="title" readonly class="form-control form-control-sm" name="title" value="{{title}}">
						</div>
					</div>

						<div class="item form-group">
							<label class="col-form-label col-md-3 col-sm-3 label-align"
								for="role">부여 권한
							</label>
							<div class="col-md-6 col-sm-6">
								<select class="form-control form-control-sm" required="required" name="role" id="role">
									<option value="A301">게스트</option>
									<option value="A302">팀원</option>
								</select>
							</div>
						</div>

						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 label-align">유저 닉네임</label>
                   			<div class="col-md-9 col-sm-9 ">
								<input type="text" data-role="tagsinput" id="inviteIdTag"
									name="userTo" class="form-control form-control-sm" name="userTo">
                      		<div id="suggestions-container"style="position: relative; float: left; width: 250px; margin: 10px;"></div>
                    	</div>
						</div>
							<!-- textarea -->
							<div class="form-group">
								<textarea class="form-control" rows="10"
									placeholder="보낼 메세지를 적어주세요." name="content"></textarea>
							</div>
					<div class="col-md-6 col-sm-6 offset-md-4 mt-3">
						<button type="button" class="btn btn-success" onclick="inviteMemberSubmit();">초대</button>
						<button class="btn btn-primary" type="button" onclick="off()">취소</button>
					</div>
					</form>
				</div>
      </div>
	</div>
  </div>
</script>




<!-- 템플레이트
<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">{{dist}} 등록</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
							<div class="x_content">
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
 -->