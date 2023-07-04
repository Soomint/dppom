package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class memberModifyActionCommand implements MemberCommand {

	MemberCommand command = null;

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();

		// model에 넣어온 req 꺼냄
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String pass = req.getParameter("Re_pass");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String univ = req.getParameter("univ");
		String major = req.getParameter("major");
		String id = req.getParameter("id");
		MemberDAO dao = new MemberDAO();

		dao.memberModify(pass, email1, email2, name, phone, univ, major, id);
		dao.close();
	}

}
