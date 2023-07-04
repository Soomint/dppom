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
<title>마이 페이지 - 내글보기</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
</head>
<script>
   function checkType(){
      
      return "redirect:myWritingList";
   }
</script>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop_black.jsp"%>
	<%@ include file="/WEB-INF/layout/LayoutTop1_black.jsp"%>
	<!-- TOP (E) --> </header>
	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<!-- My Page(메뉴) -->
			<%@ include file="/WEB-INF/layout/LayoutMyPage.jsp"%>

			<!-- My Page(메뉴) E  -->
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 section-heading text-center">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 to-animate">
							<h3>내가 쓴글 보기</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<!-- 테이블 S -->
						<div style="text-align: left; padding-bottom: 10px;">
							<select name="" id="" onchange="location.href=this.value"
								style="width: 113px; height: 35px;">
								<option value="myWritingList?id=<%=request.getParameter("id")%>">전체</option>
								<option
									value="myWritingList?id=<%=request.getParameter("id")%>&board_type=review"
									<c:if test="${board_type eq 'review'}"> selected</c:if>>후기</option>
								<option
									value="myWritingList?id=<%=request.getParameter("id")%>&board_type=dataroom"
									<c:if test="${board_type eq 'dataroom'}"> selected</c:if>>자료실</option>
								<option
									value="myWritingList?id=<%=request.getParameter("id")%>&board_type=party"
									<c:if test="${board_type eq 'party'}"> selected</c:if>>사람찾아요</option>
							</select>
						</div>
						<table id="commu_table" class="table table-hover table-bordered">
							<thead>
								<tr>
									<th style="width: 10%; text-align: center;">게시판</th>
									<th style="text-align: center;">제목</th>
									<th style="width: 10%; text-align: center;">작성자</th>
									<th style="width: 10%; text-align: center;">날짜</th>
									<th style="width: 10%; text-align: center;">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty listRows}">
										<!-- 등록된글이 없는경우 노출 -->
										<tr>
											<td colspan="7" style="height: 100px;">등록된 글이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${listRows}" var="row" varStatus="loop">
											<!-- 글이 있는경우 노출 -->
											<tr>
												<td style="text-align: center;">${row.board_type }</td>
												<td><a
													href="./myWritingView?idx=${row.idx }&nowPage=${nowPage}&category=${row.category}&checking=mylist"
													onclick="return checkFrm(${sessionScope.login_id.id });">${row.title}</a></td>
												<td style="text-align: center;">${row.id }</td>
												<td style="text-align: center;">${row.regidate }</td>
												<td style="text-align: center;">${row.click_cnt }</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
							<%--    <%} //for문 끝%>
            <%}//else문 끝 %>    --%>
						</table>
						<!-- 테이블 E  -->
						<br />
						<!-- 페이징 -->
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