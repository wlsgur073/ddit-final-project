<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="collabo-detail-body mb-3 p-3">
		<div class="x_title">
			<h2 class="title">
				<i class="fa fa-gears fa-lg"></i> <span class=""> {{title}} </span>
			</h2>
			<div class="clearfix">
				<div class="clearfix header-title justify-content-end">
					<i class="fa fa-edit fa-2x"
						onclick="getOverlayModifyTemplate('projModifyFormTemplate','/app/project/getProjectByProjNo')"></i>
				</div>
			</div>
		</div>
		<div class="x_content mb-3">
			<span class="collabo-intro"> {{{intro}}} </span>
		</div>
	</div>

	<div class="collabo-detail-body mb-3 px-3">
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

					<span class="badge badge-success">1</span>

				</div>
			</div>
		</div>
	</div>

	<!-- start accordion -->
	<div class="accordion" id="accordion1" role="tablist"
		aria-multiselectable="true">
		<div class="panel">
			<a class="panel-heading collabo-detail-a" role="tab" id="headingOne1"
				data-toggle="collapse" data-parent="#accordion1"
				href="#collapseOne1" aria-expanded="true"
				aria-controls="collapseOne">
				<h4 class="panel-title">
					<i class="fa fa-object-group"></i> 연관 프로젝트
				</h4>
			</a>
			<div id="collapseOne1" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body">
					<table class="table table-striped">

						<tbody>
							<tr>
								<td><a href="" class="collabo-detail-a">하위 프로젝트 1</a></td>
							</tr>

							<tr>
								<td><a href="" class="collabo-detail-a">하위 프로젝트 2</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- end of accordion -->


</body>
</html>