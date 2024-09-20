<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by probada</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/asserts/images/tab-img.jpg">
    <!-- Font Icon -->
    <script src="https://kit.fontawesome.com/db5d9a0152.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/css/common/login_registration.css">
</head>
<body>
	<div id="loading">
		<div class="loader"></div>
	</div>


    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                    	<div class="form-title">
                        <h2>회원가입</h2>
                        <small>회원가입을 완료되면 해당 이메일 주소로 인증 링크를 보냅니다.</small>
                    	</div>
                        <form class="register-form" id="register-form">
                            <div class="form-group custom-validate">
                                <label for="userId"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="userId" id="email" placeholder="이메일" autocomplete="off"/>
                                <i class="fas fa-check-circle"></i>
                                <i class="fas fa-exclamation-circle"></i>
                                <small>Error message</small>
                            </div>
                            <div class="form-group custom-validate">
                                <label for="nickname"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="nickname" id="username" placeholder="닉네임" autocomplete="off" />
                                <i class="fas fa-check-circle"></i>
                                <i class="fas fa-exclamation-circle"></i>
                                <small>Error message</small>
                            </div>
                            <div class="form-group custom-validate">
                                <label for="pwd"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pwd" id="pass" placeholder="비밀번호" />
                                <i class="fas fa-check-circle"></i>
                                <i class="fas fa-exclamation-circle"></i>
                                <small>Error message</small>
                            </div>
                            <div class="form-group custom-validate">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="비밀번호 재확인" />
                                <i class="fas fa-check-circle"></i>
                                <i class="fas fa-exclamation-circle"></i>
                                <small>Error message</small>
                            </div>
                            <!-- <div class="form-group custom-validate">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span><a href="#" class="term-service">이용약관</a>에 동의합니다.</label>
                                <small style="padding-left:5px;">이용 약관에 동의해주세요.</small>
                            </div> -->
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="다음"/>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="<%=request.getContextPath()%>/resources/asserts/images/signup-image.jpg" alt="sing up image"></figure>
                        <a href="<%=request.getContextPath()%>/home/login" class="signup-image-link">이미 회원이신가요?</a>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="<%=request.getContextPath()%>/resources/bootstrap/ddit-vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/asserts/js/common/login_registration.js"></script>
    
</body>
</html>