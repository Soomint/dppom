package dppom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.Dppom.Constant;

import dppom.command.board.RViewCommand;
import dppom.command.reply.ReReplyAddActionCommand;
import dppom.command.reply.ReplyAddActionCommand;
import dppom.command.reply.ReplyAddCommand;
import dppom.command.reply.ReplyDeleteActionCommand;
import dppom.command.reply.ReplyEditActionCommand;
import dppom.command.reply.ReplyEditCommand;

@Controller
public class ReplyController {
	private JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	// reviewview.jsp에서 댓글을 새로 등록하는 기능
	@RequestMapping("/ReplyAddAction")
	public String replyAddAction(Model model, HttpServletRequest req) {
		System.out.println("ReplyAddAction()호출");
		model.addAttribute("req", req);
		ReplyAddActionCommand raCommand = new ReplyAddActionCommand();
		raCommand.execute(model);

		model.addAttribute("idx", req.getParameter("board_idx"));
		model.addAttribute("filename", req.getParameter("board_type"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));

		if (req.getParameter("board_type").equals("DataList")) {
			return "redirect:DataView";
		} else if (req.getParameter("board_type").equals("Party")) {
			return "redirect:PartyView";
		} else {
			return "redirect:ReviewView";
		}

	}

	// reviewview.jsp에서 댓글 오른쪽에 있는 댓글 버튼을 클릭할때 페이지가 이동하는 기능
	@RequestMapping("/ReReplyAdd")
	public String rereplyAdd(Model model, HttpServletRequest req) {
		System.out.println("ReReplyAdd()호출");
		model.addAttribute("req", req);
		RViewCommand command = new RViewCommand();
		command.execute(model);

		ReplyAddCommand raCommand = new ReplyAddCommand();
		raCommand.execute(model);

		model.addAttribute("flag", req.getParameter("flag"));
		model.addAttribute("idx", req.getParameter("board_idx"));
		model.addAttribute("filename", req.getParameter("board_type"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));

		return "Board/Review/ReplyReviewWrite";
	}

	// 대댓글을 입력하는 action기능
	@RequestMapping("/ReReplyAddAction")
	public String rereplyAddAction(Model model, HttpServletRequest req) {
		System.out.println("ReReplyAddAction()호출");
		model.addAttribute("req", req);
		RViewCommand command = new RViewCommand();
		command.execute(model);

		ReReplyAddActionCommand rraaCommand = new ReReplyAddActionCommand();
		rraaCommand.execute(model);

		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("filename", req.getParameter("board_type"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));

		return "redirect:ReviewView";
	}

	// 댓글, 대댓글을 삭제하는 기능(삭제된 내용입니다)
	@RequestMapping("/ReplyDeleteAction")
	public String replyDeleteAction(Model model, HttpServletRequest req) {
		System.out.println("ReplyDeleteAction()호출");
		model.addAttribute("req", req);
		ReplyDeleteActionCommand rdCommand = new ReplyDeleteActionCommand();
		rdCommand.execute(model);

		model.addAttribute("idx", req.getParameter("board_idx"));
		model.addAttribute("filename", req.getParameter("board_type"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));

		return "redirect:ReviewView";
	}

	// 댓글, 대댓글의 글을 수정하는 페이지로 이동
	@RequestMapping("/ReplyEdit")
	public String rereplyEdit(Model model, HttpServletRequest req) {
		System.out.println("ReplyEdit()호출");
		model.addAttribute("req", req);
		RViewCommand command = new RViewCommand();
		command.execute(model);

		ReplyEditCommand reCommand = new ReplyEditCommand();
		reCommand.execute(model);

		model.addAttribute("flag", req.getParameter("flag"));
		model.addAttribute("idx", req.getParameter("board_idx"));
		model.addAttribute("filename", req.getParameter("board_type"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));

		return "Board/Review/ReplyReviewEdit";
	}

	// 댓글, 대댓글의 내용을 수정하는 action기능
	@RequestMapping("/ReplyEditAction")
	public String replyEditAction(Model model, HttpServletRequest req) {
		System.out.println("ReplyEditAction()호출");
		model.addAttribute("req", req);
		RViewCommand command = new RViewCommand();
		command.execute(model);

		ReplyEditActionCommand reaCommand = new ReplyEditActionCommand();
		reaCommand.execute(model);

		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("filename", req.getParameter("board_type"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));

		return "redirect:ReviewView";
	}
}