var projNo = getParameterByName("projNo");

function readProjDashboard (){

	$("#projTaskDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/task/getTaskListByProjNo?projNo=" + projNo
			},
			pageSize: 5,
			schema: {
				model: {
					fields: {
						taskNo    : { type: "string" },
						title      : { type: "string" },
						userId     : { type: "string" },
						updatedate : { type: "date"   }
					}
				}
			}
		},
		batch: true,
		noRecords: {
			template: function(e) {
				return "<h2>등록된 업무가 없습니다.</h2>";
			}
		},
		height:247,
		scrollable: false,
		columns: [
			{
				field: "title",
				title: "제목" ,
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<a href=\"javascript:myIssueDetail('#:taskNo#', '#:projNo#')\" class='text-dark d-inline-block text-truncate' style='max-width:150px;'>#:title#</a>",
				width: 200
			},
			{
				field: "userId",
				title: "작성자",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#:userId#</span>",
				width: 160
			},
			{
				field: "updatedate",
				title: "갱신일",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(updatedate, 'yyyy-MM-dd')#</span>",
				width: 120
			}
		]
	});

	$("#projIssueDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/issue/getIssueListByProjNo?projNo=" + projNo
			},
			pageSize: 5,
			schema: {
				model: {
					fields: {
						issueNo    : { type: "string" },
						title      : { type: "string" },
						userId     : { type: "string" },
						updatedate : { type: "date"   }
					}
				}
			}
		},
		batch: true,
		noRecords: {
			template: function(e) {
				return "<h2>등록된 이슈가 없습니다.</h2>";
			}
		},
		height:249,
		scrollable: false,
		columns: [
			{
				field: "title",
				title: "제목" ,
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<a href=\"javascript:myIssueDetail('#:issueNo#', '#:projNo#')\" class='text-dark d-inline-block text-truncate' style='max-width:150px;'>#:title#</a>",
				width: 200
			},
			{
				field: "userId",
				title: "작성자",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#:userId#</span>",
				width: 160
			},
			{
				field: "updatedate",
				title: "갱신일",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(updatedate, 'yyyy-MM-dd')#</span>",
				width: 120
			}
		]
	});
	//이슈 끝

	$("#projectHistoryDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getProjHistory?projNo=" + projNo
			},
			pageSize: 3,
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
	    pageable: false,
		noRecords: {
			template: function(e) {
				return "<h2>히스토리가 존재하지 않습니다.</h2>";
			}
		},
		scrollable: false,
	    columns: [
	    	{ field: "msg",     hidden:true },
	    	{ field: "url",     hidden:true },
	    	{ field: "regDate", hidden:true },
	    	{ field: "projNo",  hidden:true },
	    	{ field: "dist",    hidden:true },
	    	{ field: "userId",  hidden:true },
	    	{ field: "act",     hidden:true },
	    	{ template: kendo.template($("#projectHistoryDashboard-template").html()) }
	    ]
	});
	$("#projectHistoryDashboard").find("thead").hide();

	$("#projectDocumentDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/document/getDashDocumentByProjNo?projNo=" + projNo
			},
			pageSize: 5,
			schema: {
				model: {
					fields: {
						projTitle : { type: "string" },
						name      : { type: "string" },
						size      : { type: "string" },
						created   : { type: "date"   },
						modified  : { type: "date"   }
					}
				}
			}
		},
		batch: true,
		noRecords: {
			template: function(e) {
				return "<h2>문서가 존재하지 않습니다.</h2>";
			}
		},
		scrollable: false,
		columns: [
			{
				field: "name",
				title: "문서명",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:200px;'>#:name#</span>"
			},
			{
				field: "size",
				title: "파일크기",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: function(dataItem){

					if(dataItem.size > 1024){
						var size = Math.floor(dataItem.size / 1024);
						var exten = "KB";
							if (size > 1024){
								size = Math.floor(size / 1024);
								exten = "MB"
							}
					}

					return "<span class='text-dark d-inline-block text-truncate' style='max-width:100px;'>"+ size + exten +"</span>";
				},
				width: 110
			},
			{
				field: "modified",
				title: "갱신일",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'text-align:center;width:120px;padding-top:3px;padding-bottom:3px;'},
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(modified, 'yyyy-MM-dd')#</span>"
			}
		]
	});

}