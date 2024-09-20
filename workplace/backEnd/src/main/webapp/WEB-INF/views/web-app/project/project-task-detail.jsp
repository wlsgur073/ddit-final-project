<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div id="taskDetailFormTarget"></div>

<script id="taskDetailForm" type="text/x-handlebars-template">
      	<div class="x_title">
			<h2><i class="glyphicon glyphicon-exclamation-sign"></i>&nbsp;&nbsp;&nbsp;업무 상세	</h2>
			<div class="clearfix">
				<button type="button" class="btn btn-sm btn-secondary float-right" onclick="backToList();">목록</button>
				{{#ifCond '${userVO.nickname}' "==" userId}}
				<button type="button" class="btn btn-sm btn-primary float-right" onclick="getOverlayTaskModifyTemplate('taskModifyFormTemplate','/app/task/getTaskDetailByTaskNo','{{taskNo}}')">수정</button>
				<button type="button" class="btn btn-sm btn-danger float-right" onclick="deleteTaskById('/app/task/delete','{{taskNo}}');">삭제</button>
				{{/ifCond}}
				{{#ifCond '${userVO.nickname}' "!=" userId}}
				{{#getRoleCheck 'A303'}}
				<button type="button" class="btn btn-sm btn-primary float-right" onclick="getOverlayTaskModifyTemplate('taskModifyFormTemplate','/app/task/getTaskDetailByTaskNo','{{taskNo}}')">수정</button>
				<button type="button" class="btn btn-sm btn-danger float-right" onclick="deleteTaskById('/app/task/delete','{{taskNo}}');">삭제</button>
				{{/getRoleCheck}}
				{{/ifCond}}
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-6">
				<label>제목 :</label>
				<div class="form-control form-control-sm form-control-view text-truncate">{{title}}</div>
			</div>
			<div class="col-sm-6">
				<label>프로젝트명 :</label>
				<div class="form-control form-control-sm form-control-view text-truncate">{{projTitle}}</div>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-6">
				<label>담당자</label>
					<input type="text" class="form-control form-control-sm form-control-view"
					value="{{userId}}">
			</div>
			<div class="col-sm-3">
				<label class="control-label">중요도</label>
				{{#ifCond important "==" "B101"}}
					<div class="form-control form-control-sm form-control-view text-truncate">낮음</div>
				{{/ifCond}}
				{{#ifCond important "==" "B102"}}
					<div class="form-control form-control-sm form-control-view text-truncate">중간</div>
				{{/ifCond}}
				{{#ifCond important "==" "B103"}}
					<div class="form-control form-control-sm form-control-view text-truncate">높음</div>
				{{/ifCond}}
			</div>
			<div class="col-sm-3">
				<label class="control-label">상태</label>
				{{#ifCond status "==" "B201"}}
					<div class="form-control form-control-sm form-control-view text-truncate">미배정</div>
				{{/ifCond}}
				{{#ifCond status "==" "B202"}}
					<div class="form-control form-control-sm form-control-view text-truncate">진행중</div>
				{{/ifCond}}
				{{#ifCond status "==" "B203"}}
					<div class="form-control form-control-sm form-control-view text-truncate">지연</div>
				{{/ifCond}}
				{{#ifCond status "==" "B204"}}
					<div class="form-control form-control-sm form-control-view text-truncate">완료</div>
				{{/ifCond}}
			</div>
		</div>


		<div class="form-group row">
			<div class="col-sm-6">
				<label>시작일 :</label>
					<div class="form-control form-control-sm form-control-view">{{formatTime startdate "YYYY년 MM월 DD일"}}</div>
			</div>
			<div class="col-sm-6">
				<label>마감일 :</label>
					<div class="form-control form-control-sm form-control-view">{{formatTime enddate "YYYY년 MM월 DD일"}}</div>
			</div>
		</div>


		<div class="form-group row">
			<div class="col-sm-12">
				<label>업무 내용 :</label>
				<div class="form-control form-control-sm form-control-view" style="word-break:break-all;height:auto;">
					{{{content}}}
				</div>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">
				<label>첨부파일 :</label>
				<div class="form-control form-control-sm form-control-view" style="height:150px;">
					<ul class="row list-unstyled task-files">
					{{#ifCond fileList.length "!=" 0}}
						{{#each fileList}}
						<li class="d-flex align-items-center">
							<div class="btn files-btn d-flex flex-wrap flex-column align-items-center justify-content-center"  style="width:140px;height:140px">
								{{#ifCond '${userVO.nickname}' "==" ../userId}}
									<button type="button" class="badge badge-danger" style="margin-left:85px" onclick="deleteDocument('{{doc_NO}}','{{{path}}}','{{../taskNo}}');">X</button>
								{{/ifCond}}
								{{#ifCond '${userVO.nickname}' "!=" ../userId}}
									{{#getRoleCheck 'A303'}}
										<button type="button" class="badge badge-danger" style="margin-left:85px" onclick="deleteDocument('{{doc_NO}}','{{{path}}}','{{../taskNo}}');">X</button>
									{{/getRoleCheck}}
								{{/ifCond}}
								<a style="cursor:pointer;" onclick="taskDocumentDownload('{{{name}}}{{{extension}}}','{{{../title}}}','{{{../projTitle}}}');"><i class="fa fa-file-text fa-5x text-dark"></i></a>
								<div class="mt-2 d-flex"><span class="text-truncate" style="display:inline-block;max-width:70px;">{{{name}}}</span><span>{{{extension}}}</span></div>
								</div>
							</li>
							{{/each}}
						{{/ifCond}}
						{{#ifCond fileList.length "==" 0}}
							<div class="m-3">
								<span>첨부파일이 없습니다.</span>
							<div>
						{{/ifCond}}
						</ul>
					</div>
				</div>
			</div>
	</script>