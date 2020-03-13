<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/searchID.css?ver=1">
<title>ID/Password Find</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
   $(function(){ 
	   $("#siOkbtn").on('click',function(){
		      if(!$("#m_name").val() || !$("#m_userEmail").val()) {
		    	  alert('이름,이메일을 확인해주세요');
		    	  return false;
		      }
		   $.ajax({
			    type:'POST',
			    url:'${pageContext.request.contextPath}/member/SearchID',
			    data:  {
			    	"m_name" : $("#m_name").val(),
			    	"m_userEmail" : $("#m_userEmail").val()
			    },
			    success: function(result) {
			    	console.log(result);
		                if(result) {
		                	alert('작성하신 이메일로 전송을 완료하였습니다. 인증 후 사용 바랍니다.');
		                }
		                else {
		                      alert('이름,이메일을 확인 해주세요');
		                }
			    }		
		   });//ajax
	   });//on
   })//ready()

</script>
</head>
<body>
 <div id="searchIdPwSearch">
 		<br><br><br>
 		
 		<div id="sipTitle">FIND ID/PASSWORD</div>
 		<div id="siBoxes">
 		<div id="siTextbox">
 		<p id="siTextTitle">아이디 찾기</p>
 		<p id="siText">회원 정보를 확인하여 아이디를 찾아 드립니다</p>
 		     </div>
 		<div id="siInputTextBoxes">    
 			<div class="siInputText">
	   <input type="text" name="m_name" id="m_name" placeholder="Your Name">
	</div>
	 <div class="siInputText">
	  <input type="text" name="m_userEmail" id="m_userEmail" placeholder="Yout Email">
     </div>
       <div id="check"></div>
     
     	</div>
     		<img id="siOkbtn" class="showMask" src="${pageContext.request.contextPath}/resources/img/btn_ok.png" style="cursor: pointer;">
	       
          </div>
</div>

</body>
</html>