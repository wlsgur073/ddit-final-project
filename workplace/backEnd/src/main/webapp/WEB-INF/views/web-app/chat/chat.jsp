<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="x_content">
<div id="contentWraps">
    <nav>
	
       
    </nav>
    <div id="contentCovers">
        <div id="roomWrap">
            <div id="roomList">
                <div id="roomCreate" data-toggle="modal" data-target="#createRoomModal">방만들기+</div>
                <div id='roomHeader'>채팅 방 목록</div>
                <div id="roomSelect">
                
                
	                
	                
	                <c:forEach items="${chat }" var="chat">
	               				<c:if test="${empty chat }">
									<div class="roomEl" data-id="2" >채팅방이 없습니다.</div>
								</c:if>
		                  
		                    <div class="roomEl" data-id="2" onclick="selectMember('${chat.title}','${chat.realRoom}');">${chat.title}</div>
	                </c:forEach>    
                    
                  
              </div>
          </div>



        </div>


        <div id="chatWrap">
            <div id="chatHeader">Everyone</div>

            <div id="chatLog">
                <div class="anotherMsg">
                    <span class="anotherName">Jo</span>
                    <span class="msg">Hello, Nice to meet you.</span>
                </div>
                <div class="myMsg">
                    <span class="msg">Nice to meet you, too.</span>
                </div>
                
                <!-- test -->
               
                 <div id="chatBox"></div>
                <!-- test -->

                <!--  <h1>채팅방</h1>
					<div id="chatConnect">
					    <button onclick="connectSocket()">채팅 시작하기</button>
					</div>
					
					<div id="chat1" hidden="hidden">
					    <input id="message">
					    <button id="sendBtn">전송</button>
					  
					</div> -->
                
            </div>
            
            
           <!--  <form id="chatForm"> -->
                <input type="text" autocomplete="off" size="30" id="message" placeholder="메시지를 입력하세요">

              <!--   <input type="submit" id="sendBtn" value="보내기"> -->
              <button id="sendBtn">전송</button>
           <!--  </form> -->
            
            
            
        </div>
        <div id="memberWrap">
            <div id="memberList">
                <div id="memberHeader">사람</div>
                <div id="realMemberList">
                </div>
                <div id="memberSelect"></div>
            </div>
        </div>
    </div>
</div>

</div>



<!-- 모달 -->
<div id="createRoomModal" class="modal modal-default fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<br>
			<div class="modal-header">
				<h3 class="modal-title">채팅방 생성</h3>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" data-rno>

				<form role="form" method="post" action="/chat/createRoom" name="createForm">
					<br>
					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;채팅방 이름 </span></label>
						<div class="col-lg-12">

							<input id="title" onclick="button_click();" class="form-control col-md-12 seokid" name="title" value="" placeholder="">
			
			            </div>
							
					</div>

					

					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;프로젝트 선택</span></label>
						<div class="col-lg-12">
			                    <select class="select2_single form-control" name="Ptitle" id="selectProject" tabindex="-1" >
			                        
			                      
			                    </select>
						</div>
					</div>
					
					<div class="form-group row">
						  <label ><span class="required" >&nbsp;&nbsp;&nbsp;멤버선택</span></label>
						<div class="col-lg-12" id="memberInvite">
			                        <!-- <input type="checkbox" id="member" name="member" value="석기현">석기현<br>
					                <input type="checkbox" id="member" name="member" value="기순">기순<br>
					                <input type="checkbox" id="member" name="member" value="순기">순기 -->
						</div>
					</div>
					<span id="checkCreateRoom">가이드</span>
					<div class="float-right">
						<button type="button" class="btn btn-danger" id="replyDelBtn"
							onclick="createRoom();">생성</button>
						<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
					</div>	
					
				</form>
			</div>


		</div>

		<div class="modal-footer">

			
		</div>
	</div>
</div>
</div>

<!-- 모달끝 -->


<script>

if(${create eq 'success'}){
	alert("채팅방 등록 완료 되었습니다.");
}

chatRealRoom = null;
flag = 0;

window.onload = function(){
	

	
	//모달을 열시 
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
	
	//프로젝트 리스트 누를 시 나타나는 체크박스
	$("#selectProject").change(function(){
	
		
		var selectProj = $('#selectProject').val();
		
		alert(selectProj+"hoho");
	/* 	<div class="col-lg-12" id="memberInvite">
        <input type="checkbox" id="member" name="member" value="석기현">석기현<br>
        <input type="checkbox" id="member" name="member" value="기순">기순<br>
        <input type="checkbox" id="member" name="member" value="순기">순기 
		</div>
		 */
		
		 $.ajax({
		    url: "/chat/getProjectUser.do",
		    type: "POST",
		    data: {"selectProj":selectProj},

		    success : function(arg) {
			
		    	var userList ="";
		    	
		    	 for (var i = 0; i < arg.length; i++) { 
		    	     
		    	      userList += 
		    	    	  "<input type='checkbox' class='member'"+i+" name='userId' value='"+arg[i]+"'>"+arg[i]+"<br>";
		    	    }
		    	 document.getElementById('memberInvite').innerHTML=userList;
		    	
		    }, 
		    error : function(arg){
			alert("통신실패시에만 실행");
		    }
		}); 
		

		
	})
	//체크박스 끝
	
	  $("#message").keyup(e => {
	        if (e.keyCode == 13) {
	            sendMessage();
	        }
	    });

	    $("#sendBtn").click(() => {
	        sendMessage();
	    }); 
	   
	
}
//윈도우 함수 끝

function createRoom(){
	
	  var title = document.getElementById('title');
	  var selectProject = document.getElementById('selectProject');
		
      
		 if(title.value==""){
			 checkCreateRoom.innerHTML = "채팅방 제목을 작성해주세요.";
			
			return false;
	  	}
		 
		 if(selectProject.value==""){
			 checkCreateRoom.innerHTML = "프로젝트를 선택해주세요.";
			
			return false;
	  	}
		  	
		 var isSeasonChk = false;
	        var arr_Season = document.getElementsByName("userId");
	        for(var i=0;i<arr_Season.length;i++){
	            if(arr_Season[i].checked == true) {
	                isSeasonChk = true;
	                break;
	            }
	        }
	    
	        if(!isSeasonChk){
	        	 checkCreateRoom.innerHTML = "멤버는 적어도 한 명을 선택해야합니다.";
	            return false;
	        }
		 
	document.createForm.submit();

}
//채팅방 생성
	
	
//채팅방 제목을 누를 시 나오는, 여기서 채팅방  소켓을 같이 열어준다.
function selectMember(target,room){
	chatRealRoom = room;
	
	if(flag=="1"){
		onclose();
		flag="0";
	}
	
	
	 $.ajax({
		    url: "/chat/getRoomUser",
		    type: "POST",
		    data: {"pTitle":target},

		    success : function(arg) {
			
		    	var userList ="";
		    	
		    	 for (var i = 0; i < arg.length; i++) { 
		    	     
		    	      userList += 
		    	    	  "<div class='memberEl'>"+arg[i]+"</div>";
		    	 }
		    	 document.getElementById('realMemberList').innerHTML=userList;
		    	 //소켓 통신 시작
		    	 connectSocket(chatRealRoom);
		    	 flag =1;
		    }, 
		    error : function(arg){
			alert("통신실패시에만 실행");
		    }
		}); 

	
}
//끝

function getRoomNumId(){
	
	
	
	
	
}


//채팅방 인원 출력

/* 소켓 통신 시작 스크립트 */
    
    let sock;
    function connectSocket(roomNo) {
    	
    	
        sock = new SockJS("/chat");
        sock.onopen = function (){
        	   sock.onmessage = (data => {
                   $("<p>" + data.data + "</p>").prependTo('#chatBox');
               });
        
        alert(roomNo);

        sock.onmessage = (data => {
        	alert(data.data);
        });
        
        }
        sock.onerror = function (e) {
            alert('연결에 실패하였습니다.');
            $('#chatConnect').show();
        }
        
    
    }
    function sendMessage() {
        sock.send($("#message").val());
        $('#message').val("");
    }

  
    function onclose() {
    	sock.onclose = function () {
            alert('연결을 종료합니다.');
        };
    }




</script>  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>





