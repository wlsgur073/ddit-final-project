// 이슈 시작
var issueGridFlag = false;

function readMyIssue(uid){
	var sessionId = uid;
	
	const myIssueDataSource = new kendo.data.DataSource({
		transport: {
			read: "/app/issue/getIssueListByUserId?userId=" + sessionId,
		},
		schema: {
			model: {
				id: "id",
				fields: {
					title     : { from:"title"     , type: "string" },
					important : { from:"important" , type: "string" },
					userId    : { from:"userId"    , type: "string" },
					updatedate: { from:"updatedate", type: "date"   },
					status    : { from:"status"    , type: "string" },
					mileNo    : { from:"mileNo"    , type: "string" },
					issueNo   : { from:"issueNo"   , type: "string" },
					projNo    : { from:"projNo"    , type: "string" }
				}
			}
		}
	});
	
	if(issueGridFlag == true){
		$("#myIssueGrid").data("kendoGrid").dataSource.read();
		return;
	}
	
	var myIssueTable = $("#myIssueGrid").kendoGrid({
		dataSource: myIssueDataSource,
		toolbar: [
			{ template: "<a class='k-button' href='javascript:getOverlayMyIssueRegistTemplate(\"" + sessionId + "\");'>이슈 등록</a>" },
			{ template: "<input type='search' id='myIssueProject-category' style='width:300px;vertical-align:middle;'/>" },
			"search"
		],
		noRecords: {
			template: function(e) {
				return "<h2 class='text-center' style='color:#73879c;'>이슈가 존재하지 않습니다.</h2>";
			}
		},
		messages: {
			commands: {
				search: "검색"
		    }
		},
		columns: [
			{
				selectable: true,
				hidden: true,
				width: 75,
				attributes: {
					"class" : "checkbox-align"
				},
				headerAttributes: {
					"class" : "checkbox-align"
				}
			},
			{
				field: "title",
				title: "이슈 제목",
				template: "<a href=\"javascript:myIssueDetail('#:issueNo#', '#:projNo#')\" class='text-dark d-inline-block text-truncate' style='max-width:240px;'>#:title#</a>",
				headerAttributes: {style: 'text-align: center'},
				width: 245,
				encoded: false
			},
			{
				field: "important",
				title: "중요도",
				template: "<span id='badge_success' class='badgeTemplate badge_success text-dark' style='text-align:center;'>#=important#</span>",
				attributes: {style: 'text-align: center'},
				headerAttributes: {style: 'text-align: center'},
				width: 80,
				editable: false
			},
			{
				field: "userId",
				title: "작성자",
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#:userId#</span>",
				headerAttributes: {style: 'text-align: center'},
				width: 140
			},
			{
				field: "status",
				title: "진행상태",
				attributes: {style: 'text-align: center'},
				headerAttributes: {style: 'text-align: center'},
				width: 90
			},
			{
				field: "updatedate",
				title: "갱신일",
				format: "{0:yyyy-MM-dd}",
				attributes: {style: 'text-align: center'},
				headerAttributes: {style: 'text-align: center'},
				width: 110
			}
		]
	});
	
	var issueGrid = $("#myIssueGrid").data("kendoGrid");
	issueGrid.setOptions({
		batch: true,
	    autoSync: false,
	    height: "100%",
	    numeric: true,
	    sortable: true,
	    navigatable: true,
	    resizable: true,
	    groupable: true
	});
			
	issueGridFlag = true;
	
	var myIssueDropdown = myIssueTable.find("#myIssueProject-category").kendoDropDownList({
		dataSource: {
			type: "json",
	        transport: {
	        	read: "/app/issue/getIssueSortByUserId?userId=" + sessionId
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
				myIssueTable.data("kendoGrid").dataSource.filter({ field: "projTitle", operator: "eq", value: value });
			} else {
				myIssueTable.data("kendoGrid").dataSource.filter({});
			}
		}
	});
	
	$("#myIssueGrid").find(".k-grid-header-wrap").css("border-right", "none");
}
//이슈 끝

function readMileIssue(){
	getDefaultTemplate('issueListTemplate','issue-content');
	setTimeout(readIssue,200);
	setTimeout(readMile,200);
}
