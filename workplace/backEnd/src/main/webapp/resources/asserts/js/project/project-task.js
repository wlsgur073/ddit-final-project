var projNo = getParameterByName("projNo");
console.log(projNo);


// 프로젝트 리스트 데이터소스
const projDataSource = new kendo.data.DataSource({
      type: "json",
      transport: {
          read: "/app/project/getProjectListByUserId"
      },
      pageSize: 5,
      schema: {
          model: {
        	  id:"id",
              fields: {
            	  id: { type: "number"},
            	  title: { type: "string" },
            	  startdate: { type: "date" },
            	  enddate: { type: "date" },
            	  status: { type: "string" },
            	  updatedate: { type: "date" },
            	  tagNames: {from:"tagNames"},
            	  tagNo: {from:"tagNo"}
              }
          }
      },
      sort: { field: "updatedate", dir: "desc" }
});

// 태스크 데이터리소스
const taskDataSource = new kendo.data.DataSource({
		transport: {
				read: {
					url: "/app/task/read",
					method: "POST",
					data: {"projNo":projNo},
					success:function(result) {
						console.log(result);
						}
				},
	             update: {
	            	url: "/app/task/update",
	     			method: "POST",
	     			data: projNo,
	     			success:function(result) {
	     				console.log(result);
	     			}
	             },
	             create:  {
	            	url: "/app/task/update",
	     			method: "POST",
	     			data: {"projNo":projNo},
	     			success:function(result) {
	     				console.log(result);
	     			}
	             },
	             destroy: {
	            	url: "/app/task/destroy",
	     			method: "POST",
	     			data: projNo,
	     			success:function(result) {
	     				console.log(result);
	     			}
	             }
		    },
		    schema: {
	    	model: {
	    		fields: {
	    			taskNo: { type: "string" },
	    			title: { type: "string" },
	    			projTitle: { type: "string"},
	    			userId: { type: "string" },
	    			content: { type: "string" },
	    			important: { type: "string" },
	    			regdate: { type: "date", format:"{0:yy-MM-dd}"},
	    			startdate: { type: "date", format:"{0:yy-MM-dd}" },
	    			enddate: { type: "date", format:"{0:yy-MM-dd}" },
	    			updatedate: { type: "date", format:"{0:yy-MM-dd}" },
	    			status: { type: "string" },
	    		}
	    	}
	    },
	    /*sort: {field: "updatedate", dir:"desc"},*/
});

const projList = $("#project-list").kendoGrid({
	pageable: true,
	toolbar: [
		{ template: "<a class='k-button' href='javascript:getOverlayTemplate(\"projRegistFormTemplate\");'>프로젝트 등록</a>" }
		,{ template: "<div class='k-spacer'>&nbsp;</div>"},"search"
	],
	messages: {
	    commands: {
	      search: "검색"
	    }
	  },
    columns: [
    	{ field: "title", hidden:true},
    	{ field: "status", hidden:true },
		{ field: "startdate", hidden:true },
		{ field: "tagNames", hidden:true},
		{
			template: $("#projectCardTemplate").html(), field:"odd"
		},
	],
	height:950,
	dataSource: projDataSource
});

$(".k-toolbar.k-grid-toolbar").css("flex","1 1 auto");
$("table[role='grid']").find("thead").hide();

//
function readTask() {
	$("#taskBoard").empty();
	$("#taskBoard").kendoTaskBoard({
		template: $("#card-template").html(),
	    columns: [
	        { text: "예정", status: "B201" },
	        { text: "진행중", status: "B202" },
	        { text: "지연", status: "B203" },
	        { text: "완료", status: "B204" }
	    ],
	    messages: {
	    	search: "검색"
	    },
	    toolbar: {
	        items: [
	        	{ template: "<button class='k-button' type='button' onclick='getOverlayRegistTaskTemplate(\"taskRegistFormTemplate\",\"/app/task/getTaskRegistInfoByProjNo\")'>업무 등록</button>" },
	            "spacer",
	            "search"
	        ],
	    },
	    dataSource: taskDataSource,
	    dataStatusField: "status",
	    height: 750,
	    width: "100%",
	    columnSettings: {
	    	width:260,
	         buttons: []
	      },

	    moveEnd: function (ev) {
	    	var taskNo = ev.card.get("taskNo");
	    	var status = ev.column.get("status");
	    	var taskVO = {"taskNo":taskNo,"status":status};
	        console.log(ev.card.get("title") + " will move to " + ev.column.get("text"));
	    }
	});
}

var projGrid = $("#project-list").data("kendoGrid");

const sortingProjectByTag = function(clickTag) {
	console.log(typeof clickTag);
	var externalFilter = {
              filters:[
            	  { field:"tagNames", value:clickTag, operator: "contains" }
              ]
            };
	console.log(projGrid.dataSource.view().at(1).title);
	projGrid.dataSource.filter(externalFilter);
}

// url 파라미터값 가져오기
function getParameterByName(name) {
  	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
   	results = regex.exec(location.search);
   	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


/*핸들바스관련 CRUD*/
function deleteTaskById(url, taskNo){

	var taskVO = {"taskNo":taskNo}

	if(window.confirm("정말로 삭제하시겠습니까?")){

		$.ajax({
			url : url,
			type : 'POST',
			datatype : 'text',
			data : taskVO,
			success : function(data) {
				alert("삭제가 완료되었습니다.");
				document.getElementById('task-tab').click();
			}, // success
			error : function(xhr, status) {
				alert("삭제에 실패하였습니다.");
			}
		});

	} else {
		return;
	}

}
