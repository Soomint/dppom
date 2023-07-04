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
<title>Party - 게시글 작성</title>
<script>
	function checkWriteFrm() {

		var check = document.writeFrm;
		// 폼 객체 엘리먼트를 불러옴

		if (check.title.value == "") {
			alert("제목을 입력하세요.");
			check.title.focus();
			return false;
		}

		if (check.content.value == "") {
			alert("내용을 입력하세요.");
			check.content.focus();
			return false;
		}

		if (check.category.value == "선택") {
			alert("타입을 선택해주세요");
			check.category.focus();
			return false;
		}

		if (check.now_party.value == "") {
			alert("현재인원을 입력해주세요");
			check.now_party.focus();
			return false;
		}

		if (check.total_party.value == "") {
			alert("총 인원을 입력해주세요");
			check.total_party.focus();
			return false;
		}

		if (check.total_party.value < check.now_party.value) {
			alert("모집인원이 더 크도록 작성해 주세요");
			check.total_party.focus();
			return false;
		}

		if (check.total_party.value == "0") {
			alert("0이상을 입력해주세요");
			check.total_party.focus();
			return false;
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
					<h2>PARTY</h2>
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
						<form name="writeFrm" action="<c:url value="writePartyAction"/>"
							onsubmit="return checkWriteFrm();">
							<input type="hidden" name="board_type" value="party" /> <input
								type="hidden" name="id" value="${sessionScope.login_id.id }" />
							<table id="commu_table" class="table  table-bordered">
								<thead>
									<tr>
										<th colspan="3" style="width: 10%; text-align: center;">
											<span style="color: green">사람찾아요</span> 게시판 글쓰기
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th style="width: 10%;"><select class="" id="category"
											name="category"
											style="width: 80%; height: 40px; margin-left: 40px">
												<option>선택</option>
												<option value="팀프로젝트">팀프로젝트</option>
												<option value="스터디">스터디</option>
												<option value="etc">etc</option>
										</select></th>
										<th colspan="2"><input type="text" name="title"
											placeholder=" 제 목"
											style="width: 80%; height: 40px; margin-left: 40px" /></th>
									</tr>
									<tr>
										<td style="width: 20%;"><input type="text" name="univ"
											style="width: 80%; height: 40px; margin-left: 10px"
											value="${sessionScope.login_id.univ }" readonly /></td>
										<td style="width: 20%;"><input type="text" name="major"
											style="width: 80%; height: 40px; margin-left: 10px"
											value="${sessionScope.login_id.major }" readonly /></td>
										<td style="width: 30%;"><input name="now_party"
											id="now_party" type="text" placeholder="현재모인 인원" /> / <input
											name="total_party" id="total_party" type="text"
											placeholder="모집할 총 인원" /></td>
									</tr>
									<tr>
										<td colspan="3"><textarea name="content" id="" cols="50"
												rows="30" style="width: 100%"></textarea></td>
									</tr>
								</tbody>
								<%-- 	<%} //for문 끝%>
				<%}//else문 끝 %>	 --%>
							</table>

							<div class="col-md-12" style="text-align: center">
								<button type="submit" class="btn" style="margin-bottom: 5px;">
									쓰 기</button>
							</div>
							<!-- 테이블 E  -->
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