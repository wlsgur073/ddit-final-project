function trashMailBox(userId){
	trashMailBox = function(){};
	
	var sessionId = userId;
	
	var trashMailList = $("#trashMailList").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getTrashMailList?userId=" + sessionId
			},
			pageSize: 7,
			schema: {
				model: {
					fields: {
						mailNo   : { type: "number" },
						title    : { type: "string" },
						userTo   : { type: "string" },
						regDate  : { type: "string" },
						userTo   : { type: "string" },
						userFrom : { type: "string" }
					}
				}
			},
			change: function(e) {
				var data = e.sender.data();
				if(data[0]){
					trashMailDetail(data[0].mailNo);
				}else{
					mailNoDetail("trash");
				}
			}
		},
		batch: true,
		noRecords: true,
		pageable: {
	        alwaysVisible: false
	    },
	    scrollable: false,
	    height: 580,
		toolbar: [
			{ template:
				"<div class='btn-group'>" +
					"<button type='button' class='btn btn-sm btn-dark' style='padding-top:5px;padding-bottom:0px;cursor:default;'>" +
						"<input id='trashAllCheckButton' type='checkbox' onchange='trashAllCheck();'>" +
					"</button>" +
					"<button type='button' class='btn btn-sm btn-dark btn-append' onclick='deleteTrashMailAll();'>삭제</button>" +
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
		columns: [
			{ field: "mailNo"  , hidden: true },    
			{ field: "title"   , hidden: true },    
			{ field: "userTo"  , hidden: true }, 
			{ field: "regDate" , hidden: true },
			{ field: "userTo"  , hidden: true },
			{ field: "userFrom", hidden: true },
			{ template: kendo.template($("#trashMailList-template").html()) }
		]
	});
	
	$('#trashMailList').find('thead').hide();
}	
