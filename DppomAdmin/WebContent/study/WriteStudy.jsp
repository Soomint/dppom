<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/layout/head.jsp"%>
</head>
<script>
	function writeChk() {
		var f = document.WriteStudyFrm;

		if (f.menu.value == "") {
			alert('분야를 선택하세요.');
			f.menu.focus();
			return false;
		}
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
		if (f.contents.value == "") {
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
							<h3>어학/자격증 게시물 등록</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<form name="WriteStudyFrm" action="./WriteProc.jsp">
							<input type="hidden" name="Page" value=${param.Page } />
							<table class="table table-bordered">
								<tr>
									<td style="width: 35%">
										<%
											String Page = request.getParameter("Page");
											if (Page.equals("EnglishStudy.jsp")) {
										%> <input type="text" name="study_type" value="ENGLISH"
										style="width: 18%" readonly /> <select name="category">
											<option value="">==어학 시험 종류==</option>
											<option value="TOEIC">TOEIC</option>
											<option value="TOEIC SPEAKING">TOEIC SPEAKING</option>
											<option value="TOEFL">TOEFL</option>
											<option value="OPIC">OPIC</option>
									</select> <%
 	}
 	else if (Page.equals("LicenseStudy.jsp")) {
 %> <input type="text" name="category" value="LICENSE"
										style="width: 18%" readonly /> <select name="category">
											<option value="">==자격증 종류==</option>
											<option value="컴퓨터활용자격증">컴퓨터활용자격증</option>
											<option value="한국사">한국사</option>
											<option value="GTQ">GTQ</option>
											<option value="MOS">MOS</option>
									</select> <%
 	} else {
 %> <select name="category">
											<option value="">===</option>
											<option value="TOEIC">TOEIC</option>
											<option value="ToeicSpeaking">TOEIC Speaking</option>
											<option value="TOEFL">TOEFL</option>
											<option value="OPIC">OPIC</option>
											<option value="">===</option>
											<option value="컴활">컴활</option>
											<option value="한국사">한국사</option>
											<option value="GTQ">GTQ</option>
											<option value="MOS">MOS</option>
									</select> <%
 	}
 %>
									</td>
									<td>제목 <input type="text" name="title" style="width: 90%" /></td>
								</tr>
								<tr>
									<td colspan="2" style="height: 10px">내용</td>
								</tr>
								<tr>
									<td colspan="2"><textarea name="content" cols="30"
											rows="10" style="width: 100%;"></textarea></td>
								</tr>
								<tr>
									<td colspan="2">URL &nbsp;<input type="text" name="url"
										style="width: 96%" /></td>
								</tr>
							</table>
							<input type="submit" value="작성" onclick=""
								style="margin-left: 180px" />
							<!-- return writeChk(); -->
							<input type="button" value="취소" onclick="history.back()" />
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