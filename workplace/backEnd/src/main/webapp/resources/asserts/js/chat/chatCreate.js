

function createRoom(){
    	
	
	
	
	offChatList();
	
	
	
  	  var title = document.getElementById('title');
  	  var selectProject = document.getElementById('selectProject');
  	  var checkCreateRoom = document.getElementById('checkCreateRoom');
  		
        
  		 if(title.value==""){
  			 checkCreateRoom.innerHTML = "채팅방 제목을 작성해주세요.";
  			
  			return false;
  	  	}
  		 
  		 if(selectProject.value==""){
  			 checkCreateRoom.innerHTML = "프로젝트를 선택해주세요.";
  			
  			return false;
  	  	}

  		 
  	   var arr_Season = document.getElementsByClassName('responseChatUserSeok');
  	   var chatNickNames = document.getElementsByClassName('chatResNickname');
  		
  	   
  	
  	  
  	   
  		if(arr_Season.length==0){
  			 checkCreateRoom.innerHTML = "멤버는 적어도 한 명을 선택해야합니다.";
   			 return false;
  		}
  		
  		
  	      var userId ="";
  	      var isChk = false;
  	     
  	     
  	        
	  	      for(var i=0;i<arr_Season.length;i++){
	  	    	
	  	    		  	
	  	    		  if(i==arr_Season.length-1){
	  	    			  userId += arr_Season[i].innerText;
	  	    			  break;
	  	    		  }
	  	    		  
	  	              console.log("인운"+arr_Season[i].innerText);
	  	              userId += arr_Season[i].innerText+",";
	  	    	  
	  	    	  
	  	    	  
		        }
	  	 
	  	      
	  	  //ajax로 도
	  	      
	  	      
	  	
	  	
	  	      
	    var chatTitle = title.value;
	    var projectNo = selectProject.value;
	  
	    
	    
	    

			$.ajax({
				type : "POST",
				url : "/chat/createRoom.do",
				data : {"title":chatTitle,"ptitle":projectNo,"userId":userId},
				
				success : function(res) {

				
				    //알림테스트
					for(var i=0;i<arr_Season.length;i++){
						console.log("채팅방 성공에 id=>"+arr_Season[i].innerText);
						console.log("채팅방 성공에 title"+chatTitle);
						
					
						
					    //알림
					    let socketData = {
								nickname : res,
								where : "채팅",
								target : chatTitle,
								whatToDo :"초대",
								projNo: "0",
								receiverId : chatNickNames[i].innerText
						};
					    
					    
					      if (socket) {
					          let socketMsg = socketData.nickname 
									          + "," + socketData.where 
									          + "," + socketData.target 
									          + "," + socketData.whatToDo 
									          + "," + socketData.projNo
									          + "," + socketData.receiverId;
					          socket.send(socketMsg);
					        }
				    
				    }
				     
					//석기현test
					 Swal.fire({
						  icon: 'success',
						  title: chatTitle+'방이 생성되었습니다.',
						
						}).then((result) => {
							  
							  if (result.isConfirmed) {
							   
								  location.reload();
								  
							  } 
						})
					
			    /*	setTimeout(function() {
			    		location.reload();
					}, 1500);
			    	*/
					    	
					
					
					
				},
				error : function(err) {
					console.log("에러" + err.status);
				}
			});
        

  	        
  	//document.createForm.submit();

  }


function selectChatRoom(realRoomNumber){
	
	var chatTitle = chatTitle;
	var realRoomNo = realRoomNumber;
	
	
	var popupX = (window.screen.width / 2) - (700 / 2);
	
	var popupY= (window.screen.height / 2) - (800 / 2);
	
	
	var popup = window.open('/chat/chatRoom?roomNo='+realRoomNo, '채팅창', 'width=1200px,height=750px,scrollbars=no , left='+ popupX + ', top='+ popupY);
	
}


function chatListBox(selectProj){
	
	
	 var customerTemplate = '<span class="k-state-default" style="background-image: url(\'/user/getPicture.do?picture=#:data.picture #\')"></span>' +
     '<span class="k-state-default"><h5 class="chatInvateUser">#: data.nickname #</h5><p class="requestChatUserSeok">#: data.userId #</p></span>';	
	 
	 var responseTemplate = '<span class="k-state-default" style="background-image: url(\'/user/getPicture.do?picture=#:data.picture #\')"></span>' +
     '<span class="k-state-default" ><h5 class="chatResNickname">#: data.nickname #</h5><p class="responseChatUserSeok">#: data.userId #</p></span>';
	 
	 $("#optionalSeok").kendoListBox({
		 dataTextField: "nickname",
         dataValueField: "picture",
         template: customerTemplate,
         dataSource: {
             transport: {
            	 
            	 read: function(options) {
                 
                     $.ajax({
                         url: "/chat/getProjectUser.do",
                         dataType: "json", 
                         method: "POST",
                         data: {"selectProj":selectProj},
                       
                         success: function(result) {
                             options.success(result);
                            
                         }
                     });
                 }
	 
	 
            }
         },
         draggable: { placeholder: customPlaceholder },
         dropSources: ["selectedSeok"],
         connectWith: "selectedSeok",
         toolbar: {
             position: "right",
             tools: ["moveUp", "moveDown", "transferTo", "transferFrom", "transferAllTo", "transferAllFrom", "remove"]
         }
     });

     $("#selectedSeok").kendoListBox({
    	 dataTextField: "nickname",
         dataValueField: "picture",
         template: responseTemplate,
         draggable: { placeholder: customPlaceholder },
         dropSources: ["optionalSeok"],
         connectWith: "optionalSeok"
     });

     function customPlaceholder(draggedItem) {
         return draggedItem
                 .clone()
                 .addClass("custom-placeholder")
                 .removeClass("k-ghost");
     }
	
	
	
	
}


window.addEventListener('load', function(){
	


	$.ajax({
	    url: "/chat/getProjectTitle.do",
	    type: "POST",
	    success : function(arg) {
	    	
	    	var projTitle ="<option value=''>==내가속한 프로젝트 리스트==</option>";
	    	
	    	 for (var i = 0; i < arg.length; i++) { 
	    	    
	    	      projTitle += 
	    	    	  "<option value='"+arg[i]+"'>"+arg[i]+"</option>";
	    	    }
	    	 document.getElementById('selectProject').innerHTML=projTitle;
	    }, 
	    error : function(arg){
		alert("통신실패시에만 실행"+arg.status);
	    }
	});
	//프로젝트 리스트 출력 끝	
	
	
	$("#selectProject").change(function(){

		
		var selectProj = $('#selectProject').val();
		$('#ChatUserSeok').show();
		
		
		/* 생성하자
		 * <div class="demo-section k-content wide">
        <select id="optionalSeok"></select>
        <select id="selectedSeok"></select>
        </div>
		*/
		
		var check = $('.requestChatUserSeok').length;
		var ChatUserSeok = document.getElementById("ChatUserSeok"); 
		
		if(check){
		
			 while (ChatUserSeok.hasChildNodes()) {
				 ChatUserSeok.removeChild(ChatUserSeok.firstChild);
			    }
			
			  var demoSection = document.createElement('div');
			  var optionalSeok = document.createElement('select');
			  var selectedSeok = document.createElement('select');
			
			  demoSection.className='demo-section k-content wide';
	          optionalSeok.id = 'optionalSeok';
	          selectedSeok.id = 'selectedSeok';
	          
	          ChatUserSeok.append(demoSection);
	          demoSection.append(optionalSeok);
	          demoSection.append(selectedSeok);
		}
		
		chatListBox(selectProj);
		

	});//셀렉트 했을 때 끝
	
	
	

	
	
	
	
	
	
	
	
	
	
	

});//윈도우 오픈 함수 
