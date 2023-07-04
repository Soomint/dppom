package dppom.command.DataRoom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;

public class ModifyDataActionCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

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
		String idx = req.getParameter("idx");

		BoardDAO dao = new BoardDAO();
		dao.modifyAction(id, univ, major, board_type, category, title, content, attachedfile, idx);
		dao.close();
	}

	public void execute(Model model, String realpath) {

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
		String idx = req.getParameter("idx");

		BoardDAO dao = new BoardDAO();
		dao.modifyAction(id, univ, major, board_type, category, title, content, attachedfile, idx);
		dao.close();
	}
}