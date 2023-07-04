<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>ReView</title>
<script>
function boardreport(id,board_idx,board_type,board_id){  // 신고id , 글번호 , 게시판 type , 글제목 , 작성자 id 
	
	var isReport = window.confirm('이 글을 신고 하시겠습니까?');
	
	if(isReport == true){
		var report_reason = prompt("신고 이유를 작성해주세요 (필수)",""); 
		if( report_reason ==  null){
			alert("신고를 취소합니다.");
		}
		else{	
			location.href='boardReportAction?id='+id+'&board_idx='+board_idx+'&report_reason='+report_reason+'&board_type='+board_type+'&board_id='+board_id;
		}
	}	
 }
 
function replyReport(id,reply_idx,return_view){  // 신고id , 글번호 , 게시판 type , 글제목 , 작성자 id 
	
	var isReport = window.confirm('이 글을 신고 하시겠습니까?');
	
	if(isReport == true){
		var report_reason = prompt("신고 이유를 작성해주세요 (필수)",""); 
		if( report_reason ==  null){
			alert("신고를 취소합니다.");
		}
		else{
			//id는 커맨드에서 idx로 select따로 해줘야 할듯 
			location.href='replyReportAction?id='+id+'&reply_idx='+reply_idx+'&report_reason='+report_reason+'&return_view='+return_view;
		}
	}	
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
					<h2>Party</h2>
				</div>
			</div>
			<div class="row row-bottom-padded-lg" id="about-us">
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<div class="row">
							<div class="col-md-12" style="text-align: right">
								<button type="button" class="btn" onclick="history.back()"
									style="margin-bottom: 5px;">Back</button>
							</div>
						</div>
						<!-- 테이블 S -->
						<form action="">
							<table id="commu_table" class="table  table-bordered">
								<thead>
									<tr>
										<th colspan="3" style="width: 10%; text-align: right;">
											<button type="button" class="btn btn-danger"
												onclick="boardreport('${sessionScope.login_id.id}','${viewRow.idx}','${viewRow.board_type }','${viewRow.id }');"
												style="height: 40px;">신고</button>
										</th>
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
										<td style="width: 30%">현재인원: ${viewRow.now_party } / 모집인원
											: ${viewRow.total_party }</td>
									</tr>
									<tr>
										<td colspan="3" style="height: 400px; text-align: left;">
											${viewRow.content }</td>
									</tr>
								</tbody>
								<%--    <%} //for문 끝%>
               <%}//else문 끝 %>    --%>
							</table>
							<c:choose>
								<c:when test="${viewRow.id == sessionScope.login_id.id }">
									<!-- 로그인된 회원이 작성자일 경우 -->
									<div class="col-md-12" style="text-align: center">
										<button type="button" class="btn"
											onclick="javascript:location.href='ModifyParty?idx=${viewRow.idx}&board_type=${viewRow.board_type }&nowPage=<%=request.getParameter("nowPage") %>&category=${viewRow.category }'"
											style="margin-bottom: 5px;">수 정</button>
										<button type="button" class="btn"
											onclick="javascript:location.href='deleteParty?id=${viewRow.id}&id2=${sessionScope.login_id.id }&idx=${viewRow.idx}&nowPage=<%=request.getParameter("nowPage") %>&category=${viewRow.category }'"
											style="margin-bottom: 5px;">삭 제</button>
										<c:choose>
											<c:when test="${checking == null }">
												<button type="button" class="btn"
													onclick="javascript:location.href='<%=request.getParameter("filename") %>'"
													style="margin-bottom: 5px;">목록으로</button>
											</c:when>
										</c:choose>
									</div>
								</c:when>
								<c:otherwise>
									<!-- 작성자가 아닐경우 -->
									<div class="col-md-12" style="text-align: center">
										<button type="button" class="btn"
											onclick="javascript:location.href=''<%=request.getParameter("filename") %>?nowPage=<%=request.getParameter("nowPage") %>'"
											style="margin-bottom: 5px;">목록으로</button>
									</div>
								</c:otherwise>
							</c:choose>
							<!-- 테이블 E  -->
						</form>
						<br />
						<table width="100%"
							style="background-color: #E0FFDB; border: 1px solid #BDBDBD;"
							cellpadding="5" cellspacing="0">
							<colgroup>
								<col width="20%" />
								<col width="*" />
								<col width="50px" />
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
							<c:set var="user" value="${sessionScope.login_id.id}" />
							<c:forEach items="${replyData}" var="re">
								<c:set var="writer" value="${fn:trim(re.id)}" />
								<c:set var="date" value="${re.regidate}" />
								<tr>
									<td style="text-align: left;">&nbsp;${re.id}</td>
									<td style="text-align: left;">${re.content}</td>
									<td>
										<button class="btn btn-xs btn-danger" type="button"
											onclick="replyReport('${sessionScope.login_id.id}','${re.idx}','party');">신고</button>
									</td>
									<td>${re.regidate}</td>
									<td>
										<div class="row">
											<div class="col-xs-4 " style="padding: 5px 5px;">
												<form action="ReReplyAdd" style="padding-left: 30px;">
													<input type="hidden" name="reply_idx" value="${re.idx}" />
													<input type="hidden" name="board_type"
														value="<%=request.getParameter("filename")%>" /> <input
														type="hidden" name="idx"
														value="<%=request.getParameter("idx")%>" /> <input
														type="hidden" name="nowPage"
														value="<%=request.getParameter("nowPage")%>" />
													<button class="btn btn-xs btn-info">댓글</button>
												</form>
											</div>
											<c:if test="${fn:contains(writer, user) && date ne null}">
												<div class="col-xs-4 "
													style="padding: 5px 0px 5px 10px; width: 50px;">
													<form action="ReplyEdit" style="width: 50px;">
														<input type="hidden" name="reply_idx" value="${re.idx}" />
														<input type="hidden" name="board_type"
															value="<%=request.getParameter("filename")%>" /> <input
															type="hidden" name="idx"
															value="<%=request.getParameter("idx")%>" /> <input
															type="hidden" name="nowPage"
															value="<%=request.getParameter("nowPage")%>" />
														<button class="btn btn-xs btn-warning">수정</button>
													</form>
												</div>
											</c:if>
											<c:if test="${fn:contains(writer, user) && date ne null}">
												<div class="col-xs-4"
													style="padding: 5px 0px 5px 17px; width: 50px;">
													<form action="ReplyDeleteAction"
														onsubmit="return deleteReply();"
														style="padding-right: 30px;">
														<input type="hidden" name="idx" value="${re.idx}" /> <input
															type="hidden" name="board_type"
															value="<%=request.getParameter("filename")%>" /> <input
															type="hidden" name="board_idx"
															value="<%=request.getParameter("idx")%>" /> <input
															type="hidden" name="nowPage"
															value="<%=request.getParameter("nowPage")%>" />
														<button class="btn btn-xs btn-danger">삭제</button>
													</form>
												</div>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
						<br /> ${pagingImg} <br />
						<table width="100%">
							<colgroup>
								<col width="90%" />
								<col width="10%" />
							</colgroup>
							<form action="ReplyAddAction" name="replyform"
								onsubmit="return replyCheck();">
								<input type="hidden" name="id"
									value="${sessionScope.login_id.id}" /> <input type="hidden"
									name="board_type" value="<%=request.getParameter("filename")%>" />
								<input type="hidden" name="board_idx"
									value="<%=request.getParameter("idx")%>" /> <input
									type="hidden" name="nowPage"
									value="<%=request.getParameter("nowPage")%>" />
								<tr>
									<td><input type="text" name="content"
										style="width: 1040px;" /></td>
									<td><button>댓글남기기</button></td>
								</tr>
							</form>
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