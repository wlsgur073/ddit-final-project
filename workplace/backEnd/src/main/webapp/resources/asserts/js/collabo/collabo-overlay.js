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

/**
 * 세션 권한 리로드
 * @returns
 */
function updateUserRole(){
	$.ajax({

		url : "/app/collabo/getUserRole",
		type : 'POST',
		data : {"cprojNo":cprojNo},
		success : function(data) {
			console.log("Session Role 취득완료 =>" + data);
			sessionRole = data;
		}, // success
		error : function(xhr, status) {
			console.log("Session Role 취득실패");
		}
	});
}

/*function checkValidate(FormId, targetName) {

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
	var endPoint = window.location.search;

	if(getCookie('endPoint')){
		if(getCookie('endPoint') != endPoint){
			delCookie('endPoint');
		}
	}

	document.cookie = "endPoint="+endPoint;

	if(getCookie('cprojTab')){
		var curTab = getCookie('cprojTab');
		curTab = curTabCheck(curTab);
		document.getElementById(curTab).click();
	} else if(!getCookie('cprojTab')) {
		document.getElementById('home-tab').click();
	}

	$('a[role="tab"]').on('click', function() {
		var id = this.id
		document.cookie = "cprojTab="+id;
	})

// 상세 탭일경우 리스트로 돌려줌 (각 상세폼에 추가)
function curTabCheck(curTab){
	if(curTab == 'taskDetail-tab'){
		curTab = 'task-tab';
	} else if (curTab == 'issueDetail-tab'){
		curTab = 'issue-tab';
	}
		return curTab;
}

})


function getCookie(name) {
	  let matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
	}

const delCookie = function delCookie_by_name(name){
    let date = new Date();
    date.setDate(date.getDate() - 100);
    let Cookie = `${name}=;Expires=${date.toUTCString()}`
    document.cookie = Cookie;
}*/

//------------------------------------------------------------------------------------------------------

function getOverlayTemplate(templateId) {

	on();
	var template = document.querySelector("#" + templateId).innerText;
	var bindTemplate = Handlebars.compile(template);
	var appe = document.querySelector('#popoverlay');
	var html = bindTemplate();
	console.log(html);
	appe.innerHTML = html;
	$("#fadeInContent").fadeIn(300);
	prevTarget.push(templateId);
	console.log(prevTarget);

	if (templateId == "projRegistFormTemplate")  {
		summernote_go($('.projSummnote'));
	}

}

//콜라보 프로젝트 수정폼
function getOverlayModifyTemplate(templateId, url) {
	on();
	console.log("templateId" + templateId);
	console.log("url" + url);
	
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"cprojNo" : cprojNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			console.log(document.querySelector("#" + templateId));
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			console.log(html);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.cprojSummernote'));
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

//콜라보 프로젝트 수정폼
function modifyCollaboDetail() {
	var collaboVO = $('#modifyCollaboForm').serialize();
	collaboVO += '&cprojNo='+cprojNo;
	console.log(collaboVO);

	$.ajax({
		url : "/app/collabo/modifyCollaboDetail",
		type : 'POST',
		datatype : 'text',
		data : collaboVO,
		success : function(data) {
			alert("수정에 성공했습니다.");
			getTemplate('/app/collabo/getCollaboByCprojNo','collaboDetailIntro','collaboDetailIntroTarget');
			off();
			location.reload();
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		}
	});

}

function deleteProjectNotice() {
}

//콜라보 프로젝트 공지 수정폼
function modifyCollaboNotice() {
	console.log("hello");

	checkValidate("modifyCollaboNotice", "notice");

	var noticeVal = $('#modifyCollaboNotice [name="notice"]').val();

	if(noticeVal == ""){

		$('#modifyCollaboNotice [name="notice"]').parents('div.custom-validate').attr('class','item form-group custom-validate error');

		return

	} else {
		$('#modifyCollaboNotice [name="notice"]').parents('div.custom-validate').attr('class','item form-group custom-validate');
	}

	var CollaboVO = $('#modifyCollaboNotice').serialize();

	CollaboVO += '&cprojNo='+cprojNo;

	$.ajax({
		url : "/app/collabo/modifyCollaboNotice",
		type : 'POST',
		datatype : 'text',
		data : CollaboVO,
		success : function(data) {
			alert("요청이 완료되었습니다.");
			getTemplate('/app/collabo/getCollaboByCprojNo','collaboDetailNotify','collaboDetailNotifyTarget');
			off();
			location.reload()
		}, // success
		error : function(xhr, status) {
			alert("요청에 실패했습니다.");
		}
	});

};


//콜라보 멤버 초대폼
function inviteCollaboMemberSubmit() {
	alert("보내기");
	
	var flag = false;
	var projTitle = $('#inviteMemberCollaboForm input[name="title"]').val();
	var role = $('#inviteMemberCollaboForm select[name="role"]').val();
	var roleName = $('#inviteMemberCollaboForm select[name="role"] option:selected').text();
	var beforeContent = $('#inviteMemberCollaboForm textarea[name="content"]').val();
	var userTo = $('#inviteMemberCollaboForm input[name="userTo"]').val();
	var userNicknameFrom = $('#inviteMemberCollaboForm input[name="userNicknameFrom"]').val();
	var userIdFrom = $('#inviteMemberCollaboForm input[name="userIdFrom"]').val();
	
	var content = inviteMailContentFactory(cprojNo, projTitle, role, roleName, beforeContent, userTo, userIdFrom, userNicknameFrom);
	
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
				collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
			},
			error : function(xhr, status) {
				alert("시스템 오류입니다. 관리자에게 문의하세요.");
			}
		});
	} else {
		return;
	}
};

//콜라보 멤버초대 메일 폼
function inviteMailContentFactory(cprojNo, projTitle, role, roleName, content, userTo, userIdFrom, userNicknameFrom){
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
              <button type="button" class="btn btn-success" onclick="inviteAccept('${userTo}','${userIdFrom}','${cprojNo}','${role}', '${userNicknameFrom}', '${projTitle}');">수락</button>
              <button class="btn btn-primary" type="button" onclick="inviteDenied('${userTo}','${userIdFrom}');">거절</button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
		`;

	return afterContent;
}

//콜라보 상세페이지 파일 업로드 

function getOverlayCollaboDocumentTemplate(templateId) {

	on();
	var template = document.querySelector("#" + templateId).innerText;
	var bindTemplate = Handlebars.compile(template);
	var appe = document.querySelector('#popoverlay');
	var html = bindTemplate();
	appe.innerHTML = html;
	$("#fadeInContent").fadeIn(300);
	uploadForm('CollaboDocument');
}

//-------------------------------------------------------------------------------------

//콜라보 업무 등록 템플레이트 가져오기
function getOverlayRegistCollaboTaskTemplate(templateId, url) {

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"cprojNo" : cprojNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.cprojSummnote'));
			uploadForm('cprojTaskUpload');
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}
//콜라보 업무 등록 기능
function registTask() {
	var collaboTaskVO = $('#registTaskForm')[0];
	var formData = new FormData(collaboTaskVO);
	formData.append("cprojNo",cprojNo);
	
	console.log(collaboTaskVO);
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
		url : "/app/collabo/registTask",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록에 성공했습니다.");
			if(getCookie('projTab') == 'task-tab'){
				setTimeout(readTask, 100);
			} else if(getCookie('projTab') == 'gantt-tab'){
				setTimeout(ganttRefresh, 300);
			}
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
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



//콜라보 업무 수정 출력 폼
function getOverlayCollaboTaskModifyTemplate(templateId, url, taskNo) {
	
	console.log("templateId  => " + templateId);
	console.log("url  => " + url);
	console.log("taskNo  => " + taskNo);
	
	var collaboTaskVO = {"cprojNo": cprojNo, "taskNo": taskNo};

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : collaboTaskVO,
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.cprojSummnote'));
			uploadForm('cprojTaskUpload',data);
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});
};

//콜라보 업무 수정에서 수정하기 버튼
function modifyCollaboTaskDetail() {
	console.log("수정버튼 클릭히히히히")
	
	var collaboTaskVO = $('#modifyCollaboTaskForm')[0];
	var formData = new FormData(collaboTaskVO);
	formData.append("cprojNo",cprojNo);

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
		url : "/app/collabo/modifyTaskDetailByTaskNo",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("수정에 성공했습니다.");
			console.log("data.taskNo = > " + data.taskNo);
			getTaskTemplate('/app/collabo/getTaskDetailByTaskNo',data.taskNo,'collaboTaskDetailForm','collaboTaskDetailFormTarget');
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
			off();
		}, // success
		error : function(xhr, status) {
			alert("fail");
		},
		cache:false,
		contentType:false,
		processData:false
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
			"cprojNo" : cprojNo
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
			summernote_go($('.cprojSummnote'));
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
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
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}
//콜라보 마일스톤
function getOverlayMileRegistTemplate(templateId, url) {

	on();
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : {
			"cprojNo" : cprojNo
		},
		success : function(data) {
			console.log("Handlebars success!!");
			var template = document.querySelector("#" + templateId).innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			var html = bindTemplate(data);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.cprojSummnote'));
			$("#mileIssueTag").kendoMultiSelect({
				autoClose: false
			});
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);

		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

//콜라보 마일스톤 등록
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
		"cprojNo":cprojNo,
		"issueNoList":issueNoList
	}
	console.log(mileVO);
	$.ajax({
		url : "/app/collabo/registMilestoneDetail",
		type : 'POST',
		data : JSON.stringify(mileVO),
		contentType:'application/json;charset=UTF-8',
		success : function(data) {
			alert("등록에 성공했습니다.");
			document.getElementById('issue-tab').click();
			collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
			off();
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		}
	});
}

//-------------------------------------------------------------------------------------

function registProject() {
	var projectVO = $('#registProjectForm').serialize();
	console.log(projectVO);

	 $.ajax({
         url : "/app/project/registProject",
         type : 'POST',
         datatype : 'text',
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

//-------------------------------------------------------------------------------------




