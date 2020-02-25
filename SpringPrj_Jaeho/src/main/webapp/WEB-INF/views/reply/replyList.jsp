<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ page session="false"%> --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table style="width:700px">

<c:forEach var="row" items="${list}">
${row}
<tr>
<td>작성자:${row.b_writer} 작성날짜:(<fmt:formatDate value="${row.b_date}" pattern="HH:mm:ss"/>)
<br>
 댓글내용:${row.replytext}
</td>
</tr>
</c:forEach>


</table>
</body>
</html>