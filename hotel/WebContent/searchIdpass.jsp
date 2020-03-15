<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/searchID.css?ver=5">




<title>호텔 델루나</title>
<script>
$(document).ready(function(){
    $('#siOkbtn').on('click', function(){
    	//alert($('#m_id').val());
        $.ajax({
            type: 'POST',
            url: '/hotel/searchID.do',
            data: {
                "customerName" : $('#customerName').val(),
                "userEmail" : $('#userEmail').val(),
            },
            success: function(abc){
                if(abc.result== "null"){
					$('#modalText.id').html('가입기록이 없습니다.<br>이름 또는 이메일을 다시 한 번 확인해주세요!');
                }
                else{
                	$('#modalText.id').html('<br>회원님의 아이디는 <b>'+abc.result+'</b>입니다.');
                }
            }
        });    //end ajax    
    });    //end on    
    
    $('#spwOkbtn').on('click', function(){
    	//alert($('#customer_id').val());
   //$('#pwModal')
    	//$('#modalText.pw')
        $.ajax({
            type: 'POST',
            url: '/hotel/searchPW.do',
            data: {
                "customer_id" : $('#customer_id').val(),
                "userEmail" : $('#userEmail2').val(),
            },
            success: function(data){
                if(data.result2== "null"){
					$('#modalText.id').html('가입기록이 없습니다.<br>아이디 또는 이메일을 다시 한 번 확인해주세요!');
                }
                else {
                	$('#modalText.id').html('작성하신 이메일로 전송을 완료했습니다 <br> 확인해주세요 감사합니다.');
                }
                
            }
        });    //end ajax    
    });    //end on   
});
</script>
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
					<input type="text" maxlength="20" class="siInputTextBox"  id="customerName"
						placeholder="이름">
				</div>
				<div class="siInputText">
					<input type="text" maxlength="50" class="siInputTextBox"  id="userEmail" 
						placeholder="이메일">
				</div>
			</div>
			<img id="siOkbtn" class="showMask"
				src="./images/btn_ok.png" style="cursor: pointer;">
				
				
			     <div class="setDiv">
				<div class="mask">   </div>
				<div class="window">
					<div id="modalTitleBox">
						<p id="modalTitle">아이디확인</p>
						<p style="cursor: pointer;" class="xbtn">X</p>
					</div>
					<div class="modalTextBox">
						<p id="modalText" class="id">
							
						</p>
					</div>
					<img class="close" src="./images/btn_ok_02.png"
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
				<input type="text" maxlength="20" class="spwInputTextBox" id="customer_id"
					placeholder="아이디">
			</div>
			<div class="spwInputText">
				<input type="text" maxlength="50" class="spwInputTextBox" id="userEmail2"
						placeholder="이메일">
			</div>
		
			<img id="spwOkbtn" class="sendEmail"
				src="./images/btn_email.png" style="cursor: pointer;">

			<div class="setDiv">

			<div class="mask2"></div>
				<div class="window2">
					<div id="modalTitleBox">
						<p id="modalTitle2">비밀번호찾기</p>
						<p style="cursor: pointer;" class="xbtn">X</p>
					</div>				
					<div id="pwModal" class="modalTextBox">
		
			 <p id="modalText" class="id">  </p>  
					</div>
					<img class="close" src="./images/btn_ok_02.png"
						style="cursor: pointer;">
				</div>
			</div>
		</div>
		<%-- <jsp:include page="footer.jsp"></jsp:include> --%>
	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
	function wrapWindowBySendEmail(){
		// 화면의 높이와 너비를 변수로 만듭니다.
		var maskHeight = $(document).height();
		var maskWidth = 1200;
		//var maskWidth = $(window).width();

		// 마스크의 높이와 너비를 화면의 높이와 너비 변수로 설정합니다.
		$('.mask2').css({
			'width' : maskWidth,
			'height' : maskHeight
		});

		// fade 애니메이션 : 1초 동안 검게 됐다가 80%의 불투명으로 변합니다.

		$('.mask2').fadeTo("fast", 0.6);
//($(window).width() - $('.window').width()) / 2
		// 레이어 팝업을 가운데로 띄우기 위해 화면의 높이와 너비의 가운데 값과 스크롤 값을 더하여 변수로 만듭니다.
		var left = ($(window).scrollLeft() + 340);
		var top = ($(window).scrollTop() + ($(window).height() - $('.window2')
				.height()) / 2);

		// css 스타일을 변경합니다.
		$('.window2').css({
			'left' : left,
			'top' : top,
			'position' : 'absolute'
		});

		// 레이어 팝업을 띄웁니다.
		$('.window2').show();
	}
	function wrapWindowByMask() {
		// 화면의 높이와 너비를 변수로 만듭니다.
		var maskHeight = $(document).height();
		var maskWidth = 1200;
		//var maskWidth = $(window).width();

		// 마스크의 높이와 너비를 화면의 높이와 너비 변수로 설정합니다.
		$('.mask').css({
			'width' : maskWidth,
			'height' : maskHeight
		});

		// fade 애니메이션 : 1초 동안 검게 됐다가 80%의 불투명으로 변합니다.

		$('.mask').fadeTo("fast", 0.6);
//($(window).width() - $('.window').width()) / 2
		// 레이어 팝업을 가운데로 띄우기 위해 화면의 높이와 너비의 가운데 값과 스크롤 값을 더하여 변수로 만듭니다.
		var left = ($(window).scrollLeft() + 340);
		var top = ($(window).scrollTop() + ($(window).height() - $('.window')
				.height()) / 2);

		// css 스타일을 변경합니다.
		$('.window').css({
			'left' : left,
			'top' : top,
			'position' : 'absolute'
		});

		// 레이어 팝업을 띄웁니다.
		$('.window').show();
	}

	$(document).ready(function() {
		// showMask를 클릭시 작동하며 검은 마스크 배경과 레이어 팝업을 띄웁니다.
		$('.showMask').click(function(e) {
			// preventDefault는 href의 링크 기본 행동을 막는 기능입니다.
			e.preventDefault();
			wrapWindowByMask();
		});

		// 닫기(close)를 눌렀을 때 작동합니다.
		$('.window .close').click(function(e) {
			e.preventDefault();
			$('.mask, .window').hide();
		});
		$('.window .xbtn').click(function(e) {
			e.preventDefault();
			$('.mask, .window').hide();
		});

		// 뒤 검은 마스크를 클릭시에도 모두 제거하도록 처리합니다.
		$('.mask').click(function() {
			$(this).hide();
			$('.window').hide();
		});
		
		
		//sendEmail
		$('.sendEmail').click(function(e) {
			// preventDefault는 href의 링크 기본 행동을 막는 기능입니다.
			e.preventDefault();
			wrapWindowBySendEmail();
		});

		// 닫기(close)를 눌렀을 때 작동합니다.
		$('.window2 .close').click(function(e) {
			e.preventDefault();
			$('.mask2, .window2').hide();
		});
		$('.window2 .xbtn').click(function(e) {
			e.preventDefault();
			$('.mask2, .window2').hide();
		});

		// 뒤 검은 마스크를 클릭시에도 모두 제거하도록 처리합니다.
		$('.mask2').click(function() {
			$(this).hide();
			$('.window2').hide();
		});
		
		
	});
</script>
</html>