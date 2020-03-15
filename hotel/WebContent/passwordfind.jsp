<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/searchID.css?ver=5">

<title>비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="js/bootstrap.js"></script>

</head>
<body>
	<div id="searchIdPwSearch">
		<%-- <jsp:include page="header.jsp"></jsp:include> --%>
		<br><br><br>

		<div id="sipTitle">FIND ID/PASSWORD</div>
		<div id="siBoxes">
			<div id="siTextbox">
				<p id="siTextTitle">아이디 찾기</p>
				<p id="siText">회원정보를 확인하여 아이디를 찾아드립니다</p>
			</div>
			<div id="siInputTextBoxes">
				<div class="siInputText">
					<input type="text" maxlength="20" class="siInputTextBox"  id="m_name"
						placeholder="이름">
				</div>
				<div class="siInputText">
					<input type="text" maxlength="50" class="siInputTextBox_Email"  id="m_email_id"
						placeholder="이메일">@
					<input type="text" maxlength="50" class="siInputTextBox_Email"  id="m_email_domain"
						placeholder="도메인">
				</div>
			</div>
			<img id="siOkbtn" class="showMask"
				src="./img/btn_ok.png" style="cursor: pointer;">
			<div class="setDiv">


				<div class="mask"></div>
				<div class="window">
					<div id="modalTitleBox">
						<p id="modalTitle">아이디확인</p>
						<p style="cursor: pointer;" class="xbtn">X</p>
					</div>
					<div class="modalTextBox">
						<p id="modalText">
							
						</p>
					</div>
					<img class="close" src="./img/btn_ok_02.png"
						style="cursor: pointer;">

				</div>
			</div>


		</div>
		<div id="pwBoxes">
			<div id="spwTextbox">
				<p id="spwTextTitle">비밀번호 찾기</p>
				<p id="spwText">본인인증으로 비밀번호를 변경하세요</p>
			</div>
			<div class="spwInputText">
				<input type="text" maxlength="20" class="spwInputTextBox" id="m_id2"
					placeholder="아이디">
			</div>
			<div class="spwInputText">
				<input type="text" maxlength="50" class="siInputTextBox_Email" id="m_email_id2"
						placeholder="이메일">@
				<input type="text" maxlength="50" class="siInputTextBox_Email" id="m_email_domain2"
						placeholder="도메인">
			</div>
			<img id="spwOkbtn" class="sendEmail"
				src="./img/btn_email.png" style="cursor: pointer;">

			<div class="setDiv">


 			<div class="mask2"></div>
				<div class="window2">
					<div id="modalTitleBox">
						<p id="modalTitle2">비밀번호찾기</p>
						<p style="cursor: pointer;" class="xbtn">X</p>
					</div>
					<div class="modalTextBox">
						<p id="modalText">입력하신 이메일로 임시비밀번호를 보내드렸습니다.</p>
						<p id="modalText1">임시비밀번호로 로그인 후 비밀번호를 변경하세요.</p>
					</div>
					<img class="close" src="./img/btn_ok_02.png"
						style="cursor: pointer;">

				</div>
			</div>
		</div>
		<%-- <jsp:include page="footer.jsp"></jsp:include> --%>
	</div>
</body>
</html>