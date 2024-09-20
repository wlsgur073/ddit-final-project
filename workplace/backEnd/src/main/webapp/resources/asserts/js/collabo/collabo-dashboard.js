var projNo = getParameterByName("cprojNo");

function readCollaboDashboard() {

	$("#CollaboTaskDashboard")
			.kendoGrid(
					{
						dataSource : {
							type : "json",
							transport : {
								read : "/app/collabo/getTaskListByCprojNo?cprojNo="
										+ cprojNo
							},
							pageSize : 5,
							schema : {
								model : {
									fields : {
										taskNo : {
											type : "string"
										},
										title : {
											type : "string"
										},
										userId : {
											type : "string"
										},
										updatedate : {
											type : "date"
										}
									}
								}
							}
						},
						batch : true,
						noRecords : {
							template : function(e) {
								return "<h2>등록된 업무가 없습니다.</h2>";
							}
						},
						height : 247,
						scrollable : false,
						columns : [
								{
									field : "title",
									title : "제목",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'padding-top:3px;padding-bottom:3px;'
									},
									template : "<a href=\"javascript:myIssueDetail('#:taskNo#', '#:cprojNo#')\" class='text-dark d-inline-block text-truncate' style='max-width:150px;'>#:title#</a>",
									width : 200
								},
								{
									field : "userId",
									title : "작성자",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'padding-top:3px;padding-bottom:3px;'
									},
									template : "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#:userId#</span>",
									width : 160
								},
								{
									field : "updatedate",
									title : "갱신일",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									template : "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(updatedate, 'yyyy-MM-dd')#</span>",
									width : 120
								} ]
					});

	$("#cprojIssueDashboard")
			.kendoGrid(
					{
						dataSource : {
							type : "json",
							transport : {
								read : "/app/collabo/getIssueListByCprojNo?cprojNo="
										+ cprojNo
							},
							pageSize : 5,
							schema : {
								model : {
									fields : {
										issueNo : {
											type : "string"
										},
										title : {
											type : "string"
										},
										userId : {
											type : "string"
										},
										updatedate : {
											type : "date"
										}
									}
								}
							}
						},
						batch : true,
						noRecords : {
							template : function(e) {
								return "<h2>등록된 이슈가 없습니다.</h2>";
							}
						},
						height : 249,
						scrollable : false,
						columns : [
								{
									field : "title",
									title : "제목",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'padding-top:3px;padding-bottom:3px;'
									},
									template : "<a href=\"javascript:myIssueDetail('#:issueNo#', '#:projNo#')\" class='text-dark d-inline-block text-truncate' style='max-width:150px;'>#:title#</a>",
									width : 200
								},
								{
									field : "userId",
									title : "작성자",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'padding-top:3px;padding-bottom:3px;'
									},
									template : "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#:userId#</span>",
									width : 160
								},
								{
									field : "updatedate",
									title : "갱신일",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									template : "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(updatedate, 'yyyy-MM-dd')#</span>",
									width : 120
								} ]
					});
	// 이슈 끝

	$("#cprojectHistoryDashboard")
			.kendoGrid(
					{
						dataSource : {
							type : "json",
							transport : {
								read : "/app/myWork/getProjHistory?projNo="
										+ "999"
							},
							pageSize : 3,
							schema : {
								model : {
									fields : {
										msg : {
											type : "string"
										},
										url : {
											type : "string"
										},
										regDate : {
											type : "string"
										},
										projNo : {
											type : "string"
										},
										dist : {
											type : "string"
										},
										userId : {
											type : "string"
										},
										act : {
											type : "string"
										}
									}
								}
							}
						},
						batch : true,
						pageable : false,
						noRecords : {
							template : function(e) {
								return "<h2>히스토리가 존재하지 않습니다.</h2>";
							}
						},
						scrollable : false,
						columns : [
								{
									field : "msg",
									hidden : true
								},
								{
									field : "url",
									hidden : true
								},
								{
									field : "regDate",
									hidden : true
								},
								{
									field : "projNo",
									hidden : true
								},
								{
									field : "dist",
									hidden : true
								},
								{
									field : "userId",
									hidden : true
								},
								{
									field : "act",
									hidden : true
								},
								{
									template : kendo
											.template($(
													"#collaboHistoryDashboard-template")
													.html())
								} ]
					});
	$("#cprojectHistoryDashboard").find("thead").hide();

	$("#cprojectDocumentDashboard")
			.kendoGrid(
					{
						dataSource : {
							type : "json",
							transport : {
								read : "/document/getDashDocumentByProjNo?projNo="
										+ "999"
							},
							pageSize : 5,
							schema : {
								model : {
									fields : {
										projTitle : {
											type : "string"
										},
										name : {
											type : "string"
										},
										size : {
											type : "string"
										},
										created : {
											type : "date"
										},
										modified : {
											type : "date"
										}
									}
								}
							}
						},
						batch : true,
						noRecords : {
							template : function(e) {
								return "<h2>문서가 존재하지 않습니다.</h2>";
							}
						},
						scrollable : false,
						columns : [
								{
									field : "name",
									title : "문서명",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'padding-top:3px;padding-bottom:3px;'
									},
									template : "<span class='text-dark d-inline-block text-truncate' style='max-width:200px;'>#:name#</span>"
								},
								{
									field: "size",
									title: "파일크기 (KB)",
									headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
									attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
									template: "<span class='text-dark d-inline-block text-truncate float-right' style='max-width:100px;'>#:size# KB</span>",
									width: 110
								},
								{
									field : "modified",
									title : "갱신일",
									headerAttributes : {
										style : 'text-align:center;padding-top:3px;padding-bottom:3px;'
									},
									attributes : {
										style : 'text-align:center;width:120px;padding-top:3px;padding-bottom:3px;'
									},
									template : "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(modified, 'yyyy-MM-dd')#</span>"
								} ]
					});

}