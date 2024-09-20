/**
 * html 태그를 지워줌
 * @param html
 * @returns
 */
function xssPurify(html) {
	const extractTextPattern = /(<([^>]+)>)/gi;
	var newhtml = html.replace(extractTextPattern, "");
	console.log("old" + html);
	console.log("new" + newhtml);
	return newhtml;
}


function getDropzone() {

	console.log('hello?');

	var fileDropzone = new Dropzone('#fileDropzone', {
		url:"/document/Upload",
		method: "POST",
		addRemoveLinks: false,
	    clickable: true,
		init: function ()  {
        	console.log("hello!")
        }
	})

	Dropzone.options.fileDropzone = {
            // 설정해주면 파일 드래그앤드랍했을때 파일 아래에 remove link가 나타난다.
            addRemoveLinks: true,
            // 허용할 파일확장자
            acceptedFiles: ".xlsx",
            // 파일 업로드 최대 갯수
            maxFiles: 3,
            // 자동전송여부 -> true로 설정하면 드래그앤드랍시 바로 컨트롤러 호출하므로 false!
            autoProcessQueue : false,
            // 허용 사이즈 10 -> 10MB
            maxFilesize: 10,
            init: function ()  {
            	// error 발생 시 alert 호출 ( 파일용량초과, 업로드 갯수, 확장자 등등..)
                this.on("error", function (file, message) {
                    alert(message);
                    this.removeFile(file);
                });

                let myDropzone = this;
                $("#processUpload").click(function (e) {
                	// Controller 호출 진행
                    myDropzone.processQueue();
                });
            },
            // Controller 호출 전 파일명 변경해서 전송!
            renameFilename: function (filename) {
                name  = "test.xlsx";
                return name;
            },
            // Controller Response를 받아 분기 처리
            success: function(file, response) {
                if(response == 'success') {
                    alert('등록 되었습니다.');
                } else {
                    alert('등록 실패.')
                }
                location.reload();
            }
        };

}
