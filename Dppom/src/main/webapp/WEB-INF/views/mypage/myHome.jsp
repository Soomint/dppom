<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<%@page import="java.text.NumberFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/layout/head.jsp"%>
<style>
.navbar-brand {
	color: #6173f4;
}
</style>
<title>마이 페이지입니다</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />

</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop_black.jsp"%>
	<%@ include file="/WEB-INF/layout/LayoutTop1_black.jsp"%>
	<!-- TOP (E) --> </header>
	<script src="./resources/jquery/jquery-3.2.1.js"></script>
	<script>
		$(document).ready(function() {

			$('#menu').change(function() {

				//option태그의 value속성에 지정한 값을 읽어옴
				var value = $('#menu option:selected').val();

				if (value == 'plus') {

				}
			});

		});
	</script>
	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<!-- My Page(메뉴) -->
			<%@ include file="/WEB-INF/layout/LayoutMyPage.jsp"%>

			<!-- My Page(메뉴) E  -->
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 section-heading text-center">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 to-animate">
							<h3>Point 내역 조회</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<div class="row">
							<div class="col-md-12" style="text-align: right">
								<span style="color: black; font-size: 1.5em">${sessionScope.login_id.id }</span>
								님의 보유 포인트 : &nbsp;<span style="font-size: 2em"> <fmt:formatNumber
										value="${totalpoint }" />P
								</span>&nbsp;&nbsp; <select
									style="color: black; width: 80px; height: 50px; text-align: left"
									id=used_type name="menu" onchange="location.href=this.value">
									<option
										value="myHome?id=${sessionScope.login_id.id }&nowPage=1"
										selected>&nbsp;&nbsp;전체</option>
									<option
										value="mypointPlus?id=${sessionScope.login_id.id }&used_type=적립&nowPage=1"
										style="color: blue">&nbsp;&nbsp;적립</option>
									<option
										value="mypointPlus?id=${sessionScope.login_id.id }&used_type=차감&nowPage=1"
										style="color: red">&nbsp;&nbsp;차감</option>
								</select>
							</div>
						</div>
						<!-- 테이블 S -->
						<table id="commu_table" class="table table-hover table-bordered">
							<thead>
								<tr>
									<th style="width: 10%; text-align: center;">날짜</th>
									<th style="text-align: center;">내역</th>
									<th style="width: 10%; text-align: center;">+ / -</th>
									<th style="width: 10%; text-align: center;">증(감)금액</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty pointlist }">
										<!-- 등록된글이 없는 경우  -->
										<tr>
											<td colspan="4" style="height: 100px">등록된 글이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${pointlist }" var="list" varStatus="loop">
											<!-- 글이 있는 경우  -->
											<tr>
												<td style="text-align: center;">${list.useddate }</td>
												<td style="text-align: center">${list.detail }</td>
												<c:choose>
													<c:when test="${list.adjust<0 }">
														<td style="width: 10%; text-align: center; color: red">${list.used_type }</td>
													</c:when>
													<c:otherwise>
														<td style="width: 10%; text-align: center; color: blue">${list.used_type }</td>
													</c:otherwise>
												</c:choose>
												<td style="text-align: center">${list.adjust }</td>
												<%-- <td><a href="./view.do?idx=${row.idx }&nowPage=${nowPage}">${row.title }</a></td><!-- listcommand에서 model로 nowPage 전달 --> --%>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
							<%-- 	<%} //for문 끝%>
<%}//else문 끝 %>	 --%>
						</table>
						<!-- 테이블 E  -->
						<br />
						<table width="100%">
							<tr>
								<td style="text-align: center;">${pagingImg}</td>
							</tr>
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