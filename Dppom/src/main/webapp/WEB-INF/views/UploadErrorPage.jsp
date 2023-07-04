<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   out.println("<sctipt>alert('업로드하신 파일의 용량이 200Mb를 초과하였습니다.');");
   out.println("history.back();</script>");
%>