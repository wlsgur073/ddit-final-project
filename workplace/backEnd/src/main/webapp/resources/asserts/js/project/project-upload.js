function uploadForm(target) {

    function onChange() {
        var upload = $("#"+target).getKendoUpload();
        upload.destroy();

        initUpload();
    }

    var initUpload = function () {
        $("#"+target).kendoUpload({
        }).data("kendoUpload");
    };

    initUpload();
}

function deleteIssueDocument(DOC_NO, path, issueNo){
	var fileVO = {
		"DOC_NO" : DOC_NO,
		"path"   : path
	}

	if(window.confirm("정말로 파일을 삭제하시겠습니까?")){
		$.ajax({
			url      : "/app/task/taskDocumentRemove",
			type     : 'POST',
			datatype : 'text',
			data     : fileVO,
			success  : function(data){
				alert("삭제가 완료되었습니다.");
				getIssueTemplate('/app/issue/getIssueByIssueNo',issueNo,'issueDetailForm','issueDetailFormTarget');
		    	getIssueReplyTemplate("/app/issueReply/getIssueReplyByIssueNo",issueNo,"issueReplyForm","issueReplyFormTarget");
			},
			error : function(xhr, status) {
				alert("삭제에 실패하였습니다.");
			}
		});
	}else{
		return;
	}
}

function deleteDocument(DOC_NO, path, taskNo){

	var fileVO = {"DOC_NO":DOC_NO,"path":path};

	console.log(fileVO);
	if(window.confirm("정말로 파일을 삭제하시겠습니까?")){

		$.ajax({
			url : "/app/task/taskDocumentRemove",
			type : 'POST',
			datatype : 'text',
			data : fileVO,
			success : function(data) {
				alert("삭제가 완료되었습니다.");
				getTaskTemplate('/app/task/getTaskDetailByTaskNo',taskNo,'taskDetailForm','taskDetailFormTarget')
			}, // success
			error : function(xhr, status) {
				alert("삭제에 실패하였습니다.");
			}
		});

	} else {
		return;
	}
}

function modifyUploadForm(target, data) {

	console.log(data.fileList);
	var fileList = data.fileList;
	var files = [];

	for (var i = 0; i < fileList.length; i++) {
		var file = "";
		var name = fileList[i].name;
		var size = fileList[i].size;
		var extension = fileList[i].extension;
		file = {"name":name,"size":size,"extension":extension};
		files.push(file);
	}

	console.log(files);

    function onChange() {
        var upload = $("#"+target).getKendoUpload();
        upload.destroy();

        initModifyUpload();
    }

    var initModifyUpload = function () {
        var targetFile =
        	$("#"+target).kendoUpload({
        	select:onSelect,
        }).data("kendoUpload");
    };

    initModifyUpload();
}

function taskDocumentDownload(fileName, taskTitle, projTitle){
	// path = projTitle+ '/업무/' + taskTitle + '/' + fileName
	var path = '' + projTitle + '/업무/' + taskTitle + '/' + fileName;

	location.href='/app/task/taskDownload?path='+path;
}

function issueDocumentDownload(fileName, issueTitle, projTitle){
	var path = '' + projTitle + '/이슈/' + issueTitle + '/' + fileName;
	location.href='/app/task/taskDownload?path=' + path;
}

function projectDocumentDownload(fileName, projTitle){
	var path = '' + projTitle + '/중요첨부/'  + fileName;
	location.href='/app/task/taskDownload?path=' + path;
}

