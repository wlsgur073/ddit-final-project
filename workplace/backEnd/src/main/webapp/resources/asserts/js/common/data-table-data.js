import data from "./table_data.js";

// Korean
var lang_kor = {
  decimal: "",
  emptyTable: "데이터가 없습니다.",
  info: "_START_ - _END_ (총 _TOTAL_ 개)",
  infoEmpty: "0개",
  infoFiltered: "(전체 _MAX_ 개 중 검색결과)",
  infoPostFix: "",
  thousands: ",",
  lengthMenu: "_MENU_ <span>개씩 보기</span>",
  loadingRecords: "로딩중...",
  processing: "처리중...",
  search: "<span>검색</span>",
  zeroRecords: "검색된 데이터가 없습니다.",
  paginate: {
    first: "첫 페이지",
    last: "마지막 페이지",
    next: "다음",
    previous: "이전",
  },
  aria: {
    sortAscending: " :  오름차순 정렬",
    sortDescending: " :  내림차순 정렬",
  },
};


$(document).ready(function () {
	
	var datas;
	if($('#project-table')) {
		datas = data[0].project;
	}
	if($('#mytask-table')) {
		datas = data[0].mytask;
	}
	
	var columns = datas[0].column;
	const content = datas[1].content;
	
	var table = $('table[name="data-table-form"').DataTable({
    data: content,
    columns: columns,
    language: lang_kor,
    dom: '<"custom-list-top"B<"custom-list-filter"lf>>t<"custom-list-bottom"ip><"clear">',
    buttons: {
      name : 'tools',
      buttons : [
        "copy",
        "pdf",
        "excel",
      ],
      
    }
  });

  /* Column별 검색기능 추가 */
  $('.dataTables_wrapper .row div').removeClass('col-sm-6').addClass('filter-bar-probada')
  $("#project-table_filter").prepend(
    '<select id="select" name="status" class="form-control input-lg dataTables_filter"></select>'
  );
  $.each(sort_data, function (index, item) {
    $("#select").append("<option name='option_status'>" + item.title + "</option>");
  });

  $('#select').on('change', function () {
    var colName = $('#select option:selected').val();
    table.column(3).search(colName).draw();
  });

});

// 적용해야하는 스타일
// <!-- project datatable 관련 css -->
// <style>
//     .project-name {
//       color: black;
//     }
//      #example_wrapper .row:first-child{
//       display: flex;
//       justify-content: end;
//     }
//     #example_filter {
//       display: flex;
//     }
//     select#select{
//       margin-right: 15px;
//     }
//     div#example_length {
//       margin-right: 15px;
//   } 
//   .custom-list-top {
//     display: flex;
//     justify-content: space-between;
// }
// .custom-list-filter {
//     display: flex;
// }
// </style>