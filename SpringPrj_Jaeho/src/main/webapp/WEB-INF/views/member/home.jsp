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
  <script>alert('���̵� �н����� Ȯ��.')</script>
<h2> ${sessionScope.userId} �� ȯ���մϴ�</h2>
<h2>${sessionScope.userName}�Ե� ����</h2>
</c:if>
</body>
</html>