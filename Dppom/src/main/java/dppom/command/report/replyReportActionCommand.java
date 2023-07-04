package dppom.command.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.reply.ReplyDAO;
import dppom.model.reply.ReplyDTO;
import dppom.model.report.ReportDAO;

public class replyReportActionCommand implements IReportCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		// id=wm&idx=21&msg=321&board_type=Review&title=신고합니당

		String id = req.getParameter("id"); // 신고자
		String report_reason = req.getParameter("report_reason");
		String reply_idx = req.getParameter("reply_idx");
		ReplyDAO replydao = new ReplyDAO();
		// idx로 id select해온다 ( 대댓글 신고 당했을 경우 )
		ReplyDTO replydto = replydao.selectOne(reply_idx);
		String reply_id = replydto.getId(); // 신고당한 댓글 작성자

		System.out.println("replyReportActionCommand");

		ReportDAO reportdao = new ReportDAO();

		System.out.println(id + report_reason + " " + Integer.parseInt(reply_idx) + " " + reply_id);
		// 리포트 삽입
		reportdao.replyReport(id, report_reason, Integer.parseInt(reply_idx), reply_id);
		reportdao.close();

	}

}
