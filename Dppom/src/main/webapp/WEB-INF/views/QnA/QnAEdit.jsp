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
</style>
<title>QnA - 게시글 작성</title>
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
			</div>
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<div class="row">
							<div class="col-md-12" style="text-align: right">
								<button type="button" class="btn" onclick="history.back()"
									style="margin-bottom: 5px;">Back</button>
							</div>
						</div>
						<!-- 테이블 S -->
						<form action="QnAEditAction" method="post">
							<input type="hidden" name="idx" value="${idx}" /> <input
								type="hidden" name="nowPage" value="${nowPage}" />
							<table id="commu_table" class="table  table-bordered">
								<thead>
									<tr>
										<th colspan="3" style="width: 10%; text-align: center;">
											<span style="color: green">건의사항</span> 수정하기
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th colspan="2"><input type="text" value="${title}"
											name="title" placeholder=" 제 목"
											style="width: 95%; height: 40px; margin-left: 40px" /></th>
									</tr>
									<tr>
										<td>비밀번호</td>
										<td><input type="password" value="${pass}" name="pass"
											placeholder="비밀번호 입력" style="width: 100%;" /></td>
									</tr>
									<tr>
										<td colspan="2"><textarea name="content" id="" cols="50"
												rows="30" style="width: 100%; height: 200px;">${content}</textarea>
										</td>
									</tr>
								</tbody>
								<%-- 	<%} //for문 끝%>
					<%}//else문 끝 %>	 --%>
							</table>
							<div class="col-md-12" style="text-align: center">
								<button type="submit" class="btn" style="margin-bottom: 5px;">수정하기</button>
							</div>
							<!-- 테이블 E  -->
						</form>
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