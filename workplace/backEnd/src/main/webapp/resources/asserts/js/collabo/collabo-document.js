var CPROJ_NO = getParameterByName("cprojNo");
// 하드코딩

var cprojNo = CPROJ_NO;
var documentFlag = false;

function readDocument(){
		if(documentFlag == true){
			$("#filemanager").empty();
		}
		var baseUrl = "/document/";
		var kendo = window.kendo, extend = $.extend, Class = kendo.Class;

		var Command = Class.extend({
				     init: function (options) {
				         this.options = options;
				         this.filemanager = options.filemanager;
				     }
				 });
		var DownloadCommand = Command.extend({
				     init: function (options) {
				         Command.fn.init.call(this, options);
				     },

				     exec: function () {
							var that = this, target = that.options.target, filemanager = that.filemanager,
				              item = filemanager._view.widgetComponent.dataItem(target);

								location.href=baseUrl+'Download?path='+item.path;

					  if (!item) {
				            var uid = target.data('uid');
				            item = that.filemanager.treeView.widgetComponent.dataSource.getByUid(uid);
				         }
				       if (item.isDirectory) {
				       	console.error("Cannot download directory");
				         return
				       }
				       console.log("Downloading " + item.path);
				     }
				 });

				extend(kendo.ui.filemanager.commands, {DownloadCommand: DownloadCommand});
				kendo.ui.filemanager.ContextMenu.fn.events.push("open");

				 var filemanager = $("#filemanager").kendoFileManager({
		           dataSource: {
		        	   transport: {
		        			read: {
		        				url: baseUrl+"readCollaboDoc",
		        				method: "POST",
		        				data: {"cprojNo":CPROJ_NO},
		        				success:function(result) {
		        					options.success(result);
		        				}
		        			},
		        	             update: {
		        	            	url: baseUrl+"Update",
		        	     			method: "POST",
		        	     			data: {"cprojNo":CPROJ_NO},
		        	     			success:function(result) {
		        	     				options.success(result);
		        	     			}
		        	             },
		        	             create:  {
		        	            	url: baseUrl+"Create",
		        	     			method: "POST",
		        	     			data: {"cprojNo":CPROJ_NO},
		        	     			success:function(result) {
		        	     				options.success(result);
		        	     			}
		        	             },
		        	             destroy: {
		        	            	url: baseUrl+"Destroy",
		        	     			method: "POST",
		        	     			data: {"cprojNo":CPROJ_NO},
		        	     			success:function(result) {
		        	     				options.success(result);
		        	     			}
		        	             }
		        	    }
		           },
		           uploadUrl: baseUrl+"Upload?CPROJ_NO="+cprojNo,
		           toolbar: {
		               items: [
		                   { name: "createFolder" },
		                   { name: "upload" },
		                   { name: "sortDirection" },
		                   { name: "sortField" },
		                   { name: "changeView" },
		                   { name: "spacer" },
		                   { name: "details" },
		                   { name: "search" }
		               ]
		           },
		           contextMenu: {
		           	 open: function(ev){
		    	          	var downloadMenu = ev.sender.element.find(".k-i-download").closest(".k-item");
		    	            var isFolder = !!$(ev.target).find(".k-i-folder").length;

		    	            ev.sender.enable(downloadMenu, !isFolder);
		    	          },

		               items: [
		               	  {
		         	            name: "download",
		         	            text: "다운로드",
		         	            spriteCssClass: "k-icon k-i-download",
		         	          	command: "DownloadCommand"
		         	          },
		                   { command: "rename", text:"이름변경"},
		                   { command: "delete", text:"삭제" }
		               ]
		           },

		           model: {
		               id: "path",
		               hasChildren: "hasDirectories",
		               fields: {
		               	DOC_NO: {editable: true, type: "String"},
		                   name: {editable: true, type: "String", defaultValue: "New Folder" },
		                   size: {editable: false, type: "Number"},
		                   path: {editable: false, type: "String"},
		                   extension: {editable: false, type: "String"},
		                   isDirectory: {editable: false, defaultValue: true, type: "Boolean"},
		                   hasDirectories: {editable: false, defaultValue: false, type: "Boolean"},
		                   created: { from: "created", type: "Date", editable: false},
		                   createdUtc: { from: "createdUtc", type: "Date", editable: false },
		                   modified: { from: "modified", type: "Date", editable: false},
		                   modifiedUtc: { from: "modifiedUtc", type: "Date", editable: false }
		               }
		           },

		           draggable: true,
		           resizable: true

		   }).data("kendoFileManager");

				 documentFlag = true;
	}

// url 파라미터값 가져오기
function getParameterByName(name) {
  	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
   	results = regex.exec(location.search);
   	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}