package dppom.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.member.PointDAO;

public class plusCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

		System.out.println("minusCommand 실행");

		// DAO연결
		BoardDAO dao = new BoardDAO();

		// 파라미터받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String id = req.getParameter("id");

		int point = 1;

		System.out.println("여기서 point는? " + point);

		String detail = "'로그인 포인트'";

		PointDAO pDao = new PointDAO();

		pDao.pointPlus(point, id, detail);
		pDao.close();
	}
}