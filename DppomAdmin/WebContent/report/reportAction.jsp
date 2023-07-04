<%@page import="ReportModel.ReportDAO"%>
<%@page import="BoardModel.boardDAO"%>
<%@page import="MemberModel.pointDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 포인트 처리 board_id , 리포트 내역 삭제 idx
	String id = request.getParameter("id");
	String board_idx = request.getParameter("board_idx"); 
	String idx = request.getParameter("idx");
	String type = request.getParameter("type"); // report , fakereport 
	String what = request.getParameter("what"); //board , reply
	
	System.out.println(id+ " " +board_idx + " report: "+idx+" "+type+" "+what);
	
	//신고 처리 (board_id)  type: report 또는 fakereport
	pointDAO pointdao = new pointDAO();
	pointdao.minusPoint(id, type, board_idx, what);

	// 신고 내역 삭제 (idx)
	ReportDAO reportdao = new ReportDAO();
	reportdao.delete(idx);
	
	pointdao.close();
	reportdao.close();
	response.sendRedirect("reportadmin.jsp");
%>

