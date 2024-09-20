<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true"%>

<div class="x_content">
	<!-- 제목 시작 -->
	<div class="row">
		<!-- /.col -->
		<div class="col-md-12">
			<div class="">
				<div class="x_title"> 
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="btn-group nav">
								<button type="button" id="mailWrite-tab" class="btn btn-sm btn-dark mail-tab" data-toggle="tab" href="#regist" role="tab" aria-controls="regist" aria-selected="false">
									<i class="fa fa-inbox"></i>&nbsp;&nbsp;메일 작성
								</button>
								<button type="button" id="mailReceive-tab" onclick="receiveMailBox('${userVO.nickname}');" class="btn btn-sm btn-dark mail-tab" data-toggle="tab" href="#receive" role="tab" aria-controls="receive" aria-selected="true">
									<i class="fa fa-inbox"></i>&nbsp;&nbsp;받은 메일함
								</button>
								<button type="button" id="mailSend-tab" onclick="sendMailBox('${userVO.nickname}');" class="btn btn-sm btn-dark mail-tab"  data-toggle="tab" href="#send" role="tab" aria-controls="send" aria-selected="false">
									<i class="fa fa-send-o"></i>&nbsp;&nbsp;보낸 메일함
								</button>
								<button type="button" id="mailTemp-tab" onclick="tempMailBox('${userVO.nickname}');" class="btn btn-sm btn-dark mail-tab" data-toggle="tab" href="#temp" role="tab" aria-controls="temp" aria-selected="false">
									<i class="fa fa-folder-o"></i>&nbsp;&nbsp;임시 메일함
								</button>
								<button type="button" id="mailTrash-tab" onclick="trashMailBox('${userVO.nickname}');" class="btn btn-sm btn-dark mail-tab" data-toggle="tab" href="#trash" role="tab" aria-controls="trash" aria-selected="false">
									<i class="fa fa-trash-o"></i>&nbsp;&nbsp;휴지통
								</button>
							</div>
						</div>
						<div class="col-md-4">
							
						</div>					
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="x_body">
					<!-- 메일 리스트 시작 -->
					<div class="row">
						<div class="col-sm-12">
							<div class="card-box tab-content">
								<div class="tab-pane fade" id="regist" role="tabpanel" aria-labelledby="regist-tab">
									<%@ include file="/WEB-INF/views/web-app/myWork/myMailRegist.jsp" %>
								</div>
								<div class="tab-pane fade" id="receive" role="tabpanel" aria-labelledby="receive-tab">
									<%@ include file="/WEB-INF/views/web-app/myWork/myMailReceive.jsp" %>
								</div>
								<div class="tab-pane fade" id="send" role="tabpanel" aria-labelledby="send-tab">
									<%@ include file="/WEB-INF/views/web-app/myWork/myMailSend.jsp" %>
								</div>
								<div class="tab-pane fade" id="temp" role="temp" role="tabpanel" aria-labelledby="temp-tab">
									<%@ include file="/WEB-INF/views/web-app/myWork/myMailTemp.jsp" %>
								</div>
								<div class="tab-pane fade" id="trash" role="trash" role="tabpanel" aria-labelledby="trash-tab">
									<%@ include file="/WEB-INF/views/web-app/myWork/myMailTrash.jsp" %>
								</div>
							</div>
						</div>
					</div>
					<!-- 메일 리스트 끝 -->
				</div>
			</div>
		</div>
	</div>
</div>