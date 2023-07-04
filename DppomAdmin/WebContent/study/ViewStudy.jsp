<%@page import="StudyModel.studyDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="StudyModel.studyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	//파라미터 
	int idx = Integer.parseInt(request.getParameter("idx"));
	System.out.println("idx" + idx);
	String Page = request.getParameter("Page");

	studyDAO dao = new studyDAO();
	studyDTO dto = dao.selectOne(idx);

	dao.close();
%>
<body>
	<h2>리뷰 열람</h2>
	<a href="${param.Page }">리스트 돌아가기</a>
	<form name="ViewStudyFrm" action="./ModifyProc.jsp">
		<input type="hidden" name=Page value=${param.Page } />
		<table border="1px  black" style="width: 50%">
			<tr>
				<td style="width: 10%">
					 <span>ENGLISH</span>
				</td>
				<td style="width: 10%">
					<%=dto.getCategory()%>
				</td>
				<td style="width: 50%">제목 : <%=dto.getTitle()%></td>
			</tr>
			<tr>
				<td colspan="3">내용</td>
			</tr>
			<tr>
				<td colspan="3"><textarea name="content" cols="30" rows="10"
						style="width: 100%"><%=dto.getContent()%></textarea></td>
			</tr>
			<tr>
				<td colspan="2">URL : <%=dto.getUrl()%></td>
			</tr>
		</table>
		<input type="submit" value="수정" onclick="" style="margin-left: 180px" />
		<input type="button" value="취소" onclick="history.back()" /> <input
			type="hidden" name="idx" value=<%=idx%> />
	</form>
</body>
</html>