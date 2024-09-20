//마일스톤 시작
function readMyMile(uid){
	var sessionId = uid;
	
	const myMileDataSource = new kendo.data.DataSource({
		transport: {
			type:"json",
			read: "/app/milestone/getMilestoneListByUserId?userId=" + sessionId,
		},
		schema: {
			model: {
				id: "id",
				fields: {
					id     : { from:"mileNo" , type: "string" },
					title  : { from:"title"  , type: "string" },
					content: { from:"content", type: "string" },
					userId : { from:"userId" , type: "string" },
					regdate: { from:"regdate", type: "date"   },
					status : { from:"status" , type: "string" }
				}
			}
		}
	});
	
	$("#myMileList").empty();
	$("#myMileList").kendoListView({
		dataSource: myMileDataSource,
		selectable: true,
		change    : function() {
			var index = this.select().index();
			var selectedData = this.dataSource.view()[index];
			
			sortingIssueByClick(selectedData.id);
		},
		template  : $("#myMileTemplate").html(),
		dataBound: function() {
			$('.B301').kendoBadge({
				themeColor: 'tertiary',
				text: '미해결'
			});
			$('.B302').kendoBadge({
				themeColor: 'primary',
				text: '해결'
			});
		}
	});
	
	$('#myMileSearch').on('keyup', function(){
		var groudIds = $("#groupDistIds").val();
		var search = $.trim($(this).val());
		if(search != "")
			myMileDataSource.filter({ field: "title", operator: "contains", value: search });		
		else
			myMileDataSource.filter({});
		return;
	});
	
	const sortingIssueByClick = function(tData) {
		if(tData === "clear"){
			$("#myIssueGrid").data("kendoGrid").dataSource.filter([]);
			return;
		}
		var externalFilter = {
			filters:[
				{ field:"mileNo", value:tData, operator: "contains" }
			]
		}
		$("#myIssueGrid").data("kendoGrid").dataSource.filter(externalFilter);
	}
}

