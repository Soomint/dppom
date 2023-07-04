package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;
import dppom.model.member.MemberDTO;

public class memberInfoCommand implements MemberCommand {

	// 회원정보 수정을 위한 회원정보 select
	@Override
	public void execute(Model model) {

		System.out.println("memberInfoCommand 회원수정 execute");
		// 파라미터 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		MemberDAO dao = new MemberDAO();
		String id = req.getParameter("id");
		System.out.println("id" + id);

		MemberDTO dto = dao.myInfo(id);
		String email = dto.getEmail();
		model.addAttribute("email", email);
		model.addAttribute("user_Info", dto);
		dao.close();

	}

}
