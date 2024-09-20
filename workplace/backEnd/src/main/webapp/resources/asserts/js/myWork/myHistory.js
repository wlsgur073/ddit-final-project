//히스토리 시작
function readMyHistory(userId){
	var sessionId = userId;
	
	var myHistoryTable = $("#myHistory").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getHistory?userId=" + sessionId
			},
			pageSize: 10,
			schema: {
				model: {
					fields: {
						msg     : { type: "string" },
						url     : { type: "string" },
						regDate : { type: "string" },
						projNo  : { type: "string" },
						dist    : { type: "string" },
						userId  : { type: "string" },
						act     : { type: "string" }
					}
				}
			}
		},
	    batch: true,
	    pageable: {
	        alwaysVisible: true,
	        pageSizes: [5, 10, 20, 100]
	    },
	    scrollable: false,
	    toolbar: [    		
			{ template: "<input type='search' id='myHistoryProject-category' style='width:300px;vertical-align:middle;'/>"},    		
			{ template: "<input type='search' id='myHistoryDist-category' style='width:150px;vertical-align:middle;'/>"},    		
	    	"search"
		],
		messages: {
			commands: {
				search: "검색"
		    }
		},
		noRecords: {
			template: function(e) {
				return "<h2 class='text-center' style='color:#73879c;'>히스토리가 존재하지 않습니다.</h2>";
			}
		},
	    columns: [
	    	{ field: "msg",     hidden:true },
	    	{ field: "url",     hidden:true },
	    	{ field: "regDate", hidden:true },
	    	{ field: "projNo",  hidden:true },
	    	{ field: "dist",    hidden:true },
	    	{ field: "userId",  hidden:true },
	    	{ field: "act",     hidden:true },
	    	{ template: kendo.template($("#myHistory-template").html()) }
	    ]
	});
	
	$("#myHistory").find("thead").hide();
	
	var myHistoryProjectDropdown = myHistoryTable.find("#myHistoryProject-category").kendoDropDownList({
		dataSource: {
			type: "json",
	        transport: {
	        	read: "/app/myWork/getMyHistoryProjectSort?userId=" + sessionId
	        },
			schema: {
				model: {
					fields: {
						projNo : { type: "string" }
					}
				}
			}
		},
		dataTextField: "projNo",
		dataValueField: "projNo",
		autoBind: false,
		optionLabel: "전체",
		change: function() {
			var value = this.value();
			if (value) {
				myHistoryTable.data("kendoGrid").dataSource.filter({ field: "projNo", operator: "eq", value: value });
			} else {
				myHistoryTable.data("kendoGrid").dataSource.filter({});
			}
		}
	});
	
	var myHistoryDistDropdown = myHistoryTable.find("#myHistoryDist-category").kendoDropDownList({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getMyHistoryDistSort?userId=" + sessionId
			},
			schema: {
				model: {
					fields: {
						dist : { type: "string" }
					}
				}
			}
		},
		dataTextField: "dist",
		dataValueField: "dist",
		autoBind: false,
		optionLabel: "전체",
		change: function() {
			var value = this.value();
			if (value) {
				myHistoryTable.data("kendoGrid").dataSource.filter({ field: "dist", operator: "eq", value: value });
			} else {
				myHistoryTable.data("kendoGrid").dataSource.filter({});
			}
		}
	});
}
// 히스토리 끝