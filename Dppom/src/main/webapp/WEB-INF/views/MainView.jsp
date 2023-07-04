<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<%@ include file="/WEB-INF/layout/head.jsp"%>
</head>

<title>大PPOM 커뮤니티사이트</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
<script>
function loginCheck(PageURL)
{
   alert("로그인을 해주세요.");
   location.href='Login?PageURL='+PageURL;
    return false;
}
function cut()
{
   alert('회원님의 포인트가 0포인트 이하이므로 접근할 수 없습니다.');
   return false;
}
function cut2()
{
   alert("권한이 없습니다. 학생인증을 해주세요.");
   return false;
}
  </script>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop.jsp"%>
	<ul class="nav navbar-nav navbar-right">
		<c:choose>
			<c:when test="${not empty sessionScope.login_id }">
				<c:if
					test="${sessionScope.login_id.id!='wm' && sessionScope.login_id.id!='ms' && sessionScope.login_id.id!='jg'}">
					<li><a href="myHome?id=${sessionScope.login_id.id}"><span
							class="glyphicon glyphicon-bell" style="">${sessionScope.login_id.id}님,
								반갑습니다!</span></a></li>
					<li><a href="myHome?id=${sessionScope.login_id.id}"><span>My
								Page</span></a></li>
					<!-- 회원일때만 -->
					<li><a href="Logout"><span>Logout</span></a></li>
				</c:if>
				<c:if
					test="${sessionScope.login_id.id=='wm' || sessionScope.login_id.id=='ms' || sessionScope.login_id.id=='jg'}">
					<li><a href="../DppomAdmin/member/MemberAdmin.jsp"><span
							class="glyphicon glyphicon-bell" style="">관리자(${sessionScope.login_id.id})님,
								반갑습니다!</span></a></li>
					<li><a href="../DppomAdmin/member/MemberAdmin.jsp"><span>Admin
								Page</span></a></li>
					<!-- 관리자일때만 -->
					<li><a href="Logout"><span>Logout</span></a></li>
				</c:if>
			</c:when>
			<c:otherwise>
				<li><a href="Login"><span>Login</span></a></li>
				<li><a href="Join"><span>Join</span></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	</div>
	</nav>
	</div>
	<!-- TOP (E) --> </header>
	<!-- 슬라이더  -->
	<div id="slider" data-section="home">
		<div class="owl-carousel owl-carousel-fullwidth">
			<!-- You may change the background color here.  -->
			<div class="item"
				style="background-image: url('./resources/mainview/mainview1.png');">
				<div class="container" style="position: relative;">
					<div class="row">
						<div class="col-md-7 col-sm-7">
							<div class="fh5co-owl-text-wrap">
								<div class="fh5co-owl-text">
									<h1 class="fh5co-lead to-animate"
										style="font-family: 배달의민족 한나; font-size: 4.3em; text-shadow: 3px 3px 3px #4B4B4B; color: #2457BD;">
										大PPOM에<br />
										<p style="margin: 10px"></p>
										오신것을 환영합니다 !
									</h1>
									<h2 class="fh5co-sub-lead to-animate"
										style="font-family: 배달의민족 한나;; font-size: 2.7em; text-shadow: 3px 3px 3px #4B4B4B; color: #6a9eff;">
										이곳에서 대학교 소식들을 알아보세요!!</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- You may change the background color here.  -->
			<div class="item"
				style="background-image: url('./resources/mainview/mainview2.jpg');">
				<div class="container" style="position: relative;">
					<div class="row">
						<div
							class="col-md-7 col-md-push-1 col-md-push-5 col-sm-7 col-sm-push-1 col-sm-push-5">
							<div class="fh5co-owl-text-wrap">
								<div class="fh5co-owl-text">
									<h1 class="fh5co-lead to-animate"
										style="font-family: 배달의민족 한나; font-size: 3.5em; text-shadow: 3px 3px 3px #4B4B4B; color: #B71600;">
										강의 후기, 자료실 등<br /> 여러 기능들을 이용해보세요!
									</h1>
									<h2 class="fh5co-sub-lead to-animate"
										style="font-family: 배달의민족 한나;; font-size: 2.7em; text-shadow: 3px 3px 3px #4B4B4B; color: #810000;">
										감성가득한 大PPOM 유저들이 <br />당신을 기다리고 있습니다.
									</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="item"
				style="background-image: url('./resources/mainview/mainview3.jpg');">
				<div class="container" style="position: relative;">
					<div class="row">
						<div class="col-md-7 col-sm-7">
							<div class="fh5co-owl-text-wrap">
								<div class="fh5co-owl-text">
									<h1 class="fh5co-lead to-animate"
										style="font-family: THE왼손잡이; color: white">
										궁금한 사항이 있을때는<br /> Q&A게시판을 이용해주세요
									</h1>
									<h2 class="fh5co-sub-lead to-animate"
										style="font-family: THE왼손잡이; color: white">여러분의 소중한 의견들
										모두 귀기울이겠습니다..</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 베스트 게시판 -->
	<div id="fh5co-testimonials" data-section="testimonials"
		style="background-color: white">
		<div class="container">
			<div class="row">
				<div class="col-md-12 section-heading text-center">
					<h2 class="to-animate" style="color: grey">N E W S</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="box-testimony to-animate"
						style="background-color: #FFF2E6;">
						<blockquote style="border: 1px solid #EAEAEA">
							<span class="quote" style="background-color: violet"><span><i
									class="icon-quote-left"></i></span></span> <span><img
								src="./resources/bootstrap3.3.7/images/베스트게시판.png" width="70%"
								alt="베스트게시판" /></span>
							<p>
								<img src="./resources/bootstrap3.3.7/images/베스트게시판2.png"
									width="60%" alt="베스트게시판" />
							</p>
						</blockquote>
						<table style="text-align: center;" border="0" cellpadding="0"
							cellspacing="0" width="350">
							<colgroup>
								<col width="30%" />
								<col width="*" />
							</colgroup>
							<tr>
								<th style="text-align: center"><b>Category</b></th>
								<th style="text-align: center"><b>Title</b></th>
							</tr>
							<c:choose>
								<c:when test="${empty view}">
									<!-- 등록된글이 없는경우 노출 -->
									<tr>
										<td colspan="7" style="height: 100px;">등록된 글이 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${view}" var="board" varStatus="loop">
										<tr>
											<td>${board.category}</td>
											<td style="text-align: center;"><img
												src="./resources/mainview/best.png" alt="new" width="30" />${board.title}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
				</div>
				<div class="col-md-4">
					<div class="box-testimony to-animate"
						style="background-color: #FFF2E6;">
						<blockquote style="border: 1px solid #EAEAEA">
							<span class="quote" style="background-color: violet"><span><i
									class="icon-quote-left"></i></span></span> <span><img
								src="./resources/bootstrap3.3.7/images/최신사람찾기.png" width="70%"
								alt="최신사람찾기" /></span>
							<p>
								<img src="./resources/bootstrap3.3.7/images/최신사람찾기2.png"
									width="50%" alt="최신사람찾기2" />
							</p>
						</blockquote>
						<table style="text-align: center;" border="0" cellpadding="0"
							cellspacing="0" width="350">
							<colgroup>
								<col width="30%" />
								<col width="*" />
							</colgroup>
							<tr>
								<td style="text-align: center"><b>Category</b></td>
								<td style="text-align: center"><b>Title</b></td>
							</tr>
							<c:choose>
								<c:when test="${empty party}">
									<!-- 등록된글이 없는경우 노출 -->
									<tr>
										<td colspan="7" style="height: 100px;">등록된 글이 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${party}" var="party" varStatus="loop">
										<tr>
											<td style="text-align: center">${party.category}</td>
											<td style="text-align: center;"><img
												src="./resources/mainview/new.png" alt="new" width="30" />${party.title}</a></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when
									test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'O' && sessionScope.login_id.point lt 1}">

									<tr>
										<td colspan="5" style="text-align: right;"><a href=""
											onclick="return cut();"> <span style="color: #662500;">
													▶ Party게시판 바로가기</span></a></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5" style="text-align: right;"><a
											href="Party" style=""
											onclick="return checkFrm(${sessionScope.login_id.id });"><span
												style="color: #662500;"> ▶ Party게시판 바로가기</span></a></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
				</div>
				<div class="col-md-4">
					<div class="box-testimony to-animate"
						style="background-color: #FFF2E6;">
						<blockquote style="border: 1px solid #EAEAEA">
							<span class="quote" style="background-color: violet"><span><i
									class="icon-quote-left"></i></span></span> <span><img
								src="./resources/bootstrap3.3.7/images/최신강의.png" width="50%"
								alt="최신강의" /></span>
							<p>
								<img src="./resources/bootstrap3.3.7/images/최신강의2.png"
									width="70%" alt="최신강의2" />
							</p>
						</blockquote>
						<table style="text-align: center;" border="0" cellpadding="5"
							cellspacing="0" width="350">
							<colgroup>
								<col width="30%" />
								<col width="*" />
							</colgroup>
							<tr>
								<td>Category</td>
								<td>Title</td>
							</tr>
							<c:forEach items="${studyData}" var="study" varStatus="loop">
								<tr>
									<td>${study.study_type}</td>
									<td style="text-align: left;"><img
										src="./resources/mainview/new.png" alt="new" width="30" />${study.title}</td>
								</tr>
							</c:forEach>
							<c:if test="${sessionScope.login_id.id eq null}">
								<tr>
									<td colspan="5" style="text-align: right;"><a href="Study"
										onclick="return loginCheck('Study');"><span
											style="color: #662500;">Study게시판 바로가기</span></a></td>
								</tr>
							</c:if>
							<c:if
								test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'X' && sessionScope.login_id.point ne null}">
								<tr>
									<td colspan="5" style="text-align: right;"><a href=""
										onclick="return cut2();"><span style="color: #662500;">
												▶ Study게시판 바로가기</span></a></td>
								</tr>
							</c:if>
							<c:if
								test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'O' && sessionScope.login_id.point lt 1}">
								<tr>
									<td colspan="5" style="text-align: right;"><a href="Study"
										onclick="return cut();"><span style="color: #662500;">Study게시판
												바로가기</span></a></td>
								</tr>
							</c:if>
							<c:if
								test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'O' && sessionScope.login_id.point gt 0}">
								<tr>
									<td colspan="5" style="text-align: right;"><a href="Study"
										onclick="return checkFrm(${sessionScope.login_id.id });"><span
											style="color: #662500;">Study게시판 바로가기</span></a></td>
								</tr>
							</c:if>
						</table>
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