// url 파라미터값 가져오기
function getParameterByName(name) {
  	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
   	results = regex.exec(location.search);
   	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
window.onload = function() {
	var cprojNo = getParameterByName("cprojNo");
	console.log(cprojNo);

}

//콜라보 제안 modal 함수
function invite_go(){
	let userToCproj = document.getElementById('tags_1').value;

	let myProj = document.getElementById('selectOwnProject').value;

	let myProjNo = $('#selectOwnProject option:selected').attr('idxno');
	console.log("myProjNo =>" + myProjNo);
	let otherProj = document.getElementById('selectOtherProject').value;

	let otherProjNo = $('#selectOtherProject option:selected').attr('idxno');
	console.log("otherProjNo =>" + otherProjNo);


	let text = document.getElementById('sendMessage').value;

	let userFromCproj = document.getElementById('CollaboUserFrom').value;
	
	console.log("userToCproj =>" + userToCproj + "userFromCproj =>" + userFromCproj);

	if (userToCproj == "") {
		alert("초대할 유저를 입력하세요.")
		return false;
	}
	if (myProj == "") {
		alert("프로젝트를 선택하세요.")
		return false;
	}
	if (text == "") {
		alert("메세지를 입력하세요.")
		return false;
	}

	let title = document.getElementById('CollboMailTitle').value + "제안 메일입니다.";
	console.log("title =>" + title);
	
	let content = inviteMailForm(userFromCproj,userToCproj,myProj,text,myProjNo,otherProj,otherProjNo);

	document.getElementById('collaboContent').value = content;
	console.log(document.getElementById('collaboContent').value);

	document.getElementById('CollboMailTitle').value = title;
	
	document.collaboMailRegist.submit();
}



function inviteMailForm(userFromCproj,userToCproj,myProj,text,myProjNo,otherProj,otherProjNo) {

	//alert("메일 안에서 프로젝트 번호" + myProjNo);
	//alert("메일 안에서 프로젝트 번호" + otherProjNo);
	//alert("내꺼 프로젝트 번호" + myProjNo);
	//alert("다른사람 프로젝트 번호" + otherProjNo);


	let url = "/app/project/main?projNo="
	var content = ""
	content +=
		`<div class="x_content">

    <table class="table collabo table-hover">
      <tbody>
        <tr>
          <th class="collabo-th" style="width: 170px; border: none;">보낸 사람</th>
          <td class="collabo-td" style="border: none;"><a class="collaboMail-a" id="cprojReplyUserFrom" href="">`+ userFromCproj +`</a></td>
          <input type="hidden" id="cprojReplyUserTo" name="userFrom" value="`+userToCproj+`">
        </tr>
        <tr>
          <th class="collabo-th" style="border: none;">보낸 사람의 프로젝트</th>
          <td class="collabo-td" style="border: none;"><a class="collaboMail-a" class="userToProj" idx="`+myProjNo+`"
              href="`+ url+myProjNo+`">`+ myProj +`</a></td>
          <input type="hidden" id="idxNoTo" value="`+myProjNo+`">
        </tr>
        <tr>
          <th class="collabo-th" style="border: none;">콜라보 제안 프로젝트</th>
          <td class="collabo-td" style="border: none;"><a class="collaboMail-a" class="userFromProj" idx="`+otherProjNo+`"
              href="`+ url+otherProjNo+`">`+ otherProj +`</a></td>
          <input type="hidden" id="idxNoFrom" value="`+otherProjNo+`">
        </tr>
        <tr>
          <th class="collabo-th" style="border: none;">메세지</th>
          <td class="collabo-td" style="border: none;">`+ text +`</td>
        </tr>
      </tbody>
    </table>

    <div class="ln_solid"></div>
    <div class="item form-group-collabo">
      <div class="col-md-6 col-sm-6 offset-md-3">
        <button class="btn btn-primary" type="button" onclick="acceptCollabo()">수락</button>
        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#refuseCollabo">거절</button>
      </div>
    </div>

</div>`

	return content;
}


//나의 프로젝트 셀렉트 함수
function createCollabo() {
	$.ajax({
		url : "/app/collabo/getProjectTitleCollabo.do",
		type : "POST",
		success : function(arg) {
			console.log("나의 프로젝트 arg => " + arg)
			let projTitle = "<option value='' disabled selected hidden>프로젝트를 선택해주세요.</option>";

			for (var i = 0; i < arg.length; i++) {
				console.log("arg[i] => " + arg[i].title + arg[i].projNo);
				projTitle += "<option class='projNoIdx' idxNo='"+arg[i].projNo+"' value='"+arg[i].title+"'>"+arg[i].title+"</option>";
			}
			document.getElementById('selectOwnProject').innerHTML= projTitle;
		},
		error : function(arg) {
			alert("에러" + arg.status + "메세지" + arg.responseText);
		}
	})
};

//상대방의 프로젝트 셀렉트 함수
function selectOtherProj(){
	if (window.event.keyCode == 13) {
		//alert("입력한 ID 값의 프로젝트 출력 함수 실행");
		let userName = document.getElementById('tags_1').value;
		document.getElementById("name1").innerHTML = userName;

		 $.ajax({
			url : "/app/collabo/getProjectTitleOther.do",
			type : "POST",
			data : {"nickname" : userName},

			success : function(arg) {
				console.log("arg => " + arg)
				let projTitle = "<option value='' disabled hidden>프로젝트를 선택해주세요.</option>";

				for (var i = 0; i < arg.length; i++) {
					console.log("콜라보 Other arg[i] => " + arg[i].title + arg[i].projNo);

					projTitle += "<option class='projNoIdx' idxNo='"+arg[i].projNo+"' value='"+arg[i].title+"'>"+arg[i].title+"</option>";
				}
				document.getElementById('selectOtherProject').innerHTML= projTitle;
			},
			error : function(arg) {
				alert("에러" + arg.status + "메세지" + arg.responseText);
			}

		})
  }
};

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

        function myCollaboGantt() {
        	   $("#collaboGantt").kendoGantt({
        	      toolbar:[
        	         { template: "<a class='k-button' href='javascript:overlay(\"업무\");'>업무 등록</a>" },
        	          { name: "pdf" }
        	      ],
        	       views: [
        	             "day", // A view configuration can be a string (the view type) or an object (the view configuration).
        	             { type: "week", selected: true }, // The Week view will appear as initially selected.
        	             "month"
        	           ],
        	      dataSource: [
        	         {
        	            id: 1,
        	            orderId: 0,
        	            parentId: null,
        	            title: "업무페이지 구현",
        	            start: new Date("2022/1/10 1:00"),
        	            end: new Date("2022/1/11 24:00")
        	         },
        	         {
        	            id: 2,
        	            orderId: 0,
        	            parentId: null,
        	            title: "이슈페이지 구현",
        	            start: new Date("2022/1/11 1:00"),
        	            end: new Date("2022/1/12 24:00")
        	         },
        	         {
        	            id: 3,
        	            orderId: 0,
        	            parentId: null,
        	            title: "간트차트 구현",
        	            start: new Date("2022/1/12 1:00"),
        	            end: new Date("2022/1/13 24:00")
        	         },
        	         {
        	            id: 4,
        	            orderId: 0,
        	            parentId: null,
        	            title: "상세페이지 구현",
        	            start: new Date("2022/1/13 2:00"),
        	            end: new Date("2022/1/14 24:00")
        	         },
        	         {
        	            id: 5,
        	            orderId: 0,
        	            parentId: null,
        	            title: "등록페이지 구현",
        	            start: new Date("2022/1/13 1:00"),
        	            end: new Date("2022/1/15 24:00")
        	         },
        	         {
        	            id: 5,
        	            orderId: 0,
        	            parentId: null,
        	            title: "중간발표 준비",
        	            start: new Date("2022/1/17 1:00"),
        	            end: new Date("2022/1/18 24:00")
        	         },
        	         ],
        	         width:"100%",
        	         height:700,
        	         snap: false,
        	   })
        	   var collaboGantt = $("#collaboGantt").data("kendoGantt");
        	   collaboGantt.setOptions({ listWidth: 300});

        	   myGantt = function() {}
        	}


