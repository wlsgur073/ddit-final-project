<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			#myTaskModifyOverlay {
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
		<div id="myTaskModifyOverlay"></div>
		<!-- 오버레이 끝 -->
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
		<script type="text/x-handlebars-template" id="myTaskModifyFormTemplate">
			<div class="row" id="fadeInTaskModifyContent">
				<div class="col-md-12">
						<div class="x_panel">
							<div class="x_title row d-flex justify-content-between">
								<h5 class="title">
										<i class="fa fa-clone"></i> <span class="task-bold task-sm">업무 수정</span>
								</h5>
								<div class="clearfix">
									<button onclick="myOverlayOff('#myTaskModifyOverlay')" class="btn btn-primary btn-sm">X</button>
								</div>
							</div>
							<div class="x_content">
								<div class="row">
									<!-- 내용저장 -->
									<div class="x_content row">
										<div class="col-sm-12">
											<form id="modifyMyTaskForm" method="post" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
												<!-- 프로젝트명 -->
												<div class="item form-group">
													<label class="col-form-label col-md-2 col-sm-2 label-align" for="projNo">프로젝트</label>
													<div class="col-md-10 col-sm-10">
														<input type="hidden" id="myTaskNo" class="form-control form-control-sm" name="taskNo" value="{{taskNo}}">
														<input type="hidden" id="myTaskProjNo" class="form-control form-control-sm" name="projNo" value="{{projNo}}">
														<input type="text" id="myTaskProjTitle" class="form-control form-control-sm" name="projTitle" value="{{projTitle}}" readonly>
													</div>
												</div>
												<div class="item form-group">
	          										<label class="col-form-label col-md-2 col-sm-2 label-align" for="userNickname">담당자</label>
													<div class="col-md-10 col-sm-10">
														<select class="form-control form-control-sm" id="userNickname" name="userId">
															{{#each userList}}
              													{{#ifCond this.userId "==" "${userVO.userId}"}}
																	<option value="{{this.userId}}" selected>{{this.nickname}}</option>
																{{/ifCond}}		
																{{#ifCond this.userId "!=" "${userVO.userId}"}}
																	<option value="{{this.userId}}">{{this.nickname}}</option>
																{{/ifCond}}
															{{/each}}
            											</select>
													</div>
	        									</div>
												<div class="item form-group">
	          										<label class="col-form-label col-md-2 col-sm-2 label-align" for="important">중요도</label>
													<div class="col-md-10 col-sm-10">
	            										<select class="form-control form-control-sm" id="important" name="important">
															{{#ifCond important "==" "B101"}}
																<option value="B101" selected>낮음</option>
	              												<option value="B102">중간</option>
	             												<option value="B103">높음</option>
															{{/ifCond}}
															{{#ifCond important "==" "B102"}}
																<option value="B101">낮음</option>
	              												<option value="B102" selected>중간</option>
	             												<option value="B103">높음</option>
															{{/ifCond}}
															{{#ifCond important "==" "B103"}}
																<option value="B101">낮음</option>
	              												<option value="B102">중간</option>
	             												<option value="B103" selected>높음</option>
															{{/ifCond}}
	            										</select>
													</div>
	        									</div>
												<div class="item form-group">
			          								<label class="col-form-label col-md-2 col-sm-2 label-align" for="status">진행상태</label>
													<div class="col-md-10 col-sm-10">
			            								<select class="form-control form-control-sm" id="status" name="status">
															{{#ifCond status "==" "B201"}}
																<option value="B201" selected>미배정</option>
			              										<option value="B202">진행중</option>
			             										<option value="B203">지연중</option>
			             										<option value="B204">완료</option>
															{{/ifCond}}
															{{#ifCond status "==" "B202"}}
																<option value="B201">미배정</option>
			              										<option value="B202" selected>진행중</option>
			             										<option value="B203">지연중</option>
			             										<option value="B204">완료</option>
															{{/ifCond}}
															{{#ifCond status "==" "B203"}}
																<option value="B201">미배정</option>
			              										<option value="B202">진행중</option>
			             										<option value="B203" selected>지연중</option>
			             										<option value="B204">완료</option>
															{{/ifCond}}
															{{#ifCond status "==" "B204"}}
																<option value="B201">미배정</option>
			              										<option value="B202">진행중</option>
			             										<option value="B203">지연중</option>
			             										<option value="B204" selected>완료</option>
															{{/ifCond}}
			            								</select>
													</div>
			        							</div>
												<div class="item form-group">
													<label class="col-form-label col-md-2 col-sm-2 label-align" for="startdate">시작일</label>
													<div class="col-md-10 col-sm-10">
														<input type="date" id="myTaskModifyStartdate" name="startdate" class="form-control form-control-sm" value="{{formatTime startdate "yyyy-MM-DD"}}">
													</div>
												</div>
												<div class="item form-group">
													<label class="col-form-label col-md-2 col-sm-2 label-align" for="enddate">마감일</label>
													<div class="col-md-10 col-sm-10">
														<input type="date" id="myTaskModifyEnddate" name="enddate" class="form-control form-control-sm" value="{{formatTime enddate "yyyy-MM-DD"}}">
													</div>
												</div>
												<div class="item form-group">
													<label class="col-form-label col-md-2 col-sm-2 label-align" for="title">업무명</label>
													<div class="col-md-10 col-sm-10">
														<input type="text" id="myTaskModifyOverlayTitle" class="form-control form-control-sm" name="title" value="{{title}}">
													</div>
												</div>
												<div class="form-group">
													<textarea id="myTaskModifyOverlayContent" class="myTaskOverlayContentModify" name="content">{{content}}</textarea>
												</div>
												<div style="width:100%; float:left">
													<input name="files" id="myTaskModifyUpload" type="file" aria-label="files" />
												</div>
												<div class="col-md-12 col-sm-12 m-1 p-1 text-right">
													<button type="button" class="btn btn-success" onclick="modifyMyTask();">수정</button>
													<button type="button" class="btn btn-primary" onclick="cancelMyTaskModify();">취소</button>
													<button type="reset" class="btn btn-info">리셋</button>
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
		
		<script>
			window.addEventListener('load', function() {
				Handlebars.registerHelper('formatTime', function (date, format) {
				    var mmnt = moment(date);
				    return mmnt.format(format);
				});
				
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
			});
			
			function printData(data, target, templateObject){
				var template = Handlebars.compile(templateObject.html());
				
				var html = template(data);
				target.html('').html(html);
			}
			
			function myTaskModifyForm(data){
				printData(data, $('#myTaskModifyOverlay'), $('#myTaskModifyFormTemplate'));
			}
			
			function cancelMyTaskModify(){
				myOverlayOff('#myTaskModifyOverlay');
			}
			
			function modifyMyTask() {
				var projNo = $("myTaskProjNo").val();
				var content = $("#myTaskModifyOverlayContent").val();
				var title = $("#myTaskModifyOverlayTitle").val();
				var startdate = $("#myTaskModifyStartdate").val();
				var enddate = $("#myTaskModifyEnddate").val();
				
				if(!title){
					alert("업무명을 입력해주세요.");
					return;
				}
				if(!content){
					alert("업무내용을 입력해주세요.");
					return;
				}
				if(content == "<p><br></p>"){
					alert("업무내용을 입력해주세요.");
					return;
				}
				if(!startdate){
					alert("시작일을 입력해주세요.");
					return;
				}
				if(!enddate){
					alert("마감일을 입력해주세요.");
					return;
				}
				
				var taskVO = $('#modifyMyTaskForm')[0];
				var formData = new FormData(taskVO);
				
				$.ajax({
					url : "<%=request.getContextPath()%>/app/task/modifyTaskDetailByTaskNo",
					type : 'POST',
					datatype : 'text',
					data : formData,
					success : function(data) {
						alert("수정을 완료하였습니다.");
						myOverlayOff('#myTaskModifyOverlay');
						myTaskDetail(data.taskNo, data.projNo);
						/* document.getElementById('myTaskDetail-tab').click(); */
						/* $("#myTaskBoard").remove();
						$(".myTaskBox").append("<div id='myTaskBoard'></div>"); */
					},
					error : function(status) {
						alert("수정에 실패하였습니다.");
					},
					cache:false,
					contentType:false,
					processData:false
				});
			}
		</script>
	</body>
</html>
	