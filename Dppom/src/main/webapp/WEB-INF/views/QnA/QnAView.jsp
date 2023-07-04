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

#left {
	text-align: left;
}
</style>
<title>QnAView</title>
<script>
   function really()
   {
      var question = confirm('정말로 삭제하시겠습니까?');
      return question;
   }
   function back()
   {
      location.href='../DppomAdmin/qna/qnaadmin.jsp?category=all';
   }
</script>
</head>
<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
	<%@ include file="/WEB-INF/layout/LayoutTop2.jsp"%>

	<!-- TOP (E) --> </header>
	<div id="fh5co-about-us" data-section="about">
		<div class="container">
			<div class="row" id="team">
				<div class="col-md-12 section-heading text-center to-animate">
					<h2>QnAView</h2>
				</div>
			</div>
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<div class="row">
							<div class="col-md-12" style="text-align: right">
								<c:if
									test="${sessionScope.login_id.id eq 'wm' || sessionScope.login_id.id eq 'ms' || sessionScope.login_id.id eq 'jg'}">
									<button type="button" class="btn" onclick="back();"
										style="margin-bottom: 5px;">관리자Back</button>&nbsp;
                     </c:if>
								<button type="button" class="btn"
									onclick="location.href='QnA?nowPage=1';"
									style="margin-bottom: 5px;">목록보기</button>
							</div>
						</div>
						<!-- 테이블 S -->
						<table id="commu_table" class="table table-bordered">
							<thead>
								<tr>
									<th colspan="3" style="width: 10%; text-align: center;"><a
										href="#" name="신고"><span class="glyphicon glyphicon-bell"
											style="width: 95%; text-align: right; color: red"></span></a> <a
										href="#" name="좋아요" style="color: red"><span
											class="glyphicon glyphicon-thumbs-up"
											style="text-align: right; color: blue"></span></a></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="2">제목 : ${viewData.title}</td>
								</tr>
								<tr>
									<td>아이디</td>
									<c:if test="${viewData.id eq null}">
										<td>탈퇴회원</td>
									</c:if>
									<c:if test="${viewData.id ne null}">
										<td>${viewData.id}</td>
									</c:if>
								</tr>
								<tr>
									<td>최종수정날짜</td>
									<td>${viewData.regidate}</td>
								</tr>
								<c:if test="${param.reply eq 'O'}">
									<tr>
										<td>최종답변날짜</td>
										<td>${viewData.reply_date}</td>
									</tr>
								</c:if>
								<tr>
									<td colspan="2">건의사항</td>
								</tr>
								<tr>
									<td colspan="2">
										<p style="height: 200px;" id="left">${viewData.content}</p>
									</td>
								</tr>
								<c:if test="${param.reply eq 'O'}">
									<tr>
										<td colspan="2">관리자 답변내용</td>
									</tr>
									<tr>
										<td colspan="2">
											<p style="height: 200px;" id="left">${viewData.reply}</p>
										</td>
									</tr>
								</c:if>
							</tbody>
							<%--    <%} //for문 끝%>
               <%}//else문 끝 %>    --%>
						</table>
						<div class="col-md-12" style="text-align: center">
							<c:if
								test="${param.reply eq 'X' && (sessionScope.login_id.id eq 'wm' || sessionScope.login_id.id eq 'ms' || sessionScope.login_id.id eq 'jg')}">
								<form action="QnAAdminWrite" method="post">
									<input type="hidden" name="idx" value="${viewData.idx}" /> <input
										type="hidden" name="id" value="${viewData.id}" /> <input
										type="hidden" name="pass" value="${viewData.qna_pass}" /> <input
										type="hidden" name="title" value="${viewData.title}" /> <input
										type="hidden" name="content" value="${viewData.content}" />
									<button type="submit" class="btn" style="margin-bottom: 5px;">관리자
										글쓰기</button>
								</form>
							</c:if>
							<c:if
								test="${param.reply eq 'X' && (sessionScope.login_id.id eq viewData.id)}">
								<form action="QnAEdit" method="post">
									<input type="hidden" name="idx" value="${viewData.idx}" /> <input
										type="hidden" name="id" value="${viewData.id}" /> <input
										type="hidden" name="pass" value="${viewData.qna_pass}" /> <input
										type="hidden" name="title" value="${viewData.title}" /> <input
										type="hidden" name="content" value="${viewData.content}" />
									<button type="submit" class="btn" style="margin-bottom: 5px;">수
										정</button>
								</form>
							</c:if>
							<c:if
								test="${param.reply eq 'O' && (sessionScope.login_id.id eq 'wm' || sessionScope.login_id.id eq 'ms' || sessionScope.login_id.id eq 'jg')}">
								<form action="QnAAdminEdit" method="post">
									<input type="hidden" name="idx" value="${viewData.idx}" /> <input
										type="hidden" name="id" value="${viewData.id}" /> <input
										type="hidden" name="pass" value="${viewData.qna_pass}" /> <input
										type="hidden" name="title" value="${viewData.title}" /> <input
										type="hidden" name="content" value="${viewData.content}" /> <input
										type="hidden" name="reply" value="${viewData.reply}" />
									<button type="submit" class="btn" style="margin-bottom: 5px;">관리자
										글수정</button>
								</form>
							</c:if>
							<c:if test="${sessionScope.login_id.id eq viewData.id}">
								<form action="QnADelete" method="post"
									onsubmit="return really();">
									<input type="hidden" name="idx" value="${viewData.idx}" />
									<button type="submit" class="btn" style="margin-bottom: 5px;">삭
										제</button>
								</form>
							</c:if>
						</div>
						<!-- 테이블 E  -->
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