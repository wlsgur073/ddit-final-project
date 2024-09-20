var projNo = getParameterByName("projNo");
//히스토리 시작
var collaboHistoryTable = $("#collaboHistory").kendoGrid({
	messages: {
	    commands: {
	      search: "검색"
	    }
	  },
	dataSource: {
		type: "json",
		transport: {
			read: "/app/myWork/getProjHistory?projNo=" + 999
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
    toolbar: [
		{ template: "<input type='search' id='collaboHistoryDist-category' style='width:150px;vertical-align:middle;'/>"},
    	"search"
	],
    columns: [
    	{ field: "msg",     hidden:true },
    	{ field: "url",     hidden:true },
    	{ field: "regDate", hidden:true },
    	{ field: "projNo",  hidden:true },
    	{ field: "dist",    hidden:true },
    	{ field: "userId",  hidden:true },
    	{ field: "act",     hidden:true },
    	{ template: $("#collaboHistory-template").html() }
    ]
});

$("#collaboHistory").find("thead").hide();

var collaboHistoryDistDropdown = collaboHistoryTable.find("#collaboHistoryDist-category").kendoDropDownList({
	dataSource: {
		type: "json",
		transport: {
			read: "/app/myWork/getProjHistoryDistSort?projNo=" + 999
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
	optionLabel: "All",
	change: function() {
		var value = this.value();
		if (value) {
			collaboHistoryTable.data("kendoGrid").dataSource.filter({ field: "dist", operator: "eq", value: value });
		} else {
			collaboHistoryTable.data("kendoGrid").dataSource.filter({});
		}
	}
});

// 히스토리 끝