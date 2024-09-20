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

.seokFont{
font-family: 'Jua', sans-serif;
}
</style>

</head>
<body>
	<!-- 오버레이 시작 -->
	<div id="popoverlay"></div>
	<!-- 오버레이 끝 -->


<script type="text/x-handlebars-template" id="anyWheretaskRegistFormTemplate">
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
								<form id="anyWhereRegistTaskForm" method="post" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<select class="form-control form-control-sm" id="projNo" name="projNo">
												{{#each .}}
              									<option value="{{projNo}}">{{title}}</option>
												{{/each}}
            								</select>
									</div>
								</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											담당자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<input readonly class="form-control form-control-sm" value="${userVO.nickname}" id="nickname" name="nickname">
											<input type="hidden" value="${userVO.userId}" id="userId" name="userId">
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
										<textarea class="projSummnote" id="anyWhereTaskOverlayContent" name="content" ></textarea>
									</div>
									 <div style="width:100%; float:left">
           								 <input name="files" id="anyWhereProjectTaskUpload" type="file" aria-label="files" />
        							</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='anyWhereTaskRegistSubmit()'>등록</button>
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
<script type="text/x-handlebars-template" id="anyWhereIssueRegistFormTemplate">
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
								<form id="anyWhereRegistIssueForm" enctype="multipart/form-data" method="post" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
								<!-- 프로젝트명 -->
								<div class="item form-group">
									<label class="col-form-label col-md-3 col-sm-3 label-align"
										for="projTitle">
										프로젝트명
									</label>
								<div class="col-md-6 col-sm-6 ">
									<select class="form-control form-control-sm" id="projNo" name="projNo">
												{{#each .}}
              									<option value="{{projNo}}">{{title}}</option>
												{{/each}}
            								</select>
									</div>
								</div>
									<div class="item form-group">
          								<label class="col-form-label col-md-3 col-sm-3 label-align for="userNickname">
											제기자
										</label>
										<div class="col-md-6 col-sm-6 ">
            								<input readonly class="form-control form-control-sm" value="${userVO.nickname}" id="nickname" name="nickname">
											<input type="hidden" value="${userVO.userId}" id="userId" name="userId">
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
										<textarea class="projSummnote" id="anyWhereIssueOverlayContent" name="content" ></textarea>
									</div>
									<div style="width:100%; float:left">
											<input name="files" id="anyWhereProjectIssueUpload" type="file" aria-label="files" />
									</div>
									<div class="col-md-7 col-sm-7 offset-md-4 mt-3">
										<button type="button" class="btn btn-success" onclick='anyWhereIssueRegistSubmit()'>등록</button>
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



<script type="text/x-handlebars-template" id="anyWhereMailRegistFormTemplate">
	<div class="row" style="display:none;" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<i class="fa fa-clone"></i> <span class="task-bold task-sm">메일 전송</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
					<div class="row">
						<!-- 내용저장 -->
						<div class="x_content row">
							<div class="col-sm-12">
								<form enctype="multipart/form-data" role="form" method="post" action="<%=request.getContextPath()%>/app/myWork/mailRegist" name="overlayAnyWhereMailRegistForm" class="form-horizontal form-label-left">
									<div class="inbox-body">
										<input type="hidden" id="anyWhereUserFrom" name="userFrom" value="${userVO.nickname}">
										<input type="hidden" id="anyWhereOverlayDist" name="dist" value="">
										<input type="text" id="anyWhereMailOverlayUserTo" name="userTo" class="form-control form-control-sm mt-3 sendOverlayAnyWhereUserTo" placeholder="받는 사람:" value="">
										<br>
										<input type="text" id="anyWhereMailOverlayTitle" name="title" class="form-control form-control-sm sendOverlayAnyWhereTitle" placeholder="제목:">
										<br>
										<textarea id="anyWhereMailOverlayContent" name="content" rows="10" class="form-control content sendOverlayAnyWhereContent overlaySendAnyWhereContent" placeholder="내용을 입력하세요." style="display:none;"></textarea>
										<br>
									</div>
									<div class="card">
										<div class="card-header" style="background:#f7f7f7;border-bottom:none;">
											<button class="btn btn-sm btn-primary" onclick="addOverlayAnyWhereFile_go();" type="button" id="addFileBtn">파일첨부</button>
										</div>
										<div class="card-body overlayFileAnyWhereInput"></div>
										<div class="card-footer"></div>
									</div>
									<div class="col-sm-12 mt-3 pr-1" style="text-align:right;">
										<button type="button" id="mailAnyWhereTempSaveButton" class="btn btn-sm btn-dark" onclick="mailOverlayAnyWhereRegist_go('temp', '${userVO.nickname}');return false;">임시저장</button>
										<button type="button" id="mailAnyWhereSendButton" class="btn btn-sm btn-primary" onclick="mailOverlayAnyWhereRegist_go('send', '${userVO.nickname}');return false;">전송</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>





<!-- chatTest -->
<script type="text/x-handlebars-template" id="chatTemplate">





 <div id="overlayChat">
                        <div id="textChat">
                            <div id="roomWrap">
                                <div id="roomList">

										<div class="row">
										  <div class="col-lg-6">
                                   			 <div id="roomCreate" ><span style="font-size: 2.0em; ">&nbsp;&nbsp;<i class="fa fa-comments"></i>채팅 &nbsp;<i style="cursor : pointer;" class="fa fa-plus-square" data-toggle="modal" data-target="#createRoomModal"></i> </span></div>
                              			  </div>
                               			  <div class="col-lg-6">
                                   				 <span style="margin-right:6%; cursor : pointer; font-size: 2.0em; float:right;" id="offChatList" onclick="offChatList()">&nbsp;&nbsp;<i class="fa fa-chevron-circle-right"></i>&nbsp;접기</span>
                             			 </div>
										</div>

                                        <div id="roomSelect">

   									    <div id="chatListSeok"></div>


                                       </div>
                                </div>
                            </div>
							<br>

							<div class="accordion" id="accordion1" role="tablist" aria-multiselectable="true">
                  				  <div class="panel">
                       				 <span style="cursor : pointer;" class="panel-heading collapsed" role="tab" id="headingOne1" data-toggle="collapse" data-parent="#accordion1"
                         			   href="#collapseOne1Chat" aria-expanded="false" aria-controls="collapseOne">
                         			   <h4 class="panel-title"><i class="fa fa-check-circle"></i>가이드</h4>
                     			    </span>
                      			  <div id="collapseOne1Chat" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                         			   <div class="panel-body">



                               		   				<img style="width:420px; height:380px; margin-left:10%;" class="Gpicture" src="/resources/seok/ezgif.com-gif-maker.gif" alt="Grapefruit slice atop a pile of other slices"><br>


                            		   </div>
                       			  </div>
                                  </div>
               				 </div>



           </div>

</div>

</script>

<script type="text/x-handlebars-template" id="chatModalTemplate">
  <base href="https://demos.telerik.com/kendo-ui/listbox/index">
  <style>html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; }</style>
 <style>
    .demo-section label {
        margin-bottom: 5px;
        font-weight: bold;
        display: inline-block;
    }

    #employees {
        width: 270px;
    }

    #example .demo-section {
        max-width: none;
        width: 600px;
    }

    #example .k-listbox {
        width: 236px;
        height: 350px;
    }

    #example .k-listbox:first-of-type {
        width: 270px;
        margin-right: 1px;
    }
</style>



<div id="createRoomModal" class="modal modal-default fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">채팅방 생성</h3>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" data-rno>

				<form role="form" id="createChatForm" method="post" action="/chat/createRoom" name="createForm">
					<br>
					<div class="form-group row">


						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;채팅방 이름 </span></label>
						<div class="col-lg-12">

							<input id="title" onclick="button_click();" class="form-control col-md-12 seokid" name="title" value="" placeholder="">

			            </div>

					</div>



					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;프로젝트 선택</span></label>
						<div class="col-lg-12">
			                    <select class="select2_single form-control" name="Ptitle" id="selectProject" tabindex="-1" >
									<option value=''>==내가속한 프로젝트 리스트==</option>
			                        {{#each.}}
										<option value="{{this}}">{{this}}</option>
									{{/each}}
			                    </select>
						</div>
					</div>

					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;멤버선택</span></label>
						<div class="col-lg-12" id="memberInvite">



							    <div id="example" role="application">
   									 <div class="demo-section k-content wide">
      									  <div>
           										 <label for="optional" id="employees">Employees</label>
         										   <label for="selected">Developers</label>
          											  <br />
          									  <select id="optional" >
           									   </select>
            					<select id="selected"></select>
        									</div>
   									 </div>
   								 </div>


						</div>



					</div>
					<span id="checkCreateRoom">가이드</span>
					<div class="float-right">
						<button type="button" class="btn btn-info" id="replyDelBtn"
							onclick="createRoom();">생성</button>
						<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
					</div>

				</form>
			</div>


		</div>

		<div class="modal-footer">


		</div>
	</div>
</div>


</script>

<!-- 스푼 업무 오버레이!!!!!!!!!!!!!!! -->
<script type="text/x-handlebars-template" id="anyWhereSpoonTaskTemplate">
	<div class="row" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<img src="https://img.icons8.com/ios/20/000000/spoon.png"/> <span class="task-bold task-sm">Spoon</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
                <form id="SpoonOverlayForm" method="post"  class="form-horizontal form-label-left">

                  <div class="form-group row ">
                    <label class="control-label col-md-3 col-sm-3 ">프로젝트 명</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="text" id="taskProjTitle" name="title" class="form-control" readonly="readonly" value="{{title}}">
					  <input type="hidden" id="taskOfProjNo" name="projNo" class="form-control"readonly="readonly" value="{{projNo}}">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">업무 명</label>
                    <div class="col-md-9 col-sm-9 ">
						<select id="selectTaskTitle" name="taskTitle" multiple="multiple" data-placeholder="추가하실 업무를 선택해주세요">
							{{#each taskList}}
              					<option class="selectedTaskNoOfProj" value="{{taskNo}}">{{title}}</option>
							{{/each}}
        				</select>
                    </div>
                  </div>
				  <hr>
                  <h6 class="collaboSpButton">Spoon 할 콜라보 프로젝트를 선택하세요.</h6>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">콜라보</label>
                    <div class="col-md-9 col-sm-9 ">
                      <select id="optTaskCprojNo" name="optTaskCprojNo" class="form-control">
                        {{#each cprojTitleList}}
              				<option idx="{{cprojNo}}" value="{{title}}">{{title}}</option>
						{{/each}}
                      </select>
                    </div>
                  </div>
                </form>

			 	<div class="modal-footer">
          			<button type="button" class="btn btn-primary" onclick="SpoonTask_go()">확인</button>
          			<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
        		</div>
              </div>
			</div>
		</div>
	</div>
</script>

<!-- 스푼 이슈 오버레이!!!!!!!!!!!!!!! -->
<script type="text/x-handlebars-template" id="anyWhereSpoonIssueTemplate">
	<div class="row" id="fadeInContent">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title row d-flex justify-content-between">
					<h5 class="title">
							<img src="https://img.icons8.com/ios/20/000000/spoon.png"/> <span class="task-bold task-sm">Spoon</span>
					</h5>
					<div class="clearfix">
						<button onclick="off()" class="btn btn-primary btn-sm">X</button>
					</div>
				</div>
				<div class="x_content">
                <form id="SpoonOverlayForm" method="post" enctype="multipart/form-data" class="form-horizontal form-label-left">

                  <div class="form-group row ">
                    <label class="control-label col-md-3 col-sm-3 ">프로젝트 명</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="text" id="title" name="title" class="form-control" readonly="readonly" value="{{title}}">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">이슈 명</label>
                    <div class="col-md-9 col-sm-9 ">
						<select id="selectTaskTitle" name="taskTitle" multiple="multiple" data-placeholder="추가하실 이슈를 선택해주세요">
							{{#each taskList}}
              					<option value="{{taskNo}}">{{title}}</option>
							{{/each}}
        				</select>
                    </div>
                  </div>
				  <hr>
                  <h6 class="collaboSpButton">Spoon 할 콜라보 프로젝트를 선택하세요.</h6>
                  <div class="form-group row">
                    <label class="control-label col-md-3 col-sm-3 ">콜라보</label>
                    <div class="col-md-9 col-sm-9 ">
                      <select class="form-control">
                        {{#each cprojTitleList}}
              				<option value="{{cprojNo}}">{{title}}</option>
						{{/each}}
                      </select>
                    </div>
                  </div>
                </form>

			 	<div class="modal-footer">
          			<button type="button" class="btn btn-primary" onclick="">확인</button>
          			<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
        		</div>
              </div>
			</div>
		</div>
	</div>
</script>
<!-- 채팅 테스트 템플릿 -->
<!-- <div id="overlayChat">
  <div id="textChat">
 	 <div id="roomWrap">
	  	 <div id="roomList">
	                <div id="roomCreate" data-toggle="modal" data-target="#createRoomModal">방만들기+</div>
	                <div id='roomHeader'>채팅 방 목록</div>
	                <div id="roomSelect">


						{{#each.}}
							<div class="roomEl" data-id="2" onclick="selectChatRoom('{{this.realRoom}}');">{{this.title}}</div>
						{{/each}}


	              	</div>
	        </div>
        </div>

  <div id="offChatList" onclick="offChatList()">접기</div>

  </div>

</div> -->

<!-- 채팅 기존 템플릿

 <div id="overlayChat">
                        <div id="textChat">
                            <div id="roomWrap">
                                <div id="roomList">
                                        <div id="roomCreate" ><span style="font-size: 2.0em;">&nbsp;<i class="fa fa-comments"></i>channel &nbsp;<i class="fa fa-plus-square" data-toggle="modal" data-target="#createRoomModal"></i> </span></div>


                                        <div id="roomSelect">

                                            <table class="table table-hover">

												{{#each.}}
                                               		  <tr>
                                                	    <td class="active" onclick="selectChatRoom('{{this.realRoom}}');"><span class="seokFont" style="font-size: 1.2em;">{{this.title}}</span></td>
                                              		  </tr>
												{{/each}}

                                            </table>


                                            </div>
                                </div>
                            </div>
                             <span style="font-size: 1.5em;" id="offChatList" onclick="offChatList()">&nbsp;&nbsp;<i class="fa fa-chevron-circle-right"></i>&nbsp;접기</span>


           </div>

</div>














 -->






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