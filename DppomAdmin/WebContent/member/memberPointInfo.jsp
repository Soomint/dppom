<%@page import="MemberModel.PagingUtil"%>
<%@page import="MemberModel.pointDTO"%>
<%@page import="MemberModel.pointDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	pointDAO dao = new pointDAO();
	String id = request.getParameter("id"); //선택한 id 받아오기
	Map<String, Object> parammap = new HashMap<String, Object>();

	//페이징을 위한 로직 시작
	int totalRecordCount = dao.getTotalRecordCount(parammap, id);
	System.out.println(totalRecordCount);
	int pageSize = 10; //Integer.parseInt(application.getInitParameter("PAGE_SIZE"));
	int blockPage = 5; //Integer.parseInt(application.getInitParameter("BLOCK_PAGE"));
	int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);

	System.out.println("전체레코드 수:" + totalRecordCount); //20개 내외
	System.out.println("전체페이지 수:" + totalPage); //5페이지

	//초기상태는 무조건1, 페이지 번호를 눌렀을 경우 해당 페이지 번호
	int nowPage = request.getParameter("nowPage") == null ? 1
			: Integer.parseInt(request.getParameter("nowPage"));

	int start = (nowPage - 1) * pageSize + 1; //페이지 내 게시글의 시작
	int end = nowPage * pageSize; //페이지 내 게시글의 끝

	parammap.put("start", start);
	parammap.put("end", end);

	//페이징을 위한 로직 끝 

	//목록 가져오기
	List<pointDTO> memlist = dao.selectAll(parammap, id);
	dao.close();
%>
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memberPointInfo</title>
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
							<h3>
								<span style="color: green;"><%=id%></span> 님의 상세 포인트내역
							</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<select name="cate" onchange="location.href=this.value"
						style="color: black; width: 80px; height: 30px; text-align: left;">
						<option value="memberPointInfo.jsp?id=<%=id%>">전체</option>
						<option value="pointPlus.jsp?id=<%=id%>">적립</option>
						<option value="pointMinus.jsp?id=<%=id%>">차감</option>
					</select>
					<!-- 게시판 S> -->
					<div class="text-center">
						<table border="1" class="table table-bordered">
							<tr style="background-color: #f3f3f3;">
								<th>NO.</th>
								<th>ID</th>
								<th>상세정보</th>
								<th>얻은 날짜</th>
								<th>종류</th>
								<th>가감</th>
							</tr>
							<%
								if (memlist.isEmpty()) {
							%>
							<tr>
								<td colspan="5" style="height: 100px;">포인트 내역이 없습니다.</td>
							</tr>
							<%
								} else {

									int vNum = 0;
									int countNum = 0;
									for (pointDTO dto : memlist) {
										vNum = totalRecordCount - (((nowPage - 1) * pageSize) + countNum++);
										System.out.println(vNum);
							%>
							<tr>
								<td style="text-align: center;"><%=vNum%></td>
								<td style="text-align: center;"><%=dto.getId()%></td>
								<td style="text-align: center;"><%=dto.getDetail()%></td>
								<td style="text-align: center;"><%=dto.getUseddate()%></td>
								<td style="text-align: center;"><%=dto.getUsed_type()%></td>
								<td style="text-align: center;"><%=dto.getAdjust()%></td>
							</tr>
							<%
								}
								}
							%>
						</table>
						<!-- 게시판 E -->
						<!-- 페이징 -->
						<table width="100%;">
							<tr>
								<td style="text-align: center;">
									<!-- ◀◀ ◀ 1 2 3 4 5 ▶ ▶▶   --> <%=PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
					"memberPointInfo.jsp?id=" + id + "&")%>
									<!-- BoardList.jsp?searchColumn=title&searchWord=철수&nowPage=10 -->
								</td>
							</tr>
						</table>
						<!-- 페이징 E -->
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