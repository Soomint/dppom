package dppom.command.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.qna.QnADAO;

public class QnAEditActionCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("reply", "X");

		QnADAO dao = new QnADAO();
		dao.edit(req.getParameter("idx"), req.getParameter("pass"), req.getParameter("title"),
				req.getParameter("content"));
	}
}
