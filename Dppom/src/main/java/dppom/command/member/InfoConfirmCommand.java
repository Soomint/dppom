package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class InfoConfirmCommand {
	public void execute(Model model, String fileName) {

		Map<String, Object> map = model.asMap();

		// model에 넣어온 req 꺼냄
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String id = req.getParameter("id");
		MemberDAO dao = new MemberDAO();

		dao.confirmModify(fileName, id);
		dao.close();
	}

}