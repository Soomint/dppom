package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;
import dppom.model.member.MemberDTO;

public class InfoConfirmDeleteCommand {
	public String execute(Model model) {
		// 파라미터받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.myInfo(req.getParameter("id"));
		dao.close();
		return dto.getCerti_url();
	}
}