<%@page import="ReportModel.ReportDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="ReportModel.ReportDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

	ReportDAO dao = new ReportDAO();

	Map<String, Object> map = new HashMap<String, Object>();

	List<ReportDTO> reportData = dao.selectAll(map);

	dao.close();

	int count = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>관리자 페이지</title>
<script>
	function really() {
		var check = confirm('정말로 삭제하시겠습니까?');
		return check;
	}

	function fakechk() {
		var check = confirm('허위신고 처리 하시겠습니까?');
		return check;
	}

	function reportchk(board_id, board_idx, idx, type) {
		var check = confirm('신고 처리 하시겠습니까?');
		//alert(board_id+' '+board_idx+' '+idx);
		if (check == true) {
			// 신고 처리 
			location.href = 'reportAction.jsp?id=' + board_id + '&board_idx='
					+ board_idx + '&idx=' + idx + '&type=' + type;
		}
	}
</script>
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
			<br /> <br />
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
						<table class="table table-bordered" width="770" border="1"
							cellspacing="0" cellpadding="2" style="text-align: center;">
							<tr style="background-color: #f3f3f3;">
								<th width="20%">신고자 ID</th>
								<th width="20%">게시판 이름</th>
								<th width="10%">신고 이유</th>
								<th width="10%">글 내역보기</th>
								<th width="10%">허위신고처리</th>
							</tr>
							<%
								count = 0;
								for (ReportDTO dto : reportData) {
									if (dto.getReply_idx() == 0)
										count++;
								}
								if (count == 0) {
							%>
							<tr>
								<td colspan="6">등록된 글이 없습니다.</td>
							</tr>
							<%
								} else {
									for (ReportDTO dto : reportData) {
										if (dto.getReply_idx() == 0) {
							%>
							<tr>
								<%
									if (dto.getId() == null) {
								%>
								<td>탈퇴회원</td>
								<%
									} else {
								%>
								<td><%=dto.getId()%></td>
								<%
									}
								%>
								<td><%=dto.getBoard_type()%></td>
								<td><%=dto.getReport_reason()%></td>
								<td><a
									href="./reportDetailView.jsp?board_idx=<%=dto.getBoard_idx()%>&idx=<%=dto.getIdx()%>&type=board"
									style="text-decoration: none">내역보기</a></td>
								<td><input type="button" class="btn btn-warning"
									value="허위 신고"
									onclick="return reportchk('<%=dto.getId()%>','<%=dto.getBoard_idx()%>','<%=dto.getIdx()%>','fakereport')"
									style="" /></td>
								<%-- <td><a href="./deleteReport.jsp?idx=<%=dto.getIdx() %>" onclick="return really();" style="text-decoration:none">삭제 </a></td> --%>
							</tr>
							<%
								}
									}
								}
							%>
						</table>
						<!-- ///////////////////////////////댓 글 ///////////////////////////////////////// -->
						<div class="col-md-12 section-heading text-center">
							<div class="row">
								<div class="col-md-8 col-md-offset-2 to-animate">
									<h3>댓글 신고 내용</h3>
								</div>
							</div>
						</div>
						<table class="table table-bordered" width="770" border="1"
							cellspacing="0" cellpadding="2" style="text-align: center;">
							<tr style="background-color: #f3f3f3;">
								<th width="20%">신고자 ID</th>
								<th width="10%">신고 이유</th>
								<th width="10%">글 내역보기</th>
								<th width="10%">허위신고처리</th>
							</tr>
							<%
								count = 0;
								for (ReportDTO dto : reportData) {
									if (dto.getReply_idx() != 0)
										count++;
								}
								if (count == 0) {
							%>
							<tr>
								<td colspan="4">등록된 댓글이 없습니다.</td>
							</tr>
							<%
								} else {
									for (ReportDTO dto : reportData) {
										if (dto.getReply_idx() != 0) {
							%>
							<tr>
								<td><%=dto.getId()%></td>
								<td><%=dto.getReport_reason()%></td>
								<td><a
									href="./reportDetailView.jsp?board_idx=<%=dto.getReply_idx()%>&idx=<%=dto.getIdx()%>&type=reply"
									style="text-decoration: none">내역보기</a></td>
								<td><input type="button" class="btn btn-warning"
									value="허위 신고"
									onclick="return reportchk('<%=dto.getId()%>','<%=dto.getBoard_idx()%>','<%=dto.getIdx()%>','fakereport')"
									style="" /></td>
							</tr>
							<%
								}
									}
								}
							%>
						</table>
					</div>
				</div>
			</div>
		</div>
		<br /> <br />
		<!-- Botton (S) -->
		<%@ include file="/layout/LayoutBottom.jsp"%>
		<!--  Bottom(E) -->
	</div>
</body>
</html>