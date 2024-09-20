var cprojNo = getParameterByName("cprojNo");
console.log(cprojNo);

// 이슈 데이터리소스
const IssueDataSource = new kendo.data.DataSource({
		transport: {
			read: "/app/collabo/getIssueListByCprojNo?cprojNo="+cprojNo,
	    },
	    schema: {
            model: {
            	id:"id",
                fields: {
                	title: { from:"title", type: "string" },
                	important: { from:"important", type: "string" },
                	userId: { from:"userId", type: "string" },
                	updatedate: { from:"updatedate", type: "date"},
                	status: { from:"status", type: "string" },
                	mileNo : { from:"mileNo", type: "string"}
                }
            }
        },
});

//이슈 시작
var issueGridFlag = false;
function readIssue() {
			if(issueGridFlag == true){
				reloadIssue();
				return;
			}
			$("#issueGrid").kendoGrid({
				dataSource:IssueDataSource,
	             toolbar: [
	             	{ template: "<a class='k-button' href='javascript:getOverlayIssueRegistTemplate(\"CollaboissueRegistFormTemplate\",\"/app/collabo/getIssueRegistInfoByCprojNo\");'>이슈 등록</a>" },"search"],
	             	messages: {
	            	    commands: {
	            	      search: "검색"
	            	    }
	            	  },
	             columns: [{
	                 selectable: true,
	                 hidden:true,
	                 width: 75,
	                 attributes: {
	                     "class": "checkbox-align",
	                 },
	                 headerAttributes: {
	                     "class": "checkbox-align",
	                 }
	             },  {
	                 field: "title",
	                 title: "이슈 제목",
	                 template: function(dataItem){

	                	 var addClass = ""
	                	 var nowDate = new Date();
	                	 var date = new Date(dataItem.updatedate);
	                	 date.setSeconds(date.getSeconds() + 30);
	                	 if(date > nowDate){
	                		 addClass = "blinkFlag"
	                	 }
	                	 return "<a href=\"javascript:getIssueTemplate('/app/collabo/getIssueByIssueNo','"+dataItem.issueNo+"','issueDetailForm','issueDetailFormTarget')\" class='text-dark d-inline-block text-truncate "+addClass+"' style='max-width:240px;'>"+ dataItem.title+"</a>";
	                 },
	                 headerAttributes: {style: 'text-align: center'},
	                 width: 245,
	                 encoded: false,
	             }, {
	                 field: "important",
	                 title: "중요도",
	                 template: "<span id='badge_success' class='badgeTemplate badge_success text-dark' style='text-align:center;'>#=important#</span>",
	                 attributes: {style: 'text-align: center'},
	 				 headerAttributes: {style: 'text-align: center'},
	                 width: 80,
	                 editable: false,
	             }, {
	                 field: "userId",
	                 title: "작성자",
	                 template: "<span class='text-dark text-truncate' style='max-width:115px;'>#:userId#</span>",
	                 attributes: {style: 'text-align: center'},
	 				 headerAttributes: {style: 'text-align: center'},
					 width: 140
	             }, {
	                 field: "status",
	                 title: "진행상태",
	                 attributes: {style: 'text-align: center'},
	 			 	 headerAttributes: {style: 'text-align: center'},
	 				 width: 90
	             },{
	                 field: "updatedate",
	                 title: "갱신일",
	                 format: "{0:yy-MM-dd}",
	                 attributes: {style: 'text-align: center'},
	 				 headerAttributes: {style: 'text-align: center'},
	 				 width: 110
	             }  ],
	         });
			issueGridSetOpt();

			issueGridFlag = true;

			$("#issueGrid").find(".k-grid-header-wrap").css("border-right", "none");
}