<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>


<%
 String PageURL = request.getParameter("PageURL");
System.out.println("사람구해요 " + PageURL);
%>
               
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
         						<li><a href="Login?PageURL=<%=PageURL%>"><span style="color: black">Login</span></a></li>
         						<li><a href="Join"><span style="color: black">Join</span></a></li>
      						</c:otherwise>
                 </c:choose>
                   </ul>
              </div>
              
             </nav>
         
 </div>
 
 <!--  TOP (E)  -->