package dppom.command.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.qna.QnADAO;

public class QnADeleteCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String idx = req.getParameter("idx");
		QnADAO dao = new QnADAO();
		dao.delete(idx);
	}
}
