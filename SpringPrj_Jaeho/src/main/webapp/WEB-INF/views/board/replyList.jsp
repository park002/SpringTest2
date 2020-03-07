<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>

</script>

<!-- ���ý�Ʈ  �н�-->
<c:set var="path" value="${pageContext.request.contextPath}" />
	<table style="width: 700px">
          
		<c:forEach items="${list}" var="row">
			<tr>
				<td>${row.replyer}(�ۼ���¥:${row.b_date}) <br> ${row.replytext} <br> 
						<!--���� ��۸� ������ư ���� �ǵ��� ó��   -->
					<c:if test="${userId == row.replyer}">
						<input type="button" id="btnModify" value="����"
							onclick="showReplyModify('${row.r_no}')">
					</c:if>
					<hr>
				</td>
			</tr>
		</c:forEach>
		<!-- ����¡ -->
		<tr style="text-align: center;">
			<td>
				<!--ó�� �������� �̵�: ���� �������� 1���� ũ�� [ó��]������ ��ũ�� ȭ�鿡 ���  --> <c:if
					test="${replyPager.curBlock>1}">
					<a href="javascript:listReplyRest('1')">[ó��]</a>
				</c:if> <!--���� �������� �̵�:  --> <c:if test="${1<replyPager.curBlock}">
					<a href="javascript:listReplyRest('${replyPager.prevPage}')">[����]</a>
				</c:if> <!--�ϳ��� �������� �ݺ��� ����  �������������� ������������   -->
				 <c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd }">
					<!--���� �������� �����۸�ũ ����  -->
					<c:choose>
						<c:when test="${num == replyPager.curPage}">
							<span style="color: red">${num}</span>&nbsp;
	</c:when>
						<c:otherwise>
							<a href="javascript:listReplyRest('${num}')">${num}</a>&nbsp;
	               </c:otherwise>
					</c:choose>
				</c:forEach> <!--���� ������ �������� �̵� : ���� ������ ������ ��ü ������ �������� �۰ų� ������  [����] �����۸�ũ�� ȭ�鿡 ���  -->
				<c:if test="${replyPager.curBlock<=replyPager.totBlock}">
					<a href="javascript:listReplyRest('${replyPager.nextPage}')"> [����]</a>
				</c:if> <!--�� �������� �̵�: ���� �������� ��ü���������� �۰ų� ������ [��] �����۸�ũ ȭ�鿡 ���  --> <c:if
					test="${replyPager.curPage<=replyPager.totPage}">
					<a href="javascript:listReplyRest('${replyPager.totPage}')">[��]</a>
				</c:if>
			</td>
		</tr>
	</table>
	<div id="modifyReply"></div>