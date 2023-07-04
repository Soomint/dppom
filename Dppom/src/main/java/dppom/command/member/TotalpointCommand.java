package dppom.command.member;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kosmo.Dppom.PagingUtil;

import dppom.model.member.MemberDAO;
import dppom.model.member.PointDAO;
import dppom.model.member.PointDTO;

public class TotalpointCommand implements MemberCommand {

	MemberCommand command = null;

	@Override
	public void execute(Model model) {

		System.out.println("TotalpointCommand커맨드 execute()");

		// DAO 연결
		PointDAO dao = new PointDAO();

		// 파라미터 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req"); // 모델로부터
																			// 전달
		String id = req.getParameter("id"); // 쿼리스트링 으로부터 전달 => session 아이디값 받아랏

		// 전체 레코드수를 카운트
		paramMap.put("id", id);
		paramMap.put("used_type", req.getParameter("used_type")); // 전체인 경우 null

		int totalRecordCount = dao.getTotalCount(paramMap);

		System.out.println("totalRecordCount:" + totalRecordCount);

		// 페이지 설정값

		int pageSize = 5;
		int blockPage = 4;

		// 전체 페이지수
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
		paramMap.put("totalCount", totalRecordCount);
		paramMap.put("pageSize", pageSize);

		// 페이지 처리를 위한 문자열 생성 --수정 ~
		String addQueryStr = "id=" + id + "&"; // id는무조건
		String used_type = req.getParameter("used_type");

		System.out.println("커맨드 used_type" + paramMap.get("used_type"));

		String pagingImg = "";

		// 커맨드에서 map에다 넣기 -> 후에 DAO에서 뻬서 사용( ) (used_type)

		if (paramMap.get("used_type") == null) { // 전체 일 경우
			pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
					req.getContextPath() + "/myHome?" + addQueryStr);
		} else {
			addQueryStr += "used_type=" + used_type + "&";
			pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
					req.getContextPath() + "/mypointPlus?" + addQueryStr);
		}

		// 페이지 처리
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);

		/* 포인트 총합 */
		int totalpoint = dao.TotalPoint(id);
		System.out.println(totalpoint);
		model.addAttribute("totalpoint", totalpoint);

		/* 멤버의 point 에 set */
		MemberDAO memdao = new MemberDAO();
		memdao.myPoint(id, totalpoint);

		if (used_type == null) {
			ArrayList<PointDTO> pointlist = dao.MypointList(paramMap, id); // 전체
																			// select
			model.addAttribute("pointlist", pointlist);
		} else {
			ArrayList<PointDTO> mySearchPoint = dao.mySearchPoint(paramMap, used_type, id); // 적립/또는
																							// 차감
			model.addAttribute("mySearchPoint", mySearchPoint);
		}

		dao.close();

	}

}
