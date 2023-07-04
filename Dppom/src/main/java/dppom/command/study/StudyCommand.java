package dppom.command.study;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.study.StudyDAO;
import dppom.model.study.StudyDTO;

public class StudyCommand {
	public void execute(Model model) {
		StudyDAO dao = new StudyDAO();
		// 파라미터 전체를 받아오기
		Map<String, Object> map = model.asMap();

		HttpServletRequest req = (HttpServletRequest) map.get("req");
		map.put("searchColumn", req.getParameter("searchColumn"));
		map.put("searchWord", req.getParameter("searchWord"));
		int totalRecordCount = dao.getTotalCount(map);

		int pageSize = 6;
		int blockPage = 5;

		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);

		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));

		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;

		String searchColumn = null;
		String searchWord = null;
		if (map.get("searchWord") != null) {
			searchColumn = map.get("searchColumn").toString();
			searchWord = map.get("searchWord").toString();
		}

		map.put("start", start);
		map.put("end", end);
		map.put("totalPage", totalPage);
		map.put("nowPage", nowPage);
		map.put("totalCount", totalRecordCount);
		map.put("pageSize", pageSize);

		String addQueryStr = "";

		String pagingImg = "";
		if (searchWord != null) {
			// 페이지 계산을 위한 파라미터 생성
			addQueryStr = String.format("searchColumn=%s&searchWord=%s&", searchColumn, searchWord);

			// map타입의 컬렉션에 아래 키에 해당하는 항목 추가
			map.put("searchColumn", searchColumn);
			map.put("searchWord", searchWord);
		}
		pagingImg = com.kosmo.Dppom.PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
				req.getContextPath() + "/Study?" + addQueryStr);
		int virtualNum = 0; // 리스트 가상번호
		int countNum = 0; // 카운트하기 위한 변수

		List<StudyDTO> studyData = dao.selectAll(map);

		for (StudyDTO row : studyData) {
			virtualNum = totalRecordCount - (((nowPage - 1) * pageSize) + countNum++);
			row.setrNum(virtualNum);
		}

		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("searchColumn", searchColumn);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("studyData", studyData);
	}
}
