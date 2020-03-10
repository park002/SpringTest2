<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<!-- ���ý�Ʈ  �н�-->
<c:set var="path" value="${pageContext.request.contextPath}" />

		  <div class="bg-white rounded shadow-sm">
		               <c:forEach items="${list}" var="row">
			                    <tr>
				                     <td>
				                          <div class="board_info_box">
				                                 <span class="board_author">
				                                               ${row.replyer} 
				                                       (<fmt:formatDate pattern= "yyyy-MM-dd HH:mm"  value="${row.b_date}"/>)
				                                 </span>
				                         </div>
				                          <div class="col-sm-10"> ${row.replytext} 
						                         <!--���� ��۸� ������ư ���� �ǵ��� ó��   -->
					                              <c:if test="${userId == row.replyer}">
					                                   <button type="button" id="btnModify" class="btn btn-link btn-sm" onclick="showReplyModify('${row.r_no}')"> ����</button>
					                              </c:if>
					                       </div>
					                    <hr>
				                    </td>
			                     </tr>
		                </c:forEach>
	          </div>
		 <!-- ����¡ -->
			    <div id="paginationBox">
		             	 <ul class="pagination">
				              <!--ó�� �������� �̵�: ���� �������� 1���� ũ�� [ó��]������ ��ũ�� ȭ�鿡 ���  -->
				                <c:if test="${1<replyPager.curBlock}">
		                                   <li class="page-item"><a class="page-link" href="#" onclick="javascript:listReplyRest('1')">ó��</a></li>
			                    </c:if> <!--���� �������� �̵�:  --> 
				                <c:if test="${1<replyPager.curBlock}">
				                           <li class="page-item"><a class="page-link" href="#" onclick="javascript:listReplyRest('${replyPager.prevPage}')">����</a></li>
				                </c:if> <!--�ϳ��� ������ �ݺ��� ����  �������������� ������������   -->
				                    <c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd }">
					                        <!--���� �������� �����۸�ũ ����  -->
					                            <c:choose>
						                                  <c:when test="${num == replyPager.curPage}">
					                                                <li class="page-item">	
					                                                         <span class="page-link" style="color: red">${num}</span>
					                                                 </li>&nbsp;
	                                                      </c:when>
						                                          <c:otherwise>
							                                              <li class="page-item">
							                                                   <a class="page-link"  href="#" onclick="javascript:listReplyRest('${num}')">${num}</a>
							                                              </li>&nbsp;
	                                                              </c:otherwise>
					                            </c:choose>
				                    </c:forEach> <!--���� ������ ������� �̵� : ���� ������ ���� ��ü ������ ������ �۰ų� ������  [����] �����۸�ũ�� ȭ�鿡 ���  -->
				                <c:if test="${replyPager.curBlock<=replyPager.totBlock}">
					                    <li class="page-item"><a class="page-link"  href="#" onclick="javascript:listReplyRest('${replyPager.nextPage}')"> ����</a></li>
			                    </c:if> <!--�� �������� �̵�: ���� �������� ��ü���������� �۰ų� ������ [��] �����۸�ũ ȭ�鿡 ���  --> 
				              <c:if test="${replyPager.curPage<=replyPager.totPage}">
				                        <li class="page-item">	<a class="page-link" href="#" onclick="listReplyRest('${replyPager.totPage}')">��</a></li>
			                  </c:if>
	                    </ul>
                 </div>
	        <div id="modifyReply">
	        
	        </div>
