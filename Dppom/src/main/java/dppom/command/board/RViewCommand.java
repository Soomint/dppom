package dppom.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class RViewCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String idx = req.getParameter("idx");
		BoardDAO dao = new BoardDAO();

		BoardDTO dto = dao.RView(idx);

		// 상세보기 줄바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));

		model.addAttribute("viewRow", dto);
		dao.close();
	}
}
