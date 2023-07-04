package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class JoinSuccessCommand implements MemberCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		// 폼값 모두 받아오기
		String id = req.getParameter("id");

		String name = req.getParameter("name");

		String pass = req.getParameter("pass");
		String Re_pass = req.getParameter("Re_pass");

		String pass_question = req.getParameter("pass_question");
		String pass_answer = req.getParameter("pass_answer");

		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String email = email1 + "@" + email2;

		String phone1 = req.getParameter("phone1");
		String phone2 = req.getParameter("phone2");
		String phone3 = req.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;

		String univ = req.getParameter("univ");
		String major = req.getParameter("major");

		System.out.println(id + " " + name + " " + pass + " " + Re_pass + " " + pass_question + " " + pass_answer + " "
				+ email + " " + phone + " " + univ + " " + major);

		model.addAttribute("joinMsg", "대뽐의 회원이 된것을 환영합니다 !");

		MemberDAO dao = new MemberDAO();
		dao.joinMember(id, Re_pass, name, email, phone, univ, major, pass_question, pass_answer);
		dao.close();

	}

}
