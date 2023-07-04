<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="BoardModel.boardDAO"%>
<%
	//게시물의 일련번호 num, 현재페이지번호 nowPage
	String idx = request.getParameter("idx");
	String id = request.getParameter("id");
	int nowPage = Integer.parseInt(request.getParameter("nowPage"));

	//게시물 삭제를 위한 DAO객체생성
	boardDAO dao = new boardDAO();
	//DAO객체의 delete메소드를 호출한다.(게시물삭제)
	int affected = dao.delete(idx);

	//게시물 삭제후 전체게시물의 개수 얻기
	int totalRecord = dao.getTotalRecordCount(new HashMap(), id);
	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	//게시물 삭제후 페이지 수 계산하기
	int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
	//파라미터로 넘어온 페이지와 현재 페이지를 비교
	if (nowPage - 1 == totalPage) {
		nowPage--;
	}
	dao.close();//DB자원반납

	if (affected == 1) {
		response.sendRedirect("memberBoardInfo.jsp?id=" + id + "&nowPage=" + nowPage);
	} else {
%>
<script>
	alert("삭제실패");
	history.back();
</script>
<%
	}
%>