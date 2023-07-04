<%@page import="MemberModel.PagingUtil"%>
<%@page import="BoardModel.boardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="BoardModel.boardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	boardDAO dao = new boardDAO();
	String id = request.getParameter("id");
	Map<String, Object> parammap = new HashMap<String, Object>();

	//검색 기능 구현
	String searchColumn = request.getParameter("searchColumn");
	String searchWord = request.getParameter("searchWord");

	//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수
	String addQueryString = "";

	//검색기능
	if (searchColumn != null) {
		//페이지수 계산을 위한 파라미터 생성
		addQueryString = String.format("searchColumn=%s&searchWord=%s&", searchColumn, searchWord);

		//map타입의 컬렉션에 아래 키에 해당하는 항목 추가
		parammap.put("COLUMN", searchColumn);
		parammap.put("WORD", searchWord);
	}

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

	System.out.println("목록 불러오기전 id: " + id);
	//목록 가져오기   
	List<boardDTO> boardlist = dao.selectAll(parammap, id);
	dao.close();
%>
<html>
<head>
<%@ include file="/layout/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memberBoardInfo</title>
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
								<span style="color: green;"><%=id%></span> 님의 상세 게시글내역
							</h3>
						</div>
					</div>
				</div>

				<div class="col-md-12 to-animate">
					<div class="text-center">
						<!-- 게시판 S -->
						<table border="1" class="table table-bordered">
							<colgroup>
								<col width="10%" />
								<col />
								<col width="10%" />
								<col width="10%" />
								<col width="10%" />
								<col width="20%" />
							</colgroup>
							<tr style="background-color: #f3f3f3;">
								<td style="text-align: center;">No.</td>
								<td style="text-align: center;">제목</td>
								<td style="text-align: center;">게시판종류</td>
								<td style="text-align: center;">분류</td>
								<td style="text-align: center;">조회수</td>
								<td style="text-align: center;">게시일</td>
							</tr>
							<%
								if (boardlist.isEmpty()) {
							%>
							<tr>
								<td colspan="5" style="height: 100px;">등록한 글이 없습니다.</td>
							</tr>
							<%
								} else {

									int vNum = 0;
									int countNum = 0;
									for (boardDTO dto : boardlist) {
										vNum = totalRecordCount - (((nowPage - 1) * pageSize) + countNum++);
										System.out.println(vNum);
							%>
							<tr>
								<td style="text-align: center;"><%=dto.getrNum()%></td>
								<td style="text-align: center;"><a
									href="ReviewView.jsp?idx=<%=dto.getIdx()%>&board_type=<%=dto.getBoard_type()%>&category=<%=dto.getCategory()%>&nowPage=<%=nowPage%>&pageSize=<%=pageSize%>&id=<%=request.getParameter("id")%>"><%=dto.getTitle()%></a>
								</td>
								<td style="text-align: center;"><%=dto.getBoard_type()%></td>
								<td style="text-align: center;"><%=dto.getCategory()%></td>
								<td style="text-align: center;"><%=dto.getClick_cnt()%></td>
								<td style="text-align: center;"><%=dto.getRegidate()%></td>
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
					"memberBoardInfo.jsp?id=" + id + "&")%>
									<!-- BoardList.jsp?searchColumn=title&searchWord=철수&nowPage=10 -->
								</td>
							</tr>
						</table>
						<!-- 검색 -->
						<form name="searchFrm" method="get">
							<input type="hidden" name="nowPage" value="1" />
							<div class="row">
								<div class="col-md-2">
									<select name="searchColumn" class="form-control"
										id="searchColumn">
										<option value="title">제목</option>
										<option value="board_type">게시판종류</option>
										<option value="categofy">분류</option>
									</select>
								</div>
								<div class="col-md-6 input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-search"></i></span> <input
										name="searchWord" style="width: 70%" id="email" type="text"
										class="form-control"> <input type="hidden" name="id"
										value="<%=request.getParameter("id")%>" />
									<button type="submit" class="btn btn-default"
										style="margin-left: 0px" onclick="">Search</button>
								</div>
							</div>
						</form>
						<!-- 검색E -->
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