package dppom.command.party;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;
import dppom.model.member.MemberDAO;
import dppom.model.member.PointDAO;

public class WritingPartyActionCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		System.out.println("WritingPartyAction 커맨드 실행");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String id = req.getParameter("id");
		String univ = req.getParameter("univ");
		String major = req.getParameter("major");
		String board_type = req.getParameter("board_type");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String now_party = req.getParameter("now_party");
		String total_party = req.getParameter("total_party");

		System.out.println("커맨드에서 현재인원: " + now_party);

		BoardDAO dao = new BoardDAO();
		dao.writePartyAction(id, univ, major, board_type, category, title, content, now_party, total_party);
		dao.close();

		int point = 0;
		if (board_type.equals("dataroom")) {
			point = 15;
		} else {
			point = 10;
		}

		System.out.println("여기서 point는? " + point);

		MemberDAO mDao = new MemberDAO();
		mDao.memberPoint(id, point);
		mDao.close();

		String detail = null;

		PointDAO pDao = new PointDAO();
		if (point == 10) {
			detail = "'게시글 작성'";
		} else {
			detail = "'게시글 및 파일 업로드'";
		}
		pDao.pointInsert(point, id, detail);
		pDao.close();
	}
}