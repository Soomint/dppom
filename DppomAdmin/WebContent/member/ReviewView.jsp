<%@page import="BoardModel.boardDTO"%>
<%@page import="BoardModel.boardDAO"%>
<%@page import="MemberModel.memberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="MemberModel.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	boardDAO dao = new boardDAO();
	int idx = Integer.parseInt(request.getParameter("idx"));

	boardDTO viewRow = dao.viewPage(idx);
	//상세보기 줄바꿈 처리
	viewRow.setContent(viewRow.getContent().replace("\r\n", "<br/>"));
	dao.close();
%>
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<style>
.navbar-brand {
	color: #6173f4;
}
</style>
<title>ReView</title>
</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/layout/LayoutTop2.jsp"%> <!-- TOP (E) -->
	</header>

	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<div class="row" id="team">
				<div class="col-md-12 section-heading text-center to-animate">
					<h2>회원 게시글</h2>
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
						<form action="">
							<input type="hidden" name="nowPage"
								value="<%=request.getParameter("nowPage")%>" />
							<table id="commu_table" class="table  table-bordered">
								<thead>
									<tr>
										<th colspan="3" style="width: 10%; text-align: center;">
											<a href="" name="신고"><span
												class="glyphicon glyphicon-bell"
												style="width: 95%; text-align: right; color: red"></span></a> <a
											href="" name="좋아요" style="color: red"><span
												class="glyphicon glyphicon-thumbs-up"
												style="text-align: right; color: blue"></span></a>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td style="Width: 20%"><%=viewRow.getCategory()%></td>
										<td style="width: 60%"><%=viewRow.getTitle()%></td>
										<td style="Width: 20%"><%=viewRow.getClick_cnt()%></td>
									</tr>
									<tr>
										<td><%=viewRow.getUniv()%></td>
										<td><%=viewRow.getMajor()%></td>
										<td style="width: 30%"><%=viewRow.getAttachedfile()%></td>
									</tr>
									<tr>
										<td colspan="3" style="height: 400px; text-align: left;">
											<%=viewRow.getContent()%>
										</td>
									</tr>
								</tbody>
								<%--    <%} //for문 끝%>
               <%}//else문 끝 %>    --%>
							</table>
							<c:choose>
								<c:when test="${viewRow.id == sessionScope.login_id.id }">
									<!-- 로그인된 회원이 작성자일 경우 -->
									<div class="col-md-12" style="text-align: center">
										<button type="button" class="btn"
											onclick="javascript:location.href='deleteView.jsp?idx=<%=viewRow.getIdx()%>&nowPage=<%=request.getParameter("nowPage")%>&pageSize=<%=request.getParameter("pageSize")%>&id=<%=request.getParameter("id")%>'"
											style="margin-bottom: 5px;">삭 제</button>
										<button type="button" class="btn"
											onclick="avascript:location.href='memberBoardInfo.jsp?id=<%=viewRow.getId()%>&nowPage=<%=request.getParameter("nowPage")%>&pageSize=<%=request.getParameter("pageSize")%>'"
											style="margin-bottom: 5px;">목록으로</button>
									</div>
								</c:when>
							</c:choose>
							<!-- 테이블 E  -->
						</form>
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