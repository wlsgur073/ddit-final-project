// url 파라미터값 가져오기
function getParameterByName(name) {
  	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
   	results = regex.exec(location.search);
   	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

var cprojNo = getParameterByName("cprojNo");
console.log(cprojNo);

//콜라보 리스트 데이터소스
const cprojDataSource = new kendo.data.DataSource({
      type: "json",
      transport: {
          read: "/app/collabo/getCollaboListByUserId"
      },
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
              }
          }
      },
      pageSize : 5
});

const cprojList = $("#collabo-list").kendoGrid({
	pageable:true,
	toolbar: [
		//{ template: "<a class='k-button' href='createCollabo' data-toggle='modal' onclick='createCollabo()'>콜라보 제안</a>" }
		{ template: "<a class='k-button' href=''  data-toggle='modal' data-target='\\#createCollabo' onclick='createCollabo()'>콜라보 제안</a>" }
		//{ template: "<a class='k-button' href='javascript:getOverlayTemplate(\"collaboRegistTypeTemplate\");'>콜라보 제안</a>" }
		,{ template: "<div class='k-spacer'>&nbsp;</div>"},"search",
	],
	messages: {
		commands: {
			search: "검색"
		}
	},
    columns: [
    	{ field: "title", hidden:true},
    	{ field: "status",hidden:true },
		{ field: "startdate",hidden:true},
		{
			template: $("#collaboCardTemplate").html(),
		},
	],
	dataSource: cprojDataSource
});

function xssPurify(html) {
    const extractTextPattern = /(<([^>]+)>)/gi;
    var newhtml = html.replace(extractTextPattern, "");
    console.log("old" + html);
    console.log("new" + newhtml);
    return newhtml;
}