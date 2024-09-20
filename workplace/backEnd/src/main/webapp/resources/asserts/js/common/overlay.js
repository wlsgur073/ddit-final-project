

function on() {
	$("#popoverlay").fadeIn(300);
}

function off() {
	$("#popoverlay").fadeOut(300);
}

var prevTarget = [];

function getOverlayAnyWhereTaskRegistTemplate(userId) {



	$.ajax({
		type : 'GET',
		url : "/app/project/getProjectListByParamUserId",
		data : {"userId":userId},
		success : function(data) {
			on();
			var template = document.querySelector("#anyWheretaskRegistFormTemplate").innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			appe.innerHTML = bindTemplate(data);
			$("#fadeInContent").fadeIn(300);
			summernote_go($('#anyWhereTaskOverlayContent'));
			uploadForm("anyWhereProjectTaskUpload");
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

function getOverlayAnyWhereIssueRegistTemplate(userId) {


	$.ajax({
		type : 'GET',
		url : "/app/project/getProjectListByParamUserId",
		data : {"userId":userId},
		success : function(data) {
			on();
			var template = document.querySelector("#anyWhereIssueRegistFormTemplate").innerText;
			var bindTemplate = Handlebars.compile(template);
			var appe = document.querySelector('#popoverlay');
			appe.innerHTML = bindTemplate(data);
			$("#fadeInContent").fadeIn(300);
			summernote_go($('#anyWhereIssueOverlayContent'));
			uploadForm("anyWhereProjectIssueUpload");
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}


function getOverlayAnyWhereMailRegistTemplate(userId) {
	anyWhereMailRegistOverlayMemory(userId);

	on();

	var template = document.querySelector("#anyWhereMailRegistFormTemplate").innerText;
	var bindTemplate = Handlebars.compile(template);
	var appe = document.querySelector('#popoverlay');
	appe.innerHTML = bindTemplate();
	$("#fadeInContent").fadeIn(300);
	summernote_go($('#anyWhereMailOverlayContent'));
}




function getOverlayAnyWhereMailRegistTemplate(userId) {
	anyWhereMailRegistOverlayMemory(userId);

	on();

	var template = document.querySelector("#anyWhereMailRegistFormTemplate").innerText;
	var bindTemplate = Handlebars.compile(template);
	var appe = document.querySelector('#popoverlay');
	appe.innerHTML = bindTemplate();
	$("#fadeInContent").fadeIn(300);
	summernote_go($('#anyWhereMailOverlayContent'));
}



//스푼 오버레이 실행 함수
function getOverlayAnyWhereSpoonTemplate(url) {
	console.log("url" + url);

	if (getCookie("projTab") == "task-tab") {
		on();
		$.ajax({
			type : 'GET',
			url : url,
			data : {"projNo":projNo},
			success : function(data) {
				console.log("Handlebars success!!");
				console.log("data =>   " + JSON.stringify(data));
				var template = document.querySelector("#anyWhereSpoonTaskTemplate").innerText;
				var bindTemplate = Handlebars.compile(template);
				var appe = document.querySelector('#popoverlay');
				appe.innerHTML = bindTemplate(data);
				$("#fadeInContent").fadeIn(300);
				$("#selectTaskTitle").kendoMultiSelect({
					autoClose: false
				});

			},
			error : function(error) {
				console.log("Handlebars error!!");
			},
		});

	} else if (getCookie("projTab") == "issue-tab"){
		on();
		$.ajax({
			type : 'GET',
			url : url,
			data : {"projNo":projNo},
			success : function(data) {
				console.log("Handlebars success!!");
				console.log("data =>   " + JSON.stringify(data));
				var template = document.querySelector("#anyWhereSpoonissueTemplate").innerText;
				var bindTemplate = Handlebars.compile(template);
				var appe = document.querySelector('#popoverlay');
				appe.innerHTML = bindTemplate(data);
				$("#fadeInContent").fadeIn(300);
				$("#selectTaskTitle").kendoMultiSelect({
					autoClose: false
				});

			},
			error : function(error) {
				console.log("Handlebars error!!");
			},
		});
	}

}
function SpoonTask_go() {
	//alert("스푼 보내기");
	/*let projTitle = document.getElementById('taskProjTitle').value;
	console.log("프로젝트 제목 ====> " + projTitle);
	*/
	/*let projNum = document.getElementById('taskOfProjNo').value;
	console.log("프로젝트 번호 ====> " + projNum);
	*/
	/*let selectedTaskTitle = $('#selectTaskTitle').text();
	console.log("선택 업무 제목 ====>" + selectedTaskTitle);
	*/
	/*let selectedCprojTitle = document.getElementById('optTaskCprojNo').value;
	console.log("선택 콜라보 제목 ====> " + selectedCprojTitle);
	*/

	let taskNoList = $('#SpoonOverlayForm select[name="taskTitle"]').val();
	console.log("선택 업무 번호 ====> " + taskNoList);

	let cprojNo = $('#optTaskCprojNo option:selected').attr('idx');
	console.log("선택 콜라보 번호 ====>" + cprojNo);

	var spoonVO = {"taskNoList" : taskNoList, "cprojNo" : cprojNo, "projNo" : projNo};
	console.log(spoonVO);

	$.ajax({
		type : 'POST',
		url : "/app/spoon/setTaskToCollabo",
		contentType:'application/json;charset=UTF-8',
		data : JSON.stringify(spoonVO),
		success : function(data) {
			alert("스푼 하기를 완료하였습니다.");
			off();
		},
		error : function(error) {
			console.log("스푼 하기를 실패하였습니다.");
		},
	});
}





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
			console.log(html);
			appe.innerHTML = html;
			$("#fadeInContent").fadeIn(300);
			summernote_go($('.projSummnote'));
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});

}

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

function modifyProjectDetail() {
	var projectVO = $('#modifyProjectForm').serialize();
	projectVO += '&projNo='+projNo;
	console.log(projectVO);

	$.ajax({
		url : "/app/project/modifyProjectDetail",
		type : 'POST',
		datatype : 'text',
		data : projectVO,
		success : function(data) {
			alert("수정에 성공했습니다.");
			getTemplate('/app/project/getProjectByProjNo','projectDetailIntro','projectDetailIntroTarget');
			off();
		}, // success
		error : function(xhr, status) {
			alert("fail");
			alert(xhr + " : " + status);
		}
	});
}

function modifyProjectNotice() {

	console.log("hello");

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

	$.ajax({
		url : "/app/project/modifyProjectNotice",
		type : 'POST',
		datatype : 'text',
		data : projectVO,
		success : function(data) {
			alert("요청이 완료되었습니다.");
			getTemplate('/app/project/getProjectByProjNo','projectDetailNotify','projectDetailNotifyTarget');
			off();
		}, // success
		error : function(xhr, status) {
			alert("요청에 실패했습니다.");
		}
	});

};



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


function anyWhereTaskRegistSubmit(){

	var taskVO = $('#anyWhereRegistTaskForm')[0];
	var formData = new FormData(taskVO);

	var fBprojNo = $('#anyWhereRegistTaskForm select[name="projNo"]').val();

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
			off();
			var warpFlag = confirm("등록에 성공했습니다. 해당 프로젝트의 업무페이지로 이동하시겠습니까?");
			if(warpFlag){
				if(window.location.pathname != "/app/project/main?projNo="+fBprojNo){
					document.cookie = "projTab=task-tab";
					document.cookie = "projNo="+fBprojNo;
					location.href = "/app/project/main?projNo="+fBprojNo;
				} else {
					document.getElementById('task-tab').click();
				}
			} else {
				return;
			}
		}, // success
		error : function(xhr, status) {
			alert("등록에 실패하였습니다.");
		},
		cache:false,
		contentType:false,
		processData:false
	});
}

function anyWhereIssueRegistSubmit() {
	var issueVO = $('#anyWhereRegistIssueForm')[0];
	var formData = new FormData(issueVO);

	var fBprojNo = $('#anyWhereRegistIssueForm select[name="projNo"]').val();

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
			off();
			var warpFlag = confirm("등록에 성공했습니다. 해당 프로젝트의 이슈페이지로 이동하시겠습니까?");
			if(warpFlag){
				if(window.location.pathname != "/app/project/main?projNo="+fBprojNo){
					document.cookie = "projTab=issue-tab";
					document.cookie = "projNo="+fBprojNo;
					location.href = "/app/project/main?projNo="+fBprojNo;
				} else {
					document.getElementById('issue-tab').click();
				}
			} else {
				return;
			}
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


function anyWhereMailRegistOverlayMemory(uid){
	var anyWhereMailMemoryCapacity = null;

	var userId = uid;

	$.ajax({
		url: "/app/myWork/getMemoryCapacity",
		type: "get",
		async: false,
		data: {
			"userId" : userId
		},
		success: function(data){
			anyWhereMailMemoryCapacity = data;
		},
		error: function(error){
			alert(error.status);
		}
	});

	$(document).on('change', '.anyWhereMailOverlayAttachFile', function(event){
		if(this.files[0].size > anyWhereMailMemoryCapacity * 1024 * 1024){
			alert("파일 용량은 " + anyWhereMailMemoryCapacity + "MB 이하만 가능합니다.");
			this.value="";
			$(this).click();
			return;
		}
	});
}


var dataNum = 0;

function addOverlayAnyWhereFile_go(){
	if($('input[class="anyWhereMailOverlayAttachFile"]').length >= 5){
		alert("파일 추가는 5개 까지만 가능합니다.");
		return;
	}

	var div = $('<div>').addClass("inputRow").addClass("mb-1").attr("data-no", dataNum);
	var input = $('<input>').attr({"type":"file", "name":"attachFile", "class":"anyWhereMailOverlayAttachFile"}).css("display", "inline");

	div.append(input).append("<button type='button' class='badge bg-red' onclick='removeOverlayAnyWhereAttachFile_go(" + dataNum + ")' style='border:0;outline:0;'>X</button>");
	$('.overlayFileAnyWhereInput').append(div);

	dataNum++;
}

function removeOverlayAnyWhereAttachFile_go(dataNum){
	$('div[data-no="' + dataNum + '"]').remove();
}

function mailOverlayAnyWhereRegist_go(dist, uFrom){
	var userTo = $("#anyWhereMailOverlayUserTo").val();
	var userFrom = uFrom;

	if(dist == "temp"){
		if(userTo == userFrom){
			$("#anyWhereOverlayDist").val("tempMine");
		}else{
			$("#anyWhereOverlayDist").val("temp");
		}
	}else if(dist == "send"){
		if(userTo == userFrom){
			$("#anyWhereOverlayDist").val("mine");
		}else{
			$("#anyWhereOverlayDist").val("send");
		}
	}

	var files = $('input[class="anyWhereMailOverlayAttachFile"]');
	for(var file of files){
		console.log(file.name + " : " + file.value);
		if(file.value == ""){
			alert("파일을 선택해주세요.");
			file.focus();
			file.click();
			return;
		}
	}

	if($("input[id='anyWhereMailOverlayTitle']").val() == ""){
		alert("제목을 입력해주세요.");
		$("input[id='anyWhereMailOverlayTitle']").focus();
		return;
	}
	if($("input[id='anyWhereMailOverlayUserTo']").val() == ""){
		alert("메일을 입력해주세요.");
		$("input[id='anyWhereMailOverlayUserTo']").focus();
		return;
	}
	if($("textarea[id='anyWhereMailOverlayContent']").val() == ""){
		alert("내용을 입력해주세요.");
		$("textarea[id='anyWhereMailOverlayContent']").focus();
		return;
	}
	var result2 = null;
	$.ajax({
		type: "get",
		url : "/app/myWork/userCheck",
		data: { "userTo"  : userTo },
		async: false,
		success: function(result){
			if(result == "no"){
				alert("존재하지 않는 회원입니다.");
				result2 = result;
			}
		},
		error: function(error){
			/* alert(error.status); */
		}
	});
	
	if(dist == "send"){
		var receiverId = $("#anyWhereMailOverlayUserTo").val();
		var nickname = userFrom;
		mailAlarm(nickname, receiverId);
	}
	
	if(result2 != "no"){
		document.overlayAnyWhereMailRegistForm.submit();		
	}
}

function mailAlarm(nickname, receiverId){
	var nickname   = nickname;
	var where      = "메일";
	var target     = "메일";
	var whatTodo   = "전송";
	var projNo     = "0";
	var receiverId = receiverId;

	let mailRegistSocketData = {
		"nickname"   : nickname,
		"where"      : where,
		"target"     : target,
		"whatToDo"   : whatTodo,
		"projNo"     : projNo,
		"receiverId" : receiverId
	}

	if(socket){
		let mailRegistSocketMsg = mailRegistSocketData.nickname
						  + "," + mailRegistSocketData.where
						  + "," + mailRegistSocketData.target
						  + "," + mailRegistSocketData.whatToDo
						  + "," + mailRegistSocketData.projNo
						  + "," + mailRegistSocketData.receiverId;
		socket.send(mailRegistSocketMsg);
	}
}

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


function CollaboAlarm(nickname, receiverId){
	var nickname   = nickname;
	var where      = "메일";
	var target     = "메일";
	var whatTodo   = "전송";
	var projNo     = "0";
	var receiverId = receiverId;

	let mailRegistSocketData = {
		"nickname"   : nickname,
		"where"      : where,
		"target"     : target,
		"whatToDo"   : whatTodo,
		"projNo"     : projNo,
		"receiverId" : receiverId
	}

	if(socket){
		let mailRegistSocketMsg = mailRegistSocketData.nickname
						  + "," + mailRegistSocketData.where
						  + "," + mailRegistSocketData.target
						  + "," + mailRegistSocketData.whatToDo
						  + "," + mailRegistSocketData.projNo
						  + "," + mailRegistSocketData.receiverId;
		socket.send(mailRegistSocketMsg);
	}
}

