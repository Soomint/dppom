package dppom.command.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.qna.QnADAO;
import dppom.model.qna.QnADTO;

public class QnAViewCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String idx = req.getParameter("idx");
		QnADAO dao = new QnADAO();
		QnADTO dto = dao.selectOne(idx);
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		if (dto.getReply() != null)
			dto.setReply(dto.getReply().replace("\r\n", "<br/>"));
		model.addAttribute("viewData", dto);
	}
}
