<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
<link href="./resources/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="./resources/assets/css/material-kit.css" rel="stylesheet" />

<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="./resources/assets/css/demo.css" rel="stylesheet" />
<title>로그인 폼</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
<!--   Core JS Files   -->
<script src="./resources/assets/js/jquery.min.js" type="text/javascript"></script>
<script src="./resources/assets/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="./resources/assets/js/material.min.js"></script>

<script>
   function findIdFrm(){
      var widthVar = window.screen.width;
      var heightVar = window.screen.height;
      window.open("FindId","FindId", "width=360, height=409, left="+(widthVar/3)+", top="+(heightVar/4));
   }
   function findPassFrm(){
      var widthVar = window.screen.width;
      var heightVar = window.screen.height;
      window.open("FindPass","FindId", "width=380, height=454, left="+(widthVar/3)+", top="+(heightVar/4));
   }
   
   function chkLoginfrm(){
	   if(document.Loginfrm.id.value==""){
		   alert('아이디 입력하세요');
		   return false;
		   }
	   if(document.Loginfrm.pass.value==""){
		   alert('비밀번호 입력하세요');
		   return false;
		   }
   }
</script>
<style>
.card .header-primary {
	background: linear-gradient(20deg, #d9e5ea, #1c609d);
}

#black {
	color: black;
}
</style>
</head>
<body>
	<div class="section section-full-screen section-signup"
		style="background-size: cover; background-position: top center; min-height: 700px;">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4">
					<div class="card card-signup">
						<form class="form" name="Loginfrm" method="post"
							action="LoginProcess">
							<input type="hidden" name="PageURL" value="${PageURL}" />
							<!-- 전달받은 URL 액션 컨트롤러로 전달 -->
							<input type="hidden" name="PageURLreq" value="${PageURLreq }" />
							<div class="header header-primary text-center">
								<a href="Main" style="color: white; text-decoration: none"><h2>大
										PPOM</h2></a>
							</div>
							<br />
							<br />
							<div class="content">
								<p id="joinMsg"
									style="text-align: center; color: skyblue; font-weight: bold; font-size: 1.3em">${ joinMsg}</p>
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="material-icons">face</i>
									</span> <input type="text" name="id" class="form-control"
										placeholder="ID를 입력해주세요 " style="width: 65%">
								</div>
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="material-icons">vpn_key</i>
									</span> <input type="password" name="pass" placeholder="비밀번호를 입력해주세요"
										class="form-control" value="" />
								</div>
							</div>
							<div class="footer text-center">
								<button type="submit" class="btn btn-simple btn-primary btn-lg"
									style="color: navy; font-weight: bold; font-size: 1.2em"
									onclick="return chkLoginfrm()">로그인</button>
								<br /> <a href="Join" id="black"
									style="color: navy; text-decoration: none">아직 회원이 아니신가요?</a> <br />
								<button type="button" id="black" class="btn"
									onclick="findIdFrm()" style="background-color: aliceblue;">ID
									찾기</button>
								<button type="button" id="black" class="btn"
									onclick="findPassFrm()" style="background-color: aliceblue">비밀번호
									찾기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>