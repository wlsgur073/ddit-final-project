function readMyRequest(userId){
	readMyRequest = function(){};
	
	var sessionId = userId;
	
	//Your리퀘스트 시작
	var yourRequestTable = $("#yourRequest").kendoGrid({
		dataSource: {
	        type: "json",
	        transport: {
	        	read: "/app/myWork/getYourRequest?userTo=" + sessionId
	        },
	        pageSize: 10,
	        schema: {
	            model: {
	                fields: {
	                	reqNo   : { type: "string" },
	                	userFrom: { type: "string" },
	                	userTo  : { type: "string" },
	                	projNo  : { type: "string" },
	                	title   : { type: "string" },
	                	content : { type: "string" },
	                	url     : { type: "string" },
	                	status  : { type: "string" },
	                	regDate : { type: "string" },
	                	reqRes  : { type: "string" }
	                }
	            }
	        }
	    },
	    batch: true,
	    pageable: {
	        alwaysVisible: true,
	        pageSizes: [5, 10, 20, 100]
	    },
		toolbar: [    		
			{ template: "<input type='search' id='yourStatus-category' style='width:120px;vertical-align:middle;'/>"},    		
	    	"search"
		],
		noRecords: {
			template: function(e) {
				return "<h2>리퀘스트가 존재하지 않습니다.</h2>";
			}
		},
	   	columns: [
	   		{ field: "reqNo",    hidden: true },
	   		{ field: "userFrom", hidden: true },
	   		{ field: "userTo",   hidden: true },
	   		{ field: "projNo",   hidden: true },
	   		{ field: "title",    hidden: true },
	   		{ field: "content",  hidden: true },
	   		{ field: "url",      hidden: true },
	   		{ field: "status",   hidden: true },
	   		{ field: "regDate",  hidden: true },
	   		{ field: "reqRes",   hidden: true },
	   		{ template: kendo.template($("#yourRequest-template").html()) }
	  	]
	});
	
	$('#yourRequest').find('thead').hide();
	
	var yourStatusDropdown = yourRequestTable.find("#yourStatus-category").kendoDropDownList({
		dataSource: {
			type: "json",
	        transport: {
	        	read: "/app/myWork/getYourRequestSort?userTo=" + sessionId
	        },
			schema: {
				model: {
					fields: {
						status : { type: "string" }
					}
				}
			}
		},
		dataTextField: "status",
		dataValueField: "status",
		autoBind: false,
		optionLabel: "All",
		change: function() {
			var value = this.value();
			if (value) {
				yourRequestTable.data("kendoGrid").dataSource.filter({ field: "status", operator: "eq", value: value });
			} else {
				yourRequestTable.data("kendoGrid").dataSource.filter({});
			}
		}
	});
	//Your리퀘스트 끝
	
	//My리퀘스트 시작
	var myRequestTable = $("#myRequest").kendoGrid({
		dataSource: {
	        type: "json",
	        transport: {
	        	read: "/app/myWork/getMyRequest?userFrom=" + sessionId
	        },
	        pageSize: 10,
	        schema: {
	            model: {
	                fields: {
	                	reqNo   : { type: "string" },
	                	userFrom: { type: "string" },
	                	userTo  : { type: "string" },
	                	projNo  : { type: "string" },
	                	title   : { type: "string" },
	                	content : { type: "string" },
	                	url     : { type: "string" },
	                	status  : { type: "string" },
	                	regDate : { type: "string" },
	                	reqRes  : { type: "string" }
	                }
	            }
	        }
	    },
	    batch: true,
	    pageable: {
	        alwaysVisible: true,
	        pageSizes: [5, 10, 20, 100]
	    },
		toolbar: [    		
			{ template: "<input type='search' id='myStatus-category' style='width:120px;vertical-align:middle;'/>"},    		
	    	"search"
		],
		noRecords: {
			template: function(e) {
				return "<h2>리퀘스트가 존재하지 않습니다.</h2>";
			}
		},
	   	columns: [
	   		{ field: "reqNo",    hidden: true },
	   		{ field: "userFrom", hidden: true },
	   		{ field: "userTo",   hidden: true },
	   		{ field: "projNo",   hidden: true },
	   		{ field: "title",    hidden: true },
	   		{ field: "content",  hidden: true },
	   		{ field: "url",      hidden: true },
	   		{ field: "status",   hidden: true },
	   		{ field: "regDate",  hidden: true },
	   		{ field: "reqRes",   hidden: true },
	   		{ template: kendo.template($("#myRequest-template").html()) }
	  	]
	});
	
	$('#myRequest').find('thead').hide();
	
	var myStatusDropdown = myRequestTable.find("#myStatus-category").kendoDropDownList({
		dataSource: {
			type: "json",
	        transport: {
	        	read: "/app/myWork/getMyRequestSort?userFrom=" + sessionId
	        },
			schema: {
				model: {
					fields: {
						status : { type: "string" }
					}
				}
			}
		},
		dataTextField: "status",
		dataValueField: "status",
		autoBind: false,
		optionLabel: "All",
		change: function() {
			var value = this.value();
			if (value) {
				myRequestTable.data("kendoGrid").dataSource.filter({ field: "status", operator: "eq", value: value });
			} else {
				myRequestTable.data("kendoGrid").dataSource.filter({});
			}
		}
	});
	//My리퀘스트 끝
}