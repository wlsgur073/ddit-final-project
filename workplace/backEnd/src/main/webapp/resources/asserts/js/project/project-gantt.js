var projNo = getParameterByName("projNo");
console.log(projNo);

var ganttDataSource = new kendo.data.GanttDataSource({
	transport: {
		/*read: {
		      url: "https://demos.telerik.com/kendo-ui/service/GanttTasks",
		      dataType: "jsonp"
		    },*/
	    read: {
	      url: "/app/task/getTaskListForGanttByProjNo",
	      dataType: "json",
	      data: { "projNo": projNo }
	    }
	  },
	  schema: {
	    model: {
	      id: "id",
	     /* fields: {
	          id: { from: "ID", type: "number" },
	          orderId: { from: "OrderID", type: "number", validation: { required: true } },
	          parentId: { from: "ParentID", type: "number", validation: { required: false }, defaultValue: null },
	          start: { from: "Start", type: "date" },
	          end: { from: "End", type: "date" },
	          title: { from: "Title", defaultValue: "", type: "string" },
	          summary: { from: "Summary" },
	          expanded: { from: "Expanded" }
	        }*/
	      fields: {
	        id: { from: "taskNo", type: "number" },
	        orderId: { from: "OrderID", type: "number", validation: { required: true } },
	        parentId: { from: "ParentID", type: "number", validation: { required: true }, defaultValue: null },
	        start: { from: "startdate", type: "date" },
            end: { from: "enddate", type: "date" },
            title: { from: "title", defaultValue: "", type: "string" },
	      }
	    }
	  },
	  sort: {field: "updatedate", dir:"desc"},
	});
	function projGantt() {
		$("#projGantt").empty();
		var projectGantt = $("#projGantt").kendoGantt({
			dataSource:ganttDataSource,
			tooltip: {
				visible: true,
			},
			 messages: {
				    noRecords: "There is no data on current page"
				  },
			toolbar:[
				{ template: "<a class='k-button' href='javascript:getOverlayRegistTaskTemplate(\"taskRegistFormTemplate\",\"/app/task/getTaskRegistInfoByProjNo\")'>" +
						"업무 등록</a>" },
				],
				views: [
					"day",
					"week",
					{ type: "month",
						selected: true
					}
					],
					columns: [
						{ field: "title", title: "업무명", editable: true,
							template: "<a href=\"javascript:getTaskTemplate('/app/task/getTaskDetailByTaskNo',#:id#,'taskDetailForm','taskDetailFormTarget')\" class='text-dark'>#:title#</a>"}
						],
						autoSync:true,
						height:774,
						listWidth: 200,
						showWorkHours: false,
						showWorkDays: false,
						snap: true,
						editable: {
							update: false,
							remove: false,
							move: false,
						},
		})
		kendo.culture("ko-KR");
	}

	function ganttRefresh() {
		 var projectGantt = $("#projGantt").getKendoGantt();
			projectGantt.dataSource.read()
			projectGantt.dependencies.read()
	}