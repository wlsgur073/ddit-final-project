<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

  <!-- footer content -->
	    <footer>
	      <div class="pull-right">
	        Copyright © 2022 <a href="<%=request.getContextPath()%>/probada.com/index" style="color: #73879C;">probada</a> App Company. All Rights Reserved.
	      </div>
	      <div class="clearfix"></div>
	    </footer>
	    <!-- /footer content -->
	  </div>
	</div>
	<%@ include file="/WEB-INF/views/web-app/myWork/summernote.jsp" %>
	<input type="hidden" id="sessionUserId" value="${userVO.userId}">
	<!-- floating action button  -->
	<div class="zoom">
    	<input type="checkbox" href="#" class="menu-open" name="menu-open" id="menu-open" />
    	<label class="menu-open-button zoom-btn-large" for="menu-open" id="zoomBtn"> <span class="lines line-1"></span> <span class="lines line-2"></span> <span class="lines line-3"></span> </label>
    <ul class="zoom-menu">
      <li><a class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition scale-out" href="javascript:getOverlayAnyWhereTaskRegistTemplate('${userVO.userId}');"><i class="fa fa-edit"></i></a></li>
      <li><a class="zoom-fab zoom-btn-sm zoom-btn-person scale-transition scale-out" href="javascript:getOverlayAnyWhereIssueRegistTemplate('${userVO.userId}');"><i class="fa fa-exclamation"></i></a></li>
      <li><a class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition scale-out" href="javascript:getOverlayAnyWhereMailRegistTemplate('${userVO.nickname}');"><i class="fa fa-envelope-o"></i></a></li>
       <li><a class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition scale-out"><i class="fa fa-user-plus"></i></a></li>
      <!--  <li><a class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition scale-out" href="javascript:on()"><i class="fa fa-edit"></i></a></li>
      <li><a class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition scale-out"><i class="fa fa-bell"></i></a></li> -->
      <li><a class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition scale-out" href="javascript:getOverlayAnyWhereSpoonTemplate('/app/spoon/getTaskListByProjNo');"><i class="fa fa-spoon"></i></a></li>
      <!-- <li><a class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition scale-out" data-toggle="modal" data-target="#anyWhereSpoon"><i class="fa fa-spoon"></i></a></li> -->
    </ul>
  </div>
	<!-- /floating action button  -->


	<!-- The Modal -->
<!--   <div class="modal" id="anyWhereSpoon">
    <div class="modal-dialog">
      <div class="modal-content">

        Modal Header
        <div class="modal-header">
          <h5 class="modal-title"><img src="https://img.icons8.com/ios/20/000000/spoon.png"/>&nbsp Spoon</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        Modal body
        <div class="modal-body">
            <div class="x_panel">
              <div class="x_content">
                <form class="form-horizontal form-label-left">

                  <div class="form-group row ">
                    <label class="control-label col-md-3 col-sm-3 ">프로젝트 명</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="text" class="form-control" readonly="readonly" placeholder="현재 위치 프로젝트 올 예정" value="">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">업무 명</label>
                    <div class="col-md-9 col-sm-9 ">
                      <select class="form-control">
                        <option>업무 1</option>
                        <option>업무 2</option>
                        <option>업무 3</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">담당자</label>
                    <div class="col-md-9 col-sm-9 ">
                      <select class="form-control">
                        <option>은세주</option>
                        <option>황혜성</option>
                        <option>공의진</option>
                      </select>
                    </div>
                  </div>



                  <h6 class="collaboSpButton">Spoon 할 콜라보 프로젝트를 선택하세요.</h6>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">콜라보</label>
                    <div class="col-md-9 col-sm-9 ">
                      <select class="form-control">
                        <option >김진혁 앙골라 국제 결혼 프로젝트</option>
                        <option>김진혁 르완다 국제 결혼 프로젝트</option>
                        <option>김진혁 아프리카 장가보내기 프로젝트</option>
                      </select>
                    </div>
                  </div>
                </form>
              </div>
            </div>

        </div>

        Modal footer
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">확인</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
        </div>

      </div>
    </div>
  </div>

</div> -->



</body>
</html>

