<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head> 
<body>

<form:form action="/jaeho/board/updateBoard" method="POST" commandName="upboard" >
<table style="width:500px; border:1px solid black">
<form:input type="hidden" path="b_no" />
<tr>
<td>글 제목 </td>
<td><form:input type="text" path="b_title"/></td>
</tr>
<tr>
<td>작성자</td>
<td><form:input type="text" path="b_writer" /> </td>
</tr>
<tr>
<td>글내용</td>
<td><form:textarea  type="width:400px; height:200px;" path="b_detail" /></td>
</tr>
<tr>
<td><button>글수정</button></td>
<td><input type="button" onclick="location.href='listAll'" value="글목록"></td>
</tr>

</table>

</form:form>


</body>
</html>