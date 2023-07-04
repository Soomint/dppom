<%@page import="dppom.model.member.MemberDAO"%>
<%@page import="java.util.StringTokenizer"%>
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
<script src="./resources/jquery/jquery-3.2.1.js"></script>
<script>
//비밀번호 확인 기능 keyup(): 키보드 눌렀다가 뗐을때 

$(document).ready(function(){
$('#pass').keyup(function(){
	$('#passCorrect').text(''); //span 태그는  value  속성 없음
	$('#Re_pass').val(''); // 문제 1 : 암호를 입력할때 무조건 암호확인 부분을 clear 시킨다.
});

$('#Re_pass').keyup(function(){  // pwd2에서 누를때 검사 시작
	var cmpStr1 = $('#pass').val();
	var cmpStr2 = $(this).val(); // this-> pwd2
	
	if(cmpStr1 == cmpStr2){
		$('#passCorrect').text('');
		//암호 일치
		//$('#msg').html('<b style="color:red">암호가 일치합니다^0^*</b>');
		$('#passCorrect').html('<b>비밀번호 일치</b>');
		$('#passCorrect').css("color","blue");  
	}
	else{
		$('#passCorrect').text('');
		$('#passCorrect').html('<strong>비밀번호 불일치</strong>');
		//$('#msg').attr({style:"color:#000000","font-weight":"bold"});  //샘1
		//$('#msg').attr({"color":"#000000","font-weight":"bold"}); 컬러는 style로 지정해야함
		$('#passCorrect').attr({style:"color:red; font-weight:bold"});  //샘2
		//	font-weight는 단독 또는 style속성 안에서 지정 가능 
	}
});

});


function passchk(){
	var f = document.memberModifyFrm;
	
	if(f.pass.value != f.Re_pass.value){
		document.getElementById("passCorrect").innerHTML ='불일치 (재검사)';
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

function modifyfrmchk(){
	var f = document.memberModifyFrm;
	if(f.pass.value=="" ){
		alert('비밀번호를 입력해주세요');
		return false
	}
	if(f.Re_pass.value==""){
		alert('비밀번호를 다시 한번 입력해주세요');
		return false
	}
	if(f.pass.value != f.Re_pass.value){
		alert('비밀번호 일치 확인 해주세요');
		return false
	}
	if(f.name.value==""){
		alert('이름을 입력해주세요');
		return false
	}
	if(f.email1.value=="" || f.email2.value=="" ){
		alert('이메일을 다시 확인해주세요 ');
		return false
	}
	if(f.phone.value==""){
		alert('휴대폰번호를 입력해주세요');
		return false
	}
	if(f.univ.value==""){
		alert('학교정보를 입력해주세요');
		return false
	}
	if(f.major.value==""){
		alert('학과정보를 입력해주세요');
		return false
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
					<div class="col-md-12 section-heading text-center">
						<div class="row">
							<div class="col-md-8 col-md-offset-2 to-animate">
								<h3>
									<span style="color: navy; font-size: 1.5em; font-weight: bold">${sessionScope.login_id.id}</span>
									님의 회원 정보
								</h3>
							</div>
						</div>
					</div>
					<div class="col-md-12 to-animate">
						<div class="text-center">
							<!-- 회원수정 폼 S -->
							<div id="wrap">
								<h2 id="skip_cont" class="hide">컨텐츠영역</h2>
								<!-- 회원수정 -->
								<div class="wmp_join">
									<div class="join_banner">
										<map name="unitopbn8">
										</map>
									</div>
									<form name="memberModifyFrm" id="frm" action="myModifyAction"
										method="post" autocomplete="off" style="margin-top: 80px;">
										<!-- <fieldset> -->
										<div class="join_content">
											<!-- 필수 정보 -->
											<div class="necessary_input"
												style="padding-right: 20px; padding-left: 20px;">
												<!-- 정보 입력 공간 s -->
												<table>
													<input type="hidden" name="id"
														value="${sessionScope.login_id.id}" />
													<!-- session으로 변경  -->
													<!-- 비밀번호 -->
													<div class="wrap_text_field">
														<tr>
															<td><span style="font-size: 1.5em;">새로운 비밀번호</span>
															</td>
															<td>
																<div class="wrap_pass_field">
																	<div class="clear_fix">
																		<input type="password" id="pass" class="text_field"
																			name="pass" maxlength="15"
																			style="width: 213px; height: 40px;"
																			placeholder="비밀번호 재확인">
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td><span style="font-size: 1.5em;">비밀번호 확인</span></td>
															<td>
																<div class="wrap_pass_field" style="width: 100%">
																	<div class="clear_fix">
																		<input type="password" placeholder="비밀번호" id="Re_pass"
																			class="text_field" name="Re_pass" maxlength="15"
																			style="width: 213px; height: 40px;"> <span
																			id="passCorrect"
																			style="color: babyblue; font-size: 1.1em;">
																			&nbsp;&nbsp;&nbsp;비밀번호 일치 확인</span>
																	</div>
																</div>
															</td>
														</tr>
													</div>
													<!-- //비밀번호 -->
					<%
                      	//MemberDAO dao = new MemberDAO();
                      	String email = (String)request.getAttribute("email"); //모델에서 가져올수잇나
                      	//System.out.println("from model: "+email);  //모델에서 가져옴 
                      	StringTokenizer tokens= new StringTokenizer(email,"@"); 
                      	String email1_tok = tokens.nextToken();
                      	String email2_tok = tokens.nextToken();
                      	//System.out.println("email1 , email2"+email1_tok+" "+email2_tok);
                      %>
													<tr>
														<td><span style="font-size: 1.5em;">이메일</span></td>
														<td>
															<!-- 이메일 -->
															<div class="wrap_text_field">
																<c:set var="email1" value="<%=email1_tok %>"></c:set>
																<c:set var="email2" value="<%=email2_tok %>"></c:set>
																<input type="text" id="email1" name="email1"
																	value="${email1 }" class="text_field" placeholder="이메일"
																	dg-data="이메일"
																	style="width: 128px; height: 40px; margin-top: 5px;">
																&nbsp;&nbsp;&nbsp; <span class="txt_mail"
																	style="font-size: 1.5em;">@</span>&nbsp;&nbsp;&nbsp; <input
																	type="text" id="email2" name="email2"
																	class="text_field" value="${email2 }"
																	style="width: 128px; height: 40px; margin-top: 5px;">
															</div> <!-- //이메일 -->
														</td>
													</tr>
													<!-- 이름, 휴대폰번호/기본 -->
													<div class="wrap_text_field">
														<tr>
															<td><span style="font-size: 1.5em;">이름</span></td>
															<td>
																<div class="wrap_name_field">
																	<div class="clear_fix">
																		<input type="text" id="name" name="name"
																			value="${user_Info.name }" class="text_field"
																			style="width: 213px; height: 40px;" placeholder="이름 "
																			dg-data="이름 " />
																	</div>
																	<!--  <p id="uname_error" class="help_block">이름을 입력해주세요.</p> -->
																</div>
															</td>
														</tr>
														<tr>
															<td><span style="font-size: 1.5em;">휴대전화</span></td>
															<td>
																<div class="wrap_name_field">
																	<input type="text" id="phone" name="phone"
																		value="${user_Info.phone }" class="text_field"
																		style="width: 213px; height: 40px;" maxlength="13"
																		placeholder="휴대폰번호" dg-data="휴대폰번호" />
																</div>
															</td>
														</tr>
													</div>
													<!-- //이름, 휴대폰번호/기본 -->
													<!-- 학교 , 학과 -->
													<div class="wrap_text_field">
														<tr>
															<td><span style="font-size: 1.5em;">학교</span></td>
															<td>
																<div class="wrap_pass_field">
																	<div class="clear_fix">
																		<input type="text" placeholder="학교" id="univ"
																			class="text_field" name="univ"
																			value="${user_Info.univ }" maxlength="15"
																			style="width: 213px; height: 40px;">
																	</div>
																</div>
															</td>
														</tr>
														<tr>
															<td><span style="font-size: 1.5em;">학과</span></td>
															<td>
																<div class="wrap_pass_field">
																	<div class="clear_fix">
																		<input type="text" placeholder="학과" id="major"
																			class="text_field" name="major"
																			value="${user_Info.major }" maxlength="15"
																			style="width: 213px; height: 40px;">
																	</div>
																</div>
															</td>
														</tr>
													</div>
													<!-- //학교, 학과 -->
												</table>
												<!-- 정보 입력 공간 e -->
												<br />
												<div class="wrap_center_btn">
													<button type="submit" id="joinConfirmBtn"
														onclick="return modifyfrmchk()"
														class="btn_wmp_join btn_wmp_red btn_large">
														<span class="inner">수정하기</span>
													</button>
												</div>
												<br />
												<br />
												<div class="wrap_center_btn" style="text-align: right">
													<a href="myOut"> <span class="inner"
														style="color: red;">탈퇴하기</span>
													</a>
												</div>
											</div>
										</div>
								</div>
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