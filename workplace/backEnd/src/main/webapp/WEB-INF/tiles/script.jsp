<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<!-- jQuery -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
		<!-- FastClick -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
		<!-- NProgress -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/nprogress/nprogress.js"></script>
		<!-- Dropzone.js -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/dropzone/dist/min/dropzone.min.js"></script>
		<!-- easy-pie-chart -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>
		<!-- bootstrap-wysiwyg -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/google-code-prettify/src/prettify.js"></script>
		<!-- iCheck -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/iCheck/icheck.min.js"></script>
		<!-- Custom Theme Scripts -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/build/js/custom.min.js"></script>
		<!-- Summer Note -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/summernote/summernote-bs4.min.js"></script>
		<!-- kendo -->
		<script	src="<%=request.getContextPath()%>/resources/asserts/js/common/kendo.all.min.js"></script>
		<script	src="https://kendo.cdn.telerik.com/2021.3.1207/js/pako_deflate.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/asserts/js/common/kendo.messages.kr-KR.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/asserts/js/common/kendo.culture.ko-KR.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/asserts/js/common/overlay.js"></script>
		<!-- PNotify -->
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.js"></script>
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.buttons.js"></script>
		<script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/pnotify/dist/pnotify.nonblock.js"></script>
		<!-- Chart.js -->
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<!-- Custom_ChartJS -->
		<script src="<%=request.getContextPath()%>/resources/asserts/js/common/custom_chartJS.js"></script>
		<!-- momentJS -->
		<script src="<%=request.getContextPath()%>/resources/asserts/js/common/moment-with-locales.js"></script>
		<!-- Handlebars -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js" integrity="sha512-RNLkV3d+aLtfcpEyFG8jRbnWHxUqVZozacROI4J2F1sTaDqo1dPQYs01OMi1t1w9Y2FdbSCDSQ2ZVdAC8bzgAg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<!-- toastr js -->
		<script src="<%=request.getContextPath()%>/resources/asserts/js/common/toastr.min.js"></script>
		<!-- 결제 시스템 iamport.payment.js -->
		<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
		<!-- alertIndex js 알림리스트 제공 -->
		<script src="<%=request.getContextPath()%>/resources/asserts/js/alert/alertIndex.js"></script>
		<!-- jQuery Tags Input -->
	    <script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
	    <!-- 채팅 js -->
	    <script src="<%=request.getContextPath()%>/resources/asserts/js/chat/chatCreate.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/asserts/js/chat/chatOverlay.js"></script>
	   	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	    <!-- 통합검색 js -->
	    <script src="<%=request.getContextPath()%>/resources/asserts/js/search/totalSearch.js"></script>
	    <!-- bootstrap-progressbar -->
	    <script src="<%=request.getContextPath()%>/resources/bootstrap/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	    <script>
		    if(${mailRegist eq 'registSuccess'}) {
				alert("메일을 전송하였습니다.");
				
				location.href="<%=request.getContextPath()%>/app/myWork?mail=send";
			}else if(${mailRegist eq 'tempSuccess'}){
				alert("메일을 임시저장하였습니다.");
			}
	    </script>
	</body>
</html>
