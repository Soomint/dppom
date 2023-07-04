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
<title>大PPOM - LibReview</title>
<script>
   function checkserchFrm(){
      var check = document.searchFrm;
      // 폼 객체 엘리먼트를 불러옴
      
      if(check.sel1.value=="선택"){
         alert("선택해주세요.");
         check.sel1.focus();
         return false;
      }
      
      if(check.search.value==""){
         alert("검색할 내용을 입력하세요.");
         check.search.focus();
         return false;
      }
   }
   
   function checkFrm1(){
        alert("로그인을 해주세요.");
        location.href='Login?';
        return false;
  }
  function checkFrm2(){
         alert("권한이 없습니다. 학생인증을 해주세요.");
         return false;
      }
     
  function checkWriteFrm1(){
        alert("로그인을 해주세요");
        location.href='Login?';
        return false;
  }
  function checkWriteFrm2(){
         alert("권한이 없습니다. 학생인증을 해주세요.");
         return false;
      }
</script>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
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
					<h2>REVIEW</h2>
				</div>
			</div>
			<div class="row row-bottom-padded-lg" id="about-us">

				<div class="col-md-12 section-heading text-center to-animate">
					<div class="row">
						<div class="col-md-6">
							<h3>
								<a id="" href="Review?PageURL=Review"
									style="text-decoration: none; color: #6c727a;"> <img
									src="./resources/bootstrap3.3.7/images/전0.PNG" alt="" /></a>
							</h3>
						</div>
						<div class="col-md-6">
							<h3>
								<a id="" href="LibReview?PageURL=LibReview"
									style="text-decoration: none; color: #6c727a;"> <b><img
										src="./resources/bootstrap3.3.7/images/교양0_클릭.PNG" alt="" /></b></a>
							</h3>
						</div>
					</div>
				</div>
				<div class="col-md-12 to-animate">
					<div class="text-center">
						<div class="row">
							<div class="col-md-12" style="text-align: right">
								<c:if test="${sessionScope.login_id.id eq null }">
									<form action="ReviewWrite" onsubmit="return checkWriteFrm1();">
										<button type="submit" class="btn" style="margin-bottom: 5px;">Write</button>
									</form>
								</c:if>
								<c:if test="${sessionScope.login_id.certi_flag eq 'X'}">
									<form action="ReviewWrite" onsubmit="return checkWriteFrm2();">
										<button type="submit" class="btn" style="margin-bottom: 5px;">Write</button>
									</form>
								</c:if>
								<c:if test="${sessionScope.login_id.certi_flag eq 'O'}">
									<form action="ReviewWrite">
										<button type="submit" class="btn" style="margin-bottom: 5px;">Write</button>
									</form>
								</c:if>
							</div>
						</div>
						<!-- 테이블 S -->
						<table id="commu_table" class="table table-hover table-bordered">
							<thead>
								<tr>
									<th style="width: 10%; text-align: center;">No</th>
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
											<td colspan="6" style="height: 100px;">등록된 글이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${listRows}" var="row" varStatus="loop">
											<!-- 글이 있는경우 노출 -->
											<tr>
												<td style="text-align: center;">${row.rNum }</td>
												<c:if test="${sessionScope.login_id.id eq null }">
													<td><a href="" onclick="return checkFrm1();">${row.title}</a></td>
												</c:if>
												<c:if test="${sessionScope.login_id.certi_flag eq 'X' }">
													<td><a href="" onclick="return checkFrm2();">${row.title}</a></td>
												</c:if>
												<c:if test="${sessionScope.login_id.certi_flag eq 'O' }">
													<td><a
														href="./ReviewView?idx=${row.idx }&nowPage=${nowPage}&category=${row.category}&filename=${filename}"
														style="color: Black">${row.title}</a></td>
												</c:if>
												<c:if test="${row.id == null }">
													<td style="text-align: center;">탈퇴회원</td>
												</c:if>
												<c:if test="${row.id != null }">
													<td style="text-align: center;">${row.id }</td>
												</c:if>
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
						<!-- 검색 S -->
						<form name="searchFrm" action="<c:url value="searchAction"/>"
							onsubmit="return checkserchFrm();">
							<div class="row">
								<div class="col-md-2">
									<select name="sel1" class="form-control" id="sel1">
										<option>선택</option>
										<option value="title">제목</option>
										<option value="id">ID</option>
										<option value="univ">학교</option>
										<option value="major">학과</option>
									</select>
								</div>
								<div class="col-md-6 input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-search"></i></span> <input name="search"
										style="width: 70%" id="email" type="text" class="form-control"
										name="search"> <input type="hidden" name="category"
										value="${category }" /> <input type="hidden"
										name="board_type" value="${board_type }" /> <input
										type="hidden" name="filename" value="LibReview" />
									<button type="submit" class="btn btn-default"
										style="margin-left: 0px" onclick="">Search</button>
								</div>
							</div>
						</form>
						<!-- 검색 E -->
						<br />
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