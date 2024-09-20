<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>success send apssword reset by probada</title>
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
                <div class="signup-content successSendReset">
                    <div class="signup-form">
                        <h2 class="form-title" style="margin-bottom: 15px; text-align: center;">이메일을 확인해 주세요.</h2>
                        <form class="register-form" id="password_reset_form" name="password_reset_form" onsubmit="return false;">


                            <div class="alert alert--info" style="margin: 30px 45px;">
                                <ul>
                                    <li>입력하신 이메일로 비밀번호 재설정 링크를 보냈습니다. <br/>5 분이내에도 메일이 도착하지 않는다면, <br/> 스팸 메일함을 확인, 또는 재시도를 부탁드립니다.</li>
                                </ul>
                            </div>


                            <div classs="form-group form-button" style="text-align: center;">
                                <input type="button" class="form-submit" onclick="location.href='<%=request.getContextPath()%>/home/login'" value="로그인 페이지로 이동"/>
                            </div>

                        </form>
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