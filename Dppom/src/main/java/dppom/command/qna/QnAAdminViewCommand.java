package dppom.command.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.qna.QnADAO;
import dppom.model.qna.QnADTO;

public class QnAAdminViewCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		QnADAO dao = new QnADAO();
		QnADTO dto = dao.selectOne(req.getParameter("idx"));

		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("id", req.getParameter("id"));
		model.addAttribute("pass", req.getParameter("pass"));
		model.addAttribute("title", req.getParameter("title"));
		model.addAttribute("content", dto.getContent());
		model.addAttribute("reply", dto.getReply());

		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		if (dto.getReply() != null)
			dto.setReply(dto.getReply().replace("\r\n", "<br/>"));
	}
}
