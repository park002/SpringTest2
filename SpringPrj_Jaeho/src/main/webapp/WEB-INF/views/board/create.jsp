<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<html>
<head>



<title>게시판</title>
</head>
    <script>
        $(document).ready(function(){
        	$("#btnSave").on("click",function(e){
        		e.preventDefault();
        		fn_insertBoard();
        	});
        	
        	function fn_insertBoard() {
        		var comSubmit = new ComSubmit("frm"); 
        		comSubmit.setUrl("<c:url value='/board/create'/>"); 
        		comSubmit.submit(); 
        		}
        	
        })

    </script>
      <body>
	         <article>
		             <div class="container" role="main">
			                     <h1>게시글 작성</h1>
			                                <form id="frm" name="frm" enctype="multipart/form-data">
				                            		<div class="mb-3">
															<label for="title">제목</label> <input type="text" id="b_title"
																	class="form-control" name="b_title" placeholder="제목을 입력해주세요" required>
													</div>
													<div class="mb-3">
															<label for="content">내용</label>
																 <textarea id="b_detail" class="form-control" rows="8" name="b_detail" placeholder="내용을 입력해주세요" required></textarea>
													</div>
													<div class="mb-3">
														<label for="reg_id"> 작성자</label> <input type="text" name="b_writer" id="b_writer" placeholder="작성자" value="${userId}" readonly>
													</div>
													<div>
													<input type="file" id="file">
													</div>
													<div>
													
														<button  type="button" id="btnSave" class="btn btn-sm btn-primary">작성하기</button>
														<button type="reset" class="btn btn-sm btn-primary">취소</button>
												   </div>
										</form>
						</div>
	</article>
</body>
</html>
