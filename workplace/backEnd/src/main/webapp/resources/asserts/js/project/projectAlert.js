//////////////////////////////////////////////////////////////////////////////////////////////////////////
//	여기서부터 본인의 js파일에 구현해주세요. (개인이 구현해야될 부분)													//
//																										//
//	senderProjNo에 해당 프로젝트 넘버를 넣어주세요. 같은 구성원인지 비교합니다.											//
//	프로젝트 넘버가 필요없는 알림일 경우, 기본 값으로 0으로 지정합니다. DB의 PROJECT테이블에서 0은 사용하지 않는 상태입니다.			//
//	whatToDo에 "등록", "수정","삭제"라고만 적어주세요. "~~했습니다"라고 적지 마세요. 나중에 붙여줘야합니다.					//
//	아래의 socketData에 들어갈 데이터는 반드시 5개여야합니다. 현재 socketData구조체에서 하나라도 로컬변수가 사라지면 에러납니다.	//
//	자신의 데이터에 맡게 변수명은 바꿔주세요. 그렇지 않으면 충돌이 일어납니다.												//
//////////////////////////////////////////////////////////////////////////////////////////////////////////

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
