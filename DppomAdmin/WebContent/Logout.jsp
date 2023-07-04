<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//세션 해제 
		System.out.println("로그아웃 - 관리자" + session.getId());
		session.removeAttribute("login_id");
		System.out.println("로그아웃 - 관리자" + session.getId());
		// 사용자단 메인으로 이동 
		response.sendRedirect("/Dppom/Main");
	%>
</body>
</html>