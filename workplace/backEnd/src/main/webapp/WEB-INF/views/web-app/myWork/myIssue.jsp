<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div id="myIssue-content">
	<div class="k-content d-flex row"  style="height:800px;overflow:hidden;">
		<div class="mile-content" style="width:30%;overflow:auto;border:1px solid #e0e0e0;border-right:none;">
			<div class="x_title" style="padding-top:8px;padding-bottom:8px;border-bottom:1px solid #e0e0e0;">
				<h2 class="ml-2">마일스톤</h2>
				<div class="clearfix">
					<a class="k-button shadow-none float-right mr-1" style="background-color:#ebebeb;" href="javascript:getOverlayMyMileRegistTemplate('${userVO.userId}');">마일스톤 등록</a>
				</div>
			</div>
			<div class="panel-heading form-inline justify-content-end mb-4" style="margin-right:10px;height:33px;">
				<div class="form-group ml-2">
					<span class="k-textbox k-grid-search" style="width:220px;">
						<input autocomplete="off" id="myMileSearch" placeholder="검색" title="Search..." class="k-input">
						<span class="k-input-icon">
							<span class="k-icon k-i-search"></span>
						</span>
					</span>
				</div>
			</div>      
			<div id="myMileList" class="ml-2" style="margin-right:10px;"></div>
		</div>
		<div id="myIssueGrid" style="width:69%;"></div>
	</div>
</div>

<script type="text/x-kendo-template" id="myMileTemplate">
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
					<a class="dropdown-item" href="javascript:getOverlayMyMileModifyTemplate('#:id#')">수정</a>
					<a class="dropdown-item" href="javascript:removeMyMilestone('#:id#');">삭제</a>
				</div>
			</div>
    	</div>
        <p class="m-0 text-truncate"><small>#=content#</small></p>
        <p class="m-0"><small>#:kendo.toString(regdate,"yyyy-MM-dd")#</small></p>
    </div>
</script>

<script>
	/* Handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {
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
	}); */
	
	function xssPurify(html) {
	    const extractTextPattern = /(<([^>]+)>)/gi;
	    var newhtml = html.replace(extractTextPattern, "");
	    return newhtml;
	}
	
	function printData(data, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(data);
		target.html('').html(html);
	}
	
	function myIssueDetail(issueNo, projNo){
		$.ajax({
			type     : "GET",
		    url      : "<%=request.getContextPath()%>/app/issue/getIssueByIssueNo",
		    dataType : "JSON",
		    data : {
		    	"issueNo" : issueNo,
		    	"projNo" : projNo
		    },
		    success : function(data) {
		    	console.log(data);
		    	myIssueReply(issueNo, projNo);
		    	
		    	printData(data, $('#myIssueDetailFormTarget'), $('#myIssueDetailForm'));
		    	
		    	document.getElementById('myIssueDetail-tab').click();
		    },
		    error : function(error) {
		    	console.log("Handlebars error!!");
		    }
		});
	}
</script>
