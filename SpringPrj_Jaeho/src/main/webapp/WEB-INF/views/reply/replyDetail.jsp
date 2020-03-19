<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<!-- ���ý�Ʈ  �н�-->

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta charset="EUC-KR">
   <script>
	  //��ۼ���
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
					  alert('������ �Ϸ� �Ǿ����ϴ�.');
					  $("#modifyReply").css("visibility","hidden");
					  listReplyRest("1");
				    }
			    }
		    });
	    });
	$("#btnReplyDelete").click(function(){
			if(confirm('���� �����Ͻðڽ��ϱ�?')) {
        	 $.ajax({
        	   type:"DELETE",
        	   url:"${pageContext.request.contextPath}/reply/delete/${dto.r_no}",
        	   success:function(result){
        		   if(result=="success") {
        			   alert('������ �Ϸ� �Ǿ����ϴ�.');
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
	��۹�ȣ:${dto.r_no}
	<textarea id="detailReplyText"  class="form-control col-sm-12" rows="4" cols="70">${dto.replytext}</textarea>
	<div style="text-align: center;">
		<!--��� ���� ,����,�ݱ�-->
		<button type="button" id="btnReplyUpdate" class="btn btn-sm btn-primary">����</button>
		<button type="button" id="btnReplyDelete" class="btn btn-sm btn-primary">����</button>
		<button type="button" id="btnReplyClose" class="btn btn-sm btn-primary">�ݱ�</button>
	</div>
</body>
</html>