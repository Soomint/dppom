package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class findIdCommand implements MemberCommand {

	@Override
	public void execute(Model model) {

		System.out.println("findIdCommand 커맨드");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		MemberDAO dao = new MemberDAO();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		System.out.println(name + " 커맨드 " + email);
		String searchId = dao.findId(name, email);
		model.addAttribute("searchId", searchId);
		dao.close();
	}
}
