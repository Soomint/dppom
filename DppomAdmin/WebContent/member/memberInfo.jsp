<%@page import="MemberModel.memberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="MemberModel.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	memberDAO dao = new memberDAO();
	String id = request.getParameter("id"); //선택한 id 받아오기

	//회원 한명 가져오기
	memberDTO dto = dao.selectOne(id);
	dao.close();
%>
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>memberInfo</title>
</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/layout/LayoutTop2.jsp"%> <!-- TOP (E) -->
	</header>
	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<!-- My Page(메뉴) -->
			<%@ include file="/layout/LayoutAdminPage.jsp"%>
			<!-- My Page(메뉴) E  -->
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 section-heading text-center">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 to-animate">
							<h3>신고내용 처리 관리자</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<table border="1" class="table table-bordered">
							<colgroup>
								<col style="background-color: #f3f3f3;" width="30%" />
								<col />
							</colgroup>
							<tr>
								<th colspan="2" style="text-align: center;"><sapn
										style="color:green;"><%=dto.getId()%></sapn> 님의 회원정보</th>
							</tr>
							<tr>
								<td>아이디</td>
								<td style="text-align: center;"><%=dto.getId()%></td>
							</tr>
							<tr>
								<td>이름</td>
								<td style="text-align: center;"><%=dto.getName()%></td>
							</tr>
							<tr>
								<td>이메일</td>
								<td style="text-align: center;"><%=dto.getEmail()%></td>
							</tr>
							<tr>
								<td>대학교</td>
								<td style="text-align: center;"><%=dto.getUniv()%></td>
							</tr>
							<tr>
								<td>전공</td>
								<td style="text-align: center;"><%=dto.getMajor()%></td>
							</tr>
							<tr>
								<td>가입일</td>
								<td style="text-align: center;"><%=dto.getRegidate()%></td>
							</tr>
							<tr>
								<td>휴대폰 번호</td>
								<td style="text-align: center;"><%=dto.getPhone()%></td>
							</tr>
							<tr>
								<td>종합 포인트</td>
								<td style="text-align: center;"><a
									href="./memberPointInfo.jsp?id=<%=dto.getId()%>"
									style="color: gray; font-size: 13px; text-algin: right;"><%=dto.getPoint()%></a>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;"><a
									href="./memberBoardInfo.jsp?id=<%=dto.getId()%>"
									style="color: gray;">게시판 내역</a></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Botton (S) -->
	<%@ include file="/layout/LayoutBottom.jsp"%>
	<!--  Bottom(E) -->
</body>
</html>