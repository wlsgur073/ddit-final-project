<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">

<!------ Include the above in your HEAD tag ---------->

<link rel="icon" href="<%=request.getContextPath()%>/resources/asserts/images/tab-img.jpg">

		<!-- 추가내용 -->
		<link href="cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
		<!-- Gantt -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/gantt/sources/dhtmlxgantt.css" type="text/css"/>
		<!-- Bootstrap -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/bootstrap/dist/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/font-awesome/css/font-awesome.min.css">
		<!-- NProgress -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/nprogress/nprogress.css">
		<!-- Dropzone.js -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/dropzone/dist/min/dropzone.min.css">
		<!-- bootstrap-wysiwyg -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/google-code-prettify/bin/prettify.min.css">

		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/css/common/web-app-index.css">
		<!-- iCheck -->
		
	
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.buttons.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.nonblock.css">
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/css/common/toastr.min.css"/>


		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

		<!-- jQuery -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
		<!-- FastClick -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
		<!-- NProgress -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/nprogress/nprogress.js"></script>
		<!-- Dropzone.js -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/dropzone/dist/min/dropzone.min.js"></script>
		<!-- easy-pie-chart -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>
		<!-- bootstrap-wysiwyg -->

	
		<!-- PNotify -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.js"></script>
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.buttons.js"></script>
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.nonblock.js"></script>


		<!-- toastr js -->
		<script src="<%=request.getContextPath()%>/resources/asserts/js/common/toastr.min.js"></script>



<html>
<head>


<style>
/*     .container{max-width:800px; margin:auto;}
img{ max-width:100%;}
.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 40%; border-right:1px solid #c4c4c4;
}
.inbox_msg {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}
.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:40%;}
.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}
.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}
.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}
.received_withd_msg { width: 57%;}
.mesgs {
  float: left;
  padding: 30px 15px 0 25px;
  width: 100%;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  float: right;
  width: 46%;
}
.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  height: 516px;
  overflow-y: auto;
} */


.chatImage{


margin:5px 10px 0px 3px; 
height:40px; 
width:100px; 
border-radius:100%;



}


html {overflow:hidden;} 



   .container{max-width:1170px; margin:auto;}
img{ max-width:100%;

height:7%;

}


.chat_img img{

height: 8%;

}

.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 40%; border-right:1px solid #c4c4c4;
}
.inbox_msg {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}
.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:40%;}
.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}
.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}
.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}
.received_withd_msg { width: 57%;}
.mesgs {
  float: left;
  padding: 30px 15px 0 25px;
  width: 60%;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  float: right;
  width: 46%;
}
.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  height: 516px;
  overflow-y: auto;
}


.circleOff{


}


</style>





</head>
<body>
<div class="container">
<br>


	<h3 class="text-center">${realChat.title}<button class="btn btn-round btn-danger float-right" style="display: inline-block;" onclick="closeTabClick()">나가기</button></h3>
	
<br>

<div class="messaging">

      <div class="inbox_msg">
      
		
      <!-- 참여중인 시작 -->
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>참여중인 인원</h4>
            </div>
            <div class="srch_bar">
              
            </div>
          </div>
          <div class="inbox_chat">


           
            
            
            
             <c:forEach items="${chatUser }" var="chat">
	               				
		                  
		              <div class="chat_list active_chat">
			              <div class="chat_people">
			                <div class="chat_img"> <img src="/user/getPicture.do?picture=${chat.picture}" alt="sunil" style="margin:5px 10px 5px 3px; height:50px; width:50px; border-radius:100%;"> </div>
			                <div class="chat_ib" style="margin-top: 9px;">
			                
			               
			                 
			               
			                  <h5>${chat.nickname} <span class="chat_date" id="${chat.nickname}onoff">오프라인<div class="circleOff" style='display:inline-block; width: 12px; height: 12px; background: red; border-radius: 50%;'></div></span> </h5>
							
			                 
			                
			                  <p>${chat.userId}</p>
			                </div>
			              </div>
           			 </div>
		             
		             
		             
	         </c:forEach>   
            
            
            
            
            
            
           
           



          </div>
        </div>
      <!-- 참여중인 시작 -->
      
      
      
        <div class="mesgs">
          <div class="msg_history" id="messages-top">
		<!-- 여기에 append -->
            

            


          </div>



          <div class="type_msg">
            <div class="input_msg_write">
              <input type="text" class="write_msg"  id="update-text" placeholder="Type a message" />
              <button  id="publish-button" class="msg_send_btn" type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
              <!-- <button id="publish-button" type="submit" value="Submit Update">ddd</button> -->

            </div>
          </div>
        </div>
      </div>
      
      
     
                         
                         
                         
                         
                        
                                         
                         
                    
      
    </div></div>



    </body>




<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
const messagesTop = document.getElementById('messages-top');
const realRoomNo = "${roomNo}"; 
let sock;	


function closeTabClick() {
	
		 const data = {
                 "realRoom":"${roomNo}",
                 "userId":"${userVO.userId}",
                 "nickname":"${ userVO.nickname }",
              	 "content":"CLOSE-CHAT"
         };
         let jsonData = JSON.stringify(data);
         sock.send(jsonData);
         
         //소켓 메시지 보내기
         //sock.onmessage = onMessage;
		
         
        sock.onclose = function () {
            alert('연결을 종료합니다.');
           
        };
         
         
         
	    
		 window.close(); 

	

}




window.addEventListener('load', function(){
	

	
	//디비에 있는 메시지를 들고온다
	 $.ajax({
	    url: "<%=request.getContextPath()%>/chat/getMessageByDb",
	    type: "POST", 
	    data: {"roomNo": "${roomNo}"},

	    success : function(arg) {
	   
	    	var temp ="";
	    	
	    		//없는 경우 추가하기
	    		for(var i=0 in arg){  
	    			
	    			console.log("arg="+arg[i].user_id);
	    			console.log("로그인="+"${userVO.userId}");
	    			
	    			if(arg[i].userId=="${userVO.userId}"){
	    				
	    				 
	    	        	   temp+='<div class="outgoing_msg">';
	    	        	   temp+='<div class="sent_msg">';
	    	        	   temp+='<p>'+arg[i].nickname+" : "+arg[i].content+'</p>';
	    	        	   temp+='<span class="time_date"> '+arg[i].regdate+'</span>';
	    	        	   temp+='</div>';
	    	        	   temp+='</div>';
	    				
	    				
	    			}else{
	    				
	    				 
	    	        	   temp+='<div class="incoming_msg">';
	    	        	   temp+='<div class="incoming_msg_img"> <img src="/user/getPicture.do?picture='+arg[i].picture+'" style="margin:5px 10px 0px 3px; height:40px; width:100px; border-radius:100%;"></div>';
	    	        	   temp+='<div class="received_msg">';
	    	        	   temp+='<div class="received_withd_msg">';
	    	        	   temp+='<p>'+arg[i].nickname+" : "+arg[i].content+'</p>';
	    	        	   temp+='<span class="time_date">'+arg[i].regdate+'</span>';
	    	        	   temp+='</div>';
	    	        	   temp+='</div>';
	    	        	   temp+='</div>';
	    			}
	    			

	    	    }
	    	 document.getElementById('messages-top').innerHTML=temp;
	    	$("#messages-top").scrollTop($("#messages-top")[0].scrollHeight);
	    }, 
	    error : function(arg){
		alert("통신실패시에만 실행"+arg.status);
	    }
	});	
	 
		
	    sock = new SockJS("/chat");
	    //채팅방 입장과 동시에 실행
	    sock.onopen = function (){
	    	
	       
	        const data = {
                    "realRoom":"${roomNo}",
                    "userId":"${userVO.userId}",
                    "nickname":"${ userVO.nickname }",
                 	"content":"ENTER-CHAT"
            };
            let jsonData = JSON.stringify(data);
            sock.send(jsonData);
            
            //소켓 메시지 보내기
             sock.onmessage = onMessage; 
    
	    }

  	function onMessage(evt) {
            
  		
  		if(evt.data.substring(0,1)=='['){
  			
  			let inUser = evt.data.split(",");
  			
  			for(var i=0; i<inUser.length;i++){
  				
  				
  				let firstUser = inUser[i].replace(/\"/gi, "").replace("[","").replace("]","");
  				document.getElementById(firstUser+"onoff").innerHTML = "온라인&nbsp;<div class='circleOff' style='display:inline-block; width: 12px; height: 12px; background: green; border-radius: 50%;' >"; 
  				/* document.getElementById(firstUser+"onoff").innerText = "온라인"; */
  				
  			}
  			
  			
  			console.log(evt);
  			
  		
  	   }else{
  		
           let receive = evt.data.split(",");
            
           const data = {
        		   "userId":receive[0],
                   "nickname":receive[1],
                   "content":receive[2],
                   "regDate":receive[3],
                   "picture":receive[4]
           };
           
           
           if(receive[2] =='CLOSE-CHAT'){
        	   
        	 
        	  var chatUserId = receive[1]+"onoff";
              document.getElementById(chatUserId).innerHTML = "오프라인&nbsp;<div class='circleOff' style='display:inline-block; width: 12px; height: 12px; background: red; border-radius: 50%;' >"; 
                
              toastr.warning(receive[1]+"님이 퇴장하였습니다.");
        	  
        	   return false;
           }
            
           
           /* 
           	  일단 on
           	  미리 들어온 사람은 어떡하지?
           */
          
         
           /* 
           var chatUserId = receive[1]+"onoff";
           
           document.getElementById(chatUserId).innerText = "온라인";
 			*/
           
           
           
           console.log("이걸 append 시켜 줘야함 userId=>"+receive[0]+" 닉네임: "+receive[1]+" 메시지:"+receive[2]+"날짜 : "+receive[3]+"이미지:"+receive[4]);
           var date = new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0]
           var time = new Date().toTimeString().split(" ")[0];
           var yammyDate = date + ' ' + time;
           
           let lastMessage = receive[1]+" : "+ receive[2];
            
            let pmessage = document.createElement('p');
            let myMessageDiv = document.createElement('div');
            let myMessageDivImg = document.createElement('div');
            let br = document.createElement('br');
            let img = document.createElement('img');
            let messageReceived = document.createElement('div');
            let messageReceivedWith = document.createElement('div');
            let timeDate = document.createElement('span');

            let outgoingMsg = document.createElement('div'); 
            let sentMsg = document.createElement('div'); 

            messagesTop.after(pmessage);
            
            var insertUser = "님이 참가하였습니다.";
            
            if(receive[2] == insertUser && receive[0]!="${userVO.userId}" && receive[2]!="ENTER-CHAT"){
            
            	   toastr.success(receive[1]+"님이 입장하였습니다.");
            	
            }
            
            else if(receive[2] != insertUser && receive[0]=="${userVO.userId}" && receive[2]!="ENTER-CHAT"){
            	
            	 outgoingMsg.className='outgoing_msg';
                 sentMsg.className='sent_msg';

                 timeDate.className='time_date';
                 timeDate.innerHTML=yammyDate;
                 
                
                 messagesTop.append(outgoingMsg);
                 outgoingMsg.append(sentMsg);
                 sentMsg.append(pmessage);
                 sentMsg.append(timeDate);

                 pmessage.appendChild(document.createTextNode(lastMessage));
            	
            }else if(receive[2] != insertUser && receive[2]!="ENTER-CHAT"){
            	
            	  myMessageDiv.className='incoming_msg';
                  myMessageDivImg.className='incoming_msg_img';

                  img.src='/user/getPicture.do?picture='+receive[4];
                  img.alt='sunil'; 
                  img.className="chatImage";

                  messageReceived.className='received_msg';
                  messageReceivedWith.className='received_withd_msg';
                  timeDate.className='time_date';
                  timeDate.innerHTML=yammyDate;

                  pmessage.id='myMessage';
                  pmessage.className = 'myMessags';

	              messagesTop.append(myMessageDiv);
	              myMessageDiv.append(myMessageDivImg);
	              myMessageDivImg.append(img);
	              myMessageDiv.append(messageReceived);
	              messageReceived.append(messageReceivedWith);
	              messageReceivedWith.append(pmessage);
	              messageReceivedWith.append(timeDate);

              pmessage.appendChild(document.createTextNode(lastMessage));
            	
            } 
  	   }//else문 끝
            $("#messages-top").scrollTop($("#messages-top")[0].scrollHeight);
            
     }
	
	

	
	
	
	$("#update-text").keyup(e => {
		
		 const message = $("#update-text").val();
		
        if (e.keyCode == 13) {
            sendMessage(message);
        }
    });

    $("#publish-button").click(() => {
    	 const message = $("#update-text").val();
        sendMessage(message);
    });
	
	
	function sendMessage(message) {
		
		//.val로 얻어오긴하지만 append해주어야한다.preareto?
		 var date = new Date();
		  var year = date.getFullYear();
		  var month = ("0" + (1 + date.getMonth())).slice(-2);
		  var day = ("0" + date.getDate()).slice(-2);	
				
		  
		 var fullDay = year + "/" + month + "/" + day; 
		 
		 const data = {
				"realRoom":"${roomNo}",
				"userId":"${userVO.userId}",
                "nickname":"${ userVO.nickname }",
                "content"   : message,
                "picture":"${ userVO.picture}"
            };
              
        
         
        let jsonData = JSON.stringify(data);		

        
	    sock.send(jsonData);
	    $("#update-text").val("");
	}
	
});


function insertChatUser(){
	
	
	
	
}




</script>

