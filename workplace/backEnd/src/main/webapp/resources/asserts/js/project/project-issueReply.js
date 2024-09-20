var projNo = getParameterByName("projNo");
console.log(projNo);

// 이슈댓글 데이터리소스
const IssueReplyDataSource = new kendo.data.DataSource({
		transport: {
			read: {
			      url: "/app/task/getIssueReplyByIssueNo",
			      dataType: "json",
			      data: { "projNo": projNo, "IssueNo": }
			    }
	    },
	    schema: {
            model: {
            	id:"id",
                fields: {
                	content: { from:"important", type: "string" },
                	userId: { from:"userId", type: "string" },
                	updatedate: { from:"updatedate", type: "date"},
                	regdate: { from:"regdate", type: "string" },
                }
            }
        },
});

// 이슈 시작
var issueReplyGridFlag = false;
function readIssueReply(projNo, issueNo) {

			if(issueReplyGridFlag == true){
				reloadIssue();
				return;
			}
			$("#issueReplyGrid").kendoGrid({
				dataSource:
					transport: {
					read: {
					      url: "/app/task/getIssueReplyByIssueNo",
					      dataType: "json",
					      data: { "projNo": projNo, "IssueNo": issueNo}
					    }
			    },
			    schema: {
		            model: {
		            	id:"id",
		                fields: {
		                	content: { from:"important", type: "string" },
		                	userId: { from:"userId", type: "string" },
		                	updatedate: { from:"updatedate", type: "date"},
		                	regdate: { from:"regdate", type: "string" },
		                }
		            }
		        },
		        sort: { field: "regdate", dir: "asc" },
	         });
			issueReplyGridFlag = true;
}



/*function issueGridSetOpt(){
	var issueGrid = $("#issueGrid").data("kendoGrid");
	issueGrid.setOptions({
		 batch: true,
	     autoSync: false,
	     height: "100%",
	     numeric: true,
	     sortable: true,
	     navigatable: true,
	     resizable: true,
	     groupable: true,
	})
}*/
/*
function reloadIssue() {
	$("#issueGrid").data("kendoGrid").dataSource.read();
}*/
