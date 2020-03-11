<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<style rel="stylesheet">
@charset "UTF-8";
@import url(https://fonts.googleapis.com/css?family=Lato:400,700);
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}


body .container {
  position: relative;
  overflow: hidden;
  width: 700px;
  height: 500px;
  margin: 80px auto 0;
}
body .container .login_header {
  float: left;
  width: 50%;
  height: 100%;
  padding: 40px 40px 0;
}
body .container .login_header.bg {
  background-image: url("./resources/img/아이유1.PNG");
  background-size: 100%;
  background-repeat: no-repeat;
  position: relative;

}
body .container h1 {
  font-size: 30px;
  font-weight: 700;
  margin-bottom: 23px;
  text-align: center;
  text-indent: 6px;
  letter-spacing: 7px;
  text-transform: uppercase;
  color: #263238;
}
body .container .tabs {
  width: 100%;
  margin-bottom: 29px;
  border-bottom: 1px solid #d9d9d9;


}
body .container .tabs .tab {
  display: inline-block;
  width:100%;
  margin-bottom: -1px;

  padding-bottom: 10px;


  letter-spacing: 0;
  border-bottom: 1px solid #d9d9d9;
  transition: all 0.1s ease-in-out;
  color: #263238;
  font-size: 11px;
}
body .container .tabs span{
  text-align: center;

}


body .container .content form {
  position: relative;
  height: 287px;
}
body .container .content label:first-of-type, body .container .content input:first-of-type, body .container .content .more:first-of-type {
  -moz-animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label:nth-of-type(2), body .container .content input:nth-of-type(2), body .container .content .more:nth-of-type(2) {
  -moz-animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label:nth-of-type(3), body .container .content input:nth-of-type(3), body .container .content .more:nth-of-type(3) {
  -moz-animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label {
  font-size: 12px;
  color: #263238;
  -moz-user-select: -moz-none;
  -ms-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
body .container .content label:not([for='remember']) {
  display: none;
}
body .container .content input.inpt {
  font-size: 14px;
  display: block;
  width: 100%;
  height: 42px;
  margin-bottom: 12px;
  padding: 16px 13px;
  color: #999999;
  border: 1px solid #d9d9d9;
  background: transparent;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
}
body .container .content input.inpt::-webkit-input-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt:-moz-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt::-moz-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt:-ms-input-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt:focus {
  border-color: #999999;
}
body .container .content input.submit {
  font-size: 12px;
  line-height: 42px;
  display: block;
  width: 48%;
  height: 42px;
  cursor: pointer;
  vertical-align: middle;
  letter-spacing: 2px;
  text-transform: uppercase;
  color: #263238;
  border: 1px solid #263238;
  background: transparent;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  float : right;
}
body .container .content input.submit:hover {
  background-color: #263238;
  color: #ffffff;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -webkit-transition: all 0.2s;
  transition: all 0.2s;
}
body .container .content input:focus {
  outline: none;
}


body .container .content .checkbox {
  margin-top: 4px;
  overflow: hidden;
  clip: rect(0 0 0 0);
  width: 0;
  height: 0;
  margin: 17px -1px;
  padding: 0;
  border: 0;
}
body .container .content .checkbox + label {
  vertical-align: middle;
  display: inline-block;
  width: 50%;
}
body .container .content .checkbox + label:before {
  content: "\A";
  color: #999999;
  font-family: Verdana;
  font-weight: bold;
  font-size: 8px;
  line-height: 10px;
  text-align: center;
  display: inline-block;
  vertical-align: middle;
  position: relative;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  background: transparent;
  border: 1px solid #d9d9d9;
  width: 11px;
  height: 11px;
  margin: -2px 8px 0 0;

}
body .container .content .checkbox:checked + label:before {
  content: "✓";
}
body .container .content .submit-wrap {
  bottom: 0;
  width: 100%;
}
body .container .content .submit-wrap a {
  font-size: 12px;
  display: block;
  margin-top: 20px;
  text-align: center;
  text-decoration: none;
  color: #999999;
}
body .container .content .submit-wrap a:hover {
  text-decoration: underline;
}
body .container .content .signup-cont {
  display: none;
}

@keyframes slideIn {
  0% {
    filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
    opacity: 0;
    margin-left: -320px;
  }
  100% {
    filter: progid:DXImageTransform.Microsoft.Alpha(enabled=false);
    opacity: 1;
    margin-left: 0px;
  }
}
@-webkit-keyframes slideIn {
  0% {
    filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
    opacity: 0;
    margin-left: -320px;
  }
  100% {
    filter: progid:DXImageTransform.Microsoft.Alpha(enabled=false);
    opacity: 1;
    margin-left: 0px;
  }
}

</style>
</head>
<script>
$(document).ready(function(){
	   $("#login").click(function(){
		   var m_id = $("#m_id").val();
		   var m_password =$("#m_password").val();
		   if(! m_id) {
			   alert('아이디를 입력하세요');
			   document.form1.m_id.focus();
			   return false;
		   }
		   
		  if(! m_password) {
			   alert('비밀번호를 입력하세요');
			   document.form1.m_password.focus();
			   return false;
		 }
	});
});
</script>
<body>
<c:if test="${Emailmsg eq 0}">
<script>alert('이메일 인증 후 로그인 해주시기 바랍니다.')</script>
</c:if>
<c:if test="${Loginmsg eq  false }">
<script>alert('패스워드를 확인 해주세요');</script>
</c:if>
<c:if test="${SelectIdmsg eq false }">
<script>alert('존재하지 않는 ID 입니다.');</script>
</c:if>


<section class="container">
		    <article class="login_header">
              <h1>로그인</h1>
			        <div class="tabs">
                <span class="tab"><a>델루나에 오신것을 환영합니다.</a></span>
			        </div>
			        <div class="content">
				            <div class="signin-cont cont">
					                <form name="form1" action="/jaeho/member/login" method="post">
						                    <input type="text" name="m_id" id="m_id" class="inpt"  placeholder="Your ID">
						                    <input type="password" name="m_password" id="m_password" class="inpt"  placeholder="Your password">
                						    <label for="password">Your password</label>
						                    <input type="checkbox" id="remember" class="checkbox" checked>
						                    <label for="remember">Remember me</label>
						                    <div class="submit-wrap">
                                    <input type="submit" id="login" value="로그인" class="submit">
                                    <a href="index.jsp"><input type="button" value="메인" class="submit"></a><br><br>
                                      <a href="searchIdpass.jsp" class="more">아이디 / 비밀번호를 잊으셨나요?</a>
                                      <a href="/jaeho/member/loginform" class="more">아직 회원이 아니신가요?</a>
                                </div>
                                <div class="sign_up">

                                </div>
        					        </form>
    				        </div>
			        </div>
		    </article>
		    <div class="login_header bg"></div>
	</section>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>
</html>