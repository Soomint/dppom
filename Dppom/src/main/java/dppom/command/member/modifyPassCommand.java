package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class modifyPassCommand implements MemberCommand {

	@Override
	public void execute(Model model) {

		System.out.println("modifyPassCommand 커맨드");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		String new_Pass = req.getParameter("new_Pass");
		String new_PassRe = req.getParameter("new_PassRe");
		String id = req.getParameter("id");

		MemberDAO dao = new MemberDAO();
		dao.modifyPass(new_Pass, new_PassRe, id);
		dao.close();

	}

}
