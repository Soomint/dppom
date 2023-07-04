package dppom.command.party;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.command.board.BoardCommand;
import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class ModifyPartyCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		System.out.println("ModifyPartyCommand접속");
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String idx = req.getParameter("idx");
		System.out.println("idx는? " + idx);
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.RView(idx);

		model.addAttribute("viewRow", dto);
		dao.close();

	}
}
