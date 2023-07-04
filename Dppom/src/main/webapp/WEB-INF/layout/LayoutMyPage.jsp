<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>

   <div class="row" id="team">
            <div class="col-md-12 section-heading text-center to-animate">
               <h2>MY PAGE</h2>
            </div>
            <div class="col-md-12">
               <div class="row ">
                  <div class="col-md-3 text-center to-animate">
                     <div class="person">
                        <a href="myHome?id=${sessionScope.login_id.id}&nowPage=1"><img src="./resources/bootstrap3.3.7/images/coins.png" class="img-responsive img-rounded" alt="Person"></a>
                        <a href="myHome?id=${sessionScope.login_id.id}&nowPage=1" style="text-decoration:none"><h3 class="name">포인트 보기</h3></a>
                        <div class="position">P O I N T</div>
                        <p></p>
                     </div>
                  </div>
                  <div class="col-md-3 text-center to-animate">
                     <div class="person">
                        <a href="myWritingList?id=${sessionScope.login_id.id}"><img src="./resources/bootstrap3.3.7/images/brochure_design.png" class="img-responsive img-rounded" alt="Person"></a>
                        <a href="myWritingList?id=${sessionScope.login_id.id}" style="text-decoration:none"><h3 class="name">내글 보기</h3></a>
                        <div class="position">M Y L I S T</div>
                        <p></p>
                     </div>
                  </div>
                  <div class="col-md-3 text-center to-animate">
                     <div class="person">
                        <a href="myModify?id=${sessionScope.login_id.id}"><img src="./resources/bootstrap3.3.7/images/gear.png" class="img-responsive img-rounded" alt="Person"></a>
                        <a href="myModify?id=${sessionScope.login_id.id}" style="text-decoration:none"><h3 class="name">정보 수정</h3></a>
                        <div class="position">I N F O _ E D I T</div>
                     </div>
                  </div>
					<div class="col-md-3 text-center to-animate">
					<div class="person">
						<a href="InfoConfirm?id=${sessionScope.login_id.id}"><img src="./resources/bootstrap3.3.7/images/id.png" class="img-responsive img-rounded" alt="Person"></a>
						<a href="InfoConfirm?id=${sessionScope.login_id.id}" style="text-decoration:none"><h3 class="name">정보 인증</h3></a>
						<div class="position">I N F O _ C O N F I R M</div>
					</div>
				</div>
               </div>
            </div>
         </div>