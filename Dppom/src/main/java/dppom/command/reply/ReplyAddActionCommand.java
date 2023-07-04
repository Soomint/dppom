package dppom.command.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.reply.ReplyDAO;

public class ReplyAddActionCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		String id = req.getParameter("id");
		String content = req.getParameter("content");
		String board_type = req.getParameter("board_type");
		String board_idx = req.getParameter("board_idx");
		int reply_step = 0;
		int reply_indent = 0;

		ReplyDAO dao = new ReplyDAO();
		dao.write(id, content, board_type, board_idx, reply_step, reply_indent);
	}

}
