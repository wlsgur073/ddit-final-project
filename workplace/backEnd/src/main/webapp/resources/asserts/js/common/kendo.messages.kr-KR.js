(function ($, undefined) {
  /* FlatColorPicker messages */

  if (kendo.ui.FlatColorPicker) {
    kendo.ui.FlatColorPicker.prototype.options.messages =
      $.extend(true, kendo.ui.FlatColorPicker.prototype.options.messages, {
        "apply": "적용",
        "cancel": "취소"
      });
  }

  /* ColorPicker messages */

  if (kendo.ui.ColorPicker) {
    kendo.ui.ColorPicker.prototype.options.messages =
      $.extend(true, kendo.ui.ColorPicker.prototype.options.messages, {
        "apply": "적용",
        "cancel": "취소"
      });
  }

  /* ColumnMenu messages */

  if (kendo.ui.ColumnMenu) {
    kendo.ui.ColumnMenu.prototype.options.messages =
      $.extend(true, kendo.ui.ColumnMenu.prototype.options.messages, {
        "sortAscending": "오름차순으로 정렬",
        "sortDescending": "내림차순으로 정렬",
        "filter": "필터",
        "columns": "열",
        "done": "완료",
        "settings": "열 설정",
        "lock": "잠금",
        "unlock": "잠금해제"
      });
  }

  if (kendo.ui.TreeList) {
	  kendo.ui.TreeList.prototype.options.messages =
	  $.extend(true, kendo.ui.TreeList.prototype.options.messages,{
	      "noRows": "등록된 내용이 없습니다.",
	      "loading": "준비중...",
	      "requestFailed": "리퀘스트 실패",
	      "retry": "재시도",
	      "commands": {
	          "edit": "편집",
	          "update": "업데이트",
	          "canceledit": "취소",
	          "create": "데이터 추가",
	          "createchild": "자식노드 추가",
	          "destroy": "삭제",
	          "excel": "엑셀로 내보내기",
	          "pdf": "PDF로 내보내기"
	      }
	  });
	  }


  /* FileBrowser messages */

  if (kendo.ui.FileBrowser) {
    kendo.ui.FileBrowser.prototype.options.messages =
      $.extend(true, kendo.ui.FileBrowser.prototype.options.messages, {
        "uploadFile": "업로드",
        "orderBy": "해당 요소로 정렬",
        "orderByName": "이름",
        "orderBySize": "사이즈",
        "directoryNotFound": "해당 이름의 디렉토리가 발견되지 않았습니다.",
        "emptyFolder": "빈 폴더",
        "deleteFile": "{0} 를 정말로 삭제하시겠습니까?",
        "invalidFileType": "선택된 파일은 {0} 사용할 수 없습니다. 지원하는 파일 확장자는 {1} 입니다.",
        "overwriteFile": "{0} 의 이름을 가진 파일은 이미 해당 디렉토리에 존재합니다. 덮어쓰겠습니까?",
        "dropFilesHere": "여기에 파일을 드롭하여 업로드",
        "search": "검색"
      });
  }

  if (kendo.ui.FileManager) {
  kendo.ui.FileManager.prototype.options.messages =
	  $.extend(true, kendo.ui.FileManager.prototype.options.messages, {
      toolbar: {
        createFolder: "새 폴더",
        upload: "업로드",
        sortDirection: "정렬",
        sortDirectionAsc: "오름차순 정렬",
        sortDirectionDesc: "내림차순 정렬",
        sortField: "정렬",
        nameField: "파일명",
        sizeField: "파일 크기",
        typeField: "파일타입",
        dateModifiedField: "수정일짜",
        dateCreatedField: "생성날짜",
        listView: "List View",
        gridView: "Grid View",
        search: "검색",
        details: "파일 정보보기",
        detailsChecked: "On",
        detailsUnchecked: "Off",
        delete: "삭제",
        rename: "파일명 수정",
      },
      views: {
        nameField: "파일명",
        sizeField: "파일 크기",
        typeField: "타입",
        dateModifiedField: "수정날짜",
        dateCreatedField: "생성날짜",
        items: "파일",
        listLabel: "리스트",
        gridLabel: "그리드",
        treeLabel: "트리",
      },
      dialogs: {
        upload: {
          title: "파일 업로드",
          clear: "리스트 지우기",
          done: "확인",
        },
        moveConfirm: {
          title: "컨펌",
          content:
            "<p style='text-align: center;'>이동 혹은 복사 하시겠습니까?</p>",
          okText: "복사",
          cancel: "이동",
          close: "닫기",
        },
        deleteConfirm: {
          title: "확인",
          content:
            "<p style='text-align: center;'>선택한 파일을 정말로 삭제 하시겠습니까??</p>",
          okText: "삭제",
          cancel: "취소",
          close: "닫기",
        },
        renamePrompt: {
          title: "수정",
          content:
            "<p style='text-align: center;'>엔터키를 누르면 수정이 가능합니다.</p>",
          okText: "수정",
          cancel: "취소",
          close: "닫기",
        },
      },
      previewPane: {
        noFileSelected: "파일을 선택하지 않았습니다.",
        extension: "타입",
        size: "사이즈",
        created: "생성 날짜",
        createdUtc: "생성 날짜",
        modified: "수정 날짜",
        modifiedUtc: "수정 날짜",
        items: "아이템",
      },
    });
  }

  /* FilterCell messages */

  if (kendo.ui.FilterCell) {
    kendo.ui.FilterCell.prototype.options.messages =
      $.extend(true, kendo.ui.FilterCell.prototype.options.messages, {
        "isTrue": "참",
        "isFalse": "거짓",
        "filter": "필터",
        "clear": "클리어",
        "operator": "연산자"
      });
  }

  /* FilterMultiCheck messages */

  if (kendo.ui.FilterMultiCheck) {
    kendo.ui.FilterMultiCheck.prototype.options.messages =
      $.extend(true, kendo.ui.FilterMultiCheck.prototype.options.messages, {
        "search": "검색"
      });
  }

  /* Gantt messages */

  if (kendo.ui.Gantt) {
    kendo.ui.Gantt.prototype.options.messages =
      $.extend(true, kendo.ui.Gantt.prototype.options.messages, {
        "actions": {
          "addChild": "하위업무 추가",
          "append": "업무 추가",
          "insertAfter": "아래에 추가",
          "insertBefore": "위에 추가",
          "pdf": "PDF로 내보내기"
        },
        "cancel": "취소",
        "deleteDependencyWindowTitle": "의존관계를 삭제",
        "deleteTaskWindowTitle": "업무 삭제",
        "destroy": "삭제",
        "editor": {
          "assingButton": "배정",
          "editorTitle": "업무",
          "end": "마감",
          "percentComplete": "완료",
          "resources": "리소스",
          "resourcesEditorTitle": "리소스",
          "resourcesHeader": "리소스",
          "start": "시작",
          "title": "제목",
          "unitsHeader": "단위"
        },
        "save": "저장",
        "views": {
          "day": "일",
          "end": "마감",
          "month": "월",
          "start": "시작",
          "week": "주",
          "year": "년"
        }
      });
  }

  /* Grid messages */

  if (kendo.ui.Grid) {
    kendo.ui.Grid.prototype.options.messages =
      $.extend(true, kendo.ui.Grid.prototype.options.messages, {
        "commands": {
          "cancel": "변경 취소",
          "canceledit": "취소",
          "create": "추가",
          "destroy": "삭제",
          "edit": "편집",
          "excel": "엑셀로 내보내기",
          "pdf": "PDF로 내보내기",
          "save": "변경 저장",
          "select": "선택",
          "update": "갱신"
        },
        "editable": {
          "cancelDelete": "취소",
          "confirmation": "해당 열을 정말 삭제하시겠습니까?",
          "confirmDelete": "삭제"
        },
        "noRecords": "등록된 데이터가 없습니다.",
      });
  }

  /* Groupable messages */

  if (kendo.ui.Groupable) {
    kendo.ui.Groupable.prototype.options.messages =
      $.extend(true, kendo.ui.Groupable.prototype.options.messages, {
        "empty": "컬럼을 여기에 드래그 앤 드롭하면 해당 열을 기준으로 그룹화합니다."
      });
  }

  /* Pager messages */

  if (kendo.ui.Pager) {
    kendo.ui.Pager.prototype.options.messages =
      $.extend(true, kendo.ui.Pager.prototype.options.messages, {
        "allPages": "전체",
        "display": "{0} - {1} ({2} 항목중)",
        "empty": "표시할 항목이 없습니다.",
        "page": "페이지",
        "of": "/ {0}",
        "itemsPerPage": "항목 (1 페이지 당)",
        "first": "처음페이지로 이동",
        "previous": "이전페이지로 이동",
        "next": "다음페이지로 이동",
        "last": "마지막페이지로 이동",
        "refresh": "갱신",
        "morePages": "그 외의 페이지"
      });
  }

  /* Slider messages */

  if (kendo.ui.Slider) {
  kendo.ui.Slider.prototype.options =
  $.extend(true, kendo.ui.Slider.prototype.options,{
    "increaseButtonTitle": "더하기",
    "decreaseButtonTitle": "줄이기"
  });
  }

  /* TreeView messages */

  if (kendo.ui.TreeView) {
  kendo.ui.TreeView.prototype.options.messages =
  $.extend(true, kendo.ui.TreeView.prototype.options.messages,{
    "loading": "로드중...",
    "requestFailed": "요청에 실패하였습니다.",
    "retry": "재시도"
  });
  }

  /* Upload messages */

  if (kendo.ui.Upload) {
    kendo.ui.Upload.prototype.options.localization =
      $.extend(true, kendo.ui.Upload.prototype.options.localization, {
        "select": "파일을 선택...",
        "cancel": "취소",
        "retry": "재시도",
        "remove": "삭제",
        "uploadSelectedFiles": "파일을 업로드",
        "dropFilesHere": "여기에 파일을 드롭하여 업로드",
        "statusUploading": "업로드 중",
        "statusUploaded": "업로드 완료",
        "statusWarning": "경고",
        "statusFailed": "실패",
        "headerStatusUploading": "업로드 중...",
        "headerStatusUploaded": "완료"
      });
  }

  /* Validator messages */

  if (kendo.ui.Validator) {
    kendo.ui.Validator.prototype.options.messages =
      $.extend(true, kendo.ui.Validator.prototype.options.messages, {
        "required": "{0} 는 필수입니다.",
        "pattern": "{0} 는 유효하지 않습니다.",
        "min": "{0} 는 {1} 보다 크거나 같은 값을 입력해주세요.",
        "max": "{0} 는 {1} 보다 작거나 같은 값을 입력해주세요.",
        "step": "{0} 는 유효하지 않습니다.",
        "email": "{0} 는 유효하지 않은 메일 형식입니다.",
        "url": "{0} 는 유효하지 않은 URL 형식입니다.",
        "date": "{0} 는 유효하지 않은 날짜 형식입니다."
      });
  }

  /* Dialog */

  if (kendo.ui.Dialog) {
    kendo.ui.Dialog.prototype.options.messages =
      $.extend(true, kendo.ui.Dialog.prototype.options.localization, {
        "close": "닫기"
      });
  }

  /* Alert */

  if (kendo.ui.Alert) {
    kendo.ui.Alert.prototype.options.messages =
      $.extend(true, kendo.ui.Alert.prototype.options.localization, {
        "okText": "확인"
      });
  }

  /* Confirm */

  if (kendo.ui.Confirm) {
    kendo.ui.Confirm.prototype.options.messages =
      $.extend(true, kendo.ui.Confirm.prototype.options.localization, {
        "okText": "확인",
        "cancel": "취소"
      });
  }

  /* Prompt */
  if (kendo.ui.Prompt) {
    kendo.ui.Prompt.prototype.options.messages =
      $.extend(true, kendo.ui.Prompt.prototype.options.localization, {
        "okText": "확인",
        "cancel": "취소"
      });
  }

})(window.kendo.jQuery);