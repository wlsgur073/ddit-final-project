function uploadForm(target) {
    function onChange() {
        var upload = $("#"+target).getKendoUpload();
        upload.destroy();
        
        initUpload();
    }

    var initUpload = function () {
        $("#"+target).kendoUpload({}).data("kendoUpload");
    };

    initUpload();
}

function taskDocumentDownload(fileName, taskTitle, projTitle){
	// path = projTitle+ '/업무/' + taskTitle + '/' + fileName
	var path = '' + projTitle + '/업무/' + taskTitle + '/' + fileName;
	location.href='/app/task/taskDownload?path=' + path;

}

function issueDocumentDownload(fileName, issueTitle, projTitle){
	var path = '' + projTitle + '/이슈/' + issueTitle + '/' + fileName;
	location.href='/app/task/taskDownload?path=' + path;
}