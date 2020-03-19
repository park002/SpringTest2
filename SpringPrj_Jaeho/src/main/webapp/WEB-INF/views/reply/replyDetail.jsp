<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<!-- 컨택스트  패스-->

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta charset="EUC-KR">
   <script>
	  //댓글수정
	      $("#btnReplyUpdate").click(function(){
		       var detailReplyText = $("#detailReplyText").val();
		     $.ajax({
			 type : "PATCH",
			 url : "${pageContext.request.contextPath}/reply/update/${dto.r_no}",
			 headers:{ "Content-Type" : "application/json"},
			 dataType:"text",
			 data:JSON.stringify({
				  replytext : detailReplyText,
			}),
			 success:function(result) {
				if(result=="success") {
					  alert('수정이 완료 되었습니다.');
					  $("#modifyReply").css("visibility","hidden");
					  listReplyRest("1");
				    }
			    }
		    });
	    });
	$("#btnReplyDelete").click(function(){
			if(confirm('정말 삭제하시겠습니까?')) {
        	 $.ajax({
        	   type:"DELETE",
        	   url:"${pageContext.request.contextPath}/reply/delete/${dto.r_no}",
        	   success:function(result){
        		   if(result=="success") {
        			   alert('삭제가 완료 되었습니다.');
        			   $("#modifyReply").css("visibility","hidden");
        			   listReplyRest("1");
        		   }
        	   }                
        	 });
		}
	});
	
	$("#btnReplyClose").click(function(){
		  listReplyRest("1");
	});
	

</script>

</head>
<body>
	댓글번호:${dto.r_no}
	<textarea id="detailReplyText"  class="form-control col-sm-12" rows="4" cols="70">${dto.replytext}</textarea>
	<div style="text-align: center;">
		<!--댓글 수정 ,삭제,닫기-->
		<button type="button" id="btnReplyUpdate" class="btn btn-sm btn-primary">수정</button>
		<button type="button" id="btnReplyDelete" class="btn btn-sm btn-primary">삭제</button>
		<button type="button" id="btnReplyClose" class="btn btn-sm btn-primary">닫기</button>
	</div>
</body>
</html>