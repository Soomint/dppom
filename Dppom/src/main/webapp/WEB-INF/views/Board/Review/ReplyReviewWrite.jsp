<%@page import="dppom.model.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dppom.model.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%@ include file="/WEB-INF/layout/head.jsp"%>
<style>
.navbar-brand {
	color: #6173f4;
}

caption {
	text-align: center;
}
</style>
<title>ReView</title>
<script>
   function replyCheck()
   {
      var frm = document.replyform;
      if(frm.content.value == "")
      {
         alert("댓글 내용을 입력하세요");
         frm.content.focus();
         return false;
      }
   }
   function deleteReply()
   {
      var really = confirm("정말 삭제하시겠습니까?");
      if(really) location.href="ReplyDeleteAction";
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
					<h2>REVIEW</h2>
				</div>
			</div>
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<!-- 테이블 S -->
						<form action="">
							<input type="hidden" name="filename"
								value="<%=request.getParameter("filename") %>" />
							<table id="commu_table" class="table  table-bordered">
								<thead>
									<tr>
										<th colspan="3" style="width: 10%; text-align: right;"><a
											href="" name="신고"><span class="glyphicon glyphicon-bell"
												style="width: 95%; text-align: right; color: red"></span></a> <a
											href="" name="좋아요" style="color: red"><span
												class="glyphicon glyphicon-thumbs-up"
												style="text-align: right; color: blue"></span></a></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td style="Width: 20%">${viewRow.category }</td>
										<td style="width: 60%">${viewRow.title }</td>
										<td style="Width: 20%">${viewRow.click_cnt }</td>
									</tr>
									<tr>
										<td>${viewRow.univ }</td>
										<td>${viewRow.major }</td>
										<td style="width: 30%">${viewRow.attachedfile}</td>
									</tr>
									<tr>
										<td colspan="3" style="height: 400px; text-align: left;">
											${viewRow.content }</td>
									</tr>
								</tbody>
								<%--    <%} //for문 끝%>
               <%}//else문 끝 %>    --%>
							</table>
							<!-- 테이블 E  -->
						</form>
						<br />
						<table width="100%"
							style="background-color: #E0FFDB; border: 1px solid #BDBDBD;"
							cellpadding="5" cellspacing="0">
							<colgroup>
								<col width="20%" />
								<col width="*" />
								<col width="93px" />
								<col width="187px" />
							</colgroup>
							<caption>
								<h3 style="color: #8C8C8C;">댓&nbsp;&nbsp;글&nbsp;&nbsp;목&nbsp;&nbsp;록</h3>
							</caption>
							<c:if test="${empty replyData}">
								<tr>
									<td colspan="4">등록된 댓글이 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach items="${replyData}" var="re">
								<c:set var="writer" value="${fn:trim(re.id)}" />
								<tr>
									<c:choose>
										<c:when test="${empty writer}">
											<td style="text-align: left;">탈퇴한 회원입니다.</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: left;">&nbsp;${re.id}</td>
										</c:otherwise>
									</c:choose>
									<td style="text-align: left;">${re.content}</td>
									<td>${re.regidate}</td>
									<td></td>
								</tr>
								<c:if test="${param.reply_idx eq re.idx}">
									<tr>
										<td colspan="4">
											<form action="ReReplyAddAction" name="replyform"
												onsubmit="return replyCheck();">
												<input type="hidden" name="reply_idx"
													value="<%=request.getParameter("reply_idx")%>" /> <input
													type="hidden" name="board_type"
													value="<%=request.getParameter("board_type")%>" /> <input
													type="hidden" name="idx"
													value="<%=request.getParameter("idx")%>" /> <input
													type="hidden" name="nowPage"
													value="<%=request.getParameter("nowPage")%>" /> <input
													type="hidden" name="id"
													value="${sessionScope.login_id.id }" /> <input type="text"
													name="content" style="width: 700px;" />&nbsp;&nbsp;
												<button class="btn btn-info" style="height: 37px;">댓글남기기</button>
												&nbsp;&nbsp;
												<button type="button" class="btn btn-warning"
													onclick="history.back()" style="height: 37px;">Back</button>
											</form>
										</td>
									</tr>
								</c:if>
							</c:forEach>
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