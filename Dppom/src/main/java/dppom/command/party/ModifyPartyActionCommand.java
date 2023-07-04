package dppom.command.party;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;

public class ModifyPartyActionCommand implements BoardCommand {

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
		String idx = req.getParameter("idx");
		int now_party = Integer.parseInt(req.getParameter("now_party"));
		int total_party = Integer.parseInt(req.getParameter("total_party"));

		BoardDAO dao = new BoardDAO();
		dao.modifyPartyAction(id, univ, major, board_type, category, title, content, idx, now_party, total_party);
		dao.close();
	}
}
