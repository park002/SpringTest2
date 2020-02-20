<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<!-- <script>
	//이전 버튼 이벤트 눌렀다면
	function fn_prev(page, range, rangeSize) {
		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		var url = "${pageContext.request.contextPath}/board/listAll";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}

	//페이지 번호 클릭 했다면
	function fn_pagination(page, range, rangeSize) { //현재페이지, 현재블럭페이지,초기값 페이지 블럭 10
		var url = "${pageContext.request.contextPath}/board/listAll";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}

	//다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {//-2-,2, 5 라쳐보자
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		var url = "${pageContext.request.contextPath}/board/listAll";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
</script> -->
<script>
function list(page) {
	location.href="${pageContext.request.contextPath}/board/listAll?curPage="+page
}
</script>
<body>
	<input type="button" onclick="location.href='/jaeho/board/createform'"
		value="글 작성">
	<table class="table table-board" border="1px" width="80%"
		align="center">
		<tr>
			<th style="width: 10%">글 번호</th>
			<th style="width: 30%">제목</th>
			<th style="width: 20%">작성자</th>
			<th style="width: 20%">날짜</th>
			<th style="width: 20%">조회수</th>
		</tr>
		<c:forEach items="${map.list}" var="row">
			<tr>
				<td>${row.b_no}</td>
				<td><a href="/jaeho/board/detail?b_no=${row.b_no}">${row.b_title}</a></td>
				<td>${row.b_writer}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${row.b_date}" /></td>
				<td><span> ${row.b_count}</span></td>
			</tr>
		</c:forEach>
		<tr>
	<td colspan="5">
	<!--처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음] 하이퍼링크를 화면에 출력 -->
	<c:if test="${map.boardPager.curBlock >1}">
	<a href="javascript:list('1')">[처음]</a>
	</c:if>
	<!--이전 페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크 화면에 출력  -->
	<c:if test="${map.boardPager.curBlock >1}">
	<a href="javascript:list('${map.boardPager.prevPage}')">[이전]</a>
	</c:if>
	<!--하나의 블럭에서 반복문 수행  시작페이지부터 끝페이지까지   -->
	<c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
	<!--현재 페이지면 하이퍼링크 제거  -->
	<c:choose>
	   <c:when test="${num == map.boardPager.curPage}">
	     <span style="color:red">${num}</span>&nbsp;
	   </c:when>
        <c:otherwise>
          <a href="javascript:list('${num}')">${num}</a>&nbsp;
        </c:otherwise>	
	</c:choose>
	</c:forEach>
	<!--다음 페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면  [다음] 하이퍼링크를 화면에 출력  -->
	 <c:if test="${map.boardPager.curBlock<=map.boardPager.totBlock }">
	 			<a href="javascript:list('${map.boardPager.nextPage}')"> [다음] </a>
	 </c:if>
	<!--끝 페이지로 이동: 현재 페이지가 전체페이지보다 작거나 같으면 [끝] 하이퍼링크 화면에 출력  -->
        <c:if test="${map.boardPager.curPage<=map.boardPager.totPage}">
        		<a href="javascript:list('${map.boardPager.totPage}')">[끝]</a>
        </c:if>	
	</td>
	</tr>
	</table>
	
</body>
</html>
