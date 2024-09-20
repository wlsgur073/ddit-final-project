

function myOverlayOn(dist) {
	$(dist).fadeIn(300);
}

function myOverlayOff(dist) {
	$(dist).fadeOut(300);
}

var prevTarget = [];

function getOverlayIssueModifyTemplate(issueNo, projNo) {
	$("#myMileOverlay").empty();
	myOverlayOn("#myMileOverlay");
	
	var issueVO = {
		"projNo" : projNo,
		"issueNo": issueNo
	}
	
	$.ajax({
		type    : "GET",
		url     : "/app/issue/getIssueByIssueNo",
		dataType: "JSON",
		data    : issueVO,
		success : function(data){
			console.log(data);
			console.log("MyIssue ModifyForm - Handlebars success!!");
			myIssueModifyForm(data);
			summernote_go($('.myIssueModifySummernote'));
			$("#fadeInMyIssueContent").fadeIn(300);
			uploadForm('myIssueModifyUpload');
		},
		error   : function(error){
			console.log("MyIssue ModifyForm - Handlebars error!!");
		}
	});
}


function getOverlayMyIssueRegistTemplate(userId) {
	$("#myMileOverlay").empty();
	myOverlayOn("#myMileOverlay");
	
	$.ajax({
		url : "/app/myWork/getProjectNameForSort",
		type : 'POST',
		datatype : 'text',
		data : {"userId" : userId},
		success : function(data) {
			myIssueRegistForm(data);
			uploadForm('myIssueRegistUpload');
			summernote_go($('.myIssueRegistSummernote'));
		},
		error : function(error) {
			/*alert(error.status);*/
		}
	});
	
}

function getOverlayMyMileRegistTemplate(userId) {
	$("#myMileOverlay").empty();
	myOverlayOn("#myMileOverlay");
	
	$.ajax({
		type    : "GET",
		url     : "/app/issue/getIssueSortByUserId",
		data    : {"userId" : userId},
		dataType: "JSON",
		success : function(data){
			myMileRegistForm(data);
			summernote_go($('.myMileRegistSummernote'));
			changeProjectAndIssue();
		},
		error   : function(error){
			/*alert("짜증");*/
			console.log("MyMile RegistForm - Handlebars error!!");
		}
	});
}

function getOverlayMyMileModifyTemplate(mileNo) {
	$("#myMileOverlay").empty();
	myOverlayOn("#myMileOverlay");
	
	var mileVO = { "mileNo": mileNo };
	
	$.ajax({
		type    : "GET",
		url     : "/app/milestone/getMilestoneByMileNo",
		dataType: "JSON",
		data    : mileVO,
		success : function(data){
			console.log(data);
			myMileModifyForm(data);
			summernote_go($('.myMileModifySummernote'));
			var issueTitle = [];
			var issueNo = [];
			
			if(data.mileVO.issueList != null){
				for(var i = 0; i < data.mileVO.issueList.length; i++){
					issueTitle.push(data.mileVO.issueList[i].title);
					issueNo.push(data.mileVO.issueList[i].issueNo);
					console.log(issueTitle);
				}
				$("#myMileModifyIssueTag").kendoMultiSelect({
					autoClose: false,
					value:issueNo
				});
			} else {
				$("#myMileModifyIssueTag").kendoMultiSelect({
					autoClose: false
				});
			}
		},
		error   : function(error){
			console.log("MyMile ModifyForm - Handlebars error!!");
		}
	});
}

function getOverlayMailRegistTemplate(userFrom) {
	myOverlayOn("#myPopOverlay");
	$(".sendOverlayUserTo").val(userFrom);
	$("#fadeInContent").fadeIn(300);
	
	summernote_go($('.overlaySendContent'));
}

function getOverlayTaskRegistTemplate(userFrom) {
	myOverlayOn("#myTaskOverlay");
	$("#fadeInTaskContent").fadeIn(300);
	uploadForm('myTaskRegistUpload');
}

function getOverlayTaskModifyTemplate(taskNo, projNo) {
	myOverlayOn("#myTaskModifyOverlay");
	var taskVO = {
		"taskNo" : taskNo,
		"projNo" : projNo
	};
	
	$.ajax({
		type    : "GET",
		url     : "task/getTaskDetailByTaskNo",
		dataType: "JSON",
		data    : taskVO,
		success : function(data){
			console.log("MyTask ModifyForm - Handlebars success!!");
			myTaskModifyForm(data);
			summernote_go($('.myTaskOverlayContentModify'));
			$("#fadeInTaskModifyContent").fadeIn(300);
			uploadForm('myTaskModifyUpload');
		},
		error   : function(error){
			console.log("MyTask ModifyForm - Handlebars error!!");
		}
	});
}

function changeProjectAndIssue(){
	var projNo = $('#registMyMileForm select[name="projNo"]').val(); 
	var userId = $('#registMyMileForm select[name="userId"]').val(); 
	
	$.ajax({
		type    : "GET",
		url     : "/app/issue/getIssueListByProjNoAndUserId",
		dataType: "JSON",
		data    : {
			"projNo" : projNo,
			"userId" : userId
		},
		success : function(data){
			var issueList = "";
			for(var i = 0; i < data.length; i++){
				issueList += "<option value='" + data[i].issueNo +"'>" + data[i].title + "</option>";
			}
			$("#myMileIssueTag").empty().append(issueList);
			$("#myMileIssueTag").kendoMultiSelect({
				autoClose: false
			});
		},
		error   : function(error){
			console.log(error.status);
		}
	});
	
	$('#registMyMileForm select[name="projNo"]').on("change", function(){
		projNo = $('#registMyMileForm select[name="projNo"]').val();
		
		$.ajax({
			type    : "GET",
			url     : "/app/issue/getIssueListByProjNoAndUserId",
			dataType: "JSON",
			data    : {
				"projNo" : projNo,
				"userId" : userId
			},
			success : function(data){
				var issueList = "";
				for(var i = 0; i < data.length; i++){
					issueList += "<option value='" + data[i].issueNo +"'>" + data[i].title + "</option>\n";
				}
				$("#MileIssueTagFormGroup").empty();
				$("#MileIssueTagFormGroup").append('<label class="label-align mt-3">이슈 등록</label><select id="myMileIssueTag" name="myIssueList" multiple="multiple" data-placeholder="추가하실 이슈를 선택해주세요."></select>');
				$("#myMileIssueTag").empty().append(issueList);
				$("#myMileIssueTag").kendoMultiSelect({
					autoClose: false
				});
			},
			error   : function(error){
				console.log(error.status);
			}
		});
	});
}

function modifyMyIssue() {
	var projNo = $("#myIssueModifyProjNo").val();
	var startdate = $("#myIssueModifyStartdate").val();
	var enddate = $("#myIssueModifyEnddate").val();
	var title = $("#myIssueModifyTitle").val();
	var content = $("#myIssueModifyContent").val();
	
	if(!title){
		alert("이슈명을 입력해주세요.");
		return;
	}
	if(!content){
		alert("이슈내용을 입력해주세요.");
		return;
	}
	if(content == "<p><br></p>"){
		alert("이슈내용을 입력해주세요.");
		return;
	}
	if(!startdate){
		alert("시작일을 입력해주세요.");
		return;
	}
	if(!enddate){
		alert("마감일을 입력해주세요.");
		return;
	}
	
	var issueVO = $('#modifyMyIssueForm')[0];
	var formData = new FormData(issueVO);
	console.log(issueVO);
	$.ajax({
		url : "/app/issue/modifyIssueByIssueNo",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("수정을 완료하였습니다.");
			myIssueDetail(data.issueNo, projNo);
			readMyDashboard('${userVO.userId}', '${userVO.nickname}');
			myOverlayOff('#myMileOverlay');
		},
		error : function(status) {
			alert("수정에 실패하였습니다.");
		},
		cache:false,
		contentType:false,
		processData:false
	});
}

function registMyIssue() {
	var projNo = $('#registMyIssueForm select[name="projNo"]').val();
	var projTitle = $("#myIssueRegistProjTitle option:selected").text();
	var userId = $('#registMyIssueForm select[name="userId"]').val();
	var important = $('#registMyIssueForm select[name="important"]').val();
	var status = $('#registMyIssueForm select[name="status"]').val();
	var startdate = $("#myIssueRegistStartdate").val();
	var enddate = $("#myIssueRegistEnddate").val();
	var title = $('#registMyIssueForm input[name="title"]').val();
	var content = $('#registMyIssueForm textarea[name="content"]').val();
	
	if(!title){
		alert("이슈명을 입력해주세요.");
		return;
	}
	if(!content){
		alert("이슈내용을 입력해주세요.");
		return;
	}
	if(content == "<p><br></p>"){
		alert("이슈내용을 입력해주세요.");
		return;
	}
	if(!startdate){
		alert("시작일을 입력해주세요.");
		return;
	}
	if(!enddate){
		alert("마감일을 입력해주세요.");
		return;
	}
	
	var issueVO = $('#registMyIssueForm')[0];
	var formData = new FormData(issueVO);
	formData.append("projTitle", projTitle);

	$.ajax({
		url : "/app/issue/registIssue",
		type : 'POST',
		data : formData,
		success : function(data) {
			alert("등록을 완료하였습니다.");
			readMyDashboard('${userVO.userId}', '${userVO.nickname}');
			myOverlayOff('#myMileOverlay');
			
			location.reload();
		},
		error : function(status) {
			alert("등록에 실패하였습니다.");
		},
		cache:false,
		contentType:false,
		processData:false
	});
}

function registMyMilestone() {
	var userId = $('#registMyMileForm select[name="userId"]').val();
	var projNo = $('#registMyMileForm select[name="projNo"]').val();
	var status = $('#registMyMileForm select[name="status"]').val();
	var title = $('#registMyMileForm input[name="title"]').val();
	var content = $('#registMyMileForm textarea[name="content"]').val();
	var issueNoList = $('#registMyMileForm select[id="myMileIssueTag"]').val();
	
	if(!title){
		alert("제목을 입력해주세요.");
		return;
	}
	if(!content){
		alert("내용을 입력해주세요.");
		return;
	}
	if(content == "<p><br></p>"){
		alert("내용을 입력해주세요.");
		return;
	}
	if(!issueNoList){
		alert("이슈를 선택해주세요.");
		return;
	}
	
	var mileVO = {
		"userId"      : userId,
		"projNo"      : projNo,
		"status"      : status,
		"title"       : title,
		"content"     : content,
		"issueNoList" : issueNoList
	}
	
	$.ajax({
		url : "/app/milestone/registMilestoneDetail",
		type : 'POST',
		data : JSON.stringify(mileVO),
		contentType:'application/json;charset=UTF-8',
		success : function(data) {
			alert("등록을 완료하였습니다.");
			document.getElementById('issue-tab').click();
			myOverlayOff('#myMileOverlay');
		}, // success
		error : function(xhr, status) {
			/*alert("fail");
			alert(xhr + " : " + status);*/
		}
	});
}

function modifyMyMilestone() {
	var mileNo = $('#modifyMyMileForm input[name="mileNo"]').val();
	var userId = $('#modifyMyMileForm select[name="userId"]').val();
	var status = $('#modifyMyMileForm select[name="status"]').val();
	var title = $('#modifyMyMileForm input[name="title"]').val();
	var content = $('#modifyMyMileForm textarea[name="content"]').val();
	var projNo = $('#modifyMyMileForm input[name="projNo"]').val();
	var issueNoList = $('#modifyMyMileForm select[name="issueList"]').val();
	
	if(!title){
		alert("제목을 입력해주세요.");
		return;
	}
	if(!content){
		alert("내용을 입력해주세요.");
		return;
	}
	if(content == "<p><br></p>"){
		alert("내용을 입력해주세요.");
		return;
	}
	if(!issueNoList){
		alert("이슈를 선택해주세요.");
		return;
	}
	
	var mileVO = {
		"mileNo":mileNo,
		"userId":userId,
		"status":status,
		"title":title,
		"content":content,
		"projNo":projNo,
		"issueNoList":issueNoList
	}
	
	$.ajax({
		url        : "/app/milestone/modifyMilestoneByMileNo",
		type       : 'POST',
		data       : JSON.stringify(mileVO),
		contentType:'application/json;charset=UTF-8',
		success    : function(data) {
			alert("수정을 완료하였습니다.");
			document.getElementById('issue-tab').click();
			myOverlayOff('#myMileOverlay');
		},
		error      : function(xhr, status) {
			/*alert("fail");
			alert(xhr + " : " + status);*/
		}
	});
}

function removeMyMilestone(mileNo){
	var removeMyMilestoneConfirm = confirm("삭제하시겠습니까?");
	
	if(removeMyMilestoneConfirm){
		$.ajax({
			url     : "/app/milestone/removeMilestone",
			type    : 'GET',
			data    : {"mileNo" : mileNo},
			success : function(data) {
				if(data == "success"){
					alert("삭제를 완료하였습니다.");
				}
				document.getElementById('issue-tab').click();
			},
			error   : function(xhr, status) {
				/*alert("fail");
				alert(xhr + " : " + status);*/
			}
		});
	}
}
