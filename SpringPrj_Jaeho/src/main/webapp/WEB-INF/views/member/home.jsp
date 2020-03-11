<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
</head>
<body>
<c:if test="${Loginmsg== 'failure'}" >
  <script>alert('아이디나 패스워드 확인.')</script>
<h2> ${sessionScope.userId} 님 환영합니다</h2>
<h2>${sessionScope.userName}님도 ㅎㅇ</h2>
</c:if>
</body>
</html>