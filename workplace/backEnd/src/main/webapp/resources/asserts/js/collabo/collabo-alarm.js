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