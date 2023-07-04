<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/layout/head.jsp"%>
<style>
.navbar-brand {
	color: #6173f4;
}
#cate:hover {
	background-color: black;
	color: white !important;
}
</style>
<title>大PPOM - QnA</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop2.jsp"%>

	<!-- TOP (E) --> </header>
	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<div class="row" id="team">
				<div class="col-md-12 section-heading text-center to-animate">
					<h2>QnA</h2>
				</div>
				<form action="QnAPassAction" method="post">
					<input type="hidden" name="idx" value="${idx}" /> <input
						type="hidden" name="reply" value="${reply}" /> <input
						type="hidden" name="nowPage" value="${nowPage}" />
					<h3 style="color: red; text-align: center;">${nope}</h3>
					<div class="text-center">
						<span>비밀번호 입력</span><br />
						<br /> <input type="password" name="pass" /> &nbsp;
						<button>입력</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Botton (S) -->
	<%@ include file="/WEB-INF/layout/LayoutBottom.jsp"%>
	<!--  Bottom(E) -->
</body>
</html>