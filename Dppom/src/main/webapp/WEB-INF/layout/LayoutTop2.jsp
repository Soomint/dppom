<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="with=device-width, initial-scale=1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>

 <script>
 function loginCheck(id)
   {
      if(id == null)
      {
         alert("회원만 이용할 수 있는 기능입니다.");
         return false;
      }
   }
</script>
 <!--  TOP ( 메뉴 )  -->
<div class="container">
             <nav class="navbar navbar-default">
              <div class="navbar-header">
                 <!-- Mobile Toggle Menu Button -->
               
                   <a class="navbar-brand" href="Main" style="color:#6173f4">大  PPOM</a> 
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar">
                  <li><a href="SiteInfo?PageURL=SiteInfo" style="color:black" ><span>About</span></a></li>
                  <li><a href="Review?PageURL=Review" style="color:black"><span>Review</span></a></li>
                  <li><a href="DataList?PageURL=DataList" style="color:black"><span>DataRoom</span></a></li>
                  <li><a href="Party?PageURL=Party" style="color:black"><span>Party</span></a></li>
                  <li><a href="Study?PageURL=Study" style="color:black"  onclick="return loginCheck(${sessionScope.login_id.id});"><span>Study</span></a></li>
                  <li><a href="QnA?PageURL=QnA"style="color:black" ><span>QnA</span></a></li>
                </ul>
                
                
                  <ul class="nav navbar-nav navbar-right">
                 <c:choose>
                       <c:when test="${not empty sessionScope.login_id }">
                       <c:if test="${sessionScope.login_id.id!='wm' && sessionScope.login_id.id!='ms' && sessionScope.login_id.id!='jg'}">
                          <li>
                            <a href="myHome?id=${sessionScope.login_id.id}"><span class="glyphicon glyphicon-bell" style="color:black">${sessionScope.login_id.id}님, 반갑습니다!</span></a>
                          </li>
                          <li><a href="myHome?id=${sessionScope.login_id.id}" ><span style="color:black">My Page</span></a></li>  <!-- 회원일때만 -->
                          <li><a href="Logout" ><span style="color:black">Logout</span></a></li>
                       </c:if>
                       <c:if test="${sessionScope.login_id.id=='wm' || sessionScope.login_id.id=='ms' || sessionScope.login_id.id=='jg'}">
                          <li>
                            <a href="../DppomAdmin/member/MemberAdmin.jsp"><span class="glyphicon glyphicon-bell" style="color:black">관리자(${sessionScope.login_id.id})님, 반갑습니다!</span></a>
                          </li>
                          <li><a href="../DppomAdmin/member/MemberAdmin.jsp" ><span style="color:black">Admin Page</span></a></li> <!-- 관리자일때만 -->
                          <li><a href="Logout" ><span style="color:black">Logout</span></a></li>
                       </c:if>
                       </c:when>
                       <c:otherwise>
                          <li><a href="Login" ><span style="color:black">Login</span></a></li>
                         <li><a href="Join" ><span style="color:black">Join</span></a></li>
                       </c:otherwise>
                 </c:choose>
                   </ul>
                
                
                
               
              </div>
              
             </nav>
         
</div>
