package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class FindPassActionCommand {

	public void execute(Model model) {

		// implements 없는 ver

		System.out.println("FindPassActionCommand 커맨드");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		MemberDAO dao = new MemberDAO();
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String pass_question = req.getParameter("pass_question");
		String pass_answer = req.getParameter("pass_answer");

		String isUser = dao.findPass(id, email, pass_question, pass_answer);
		System.out.println(isUser);

		model.addAttribute("isUser", isUser);
		dao.close();
	}
}
