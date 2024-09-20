<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!-- 내 작업 바디 시작 -->
    <div class="x_panel">

      <div class="x_title">
        <h2><img src="https://img.icons8.com/ios/20/000000/spoon.png" /> Spoon </h2>

        <div class="clearfix"></div>
      </div>

      <div class="x_content spoon">
        <p>'스푼' 기능을 이용해 자료를 가져오세요.</p>
        <div class="form-group-collabo row spoon mt-5">
          <div class="spoon-select-bar">
            <div class="spoon-select">
              <select class="select2_group form-control-collabo" style="width: 400px;">
                <option value="" disabled selected hidden>산하 프로젝트의 자료를 선택하세요.</option>
                <option value="">김진혁 우간다 국제 결혼 프로젝트</option>
                <option value="">김진혁 앙골라 국제 결혼 프로젝트</option>
                </optgroup>
              </select>
            </div>
            <div class="empty" style="width: 103px;"></div>
            <div class="spoon-select">
              <select class="select2_group form-control-collabo" style="width: 400px;">
                <option value="" disabled selected hidden>콜라보 프로젝트를 선택하세요.</option>
                <option value="">김진혁 국제 결혼 진행 프로젝트</option>
                <option value="">콜라보 2</option>
                <option value="">콜라보 3</option>
              </select>
            </div>
          </div>


          <div id="example" role="application" style="margin-top: 10px;">
            <select id="optional"></select>
            <select id="selected"></select>
          </div>

        </div>
        <input type="button" class="btn btn-outline-primary" value="확인" name="spoon-btn" id="spoon-btn"
          style="float: right;">

      </div>
    </div>
    <!-- 내 작업 바디 끝 -->
