<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="<c:url value='/resources/images/icons/favicon.ico'/>" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/vendor/animate/animate.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resoruces/vendor/css-hamburgers/hamburgers.min.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/vendor/animsition/css/animsition.min.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/vendor/select2/select2.min.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/vendor/daterangepicker/daterangepicker.css'/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/util.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>">
<!--===============================================================================================-->
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title"
					style="background-image: url(<c:url value='/resources/images/bg-01.jpg'/>);">
					<!-- 					<span class="login100-form-title-1">호텔델루나</span> -->
					<span class="login100-form-title-1">Hotel Delluna</span>
				</div>
	<%-- 			<c:choose>
					<c:when test="${login eq null}"> --%>
						<form class="login100-form validate-form" action="memLogin.do"
							method="post">
							<div class="wrap-input100 validate-input m-b-26"
								data-validate="ID를 입력해주세요">

								<span class="label-input100">아이디</span> <input class="input100"
									type="text" name="customer_id" placeholder="Enter username">
								<span class="focus-input100"></span>
							</div>
							<div class="wrap-input100 validate-input m-b-18"
								data-validate="비밀번호를 입력해주세요">
								<span class="label-input100">비밀번호</span> <input class="input100"
									type="password" name="PASSWORD" placeholder="Enter password">
								<span class="focus-input100"></span>
							</div>
							<div class="flex-sb-m w-full p-b-30">
								<div class="contact100-form-checkbox">
									<input class="input-checkbox100" id="ckb1" type="checkbox"
										name="remember-me"> <label class="label-checkbox100"
										for="ckb1"> Remember me </label>
								</div>

								<div>
									<a href="searchIdpass.jsp" class="txt1"> Forgot Password? </a>
								</div>
							</div>
							<div class="container-login100-form-btn">

								<button class="login100-form-btn" value="로그인">로그인</button>

								<button class="login100-form-btn" type="button" value="회원가입" onclick="location.href='<c:url value='/member/MemberInsert'/>'">회원가입</button>
									<!-- 	onclick="location.href='MemberAddForm2.jsp'">회원가입</button> -->
							</div>
						</form>
					<%--  	<c:if test="${pwnot ne null}"> 
							<script>alert('${pwnot}')</script>
							<c:remove var="pwnot"/>
						</c:if>
					</c:when>
					<c:otherwise>
						<p>${sessionScope.customer_id}님환영합니다!</p>
						<p>
							<a href="memLogout.do">로그아웃</a>
					</c:otherwise>
				</c:choose> --%>
			</div>
		</div>
	</div>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/vendor/animsition/js/animsition.min.js'/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/vendor/bootstrap/js/popper.js'/>"></script>
	<script src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/vendor/select2/select2.min.js'/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/vendor/daterangepicker/moment.min.js'/>"></script>
	<script src="<c:url value='/resources/vendor/daterangepicker/daterangepicker.js'/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/vendor/countdowntime/countdowntime.js'/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value='/resources/js/main.js'/>"></script>

</body>
</html>