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
<style>
.card .header-primary {
	background: linear-gradient(20deg, #d9e5ea, #1c609d);
}

#black {
	color: black;
}
</style>
<script>
function frmchk(){
	var f = document.findIdfrm;
	
	if(f.name.value==""){
		alert('이름을 입력하세요.');
		return false;
	}
	if(f.email.value==""){
		alert('이메일을 입력하세요.');
		return false;
	}
	
}
</script>
</head>
<body>
	<div class="section section-full-screen section-signup"
		style="background-size: cover; background-position: top center; min-height: 400px;">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4">
					<div class="card card-signup">
						<form name="findIdfrm" class="form" method="post"
							action="FindIdAction">
							<div class="header header-primary text-center">
								<h3>아이디 찾기</h3>
							</div>
							<br />
							<br />
							<div class="content">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="material-icons">face</i>
									</span> <input type="text" name="name" class="form-control"
										placeholder="이름">
								</div>

								<div class="input-group">
									<span class="input-group-addon"> <i
										class="material-icons">email</i>
									</span> <input type="text" name="email" placeholder="이메일"
										class="form-control" />
								</div>
							</div>
							<div class="footer text-center">
								<input type="submit" class="btn btn-simple btn-primary btn-lg"
									onclick="return frmchk()" value="아이디 찾기" style="color: navy" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>