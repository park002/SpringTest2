<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<html>
<head>
	<title>게시판</title>
</head>
<script>
$(document).ready(function(){
	$("#btnSave").click(function(){
	    	var title =$("#b_title").val();
	    	var detail = $("#b_detail").val();
	    	var writer = $("#b_writer").val();
		    if (title==""){
		    	alert('제목을 입력하세요');
		    	document.form1.b_title.focus();
		    	return;
		    }
		    if(detail==""){
		    	alert('내용을 입력하세요');
		    	document.form1.b_detail.focus();
		    	return;
		    }
		    if(writer==""){
		    	alert('작성자를 입력하세요');
		    	document.form1.b_writer.focus();
		    	return;
		    }
		    document.form1.submit();
          });
	});

</script>
<body>
	<h1>게시글 작성</h1>
	<form name ="form1"  action="<c:url value="/board/create"/>" method="POST">
		<div>
			제목
			 <input type="text" name="b_title" id="b_title"  size="80"  placeholder="제목을 입력해주세요">
		</div>
		<div>
			내용
			<textarea rows="4" cols="80" name="b_detail"  id ="b_detail" placeholder="내용을 입력해주세요"></textarea>
		</div>
		<div>
			작성자 <input type="text" name="b_writer" id="b_writer" placeholder="작성자" value="${userId}" readonly>
		</div>
		<div style="with:650px; text-align: center;">
			<button type="button" id ="btnSave">작성하기</button>
			<button type="reset">취소</button>
		</div>
	</form>

</body>
</html>

