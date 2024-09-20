var projNo = getParameterByName("projNo");
console.log(projNo);

// 이슈 데이터리소스
const IssueDataSource = new kendo.data.DataSource({
		transport: {
			read: "/app/issue/getIssueListByProjNo?projNo="+projNo,
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

// 이슈 시작
var issueGridFlag = false;
function readIssue() {
			if(issueGridFlag == true){
				reloadIssue();
				return;
			}
			$("#issueGrid").kendoGrid({
				dataSource:IssueDataSource,
	             toolbar: [
	             	/*{ template: "<a class='k-button' href='javascript:getOverlayIssueRegistTemplate(\"issueRegistFormTemplate\",\"/app/issue/getIssueRegistInfoByProjNo\");'>이슈 등록</a>" },"search","excel", "pdf" ],*/
	             	{ template: "<a class='k-button' href='javascript:getOverlayIssueRegistTemplate(\"issueRegistFormTemplate\",\"/app/issue/getIssueRegistInfoByProjNo\");'>이슈 등록</a>" },"search"],
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
	                	 return "<a href=\"javascript:getIssueTemplate('/app/issue/getIssueByIssueNo','"+dataItem.issueNo+"','issueDetailForm','issueDetailFormTarget')\" class='text-dark d-inline-block text-truncate "+addClass+"' style='max-width:240px;'>"+ dataItem.title+"</a>";
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

function issueGridSetOpt(){
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
}

function reloadIssue() {
	$("#issueGrid").data("kendoGrid").dataSource.read();
}

const sortingIssueByClick = function(tData) {
	if(tData === "clear"){
		$("#issueGrid").data("kendoGrid").dataSource.filter([]);
		return;
	}
	var externalFilter = {
			filters:[
					{ field:"mileNo", value:tData, operator: "contains" }
					]
		};
			$("#issueGrid").data("kendoGrid").dataSource.filter(externalFilter);
		}

		function readMileIssue(){
			getDefaultTemplate('issueListTemplate','issue-content');
			setTimeout(readIssue,200);
			setTimeout(readMile,200);
		}

/*핸들바스관련 CRUD*/
function deleteIssueByNo(url, issueNo){

	var issueVO = {"issueNo":issueNo, "projNo":projNo};
	console.log(issueVO);

	if(window.confirm("정말로 삭제하시겠습니까?")){

		$.ajax({
			url : url,
			type : 'POST',
			datatype : 'text',
			data : issueVO,
			success : function(data) {
				alert("삭제가 완료되었습니다.")
				document.getElementById('issue-tab').click();
			}, // success
			error : function(xhr, status) {
				alert("삭제에 실패하였습니다.");
			}
		});

	} else {
		return;
	}

}


function projIssueReplyModify(){
	var issueResNo = $("#projIssueReplyModifyIssueResNo").val();
	var content = $("#projIssueReplyModifyContent").val();
	var issueNo = $("#issueNo").val();
	var projNo = $("#projIssueDetailProjNo").val();
	var issueReplyVO = {
			"issueResNo" : issueResNo,
			"content"    : content,
			"issueNo"    : issueNo
		}

	var projIssueReplyModifyConfirm = confirm("수정하시겠습니까?");

	if(projIssueReplyModifyConfirm){
		$.ajax({
			type   : "POST",
			url    : "/app/issueReply/modifyIssueReply",
			data   : issueReplyVO,
			success: function(data){
				alert("수정이 완료되었습니다.");
				getIssueTemplate('/app/issue/getIssueByIssueNo',data,'issueDetailForm','issueDetailFormTarget');
				var content = document.getElementById('projIssueReplyRegistContent');
				content.value = null;
				$("#projIssueReplyModifyModalCloseButton").click();
			},
			error  : function(error){
				console.log("댓글 수정 실패 " + error.status + " 에러");
			}
		});
	}
}

function projIssueReplyRemove(issueResNo){
	var issueNo = $("#issueNo").val();
	var projNo = $("#projIssueDetailProjNo").val();

	var projIssueReplyRemoveConfirm = confirm("삭제하시겠습니까?");

	if(projIssueReplyRemoveConfirm){
		$.ajax({
			type   : "POST",
			url    : "/app/issueReply/removeIssueReply",
			data   : { "issueResNo" : issueResNo },
			success: function(data){
				alert("삭제가 완료되었습니다.");
				getIssueTemplate('/app/issue/getIssueByIssueNo',issueNo,'issueDetailForm','issueDetailFormTarget');
				var content = document.getElementById('projIssueReplyRegistContent');
				content.value = null;
			},
			error  : function(error){
				console.log("댓글 삭제 실패 " + error.status + " 에러");
			}
		});
	}
}

function projIssueReplyRegist(uid){
	var issueNo = $("#issueNo").val();
	var content = $("#projIssueReplyRegistContent").val();
	var userId = uid;
	var issueReplyVO = {
		"issueNo" : issueNo,
		"content" : content,
		"userId" : userId,
		"projNo" : projNo
	}

	if(!content){
		alert("댓글을 입력해주세요.");
		return;
	}

	$.ajax({
		type   : "POST",
		url    : "/app/issueReply/registIssueReply",
		data   : issueReplyVO,
		success: function(data){
			console.log("댓글 등록 성공");
			getIssueTemplate('/app/issue/getIssueByIssueNo',data,'issueDetailForm','issueDetailFormTarget');
			var content = document.getElementById('projIssueReplyRegistContent');
			content.value = null;
		},
		error  : function(error){
			console.log("댓글 등록 실패 " + error.status + " 에러");
		}
	});
}

function projIssueReplyModifyForm_go(issueResNo, content){
	$("#projIssueReplyModifyContent").val(content);
	$("#projIssueReplyModifyIssueResNo").val(issueResNo);
}

