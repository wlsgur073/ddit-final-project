function sendMailBox(userId){
	sendMailBox = function(){};
	
	var sessionId = userId;
	
	var sendMailList = $("#sendMailList").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getSendMailList?userFrom=" + sessionId
			},
			pageSize: 7,
			schema: {
				model: {
					fields: {
						mailNo  : { type: "number" },
						title   : { type: "string" },
						userTo  : { type: "string" },
						regDate : { type: "string" }
					}
				}
			},
			change: function(e) {
				var data = e.sender.data();
				if(data[0]){
					sendMailDetail(data[0].mailNo);
				}else{
					mailNoDetail("send");
				}
			}
		},
		batch: true,
		pageable: {
	        alwaysVisible: false
	    },
	    height: 580,
		toolbar: [
			{ template:
				"<div class='btn-group'>" +
					"<button type='button' class='btn btn-sm btn-dark' style='padding-top:5px;padding-bottom:0px;cursor:default;'>" +
						"<input id='sendAllCheckButton' type='checkbox' onchange='sendAllCheck();'>" +
					"</button>" +
					"<button type='button' class='btn btn-sm btn-dark btn-append' onclick='deleteSendMailAll();'>삭제</button>" +
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
			{ field: "mailNo" , hidden: true },    
			{ field: "title"  , hidden: true },    
			{ field: "userTo" , hidden: true }, 
			{ field: "regDate", hidden: true },
			{ template: kendo.template($("#sendMailList-template").html()) }
		]
	});
	
	$('#sendMailList').find('thead').hide();
}