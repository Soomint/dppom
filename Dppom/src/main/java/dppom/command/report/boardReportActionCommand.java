package dppom.command.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.report.ReportDAO;

public class boardReportActionCommand implements IReportCommand {

	@Override
	public void execute(Model model) {
		// 신고 처리 커맨드
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		// id=wm&idx=21&msg=321&board_type=Review&title=신고합니당

		String id = req.getParameter("id"); // 신고자
		String board_type = req.getParameter("board_type");
		String report_reason = req.getParameter("report_reason");
		String board_idx = req.getParameter("board_idx");
		String board_id = req.getParameter("board_id"); // 신고당한 게시글 작성자

		System.out.println("boardReportActionCommand");

		ReportDAO reportdao = new ReportDAO();

		System.out.println(
				id + " " + board_type + " " + report_reason + " " + Integer.parseInt(board_idx) + " " + board_id);
		// 리포트 삽입
		reportdao.boardReport(id, board_type, report_reason, Integer.parseInt(board_idx), board_id);
		reportdao.close();

	}

}
