<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- page content -->
<div class="right_col" role="main">

    <div class="clearfix"></div>

    <div class="row">
      <div class="col-md-12 col-sm-12  ">

        <!-- x_panel start -->
        <div class="x_panel">

          <div class="x_title">
            <h2><i class="fa fa-video-camera"></i> 온라인 회의 </h2>

            <div class="clearfix"></div>
          </div>

          <!-- x_content start -->
          <div class="x_content">
            <p>같은 프로젝트 구성원만 온라인 회의에 초대할 수 있습니다.</p>

            <!-- listbox start -->
            <div class="row m-5 justify-content-center">

              <!-- meeting-select-bar start -->
              <div class="selbox d-flex">
                <div class="sel sel--black-panther">
                  <select name="select-profession" id="select-profession" >
                    <option value="" disabled>프로젝트를 선택하세요.</option>
                    <option value="hacker">커피 에너지 개발 프로젝트</option>
                    <option value="gamer">전기차 대체 커피 자동차 프로젝트</option>
                    <option value="developer">LG화학 커피 에너지 변환 프로젝트</option>
                    <option value="programmer">로스트아크 도화가 3D 랜더링 프로젝트</option>
                    <option value="designer">아서왕 홈랜드를 떠나다</option>
                  </select>
                </div>
  
                <div class="sel sel--superman">
                  <select name="select-superpower" id="select-superpower">
                    <option value="" disabled>참가 목록</option>
                    
                  </select>
                </div>
              </div>
              <!-- meeting-select-bar start -->

                <div id="example" role="application">
                  <div class="demo-section k-content wide">
                    <select id="optional"></select>
                    <select id="selected"></select>
                  </div>
                </div>
              </div>
              <!-- listbox end -->
              <button type="submit" class="btn btn-outline-primary" style="float: right;">시작하기</button>
          </div>
          <!-- x_content end -->
        </div>
        <!-- x_panel start -->

      </div>
    </div>

</div>
<!-- /page content -->