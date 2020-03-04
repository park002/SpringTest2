<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <!-- 컨택스트  패스-->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>
<table style="width:700px">
<!--댓글 목록  -->
<c:forEach items="${list}" var="row">
<tr>
<td>${row.replyer} (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${row.b_date}" />)
<br>
${row.replytext}
<br>
<!--본인 댓글만 수정버튼 생성 되도록 처리   -->
<c:if test="${userId == row.replyer}">
<input type="button" id="btnModify" value="수정" onclick="showReplyModify('${row.r_no}')">
</c:if>
<hr>
</td>
</tr>
</c:forEach>
<!-- 페이징 --> 
<tr style="text-align: center;">
<td>
<!--처음 페이지로 이동: 현재 페이지가 1보다 크면 [처음]하이퍼 링크를 화면에 출력  -->
<c:if test="${replyPager.curBlock>1}"><a href="javascript:listReply('1')">[처음]</a>
</c:if>
<!--이전 페이지로 이동:  -->
<c:if test="${1<replyPager.curBlock}"><a href="javascript:listReply('${replyPager.prevPage}')">[이전]</a>
</c:if>
 <!--하나의 블럭에서 반복문 수행  시작페이지부터 끝페이지까지   -->
 <c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd }" >
	<!--현재 페이지면 하이퍼링크 제거  -->
	<c:choose>
	<c:when test="${num == replyPager.curPage}">
	<span style="color:yellow">${num}</span>&nbsp;
	</c:when>
	<c:otherwise>
	<a href="javascript:listReply('${num}')">${num}</a>&nbsp;
	</c:otherwise>
	</c:choose>
	</c:forEach>
	<!--다음 페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면  [다음] 하이퍼링크를 화면에 출력  -->
	<c:if test="${replyPager.curBlock<replyPager.totBlock}">
	 <a href="javascript:listReply('${replyPager.nextBlock}')"> [다음]</a>
	</c:if>
	 <!--끝 페이지로 이동: 현재 페이지가 전체페이지보다 작거나 같으면 [끝] 하이퍼링크 화면에 출력  -->
	 <c:if test="${replyPager.curPage<=replyPager.totPage}"> 
	 <a href="javascript:listReply('${replyPager.totPage}')">[끝]</a> 
	 </c:if>
</td>
</tr>
</table>
<div id="modifyReply"></div>
</body>
</html>