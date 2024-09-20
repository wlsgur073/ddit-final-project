function receiveMailBox(userId){
	receiveMailBox = function(){};

	var sessionId = userId;

	var myReceiveMailDateSource = new kendo.data.DataSource({
		transport: {
			read: "/app/myWork/getReceiveMailList?userTo=" + sessionId
		},
		pageSize: 7,
		schema: {
			model: {
				fields: {
					mailNo   : { type: "number" },
					title    : { type: "string" },
					userFrom : { type: "string" },
					regDate  : { type: "string" }
				}
			}
		},
		change: function(e) {
			var data = e.sender.data();
			if(data[0]){
				receiveMailDetail(data[0].mailNo);
			}else{
				mailNoDetail("receive");
			}
		}
	});

	var receiveMailList = $("#receiveMailList").kendoGrid({
		dataSource: myReceiveMailDateSource,
		batch: true,
		pageable: {
	        alwaysVisible: false
	    },
	    height: 580,
		toolbar: [
			{ template:
				"<div class='btn-group'>" +
					"<button type='button' class='btn btn-sm btn-dark' style='padding-top:5px;padding-bottom:0px;cursor:default;'>" +
						"<input id='receiveAllCheckButton' type='checkbox' onchange='receiveAllCheck();'>" +
					"</button>" +
					"<button type='button' class='btn btn-sm btn-dark btn-append' onclick='deleteReceiveMailAll();'>삭제</button>" +
				"</div>"
			},
			"search"
		],
		messages: {
			commands: {
				search: "검색"
		    }
		},
		noRecords: {
			template: function(e) {
				return "<h2 class='text-center' style='color:#73879c;'>메일이 존재하지 않습니다.</h2>";
			}
		},
		scrollable: false,
		columns: [
			{ field: "mailNo"  , hidden: true },
			{ field: "title"   , hidden: true },
			{ field: "userFrom", hidden: true },
			{ field: "regDate" , hidden: true },
			{ template: kendo.template($("#receiveMailList-template").html()) }
		]
	});

	$('#receiveMailList').find('thead').hide();
}



//콜라보 메일 관련
//수락 버튼 중복 클릭 방지
var doubleSubmitFlag = false;

function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}



//콜라보 수락 버튼 함수
function acceptCollabo() {

	if(doubleSubmitCheck()){
		alert("이미 수락하셨습니다.");
		return;
	}else{

		let userFromCproj = document.getElementById('cprojReplyUserTo').value; //test29

		//let userFromProjNo = $('.userToProj').attr('idx');  //4 idxNoTo
		let userFromProjNo = document.getElementById('idxNoTo').value;  //4

		let userToCproj = document.getElementById('cprojReplyUserFrom').innerText; //pooh_00
		
		//let userToProjNo = $('.userFromProj').attr('idx'); //3
		let userToProjNo = document.getElementById('idxNoFrom').value; //3

		let title = document.getElementById('CollboMailTitle').value + "제안 수락메일입니다.";

		var collaboVO = {"userFrom" : userFromCproj,
				"userFromProjNo" : userFromProjNo,
				"userTo" : userToCproj,
				"userToProjNo" : userToProjNo,
		}
		let sessionNickname = userFromCproj;
		let targetNickname = userToCproj;
		console.log("========>>11111" + sessionNickname);
		console.log("========>>22222" + targetNickname);
		
		$.ajax({
			url : "/app/collabo/registCollabo",
			type : "POST",
			datatype : 'text',
			data : collaboVO,
			success : function(data) {
				alert("등록에 성공했습니다.");
				if(targetNickname === "test10@asd.com") targetNickname = "이길과장";
				collaboAlert(sessionNickname, "콜라보", "", "수락", "0", targetNickname);
				// off();
			},
			error : function(arg) {
				alert("콜라보 생성 에러" + arg.status + "메세지" + arg.responseText);
			}
		});

		/*let receiveContent = replyMailForm(userFromCproj,userToCproj,userToProjNo,userFromProjNo);
		console.log("receiveContent =>" + receiveContent);

		document.getElementById('collaboContent').value = receiveContent;

		document.getElementById('tags_1').value = userToCproj;

		document.getElementById('CollboMailTitle').value = title;

		console.log(document.getElementById('collaboContent').value);

		document.collaboMailRegist.submit();*/

		//location.href="/app/collabo-list";
	}

}
function replyMailForm(userFromCproj,userToCproj,userToProjNo,userFromProjNo) {

	let receiveContent = "";
	receiveContent +=
		`
		<div class="x_content">
			<div class="item form-group">
				<h4>`+userFromCproj+`님이 콜라보 제안을 수락하셨습니다.</h4>
			</div>

			<div class="col-md-6 col-sm-6 offset-md-3">
				<button class="btn btn-primary" type="button" onclick="CollaboGo()";>바로 가기</button>
			</div>

		</div>
		`;

	$.ajax({
		url : "/app/collabo/getProjectTitleCollabo.do",
		type : "POST",
		success : function(arg) {
			console.log("나의 프로젝트 arg => " + arg)
			let projTitle = "<option value='' disabled selected hidden>프로젝트를 선택해주세요.</option>";

			for (var i = 0; i < arg.length; i++) {
				console.log("arg[i] => " + arg[i].title + arg[i].projNo);
				projTitle += "<option class='projNoIdx' idxNo='"+arg[i].projNo+"' value='"+arg[i].title+"'>"+arg[i].title+"</option>";
			}
			document.getElementById('selectOwnProject').innerHTML= projTitle;
		},
		error : function(arg) {
			alert("리스트 출력 에러임" + arg.status + "메세지" + arg.responseText);
		}
	})

	return receiveContent;

}

function CollaboGo() {
	alert("콜라보 프로젝트 제안을 수락하셨습니다.")
	location.href="/app/collabo-list";
}




//콜라보 제안 메일 거절
function refuseCoproj() {
	alert("콜라보 프로젝트 제안을 거절하셨습니다.");

	let refuseTo = document.getElementById('cprojReplyUserFrom').innerText;
	console.log("refuseTo =>" + refuseTo);

	let refuseFrom = document.getElementById('cprojReplyUserTo').value; //test29
	console.log("refuseFrom =>" + refuseFrom);

	let title = document.getElementById('CollboMailTitle').value + "제안 거절메일입니다.";
	console.log("title =>" + title);

	let text = document.getElementById('sendMessageOther').value;
	console.log("text =>" + text);

	let refuseContent = refuseMailForm(refuseTo,refuseFrom,title,text);
	console.log("contetn = >>>>" + refuseContent);

	document.getElementById('collaboContent').value = refuseContent;
	console.log("content => " + document.getElementById('collaboContent').value)

	document.getElementById('tags_1').value = refuseTo;

	document.getElementById('CollaboUserFrom').value = refuseFrom;

	document.getElementById('CollboMailTitle').value = title;

	document.collaboMailRegist.submit();
}

function collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname){

	socketData3 = {
		nickname : sessionNickname,
		where    : cprojectTitle,
		target   : targetTitle,
		whatToDo : crud,
		projNo   : cprojectNumber,
		receiverId : targetNickname
	};

	if(socket){
		//protocol : 누가(nickname) 어디서(분야) 무엇(target)을 curd(어떻게), 프로젝트 넘버
		//","를 구분자로 분리합니다. 반드시 위처럼 순서대로 작성해주세요.
		let socketMsg = socketData3.nickname
				+ "," + socketData3.where
				+ "," + socketData3.target
				+ "," + socketData3.whatToDo
				+ "," + socketData3.cprojNo
				+ "," + socketData3.receiverId;
		//send()하게 되면 alert에 있는 AlertHandler의 handleTextMessage() 메서드로 파라미터를 전달하게 됩니다.
		socket.send(socketMsg);
	}
}


function refuseMailForm(refuseTo,refuseFrom,title,text) {
	console.log("refuseTo =>" + refuseTo);
	console.log("refuseFrom=>" + refuseFrom);
	console.log("title =>" + title);
	console.log("text =>" + text);


	let refuseContent = "";
	refuseContent +=
		`
		<div class="x_content">
			<div class="item form-group">
				<h5>`+refuseFrom+`님이 콜라보 제안을 거절하셨습니다.</h5>
			</div>

			<div class="item form-group-collabo">
		      <label for="middle-name" class="col-form-label col-md-3 col-sm-3 label-align">거절 사유 :
		      <span>`+ text +`</span>
		      </label>
		    </div>
		</div>
		`;

	return refuseContent;
}


function inviteAccept(userId,userFrom,projNo, role, userNicknameFrom, projTitle){

	var projectVO = {"userId":userId,"projNo":projNo,"role":role};

	$.ajax({
		url : "/app/project/registUserProjectRelation",
		type : "POST",
		data : projectVO,
		success : function(data) {
			alert('수락이 완료되었습니다.');
			projectAlert(userId, "프로젝트", projTitle, "수락", projNo, userNicknameFrom);
			location.href="/app/project/main?projNo="+data;
		},
		error : function(error) {
			alert('이미 프로젝트에 참가해있습니다.');
		}
	})

}

function projectAlert(sessionNickname, projectTitle, targetTitle, crud, projectNumber, targetNickname){

	socketData3 = {
		nickname : sessionNickname,
		where    : projectTitle,
		target   : targetTitle,
		whatToDo : crud,
		projNo   : projectNumber,
		receiverId : targetNickname
	};

	if(socket){
		//protocol : 누가(nickname) 어디서(분야) 무엇(target)을 curd(어떻게), 프로젝트 넘버
		//","를 구분자로 분리합니다. 반드시 위처럼 순서대로 작성해주세요.
		let socketMsg = socketData3.nickname
				+ "," + socketData3.where
				+ "," + socketData3.target
				+ "," + socketData3.whatToDo
				+ "," + socketData3.projNo
				+ "," + socketData3.receiverId;
		//send()하게 되면 alert에 있는 AlertHandler의 handleTextMessage() 메서드로 파라미터를 전달하게 됩니다.
		socket.send(socketMsg);
	}
}
