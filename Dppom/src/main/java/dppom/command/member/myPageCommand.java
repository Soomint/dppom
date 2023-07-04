package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;
import dppom.model.member.MemberDTO;

public class myPageCommand implements MemberCommand {

	// certi_flag, certi_url 가져오기
	@Override
	public void execute(Model model) {

		System.out.println("myPageCommand 정보 가져오기");
		// 파라미터 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		MemberDAO dao = new MemberDAO();
		String id = (String) paramMap.get("id");
		System.out.println("id" + id);

		MemberDTO dto = dao.myInfo(id);
		model.addAttribute("certi", dto);
		dao.close();
	}
}