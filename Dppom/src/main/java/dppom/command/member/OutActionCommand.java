package dppom.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.feedback.FeedbackDAO;

public class OutActionCommand implements MemberCommand {

	@Override
	public void execute(Model model) {

		System.out.println("OutActionCommand 커맨드");

		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		// 피드백 사유 저장
		FeedbackDAO feeddao = new FeedbackDAO();
		System.out.println(req.getParameter("feedback_reason"));
		feeddao.feedbackInsert(req.getParameter("id"), req.getParameter("feedback_reason"));

		feeddao.close();

	}

}
