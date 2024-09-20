<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
 <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>유저 프로필 </h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content" style="height:450px;">
                    <div class="col-md-3 col-sm-3  profile_left">
                      <div class="profile_img">
                        <div id="crop-avatar">
                          <!-- Current avatar -->
                          <img class="img-responsive avatar-view" src="<%=request.getContextPath()%>/user/getPictureById?userId=${userProfileVO.userId}" alt="Avatar" title="${userProfileVO.userId }님의 프로필 이미지입니다.">
                        </div>
                      </div>
                      
                      <div class="profileIntro">
	                      <h3 class="user-profile-nickname text-truncate" style="cursor:pointer;">${userProfileVO.nickname }</h3>
	                      <p> 이메일: ${userProfileVO.userId}</p>
                      </div>

                  	<div class="user-profile-intro">
                  		<p>${userProfileVO.intro }</p>
                  	</div>
					<br/>

                    </div>
                    <div class="col-md-9 col-sm-9 ">

                      <div class="profile_title">
                        <div class="col-md-6">
                          <h2>${userProfileVO.nickname }님의 프로젝트 활동</h2>
                        </div>
                      </div>
                     

                      <!-- start user projects -->
                        <div id="myTabContent" class="tab-content">
                          <div role="tabpanel" class="user-profile-projects mt-5">

                            <table class="table no-margin table-hover">
                              <thead>
                                <tr>
                                  <th>프로젝트 명</th>
                                  <th>진행상태</th>
                                  <th>업무량</th>
                                  <th>진척도</th>
                                </tr>
                              </thead>
                              <tbody class="profile_projectList">
                               <!-- alertIndex.js의 projectList_for_profile()메서드 참고  --> 
                              </tbody>
                            </table>
                            
                          </div>
                        </div>
                        <!-- end user projects -->

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->