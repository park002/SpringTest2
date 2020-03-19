<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/board/BoardLayout/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <html>
   <head>
   <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>글 목록</title>
            </head>
                 <script>
                       $(document).ready(function(){
    	                     $("#btnWrite").click(function(){
			                         location.href="${pageContext.request.contextPath}/board/createform";
                      	         });
    	                     $("#btnLogout").click(function(){
    	                    	      location.href="${pageContext.request.contextPath}/member/Logout"
    	                     });
    	                     $("#btnRemove").click(function(){
    	                    	 location.href="${pageContext.request.contextPath}/member/Remove?m_name="+"${userName}"
    	                     });
    	     	         });
    
	                         function list(page) {
		                       location.href = "${pageContext.request.contextPath}/board/listAll?curPage="
				                  +page+"&searchOption=${map.searchOption}"+"&keyword=${map.keyword}"
	                          }
	                         
	                         
	                         
                 </script>
              <body>
                   <div class="container">
                         <div class="table-responsive">
                                 <table class="table table-striped table-sm">
			       		               <!--로그인한 사용자만 글쓰기,로그아웃,회원탈퇴  버튼 활성화  -->
                                               <c:if test="${userName ne null}"> 
                                                                안녕하세요 ${userName}님
                                                     <button class="btn btn-link btn-sm"  id="btnLogout">로그아웃</button>
		                                             <button class="btn btn-link btn-sm"  id="btnWrite">글쓰기</button>
		                                             <button class="btn btn-link btn-sm"  id="btnRemove">회원탈퇴</button>
                                                </c:if>
		                                <colgroup>
		                	                 <col style="width: 5%;" />
		                	                 <col style="width: auto;" />
		                 	                 <col style="width: 15%;" />
			                                 <col style="width: 10%;" />
		                 	                 <col style="width: 10%;" />
	                                   </colgroup>
		                              <thead>
		                                      <tr>
			                                        <th>NO</th>
				                                   <th>글제목</th>
				                                   <th>작성자</th>
				                                   <th>조회수</th>
				                                   <th>작성일</th>
		                                     </tr>
			                         <thead>
			                             <tbody>
		                                         <c:forEach items="${map.list}" var="row">
			                                            <tr>
				                                               <td>${row.b_no}</td>
				                                               <td><a href="#" onclick="location.href='/jaeho/board/detail?b_no=${row.b_no}'">${row.b_title}
				                                                     <c:if test="${row.recnt>0}">
				                                                     <span style="color:red;">(${row.recnt}) </span>
				                                                     </c:if></a>
				                                               </td>
				                                               <td>${row.b_writer}</td>
				                                               <td><span> ${row.b_count}</span></td>
				                                               <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${row.b_date}" /></td>
			                                            </tr>
		                                        </c:forEach>
		                                  </tbody>
	                              </table>
	                         </div>
			            <!--게시글 페이징  -->
	                       <div id="paginationBox">
		                                 <ul class="pagination">
				                        <!--처음페이지로 이동 : 현재 블럭이 1보다 크면  [처음] 하이퍼링크를 화면에 출력 --> 
				                                 <c:if test="${map.boardPager.curBlock >1}">
	     		                                        <li class="page-item">
	     		                                                <a class="page-link" href="#" onclick="javascript:list('1')">처음</a></li>
				                                 </c:if> 
				                                 <!--이전 페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크 화면에 출력  -->
			                                     <c:if test="${map.boardPager.curBlock >1}">
				                                        <li class="page-item">
				             	                                <a class="page-link"  href="#" onclick="javascript:list('${map.boardPager.prevPage}')">이전</a></li>
				                                 </c:if>
				                              <!--하나의 블럭에서 반복문 수행  시작페이지부터 끝페이지까지   -->
				                              <c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
				            	                            <!--현재 페이지면 하이퍼링크 제거  -->
					                                          <c:choose>
					            	                                   <c:when test="${num == map.boardPager.curPage}">
						                                                      <li class="page-item">
						                                                              <span style="color: red" class="page-link">${num}</span>&nbsp; </li>
	                                                                   </c:when>
	 					                                                       <c:otherwise>
					                                                               <li class="page-item">
					                                                                    <a class="page-link" href="#" onclick="javascript:list('${num}')">${num}</a></li>&nbsp;
                                                                               </c:otherwise>
					                                          </c:choose>
			           	                          </c:forEach>
			           	               <!--다음 페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면  [다음] 하이퍼링크를 화면에 출력  -->
				                             <c:if test="${map.boardPager.curBlock<=map.boardPager.totBlock }">
			        	                                   <li class="page-item">
			        	                                             <a class="page-link" href="#" onclick="javascript:list('${map.boardPager.nextPage}')"> 다음 </a> </li>
				                             </c:if> <!--끝 페이지로 이동: 현재 페이지가 전체페이지보다 작거나 같으면 [끝] 하이퍼링크 화면에 출력  -->
			            	                 <c:if test="${map.boardPager.curPage<=map.boardPager.totPage}">
					                                      <li class="page-item">
					                                                 <a class="page-link" href="#" onclick="javascript:list('${map.boardPager.totPage}')">끝</a></li>
				                            </c:if>
                                      </ul>
                                 </div>
                            </div>
                                                   <!--검색  -->
                           <div class="form-group row justify-content-center">
                                     <div class="w100" style="padding-right:30px">
	                                          <form name="form1" method="get" action="/jaeho/board/listAll">
		                                                 <select class="mdb-select md-form" name="searchOption">
			                                                       <!--검색 조건을 검색처리 후  결과화면을 보여주기위해 c:out 출력 태그 사용  -->
			                                                        <option value="writer"
				                                                              <c:out value="${map.searchOption == 'writer' ? 'selected':''}" />>작성자</option>
			                                                        <option value="content"
				                                                              <c:out value="${map.searchOption == 'content' ? 'selected':''}"/>>내용</option>
			                                                        <option value="title"
				                                                              <c:out value="${map.searchOption== 'title' ? 'selected':''}"/>>제목</option>
		                                                 </select>
		                                                 <input name="keyword"  value="${map.keyword}"> 
		                                                     <button class="btn btn-outline-dark btn-sm" style="width:60px; height:27"> 조회 </button>
	                                          </form>
	                                 </div>
	                       </div>
                  </body>
          </html>
