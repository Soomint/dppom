<%@page import="StudyModel.studyDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="StudyModel.studyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/layout/head.jsp"%>
</head>
<%
	//파라미터 
	int idx = Integer.parseInt(request.getParameter("idx"));
	System.out.println("idx" + idx);
	String Page = request.getParameter("Page");

	studyDAO dao = new studyDAO();
	studyDTO dto = dao.selectOne(idx);

	dao.close();
%>
<script>
	function modifyChk() {
		var f = document.ModifyStudyFrm;

		if (f.category.value == "") {
			alert('종류를 선택하세요');
			f.category.focus();
			return false;
		}
		if (f.title.value == "") {
			alert("제목을 입력하세요.");
			f.title.focus();
			return false;
		}
		if (f.content.value == "") {
			alert("내용을 입력하세요.");
			f.content.focus();
			return false;
		}
		if (f.url.value == "") {
			alert("url를  입력하세요.");
			f.URI.focus();
			return false;
		}
	}
</script>
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
							<h3>어학 게시물 관리자 페이지</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<form name="ModifyStudyFrm" action="./ModifyProc.jsp">
							<input type="hidden" name=Page value=${param.Page } />
							<table class="table table-bordered" border="1px  black">
								<tr>
									<td style="width: 30%">
										<%
											if (Page.contains("EnglishStudy.jsp")) {
										%> ENGLISH &nbsp;&nbsp; <select name="category">
											<option value="">==어학 시험 종류==</option>
											<option value="TOEIC"
												<%if (dto.getCategory().equals("TOEIC")) {%> selected <%}%>>TOEIC</option>
											<option value="TOEIC SPEAKING"
												<%if (dto.getCategory().equals("TOEIC SPEAKING")) {%>
												selected <%}%>>TOEIC Speaking</option>
											<option value="TOEFL"
												<%if (dto.getCategory().equals("TOEFL")) {%> selected <%}%>>TOEFL</option>
											<option value="OPIC"
												<%if (dto.getCategory().equals("OPIC")) {%> selected <%}%>>OPIC</option>
									</select> <%
 	} else if (Page.contains("LicenseStudy.jsp")) {
 %> <input type="text"
										name="study_type" value="LICENSE" /> <select name="category">
											<option value="" style="height: 50px">==자격증 종류==</option>
											<option value="컴퓨터활용자격증"
												<%if (dto.getCategory().equals("컴퓨터활용자격증")) {%> selected
												<%}%>>컴활</option>
											<option value="한국사"
												<%if (dto.getCategory().equals("한국사")) {%> selected <%}%>>한국사</option>
											<option value="GTQ"
												<%if (dto.getCategory().equals("GTQ")) {%> selected <%}%>>GTQ</option>
											<option value="MOS"
												<%if (dto.getCategory().equals("MOS")) {%> selected <%}%>>MOS</option>
									</select> <%
 	}
 %>
									</td>
									<td>제목 <input type="text" name="title"
										value="<%=dto.getTitle()%>"
										style="width: 95%; margin-top: 0px; margin-bottom: 0px" /></td>
								</tr>
								<tr>
									<td colspan="2" style="height: 10px">내용</td>
								</tr>
								<tr>
									<td colspan="2"><textarea name="content" cols="30"
											rows="10" style="width: 100%;"><%=dto.getContent()%></textarea></td>
								</tr>
								<tr>
									<td colspan="2">URL &nbsp;<input type="text" name="url"
										style="width: 96%" value="<%=dto.getUrl()%>" /></td>
								</tr>
							</table>
							<input type="submit" value="수정" onclick="return modifyChk();"
								style="margin-left: 180px" />
							<%-- location.href='./ModifyProc.jsp?idx=<%=idx %>' --%>
							<input type="button" value="취소" onclick="history.back()" /> <input
								type="hidden" name="idx" value=<%=idx%> />
						</form>
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