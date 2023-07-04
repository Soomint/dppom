package dppom.command.reply;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.reply.ReplyDAO;
import dppom.model.reply.ReplyDTO;

public class ReplyAddCommand {
	public void execute(Model model) {
		ReplyDAO dao = new ReplyDAO();

		Map<String, Object> map = model.asMap();

		HttpServletRequest req = (HttpServletRequest) map.get("req");
		map.put("board_type", req.getParameter("board_type"));
		map.put("board_idx", req.getParameter("idx"));

		int totalRecordCount = dao.getTotalCount(map);

		int pageSize = 5;
		int blockPage = 5;

		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);

		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));

		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;

		map.put("start", start);
		map.put("end", end);
		map.put("totalPage", totalPage);
		map.put("nowPage", nowPage);
		map.put("totalCount", totalRecordCount);
		map.put("pageSize", pageSize);

		String addQuery = "idx=" + req.getParameter("idx") + "&filename=" + req.getParameter("filename") + "&";
		String pagingImg = com.kosmo.Dppom.PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
				req.getContextPath() + "/ReviewView?" + addQuery);

		int virtualNum = 0; // 리스트 가상번호
		int countNum = 0; // 카운트하기 위한 변수

		List<ReplyDTO> replyData = dao.selectAll(map);

		for (ReplyDTO row : replyData) {
			String reSpace = "";
			String reSpace2 = "";
			if (row.getReply_indent() > 0) {
				// 답변글 들여쓰기
				for (int i = 0; i <= row.getReply_indent(); i++) {
					if (i == row.getReply_indent())
						reSpace += "<img src=\"./resources/mainview/reply.gif\" alt=\"reply\" />";
					else
						reSpace += "&nbsp;";
					reSpace2 += "&nbsp;&nbsp;";
				}

				// 답변글 아이콘달기
				if (row.getId() != null)
					row.setId("&nbsp;" + reSpace + "&nbsp;" + row.getId());
				else
					row.setId("&nbsp;" + reSpace + "&nbsp;" + "탈퇴회원");
				row.setContent(reSpace2 + row.getContent());
			} else if (row.getId() == null)
				row.setId("탈퇴회원");

			reSpace = "";
			reSpace2 = "";
			virtualNum = totalRecordCount - (((nowPage - 1) * pageSize) + countNum++);
			row.setrNum(virtualNum);
		}

		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("replyData", replyData);
	}
}