package dppom.command.DataRoom;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kosmo.Dppom.PagingUtil;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class SearchPTActionCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		System.out.println("서치액션커맨드 실행");
		// DAO연결
		BoardDAO dao = new BoardDAO();

		// 파라미터받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		System.out.println("선택한 컬럼명? " + paramMap.get("sel1"));

		// 검색 기능 구현
		String searchColumn = (String) paramMap.get("sel1");
		String searchWord = (String) paramMap.get("search");
		String filename = (String) paramMap.get("filename");

		// 검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수
		String addQueryString = "";

		// 검색기능
		if (searchColumn != null) {
			// 페이지수 계산을 위한 파라미터 생성
			addQueryString = String.format("searchColumn=%s&searchWord=%s&", searchColumn, searchWord);

			// map타입의 컬렉션에 아래 키에 해당하는 항목 추가
			paramMap.put("searchColumn", searchColumn);
			paramMap.put("searchWord", searchWord);
		}

		// 전체 레코드수를 카운트
		int totalRecordCount = dao.getTotalCount(paramMap);

		// 페이지 설정값
		int pageSize = 10;
		int blockPage = 5;

		// 전체 페이지 수
		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);

		// 현재 페이지를 파라미터로 받기
		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));

		// 시작 및 끝 rownum 구하기
		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;

		// DAO로 넘길 파라미터 조립
		paramMap.put("start", start);
		paramMap.put("end", end);
		paramMap.put("totalPage", totalPage);
		paramMap.put("nowPage", nowPage);
		paramMap.put("totalRecordCount", totalRecordCount);
		paramMap.put("pageSize", paramMap);

		// 페이지 처리를 위한 문자열 생성
		String addQueryStr = "";
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
				req.getContextPath() + "/" + filename + "?" + addQueryStr);

		// 게시판 타입
		String board_type = req.getParameter("board_type");
		String category = req.getParameter("category");

		paramMap.put("board_type", board_type);
		paramMap.put("category", category);

		// 리스트 목록 가져오기
		ArrayList<BoardDTO> listRows = dao.searchDPAction(paramMap);

		// 페이지 처리를 위한 부분
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("board_type", board_type);

		// 리스트 레코드를 저장
		model.addAttribute("listRows", listRows);
		dao.close();

		System.out.println("MajCommand > execute() 메소드 호출");
	}
}