//Collabo Task 데이터리소스//
const collaboTaskDataSource = new kendo.data.DataSource({
		transport: {
				read: {
					url: "/app/collabo/TaskRead",
					method: "POST",
					data: {"cprojNo":cprojNo},
					success:function(result) {
						console.log("===============>"+result);
						}
				},
	             update: {
	            	url: "/app/collabo/TaskUpdate",
	     			method: "POST",
	     			data: cprojNo,
	     			success:function(result) {
	     				console.log(result);
	     			}
	             },
	             create:  {
	            	url: "/app/collabo/TaskUpdate",
	     			method: "POST",
	     			data: {"cprojNo":cprojNo},
	     			success:function(result) {
	     				console.log(result);
	     			}
	             },
	             destroy: {
	            	url: "/app/collabo/TaskDelete",
	     			method: "POST",
	     			data: cprojNo,
	     			success:function(result) {
	     				console.log(result);
	     			}
	             }
		    },
		    schema: {
	    	model: {
	    		fields: {
	    			taskNo: { from:"taskNo", type: "string" },
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


// Collabo Task Read
function readCollaboTask() {
	$("#collaboTaskBoard").empty();
	$("#collaboTaskBoard").kendoTaskBoard({
		template: $("#collabo-card-template").html(),
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
	        	{ template: "<button class='k-button' type='button' onclick='getOverlayRegistCollaboTaskTemplate(\"collaboTaskRegistFormTemplate\",\"/app/collabo/getTaskRegistInfoByCprojNo\")'>업무 등록</button>" },
	            "spacer",
	            "search"
	        ],
	    },
	    dataSource: collaboTaskDataSource,
	    dataStatusField: "status",
	    height: 750,
	    width: "100%",
	    columnSettings: {
	    	width:260,
	         buttons: []
	      },

	    moveEnd: function (ev) {
	        console.log(ev.card.get("title") + " will move to " + ev.column.get("text"));
	    }
	})     
};

/*핸들바스관련 삭제*/
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
				collaboAlert(sessionNickname, cprojectTitle, targetTitle, crud, cprojectNumber, targetNickname);
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
