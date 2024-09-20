<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!-- page content -->
  <div class="right_col" role="main">
    <!-- 콜라보 프로젝트 바디 시작 -->
	
    <div class="x_title">
      <h2>
        <img src="https://img.icons8.com/ios/30/000000/handshake--v2.png" />
        				콜라보 프로젝트 리스트
      </h2>
      <div class="clearfix"></div>
    </div>

    <div class="x_content">
      <div class="col-md-12">
        <!-- sort 기능(아직 미구현)-->
			<div class="collabo-list" id="collabo-list"></div>

          </div><!-- /.tab-pane -->
        </div><!-- /.tab-content -->
      </div><!-- col-md-12 끝-->


    


    <script id="collaboCardTemplate" type="text/x-kendo-template">
       # if(title != "루트"){ #
<div class="collabo-list card">
  <div class="card-body project-list">
    <div class="user-block mt-3 mb-3">
      <h2>
        <a href="<%= request.getContextPath() %>/app/collabo/main?cprojNo=#:cprojNo#"
          class="collabo-list-a" style="font-weight:bold;color:olive;	">#:title#</a> <span
          class="badge badge-success float-right">#:status#</span>
      </h2>
    </div>
    <!-- /.user-block -->
    <div class="col-sm-8 text-truncate">#: xssPurify(intro)#</div>
    <div class="col-sm-4 text-right">
      <p>
        <span class="ml-3 rounded rounded-2" style="background:rgb(239,239,239);">마감일 : #:kendo.toString(enddate,"yyyy년 MM월 dd일")#</span>
      </p>
    </div>
    <div class="col-sm-8 row d-flex justify-content-evenly">
        <div class="d-flex justify-content-end"><span class="ml-3">갱신일 : #:kendo.toString((updatedate),"yyyy년 MM월 dd일")#</span></div>
    </div>
    <div class="col-sm-4 ml-3 text-right" style="height:50px">
      <ul class="list-inline" style="display: inline-flex">
    # for(var i=0, len=member.length; i<len; i++) { #
        # if(i < 5) { #
          <!-- 구성원 단위 시작 -->
          <li class="project-member nav-item dropdown open ml-3">
            <div class="d-flex flex-column">
              <a href="" class="project-member " aria-haspopup="true"
              id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                # if(member[i].picture == null){ #
                <img src="/user/getPictureById?userId=#:member[i].userId#" class="img-circle" alt="img" />
                # } else { #
                <img src="/user/getPictureById?userId=#:member[i].userId#" class="img-circle" alt="img" />
                # } #
              </a>
              <div class="dropdown-menu dropdown-membermenu pull-right" aria-labelledby="navbarDropdown">
              <div class="col-md-3   widget widget_tally_box">
                <div class="fixed_height_390">
                  <div class="x_content">
                    <div class="flex">
                      <ul class="list-inline widget_profile_box">
                        <li></li>
                        <li class="d-flex justify-content-center">
                          # if(member[i].picture == null){ #
                          <img src="/user/getPictureById?userId=#:member[i].userId#" alt="img" class="img-circle profile_img"></li>
                          # } else { #
                          <img src="/user/getPictureById?userId=#:member[i].userId#" alt="img" class="img-circle profile_img"></li>
                          # } #
                        <li></li>
                      </ul>
                    </div>
                    <div>
                      <h3 class="d-flex justify-content-center mt-3">#: member[i].nickname #</h3>
                    </div>
                    <div class="flex">
                      <ul	class="list-inline count2 d-flex justify-content-between">
                      <li><span class="badge badge-primary">Projects</span>
                        <h3>#: member[i].projectCount #</h3></li>
                      <li><span class="badge badge-danger">Likes</span>
                        <h3>#: member[i].likeCount #</h3></li>
                      <li><span class="badge badge-info">Tasks</span>
                        <h3>#: member[i].taskCount #</h3></li>
                      </ul>
                    </div>
                    <div class="p-1" style="max-height: 110px; background-color: rgb(229,229,229);display:-webkit-box;
                      -webkit-line-clamp: 4;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;">
                      <p class="mt-2 mb-2">#= member[i].intro #</p>
                    </div>
                  </div>
                </div>
              </div>
              </div>
            </div>
          </li>
        # } #
    # } #
      </ul>
  </div>

  </div>
</div>
# } #
  
		
		
		
    </script>