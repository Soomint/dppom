<%@page import="ReplyModel.ReplyDTO"%>
<%@page import="ReplyModel.ReplyDAO"%>
<%@page import="BoardModel.boardDTO"%>
<%@page import="BoardModel.boardDAO"%>
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
	// reply_idx , board_idx  둘다 사용 
	String board_idx = request.getParameter("board_idx");
	//String reply_idx = request.getParameter("reply_idx"); 
	String idx = request.getParameter("idx");
	String type = request.getParameter("type");

	System.out.println(board_idx + " " + idx + " " + type);

	//게시판 글 가져오기
	boardDAO dao = new boardDAO();
	boardDTO dto = dao.viewPage(Integer.parseInt(board_idx));

	//댓글 가져오기
	ReplyDAO replydao = new ReplyDAO();
	ReplyDTO replydto = replydao.ReplyOne(Integer.parseInt(board_idx));

	dao.close();
	replydao.close();
%>
<html>
<script>
	function reportchk(board_id, board_idx, idx, type, what) {
		var check = confirm('신고 처리 하시겠습니까?');
		//alert(board_id+' '+board_idx+' '+idx);
		if (check == true) {
			// 신고 처리 
			location.href = 'reportAction.jsp?id=' + board_id + '&board_idx='
					+ board_idx + '&idx=' + idx + '&type=' + type + '&what='
					+ what;
		}
	}
</script>
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
							<h3 style="color: navy">신고내용 처리 관리자</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<c:set var="viewtype" value="<%=type%>"></c:set>
						<c:if test="${viewtype eq 'board' }">
							<form name="WriteStudyFrm" action="./WriteProc.jsp">
								<input type="hidden" name="Page" value=${param.Page } />
								<table class="table table-bordered" width="770" border="1"
									cellspacing="0" cellpadding="2" style="text-align: center;">
									<tr style="">
										<!-- background-color: #f3f3f3 -->
										<%
											if (dto.getId() == null) {
										%>
										<td width="20%">탈퇴회원</td>
										<%
											} else {
										%>
										<td width="20%"><%=dto.getId()%></td>
										<%
											}
										%>
										<td width="20%"><%=dto.getBoard_type()%></td>
										<td width="45%"><%=dto.getTitle()%></td>
										<%
											if (dto.getRegidate() == null) {
										%>
										<td width="45%"></td>
										<%
											} else {
										%>
										<td width="45%"><%=dto.getRegidate()%></td>
										<%
											}
										%>
									</tr>
									<tr>
										<td colspan="4" width="10%"><textarea style="width: 100%"
												name="" id="" cols="30" rows="10" readonly><%=dto.getContent()%> </textarea>
										</td>
									</tr>
								</table>
								<input type="button" class="btn btn-danger" value="신고 처리"
									onclick="return reportchk('<%=dto.getId()%>','<%=board_idx%>','<%=idx%>','report','board')"
									style="margin-left: 100px" />
								<!-- return writeChk(); -->
								<input type="button" class="btn btn-default" value="취소"
									onclick="history.back()" />
							</form>
						</c:if>
						<c:if test="${viewtype eq 'reply' }">
							<form name="WriteStudyFrm" action="./WriteProc.jsp">
								<input type="hidden" name="Page" value=${param.Page } />
								<table class="table table-bordered" width="770" border="1"
									cellspacing="0" cellpadding="2" style="text-align: center;">
									<tr style="">
										<!-- background-color: #f3f3f3 -->
										<%
											if (replydto.getId() == null) {
										%>
										<td width="20%">탈퇴회원</td>
										<%
											} else {
										%>
										<td width="20%"><%=replydto.getId()%></td>
										<%
											}
										%>
										<td width="20%"><%=replydto.getBoard_type()%>의 댓글</td>
										<td width="45%"><%=replydto.getContent()%></td>
										<%
											if (replydto.getRegidate() == null) {
										%>
										<td width="45%"></td>
										<%
											} else {
										%>
										<td width="45%"><%=replydto.getRegidate()%></td>
										<%
											}
										%>
									</tr>
								</table>
								<input type="button" class="btn btn-danger" value="신고 처리"
									onclick="return reportchk('<%=replydto.getId()%>','<%=board_idx%>','<%=idx%>','report','reply')"
									style="margin-left: 100px" />
								<!-- return writeChk(); -->
								<input type="button" class="btn btn-default" value="취소"
									onclick="history.back()" />
							</form>
						</c:if>
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