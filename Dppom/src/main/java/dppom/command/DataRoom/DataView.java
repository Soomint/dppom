package dppom.command.DataRoom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class DataView implements BoardCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String idx = req.getParameter("idx");
		String board_type = req.getParameter("board_type");

		BoardDAO dao = new BoardDAO();

		BoardDTO dto = dao.RView(idx);

		// 상세보기 줄바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));

		model.addAttribute("viewRow", dto);
		dao.close();
	}
}
