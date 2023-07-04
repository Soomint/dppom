<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/layout/head.jsp" %>
<style>
.navbar-brand{
 color:#6173f4;
}

</style>
<title>Review - 게시글 작성</title>
<script>
	function checkWriteFrm(){
	   
	   var check = document.writeFrm;
	   // 폼 객체 엘리먼트를 불러옴
	   
	   if(check.title.value==""){
	      alert("제목을 입력하세요.");
	      check.title.focus();
	      return false;
	   }
	   
	   if(check.content.value==""){
	      alert("내용을 입력하세요.");
	      check.content.focus();
	      return false;
	   }
	   
	   if(check.category.value=="선택")
	   {
		   alert("종류를 선택해 주세요");
		   check.category.focus();
		   return false;
	   }
	}
</script>
</head>

<body>
	<header role="banner" id="fh5co-header"> <!--  TOP ( 메뉴 )  -->
    <%@ include file="/WEB-INF/layout/LayoutTop_black.jsp"%>
    <%@ include file="/WEB-INF/layout/LayoutTop1_black.jsp"%>
	<!-- TOP (E) -->
</header>

<div id="fh5co-about-us" data-section="about">
	<div class="container">
		<div class="row" id="team">
			<div class="col-md-12 section-heading text-center to-animate">
				<h2>Review</h2>
				
			</div>
		</div>
			<div class="row row-bottom-padded-lg" id="about-us" >

				
			<!-- 	<div class="col-md-12 section-heading text-center to-animate" >
					<div class="row" >
						<div class="col-md-12" style="text-align:right">
							<h3> <a href="Review" style="text-decoration:none; color:darkgrey;">전 공 Review</a> </h3>
						</div>
					</div>
				</div> -->
				
				<div class="col-md-12 to-animate"  >
					<div class="text-center">
					<div class="row" >
						<div class="col-md-12" style="text-align:right">
							<button type="button" class="btn" onclick="history.back()" style="margin-bottom:5px;">Back</button>
						</div>
						
					</div>
					
						<!-- 테이블 S -->	
						<form name="writeFrm" action="<c:url value="writeAction"/>" onsubmit="return checkWriteFrm();">
							<input type="hidden" name="board_type" value="review" />
							<input type="hidden" name="id"  value="${sessionScope.login_id.id }" />
						<table id="commu_table" class="table  table-bordered" >
						<thead>
							 <tr >
								<th colspan="3" style="width:10%; text-align:center;">
								<span style="color:green">후기</span> 게시판 글쓰기</th>
							</tr> 
							
						</thead>
						<tbody>
							<tr>
								<th  style="width:10%;">
									<select class="" id="category" name="category" style="width:80%; height:40px;margin-left:40px">
									    <option>선택</option>
									    <option value="전공">전공</option>
									    <option value="교양">교양</option>
			  						</select>
								</th>
								<th colspan="2">
								<input type="text" name="title" placeholder=" 제 목" style="width:80%; height:40px; margin-left:40px" />
								</th>
							</tr>
							<tr>
								<td style="width:20%;"><input type="text" name="univ"  style="width:80%; height:40px; margin-left:10px" value="${sessionScope.login_id.univ }" readonly/></td>
								<td style="width:20%;"><input type="text" name="major"  style="width:80%; height:40px; margin-left:10px" value="${sessionScope.login_id.major }" readonly /></td>
								<td style="width:30%;" >
									<span>평점 ( 1~10 )  <input type="range" name="attachedfile"style="width:90%; margin-left:20px" value="5" min="1" max="10"/></span>
								</td>

							</tr>
							<tr>
								<td colspan="3">
								<textarea name="content" id="" cols="50" rows="30" style="width:100%"></textarea>
								</td>
							</tr>

						</tbody>	
					<%-- 	<%} //for문 끝%>
					<%}//else문 끝 %>	 --%>
					</table>
						<div class="col-md-12" style="text-align:center">
							<button type="submit" class="btn" style="margin-bottom:5px;">쓰 기</button>
						</div>
					<!-- 테이블 E  -->	
					</form>
				</div>
			</div> 
			
		
		</div>
	</div>
</div>
<!-- Botton (S) -->
	<%@ include file="/WEB-INF/layout/LayoutBottom.jsp" %>
	<!--  Bottom(E) -->
</body>
</html>