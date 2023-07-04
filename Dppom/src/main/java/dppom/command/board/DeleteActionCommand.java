package dppom.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;

public class DeleteActionCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String idx = req.getParameter("idx");

		BoardDAO dao = new BoardDAO();
		dao.deleteView(idx);
		dao.close();
	}
}
