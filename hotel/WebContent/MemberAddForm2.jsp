<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">	
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body>
	<!-- <div class="form-group">
			<label for="Name" class="sr-only" >이름</label>
			<input required type="text" class="form-control" placeholder="이름">
	</div>  -->
	<form id="f" action="emailSendAction.do" method="post">
		<%-- <c:if test="${!empty msg}">
			<script> alert('${msg}')</script>
			<% session.removeAttribute("msg"); %>
		</c:if> --%>
		<c:if test="${msg ne null}">
			<script>
				alert('${msg}')
			</script>
			<c:remove var="msg" />
		</c:if>
		<div class="container">
			<table class="table"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2"
							style="background-color: #fafafa; text-align: center;"><h1>Hotel
								Delluna</h1></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>ID</h5></td>
						<td>
							<div class="row">
								<div class="col-xs-9">
									<input id="customer_id" class="form-control" name="customer_id"
										size="20" placeholder="ID를 입력해주세요">
								</div>
								<div class="col-xs-3">
									<button id="chk" type="button"
										class="btn btn-block btn-secondary">중복검사</button>
									<span id="msg2" style="color: red"></span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>Password</h5></td>
						<td><input id="PASSWORD" class="form-control" type="password"
							name="PASSWORD" size="20" placeholder="비밀번호를 입력해주세요"></td>
					</tr>
					<tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>우편번호</h5></td>
						<!-- <td colspan="2"> -->
						<td>
							<div class="row">
								<div class="col-xs-9">
									<input id="sample6_postcode" class="form-control" type="text"
										name="ZIP" size="20" placeholder="우편번호" readonly>
								</div>
								<div class="col-xs-3">
									<button id="" type="button"
										onclick="sample6_execDaumPostcode()"
										class="btn btn-block btn-secondary">우편번호찾기</button>
								</div>
							</div>
							<div class="row" style="padding-top: 15px">
								<div class="col-xs-12">
									<input id="sample6_address" class="form-control" 
										placeholder="주소">
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<input class="form-control" id="sample6_detailAddress"
										placeholder="상세주소" >
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<input class="form-control" id="sample6_extraAddress"
										placeholder="기본주소" >
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>이름</h5></td>
						<td><input id="CUSTOMER_NAME" class="form-control"
							type="text" name="CUSTOMER_NAME" size="20"
							placeholder="성함을 입력해주세요"></td>
					</tr>
					<tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>E-Mail</h5></td>
						<td><input id="EMAIL" class="form-control" type="email"
							name="EMAIL" size="20" placeholder="이메일을 입력해주세요"></td>
					</tr>
						<!-- <tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>userEmail</h5></td>
						<td><input id="userEmail" class="form-control" type="email"
							name="userEmail" size="20" placeholder="이메일을 입력해주세요"></td>
					</tr> -->
					<tr>
						<td style="background-color: #fafafa; text-align: center;"><h5>전화번호</h5></td>
						<td><input id="TEL" class="form-control" type="number"
							name="TEL" size="20" placeholder="전화번호를 입력해주세요"></td>
					</tr>
					<tr>
						<td colspan="2">
							<!-- <button class="btn btn-primary pull-right" type="button">등록하기</button> -->
							<!--onclick="location.href='emailSendAction.jsp'"  -->
							<button id="create" class="btn btn-primary pull-right">등록하기</button> 
								<button id="historyback" class="btn btn-primary pull-right"  onclick='history.back()'>이전</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- /* $('#chk').click(function() { $(this).text('사용 가능한 ID입니다.') }); */ -->
			<script>
			let dublicpasdfjlksdf = false; 
			/* $(function() {
				$('#create').click(function() {
				   if(!'#chk').click(function({
					  alert('중복확인을 해주시기 바랍니다.')
					  return true;
				   })
				}
			)}  */
			
// 			function beforeSubmit() {
// 				var duplicate= false;
// 				if (duplicate == false) {
// 					alert("아이디 중복확인을 해주세요!");
// 					return false;
// 				}
// 			}
			
			$('#customer_id').change(function() { //input에 있는 값이 변경될떄의 이벤트  사용자가 값을 바꿨을 때 
				dublicpasdfjlksdf = false 
				});
			
				$(function() {
					$('#chk').click(function() {
					/* 	customer_id.trim().length==0 */
						var customer_id = $('#customer_id').val();
						if (!customer_id.trim()) { //값이 비었을떄 
							alert('아이디를 적어주세요')
							return false;
						}
						var sendData = "customer_id=" + customer_id;
						$.post('confirmId.jsp', sendData, function(msg2) {
							dublicpasdfjlksdf = (msg2.trim() == '사용할 수 있는 아이디입니다.');  // 연산자 우선순위. 
							$('#msg2').html(msg2);
						});
					});
				});
				
				$('#f').submit(function(e){
					if (!$('#customer_id').val()) {
						alert('ID값을 입력해주세요');
						e.preventDefault();
						
					}else if(!dublicpasdfjlksdf) {
						alert('id 중복검사를 해주세요')
						e.preventDefault();
					}
					else if (!$('#PASSWORD').val()) {
						alert('비밀번호를 입력해주세요');
						e.preventDefault();
						
					} else if (!$('#sample6_postcode').val()) {
						alert('우편번호를 입력해주세요');
						e.preventDefault();
					}
					else if (!$('#sample6_address').val()) {
						alert('주소를 입력해주세요');
						e.preventDefault();
					}
					else if (!$('#sample6_detailAddress').val()) {
						alert('상세주소를 입력해주세요');
						e.preventDefault();
					}
					else if (!$('#sample6_extraAddress').val()) {
						alert('기본주소를 입력해주세요');
						e.preventDefault();
					} else if (!$('#CUSTOMER_NAME').val()) {
						alert('이름을 입력해주세요');
						e.preventDefault();
					} else if (!$('#EMAIL').val()) {
						alert('Email 을 확인해주세요');
						e.preventDefault();
					} else if (!$('#TEL').val()) {
						alert('전화번호를 입력해주세요');
						e.preventDefault();
					}
				});
				function sample6_execDaumPostcode() {
					new daum.Postcode(
							{
								oncomplete : function(data) {/* 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분. 각 주소의 노출 규칙에 따라 주소를 조합한다. 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.*/
									var addr = ''; /* 주소 변수*/
									var extraAddr = ''; /* 참고항목 변수*//*사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.*/
									if (data.userSelectedType === 'R') { /* 사용자가 도로명 주소를 선택했을 경우*/
										addr = data.roadAddress;
									} else { /* 사용자가 지번 주소를 선택했을 경우(J)*/
										addr = data.jibunAddress;
									}/* 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.*/
									if (data.userSelectedType === 'R') {/* 법정동명이 있을 경우 추가한다. (법정리는 제외) 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.*/
										if (data.bname !== ''
												&& /[동|로|가]$/g.test(data.bname)) {
											extraAddr += data.bname;
										}/* 건물명이 있고, 공동주택일 경우 추가한다.*/
										if (data.buildingName !== ''
												&& data.apartment === 'Y') {
											extraAddr += (extraAddr !== '' ? ', '
													+ data.buildingName
													: data.buildingName);
										}/* 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.*/
										if (extraAddr !== '') {
											extraAddr = ' (' + extraAddr + ')';
										}/* 조합된 참고항목을 해당 필드에 넣는다.*/
										document
												.getElementById("sample6_extraAddress").value = extraAddr;
									} else {
										document
												.getElementById("sample6_extraAddress").value = '';
									}/* 우편번호와 주소 정보를 해당 필드에 넣는다.*/
									document.getElementById('sample6_postcode').value = data.zonecode;
									document.getElementById("sample6_address").value = addr;/* 커서를 상세주소 필드로 이동한다.*/
									document.getElementById(
											"sample6_detailAddress").focus();
								}
							}).open();
				}
			</script>
		</div>
	</form>
	<%-- <c:if test="${!empty msg}">
		<script>alert('${msg}')</script>
		<%session.removeAttribute("msg");%>
	</c:if> --%>
</body>