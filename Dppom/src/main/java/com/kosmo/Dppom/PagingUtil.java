package com.kosmo.Dppom;

public class PagingUtil 
{
	public static String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page)
	{
		String pagingStr = "";
		
		// 1. 전체페이지 구하기
		int totalPage = (int)(Math.ceil((double)totalRecordCount/pageSize));
		int intTemp = ((nowPage - 1) / blockPage) * blockPage + 1; // (blockPage*0 + 1, blockPage*1 + 1, blockPage*2 + 1, ...) 
		
		// 처음페이지 & 이전페이지를 위한 로직
		if(intTemp != 1)
		{
			pagingStr += "<a href='" + page + "nowPage=1'><img src='./resources/paging/paging1.gif'></a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + page + "nowPage=" + (intTemp - blockPage) + "'><img src='./resources/paging/paging2.gif'></a>";
		}
		
		// 페이지표시 제어를 위한 변수
		int blockCount = 1;
		
		// 페이지를 뿌려주는 로직 : 블럭페이지의 수만큼 혹은 마지막 페이지가 될때까지 페이지를 표시한다.
		while(blockCount <= blockPage && intTemp <= totalPage)
		{
			// 현재페이지인 경우
			if(intTemp == nowPage)
				pagingStr += "<span style='color:red;'>" + intTemp + "</span>";
			else
				pagingStr += "<a href='" + page + "nowPage=" + intTemp + "'>" + intTemp + "</a>";
			
			intTemp++;
			blockCount++;
		}
		
		// 다음 및 마지막 페이지를 위한 로직
		if(intTemp <= totalPage)
		{	
			// while문에서 계속 intTemp를 증가시키므로 (intTemp + blockPage)가 아니다.
			pagingStr += "<a href='" + page + "nowPage=" + intTemp + "'>" + "<img src='./resources/paging/paging3.gif'></a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+ page + "nowPage=" + totalPage + "'>" + "<img src='./resources/paging/paging4.gif'></a>";
		}
		return pagingStr;
	}
}
