<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head>
<style type="text/css">

.k-grid tr .checkbox-align {
	text-align: center;
	vertical-align: middle;
}

.k-rating-container .k-rating-item {
	padding: 4px 0;
}

.k-rating-container .k-rating-item .k-icon {
	font-size: 16px;
}

.dropdown-country-wrap {
	display: flex;
	flex-wrap: nowrap;
	align-items: center;
	white-space: nowrap;
}

.dropdown-country-wrap img {
	margin-right: 10px;
}

#tagGrid-table .k-grid-edit-row>td>.k-rating {
	margin-left: 0;
	width: 100%;
}

.k-grid .k-grid-search {
	margin-left: auto;
	margin-right: 0;
}
.k-grid-header{
	padding:0px;
}
.k-grid-header-wrap.k-auto-scrollable{
	border-right: none;
	
}

</style>

</head>

<body>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 ">
				<div class="x_panel">
					<div class="x_title">
						<h1 class="printTagName"></h1>
						<div class="clearfix"></div>
					</div>
					<!-- start x_content -->
					<div class="x_content">
						<div id="tagGrid-layout">
						    <div id="tagGrid-table" style="height:100%; min-height: 680px;"></div>
						</div>
					</div>
					<!-- end x_content -->

				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
</body>
