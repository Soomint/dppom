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
.col-xs-1 {
    width: 8.4%;
}
#margi{margin-left:5px;}
#margi1{margin-left:-5px;}
#margi2{margin-left:-26px;}
#margi3{margin-left:-23px;}
/* button.ytp-large-play-button.ytp-button.ytp-red2 */
</style>
<title>大PPOM - Study</title>
<link rel="shortcut icon" href="./resources/bootstrap3.3.7/images/D.ico" />
<script src="./resources/jquery/jquery-3.2.1.js"></script>
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
	   location.href='Login?PageURL=Study';
   }

</script>
</head>

<body>
<header role="banner" id="fh5co-header">
   
   <!--  TOP ( 메뉴 )  -->
     <%@ include file="/WEB-INF/layout/LayoutTop_black.jsp"%>
    <%@ include file="/WEB-INF/layout/LayoutTop1_black.jsp"%>
   </header>
   <div id="fh5co-about-us" data-section="about">
      <div class="container">
         <div class="row" id="team">
            <div class="col-md-12 section-heading text-center to-animate">
               <h2>Study</h2>
            </div>
         </div>
         <div class="row row-bottom-padded-lg" id="about-us">
            <div class="col-md-12 to-animate">
               <div class="row" id="margi">
                  <div class="col-xs-1" id="margi1">
                     <form action="Study">
                        <button class="btn btn-danger">ALL</button>
                     </form>
                  </div>
                  <div class="col-xs-1" id="margi2">
                     <form action="StudySelect">
                        <input type="hidden" name="study_type" value="ENGLISH"/>
                        <button class="btn btn-info">어학</button>
                     </form>
                  </div>
                  <div class="col-xs-1" id="margi3">
                     <form action="StudySelect">
                        <input type="hidden" name="study_type" value="LICENSE"/>
                        <button class="btn btn-success">자격증</button>
                     </form>
                  </div>
               </div>
               <div class="text-center">
                  <!-- 테이블 S -->
                  <table id="commu_table" class="table table-hover table-bordered">
                     <thead>
                     </thead>
                     <tbody>
                        <c:if test="${empty studyData}">
                           <tr>
                              <td colspan="5">등록된 글이 없습니다.</td>
                           </tr>
                        </c:if>
                        <c:forEach items="${studyData}" var="study" varStatus="loop">
                           <div class="col-sm-4">
                              <div class="thumbnail" style="background-color: aliceblue; text-align: center;">
                                 <iframe name="test" width="340" height="300" src="${study.url}" frameborder="0" allowfullscreen></iframe> 
                                 <span class="caption" style="text-align: center; text-decoration:none;">
                                    <small>${study.content}</small><br />${study.title}
                                 </span>
                              </div>
                           </div>
                        </c:forEach>
                     </tbody>
                     <%--    <%} //for문 끝%>
               <%}//else문 끝 %>    --%>
                  </table>
                  <!-- 테이블 E  -->
                  <br />
                  <table width="100%">
                     <tr>
                        <td style="text-align: center;">${pagingImg}</td>
                     </tr>
                  </table>
                  <form action="StudySearch" method="get">
                     <input type="hidden" name="nowPage" value="1" />
                     <table width="100%">
                        <tr>
                           <td style="text-align:center;">
                              <select name="searchColumn" style="height:34px;">
                                 <c:if test="${empty study_type}">
                                    <option value="TOEIC">TOEIC</option>
                                    <option value="TOEICSPEAKING">TOEIC SPEAKING</option>
                                    <option value="TOEFL">TOEFL</option>
                                    <option value="OPIC">OPIC</option>
                                    <option value="컴퓨터활용자격증">컴퓨터활용자격증</option>
                                    <option value="한국사">한국사</option>
                                    <option value="GTQ">GTQ</option>
                                    <option value="MOS">MOS</option>
                                 </c:if>
                                 <c:if test="${study_type eq 'ENGLISH'}">
                                    <option value="TOEIC">TOEIC</option>
                                    <option value="TOEICSPEAKING">TOEIC SPEAKING</option>
                                    <option value="TOEFL">TOEFL</option>
                                    <option value="OPIC">OPIC</option>
                                 </c:if>
                                 <c:if test="${study_type eq 'LICENSE'}">
                                    <option value="컴퓨터활용자격증">컴퓨터활용자격증</option>
                                    <option value="한국사">한국사</option>
                                    <option value="GTQ">GTQ</option>
                                    <option value="MOS">MOS</option>
                                 </c:if>
                              </select> 
                              <input type="text" name="searchWord" value="${study.category}"/> 
                              <input type="submit" value="제목 검색하기" />
                           </td>
                        </tr>
                     </table>
                     <c:if test="${!empty study_type}">
                        <input type="hidden" name="study_type" value="${study_type}" />
                     </c:if>
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