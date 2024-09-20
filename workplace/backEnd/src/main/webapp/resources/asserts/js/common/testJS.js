// 업무 시작
function go(url) {
	location.href=url;
}

 var cardsData = [
            { id: 1, title: "업무페이지", order: 1, description: "업무페이지를 구현", status: "todo", color: "orange" },
            { id: 2, title: "이슈페이지", order: 2, description: "이슈페이지를 구현", status: "todo", color: "blue" },
            { id: 3, title: "간트차트", order: 3, description: "간트차트를 구현", status: "todo", color: "green" },
            { id: 4, title: "마일스톤", order: 4, description: "마일스톤를 구현", status: "inProgress", color: "blue" },
            { id: 5, title: "문서관리", order: 5, description: "문서관리를 구현", status: "inProgress", color: "orange" },
            { id: 6, title: "히스토리", order: 6, description: "히스토리를 구현", status: "done", color: "green" },
            { id: 7, title: "상세페이지", order: 7, description: "상세페이지를 구현", status: "done", color: "green" },
            { id: 8, title: "등록페이지", order: 8, description: "등록페이지를 구현", status: "done", color: "blue" },
        ];

        $("#taskBoard").kendoTaskBoard({
        	template: $("#card-template").html(),
            columns: [
                { text: "예정", status: "todo" },
                { text: "진행중", status: "inProgress" },
                { text: "지연", status: "delay" },
                { text: "완료", status: "done" }
            ],
            toolbar: {
                items: [
                	{ template: "<button class='k-button' type='button' onclick='overlay(\"업무\")'>업무 등록</button>" },
                    "spacer",
                    "search"
                ]
            },
            dataSource: {
                data: cardsData,
                schema: {
                    model: {
                        id: "id",
                        fields: {
                            id: { type: "number" },
                            order: { type: "number", defaultValue: 0 },
                            title: { field: "title", defaultValue: "No title" },
                            description: { field: "description", validation: { required: true } },
                        }
                    }
                }
            },
            dataStatusField: "status",
            dataOrderField: "order",
            dataCategoryField: "color",
            height: 750,
            columnSettings: {
            	width:220,
                 buttons: []
              },
            resources: [
                {
                    field: "color",
                    dataSource: [
                        { value: "orange", color: "#ffa500" },
                        { value: "green", color: "#008000" },
                        { value: "blue", color: "#0000ff" }
                    ]
                }
            ]
	    });

// 업무 끝

// 이슈 시작
$(document).ready(function () {

    		var dataBase = [
    			  {
    				    "IssueNo": 1,
    				    "IssueName": "자바스크립트가 안되요",
    				    "IssueImportant":"높음",
    				    "MileName": "프로젝트 화면 검수",
    				    "UserNickName": "RealHyukPL",
    				    "IssueUpdatedate": "\/Date(1593561600000)\/",
    				    "IssueStatus":"진행중"
    				  },
    				  {
    				    "IssueNo": 2,
    				    "IssueName": "JSP가 안되요",
    				    "IssueImportant":"높음",
    				    "MileName": "내 업무 화면 수정작업",
    				    "UserNickName": "RealHyukPL",
    				    "IssueUpdatedate": "\/Date(1542361600000)\/",
    				    "IssueStatus":"진행중"
    				  },
    				  {
    				    "IssueNo": 3,
    				    "IssueName": "스타일이 이상해요",
    				    "IssueImportant":"낮음",
    				    "MileName": "콜라보 화면 설계",
    				    "UserNickName": "EnergyWookAA",
    				    "IssueUpdatedate": "\/Date(1593561600000)\/",
    				    "IssueStatus":"완료"
    				  },
    				  {
    				    "IssueNo": 4,
    				    "IssueName": "자바가 안되요",
    				    "IssueImportant":"낮음",
    				    "MileName": "프로젝트 화면 구현",
    				    "UserNickName": "RealHyukPL",
    				    "IssueUpdatedate": "\/Date(1594231600000)\/",
    				    "IssueStatus":"완료"
    				  },
    				  {
    				    "IssueNo": 5,
    				    "IssueName": "컴퓨터가 안되요",
    				    "IssueImportant":"높음",
    				    "MileName": "프로젝트 화면 구현",
    				    "UserNickName": "EnergyWookAA",
    				    "IssueUpdatedate": "\/Date(1511161600000)\/",
    				    "IssueStatus":"진행중"
    				  },
    				  {
    				    "IssueNo": 6,
    				    "IssueName": "다 안되요",
    				    "IssueImportant":"높음",
    				    "MileName": "프로젝트 화면 설계",
    				    "UserNickName": "EnergyWookAA",
    				    "IssueUpdatedate": "\/Date(1513331600000)\/",
    				    "IssueStatus":"진행중"
    				  },
    				  {
    				    "IssueNo": 7,
    				    "IssueName": "마우스가 안되요",
    				    "IssueImportant":"낮음",
    				    "MileName": "내 업무 화면 수정 및 검수",
    				    "UserNickName": "EnergyWookAA",
    				    "IssueUpdatedate": "\/Date(1511161600000)\/",
    				    "IssueStatus":"완료"
    				  },
    				  {
    				    "IssueNo": 8,
    				    "IssueName": "모니터가 안되요",
    				    "IssueImportant":"높음",
    				    "MileName": "기기관련",
    				    "UserNickName": "EnergyWookAA",
    				    "IssueUpdatedate": "\/Date(1511161600000)\/",
    				    "IssueStatus":"진행중"
    				  },
    				  {
    				    "IssueNo": 9,
    				    "IssueName": "배고파요",
    				    "IssueImportant":"높음",
    				    "MileName": "그 외",
    				    "UserNickName": "EnergyWookAA",
    				    "IssueUpdatedate": "\/Date(1511161600000)\/",
    				    "IssueStatus":"진행중"
    				  },
    				];
				var registBtn = kendo.template("<button type='button'>hello<button>")
             /*
				 * var crudServiceBaseUrl =
				 * "https://demos.telerik.com/kendo-ui/service", dataSource =
				 * new kendo.data.DataSource({ transport: { read: { url:
				 * crudServiceBaseUrl + "/detailproducts", dataType: "jsonp" },
				 * update: { url: crudServiceBaseUrl + "/detailproducts/Update",
				 * dataType: "jsonp" }, destroy: { url: crudServiceBaseUrl +
				 * "/detailproducts/Destroy", dataType: "jsonp" }, parameterMap:
				 * function (options, operation) { if (operation !== "read" &&
				 * options.models) { return { models:
				 * kendo.stringify(options.models) }; } } } , batch: true,
				 * pageSize: 20, autoSync: true, schema: { model: { id:
				 * "IssueNo", fields: { IssueNo: { type:"String", editable:
				 * false, nullable: true }, IssueName: { type:"String",
				 * editable: false, nullable: true }, IssueImportant: {
				 * type:"String", editable: false, nullable: true }, MileName: {
				 * type:"String", editable: false, nullable: true },
				 * UserNickName: { type:"String", editable: false, nullable:
				 * true }, IssueUpdatedate: { type: "date", editable: false },
				 * IssueStatus: { type:"String", editable: false, nullable: true }, } } }
				 * });
				 */
            $("#grid").kendoGrid({
                dataSource: {
                    data: dataBase,
                    schema: {
                        model: {
                            fields: {
                            	IssueName: { type: "string" },
                            	IssueImportant: { type: "string" },
                            	MileName: { type: "string" },
                            	UserNickName: { type: "string" },
                            	IssueUpdatedate: { type: "date"},
                            	IssueStatus: { type: "string" },
                            }
                        }
                    }
                },
                columnMenu: {
                    filterable: false
                },
                batch: true,
                pageSize: 10,
                autoSync: true,
                /*
				 * schema: { model: { id: "IssueNo", fields: { IssueNo: {
				 * type:"String", editable: false, nullable: true }, IssueName: {
				 * type:"String", editable: false, nullable: true },
				 * IssueImportant: { type:"String", editable: false, nullable:
				 * true }, MileName: { type:"String", editable: false, nullable:
				 * true }, UserNickName: { type:"String", editable: false,
				 * nullable: true }, IssueUpdatedate: { type: "date", editable:
				 * false }, IssueStatus: { type:"String", editable: false,
				 * nullable: true }, } } },
				 */
                height: 850,
                pageable: true,
                numeric: true,
                sortable: true,
                navigatable: true,
                resizable: true,
                reorderable: true,
                groupable: true,
                toolbar: [
                	{ template: "<a class='k-button' href='javascript:overlay(\"이슈\");'>이슈 등록</a>" }
                	,"search","excel", "pdf" ],
                columns: [{
                    selectable: false,
                    hidden:true,
                    width: 75,
                    attributes: {
                        "class": "checkbox-align",
                    },
                    headerAttributes: {
                        "class": "checkbox-align",
                    }
                },  {
                    field: "IssueName",
                    title: "이슈 제목",
                    format: "{0:c}",
                    width: 200,
                    encoded: false,
                }, {
                    field: "IssueImportant",
                    title: "중요도",
                    template: "<span id='badge_success' class='badgeTemplate badge_success'>#=IssueImportant#</span>",
                    width: 100,
                    editable: false,
                }, {
                    field: "MileName",
                    title: "마일스톤",
                    /* editor: clientCategoryEditor, */
                    groupHeaderTemplate: "마일스톤: <a href='' style='color:black;'>#=data.value#</a> ",
                    width: 200
                },  {
                    field: "UserNickName",
                    title: "작성자",
                    width: 105
                }, {
                    field: "IssueUpdatedate",
                    title: "갱신일",
                    format: "{0:yyyy-MM-dd}",
                    width: 120
                },  {
                    field: "IssueStatus",
                    title: "진행상태",
                    width: 100
                }],
            });

        });

        /*
		 * function onDataBound(e) { var grid = this;
		 * grid.table.find("tr").each(function () { var dataItem =
		 * grid.dataItem(this); var themeColor = dataItem.Discontinued ? 'info' :
		 * 'error'; var text = dataItem.Discontinued ? '낮음' : '높음';
		 *
		 * $(this).find(".badgeTemplate").kendoBadge({ themeColor: themeColor,
		 * text: text, });
		 *
		 * $(this).find(".sparkline-chart").kendoSparkline({ legend: { visible:
		 * false }, data: [dataItem.TargetSales], type: "bar", chartArea: {
		 * margin: 0, width: 180, background: "transparent" }, seriesDefaults: {
		 * labels: { visible: true, format: '{0}%', background: 'none' } },
		 * categoryAxis: { majorGridLines: { visible: false }, majorTicks: {
		 * visible: false } }, valueAxis: { type: "numeric", min: 0, max: 130,
		 * visible: false, labels: { visible: false }, minorTicks: { visible:
		 * false }, majorGridLines: { visible: false } }, tooltip: { visible:
		 * false } });
		 *
		 * kendo.bind($(this), dataItem); }); }
		 */
        function returnFalse() {
            return false;
        }
/*
 * function clientCategoryEditor(container, options) { $('<input required
 * name="Category">') .appendTo(container) .kendoDropDownList({ autoBind: false,
 * dataTextField: "CategoryName", dataValueField: "CategoryID", dataSource: {
 * data: categories } }); }
 *
 * var categories = [{ "CategoryID": 1, "CategoryName": "프로젝트 화면 제작" }, {
 * "CategoryID": 2, "CategoryName": "Oracle DB 검수" }, { "CategoryID": 3,
 * "CategoryName": "내 작업 화면 제작" }, { "CategoryID": 4, "CategoryName": "콜라보
 * Controller 수정" }, { "CategoryID": 5, "CategoryName": "depth 수정" }, {
 * "CategoryID": 6, "CategoryName": "Bootstrap 버젼업" }, { "CategoryID": 7,
 * "CategoryName": "콜라보 화면 제작" }, { "CategoryID": 8, "CategoryName": "프로젝트 리스트
 * 수정" }];
 */
// 이슈 끝



// 간트차트
        // Import DejaVu Sans font for embedding

        // NOTE: Only required if the Kendo UI stylesheets are loaded
        // from a different origin, e.g. cdn.kendostatic.com
        kendo.pdf.defineFont({
            "DejaVu Sans": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans.ttf",
            "DejaVu Sans|Bold": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Bold.ttf",
            "DejaVu Sans|Bold|Italic": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
            "DejaVu Sans|Italic": "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
            "WebComponentsIcons": "https://kendo.cdn.telerik.com/2017.1.223/styles/fonts/glyphs/WebComponentsIcons.ttf"
        });




