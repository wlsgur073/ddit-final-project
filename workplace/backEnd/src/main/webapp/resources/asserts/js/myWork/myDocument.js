//내 문서 시작
function readMyDocument(userId){
	readMyDocument = function(){};

	var baseUrl = "/app/myWork/";
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

			 $("#filemanager").kendoFileManager({
	            dataSource: {
	            transport: {


	            	 read: function(options) {
	                     var that = this;

	                     $.ajax({
	                         url: baseUrl + "Read",
	                         dataType: "json",
	                         method: "POST",
	                         data: that.parameterMap ? that.parameterMap(options.data, "read") : options.data,

	                         success: function(result) {
	                             options.success(result);

	                         }
	                     });
	                 },
	                 update: function(options) {
	                	 var that = this;

	                     $.ajax({
	                         url: baseUrl + "Update",
	                         dataType: "json",
	                         method: "POST",
	                         data: that.parameterMap ? that.parameterMap(options.data, "read") : options.data,
	                         success: function(result) {

	                             options.success(result);

	                         }
	                     });
	                 },
	                 create: function(options) {
	                     var that = this;

	                     $.ajax({
	                         url: baseUrl + "Create",
	                         dataType: "json",
	                         method: "POST",
	                         data: that.parameterMap ? that.parameterMap(options.data, "read") : options.data,
	                         success: function(result) {
	                             options.success(result);



	                         }
	                     });

	                 },
	                 destroy: function(options) {
	                     var that = this;

	                     $.ajax({
	                         url: baseUrl + "Destroy",
	                         dataType: "json",
	                         method: "POST",
	                         data: that.parameterMap ? that.parameterMap(options.data, "read") : options.data,
	                         success: function(result) {
	                             options.success(result);

	                         }
	                     });
	                 }

	            }


	        },

	        uploadUrl: baseUrl+"Upload",
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
	          	            text: "Download",
	          	            spriteCssClass: "k-icon k-i-download",
	          	          	command: "DownloadCommand"
	          	          },
	                    { name: "rename" },
	                    { name: "delete" }
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

}
//내 문서 끝