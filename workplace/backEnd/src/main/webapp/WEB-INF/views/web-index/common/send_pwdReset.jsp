<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>password reset form by probada</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/asserts/images/tab-img.jpg">
    <!-- Font Icon -->
    <script src="https://kit.fontawesome.com/db5d9a0152.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/css/common/login_registration.css">
</head>
<body>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title" style="margin-bottom: 15px;">비밀번호 재설정</h2>
                        <div class="reset_guide" style="margin-bottom: 60px;">
                            <p><small>비밀번호를 찾고자하는 아이디를 입력해 주세요.</small></p>
                        </div>
                        <form class="register-form" id="send_pwdReset_form" name="send_pwdReset_form" onsubmit="return false;">


                            <div class="form-group custom-validate">
                                <label for="userId"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="userId" id="email" placeholder="이메일" autocomplete="off"/>
                                <i class="fas fa-check-circle"></i>
                                <i class="fas fa-exclamation-circle"></i>
                                <small>Error message</small>
                            </div>


                            <div class="form-group form-button">
                                <input type="submit" name="password_resetBtn" class="form-submit" value="다음"/>
                            </div>

                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="<%=request.getContextPath()%>/resources/asserts/images/signup-image.jpg" alt="sing up image"></figure>
                        <a href="<%=request.getContextPath()%>/home/login" class="signup-image-link">로그인</a>
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