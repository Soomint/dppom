package dppom.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.good.GoodDAO;

public class likeupCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		System.out.println("likeupCommand접속");
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String id = req.getParameter("id");
		String idx = req.getParameter("idx");
		System.out.println("id는? : " + id + " idx는? " + idx);

		GoodDAO gdao = new GoodDAO();
		BoardDAO dao = new BoardDAO();
		int check = gdao.check(id, idx);

		System.out.println("check는 몇개인가? : " + check);

		if (check != 0) {
			// 추천수 -1
			dao.likedown(idx);

			// 추천한 idx와 id 체거
			gdao.likedelete(idx, id);
		} else {
			// 추천수 +1
			dao.likeup(idx);

			// 추천한 idx와 id 추가
			gdao.like(idx, id);
		}

		gdao.close();
		dao.close();
	}
}