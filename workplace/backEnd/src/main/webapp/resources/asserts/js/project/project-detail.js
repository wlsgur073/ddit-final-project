function readProjDetail(){
	getTemplate('/app/project/getProjectByProjNo','projectDetailIntro','projectDetailIntroTarget');
	getTemplate('/app/project/getProjectByProjNo','projectDetailNotify','projectDetailNotifyTarget');
	getTemplate('/document/getDocumentListForProjDetail','projectDetailDocument','projectDetailDocumentTarget');
	getTemplate('/getUserByProjNo','projectDetailMember','projectDetailMemberTarget');
}

var projNo = getParameterByName("projNo");
console.log(projNo);

function uploadProjectDoc(){

	var projectVO = $('#uploadProjectDocument')[0];
	var formData = new FormData(projectVO);
	formData.append("projNo",projNo);

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
		url : "/app/project/uploadProjectDocument",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록에 성공했습니다.");
			getTemplate('/document/getDocumentListForProjDetail','projectDetailDocument','projectDetailDocumentTarget');
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

/*핸들바스관련 CRUD*/
function deleteProjectDoc(path, DOC_NO){
	var fileVO = {
			"DOC_NO" : DOC_NO,
			"path"   : path
		}

		if(window.confirm("정말로 파일을 삭제하시겠습니까?")){
			$.ajax({
				url      : "/app/project/deleteProjectDoc",
				type     : 'POST',
				datatype : 'text',
				data     : fileVO,
				success  : function(data){
					alert("삭제가 완료되었습니다.");
					getTemplate('/document/getDocumentListForProjDetail','projectDetailDocument','projectDetailDocumentTarget');
				},
				error : function(xhr, status) {
					alert("삭제에 실패하였습니다.");
				}
			});
		}else{
			return;
		}
}

function getOverlayModifyUserRole(templateId, clickName) {

	$.ajax({
		url      : "/app/project/getProjectMemberList",
		type     : 'POST',
		datatype : 'text',
		data     : {"projNo":projNo},
		success  : function(data){
			console.log("overlay Success");
			var dataMap = {"userList" : data , "clickName": clickName};
			console.log(dataMap);

			on();
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(dataMap);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);

		},
		error : function(xhr, status) {
			alert("시스템 오류입니다. 관리자에게 문의하세요.");
		}
	});
}

function getOverlayRemoveUserProject(templateId, clickName) {

	$.ajax({
		url      : "/app/project/getProjectMemberList",
		type     : 'POST',
		datatype : 'text',
		data     : {"projNo":projNo},
		success  : function(data){
			console.log("overlay Success");
			var dataMap = {"userList" : data , "clickName": clickName};
			console.log(dataMap);

			on();
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(dataMap);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
		},
		error : function(xhr, status) {
			alert("시스템 오류입니다. 관리자에게 문의하세요.");
		}
	});

}

function getOverlayinviteMemberForm(templateId) {

	$.ajax({
		url      : "/app/project/getProjectByProjNo",
		type     : 'POST',
		datatype : 'text',
		data     : {"projNo":projNo},
		success  : function(data){
			console.log(" getOverlayinviteMemberForm => overlay Success");

			on();
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			$('#inviteIdTag').tagsInput({
				defaultText:'닉네임 입력'
			});
		},
		error : function(xhr, status) {
			alert("시스템 오류입니다. 관리자에게 문의하세요.");
		}
	});

}

//url 파라미터값 가져오기
function getParameterByName(name) {
  	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
   	results = regex.exec(location.search);
   	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
