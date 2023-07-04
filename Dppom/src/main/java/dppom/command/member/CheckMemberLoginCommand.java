package dppom.command.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dppom.model.member.MemberDAO;

public class CheckMemberLoginCommand {
	public boolean execute(Model model) {
		// 파라미터 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		MemberDAO dao = new MemberDAO();
		System.out.println(req.getParameter("id"));
		String dbDate = dao.myInfo(req.getParameter("id")).getLastlogin().substring(0, 10);

		long temp = Calendar.getInstance().getTime().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(temp);
		System.out.println("db에 저장된 최근 로그인 날짜 : " + dbDate);
		System.out.println("오늘 날짜 : " + nowDate);
		if (!dbDate.equals(nowDate)) {
			dao.lastLoginUpdate(req.getParameter("id"));
			dao.loginPoint(req.getParameter("id"));
			dao.close();
			return true;
		} else {
			dao.close();
			return false;
		}
	}
}