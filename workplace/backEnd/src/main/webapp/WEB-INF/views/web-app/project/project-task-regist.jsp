<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<!-- page content -->
<div class="right_col" role="main">
  <!-- 내 작업 바디 시작 -->
  <div class="x_panel">
    <div class="x_content">
      <!-- 제목 시작 -->
      <div class="x_title">
        <h2><i class="glyphicon glyphicon-exclamation-sign"></i>&nbsp;&nbsp;&nbsp;업무 등록</h2>
        <button type="button" class="btn btn-sm btn-secondary float-right" onclick="history.go(-1);">취소</button>
        <button type="button" class="btn btn-sm btn-primary float-right">등록</button>
        <div class="clearfix"></div>
      </div>
      <!-- 제목 끝 -->
      <!-- 이슈 수정 시작 -->
      <div class="form-group row">
        <div class="col-sm-6">
          <label>제목 :</label>
          <input type="text" class="form-control form-control-sm" value="">
        </div>
        <div class="col-sm-6">
          <label>프로젝트명 :</label>
          <input type="text" class="form-control form-control-sm form-control-view" value="AA 프로젝트" readonly>
        </div>
      </div>
    
     <div class="form-group row">
        <div class="col-sm-6">
          <label>담당자</label>
          <input type="text" class="form-control form-control-sm form-control-view" value="EnergyWookAA">
        </div>
        <div class="col-sm-6">
          <label class="control-label">중요도</label>
            <select class="form-control form-control-sm">
              <option>낮음</option>
              <option>보통</option>
              <option>높음</option>
            </select>
        </div>
      </div>
      

      <div class="form-group row">
        <div class="col-sm-6">
          <label>시작일 :</label>
          <input type="text" class="form-control form-control-sm form-control-view" value="2021-01-13">
        </div>
        <div class="col-sm-6">
          <label>마감일 :</label>
          <input type="text" class="form-control form-control-sm form-control-view" value="2021-01-20">
        </div>
      </div>
  
      
      <div class="form-group row">
        <div class="col-sm-12">
          <label>업무 내용 :</label>
          <textarea class="form-control form-control-sm" style="height:120px;"></textarea>
        </div>
      </div>


      <div class="form-group row">
        <div class="col-sm-12">
          <label>첨부파일 :</label>
          <form action="form_upload.html" class="dropzone dz-clickable">
            <div class="dz-default dz-message">
              <span>파일을 등록해주세요.</span>
            </div>
          </form>
        </div>
      </div>
      <!-- 이슈 수정 끝 -->
    </div>
  </div>
<!-- 내 작업 바디 끝 -->
</div>
<!-- /page content -->