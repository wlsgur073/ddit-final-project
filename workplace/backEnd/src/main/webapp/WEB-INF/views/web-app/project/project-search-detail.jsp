<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>
<script>
$(document).ready(function(){
	getTemplate('/app/project/getProjectByProjNo','searchProjectDetailIntro','searchProjectDetailIntroTarget');
	getTemplate('/app/project/getProjectByProjNo','searchProjectDetailNotify','searchProjectDetailNotifyTarget');
	getTemplate('/document/getDocumentListForProjDetail','searchProjectDetailDocument','searchProjectDetailDocumentTarget');
	getTemplate('/getUserByProjNo','searchProjectDetailMember','searchProjectDetailMemberTarget');
})


</script>
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
        <li class="nav-item">
          <a class="nav-link" id="detail-tab" data-toggle="tab" href="#detail" role="tab" aria-controls="detail" aria-selected="false" onclick="setTimeout(readProjDetail, 200);">프로젝트 개요</a>
        </li>
      </ul>

<div class="tab-pane fade show" id="detail" role="tabpanel" aria-labelledby="detail-tab">
	<div class="row">
		<!--  컬럼 시작 -->
		<div class="col-sm-12">
				<div class="project-detail d-flex flex-column ">

						<!-- 프로젝트 소개 시작 -->
						<div id="searchProjectDetailIntroTarget"></div>
						<script type="text/x-handlebars-template" id="searchProjectDetailIntro">
						<div class="project-detail-body mb-3 p-3">
							<div class="x_title">
								<h2 class="title">
										<i class="fa fa-edit" style="font-size: 1.3em;{{#getRoleCheck 'A303'}}cursor:pointer;"
											onclick="getOverlayModifyTemplate('projModifyFormTemplate','/app/project/getProjectByProjNo','')"
										{{/getRoleCheck}}"
										 ></i>
									 <span class="">
										{{title}} </span>
								</h2>
								<div class="clearfix">
								</div>
							</div>
							<div class="x_content mb-3" >
								<span class="project-intro">
									{{{intro}}}
									</span>
							</div>
						</div>
						<div class="project-detail-body mb-3 px-3">
							<div class="panel-body">
								<div class="task_detail mb-3">
									<div class="col-sm-12 p-0">
										<div class="col-sm-6">
											<h4 class="title">
												<i class="fa fa-calendar-o"></i> 시작일
											</h4>
											<p>{{formatTime startdate "YYYY년 MM월 DD일"}}</p>
											<div class="boxed">
												<h4 class="title">
													<i class="fa fa-unlock-alt fa-lg"></i> 공개범위
												</h4>
												<p>{{privacy}}</p>
											</div>
										</div>
										<div class="col-sm-6">
											<h4 class="title">
												<i class="fa fa-calendar-check-o"></i> 마감일
											</h4>
											<p>{{formatTime enddate "YYYY년 MM월 DD일"}}</p>
											<h4 class="title">
												<i class="fa fa-question-circle-o"></i> 진행상태
											</h4>
											<p>{{status}}</p>
										</div>

									</div>
									<div class="col-sm-12 mb-3">
										<h2 class="title">
											<i class="fa fa-hashtag"></i> 태그
										</h2>
									{{#each tagNames}}
										<span class="badge badge-success">{{this}}</span>
									{{/each}}
									</div>
								</div>
							</div>
						</div>
						</script>
						<!-- 프로젝트 소개 끝 -->



						<!-- 프로젝트 공지사항 본문 시작 -->
						<div id="searchProjectDetailNotifyTarget"></div>
						<script type="text/x-handlebars-template" id="searchProjectDetailNotify">

						<div class="project-detail-body project-notice p-3">
							<div class="x_title">
								<h2 class="title">
										<i class="fa fa-edit" style="font-size: 1.3em;{{#ifCond notice '!=' NULL}}{{#getRoleCheck 'A303'}}cursor:pointer;" onclick="getOverlayModifyTemplate('noticeModifyFormTemplate','/app/project/getProjectByProjNo','')"{{/getRoleCheck}}{{/ifCond}}"></i>
									공지사항
								</h2>
								<div class="clearfix">
								</div>
							</div>
							{{#ifCond notice '!=' NULL}}
							<div class="border round round-3 p-3 mb-3">
								<!-- 프로젝트 공지사항 예시 시작 -->
								<div class="x_title">
									<h5 class="title">
										<span>{{{notice}}}</span>
									</h5>
									<div class="clearfix"></div>
								</div>
								<div class="project-detail-body project-notice-content">
									&nbsp;{{{noticeCont}}}</div>
							</div>
							<!-- 프로젝트 공지사항 예시 끝 -->
							{{/ifCond}}
							{{#ifCond notice '==' NULL}}
                    	<div class="btn create-btn project-bg-btn" style="width:80%;" onclick="getOverlayModifyTemplate('noticeRegistFormTemplate','/app/project/getProjectByProjNo','')">
                      		<span class="text-dark">새로운 공지사항을 등록해보세요!</span>
						</div>
							{{/ifCond}}
				</script>


				<!-- 중요첨부 파일 시작 -->
				<div id="searchProjectDetailDocumentTarget"></div>
				<script type="text/x-handlebars-template" id="searchProjectDetailDocument">
				<div class="project-detail-body p-3">
					<div class="x_title">
						<h2 class="title">
							<i class="glyphicon glyphicon-hdd"></i> 중요첨부파일
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="col-sm-12">
						<ul class="row list-unstyled project-files">
							{{#each .}}
								{{#ifCond @index "<" 5}}
							<li class="d-flex align-items-center">
								<div class="btn files-btn d-flex flex-wrap flex-column align-items-center justify-content-center"  style="width:200px;height:140px">
								{{#getRoleCheck 'A303'}}
								<button type="button" class="badge badge-danger" style="margin-left:85px" onclick="deleteProjectDoc('{{path}}','{{doc_NO}}');">X</button>
								{{/getRoleCheck}}
								<a style="cursor:pointer;" onclick="projectDocumentDownload('{{{name}}}{{{extension}}}','{{projTitle}}');"><i class="fa fa-file-text fa-5x text-dark"></i></a>
								<div class="mt-2 d-flex"><span class="text-truncate" style="display:inline-block;max-width:70px;">{{{name}}}</span><span>{{{extension}}}</span></div>
							</div>
							</li>
								{{/ifCond}}
							{{/each}}
							{{#ifCond this.length "<=" 4}}
							{{#getRoleCheck 'A303'}}
							<li>
								<div class="btn create-btn files-btn" onclick="getOverlayProjDocumentTemplate('uploadProjectDocumentForm');">
									<i class="fa fa-plus fa-lg text-dark" ></i>
								</div>
							</li>
							{{/getRoleCheck}}
							{{/ifCond}}
						</ul>
						</div>
					<br />
				</div>

				</script>

				</div>
				<!-- 프로젝트 공지사항 본문 끝 -->




				<!-- 구성원 시작-->
				<div id="searchProjectDetailMemberTarget"></div>
				<script type="text/x-handlebars-template" id="searchProjectDetailMember">
				<div class="project-detail-body p-3">
					<div class="x_title header-title">
						<h2 class="title">
								<i class="fa fa-user-plus" style="font-size: 1.3em;{{#getRoleCheck 'A303'}}cursor:pointer;"
									onclick="getOverlayinviteMemberForm('inviteMemberForm')"
									{{/getRoleCheck}}"
								></i>
							 구성원
						</h2>
						<div class="clearfix header-title">
						</div>
					</div>
					<ul class="list-inline" style="display: inline-flex">
							<!-- 구성원 단위 시작 -->
							{{#each .}}
								{{#ifCond userId "!=" '0'}}
							<li class="project-member nav-item dropdown open ml-3">
								<div class="d-flex flex-column">
									<a href="javascript:;" class="project-member " aria-haspopup="true"
									id="navbarDropdown" data-toggle="dropdown"	aria-expanded="false">
										{{#ifCond picture "==" NULL}}
										<img src="/user/getPictureById?userId={{userId}}" class="img-circle" alt="Avatar" />
										{{/ifCond}}
										{{#ifCond picture "!=" NULL}}
										<img src="/user/getPictureById?userId={{userId}}" class="img-circle" alt="Avatar" />
										{{/ifCond}}
									</a>
									<div class="dropdown-menu dropdown-membermenu pull-right" aria-labelledby="navbarDropdown">
									<div class="col-md-3   widget widget_tally_box">
										<div class="fixed_height_390">
											<div class="x_content">
												<div class="flex">
													<ul class="list-inline widget_profile_box">
														<li></li>
														<li class="d-flex justify-content-center">
															{{#ifCond picture "==" NULL}}
															<img src="/user/getPictureById?userId={{userId}}" alt="" class="img-circle profile_img"></li>
															{{/ifCond}}
															{{#ifCond picture "!=" NULL}}
															<img src="/user/getPictureById?userId={{userId}}" alt="" class="img-circle profile_img"></li>
															{{/ifCond}}
														<li></li>
													</ul>
												</div>
												<div>
													<h3 class="d-flex justify-content-center mt-3">{{{nickname}}}</h3>
												</div>
												<div class="flex">
													<ul	class="list-inline count2 d-flex justify-content-between">
													<li><span class="badge badge-primary">Projects</span>
														<h3>{{projectCount}}</h3></li>
													<li><span class="badge badge-danger">Likes</span>
														<h3>{{likeCount}}</h3></li>
													<li><span class="badge badge-info">Tasks</span>
														<h3>{{taskCount}}</h3></li>
													</ul>
												</div>
												<div class="p-1" style="max-height: 100px; background-color: rgb(229,229,229);display:-webkit-box;
													-webkit-line-clamp: 5;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;">
													<p class="mt-2 mb-2">{{intro}}</p>
												</div>
												<div class="text-center mt-3">
													{{#getRoleCheck 'A303'}}
													{{#ifCond '${userVO.nickname}' "!=" nickname}}
													<button type="button" class="btn btn-success btn-sm" onclick="getOverlayModifyUserRole('modifyUserRoleForm','{{{nickname}}}');">
														<i class="fa fa-pencil-square"> 권한</i>
													</button>
													{{/ifCond}}

													{{#ifCond '${userVO.nickname}' "==" nickname}}
													<button type="button" class="btn btn-success btn-sm" onclick="alert('팀장 본인의 권한을 변경할 수 없습니다. \n팀장을 위임하면 자동으로 전환됩니다.')">
														<i class="fa fa-pencil-square"> 권한</i>
													</button>
													{{/ifCond}}

													{{#ifCond '${userVO.nickname}' "!=" nickname}}
													<button type="button" class="btn btn-danger btn-sm" onclick="getOverlayRemoveUserProject('removeUserProjectForm','{{{nickname}}}');">
														<i class="fa fa-user-times"> 제명</i>
													</button>
													{{/ifCond}}

													{{#ifCond '${userVO.nickname}' "==" nickname}}
													<button type="button" class="btn btn-danger btn-sm" onclick="alert('팀장은 프로젝트를 탈퇴할 수 없습니다. \n팀장을 위임한 후에 시도해주세요.')">
														<i class="fa fa-user-times"> 탈퇴</i>
													</button>
													{{/ifCond}}
													{{/getRoleCheck}}

													{{#ifCond '${userVO.nickname}' "==" nickname}}
													{{#getRoleCheck 'A302'}}
													<button type="button" class="btn btn-danger btn-sm" onclick="getOverlayRemoveUserProject('quitUserProjectForm','{{{nickname}}}');">
														<i class="fa fa-user-times"> 탈퇴</i>
													</button>
													{{/getRoleCheck}}
													{{/ifCond}}
													{{#ifCond '${userVO.nickname}' "==" nickname}}
													{{#getRoleCheck 'A301'}}
													<button type="button" class="btn btn-danger btn-sm" onclick="getOverlayRemoveUserProject('quitUserProjectForm','{{{nickname}}}');">
														<i class="fa fa-user-times"> 탈퇴</i>
													</button>
													{{/getRoleCheck}}
													{{/ifCond}}
												</div>
											</div>
										</div>
									</div>
									</div>
									<span>{{{nickname}}}</span>
								</div>
							</li>
								{{/ifCond}}
							{{/each}}
					</ul>


				</div>
				</script>

		</div>
	</div>
</div>
</div>
</div>
</div>
</body>
