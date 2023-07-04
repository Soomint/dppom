<%@page import="QnAModel.QuitDTO"%>
<%@page import="QnAModel.QuitDAO"%>
<%@page import="QnAModel.PagingUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

	QuitDAO dao = new QuitDAO();

	Map<String, Object> map = new HashMap<String, Object>();
	
	// 페이징(게시물 여러페이지로 나누기)을 위한 로직 시작
	int totalRecordCount = dao.getTotalRecordCount(map);
	int pageSize = 5;
	int blockPage = 5;
	int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);
	// 현재페이지의 초기상태는 무조건 1, 페이지번호를 눌렀을 경우 해당페이지번호
	int nowPage = request.getParameter("nowPage") == null ? 1
			: Integer.parseInt(request.getParameter("nowPage"));
	// PagingUtil클래스파일에서 쿼리스트링으로 nowPage데이터를 전달받는다.
	int start = (nowPage - 1) * pageSize + 1;
	int end = nowPage * pageSize;
	
	map.put("start", start);
	map.put("end", end);

	List<QuitDTO> quitData = dao.selectAll(map);
	dao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap3.3.7/css/bootstrap.min.css">
<style>
span {
	font-size: 16px;
}

h2, h3 {
	font-family: "Source Sans Pro", Arial, sans-serif;
	color: black;
}

tr, td {
	font-family: Tahoma;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>관리자 페이지</title>
<script>
   function change(frm) {
      location.href = frm + '.jsp';
   }
   function change2(frm)
   {
	   location.href='quitreason.jsp';
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
							<h3>사용자 피드백(탈퇴) 게시판 관리</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="row">
						<div class="col-sm-6 text-left">
							건의사항 <select name="category" onchange="change(this.value);">
								<option value="">====================</option>
								<option value="qnaadmin">모두보기 이동</option>
								<option value="qnaadminfalse">답변안한 건의사항 보기 이동</option>
							</select>
						</div>
						<div class="col-sm-6 text-right">
							탈퇴사유
							<button onclick="change2();">탈퇴사유 이동</button>
						</div>
					</div>
					<div class="text-center">
						<table class="table table-bordered" width="770" border="1"
							cellspacing="0" cellpadding="2">
							<tr style="background-color: #f3f3f3;">
								<th width="10%">아이디</th>
								<th width="10%">등록날짜</th>
								<th width="*">탈퇴사유</th>
							</tr>
							<%
                        if (quitData.isEmpty()) {
                     %>
							<tr>
								<td colspan="9">등록된 글이 음슴</td>
							</tr>
							<%
                        } else {
                           for (QuitDTO dto : quitData) {
                     %>
							<tr>
								<td><%=dto.getId()%></td>
								<td><%=dto.getRegidate()%></td>
								<td><%=dto.getFeedback_reason()%></td>
							</tr>
							<%
                        }
                     %>
							<%} %>
						</table>
						<br />
						<table width="100%">
							<tr>
								<td style="text-align: center;"><%=PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, "quitreason.jsp?") %>
								</td>
							</tr>
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