<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<!-- 컨택스트  패스-->
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
						                         <!--본인 댓글만 수정버튼 생성 되도록 처리   -->
					                              <c:if test="${userId == row.replyer}">
					                                   <button type="button" id="btnModify" class="btn btn-link btn-sm" onclick="showReplyModify('${row.r_no}')"> 수정</button>
					                              </c:if>
					                       </div>
					                    <hr>
				                    </td>
			                     </tr>
		                </c:forEach>
	          </div>
		 <!-- 페이징 -->
			    <div id="paginationBox">
		             	 <ul class="pagination">
				              <!--처음 페이지로 이동: 현재 페이지가 1보다 크면 [처음]하이퍼 링크를 화면에 출력  -->
				                <c:if test="${1<replyPager.curBlock}">
		                                   <li class="page-item"><a class="page-link" href="#" onclick="javascript:listReplyRest('1')">처음</a></li>
			                    </c:if> <!--이전 페이지로 이동:  --> 
				                <c:if test="${1<replyPager.curBlock}">
				                           <li class="page-item"><a class="page-link" href="#" onclick="javascript:listReplyRest('${replyPager.prevPage}')">이전</a></li>
				                </c:if> <!--하나의 블럭에서 반복문 수행  시작페이지부터 끝페이지까지   -->
				                    <c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd }">
					                        <!--현재 페이지면 하이퍼링크 제거  -->
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
				                    </c:forEach> <!--다음 페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면  [다음] 하이퍼링크를 화면에 출력  -->
				                <c:if test="${replyPager.curBlock<=replyPager.totBlock}">
					                    <li class="page-item"><a class="page-link"  href="#" onclick="javascript:listReplyRest('${replyPager.nextPage}')"> 다음</a></li>
			                    </c:if> <!--끝 페이지로 이동: 현재 페이지가 전체페이지보다 작거나 같으면 [끝] 하이퍼링크 화면에 출력  --> 
				              <c:if test="${replyPager.curPage<=replyPager.totPage}">
				                        <li class="page-item">	<a class="page-link" href="#" onclick="listReplyRest('${replyPager.totPage}')">끝</a></li>
			                  </c:if>
	                    </ul>
                 </div>
	        <div id="modifyReply">
	        
	        </div>
