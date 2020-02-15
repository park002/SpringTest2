<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>
</head>
<style>
	h2 { text-align: center;}
  table { width: 100%;}
  textarea { width: 100%;}
 	#outter {
		display: block;
		width: 30%;
		margin: auto;
	}
</style>
<body>
	<%-- <td><a href="delete?mId=${dto.mId}">X</a></td> --%>
<h2>글 상세보기</h2>
<br><br><br>
<div id="outter">
	<table border="1">
		<tr>
			<td>제목: ${boardContent.b_title}</td>
		</tr>
		<tr>
			<td>
				작성자: ${boardContent.b_writer}
				<span style="float: right;"><fmt:formatDate value="${boardContent.b_date}" pattern="yyyy.MM.dd HH:mm"/></span>
			</td>
		</tr>
		<tr>
			<td><div style="height: 300px; margin: 10px; display: inline-block">${boardContent.b_detail}</div></td>
		</tr>
	</table>                                                                         
	<button type="button"  style="float: right;" onclick="location.href='listAll'">글 목록 </button>
    <button type ="button"  style="float: right;" onclick="location.href='/jaeho/board/updateBoardForm?b_no=${boardContent.b_no}'">글 수정 </button>
 
	  <a href="/jaeho/board/delete?b_no=${boardContent.b_no}">글삭제</a>
	  
</div>
</body>
</html>