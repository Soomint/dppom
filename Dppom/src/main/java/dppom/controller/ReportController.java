package dppom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.Dppom.Constant;

import dppom.command.report.IReportCommand;
import dppom.command.report.boardReportActionCommand;
import dppom.command.report.replyReportActionCommand;

@Controller
public class ReportController {
	private JdbcTemplate template;

	IReportCommand command = null;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	// 게시글 신고 처리
	@RequestMapping("/boardReportAction")
	public String boardReportAction(Model model, HttpServletRequest req) {
		System.out.println("boardReportAction()호출");
		model.addAttribute("req", req);

		String go = "";

		String board_type = req.getParameter("board_type");

		System.out.println("============" + board_type);

		if (board_type != null) {
			if (board_type.equals("review")) {
				go = "redirect:Review";
			}
			if (board_type.equals("dataroom")) {
				go = "redirect:DataList";
			}
			if (board_type.equals("party")) {
				go = "redirect:Party";
			}
		}

		command = new boardReportActionCommand();
		command.execute(model);

		return go;
	}

	// 댓글 신고 처리
	@RequestMapping("/replyReportAction")
	public String replyReportAction(Model model, HttpServletRequest req) {
		System.out.println("replyReportAction()호출");
		model.addAttribute("req", req);
		command = new replyReportActionCommand();
		command.execute(model);

		String return_view = req.getParameter("return_view");
		String go = "";

		System.out.println("============" + return_view);

		if (return_view != null) {
			if (return_view.equals("review")) {
				go = "redirect:Review";
			}
			if (return_view.equals("dataroom")) {
				go = "redirect:DataList";
			}
			if (return_view.equals("party")) {
				go = "redirect:Party";
			}
		}

		return go;
	}

}
