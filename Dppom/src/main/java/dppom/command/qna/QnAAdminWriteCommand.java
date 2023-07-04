package dppom.command.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.qna.QnADAO;
import dppom.model.qna.QnADTO;

public class QnAAdminWriteCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String idx = req.getParameter("idx");
		String reply = req.getParameter("reply");
		QnADAO dao = new QnADAO();
		QnADTO dto = dao.selectOne(req.getParameter("idx"));
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("pass", req.getParameter("pass"));
		model.addAttribute("title", req.getParameter("title"));
		model.addAttribute("content", dto.getContent());
		if (reply != null)
			model.addAttribute("reply", dto.getReply());

		dao.adminWrite(idx, reply);
	}
}
