package MemberModel;

public class PagingUtil {
	public static String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page){
		String pagingStr = "";
		
		//1.전체페이지 구하기
		int totalPage = (int)(Math.ceil(((double)totalRecordCount/pageSize)));
		int intTemp = ((nowPage-1)/ blockPage) * blockPage + 1;
		
		//처음페이지 & 이전페이지를 위한 로직
		if(intTemp != 1){
			pagingStr += "<a href='" +page+ "nowPage=1'><img src='../images/paging1.gif'></a>";
			pagingStr += "&nbsp;";	 
			pagingStr += "<a href ='" +page+ "nowPage"	+ (intTemp - blockPage) +"'><img src =' ../images/paging2.gif'></a>";
		}
		
		//페이지표시 제어를 위한 변수
		int blockCount	= 1;
		
		//페이지를 뿌려주는 로직 : 블럭페이지 수만큼 혹은 마지막 페이지가 될때까지 페이지를 표시한다.
		while(blockCount<=blockPage && intTemp<=totalPage){
			//현재페이지인 경우
			if(intTemp == nowPage){
				pagingStr += "<span style ='color:red'>" +intTemp+"</span>";
			}
			else{
				pagingStr += "<a href='" +page+ "nowPage="+intTemp+"'>" +intTemp+"</a>";
			}
			
			intTemp = intTemp +1;
			blockCount = blockCount +1;
		}
		
		//다음 및 마지막 페이지를 위한 로직
		if(intTemp <= totalPage){
			pagingStr += "<a href='" +page+ "nowPage="+intTemp+"'><img src='../images/paging3.gif'></a>";
			pagingStr +="&nbsp;";
			pagingStr +="<a href='"+page+"nowPage="+totalPage+"'><img src='../images/paging4.gif'></a>";
		}
		
		return pagingStr;
	}
	
}
