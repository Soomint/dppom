package dppom.command.party;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kosmo.Dppom.PagingUtil;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class PartyListCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		System.out.println("PartyList 실행");
		// DAO연결
		BoardDAO dao = new BoardDAO();

		// 파라미터받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");
		String category = (String) paramMap.get("category");
		if (category == null) {
			category = "팀프로젝트";
		}

		System.out.println("여기서 카데코리는? : " + category);
		paramMap.put("category", category);

		String filename = (String) paramMap.get("filename");
		System.out.println("여기서 파일명은? : " + filename);

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
		paramMap.put("category", category);

		// 페이지 처리를 위한 문자열 생성
		String addQueryStr = "";
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
				req.getContextPath() + "/Party?" + addQueryStr + "category=" + req.getParameter("category") + "&");

		String board_type = (String) paramMap.get("board_type");
		System.out.println("보드타입 " + req.getParameter("board_type"));

		paramMap.put("board_type", board_type);

		// 리스트 목록 가져오기
		ArrayList<BoardDTO> listRows = dao.allList(paramMap);

		// 페이지 처리를 위한 부분
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);

		// 리스트 레코드를 저장
		model.addAttribute("listRows", listRows);
		model.addAttribute("category", category);
		model.addAttribute("filename", filename);
		dao.close();

		System.out.println("MajCommand > execute() 메소드 호출");
	}
}
