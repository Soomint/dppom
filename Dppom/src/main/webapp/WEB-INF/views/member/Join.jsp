<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>대뽐!!</title>
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

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />

<!--     Fonts and icons     -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

<!-- CSS Files -->
<link href="./resources/assets/css/modal.css" rel="stylesheet" />
<link href="./resources/assets/css/material-kit.css" rel="stylesheet" />

<style>
#btn_small_color {
	background-color: yellowgreen;
}

#btn_content_color {
	background-color: yellowgreen;
}

.inner {
	font-family: Tahoma;
}
</style>
<!--   Core JS Files   -->
<script src="./resources/assets/js/jquery.min.js" type="text/javascript"></script>
<script src="./resources/assets/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="./resources/assets/js/material.min.js"></script>


<script src="./resources/jquery/jquery-3.2.1.js"></script>
<script>

//에러시 실행할 함수
function errFunc(){
   alert("실패 !");
}
   
   //비밀번호 확인 기능 keyup(): 키보드 눌렀다가 뗐을때 
   $(document)
         .ready(
               function() {
                  
                  $('#pass').keyup(function() {
                     $('#passCorrect').text('');
                     $('#Re_pass').val('');
                  });

                  $('#Re_pass')
                        .keyup(
                              function() {
                                 var cmpStr1 = $('#pass').val();
                                 var cmpStr2 = $(this).val();

                                 if (cmpStr1 == cmpStr2) {
                                    $('#passCorrect').text('');
                                    //암호 일치
                                    $('#passCorrect')
                                          .html(
                                                '<b style="font-size:1.2em">&nbsp;&nbsp;&nbsp;비밀번호 일치</b>');
                                    $('#passCorrect').css("color",
                                          "blue");
                                 } else {
                                    $('#passCorrect').text('');
                                    $('#passCorrect')
                                          .html(
                                                '<b style="font-size:1.2em">&nbsp;&nbsp;&nbsp;비밀번호 불일치</b>');
                                    $('#passCorrect')
                                          .attr(
                                                {
                                                   style : "color:red; font-weight:bold"
                                                });
                                 }
                              });
                  
                  
                  // 도메인 선택 
                  $('#selectdomain').change(function(){
                     
                     var value=$('#selectdomain option:selected').val();
                     if(value=='write'){
                        $('#email2').val('');
                        $('#email2').focus();
                     }
                     else{
                        $('#email2').val(value);
                     }
                  
                  });
                  
                  //중복검사 버튼 클릭
                  $("#idchkBtn").click(function(){
                     $.ajax({
                        url : "isIdchk",
                        dataType : "html",
                        type : "get",
                        contentType : "text/html;charset:utf-8",
                        data : {
                           id : $('#id').val(),
                           isIDflag : "",
                        },
                        //success : sucFunc,
                        success : function(data){   //매개변수일치
                           //alert('고고');
                           $("#isIDflag").val(isIDflag);  
                           $("#isId").html(data);  //매개변수일치
                        },
                        error : errFunc
                     });
                  });
                  
                  
                  //회원약관 동의      
                  $("input[type='radio']").click(function() {
                     var res = $('input:radio[name=agreeChk]:checked').val();
                     if(res=='disagree'){
                        //alert('거절'+res);
                        $("#agreeFlag").val("disagree");
                        alert('약관 동의 거절시 회원가입에 제한 됩니다.');
                     }
                     else{
                        $("#agreeChk").empty();
                        var str ="<p style='font-weight:bold; font-size:1.2em; padding:15px; color:navy'> 회원약관에 동의하셨습니다.</p>";
                        $("#agreeFlag").val("agree");
                        $("#agreeChk").html(str);
                     }
                  });

               });

   function joinfrmchk() {
      var f = document.joinfrm;

      if (f.id.value == "") {
         alert('아이디를 입력해주세요');
         return false
      }
      
      if (f.isIDflag.value == "") {
         alert('아이디 중복검사 해주세요');
         return false
      }
      
      if (f.isIDflag.value == "no") {
         alert('사용할수 없는 아이디 입니다.');
         return false
      }
      
      
      if (f.name.value == "") {
         alert('이름을 입력해 주세요');
         return false
      }
      
      if (f.pass.value == "") {
         alert('비밀번호를 입력해주세요');
         return false
      }
      if (f.Re_pass.value == "") {
         alert('비밀번호를 다시 한번 입력해주세요');
         return false
      }
      if (f.pass.value != f.Re_pass.value) {
         alert('비밀번호 일치 확인 해주세요');
         return false
      }
      
      
      if (f.pass_question.value == "") {
         alert('비밀번호 찾기 질문을 선택하세요');
         return false
      }
      if (f.pass_answer.value == "") {
         alert('비밀번호 찾기 대답을 입력하세요');
         return false
      }

      
      if (f.name.value == "") {
         alert('이름을 입력해주세요');
         return false
      }
      if (f.email1.value == "" || f.email2.value == "") {
         alert('이메일을 다시 확인해주세요 ');
         return false
      }
      if (f.phone1.value == "") {
         alert('휴대폰번호를 확인해주세요');
         return false
      }
      if (f.phone2.value == "") {
         alert('휴대폰번호를 확인해주세요');
         return false
      }
      if (f.phone3.value == "") {
         alert('휴대폰번호를 확인해주세요');
         return false
      }
      if (f.univ.value == "") {
         alert('학교정보를 입력해주세요');
         return false
      }
      if (f.major.value == "") {
         alert('학과정보를 입력해주세요');
         return false
      }
      
      if(f.agreeFlag.value==""){
         alert('회원약관에 동의여부를 체크해주세요');
         return false;
      }
      
      if(f.agreeFlag.value=="disagree"){
         alert('회원약관에 동의해 주세요');
         return false;
      }
      
      if(f.correct.checked==false){
         alert('이메일 인증해주세요');
         return false;
      }
   
   }
   function send(frm)
   {
      if (document.joinfrm.email1.value == "" || document.joinfrm.email2.value == "") {
         alert('이메일을 다시 확인해주세요.');
         return false;
      }
      else
      {
         alert('이메일이 발송되었습니다.');
         window.open("member/MailAgree?email=" + document.joinfrm.email1.value + "@" + document.joinfrm.email2.value, "이메일 인증창", "width=450, height=409, left=300, top=100");
      }
         
   }
</script>
</head>
<body onLoad="return false;">
	<div id="wmpSkip">
		<a href="#header" onclick="skipNavigation('#header');return false;"><span>메뉴
				바로가기</span></a> <a href="#container"
			onclick="skipNavigation('#container');return false;"><span>본문
				바로가기</span></a>
	</div>
	<div id="wrap">
		<div id="container">
			<h2 id="skip_cont" class="hide">컨텐츠영역</h2>
			<!-- 회원가입 -->
			<div class="wmp_join">
				<div class="join_banner">
					<map name="unitopbn8"></map>
				</div>
				<form name="joinfrm" action="JoinSuccess" onSubmit="" method="post"
					autocomplete="off">
					<fieldset>
						<legend>회원가입</legend>
						<div class="join_content">
							<!-- 필수 정보 -->
							<div class="wrap_cont_necessary">
								<h1 class="text-center" style="font-size: 50px; color: green;">
									<a href="Main"><img
										src="./resources/bootstrap3.3.7/images/로고1.png" alt="로고염"
										style="width: 450px; height: 150px" /></a>
								</h1>
								<h4 class="tit_wmp_join tit_join_necessary mg-section-sm">
									필수 정보</h4>
								<div class="necessary_input">
									<div class="wrap_text_field">
										<input type="text" placeholder="아이디" id="id" name="id"
											class="text_field" maxlength="15" style="width: 213px;">
										<span id="isId">&nbsp;&nbsp;&nbsp;아이디중복결과</span> &nbsp;&nbsp;
										<button type="button" id="idchkBtn"
											class="btn_wmp_join btn_wmp_red">
											<span class="inner">중복확인</span>
										</button>
										<input type="hidden" id="isIDflag" name="isIDflag" value="" />
									</div>
									<!-- 이름 -->
									<div class="wrap_text_field">
										<input type="text" placeholder="이름" id="name" name="name"
											class="text_field" maxlength="15" style="width: 213px;">
									</div>
									<!-- 비밀번호 -->
									<div class="wrap_text_field">
										<input type="password" placeholder="비밀번호" id="pass"
											class="text_field" name="pass" maxlength="15"
											style="width: 213px;">
									</div>
									<div class="wrap_text_field">
										<input type="password" placeholder="비밀번호재확인" id="Re_pass"
											class="text_field" name="Re_pass" maxlength="15"
											style="width: 213px;"> <span id="passCorrect"
											style="line-height: 39px; padding: 2px 8px 0 0; color: red;">
										</span> &nbsp;&nbsp;
									</div>
									<!-- 비밀번호 찾기 질문 & 답 -->
									<div class="wrap_text_field">
										<div class="wrap_pass_field">
											<div class="clear_fix">
												<select name="pass_question"
													style="width: 234px; height: 40px;">
													<option value="">비밀번호 찾기 질문</option>
													<option value="1">이성친구 이름은?</option>
													<option value="2">첫사랑 이름은?</option>
													<option value="3">나의 가장 소중한 보물은?</option>
													<option value="4">당신이 나온 학교이름은?</option>
													<option value="5">당신의 보유 자산은?</option>
													<option value="6">당신이 살고 싶은 도시는?</option>
												</select>
											</div>
										</div>
									</div>
									<div class="wrap_text_field">
										<div class="wrap_pass_field">
											<div class="clear_fix">
												<input type="text" placeholder="답변" name="pass_answer"
													id="pass_answer" class="text_field" style="width: 455px;">
											</div>
										</div>
									</div>
									<!-- //비밀번호 -->
									<!-- 이메일 -->
									<div class="wrap_text_field">
										<input type="text" name="email1" class="text_field"
											placeholder="이메일" dg-data="이메일" style="width: 128px;">
										<span class="txt_mail">@</span> <input type="text" id="email2"
											class="text_field" name="email2" placeholder="도메인"
											style="width: 128px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select
											name="domain" id="selectdomain"
											style="width: 128px; height: 40px">
											<option value="">도메인 선택</option>
											<option value="naver.com">naver.com</option>
											<option value="daum.net">daum.net</option>
											<option value="google.com">google.com</option>
											<option value="write">직접 입력</option>
										</select>
									</div>
									<p class="help_block help_block_notice ico_dot"
										id="mobile_notice">이메일은 아이디 또는 비밀번호를 찾기 위한 정보이므로 정확하게 입력해
										주세요.</p>
									<!-- //이메일 -->
									<!-- 이름, 휴대폰번호/기본 -->
									<div class="wrap_text_field">
										<div class="wrap_name_field" style="width: 100%">
											<input type="text" id="umobile" name="phone1"
												class="text_field" style="width: 18%;" maxlength="3"
												placeholder="휴대폰번호" dg-data="휴대폰번호" /> <span
												class="txt_mail">&nbsp; - &nbsp;</span> <input type="text"
												id="umobile" name="phone2" class="text_field"
												style="width: 25%;" maxlength="4" placeholder="휴대폰번호"
												dg-data="휴대폰번호" /> <span class="txt_mail">&nbsp; -
												&nbsp;</span> <input type="text" id="umobile" name="phone3"
												class="text_field" style="width: 25%;" maxlength="4"
												placeholder="휴대폰번호" dg-data="휴대폰번호" />
										</div>
									</div>
									<!-- 학과 -->
									<div class="wrap_text_field">
										<div class="wrap_pass_field">
											<div class="clear_fix">
												<input type="text" name="univ" placeholder="학교" id="univ"
													class="text_field" maxlength="15" style="width: 213px;" />
											</div>
										</div>
										<div class="wrap_pass_field">
											<div class="clear_fix">
												<input type="text" name="major" placeholder="학과" id="major"
													class="text_field" maxlength="15" style="width: 213px;" />
											</div>
										</div>
									</div>
									<!-- //학과 -->
									<!-- 이용약관동의 -->
									<div class="wrap_text_field wrap_agree_area">
										<div class="wrap_check_agree">
											<div class="row" id="modals">
												<div class="col-sm-6">
													<strong>이용약관 동의</strong> <a data-toggle="modal"
														href="#myModal" id="" style="padding: 2px"
														class="btn_wmp_join btn_wmp_white btn_small"> <span
														class="" id="" style="color: white">내용보기</span>
													</a>
												</div>
												<div class="col-sm-6" id="agreeChk">
													<div class="wrap_check_agree">
														<label> <input type="radio" name="agreeChk"
															class="check" id="agree" value="agree" /> 동의
														</label> <label> <input type="radio" name="agreeChk"
															class="check" id="disagree" name="disagree"
															value="disagree" /> 거절
														</label>
													</div>
												</div>
												<input type="hidden" id="agreeFlag" name="agreeFlag"
													value="" />
											</div>
										</div>
									</div>
									<div class="container">
										<p class="help_block help_block_notice ico_dot">본 약관에는 마케팅
											정보 수신에 대한 동의에 관한 내용이 포함되어 있으며, 회원은 언제든지 정보 메시지 수신 설정 메뉴에서 수신
											거부로 변경할 수 있습니다.</p>
										<p class="help_block">이용약관동의 여부를 체크해주세요.</p>
										<!-- //이용약관동의 -->
										<!-- 개인정보 수집 및 이용안내 -->
										<p class="tit_wmp_join tit_agree_collecting">개인정보 수집 및
											이용안내</p>
										<table summary="개인정보 수집 및 이용안내 목적, 항목, 보유 및 이용기간에 대한 정보 제공">
											<caption>개인정보 수집 및 이용안내</caption>
											<colgroup>
												<col width="34%" />
												<col width="33%" />
												<col width="33%" />
											</colgroup>
											<thead>
												<tr>
													<th>목적</th>
													<th>항목</th>
													<th>보유 및 이용 기간</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>이용자 식별,<br /> 서비스 이용 및 상담
													</td>
													<td>이름, 이메일,<br />비밀번호, 전화번호
													</td>
													<td>회원탈퇴 후 5일 까지</td>
												</tr>
											</tbody>
										</table>
										<!-- //개인정보 수집 및 이용안내 -->
									</div>
									<!-- 이메일 인증기능 -->
									<div class="wrap_text_field wrap_agree_area">
										<div class="wrap_check_agree">
											<div class="row">
												<div class="col-sm-6">
													<div class="wrap_check_agree">
														<label> <input type="checkbox" name="correct"
															class="check" disabled /> 인증완료
														</label> <label> <input type="checkbox" name="incorrect"
															class="check" disabled /> 인증실패
														</label>
													</div>
													<input type="hidden" name="que" value="" /> <input
														type="hidden" name="ans" value="" />
													<button type="button" style="padding: 2px"
														class="btn_wmp_join btn_wmp_white btn_small">
														<span style="color: white" onclick="return send();">이메일
															인증</span>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="wrap_center_btn">
									<button type="submit" id="joinConfirmBtn"
										class="btn_wmp_join btn_wmp_red btn_large"
										onclick="return joinfrmchk()">
										<span class="inner">동의하고 회원가입</span>
									</button>
								</div>
								<br /> <br /> <br />
								<!--                   </div> -->
								<!-- //선택 항목 -->
							</div>
					</fieldset>
				</form>
			</div>
			<!-- //회원가입 -->
		</div>
		<!-- // container -->
	</div>
	<!-- Sart Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"></div>
				<div class="modal-body">
					<p>
						<%@ include file="/WEB-INF/layout/agreement.jsp"%>
					</p>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-default btn-simple">동의</button> -->
					<button type="button" class="btn btn-danger btn-simple"
						data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	<!--  End Modal -->
</body>
</html>