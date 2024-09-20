<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="issue-content">
  <div class="k-content d-flex row"  style="height:800px;overflow:hidden;">
  			  <div class="mile-content" style="width:30%;overflow:auto;">
  			  	<div class="x_title" style="padding-bottom:16px;border-bottom:1px solid #e0e0e0;">
      				<h2>마일스톤</h2>
      				<div class="clearfix">
      					<a class="k-button float-right" style="background-color:#ebebeb" href="javascript:getOverlayMileRegistTemplate('mileStoneRegistFormTemplate','/app/milestone/getMilestoneInfo');">마일스톤 등록</a>
      				</div>
    			</div>
        		  <div class="panel-heading form-inline justify-content-end mb-4">
        		  <!-- <div class="form-group">
			            <select id="groupIds" class="form-control">
							<option>전체</option>
							<option>해결</option>
							<option>미해결</option>
						</select>
			        </div> -->
			        <div class="form-group justify-content-end">
			            <span class="k-textbox k-grid-search k-display-flex">
			            	<input autocomplete="off" id="search-term" placeholder="검색" title="Search..." class="k-input">
			            	<span class="k-input-icon">
			            		<span class="k-icon k-i-search">
			            		</span>
			            	</span>
			            </span>
			        </div>

			    </div>
        		<div id="mileList" style="margin-right:10px;"></div>
    		</div>
        	  <div id="issueGrid" style="width:69%;"></div>
    </div>
</div>

<script type="text/x-kendo-template" id="mileTemplate">
    <div class="milestone" data-bind="click: searchIssue" style="border-left-width: 2px;border-left-color:
		# if(status == "B301"){ #
			rgb(0,105,92)
		# }else if(status == "B302"){ #
			rgb(68,81,181)
		# } #
	;">
		<div class="x_title row justify-content-between">
      		<h4>
				<span class="d-inline-block text-truncate" style="max-width:200px;color:black;">#:title#</span>
				<span class="#=status#"></span><span class="internal"></span>
			</h4>
				<div class="text-right">
					<a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"></a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="javascript:getOverlayMileModifyTemplate('mileStoneModifyFormTemplate','/app/milestone/getMilestoneByMileNo','#:id#')">수정</a>
							<a class="dropdown-item" href="">삭제</a>
						</div>
				</div>
    	</div>
        <p class="m-0"><small>#:xssPurify(content)#</small></p>
        <p class="m-0"><small>#:kendo.toString(regdate,"yyyy-MM-dd")#</small></p>
    </div>
</script>

