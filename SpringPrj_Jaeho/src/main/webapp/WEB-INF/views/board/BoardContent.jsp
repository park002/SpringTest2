<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>
<script>
$(document).ready	(function(){
	//listReply();
	 //댓글목록불러오기
	//댓글 쓰기 버튼 클릭 이벤트 (ajax) 처리
	listReply2();
	$("#btnReply").click(function(){
		var replytext =$("#replytext").val();
		var b_no = "${boardContent.b_no}"
		var b_writer = "${boardContent.b_writer}"
		var param ="replytext="+replytext+"&b_no="+b_no+"&b_writer="+b_writer;
		$.ajax({
			type: "get",
			contentType:"application/json",
			url:"${pageContext.request.contextPath}/reply/insert",
			data:param,
			success: function(){
				alert('댓글이 등록되었습니다.');
				//listReply();
				listReply2();
			}
		});
	});
	 
	function listReply(){
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/reply/listReply?b_no=${boardContent.b_no}",
			success:function(result){ //result=> ajax로 request보냈을때 돌아오는 response
				$("#listReply").html(result);
			}
		});
	}
	function listReply2() {
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/reply/listJson?b_no=${boardContent.b_no}",
			success:function(result){//result=> ajax로 request보냈을때 돌아오는 response
				console.log(result);
				var output="<table>";
				for(var i in result) {
					output+="<tr>";
					output+="<td>"+result[i].b_writer;
					output+="("+changeDate(result[i].b_date)+")<br>";
					output+=result[i].replytext+"</td>";
					output+="<tr>";
				}
				output+="</table>";
				$("#listReply").html(output);
			}
		})
	}
	
	function changeDate(date){ //날짜 변환 함수 작성 timestamp
		let unix_timestamp = 1549312452
		var date = new Date(unix_timestamp * 1000);
		var hours = date.getHours();
		var minutes = "0" + date.getMinutes();
		var seconds = "0" + date.getSeconds();
		var formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
		//console.log(formattedTime);
		return formattedTime;
	}
	
	
	
	
	$("#btnList").click(function(){
		location.href="${pageContext.request.contextPath}/board/listAll"
	});
	
	$("#btnDelete").click(function(){
		if(confirm('정말 삭제하시겠습니까?')) {
			document.form1.action="${pageContext.request.contextPath}/board/delete";
			document.form1.submit();
		}
	});
		
		$("#btnUpdate").click(function(){
			var b_title=$("#b_title").val();
			var b_detail=$("#b_detail").val();
			var b_writer=$("#b_writer").val();
			
			if(b_title=="") {
				alert('제목을 입력하세요');
				document.form1.b_title.focus();
				return;
			}
			if(b_detail=="") {
				alert('내용을 입력하세요');
				document.form1.b_detail.focus();
				return;
			}
			if(b_writer=="") {
				alert('작성자를 입력하세요');
				document.form1.b_writer.focus();
				return;
			}
			document.form1.action="${pageContext.request.contextPath}/board/updateBoardForm";
			document.form1.submit();
			
	});
	
	});
</script>
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
<h2>게시글 보기 </h2>
<br><br><br>
<form name="form1" method="get">
<div>

 작성일자:<fmt:formatDate value="${boardContent.b_date}" pattern="yyyy-MM-dd HH:mm:ss" />
</div>
<div>
조회수 : ${boardContent.b_count}
</div>
<div>
제목<input name="b_title" id="b_title" size="80" value="${boardContent.b_title}">
</div>
내용<textarea name="b_detail" id="b_detail" rows="4" cols="80" placeholder="내용을 입력하세요">${boardContent.b_detail}</textarea>
<div>
작성자 <input name="b_writer" id="b_writer" value="${boardContent.b_writer}"placeholder="이름을 입력하세요">
</div>
<div style="width:650px; text-align:center;">
<input type="hidden" name="b_no" value="${boardContent.b_no}">
<button type="button" id="btnUpdate">수정</button>
<button type="button" id="btnDelete">삭제</button>
<button type="button" id="btnList">목록</button>
</div>
</form>
<div style="width:650px; text-align:center;">
<br>
<textarea rows="5" clos="80" id="replytext" name="replytext" placeholder="댓글을 작성해주세요"></textarea>
<br>
<button type="button" id="btnReply">댓글 작성</button>
</div>

<!--댓글 목록 출력할 위치   -->
<div id="listReply">

</div>
<!--  --> <!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->

<%-- <div id="outter">
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
	  </div> --%>
</body>
</html>