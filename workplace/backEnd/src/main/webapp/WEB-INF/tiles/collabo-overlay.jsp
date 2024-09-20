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

<!-- 콜라보프로젝트 수정 -->	
<script type="text/x-handlebars-template" id="collaboModifyFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
<div class="col-md-12">
  <div class="x_panel">
    <div class="x_title row d-flex justify-content-between">
      <h5 class="title">
        <i class="fa fa-clone"></i>
        <span class="task-bold task-sm">콜라보 프로젝트 수정</span>
      </h5>
      <div class="clearfix">
        <button onclick="off()" class="btn btn-primary btn-sm">X</button>
      </div>
    </div>
    <div class="x_content">
      <div class="row">
        <div class="x_content">
			<form id="modifyCollaboForm" method="post" data-parsley-validate=""
				class="form-horizontal form-label-left" novalidate="">
			<!-- 프로젝트명 -->
					<div class="item form-group">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="title">콜라보명 <span class="required">*</span>
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
				<label class=" label-align"> 콜라보 소개 </label>
				<textarea class="cprojSummernote" name="intro" required="required">{{intro}}</textarea>
			</div>
			<div class="col-md-7 col-sm-7 offset-md-3 mt-3">
				<button type="button" class="btn btn-success" onclick='modifyCollaboDetail()'>수정</button>
				<button class="btn btn-primary" type="button">취소</button>
				<button class="btn btn-primary" type="reset">리셋</button>
			</div>
		</form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</script>	

<!-- 콜라보 공지 수정 -->
<script type="text/x-handlebars-template" id="collaboNoticeModifyFormTemplate">
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
			<form id="modifyCollaboNotice" method="post" data-parsley-validate=""
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
				<textarea class="cprojSummernote" name="noticeCont" required="required">{{{noticeCont}}}</textarea>
			</div>
			<div class="col-md-7 col-sm-7 offset-md-3 mt-3">
				<button type="button" class="btn btn-success" onclick='modifyCollaboNotice()'>수정</button>
				<button class="btn btn-primary" type="button" onclick='deleteProjectNotice()'>삭제</button>
				<button class="btn btn-primary" type="button">취소</button>
			</div>

		</form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>	
</script>

<%-- <script type="text/x-handlebars-template" id="collaboRegistTypeTemplate">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title modalTitle">콜라보 제안</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>

          </div>

          <!-- Modal body -->
          <div class="modal-body" id="collaboModal">
	
            <div class="x_content">
              <!-- form start -->
              <form role="form" method="post" action="<%=request.getContextPath()%>/app/collabo/sendInviteCollaboMail" name="collaboMailRegist">
                
                <p>콜라보 제안은 다른 프로젝트 팀과 협업 할 수 있는 곳입니다.</p>
                <p>다른 팀과 협업 공간을 만들고 함께 일해보세요.</p>
                <div id="name1"></div>
                <div class="card-body" onkeydown="selectOtherProj()">
                  
                  <div class="control-group row">
                    <label class="control-label col-md-3 col-sm-3 ">이메일 입력</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="hidden" id="CollaboUserFrom" name="userFrom" value="${userVO.userId}">
                      <input type="hidden" id="CollboMailTitle" name="title" value="${userVO.userId}의 콜라보">
                      <input type="hidden" id="dist" name="dist" value="">
                      <input id="tags_1" name="userTo" type="text" class="tags form-control-collabo" value=""> 
                      <div id="suggestions-container"style="position: relative; float: left; width: 250px; margin: 10px;"></div>
                    </div>
                  </div>

                  <!-- select -->
                  <div class="form-group form-group-collabo">
                    <label>나의 프로젝트</label>
                    <select class="form-control form-control-collabo" id="selectOwnProject">
                    	
                    </select>
                  </div>
                  
                  <div class="form-group form-group-collabo">
                    <label>상대방의 프로젝트</label>
                    <select class="form-control form-control-collabo" id="selectOtherProject">
                    	
                    </select>
                  </div>
                  
                  <!-- textarea -->
                  <div class="form-group form-group-collabo">
                    <label>보낼 메세지</label>
                    <textarea id="sendMessage" class="form-control form-control-collabo" rows="3" placeholder="메세지를 입력해주세요."></textarea>
                  </div>
                </div>
                <!-- /.card-body -->
                <input type="hidden" name="content" id="collaboContent">
                <button type="button" id="inviteButton" class="btn  btn-outline-primary collabo-submit-btn" onclick="invite_go();">보내기</button>
              </form>
            </div>
          </div>
</script> --%>

<!-- 콜라보 업무 수정 템플레이트 -->
<script type="text/x-handlebars-template" id="collaboTaskModifyFormTemplate">
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
								<form id="modifyCollaboTaskForm" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 콜라보명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="title">
										콜라보명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="title" required="required" class="form-control form-control-sm"
										name="cprojTitle" value="{{cprojTitle}}" readonly>
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
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="important">
											중요도
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="important" name="important">
              									<option value="B101">낮음</option>
              									<option value="B102">보통</option>
             									<option value="B103">높음</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="status">
											진행상태
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<select class="form-control form-control-sm" id="status" name="status">
              									<option value="B201">예정</option>
              									<option value="B202" selected>진행중</option>
             									<option value="B203">지연중</option>
             									<option value="B204">완료</option>
            								</select>
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
										<label class=" label-align"> 업무내용 </label>
										<textarea class="cprojSummnote" name="content" >{{{content}}}</textarea>
									</div>
									<div style="width:100%; float:left">
           								 <input name="files" id="cprojTaskUpload" type="file" aria-label="files" />
        							</div>
									<div class="col-md-7 col-sm-7 offset-md-3 mt-3">
										<button type="button" class="btn btn-success" onclick='modifyCollaboTaskDetail()'>수정</button>
										<button class="btn btn-primary" type="button">취소</button>
										<button class="btn btn-primary" type="reset">리셋</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script> 

<!-- 콜라보 업무 등록-->
<script type="text/x-handlebars-template" id="collaboTaskRegistFormTemplate">
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
								<!-- 콜라보명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										콜라보명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="projTitle" required="required" class="form-control form-control-sm"
										name="cprojTitle" value="{{cprojTitle}}" readonly>
									<input type="hidden" value="{{cprojNo}}">
								</div>
							</div>
									<!-- 담당자 -->
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
              									<option value="B101">낮음</option>
              									<option value="B102">보통</option>
             									<option value="B103">높음</option>
            								</select>
										</div>
        							</div>
									<div class="item form-group">
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
										<textarea class="cprojSummnote" name="content" ></textarea>
									</div>
									 <div style="width:100%; float:left">
           								 <input name="files" id="cprojTaskUpload" type="file" aria-label="files" />
        							</div>
									<div class="col-md-7 col-sm-7 offset-md-3 mt-3">
										<button type="button" class="btn btn-success" onclick='registTask()'>등록</button>
										<button class="btn btn-primary" type="button">취소</button>
										<button class="btn btn-primary" type="reset">리셋</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!---------------------------- 멤버 초대 ---------------------------->
<script type="text/x-handlebars-template" id="inviteCollaboMemberForm">
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
						<p>콜라보를 함께 할 구성원을 초대해보세요.</p>
						<form id="inviteMemberCollaboForm" method="post" data-parsley-validate=""
							class="form-horizontal form-label-left mt-3" novalidate="">

						<input type="hidden" name="userNicknameFrom" value="${userVO.nickname}">
						<input type="hidden" name="userIdFrom" value="${userVO.userId}">
					<!-- 콜라보명 -->
					<div class="item form-group">
						<label class="col-form-label col-md-3 col-sm-3 label-align"
							for="title">콜라보명
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
						<button type="button" class="btn btn-success" onclick="inviteCollaboMemberSubmit();">초대</button>
						<button class="btn btn-primary" type="button" onclick="off()">취소</button>
					</div>
					</form>
				</div>
      </div>
	</div>
  </div>
</script>

<!-- 콜라보 상세페이지의 문서 업로드 폼 -->
<script type="text/x-handlebars-template" id="uploadCollaboDocumentForm">
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
							<form id="uploadCollaboDocument" method="post" data-parsley-validate=""
								class="form-horizontal form-label-left" novalidate="">
							<div style="width:100%; float:left">
           						<input name="files" id="CollaboDocument" type="file" aria-label="files" />
        					</div>
						</div>
						<div class="col-md-4 col-sm-4 offset-md-4 mt-3">
							<button type="button" class="btn btn-success" onclick='uploadCollaboDoc()'>등록</button>
							<button class="btn btn-primary" type="button" onclick="off()">취소</button>
						</div>
							</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<!-- 콜라보 마일스톤 등록 -->
<!------------------------------- 마일스톤 등록 ----------------------------->
<script type="text/x-handlebars-template" id="collaboMileStoneRegistFormTemplate">
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
								<!-- 콜라보명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										콜라보명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="cprojTitle" required="required" class="form-control form-control-sm"
										name="cprojTitle" value="{{cprojTitle}}" disabled>
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
										<textarea class="cprojSummnote" name="content" ></textarea>
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

<!------------------------------- 콜라보 마일스톤 수정 ----------------------------->
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

<!-- 콜라보 이슈 등록 -->
<script type="text/x-handlebars-template" id="collaboIssueRegistFormTemplate">
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
								<!-- 콜라보명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										콜라보명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="text" id="cprojTitle" required="required" class="form-control form-control-sm"
										name="projTitle" value="{{cprojTitle}}" readonly>
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
	
</body>
</html>
