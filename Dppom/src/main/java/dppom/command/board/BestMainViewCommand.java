package dppom.command.board;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.board.BoardDTO;

public class BestMainViewCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		System.out.println("bestMainViewCommand 실행");
		BoardDAO dao = new BoardDAO();
		// 파라미터 전체를 받아오기
		Map<String, Object> map = model.asMap();
		List<BoardDTO> boardData = dao.bestList(map);

		model.addAttribute("view", boardData);

		dao.close();
	}
}