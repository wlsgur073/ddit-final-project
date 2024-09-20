//이슈 댓글 시작
function myIssueReply(issueNo, projNo){
	var issueVO = {
		"issueNo": issueNo,
		"projNo" : projNo
	}
	
	$.ajax({
		type    : 'GET',
		url     : "/app/issueReply/getIssueReplyByIssueNo",
		data    : issueVO,
		dataType: "JSON",
		success : function(data){
			printData(data, $('#myIssueReplyFormTarget'), $('#myIssueReplyForm'));
		},
		error : function(error){
			console.log("Handlebars error!!");
		},
	});
}
//이슈 댓글 끝
