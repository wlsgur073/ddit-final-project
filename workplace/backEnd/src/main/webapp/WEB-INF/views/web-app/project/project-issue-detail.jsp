<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div id="issueDetailFormTarget"></div>
    <script type="text/x-handlebars-template" id="issueDetailForm">
      <div class="x_title">
        <h2><i class="glyphicon glyphicon-exclamation-sign"></i>&nbsp;&nbsp;&nbsp;이슈 - 상세</h2>
        <div class="clearfix">
			<button type="button" class="btn btn-sm btn-secondary float-right" onclick="backToList();">목록</button>
			{{#ifCond '${userVO.nickname}' "==" userId}}
        	<button type="button" class="btn btn-sm btn-primary float-right" onclick="getOverlayIssueModifyTemplate('issueModifyFormTemplate','/app/issue/getIssueByIssueNo','{{issueVO.issueNo}}')">수정</button>
        	<button type="button" class="btn btn-sm btn-danger float-right" onclick="deleteIssueByNo('/app/issue/removeIssue','{{issueVO.issueNo}}');">삭제</button>
			{{/ifCond}}
			{{#ifCond '${userVO.nickname}' "!=" userId}}
        	<button type="button" class="btn btn-sm btn-primary float-right" onclick="getOverlayIssueModifyTemplate('issueModifyFormTemplate','/app/issue/getIssueByIssueNo','{{issueVO.issueNo}}')">수정</button>
        	<button type="button" class="btn btn-sm btn-danger float-right" onclick="deleteIssueByNo('/app/issue/removeIssue','{{issueVO.issueNo}}');">삭제</button>
			{{/ifCond}}
		</div>
      </div>
      <!-- 제목 끝 -->
      <!-- 이슈 디테일 시작 -->
      <div class="form-group row">
        <div class="col-sm-6">
          <label>제목 :</label>
			<input type="hidden" id="issueNo" value="{{issueVO.issueNo}}">
          <div class="form-control form-control-sm form-control-view text-truncate">{{issueVO.title}}</div>
        </div>
        <div class="col-sm-6">
          <label>프로젝트명 :</label>
          <div class="form-control form-control-sm form-control-view text-truncate">{{projTitle}}</div>
        </div>
      </div>

		<div class="form-group row">
			<div class="col-sm-6">
				<label>담당자</label>
					<input type="text" class="form-control form-control-sm form-control-view text-truncate"
					value="{{issueVO.nickname}}">
			</div>
			<div class="col-sm-6">
				<label class="control-label">중요도</label>
					<div class="form-control form-control-sm form-control-view text-truncate">{{issueVO.important}}</div>
			</div>
		</div>

      <div class="form-group row">
        <div class="col-sm-6">
          <label>발생일자 :</label>
          <div class="form-control form-control-sm form-control-view">{{formatTime issueVO.startdate "YYYY년 MM월 DD일"}}</div>
        </div>
        <div class="col-sm-6">
          <label>마감일자 :</label>
          <div class="form-control form-control-sm form-control-view">{{formatTime issueVO.enddate "YYYY년 MM월 DD일"}}</div>
        </div>
      </div>

      <div class="form-group row">
        <div class="col-sm-12">
          <label>이슈 내용 :</label>
          <div class="form-control form-control-sm form-control-view" style="word-break:break-all;height:auto;">{{{issueVO.content}}}</div>
        </div>
      </div>



      <div class="form-group row">
		<div class="col-sm-12">
			<label>첨부파일 :</label>
			<div class="form-control form-control-sm form-control-view" style="height:150px;">
				<ul class="row list-unstyled task-files">
				{{#ifCond issueVO.fileList.length "!=" 0}}
					{{#each issueVO.fileList}}
						<li class="d-flex align-items-center">
							<div class="btn files-btn d-flex flex-wrap flex-column align-items-center justify-content-center"  style="width:200px;height:140px">
								{{#ifCond '${userVO.nickname}' "==" ../userId}}
								<button type="button" class="badge badge-danger" style="margin-left:85px" onclick="deleteIssueDocument('{{doc_NO}}', '{{{path}}}', '{{../issueVO.issueNo}}');">X</button>
								{{/ifCond}}
								{{#ifCond '${userVO.nickname}' "!=" ../userId}}
								{{#getRoleCheck 'A303'}}
								<button type="button" class="badge badge-danger" style="margin-left:85px" onclick="deleteIssueDocument('{{doc_NO}}', '{{{path}}}', '{{../issueVO.issueNo}}');">X</button>
								{{/getRoleCheck}}
								{{/ifCond}}
								<a style="cursor:pointer;" onclick="issueDocumentDownload('{{{name}}}{{{extension}}}','{{{../issueVO.title}}}','{{{../projTitle}}}');"><i class="fa fa-file-text fa-5x text-dark"></i></a>
								<div class="mt-2 d-flex"><span class="text-truncate" style="display:inline-block;max-width:70px;">{{{name}}}</span><span>{{{extension}}}</span></div>
							</div>
						</li>
					{{/each}}
				{{/ifCond}}
				{{#ifCond issueVO.fileList.length "==" 0}}
					<div class="m-3">
						<span>첨부파일이 없습니다.</span>
					<div>
				{{/ifCond}}
				</ul>
			</div>
		</div>
	</div>
      <!-- 이슈 디테일 끝 -->
</script>

<!-- 이슈 디테일 댓글 시작 -->
<div class="form-group row">
	<div class="col-sm-12">
		<div class="card mb-2">
			<div class="card-header bg-light">
				<div class="input-group" style="margin:0px;">
					<input type="text" name="content" id="projIssueReplyRegistContent" class="form-control input-group" style="margin:0px;" placeholder="댓글을 입력하세요.">
					<button type="button" class="btn btn-dark input-group-append" onclick="projIssueReplyRegist('${userVO.userId}');" style="margin-right:0px;">작성</button>
				</div>
			</div>
			<div class="card-body">
				<div id="issueReplyFormTarget"></div>
			</div>
			<div class="card-footer bt-light"></div>
		</div>
	</div>
</div>
<!-- 댓글 작성폼 끝 -->

<script type="text/x-handlebars-template" id="issueReplyForm">
	{{#each .}}
	<div class="block bg-light d-flex">
		<div class="block_content" style="width:100%">
			<div class="col-sm-2 pt-2">
				<img src="<%=request.getContextPath()%>/user/getPictureById?userId={{userId}}" alt="img" style="height:31px;width:31px;border-radius:100%;"/>
				<span>{{userVO.nickname}}</span>
			</div>
			<div class="col-sm-7">
				<p class="excerpt mt-3" style="word-break:break-all;height:auto;">{{content}}</p>
			</div>
			<div class="col-sm-3">
				<p class="mt-3" style="display:inline-block;">{{formatTime updatedate "YYYY년 MM월 DD일"}}</p>
				{{#ifCond userId "==" "${userVO.userId}"}}
				<button type="button" class="btn btn-sm btn-danger float-right" style="margin-top:9px;" onclick="projIssueReplyRemove('{{issueResNo}}');">삭제</button>
				<button type="button" class="btn btn-sm btn-primary float-right" style="margin-top:9px;" data-toggle="modal" data-target=".projIssueReplyModifyModal" onclick="projIssueReplyModifyForm_go('{{issueResNo}}', '{{content}}');">수정</button>
				{{/ifCond}}
			</div>
		</div>
	</div>
	{{/each}}
</script>

<!-- 댓글 수정폼 시작 -->
<div class="projIssueReplyModifyModal modal fade modify-modal-lg" tabindex="-1" role="dialog" aria-modal="true" style="display:none;" >
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="projModalLabel">댓글 수정</h4>
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
				<input type="text" name="content" id="projIssueReplyModifyContent" class="form-control input-group" style="margin:0px;" placeholder="댓글을 입력하세요.">
				<input type="hidden" name="issueResNo" id="projIssueReplyModifyIssueResNo">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-primary" onclick="projIssueReplyModify();">저장</button>
				<button type="button" id="projIssueReplyModifyModalCloseButton" class="btn btn-sm btn-secondary" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- 댓글 수정폼 끝 -->
