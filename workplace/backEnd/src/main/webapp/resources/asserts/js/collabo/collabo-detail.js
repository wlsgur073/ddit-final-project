if(window.location.pathname == '/app/collabo/main'){
		getTemplate('/app/collabo/getCollaboByCprojNo','collaboDetailIntro','collaboDetailIntroTarget');
		getTemplate('/app/collabo/getCollaboByCprojNo','collaboDetailNotify','collaboDetailNotifyTarget');
		getTemplate('/document/getDocumentListForCprojDetail','collaboDetailDocument','collaboDetailDocumentTarget');
		getTemplate('/getUserByCprojNo','collaboDetailMember','collaboDetailMemberTarget');
}

function subProjList(cprojNo) {
	
	$.ajax({
		url : "/app/collabo/getCollaboSubProj",
		type : "POST",
		data : {"cprojNo":cprojNo},
		success : function(arg) {
			
			console.log(arg);
			
			let projUrl = "/app/project/main?projNo=";
			let subProjTitle = "";
			
			for (var i = 0; i < arg.length; i++) {
				
				subProjTitle += "<tr><td><a class='collabo-a' href='"+projUrl+""+arg[i].projNo+"' >"+arg[i].title+"</a></td></tr>";
			}
			
			//subProjTitle += "<tr><td><a href='"+projUrl+""+arg.projNo+"' >"+arg.title+"</a></td></tr>";
			
			document.getElementById('selectSubProject').innerHTML= subProjTitle;
		},
		error : function(arg) {
			alert("에러" + arg.status + "메세지" + arg.responseText);
		}
	})
	
};


//콜라보 멤버 초대폼
function getOverlayinviteCollaboMemberForm(templateId) {

	$.ajax({
		url      : "/app/collabo/getCollaboByCprojNo",
		type     : 'POST',
		datatype : 'text',
		data     : {"cprojNo":cprojNo},
		success  : function(data){
			console.log(" getOverlayinviteCollaboMemberForm => overlay Success");

			on();
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			$('#inviteIdTag').tagsInput({
				defaultText:'메일 입력'
			});
		},
		error : function(xhr, status) {
			alert("시스템 오류입니다. 관리자에게 문의하세요.");
		}
	});

}

//콜라보 상세페이지 문서 업로드
function uploadCollaboDoc(){

	var cprojectVO = $('#uploadCollaboDocument')[0];
	var formData = new FormData(cprojectVO);
	formData.append("cprojNo",cprojNo);

	//파일 개수 체크
	var uploadFileList = document.querySelectorAll('.k-upload-files li');
	var remainFileList = document.querySelectorAll('.task-files li');
	var multiFileList = document.querySelectorAll('.k-file-name-size-wrapper');
	var refileLen = remainFileList.length;
	var upFileLen = uploadFileList.length;
	var muFileLen = multiFileList.length;

	if((refileLen+upFileLen+muFileLen) > 6){
		alert('파일은 총 5개까지만 업로드 할 수 있습니다.');
		return;
	}

	$.ajax({
		url : "/app/collabo/uploadCollaboDocument",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록에 성공했습니다.");
			getTemplate('/document/getDocumentListForCprojDetail','collaboDetailDocument','collaboDetailDocumentTarget');
			off();
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		},
		cache:false,
		contentType:false,
		processData:false
	});

}
//콜라보 문서삭제
function deleteCollaboDoc(path, DOC_NO){
	var fileVO = {
			"DOC_NO" : DOC_NO,
			"path"   : path
		}

		if(window.confirm("정말로 파일을 삭제하시겠습니까?")){
			
			$.ajax({
				url      : "/app/collabo/deleteCollaboDoc",
				type     : 'POST',
				datatype : 'text',
				data     : fileVO,
				success  : function(data){
					alert("삭제가 완료되었습니다.");
					getTemplate('/document/getDocumentListForCprojDetail','collaboDetailDocument','collaboDetailDocumentTarget');
				},
				error : function(xhr, status) {
					alert("삭제에 실패하였습니다.");
				}
			});
		}else{
			return;
		}
}














