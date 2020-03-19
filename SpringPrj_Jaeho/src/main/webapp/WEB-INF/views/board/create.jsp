<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<title>게시판</title>
</head>
<script>
	$(document).ready(function() {
		$("#btnSave").click(function() {
			var title = $("#b_title").val();
			var detail = $("#b_detail").val();
			var writer = $("#b_writer").val();
			if (title == "") {
				alert('제목을 입력하세요');
				document.form1.b_title.focus();
				return;
			}

			if (detail == "") {
				alert('내용을 입력하세요');
				document.form1.b_detail.focus();
				return;
			}
			if (writer == "") {
				alert('작성자를 입력하세요');
				document.form1.b_writer.focus();
				return;
			}
			document.form1.submit();
		});
	});
</script>
      <body>
	         <article>
		             <div class="container" role="main">
			                     <h1>게시글 작성</h1>
			                                <form name="form1" action="<c:url value="/board/create"/>"method="POST">
				                            		<div class="mb-3">
															<label for="title">제목</label> <input type="text" id="b_title"
																	class="form-control" name="b_title" placeholder="제목을 입력해주세요">
													</div>
													<div class="mb-3">
															<label for="content">내용</label>
																 <textarea id="b_detail" class="form-control" rows="8" name="b_detail" placeholder="내용을 입력해주세요"></textarea>
													</div>
													<div class="mb-3">
														<label for="reg_id"> 작성자</label> <input type="text" name="b_writer" id="b_writer" placeholder="작성자" value="${userId}" readonly>
													</div>
													<div>
														<button type="button" id="btnSave" class="btn btn-sm btn-primary">작성하기</button>
														<button type="reset" class="btn btn-sm btn-primary">취소</button>
												   </div>
										</form>
						</div>
	</article>
</body>
</html>
