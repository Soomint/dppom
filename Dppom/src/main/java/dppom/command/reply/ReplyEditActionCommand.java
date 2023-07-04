package dppom.command.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.reply.ReplyDAO;

public class ReplyEditActionCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		String idx = req.getParameter("reply_idx");
		String content = req.getParameter("content");

		ReplyDAO dao = new ReplyDAO();
		dao.edit(idx, content);
	}
}
