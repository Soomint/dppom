<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>정보수정 페이지</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />

<link href="./resources/bootstrap3.3.7/css/join1.css" type="text/css"
	rel="stylesheet">
<link href="./resources/bootstrap3.3.7/css/join2.css" type="text/css"
	rel="stylesheet">
<link href="./resources/bootstrap3.3.7/css/join3.css" type="text/css"
	rel="stylesheet">
<link href="./resources/bootstrap3.3.7/css/join4.css" type="text/css"
	rel="stylesheet">
<link href="./resources/bootstrap3.3.7/css/join5.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ include file="/WEB-INF/layout/head.jsp"%>
<style>
.navbar-brand {
	color: #6173f4;
}
</style>
<script>
//비밀번호 확인 기능 keyup(): 키보드 눌렀다가 뗐을때 
$('#pass').keyup(function(){
	$('#passCorrect').text(''); //span 태그는  value  속성 없음
	$('#Re_pass').val(''); // 문제 1 : 암호를 입력할때 무조건 암호확인 부분을 clear 시킨다.
});

$('#Re_pass').keyup(function(){  // pwd2에서 누를때 검사 시작
	var cmpStr1 = $('#pass').val();
	var cmpStr2 = $(this).val(); // this-> pwd2
	
	if(cmpStr1 == cmpStr2){
		$('#passCorrect').text('');
		//문제2: 암호가 일치할 경우 텍스트는 빨간색으로 표현한다.
		//$('#msg').html('<b style="color:red">암호가 일치합니다^0^*</b>');
		$('#passCorrect').html('<b>암호가 일치합니다^0^*</b>');
		$('#passCorrect').css("color","red");  //샘
	}
	else{
		$('#passCorrect').text('');
		$('#passCorrect').html('<strong>암호가 틀립니다.ㅠ_ㅠ</strong>');
		//$('#msg').attr({style:"color:#000000","font-weight":"bold"});  //샘1
		//$('#msg').attr({"color":"#000000","font-weight":"bold"}); 컬러는 style로 지정해야함
		$('#passCorrect').attr({style:"color:#000000; font-weight:bold"});  //샘2
		//	font-weight는 단독 또는 style속성 안에서 지정 가능 
	}
});


function passchk(){
	var f = document.memberModifyFrm;
	
	if(f.pass.value != f.Re_pass.value){
		document.getElementById("passCorrect").innerHTML ='일치하지 않습니다';
		document.getElementById("passCorrect").style.color='red';
		document.getElementById("passCorrect").style.fontWeight='bold';
		f.Re_pass.value='';
	}
	else{
		//alert('비밀번호가 일치합니다');
		document.getElementById("passCorrect").innerHTML ='일치';
		document.getElementById("passCorrect").style.color='blue';
		document.getElementById("passCorrect").style.fontWeight='bold';

	}
}
</script>
</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop_black.jsp"%>
	<%@ include file="/WEB-INF/layout/LayoutTop1_black.jsp"%>
	<!-- TOP (E) --> </header>
	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<!-- My Page(메뉴) -->
			<%@ include file="/WEB-INF/layout/LayoutMyPage.jsp"%>

			<!-- My Page(메뉴) E  -->
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="wrap_cont_necessary">
					<div class="col-md-12 to-animate">
						<div class="text-center">
							<!-- 회원정보 폼 S -->
							<div id="wrap">
								<h2 id="skip_cont" class="hide">컨텐츠영역</h2>
								<!-- 회원가입 -->
								<div class="wmp_join">
									<div class="join_banner">
										<map name="unitopbn8">
										</map>
									</div>
									<form name="memberModifyFrm" id="frm" action="myModifyAction"
										onSubmit="" method="post" autocomplete="off"
										style="margin-top: 80px;">
										<!-- <fieldset> -->
										<div class="join_content">
											<p style="margin: 30px; color: darkgreen">
												${sessionScope.login_id.id }님의 회원 정보가 정상적으로 수정되었습니다 ^_^</p>
										</div>
										<br /> <input type="button"
											onclick="location.href='myHome?id=${sessionScope.login_id.id}'"
											value="마이페이지로 돌아가기">
										<!--    </fieldset> -->
									</form>
								</div>
								<!-- 회원정보 E  -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Botton (S) -->
		<%@ include file="/WEB-INF/layout/LayoutBottom.jsp"%>
		<!--  Bottom(E) -->
</body>
</html>