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

#cate:hover {
	background-color: black;
	color: white !important;
}
</style>
<title>大PPOM - QnA</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
<script>
	function check(id)
	{
		if(id == null)
		{
			alert('로그인이 필요합니다.로그인 페이지로 이동합니다.');
			login();
			return false;
		}
	}
	function login()
	{
		location.href='Login?PageURL=QnA';
	}
</script>
</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop_black.jsp"%>
	<%@ include file="/WEB-INF/layout/LayoutTop1_black.jsp"%>
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
								<form action="QnAWrite" onsubmit="return check(${login_id});">
									<button type="submit" class="btn" style="margin-bottom: 5px;">Write</button>
								</form>
							</div>
						</div>
						<!-- 테이블 S -->
						<table id="commu_table" class="table table-hover table-bordered">
							<thead>
								<tr>
									<th style="width: 10%; text-align: center;">No</th>
									<th style="text-align: center;">제목</th>
									<th style="text-align: center;">작성자</th>
									<th style="width: 10%; text-align: center;">날짜</th>
									<th style="width: 10%; text-align: center;">답변유무</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty qnaData}">
									<tr>
										<td colspan="5">등록된 글이 없습니다.</td>
									</tr>
								</c:if>
								<c:forEach items="${qnaData}" var="qna" varStatus="loop">
									<c:choose>
										<c:when test="${qna.reply_flag == 'O'}">
											<tr>
												<td rowspan="2" width="10%"><br />${qna.rNum}</td>
												<td><a
													href="QnAPass?idx=${qna.idx}&reply=${qna.reply_flag}&nowPage=${nowPage}"
													onclick="return check(${login_id});">${qna.title}</a></td>
												<c:if test="${qna.id eq null}">
													<td width="10%">탈퇴회원</td>
												</c:if>
												<c:if test="${qna.id ne null}">
													<td width="10%">${qna.id}</td>
												</c:if>
												<td width="10%">${qna.regidate}</td>
												<td width="10%"></td>
											</tr>
											<tr style="background-color: aliceblue;">
												<td><a
													href="QnAPass?idx=${qna.idx}&reply=${qna.reply_flag}&nowPage=${nowPage}"
													onclick="return check(${login_id});">${qna.title}</a></td>
												<td width="10%">관리자</td>
												<td width="10%">${qna.regidate}</td>
												<td width="10%">답변완료</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td width="10%">${qna.rNum}</td>
												<td><a
													href="QnAPass?idx=${qna.idx}&reply=${qna.reply_flag}&nowPage=${nowPage}"
													onclick="return check(${login_id});">${qna.title}</a></td>
												<c:if test="${qna.id eq null}">
													<td width="10%">탈퇴회원</td>
												</c:if>
												<c:if test="${qna.id ne null}">
													<td width="10%">${qna.id}</td>
												</c:if>
												<td width="10%">${qna.regidate}</td>
												<td width="10%"></td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</tbody>
							<%-- 	<%} //for문 끝%>
					<%}//else문 끝 %>	 --%>
						</table>
						<!-- 테이블 E  -->
						<br />
						<table width="100%">
							<tr>
								<td style="text-align: center;">${pagingImg}</td>
							</tr>
						</table>
						<form action="QnASearch" method="get">
							<input type="hidden" name="nowPage" value="1" />
							<table width="100%">
								<tr>
									<td style="text-align: center;"><select
										name="searchColumn" style="height: 34px;">
											<option value="id">작성자</option>
											<option value="title">제목</option>
									</select> <input type="text" name="searchWord" value="${searchWord}" />
										<input type="submit" value="검색하기" /></td>
								</tr>
							</table>
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