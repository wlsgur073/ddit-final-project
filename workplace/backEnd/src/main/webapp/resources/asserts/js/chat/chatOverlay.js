function onChatList() {
	$("#popoverlay").fadeIn(300);
}

function offChatList() {
	$("#popoverlay").fadeOut(300);
	
	
}


chatFlag = false; 

function getOverlayTemplateChatList(templateId) {
	
	
	if(chatFlag==false){
		
	
	
	
		onChatList();
		        console.log("Handlebars success!!");
		        var template = document.querySelector("#" + templateId).innerText;
		        var bindTemplate = Handlebars.compile(template);
		        var appe = document.querySelector('#popoverlay');
		        var html = bindTemplate();
		        appe.innerHTML = html;
		        $("#fadeInContent").fadeIn(300);
		        chatFlag = true;
		        openChatList();
		        
	}else{
		
		offChatList();
		chatFlag = false;
		
	}
	 
	   /*getModalTemplate(chatModalTemplate);*/
    
}


function openChatList(){
	
		var crudServiceBaseUrlSeok="/chat/"
		var seokListTemplate = "<span style='cursor : pointer;' onclick='selectChatRoom('#: realRoom # ')'> #: title #</span>";
			
          var dataSource = new kendo.data.DataSource({
                 transport: {
                     read: {
                    
                         url: crudServiceBaseUrlSeok + "Read",
                         dataType: "json"
                     },
                     
                     update: {
                         url: crudServiceBaseUrlSeok + "Update",
                         contentType: "application/json",
                         type: "POST",
                         dataType: "json"
                     },
                     
            
                     destroy: {
                    	 url: crudServiceBaseUrlSeok + "Destroy",
                         contentType: "application/json",
                         type: "POST",
                         dataType: "json"
                     },
                  
                     parameterMap: function (options, operation) {
                        if (operation !== "read" && options.models) {
                            // return { models: kendo.stringify(options.models) };
                        	 return JSON.stringify(options);                         
                        }
                     }
                     
                 },
                 batch: true,
                 pageSize: 8,
                 schema: {
                     model: {
                         id: "chatroomNo",
                         fields: {
                        	 chatroomNo: { editable: false, type: "String" },
                             title: {
                                 validation: {
                                     required: true
                                     //productnamevalidation: function (input) {
                                     //    if (input.is("[name='ProductName']") && input.val() != "") {
                                      //       input.attr("data-productnamevalidation-msg", "Product Name should start with capital letter");
                                       //      return /^[A-Z]/.test(input.val());
                                      //   }

                                       //  return true;
                                     //}
                                 }
                             },
                             projNo: { editable: true, type: "String"},
    	                     realRoom: { editable: true, type: "String"},
    	                     userId: { editable: true, type: "String"},
    	                     regdate: { editable: true, type: "String"}	
                         }
                     }
                 }
             });

         $("#chatListSeok").kendoGrid({
             dataSource: dataSource,
             pageable: true,
             width:500,
             height: 400,
             columns: [
                
            	 {
	                 field: "title",
	                 title: "팀원들과 소통해 보아요.",
	                 template: "<span style='cursor : pointer;' onclick=selectChatRoom('#=realRoom #')> #:title #</span>",
	                 width: 200,
	                 encoded: false
	             },
	           
            	 
            	 { 
            		 command: ["edit", "destroy"], 
            		 title: "&nbsp;", 
            		 width: "200px"
            	 }
                 
                 ],
                 noRecords: {
 	      			template: function(e) {
 	      				return "<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;채팅방이 존재하지 않습니다.</h2>";
 	      			}
 	      		},
             editable: "inline"
         });

}



