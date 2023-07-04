package dppom.command.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.reply.ReplyDAO;
import dppom.model.reply.ReplyDTO;

public class ReReplyAddActionCommand {
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		ReplyDAO dao = new ReplyDAO();
		ReplyDTO dto = dao.selectOne(req.getParameter("reply_idx"));

		String id = req.getParameter("id");
		String content = req.getParameter("content");
		String board_type = req.getParameter("board_type");
		int board_idx = Integer.parseInt(req.getParameter("idx"));
		int reply_idx = dto.getReply_idx();
		int reply_step = dto.getReply_step() + 1;
		int reply_indent = dto.getReply_indent() + 1;

		dao.replyWrite(id, content, board_type, board_idx, reply_idx, reply_step, reply_indent);
	}

}
