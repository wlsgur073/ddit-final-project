<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/seok/seok.css"/>
</head>
<body>
	<!-- 알림설정 모달폼 -->
	<div id="alarmModal" class="modal modal-default fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<br>
				<div class="modal-header">
					<h3 class="modal-title">알림 설정</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>


				<div class="modal-body" data-rno>

					<div class="form-group row">
						<div class="col-lg-12">
							<label class="switch float-right">
							 <input type="checkbox" id="allAlertCheckBox" onchange="settingAllAlert();">
								<span class="slider round"></span>
							</label>
							<h4>모든 알림</h4>
							모든 알림에 대하여 받거나 거부 할 수 있어요.
						</div>

					</div>
					<hr>

					<div class="form-group row">
						<div class="col-lg-12">
							<h4>프로젝트 별 알림</h4>
							내가 속해 있는 프로젝트의 알림을 꺼보세요. 프로젝트 내 모든 활동의 알림을 받지 않게 됩니다.
						</div>
					</div>

					<br>
					<ul style="padding:0px; list-style:none;" id="projectAlertSettingList">
						<!-- alertIndex.js의 getAlertModalProjectList() 메서드에서 li를 추가하고 있다. -->
					</ul>

				</div>

				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<!-- 모달끝 -->

	<!--  콜라보 제안 모달 -->
	<!-- The Modal -->
    <div class="modal" id="createCollabo" data-backdrop="static">
      <div class="modal-dialog">
        <div class="modal-content modal-content-collabo">

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
                <div>
                	<input type="hidden" id="name1" value="">
                </div>
                <div class="card-body" onkeyup="selectOtherProj()">

                  <div class="control-group row">
                    <label class="control-label collabo col-md-3 col-sm-3">닉네임 입력</label>
                    <div class="col-md-9 col-sm-9 ">
                      <input type="hidden" id="CollaboUserFrom" name="userFrom" value="${userVO.userId}">
                      <input type="hidden" id="CollboMailTitle" name="title" value="${userVO.userId}의 콜라보">
                      <input type="hidden" id="dist" name="dist" value="">
                      <input id="tags_1" name="userTo" type="text" class="tags form-control-collabo" value="">
                      <div id="suggestions-container"style="position: relative; float: left; width: 250px; margin: 10px;"></div>
                    </div>
                  </div>

                  <!-- select -->
                  <!-- <div class="form-group form-group-collabo">
                    <label>나의 프로젝트</label>
                    <select class="form-control form-control-collabo" id="selectOwnProject">

                    </select>
                  </div>

                  <div class="form-group form-group-collabo">
                    <label>상대방의 프로젝트</label>
                    <select class="form-control form-control-collabo" id="selectOtherProject">

                    </select>
                  </div> -->
                  <div class="form-group row">
					<label class="control-label collabo  col-md-3 col-sm-3 ">나의 프로젝트</label>
						<div class="col-md-9 col-sm-9 ">
							<select class="form-control form-control-collabo" id="selectOwnProject">

							</select>
						</div>
				  </div>

                  <div class="form-group row">
					<label class="control-label collabo  col-md-3 col-sm-3 ">상대방의 프로젝트</label>
						<div class="col-md-9 col-sm-9 ">
							<select class="form-control form-control-collabo" id="selectOtherProject">

							</select>
						</div>
				  </div>
				  <!-- textArea -->
				  <div class="form-group row">
					<label class="control-label collabo  col-md-3 col-sm-3 ">보낼 메세지<span class="required"></span></label>
						<div class="col-md-9 col-sm-9 ">
							<textarea id="sendMessage" class="form-control form-control-collabo" rows="3" placeholder="메세지를 입력해주세요"></textarea>
						</div>
				  </div>
                  <!-- textarea -->
                  <!-- <div class="form-group form-group-collabo">
                    <label>보낼 메세지</label>
                    <textarea id="sendMessage" class="form-control form-control-collabo" rows="3" placeholder="메세지를 입력해주세요."></textarea>
                  </div> -->
                </div>
                <!-- /.card-body -->
                <input type="hidden" name="content" id="collaboContent">
                <button type="button" id="inviteButton" class="btn  btn-outline-primary collabo-submit-btn" onclick="invite_go();">보내기</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 콜라보 제안 모달 끝-->

     <!-- 콜라보 거절 모달 The Modal -->
	  <div class="modal" id="refuseCollabo">
	    <div class="modal-dialog">
	      <div class="modal-content">

	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h5 class="modal-title">콜라보 제안 거절</h5>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>

	        <!-- Modal body -->
	        <div class="modal-body">
	          <div class="form-group row">
	            <label class="control-label collabo  col-md-3 col-sm-3 ">보낼 메세지<span class="required"></span></label>
	              <div class="col-md-9 col-sm-9 ">
	                <textarea id="sendMessageOther" class="form-control form-control-collabo" rows="3" placeholder="메세지를 입력해주세요"></textarea>
	              </div>
	            </div>
	        </div>

	        <!-- Modal footer -->
	        <div class="modal-footer">
	          <button type="button" class="btn btn-primary" onclick="refuseCoproj()">보내기</button>
	          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	        </div>

	      </div>
	    </div>
	  </div>


    <!-- 채팅모달 -->
     <div id="createRoomModal" class="modal modal-default fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<br>
			<div class="modal-header">
				<h3 class="modal-title">채팅방 생성</h3>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" data-rno>

				<form role="form" method="post" action="/chat/createRoom" name="createForm">
					<br>
					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;채팅방 이름 </span></label>
						<div class="col-lg-12">

							<input id="title" class="form-control col-md-12 seokid" name="title" value="" placeholder="">

			            </div>

					</div>



					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;프로젝트 선택</span></label>
						<div class="col-lg-12">
			                    <select class="select2_single form-control" name="Ptitle" id="selectProject" tabindex="-1" >
									<option value=''>==내가속한 프로젝트 리스트==</option>



			                    </select>
						</div>
					</div>

					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;멤버초대</span></label>
						<div class="col-lg-12" id="memberInvite">

							<div id="ChatUserSeok" role="application" style="display: none;">
							    <div class="demo-section k-content wide">
							        <select id="optionalSeok"></select>
							        <select id="selectedSeok"></select>
							    </div>
						    </div>


						</div>
					</div>
					<span id="checkCreateRoom">가이드</span>
					<div class="float-right">
						<button type="button" class="btn btn-danger" id="replyDelBtn"
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

    <!-- 채팅끝 -->









</body>
</html>