//대시보드 시작
function readMyDashboard(userId, nickname){
	readMyDashboard = function(){};

	var sessionId = userId;
	
	//내 업무 시작
	myTaskChart(sessionId);
	//내 업무 끝
	
	//내 이슈 시작
	$("#myIssueDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/issue/getIssueListByUserId?userId=" + sessionId
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
				return "<h2 class='text-center' style='color:#73879c;'>이슈가 존재하지 않습니다.</h2>";
			}
		},
		scrollable: false,
		height: 250,
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
	//내 이슈 끝
	
	//내 메일 시작
	$("#myReceiveMailDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getReceiveMailList?userTo=" + nickname
			},
			pageSize: 5,
			schema: {
				model: {
					fields: {
						mailNo   : { type: "number" },
						title    : { type: "string" },
						userFrom : { type: "string" },
						regDate  : { type: "string" }
					}
				}
			}
		},
		batch: true,
		noRecords: {
			template: function(e) {
				return "<h2 class='text-center' style='color:#73879c;'>메일이 존재하지 않습니다.</h2>";
			}
		},
		scrollable: false,
		height: 250,
		columns: [
			{ 
				field: "title",
				title: "제목",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<a href=\"javascript:myMailGo('#:mailNo#')\" class='text-dark d-inline-block text-truncate' style='max-width:150px;'>#:title#</a>",
				width: 200
			},
			{ 
				field: "userFrom",
				title: "보낸사람",
				headerAttributes: { style: 'width:70;text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'width:70;padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#:userFrom#</span>",
				width: 160
			},
			{ 
				field: "regDate",
				title: "보낸날짜",
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'text-align:center;width:120px;padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(regDate, 'yyyy-MM-dd')#</span>"
			}
		]
	});
	//내 메일 끝
	
	//내 히스토리 시작
	$("#myHistoryDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/app/myWork/getHistory?userId=" + sessionId
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
				return "<h2 class='text-center' style='color:#73879c;'>히스토리가 존재하지 않습니다.</h2>";
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
	    	{ template: kendo.template($("#myHistoryDashboard-template").html()) }
	    ]
	});
	$("#myHistoryDashboard").find("thead").hide();
	//내 히스토리 끝

	//내 리퀘스트 시작
	/*var yourRequestDashboard = $("#yourRequestDashboard").kendoGrid({
		dataSource: {
	        type: "json",
	        transport: {
	        	read: "/app/myWork/getYourRequest?userTo=" + sessionId
	        },
	        pageSize: 2,
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
	        },
	        change: function() {
	        	readMyDashboard(sessionId);
			}
	    },
	    batch: true,
	    pageable: false,
		noRecords: {
			template: function(e) {
				return "<h2>리퀘스트가 존재하지 않습니다.</h2>";
			}
		},
	   	columns: [
	   		{ field: "reqNo"   , hidden: true },
	   		{ field: "userFrom", hidden: true },
	   		{ field: "userTo"  , hidden: true },
	   		{ field: "projNo"  , hidden: true },
	   		{ field: "title"   , hidden: true },
	   		{ field: "content" , hidden: true },
	   		{ field: "url"     , hidden: true },
	   		{ field: "status"  , hidden: true },
	   		{ field: "regDate" , hidden: true },
	   		{ field: "reqRes"  , hidden: true },
	   		{ template: kendo.template($("#yourRequest-template").html()) }
	  	]
	});
	
	$('#yourRequestDashboard').find('thead').hide();
	
	var myRequestDashboard = $("#myRequestDashboard").kendoGrid({
		dataSource: {
	        type: "json",
	        transport: {
	        	read: "/app/myWork/getMyRequest?userFrom=" + sessionId
	        },
	        pageSize: 2,
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
	    pageable: false,
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
	
	$('#myRequestDashboard').find('thead').hide();*/
	//내 리퀘스트 끝
	
	//내 문서 시작
	$("#myDocumentDashboard").kendoGrid({
		dataSource: {
			type: "json",
			transport: {
				read: "/document/getDocumentListByUserId?userId=" + sessionId
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
				return "<h2 class='text-center' style='color:#73879c;'>문서가 존재하지 않습니다.</h2>";
			}
		},
		scrollable: false,
		height: 250,
		columns: [
			{ 
				field: "projTitle", 
				title: "소속 프로젝트", 
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' }, 
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:180px;'>#:projTitle#</span>",
				width: 230 
			},
			{ 
				field: "name", 
				title: "문서명", 
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' }, 
				attributes: { style: 'padding-top:3px;padding-bottom:3px;' },
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:430px;'>#:name#</span>"
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
				field: "created", 
				title: "등록일", 
				headerAttributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;' },
				attributes: { style: 'text-align:center;padding-top:3px;padding-bottom:3px;'},
				attributes: { style: 'text-align:center;width:120px;padding-top:3px;padding-bottom:3px;'},
				template: "<span class='text-dark d-inline-block text-truncate' style='max-width:115px;'>#=kendo.toString(created, 'yyyy-MM-dd')#</span>"
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
	//내 문서 끝
}
function myMailGo(mailNo){
	document.getElementById('mail-tab').click();
	document.getElementById('mailReceive-tab').click();
	receiveMailDetail(mailNo);
}

function myTaskChart(sessionId){
	//내 업무 시작
	$.ajax({
		url     : "/app/myWork/getMyTaskChartByUserId", 
		type    : "GET",
		data    : {
			"userId" : sessionId
		},
		dataType: "json",
		success : function(data){
        	$("#canvasBox").empty();

        	var dataMsg = "<canvas id='myTaskChartDashboard' width='450' height='210'></canvas>";
        	$("#canvasBox").append(dataMsg);
			
			var data_doughnut = {
				labels: ['미배정', '진행중', '지연중', '완료'],
				datasets: [{
					data: [data.b201, data.b202, data.b203, data.b204],
					backgroundColor: ['#6c757d', '#007bff', '#ffc107', '#28a745'],
					hoverOffset: 4
				}]
			};
			var myTaskChartData = {
				type: 'doughnut',
				data: data_doughnut,
				options: {
					responsive: false,
					plugins: {
						legend: {
							position: 'right',
							align: 'center'
						}
			        }
				}
			};
			
			var myTaskChart = new Chart(
				document.getElementById('myTaskChartDashboard'),
				myTaskChartData
			);
        },
        error   : function(error){
			/*alert(error.status);*/
        	
		}
    });
	//내 업무 끝
}
