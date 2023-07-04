<%@page import="MemberModel.PagingUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="MemberModel.memberDAO"%>
<%@page import="MemberModel.memberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	memberDAO dao = new memberDAO();
	Map<String, Object> parammap = new HashMap<String, Object>();

	//페이징을 위한 로직 시작
	int totalRecordCount = dao.getTotalRecordCount(parammap);
	System.out.println(totalRecordCount);
	int pageSize = 20; //Integer.parseInt(application.getInitParameter("PAGE_SIZE"));
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

	//회원 전체 가져오기
	List<memberDTO> memlist = dao.selectAll(parammap);
	dao.close();
%>
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<script src="../bootstrap3.3.7/js/jquery.min.js"></script>
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
							<h3>회원정보 처리 관리자</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<table class="table table-bordered" border="1">
							<colgroup>
								<col style="width: 10%" />
								<col style="width: 10%" />
								<col style="width: 20%" />
								<col style="width: 20%" />
								<col style="width: 20%" />
								<col style="width: 10%" />
								<col style="width: 10%" />
							</colgroup>
							<tr style="background-color: #f3f3f3;">
								<td style="text-align: center;">아이디</td>
								<td style="text-align: center;">이름</td>
								<td style="text-align: center;">이메일</td>
								<td style="text-align: center;">총 포인트</td>
								<td style="text-align: center;">가입일</td>
								<td style="text-align: center;">인증여부</td>
								<td></td>
							</tr>
							<%
								if (memlist.isEmpty()) {
							%>
							<tr>
								<td colspan="5" style="height: 100px;">등록된 글이 없습니다.</td>
							</tr>
							<%
								} else {

									int vNum = 0;
									int countNum = 0;
									for (memberDTO dto : memlist) {
										vNum = totalRecordCount - (((nowPage - 1) * pageSize) + countNum++);
										System.out.println(vNum);
							%>
							<tr>
								<td style="text-align: center;"><a
									href="./memberInfo.jsp?id=<%=dto.getId()%>"><%=dto.getId()%></a></td>
								<td style="text-align: center;"><%=dto.getName()%></td>
								<td style="text-align: center;"><%=dto.getEmail()%></td>
								<td style="text-align: center;"><%=dto.getPoint()%></td>
								<td style="text-align: center;"><%=dto.getRegidate()%></td>
								<td style="text-align: center;"><%=dto.getCerti_flag()%></td>
								<td>
									<%
										if (dto.getCerti_flag().equals("X") && !dto.getCerti_url().equals("1")) {
									%> <a href="./approval.jsp?id=<%=dto.getId()%>">승인</a> <%
 	}
 %>
								</td>
							</tr>
							<%
								}
								}
							%>
						</table>
						<!-- 페이징 -->
						<table width="100%;">
							<tr>
								<td style="text-align: center;">
									<%=PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, "MemberAdmin.jsp?")%>
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