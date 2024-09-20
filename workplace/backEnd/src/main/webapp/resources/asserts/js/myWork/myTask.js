// 업무 시작
function readMyTask(uid, nname){
	$("#myTaskBoard").empty();
	var userId = uid;
	var nickname = nname;
	
	var myTaskDataSource = new kendo.data.DataSource({
		transport: {
			read: {
				url    : "/app/myWork/getTaskListByUserId",
				method : "POST",
				data   : { "userId" : userId },
				success: function(result) {
					console.log(result);
				}
			},
			update: {
				url    : "/app/myWork/updateMyTaskListByUserId",
				method : "POST",
				data   : { "userId" : userId },
				success: function(result) {
				}
			},
			create: {
				url    : "/app/myWork/updateMyTaskListByUserId",
				method : "POST",
				data   : { "userId" : userId },
				success: function(result) {
				}
			},
			destroy: {
				url    : "/app/myWork/destroyMyTaskListByUserId",
				method : "POST",
				data   : { "userId" : userId },
				success: function(result) {
					console.log(result);
				}
			}
		},
	    schema: {
	    	model: {
	    		fields: {
	    			projNo     : { type: "string" },
	    			taskNo     : { type: "string" },
	    			status     : { type: "string" },
	    			important  : { type: "string" },
	    			userId     : { type: "string" },
	    			title      : { type: "string" },
	    			content    : { type: "string" },
	    			projTitle  : { type: "string" },
	    			nickname   : { type: "string" },	    			
	    			regdate    : { type: "date", format:"{0:yy-MM-dd}"},
	    			startdate  : { type: "date", format:"{0:yy-MM-dd}"},
	    			enddate    : { type: "date", format:"{0:yy-MM-dd}"},
	    			updatedate : { type: "date", format:"{0:yy-MM-dd}"}
	    		}
	    	}
	    },
	   change: function() {
		   var canvasTag = "<canvas id='myTaskChartDashboard'></canvas>";
		   
		   $("#canvasBox").empty();
		   $("#canvasBox").append(canvasTag);
		   myTaskChart(userId);
		}
	    /*sort: {field: "updatedate", dir:"desc"}*/
	});
	
	var myTaskTable = $("#myTaskBoard").kendoTaskBoard({
		template: $("#myTask-template").html(),
		columns: [
			{ text: "미배정" , status: "B201" },
			{ text: "진행중" , status: "B202" },
			{ text: "지연중" , status: "B203" },
			{ text: "완료" , status: "B204" }
	    ],
	   	toolbar: {
	   		items: [
	   			{ template: "<button type='button' class='k-button' onclick='getOverlayTaskRegistTemplate(\"{{userFrom}}\")'>업무 등록</button>" },
	   			"spacer",
	   			{ template: "<input type='search' id='myTaskProject-category' style='height:41px;width:300px;vertical-align:middle;'/>" },
	    		"search"
	    	]
    	},
    	messages: {
            search: "검색"
        },
    	dataSource: myTaskDataSource,
    	dataStatusField: "status",
    	height: 750,
    	width: "100%",
    	columnSettings: {
    		width:260,
    		buttons: []
    	}
	});
	
	var myTaskDropdown = myTaskTable.find("#myTaskProject-category").kendoDropDownList({
		dataSource: {
			type: "json",
	        transport: {
	        	read: "/app/myWork/getProjectNameForSort?userId=" + userId
	        },
			schema: {
				model: {
					fields: {
						projTitle : { type: "string" }
					}
				}
			}
		},
		dataTextField: "projTitle",
		dataValueField: "projTitle",
		autoBind: false,
		optionLabel: "전체",
		change: function() {
			var value = this.value();
			if (value) {
				myTaskTable.data("kendoTaskBoard").dataSource.filter({ field: "projTitle", operator: "eq", value: value });
			} else {
				myTaskTable.data("kendoTaskBoard").dataSource.filter({});
			}
		}
	});
}