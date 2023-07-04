package dppom.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.board.BoardDAO;
import dppom.model.member.MemberDAO;
import dppom.model.member.PointDAO;

public class minusCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

		System.out.println("minusCommand 실행");

		// DAO연결
		BoardDAO dao = new BoardDAO();

		// 파라미터받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String id = (String) paramMap.get("id");

		int point = -10;

		System.out.println("여기서 point는? " + point);

		MemberDAO mDao = new MemberDAO();
		mDao.memberminusPoint(id, point);
		mDao.close();

		String detail = "'파일 다운로드'";

		PointDAO pDao = new PointDAO();

		pDao.pointMinus(point, id, detail);
		pDao.close();

	}

}