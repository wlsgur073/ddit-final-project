var projNo = getParameterByName("projNo");
console.log(projNo);
var sessionRole = "";

function backToList(){
	document.getElementById(prevTab).click();
}

var prevTab = "";

$(function(){

	sessionRole = "";

	$.ajax({

		url : "/app/project/getUserRole",
		type : 'POST',
		data : {"projNo":projNo},
		success : function(data) {
			console.log("Session Role 취득완료 =>" + data);
			sessionRole = data;
		}, // success
		error : function(xhr, status) {
			console.log("Session Role 취득실패");
		}
	});

})


function getDefaultTemplate(templateId,appendTarget){
	var formTemplate = document.getElementById(templateId).innerText;
	var bindTemplate = Handlebars.compile(formTemplate);
	var appe = document.getElementById(appendTarget);
	var html = bindTemplate();
	appe.innerHTML = html;
	$("#fadeInContent").fadeIn(300);
}

function getTemplate(url,templateId,appendTarget) {

	$.ajax({
	    type : 'GET',
	    url : url,
	    dataType : "JSON",
	    data : {"projNo" : projNo },
	    success : function(data) {
	    	console.log("Handlebars success!!");
	    	var formTemplate = document.getElementById(templateId).innerText;
	    	var bindTemplate = Handlebars.compile(formTemplate);
	    	var appe = document.getElementById(appendTarget);
	    	var html = bindTemplate(data);
	    	appe.innerHTML = html;
	    },
	    error : function(error) {
	    	console.log("Handlebars error!!");
	    },
	});
}

function getTaskTemplate(url,taskNo,templateId,appendTarget) {

	var formTemplate = "";
	var bindTemplate = "";
	var appe = "";
	var html = "";
	var data = "";

	$.ajax({
	    type : 'GET',
	    url : url,
	    dataType : "JSON",
	    data : {"taskNo" : taskNo,"projNo" : projNo },
	    success : function(data) {
	    	console.log("Handlebars success!!");
	    	formTemplate = document.getElementById(templateId).innerText;
	    	console.log(formTemplate)
	    	bindTemplate = Handlebars.compile(formTemplate);
	    	appe = document.getElementById(appendTarget);
	    	html = bindTemplate(data);
	    	console.log(html);

	    	appe.innerHTML = html;
	    	document.getElementById('taskDetail-tab').click();
	    },
	    error : function(error) {
	    	console.log("Handlebars error!!");
	    },
	});
}


function getTaskTemplate(url,taskNo,templateId,appendTarget) {

	prevTab = getCookie('projTab');

	var formTemplate = "";
	var bindTemplate = "";
	var appe = "";
	var html = "";
	var data = "";

	$.ajax({
	    type : 'GET',
	    url : url,
	    dataType : "JSON",
	    data : {"taskNo" : taskNo,"projNo" : projNo },
	    success : function(data) {
	    	console.log("Handlebars success!!");
	    	formTemplate = document.getElementById(templateId).innerText;
	    	console.log(formTemplate)
	    	bindTemplate = Handlebars.compile(formTemplate);
	    	appe = document.getElementById(appendTarget);
	    	html = bindTemplate(data);
	    	console.log(html);

	    	appe.innerHTML = html;
	    	document.getElementById('taskDetail-tab').click();
	    },
	    error : function(error) {
	    	console.log("Handlebars error!!");
	    },
	});
}
function getIssueTemplate(url,issueNo,templateId,appendTarget) {

	prevTab = 'issue-tab';


	var formTemplate = "";
	var bindTemplate = "";
	var appe = "";
	var html = "";
	var data = "";

	$.ajax({
	    type : 'GET',
	    url : url,
	    dataType : "JSON",
	    data : {"issueNo" : issueNo,"projNo" : projNo },
	    success : function(data) {
	    	console.log("Handlebars success!!");
	    	var formTemplate = document.getElementById(templateId).innerText;
	    	var bindTemplate = Handlebars.compile(formTemplate);
	    	var appe = document.getElementById(appendTarget);
	    	var html = bindTemplate(data);
	    	appe.innerHTML = html;
	    	document.getElementById('issueDetail-tab').click();
//	    	history.pushState(issueNo,'/app/project/issue?projNo='+projNo+'&issueNo'+issueNo);
	    	getIssueReplyTemplate("/app/issueReply/getIssueReplyByIssueNo",issueNo,"issueReplyForm","issueReplyFormTarget")
	    },
	    error : function(error) {
	    	console.log("Handlebars error!!");
	    },
	});
}

function getIssueReplyTemplate(url,issueNo,templateId,appendTarget) {

	var issueVO = {"issueNo" : issueNo,"projNo" : projNo }

	$.ajax({
		type : 'GET',
		url : url,
		dataType : "JSON",
		data : issueVO,
		success : function(data) {
			console.log("Handlebars success!!");
			var formTemplate = document.getElementById(templateId).innerText;
			var bindTemplate = Handlebars.compile(formTemplate);
			var appe = document.getElementById(appendTarget);
			var html = bindTemplate(data);
			appe.innerHTML = html;
		},
		error : function(error) {
			console.log("Handlebars error!!");
		},
	});
}


Handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {

    switch (operator) {
        case '==':
            return (v1 == v2) ? options.fn(this) : options.inverse(this);
        case '===':
            return (v1 === v2) ? options.fn(this) : options.inverse(this);
        case '!=':
            return (v1 != v2) ? options.fn(this) : options.inverse(this);
        case '!==':
            return (v1 !== v2) ? options.fn(this) : options.inverse(this);
        case '<':
            return (v1 < v2) ? options.fn(this) : options.inverse(this);
        case '<=':
            return (v1 <= v2) ? options.fn(this) : options.inverse(this);
        case '>':
            return (v1 > v2) ? options.fn(this) : options.inverse(this);
        case '>=':
            return (v1 >= v2) ? options.fn(this) : options.inverse(this);
        case '&&':
            return (v1 && v2) ? options.fn(this) : options.inverse(this);
        case '||':
            return (v1 || v2) ? options.fn(this) : options.inverse(this);
        default:
            return options.inverse(this);
    }
});

Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});

Handlebars.registerHelper('getRoleCheck', function (role, options) {
	var flag = (sessionRole == role) ? options.fn(this) : options.inverse(this);
	console.log(flag);
    return flag;
});

Handlebars.registerHelper('getRoleCheckReverse', function (role, options) {
	var flag = (sessionRole != role) ? options.fn(this) : options.inverse(this);
	console.log(flag);
	return flag;
});



function getParameterByName(name) {
  	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
   	results = regex.exec(location.search);
   	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


$(document).ready(function(){
	var endPoint = window.location.search;

	var from = getParameterByName('from');

	if(from === "search"){
		document.getElementById('detail-tab').click();
		console.log('hellooooooooooooooooooooooooo');
		return;
	} else if(getCookie('projTab') && getCookie('projNo') == projNo){
		var curTab = getCookie('projTab');
		curTab = curTabCheck(curTab);
		document.getElementById(curTab).click();
	} else if(!getCookie('projTab')) {
		document.getElementById('home-tab').click();
	} else if(getCookie('projNo') != projNo){
		document.getElementById('home-tab').click();
	}


})

$('a[role="tab"]').on('click', function() {
		var id = this.id
		document.cookie = "projTab="+id;
		document.cookie = "projNo="+projNo;
	})
// 상세 탭일경우 리스트로 돌려줌 (각 상세폼에 추가)
function curTabCheck(curTab){
	if(curTab == 'taskDetail-tab'){
		curTab = 'task-tab';
	} else if (curTab == 'issueDetail-tab'){
		curTab = 'issue-tab';
	}
		return curTab;
}

function getCookie(name) {
	  let matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
	}

const delCookie = function delCookie_by_name(name){
    let date = new Date();
    date.setDate(date.getDate() - 100);
    let Cookie = `${name}=;Expires=${date.toUTCString()}`
    document.cookie = Cookie;
}