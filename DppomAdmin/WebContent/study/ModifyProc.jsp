<%@page import="StudyModel.studyDAO"%>
<%@page import="StudyModel.studyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	System.out.println("modifyproc진입");
	String Page = request.getParameter("Page");

	studyDTO dto = new studyDTO();

	String category = request.getParameter("category");
	System.out.println("category" + category);
	String content = request.getParameter("content");
	String study_type = request.getParameter("study_type");
	String title = request.getParameter("title");
	String url = request.getParameter("url");
	int idx = Integer.parseInt(request.getParameter("idx"));

	dto.setCategory(category);
	dto.setContent(content);
	dto.setStudy_type(study_type);
	dto.setTitle(title);
	dto.setUrl(url);
	dto.setIdx(idx);

	studyDAO dao = new studyDAO();
	int affected = dao.modify(dto);

	dao.close();

	if (affected == 1) {
		System.out.println(Page);
		response.sendRedirect(Page);
	} else {
%>
<script>
	alert("입력실패");
	history.go(-1);
</script>
<%
	}
%>

