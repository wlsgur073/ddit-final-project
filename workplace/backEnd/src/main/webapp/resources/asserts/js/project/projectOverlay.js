
/**
 * 세션 권한 리로드
 * @returns
 */
function updateUserRole(){
	$.ajax({

		url : "/app/project/getUserRole",
		type : 'POST',
		data : {"projNo":projNo},
		success : function(data) {
			console.log("Session Role 취득완료 =>" + data);
			sessionRole = data;
		}, // success
		error : function(xhr, status) {
			console.log("Session Role 취득실패");
		}
	});
}

/**
 * 기본 템플레이트 가져오기
 * @param templateId
 * @returns
 */
function getOverlayTemplate(templateId) {

	on();
	var template = document.querySelector("#" + templateId).innerText;
	var bindTemplate = Handlebars.compile(template);
	var appe = document.querySelector('#popoverlay');
	var html = bindTemplate();
	appe.innerHTML = html;
	$("#fadeInContent").fadeIn(300);
	prevTarget.push(templateId);
	console.log(prevTarget);

	if (templateId == "projRegistFormTemplate")  {
		summernote_go($('.projSummnote'));
	}
	if (templateId == "projRegistFormTemplate") {
		$('#projectTag').tagsInput({
			defaultText:'태그 입력'
		});
	}

}
/**
 * 프로젝트 수정 템플레이트 가져오기
 * @param templateId
 * @param url
 * @returns
 */
function getOverlayModifyTemplate(templateId, url) {

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"projNo" : projNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.projSummnote'));
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}


/**
 * 프로젝트 중요첨부파일 등록
 * @param templateId
 * @returns
 */
function getOverlayProjDocumentTemplate(templateId) {

	on();
	var template = document.querySelector("#" + templateId).innerText;
	var bindTemplate = Handlebars.compile(template);
	var appe = document.querySelector('#popoverlay');
	var html = bindTemplate();
	appe.innerHTML = html;
	$("#fadeInContent").fadeIn(300);
	uploadForm('projectDocument');
}

/**
 * 업무 등록 템플레이트 가져오기
 * @param templateId
 * @param url
 * @returns
 */
function getOverlayRegistTaskTemplate(templateId, url) {

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"projNo" : projNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.projSummnote'));
			uploadForm('projectTaskUpload');
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

/**
 * 업무 수정 템플레이트 가져오기
 * @param templateId
 * @param url
 * @param taskNo
 * @returns
 */
function getOverlayTaskModifyTemplate(templateId, url, taskNo) {

	var taskVO = {"projNo": projNo, "taskNo": taskNo};

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : taskVO,
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.projSummnote'));
			uploadForm('projectTaskUpload',data);
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}
/**
 * 이슈 등록 템플레이트
 * @param templateId
 * @param url
 * @param IssueNo
 * @returns
 */
function getOverlayIssueRegistTemplate(templateId, url) {

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"projNo" : projNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			uploadForm('projectIssueUpload');
			summernote_go($('.projSummnote'));
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

/**
 * 이슈 수정 템플레이트
 * @param templateId
 * @param url
 * @param IssueNo
 * @returns
 */
function getOverlayIssueModifyTemplate(templateId, url, IssueNo) {

	var issueVO = {"projNo": projNo, "IssueNo": IssueNo};

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : issueVO,
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			uploadForm('projectIssueUpload');
			summernote_go($('.projSummnote'));
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

function getOverlayMileRegistTemplate(templateId, url) {

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"projNo" : projNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.projSummnote'));
			$("#mileIssueTag").kendoMultiSelect({
				autoClose: false
			});

		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}


function getOverlayMileModifyTemplate(templateId, url, mileNo) {

	var mileVO = {"mileNo": mileNo};

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : mileVO,
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.projSummnote'));

			var issueTitle = [];
			var issueNo = [];
			console.log(data.mileVO.issueList);

			if(data.mileVO.issueList != null){
				for(var i = 0; i < data.mileVO.issueList.length; i++){
					issueTitle.push(data.mileVO.issueList[i].title);
					issueNo.push(data.mileVO.issueList[i].issueNo);
					console.log(issueTitle);
				}
				$("#mileIssueTag").kendoMultiSelect({
					autoClose: false,
					value:issueNo,
				});
			} else {
				$("#mileIssueTag").kendoMultiSelect({
					autoClose: false
				});
			}


		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

/* ------------------------------------------- SUBMIT 용 -------------------------------------------*/


function modifyProjectDetail() {
	var projectVO = $('#modifyProjectForm').serialize();
	projectVO += '&projNo='+projNo;
	console.log(projectVO);

	var projectNumber = projNo;
	var whereDist = "프로젝트 상세";
	var targetTitle = document.getElementById('title').value;
	var crud = "수정";

	$.ajax({
		url : "/app/project/modifyProjectDetail",
		type : 'POST',
		datatype : 'text',
		data : projectVO,
		success : function(data) {
			alert("수정에 성공했습니다.");
			getTemplate('/app/project/getProjectByProjNo','projectDetailIntro','projectDetailIntroTarget');
			off();
			projectAlert(sessionId, projectTitle, targetTitle, crud, projectNumber);
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		}
	});
}

function modifyTaskDetail() {
	var taskVO = $('#modifyTaskForm')[0];
	var formData = new FormData(taskVO);
	formData.append("projNo",projNo);

	//파일 개수 체크
	var uploadFileList = document.querySelectorAll('.k-upload-files li');
	var remainFileList = document.querySelectorAll('.task-files li');
	var multiFileList = document.querySelectorAll('.k-file-name-size-wrapper');
	var refileLen = remainFileList.length;
	var upFileLen = uploadFileList.length;
	var muFileLen = multiFileList.length;

	if((refileLen+upFileLen+muFileLen)>6){
		alert('파일은 총 5개까지만 업로드 할 수 있습니다.');
		return;
	}

	var projectNumber = projNo;
	var whereDist = "업무";
	var targetTitle = document.getElementById('title').value;
	var crud = "수정";

	$.ajax({
		url : "/app/task/modifyTaskDetailByTaskNo",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("수정에 성공했습니다.");
			getTaskTemplate('/app/task/getTaskDetailByTaskNo',data.taskNo,'taskDetailForm','taskDetailFormTarget')
			off();
			projectAlert(sessionId, projectTitle, targetTitle, crud, projectNumber);
		}, // success
		error : function(xhr, status) {
			alert("fail");
		},
		cache:false,
		contentType:false,
		processData:false
	});
}

function modifyIssueDetail() {
	var issueVO = $('#modifyIssueForm')[0];
	var formData = new FormData(issueVO);
	formData.append("projNo",projNo);

	//파일 개수 체크
	var uploadFileList = document.querySelectorAll('.k-upload-files li');
	var remainFileList = document.querySelectorAll('.task-files li');
	var multiFileList = document.querySelectorAll('.k-file-name-size-wrapper');
	var refileLen = remainFileList.length;
	var upFileLen = uploadFileList.length;
	var muFileLen = multiFileList.length;

	if((refileLen+upFileLen+muFileLen)>6){
		alert('파일은 총 5개까지만 업로드 할 수 있습니다.');
		return;
	}

	var projectNumber = projNo;
	var whereDist = "이슈";
	var targetTitle = document.getElementById('title').value;
	var crud = "수정";

	$.ajax({
		url : "/app/issue/modifyIssueByIssueNo",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("수정에 성공했습니다.");
			console.log(data.issueNo);
			getIssueTemplate('/app/issue/getIssueByIssueNo',data.issueNo,'issueDetailForm','issueDetailFormTarget')
			off();
			projectAlert(sessionId, projectTitle, targetTitle, crud, projectNumber);
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

function modifyMileDetail() {
	var mileNo = $('#modifyMileForm input[name="mileNo"]').val();
	var userId = $('#modifyMileForm select[name="userId"]').val();
	var status = $('#modifyMileForm select[name="status"]').val();
	var title = $('#modifyMileForm input[name="title"]').val();
	var content = $('#modifyMileForm textarea[name="content"]').val();

	var issueNoList = $('#modifyMileForm select[name="issueList"]').val();

	console.log(issueNoList);

	var mileVO = {
		"mileNo":mileNo,
		"userId":userId,
		"status":status,
		"title":title,
		"content":content,
		"projNo":projNo,
		"issueNoList":issueNoList
	}
	console.log(mileVO);

	var projectNumber = projNo;
	var whereDist = "마일스톤";
	var targetTitle = document.getElementById('title').value;
	var crud = "수정";

	$.ajax({
		url : "/app/milestone/modifyMilestoneByMileNo",
		type : 'POST',
		data : JSON.stringify(mileVO),
		contentType:'application/json;charset=UTF-8',
		success : function(data) {
			alert("수정에 성공했습니다.");
			document.getElementById('issue-tab').click();
			off();
			projectAlert(sessionId, projectTitle, targetTitle, crud, projectNumber);
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		}
	});
}

function modifyProjectNotice() {

	checkValidate("modifyProjectNotice", "notice");
	var noticeVal = $('#modifyProjectNotice [name="notice"]').val();

	if(noticeVal == ""){
		$('#modifyProjectNotice [name="notice"]').parents('div.custom-validate').attr('class','item form-group custom-validate error');
		return

	} else {
		$('#modifyProjectNotice [name="notice"]').parents('div.custom-validate').attr('class','item form-group custom-validate');
	}

	var projectVO = $('#modifyProjectNotice').serialize();
	projectVO += '&projNo='+projNo;

	var projectNumber = projNo;
	var whereDist = "프로젝트 공지";
	var targetTitle = document.getElementById('notice').value;
	var crud = "수정";

	$.ajax({
		url : "/app/project/modifyProjectNotice",
		type : 'POST',
		datatype : 'text',
		data : projectVO,
		success : function(data) {
			alert("요청이 완료되었습니다.");
			getTemplate('/app/project/getProjectByProjNo','projectDetailNotify','projectDetailNotifyTarget');
			off();
			projectAlert(sessionId, projectTitle, targetTitle, crud, projectNumber);
		}, // success
		error : function(xhr, status) {
			alert("요청에 실패했습니다.");
		}
	});

};



function registProject() {
	var title = $('#registProjectForm input[name="title"]').val();
	var privacy = $('#registProjectForm select[name="privacy"]').val();
	var startdate = $('#registProjectForm input[name="startdate"]').val();
	var enddate = $('#registProjectForm input[name="enddate"]').val();
	var intro = $('#registProjectForm textarea[name="intro"]').val();
	var tagNames = $('#registProjectForm input[name="projectTag"]').val();

	var projectVO = {"title":title,"privacy":privacy,"startdate":startdate,"enddate":enddate,"intro":intro,"tagNames":tagNames};

	console.log(projectVO);

	 $.ajax({
         url : "/app/project/registProject",
         type : 'POST',
         data : projectVO,
         success : function(data) {
        	 alert("등록에 성공했습니다.");
        	 console.log(data.projNo);
        	 location.href="project/main?projNo="+data.projNo;
        	 off();
         }, // success
         error : function(xhr, status) {
        	 alert("등록에 실패하였습니다.");
         }
     });

}

function registTask() {
	var taskVO = $('#registTaskForm')[0];
	var formData = new FormData(taskVO);
	formData.append("projNo",projNo);

	console.log(taskVO);
	console.log(formData);

	//파일 개수 체크
	var uploadFileList = document.querySelectorAll('.k-upload-files li');
	var remainFileList = document.querySelectorAll('.task-files li');
	var multiFileList = document.querySelectorAll('.k-file-name-size-wrapper');
	var refileLen = remainFileList.length;
	var upFileLen = uploadFileList.length;
	var muFileLen = multiFileList.length;

	if((refileLen+upFileLen+muFileLen)>6){
		alert('파일은 총 5개까지만 업로드 할 수 있습니다.');
		return;
	}

	$.ajax({
		url : "/app/task/registTask",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록에 성공했습니다.");
			if(getCookie('projTab') == 'task-tab'){
				setTimeout(readTask, 100);
			} else if(getCookie('projTab') == 'gantt-tab'){
				setTimeout(ganttRefresh, 300);
			}
			off();
		}, // success
		error : function(xhr, status) {
			alert("등록에 실패하였습니다.");
		},
		cache:false,
		contentType:false,
		processData:false
	});

}

function registTaskOnGantt() {
	var taskVO = $('#registTaskForm')[0];
	var formData = new FormData(taskVO);
	formData.append("projNo",projNo);

	$.ajax({
		url : "/app/task/registTask",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록에 성공했습니다.");

			off();
		}, // success
		error : function(xhr, status) {
			alert("등록에 실패하였습니다.");
		},
		cache:false,
		contentType:false,
		processData:false
	});

}

function registIssue() {
	var issueVO = $('#registIssueForm')[0];
	var formData = new FormData(issueVO);
	formData.append("projNo",projNo);

	//파일 개수 체크
	var uploadFileList = document.querySelectorAll('.k-upload-files li');
	var remainFileList = document.querySelectorAll('.task-files li');
	var multiFileList = document.querySelectorAll('.k-file-name-size-wrapper');
	var refileLen = remainFileList.length;
	var upFileLen = uploadFileList.length;
	var muFileLen = multiFileList.length;

	if((refileLen+upFileLen+muFileLen)>6){
		alert('파일은 총 5개까지만 업로드 할 수 있습니다.');
		return;
	}

	$.ajax({
		url : "/app/issue/registIssue",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록에 성공했습니다.");
			setTimeout(readIssue, 100);
			setTimeout(readMile, 100);
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

function registMileDetail() {
	var mileNo = $('#registMileForm input[name="mileNo"]').val();
	var userId = $('#registMileForm select[name="userId"]').val();
	var status = $('#registMileForm select[name="status"]').val();
	var title = $('#registMileForm input[name="title"]').val();
	var content = $('#registMileForm textarea[name="content"]').val();

	var issueNoList = $('#registMileForm select[name="issueList"]').val();

	console.log(issueNoList);

	var mileVO = {
		"mileNo":mileNo,
		"userId":userId,
		"status":status,
		"title":title,
		"content":content,
		"projNo":projNo,
		"issueNoList":issueNoList
	}
	console.log(mileVO);
	$.ajax({
		url : "/app/milestone/registMilestoneDetail",
		type : 'POST',
		data : JSON.stringify(mileVO),
		contentType:'application/json;charset=UTF-8',
		success : function(data) {
			alert("등록에 성공했습니다.");
			document.getElementById('issue-tab').click();
			off();
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		}
	});
}

function modifyUserRoleSubmit(){

	var flag = false;
	var userId = $('#modifyUserRole select[name="userId"]').val();
	var role = $('#modifyUserRole select[name="role"]').val();

	var projectVO = {"projNo":projNo, "userId":userId,"role":role};

	if(role == "A303"){
		flag = confirm('팀장을 양도할 경우 현 프로젝트에서 팀장으로써의 모든 권한을 잃게 됩니다! \n정말로 변경하시겠습니까?');
	} else {
		flag = confirm('정말로 권한을 변경하시겠습니까?')
	}

	if(flag){
		$.ajax({
			url      : "/app/project/modifyUserRole",
			type     : 'POST',
			data     : projectVO,
			success  : function(data){
				alert("수정이 완료되었습니다.");
				off();
				updateUserRole();
				readProjDetail();
			},
			error : function(xhr, status) {
				alert("시스템 오류입니다. 관리자에게 문의하세요.");
			}
		});
	} else {
		return;
	}

}

function removeUserRoleSubmit(){

	var flag = false;

	var userId = $('#removeUserProject select[name="userId"]').val();
	var nickname = $('#userId option:selected').text();
	var content = $('#removeUserProject textarea[name="content"]').val();
	var validate = $('#removeUserProject input[name="delete_validate"]').val();
	var correct = "프로젝트 구성원 제명";

	console.log(userId +"/"+ validate +"/"+ nickname +"/"+ content);

	if(content.length < 10){
		alert('제명 사유는 10자 이상 작성해야합니다.');
		return;
	}

	if(validate != "프로젝트 구성원 제명"){
		alert('확인 문구를 바르게 입력해주세요.');
		return;
	}

	var projectVO = {"userId":userId};

	flag = confirm("정말로 " + nickname + " 님을 프로젝트에서 제명하시겠습니까?");

	if(flag){

		$.ajax({
			url      : "/app/project/removeProjectUserRelation",
			type     : 'POST',
			data     : projectVO,
			success  : function(data){
				alert("제명이 완료되었습니다.");
				off();
				updateUserRole();
				readProjDetail();
			},
			error : function(xhr, status) {
				alert("시스템 오류입니다. 관리자에게 문의하세요.");
			}
		});
	} else {
		return;
	}
}


function quitUserRoleSubmit(){

	var flag = false;
	var userId = $('#quitUserProject select[name="userId"]').val();
	var content = $('#quitUserProject textarea[name="content"]').val();
	var validate = $('#quitUserProject input[name="delete_validate"]').val();

	console.log(userId +"/"+ validate +"/"+ content);

	if(content.length < 10){
		alert('탈퇴 사유는 10자 이상 작성해야합니다.');
		return;
	}

	if(validate != "해당 프로젝트 탈퇴"){
		alert('확인 문구를 바르게 입력해주세요.');
		return;
	}

	var projectVO = {"userId":userId,"projNo":projNo};

	console.log("---------------->"+projectVO);
	flag = confirm("정말로 프로젝트에서 탈퇴하시겠습니까?");

	if(flag){

		$.ajax({
			url      : "/app/project/quitProjectUserRelation",
			type     : 'POST',
			data     : projectVO,
			success  : function(data){
				alert("탈퇴가 완료되었습니다.");
				off();
				location.href="app/project-list";
			},
			error : function(xhr, status) {
				alert("시스템 오류입니다. 관리자에게 문의하세요.");
			}
		});
	} else {
		return;
	}
}

function inviteMemberSubmit(){

	var flag = false;
	var projTitle = $('#inviteMemberProjectForm input[name="title"]').val();
	var role = $('#inviteMemberProjectForm select[name="role"]').val();
	var roleName = $('#inviteMemberProjectForm select[name="role"] option:selected').text();
	var beforeContent = $('#inviteMemberProjectForm textarea[name="content"]').val();
	var userTo = $('#inviteMemberProjectForm input[name="userTo"]').val();
	var userNicknameFrom = $('#inviteMemberProjectForm input[name="userNicknameFrom"]').val();
	var userIdFrom = $('#inviteMemberProjectForm input[name="userIdFrom"]').val();
//	var targetTitle = $('#inviteMemberProjectForm input[name="targetTitle"]').val();

	var content = inviteMailContentFactory(projNo, projTitle, role, roleName, beforeContent, userTo, userIdFrom, userNicknameFrom);

	regData = {"content":content,"userTo":userTo};

	flag = confirm("작성한 내용으로 초대를 보내시겠습니까?");

	if(flag){

		$.ajax({
			url      : "/app/project/registInviteMail",
			type     : 'POST',
			data     : regData,
			success  : function(data){
				alert("초대가 발송되었습니다.");
				off();
				projectAlert(userNicknameFrom, "프로젝트", projTitle, "초대", projNo, userTo);
			},
			error : function(xhr, status) {
				alert("시스템 오류입니다. 관리자에게 문의하세요.");
			}
		});
	} else {
		return;
	}
}

/* ------------------------------------------- 필수 함수들 -------------------------------------------*/

function on() {
	$("#popoverlay").fadeIn(300);
}

function off() {
	$("#popoverlay").fadeOut(300);
}

var prevTarget = [];

function prev() {
	var currPage = prevTarget.pop();
	var prevPage = prevTarget.pop();
	console.log(prevPage);
	getOverlayTemplate(prevPage);
}

function checkValidate(FormId, targetName) {

		var validateValue = $('#'+FormId+' [name="'+targetName+'"]').val();

		console.log(validateValue);

		if(validateValue == ""){

			$('#'+FormId+' [name="'+targetName+'"]').parents('div.custom-validate').attr('class','item form-group custom-validate error');

			return

		} else {
			$('#'+FormId+' [name="'+targetName+'"]').parents('div.custom-validate').attr('class','item form-group custom-validate');
		}

}

$(document).ready(function(){

})


function getCookie(name) {
	  let matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
	}

function inviteMailContentFactory(projNo, projTitle, role, roleName, content, userTo, userIdFrom, userNicknameFrom){
	var afterContent =
		`
		<table style="width: 100%">
      <tbody>
        <tr>
          <td style="background-color: #0979cb;margin-left:10px;">
            <h2 class="" style="color: white">프로젝트 초대 메일</h2>
          </td>
        </tr>
        <tr>
          <td height="20px"></td>
        </tr>
        <tr>
          <td>
            <div style="border: 1px solid black;padding:10px;width:50%;">
              <span>유저명 : ${userNicknameFrom}</span><br>
              <span>프로젝트명 : ${projTitle}</span><br>
              <span>권한 : ${roleName}</span><br>
              <span>메세지 :${content}</span>
            </div>
          </td>
        </tr>
        <tr>
          <td height="5px"></td>
        </tr>
        <tr>
          <td>&nbsp;해당 유저가 당신을 프로젝트로 초대했습니다.</td>
        </tr>
        <tr>
          <td height="5px"></td>
        </tr>
        <tr>
          <td>&nbsp;수락하시려면 '수락' 버튼을, 거절하시려면 '거절' 버튼을 클릭해주세요.</td>
        </tr>
        <tr>
          <td height="20px"></td>
        </tr>
        <tr>
          <td>
            <div class="align-center">
              <button type="button" class="btn btn-success" onclick="inviteAccept('${userTo}','${userIdFrom}','${projNo}','${role}', '${userNicknameFrom}', '${projTitle}');">수락</button>
              <button class="btn btn-primary" type="button" onclick="inviteDenied('${userTo}','${userIdFrom}');">거절</button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
		`;

	return afterContent;
}
