<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<!DOCTYPE html>

<!-- <script src="/jaeho/resources/js/moment.js"></script>  -->
<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/locale/ko.js"></script> -->

<%-- <script src="<c:url value="/resources/js/moment.js" />"></script> --%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세보기</title>
<script>
	function replyJson() {
		var replytext = $("#replytext").val(); //댓글의 내용을 가져온다 
		var b_no = "${boardContent.b_no}" //게시글번호 

		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/reply/insertRest",
			headers : {
				"Content-Type" : "application/json"
			},
			dataType : "text",
			//param형식보다 간편 한 것.
			//JSON=>브라우저와 서버사이에서 오고가는 데이터의 형식
			//JSON.stringify() => 자바스크립트의 객체의 값을 JSON문자열로 반환한다.
			//서버의 @RequestBody 어노테이션으로 인해 반드시 JSON.stringify(), 그리고 content-Type 지정해야한다 .
			data : JSON.stringify({ // 자바스크립트 객체생성=>{}
				b_no : b_no, //왼쪽이 속성명(문자열): 속성값(변수)
				replytext : replytext,

			}),
			success : function(abc) {
				console.log(abc); // console에 success 뜬다
				alert('댓글이 등록되었습니다');
				//댓글 입력 완료 후 댓글 목록 불러오기 함수호출
				listReplyRest("1"); //Rest방식
			},
			error : function() {
				alert('댓글 등록에 실패했습니다.');

			}
		});
	}
	//댓글목록 -Rest방식
	function listReplyRest(num) { // 매개변수 num==현재페이지
		$.ajax({
					type : "get",
					url : "${pageContext.request.contextPath}/reply/list/${boardContent.b_no}/"
							+ num,
					success : function(result) { //ajax가 request를 보내고 response로 돌아온 값 =>result

						$("#listReply").html(result);
					}
				});
	}

	//댓글수정버튼 클릭 시 전체 댓글 상세내용을 가져온다 
	function showReplyModify(r_no) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/reply/detail/" + r_no,
			success : function(result) { //ajax가 request를 보내고 response로 돌아온 값=>result
				$("#modifyReply").html(result);
				//태그 css("속성","값")
				$("#modifyReply").css("visibility", "visible");
			}
		});
	}

	$(function() { //ready함수 => DOM이 문서를 다 읽고 난 후 실행
		$("#btnList").click(function() {
			location.href = "${pageContext.request.contextPath}/board/listAll"
		});

		$("#btnDelete").click(function() {
							alert('정말 삭제하시겠습니까?');
							var b_no = "${boardContent.b_no}"
							document.form1.action = "${pageContext.request.contextPath}/board/delete?b_no";
							document.form1.submit();
						});

		$("#btnUpdate").click(function() {
							var b_title = $("#b_title").val();
							var b_detail = $("#b_detail").val();
							var b_writer = $("#b_writer").val();

							if (b_title == "") {
								alert('제목을 입력하세요');
								document.form1.b_title.focus();
								return;
							}
							if (b_detail == "") {
								alert('내용을 입력하세요');
								document.form1.b_detail.focus();
								return;
							}

							alert('작성하신대로 수정 하시겠습니까?');
							document.form1.action = "${pageContext.request.contextPath}/board/updateBoard";
							document.form1.submit();
						});
		//댓글입력버튼
		$("#btnReply").click(function() {
			var replytext = $("#replytext").val();
			if (replytext.replace(/\s | /gi, "").length == 0) {
				alert('내용을 입력하세요')
			} else {
				replyJson(); //json형식으로 입력
			}
		});
		listReplyRest("1"); //댓글 목록 불러오기
	});
</script>



</head>
<style>
#modifyReply {
	width: 600px;
	height: 130px;
	background-color: gray;
	padding: 10px;
	z-index: 10;
	visibility: hidden;
}

</style>
<body>
	<article>

		<div class="container" role="main">
			<h2>Board Content</h2>

			<form name="form1" method="get">
				<div class="bg-white rounded shadow-sm">
					<div class="board_date">
						작성일:
						<fmt:formatDate value="${boardContent.b_date}"
							pattern="yyyy-MM-dd HH:mm" />
					</div>
					<div class="board_date">조회수 : ${boardContent.b_count}</div>
					<div class="board_title">
					   <div class="col-sm-10">
						제목<input name="b_title" id="b_title"  class="form-control" size="80"
							value="${boardContent.b_title}" >
							</div>
					</div>
					<div class="board_content">
						내용
						<textarea name="b_detail" id="b_detail"   class="form-control" rows="15" cols="3"
							placeholder="내용을 입력하세요">${boardContent.b_detail}</textarea>
					</div>
					<div class="board_info_box">
						<span class="board_author"> 작성자 <input name="b_writer"
							id="b_writer" value="${boardContent.b_writer}" readonly>
						</span>
					</div>
				</div>

				<div style="margin-top: 20px">

					<input type="hidden" name="b_no" value="${boardContent.b_no}">
					<!--본인이 쓴 게시물만 수정,삭제 가능하도록 처리  -->
					<c:if test="${userId eq boardContent.b_writer }">
						<button type="button" class="btn btn-sm btn-primary"
							id="btnUpdate">수정</button>
						<button type="button" class="btn btn-sm btn-primary"
							id="btnDelete">삭제</button>
					</c:if>
					<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				</div>
			</form>


			<div class="my-3 p-3 bg-white rounded shadow-sm"
				style="padding-top: 10px">

				<!--로그인 한 회원에게만 댓글 작성폼이 보이게 처리  -->
				<c:if test="${userId ne null}">
					<div class="row">
						<!-- 댓글 폼  -->
						<div class="col-sm-10">
							<textarea id="replytext" name="replytext" class="form-control"
								placeholder="댓글 작성"></textarea>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-sm btn-primary"
								id="btnReply" style="width: 100%; margin-top: 10px">댓글
								작성</button>
						</div>
					</div>
				</c:if>
			</div>
			<!--댓글 목록 출력할 위치  -->
			<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
				<h6 class="border-bottom pb-2 mb-0">Reply list</h6>
				<div id="listReply"></div>
			</div>
		</div>
	</article>
</body>
</html>