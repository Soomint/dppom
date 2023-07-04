<%@page import="MemberModel.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");

	memberDAO dao = new memberDAO();
	
	dao.approval(id);	
	
	dao.close();
%>
<script>
	location.href='MemberAdmin.jsp?';
</script>