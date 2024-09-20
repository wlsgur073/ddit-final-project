<%@page import="com.probada.user.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 석기현 테스트 -->




<!-- 석기현 테스트 -->



<!-- 변수 선언 시작 -->
<c:set var="nickname" value="${userVO.nickname}"/>
<c:set var="userId" value="${userVO.userId}"/>
<c:set var="userPicture" value="${userVO.picture}"/>

<!-- 변수 선언 끝 -->

<!DOCTYPE html>
<html>
<head>
</head>
  <body class="nav-sm">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col menu_fixed">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="<%=request.getContextPath() %>/app/index" class="site_title"><img src="https://img.icons8.com/ios-filled/30/3598db/lighthouse.png"/> <span> <small>prodaba</small> </span></a>
            </div>

            <div class="clearfix"></div>


            <br />

 <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <ul class="nav side-menu">
                  <li><a href="<%=request.getContextPath() %>/app/index"><i class="fa fa-home"></i> 홈</a>
                  </li>
                  <li><a id="myWorkMenu" href="<%=request.getContextPath()%>/app/myWork"><i class="fa fa-edit"></i> 내 작업 </a>
                  </li>
                  <li><a href="<%=request.getContextPath()%>/app/project-list"><i class="fa fa-desktop"></i> 프로젝트</a>
                  </li>
                  <li><a id="OwnCollabo" href="<%=request.getContextPath()%>/app/collabo-none"><i class="fa fa-group"></i> 콜라보</a>
                  </li>
                  <!-- <li><a href="javascript:meeting_newteb();"><i class="fa fa-video-camera"></i> 온라인 회의</a>
                  </li> -->
                  <li><a href="javascript:getOverlayTemplateChatList('chatTemplate');"><i class="fa fa-comments-o"></i> 채팅</a>
                  </li>
                </ul>
              </div>

              <div class="menu_section">
                <h3>내 프로젝트</h3>
                <ul class="nav side-menu">
                  <!-- <li><a><i class="fa fa-bug"></i> 개인 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu user_task_list">
                      리스트 추가
                      alertIndex.js의 showAsideBarList() 메서드가 처리중..
                    </ul>
                  </li> -->
                  <li><a><i class="fa fa-windows"></i> 프로젝트 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu user_project_list">
                      <!-- 리스트 추가 -->
                     <!-- alertIndex.js의 showAsideBarList() 메서드가 처리중.. -->
                    </ul>
                  </li>
                  <li><a><i class="fa fa-sitemap"></i> 콜라보 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu user_collabo_list">
                        <!-- alertIndex.js의 showAsideBarList() 메서드가 처리중.. -->
                    </ul>
                  </li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="알림설정">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="유료 구독" href="<%=request.getContextPath()%>/app/pricing">
                <span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="로그아웃" href="<%=request.getContextPath()%>/logout.do" id="logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <div class="nav toggle">
                  <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                </div>
                <nav class="nav navbar-nav">



			<ul class=" navbar-right">

				


			 <div class="search-box"> 
			
			
			<!-- <div class="col-md-5 col-sm-7  form-group pull-right top_search" >
				<div class="input-group" style="margin-top: 7px; " >
					<input type="text" class="form-control" placeholder="프로젝트 또는 닉네임 검색" >
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">검색</button>
					</span>
				</div>
			</div> -->
			
			
			
				  <button class="btn-search" id="showSearchInput" onclick="submitTotal();"><i class="fa fa-search"></i> </button> 
                <!--  <input type="text" id="totalSearchInput" class="input-search" placeholder="프로젝트명 또는 닉네임을 입력해주세요..."> -->
                  <input type="text" class="seokid" id="totalSearchInput" onkeyup="pressSearch()" placeholder="프로젝트명 또는 닉네임을 입력해주세요" style="width: 400px; height: 50px;">
             
                 
              </div> 
				
				<!-- 알림 리스트 시작 -->
				<li role="presentation" class="nav-item dropdown open" style="margin-left: 15px;">
                    <a href="javascript:;" class="dropdown-toggle info-number" id="navbarDropdown1" data-toggle="dropdown" aria-expanded="false">
                      <i class="fa fa-envelope-o"></i>
                      <span class="badge bg-green"></span>
                    </a>
                    <ul class="dropdown-menu list-unstyled msg_list" role="menu" aria-labelledby="navbarDropdown1" id="alertVOList">
                    
                  <%--   이거 없어도 잘돌아감
                  <c:choose>
                    	<c:when test="${empty alertList }">
                    		<!-- <li class="nav-item">
                    			<strong>해당 내용이 없습니다.</strong>
                    		</li> -->
                    	</c:when>
                    	<c:otherwise>
                    		 <c:forEach items="${alertList }" var="alert">
                      			<li class="nav-item" >
	                        		<a class="dropdown-item">
			                          <span class="image"><img src="/resources/asserts/images/img.jpg" alt="Profile Image" /></span>
			                          <span>
			                            <span>${alert.nickname}</span>
			                            <span class="time">${alert.writeTimeDisplay}</span>
			                          </span>
			                          <span class="message">
			                            ${alert.content }
			                          </span>
			                        </a>
		                        </li>
		                    </c:forEach>
                    	</c:otherwise>
	                  
                    </c:choose> 
                    --%>
                      
                    <!--   알림 상세보기 // 구현 계획에 미포함
                    <li class="nav-item">
                        <div class="text-center">
                          <a class="dropdown-item">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                          </a>
                        </div>
                      </li> 
                      -->
                      
                    </ul>
                  </li>
                  <!-- 알림 리스트 끝 -->

						<li class="nav-item dropdown open" style="padding: 0px 15px;">
                    <a href="javascript:;" class="user-profile dropdown-toggle" aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                      <img src="<%=request.getContextPath()%>/user/getPictureById?userId=${userVO.userId}" alt="">${nickname }
                    </a>
                    <div class="dropdown-menu dropdown-usermenu pull-right" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item"  href="<%=request.getContextPath()%>/user/my-page"> 프로필</a>
                      <a class="dropdown-item"  href="#" data-toggle="modal" data-target="#alarmModal"><span>알림설정</span></a>
                      <!-- <a class="dropdown-item"  href="#">FAQ</a> -->
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/logout.do" id="logout"><i class="fa fa-sign-out pull-right"></i> 로그아웃</a>
                    </div>
                  </li>


                </ul>
              </nav>
            </div>
          </div>
        <!-- /top navigation -->
        
        



        
        
 <!-- 채팅 모달 테스트 -->       
        
        
        
</body>
</html>
