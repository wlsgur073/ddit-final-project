<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- page content -->
<div class="right_col" role="main">
	<!-- 내 작업 바디 시작 -->
	<div class="x_panel">
		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>프로젝트 등록</h2>
						<div class="clearfix"></div>
					</div>
					<span>프로젝트를 등록할 수 있습니다.</span>
					<div class="x_content">
						<br />
						<form class="form-horizontal form-label-left">
							<!-- 프로젝트명 -->
							<div class="form-group row">
								<label class="control-label col-md-3 col-sm-3">프로젝트명</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control"
										placeholder="프로젝트명을 입력해주세요" />
								</div>
							</div>
							<div class="ln_solid"></div>
							<!-- 프로젝트명 -->
							<!-- 공개범위 -->
							<div class="form-group row">
								<label class="col-md-3 col-sm-3 control-label">공개범위
									<br /> <small class="text-navy">검색결과에
										따른 공개여부</small>
								</label>
								<div class="col-md-7 col-sm-7">
									<div class="radio">
										<i class="fa fa-globe"></i> 전체 : 모든 사람들에게 프로젝트를 공개합니다. <label
											class="ml-2 mt-2"> <input type="radio" checked=""
											value="option1" id="optionsRadios1" name="optionsRadios" />
										</label>
									</div>
									<div class="radio">
										<i class="fa fa-shield"></i> 제한 : 검색결과에 출력되지만 내용은 공개하지 않습니다. <label
											class="ml-2"> <input type="radio" checked=""
											value="option2" id="optionsRadios2" name="optionsRadios" />
										</label>
									</div>
									<div class="radio">
										<i class="fa fa-lock"></i> 비공개 : 검색결과에 출력되지 않습니다. <label
											class="ml-2"> <input type="radio" checked=""
											value="option3" id="optionsRadios3" name="optionsRadios" />
										</label>
									</div>
								</div>
							</div>
							<div class="ln_solid"></div>
							<!-- 공개범위 -->
							<!-- 멤버 초대 -->
							<div class="form-group row">
								<label class="control-label col-md-3 col-sm-3">구성원
									초대</label>
								<!-- 프로젝트 공지사항 추가 (모달)버튼 시작 -->
								<div class="btn create-btn memberadd-bg-btn" data-toggle="modal"
									data-target="#project-member-add">
									<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50"
										fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                      <path
											d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"
											class="cQfklM" />
                    </svg>
								</div>
								<div class="text-center"></div>
								<!-- 프로젝트 공지사항 추가 (모달)버튼 끝 -->
							</div>
							<div class="ln_solid"></div>
							<!-- 멤버 초대 -->
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-9 col-sm-9 offset-md-3">
									<button type="button" class="btn btn-primary">취소</button>
									<button type="submit" class="btn btn-success">확인</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<!-- 프로젝트 구성원 추가 모달 시작-->
<div class="modal" id="project-member-add" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title modalTitle">구성원 초대</h4>
				<button type="button" class="close" data-dismiss="modal">
					&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="x_content">
					<!-- form start -->
					<form>
						<p>프로젝트를 함께 할 구성원들을 초대해보세요.</p>
						<div class="card-body">
							<div class="form-group">
								<label for="exampleInputEmail1">이메일로 초대</label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									placeholder="상대방의 이메일을 입력하세요." />
							</div>
							<!-- select -->
							<div class="form-group">
								<label>접근 권한</label> <select class="form-control">
									<option value="" disabled selected hidden>접근 권한을 설정해
										주세요.</option>
									<option>게스트</option>
									<option>팀원</option>
								</select>
							</div>
							<!-- textarea -->
							<div class="form-group">
								<label>보낼 메세지</label>
								<textarea class="form-control" rows="3" placeholder="내용을 입력해주세요"></textarea>
							</div>
						</div>
						<!-- /.card-body -->
						<button type="submit"
							class="btn btn-outline-primary collabo-submit-btn">
							보내기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 프로젝트 구성원 제안 모달 끝-->