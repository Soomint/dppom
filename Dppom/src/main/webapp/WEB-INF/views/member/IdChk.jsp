<%@page import="dppom.model.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	System.out.println("id "+id);
	
	String isIDflag = request.getParameter("isIDflag");
	System.out.println("isIDflag "+isIDflag);
	
	MemberDAO dao = new MemberDAO();
	String idResult = dao.idIdchk(id);
	System.out.println("idResult "+idResult);
	String impossibleId = dao.impossibleId(id);
	System.out.println("impossibleId "+impossibleId);
	
	if (id.equals(impossibleId)) {
		out.println("<b style='font-size:1.2em; color:red'>&nbsp;&nbsp;&nbsp;사용 불가</b>");
		if (id.equals("탈퇴회원")) {
			out.println("<b style='font-size:1.2em; color:red'>&nbsp;&nbsp;&nbsp;사용 불가</b>");
		}
		isIDflag ="no";
	} else {
		if (id.equals(idResult)) {
			out.println("<b style='font-size:1.2em; color:red'>&nbsp;&nbsp;&nbsp;아이디 중복</b>");
			isIDflag ="no";  // data로 처리해서 결국 밖에서 value지정은 X 
		} else {
			out.println("<b style='font-size:1.2em; color:blue'>&nbsp;&nbsp;&nbsp;사용 가능</b>");
			isIDflag ="yes";
		}
	}
	dao.close();
%>