<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In Form by probada</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/asserts/images/tab-img.jpg">
    <!-- Font Icon -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/fonts/material-icon/css/material-design-iconic-font.min.css">
    <!-- Main css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/css/common/login_registration.css">
</head>
<body>
	<div id="loading">
		<div class="loader"></div>
	</div>
	<div class="main">
		<c:if test="${not empty param.welcome}">
			<h1 class="welcome_title">probada에 오신 걸 환영합니다!</h1>
			<canvas id="canvas"></canvas>
		</c:if>

		<!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="<%=request.getContextPath()%>/resources/asserts/images/signin-image.jpg" alt="sing up image"></figure>
                        <a href="<%=request.getContextPath()%>/home/register" class="signup-image-link">회원가입</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">로그인 </h2>
                        <form class="register-form" id="login-form" name="loginForm" onsubmit="return false;">
                        
                        <c:if test="${not empty successReset}">
                        	    <div class="alert alert--info">
                                <button type="button" class="close">&times;</button>
                                <ul>
                                    <li>비밀번호 재설정이 성공적으로 처리되었습니다.</li>
                                </ul>
                            </div>
                        </c:if>
                            <div class="alert alert--error" hidden>
                                <button type="button" class="close">&times;</button>
                                <ul>
                                    <li></li>
                                </ul>
                            </div>

                            <div class="form-group">
                                <label for="user-email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="userEmail" id="user-email" placeholder="이메일" autocomplete="off" />
                            </div>

                            <div class="form-group">
                                <label for="user-pwd"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="user-pwd" id="user-pwd" placeholder="비밀번호" />
                            </div>

                            <div class="remember_search" style="margin-top:50px">
                                <div class="form-group">
                                    <input type="checkbox" name="rememberMe" id="remember-me" class="agree-term" />
                                    <label for="remember-me" class="label-agree-term"><span><span></span></span>아이디 기억하기</label>
                                </div>
                                <div class="form-group search_pwd">
                                    <a class="" href="<%=request.getContextPath()%>/home/send_pwdReset">비밀번호 찾기</a>
                                </div>
                            </div>

                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="로그인"/>
                            </div>

                        </form>

                      <!--   <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-kakao"></i></a></li>
                                <li><a href="#" id="naverIdLogin"><i class="display-flex-center zmdi zmdi-naver"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>
                        </div> -->

                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/asserts/js/common/login_registration.js"></script>
     
</body>
</html>

<!-- error css reference https://codepen.io/naveenbhaskar/pen/kivzF -->