package dppom.command.DataRoom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class DataDeleteCommand {
	public String execute(Model model) {
		// 파라미터받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.RView(req.getParameter("idx"));
		dao.close();
		return dto.getAttachedfile();
	}
}