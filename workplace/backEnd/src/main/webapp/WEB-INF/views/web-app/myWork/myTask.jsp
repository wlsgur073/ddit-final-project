<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<!-- 업무 리스트 -->
<div class="x_content">
	<div class="row">
		<div class="col-sm-12">
			<div class="card-box">
				<div id="example">
					<div class="k-content myTaskBox">
						<div id="myTaskBoard"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script id="myTask-template" type="text/x-kendo-template">
		<div class="">
			<div class="two" style="border-left:2px solid;border-left-width: 2px;border-left-color:
				# if(important == "B101"){ #
					rgb(40, 167, 69)
				# }else if(important == "B102"){ #
					rgb(255, 193, 7)
				# }else if(important == "B103"){ #
					rgb(220, 53, 69)
				# } #
				;">
				<div class="d-flex justify-content-end px-2"><i class="mdi mdi-star-outline pr-1 star"></i><i class="mdi mdi-dots-horizontal dot"></i></div>
				<div class="px-3">
					<h2 class="name"><a id="task_go#:taskNo#" href="javascript:myTaskDetail(#:taskNo#, #:projNo#);" class="text-dark d-inline-block text-truncate" style="max-width:199px;">#:title#</a></h2>
					<div class="quote2  bg-light text-truncate mb-2"> #:important# #:xssPurify(content)#</div>
				</div>
				<div class="d-flex justify-content-between px-3 align-items-center pb-2">
					<div class="d-flex justify-content-start align-items-center"> <i class="mdi mdi-calendar-clock date"></i> <span class="quote2 d-inline-block text-truncate" style="max-width:110px;"><small><i class="fa fa-user"></i> #:nickname#</small></span> </div>
					<div class="d-flex justify-content-start align-items-center"> <i class="mdi mdi-calendar-clock date"></i> <span class="quote2 pr-3"><small><i class="fa fa-clock-o"></i> #:kendo.toString(regdate,"yyyy-MM-dd")#</small></span> </div>
				</div>
			</div>
		</div>
	</script>
	
	<script>
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
		
		function myTaskDetail(taskNo, projNo){
			$.ajax({
				type     : "GET",
			    url      : "<%=request.getContextPath()%>/app/task/getTaskDetailByTaskNo",
			    dataType : "JSON",
			    data : {
			    	"taskNo" : taskNo,
			    	"projNo" : projNo
			    },
			    success : function(data) {
			    	printData(data, $('#myTaskDetailFormTarget'), $('#myTaskDetailForm'));
			    	
			    	document.getElementById('myTaskDetail-tab').click();
			    },
			    error : function(error) {
			    	console.log("Handlebars error!!");
			    }
			});
		}
	</script>
</div>
