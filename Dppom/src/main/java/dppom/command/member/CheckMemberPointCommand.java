package dppom.command.member;

import dppom.model.member.MemberDAO;

public class CheckMemberPointCommand {
	public int point(String id) {
		// 파라미터 받기
		MemberDAO dao = new MemberDAO();
		System.out.println("포인트 : " + dao.myInfo(id).getPoint());
		int point = dao.myInfo(id).getPoint();
		dao.close();
		return point;
	}
}