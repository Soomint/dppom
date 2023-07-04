package dppom.command.qna;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.qna.QnADAO;

public class QnAWriteCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		QnADAO dao = new QnADAO();
		dao.write(id, pass, title, content);
	}
}
