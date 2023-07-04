package dppom.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.member.MemberDAO;
import dppom.model.member.PointDAO;

public class WriteActionCommand {

	public void execute(Model model) {
		System.out.println("WriteActionCommand1 실행");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String id = req.getParameter("id");
		String univ = req.getParameter("univ");
		String major = req.getParameter("major");
		String board_type = req.getParameter("board_type");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String attachedfile = req.getParameter("attachedfile");

		BoardDAO dao = new BoardDAO();
		dao.writeAction(id, univ, major, board_type, category, title, content, attachedfile);
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

	public void execute(Model model, String realpath) {

		System.out.println("WriteActionCommand2 실행");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String id = req.getParameter("id");
		String univ = req.getParameter("univ");
		String major = req.getParameter("major");
		String board_type = req.getParameter("board_type");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String attachedfile = realpath;

		BoardDAO dao = new BoardDAO();
		dao.writeAction(id, univ, major, board_type, category, title, content, attachedfile);

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