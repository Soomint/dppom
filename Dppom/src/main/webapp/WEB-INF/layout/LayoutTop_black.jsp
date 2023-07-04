<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<script>
   //비회원 접근 제한
   function loginCheck(PageURL)
   {
      alert("로그인을 해주세요.");
      location.href='Login?PageURL='+PageURL;
       return false;
   }
   function cut()
   {
      alert('회원님의 포인트가 0포인트 이하이므로 접근할 수 없습니다.');
      return false;
   }
   function cut2()
   {
      alert("권한이 없습니다. 학생인증을 해주세요.");
      return false;
   }
</script>
     
<meta name="viewport" content="with=device-width, initial-scale=1" />
 
 <!--  TOP ( 메뉴 )  -->
<div class="container">
             <nav class="navbar navbar-default">
              <div class="navbar-header">
                 <!-- Mobile Toggle Menu Button -->
               <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
                   <a class="navbar-brand" href="Main" style="color:#6173f4">大  PPOM</a> 
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar">
                  <li><a href="SiteInfo?PageURL=SiteInfo"  ><span style="color:black">About</span></a></li>
                  <c:if test="${sessionScope.login_id.id eq null}">
                  <li><a href="Review?PageURL=Review" ><span style="color:black">Review</span></a></li>
                  <li><a href="DataList?PageURL=DataList" ><span style="color:black">DataRoom</span></a></li>
                  <li><a href="Party?PageURL=Party" ><span style="color:black">Party</span></a></li>
                  <li><a href="Study?PageURL=Study" onclick="return loginCheck('Study');"><span style="color:black">Study</span></a></li>
                  </c:if>
                  <c:if test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'X' && sessionScope.login_id.point ne null}">
                  <li><a href="Review?PageURL=Review" ><span style="color:black">Review</span></a></li>
                  <li><a href="DataList?PageURL=DataList" ><span style="color:black">DataRoom</span></a></li>
                  <li><a href="Party?PageURL=Party" ><span style="color:black">Party</span></a></li>
                  <li><a href="Study?PageURL=Study" onclick="return cut2();"><span style="color:black">Study</span></a></li>
                  </c:if>
                  <c:if test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'O' && sessionScope.login_id.point lt 1}">
                  <li><a href="" onclick="return cut();"><span style="color:black">Review</span></a></li>
                  <li><a href="" onclick="return cut();"><span style="color:black">DataRoom</span></a></li>
                  <li><a href="" onclick="return cut();"><span style="color:black">Party</span></a></li>
                  <li><a href="" onclick="return cut();"><span style="color:black">Study</span></a></li>
                  </c:if>
                  <c:if test="${sessionScope.login_id.id ne null && sessionScope.login_id.certi_flag eq 'O' && sessionScope.login_id.point gt 0}">
                  <li><a href="Review?PageURL=Review" ><span style="color:black">Review</span ></a></li>
                  <li><a href="DataList?PageURL=DataList" ><span style="color:black">DataRoom</span></a></li>
                  <li><a href="Party?PageURL=Party" ><span style="color:black">Party</span></a></li>
                  <li><a href="Study?PageURL=Study" ><span style="color:black">Study</span></a></li>
                  </c:if>
                  <li><a href="QnA?PageURL=QnA" ><span style="color:black">QnA</span></a></li>
                </ul>

       
 
 <!--  TOP (E)  -->