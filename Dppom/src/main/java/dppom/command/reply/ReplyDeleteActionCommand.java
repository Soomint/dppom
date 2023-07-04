package dppom.command.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.reply.ReplyDAO;

public class ReplyDeleteActionCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		String idx = req.getParameter("idx");

		ReplyDAO dao = new ReplyDAO();
		dao.delete(idx);
	}
}
