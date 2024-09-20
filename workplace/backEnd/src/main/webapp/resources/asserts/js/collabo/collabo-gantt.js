var cprojNo = getParameterByName("cprojNo");
console.log(cprojNo);

var ganttDataSource = new kendo.data.GanttDataSource({
	transport: {
	    read: {
	      url: "/app/collabo/getTaskListForGanttByCprojNo",
	      dataType: "json",
	      data: { "cprojNo": cprojNo }
	    }
	  },
	  schema: {
	    model: {
	      id: "id",
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
	function collaboGantt() {
		$("#collaboGantt").empty();
		var collaboGantt = $("#collaboGantt").kendoGantt({
			dataSource:ganttDataSource,
			tooltip: {
				visible: true,
			},
			 messages: {
				    noRecords: "There is no data on current page"
				  },
			toolbar:[
				{ template: "<a class='k-button' href='getOverlayRegistCollaboTaskTemplate(\"collaboTaskRegistFormTemplate\",\"/app/collabo/getTaskRegistInfoByCprojNo\")'>" +
						"업무 등록</a>" },
				],
				views: [
					"day",
					{ type: "week",
						selected: true
					},
					{ type: "month",

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
		 var collaboGantt = $("#collaboGantt").getKendoGantt();
		 	collaboGantt.dataSource.read()
			collaboGantt.dependencies.read()
	}