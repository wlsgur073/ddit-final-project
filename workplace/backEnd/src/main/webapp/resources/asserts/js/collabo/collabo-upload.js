//콜라보 업무 수정 파일 업로드 함수
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

//콜라보 업무 파일 다운로드 함수
function taskDocumentDownload(fileName, taskTitle, cprojTitle){
	// path = projTitle+ '/업무/' + taskTitle + '/' + fileName
	var path = '' + cprojTitle + '/업무/' + taskTitle + '/' + fileName;
	console.log("cprojTitle => " + cprojTitle);

	location.href='/app/collabo/taskDownload?path='+path;
}

//콜라보 업무 파일 삭제 함수
function deleteDocument(DOC_NO, path, taskNo){

	var fileVO = {"DOC_NO":DOC_NO,"path":path};

	console.log(fileVO);
	if(window.confirm("정말로 파일을 삭제하시겠습니까?")){

		$.ajax({
			url : "/app/collabo/taskDocumentRemove",
			type : 'POST',
			datatype : 'text',
			data : fileVO,
			success : function(data) {
				alert("삭제가 완료되었습니다.");
				getTaskTemplate('/app/collabo/getTaskDetailByTaskNo',taskNo,'collaboTaskDetailForm','collaboTaskDetailFormTarget')
				collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
			}, // success
			error : function(xhr, status) {
				alert("삭제에 실패하였습니다.");
			}
		});

	} else {
		return;
	}
}

//콜라보 문서 다운로드
function CollaboDocumentDownload(fileName, cprojTitle){
	var path = '' + cprojTitle + '/중요첨부/'  + fileName;
	location.href='/app/collabo/taskDownload?path=' + path;
}



