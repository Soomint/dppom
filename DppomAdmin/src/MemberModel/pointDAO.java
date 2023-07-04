package MemberModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import BoardModel.boardDAO;
import ReplyModel.ReplyDAO;
import StudyModel.studyDTO;

public class pointDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	// DB 연결
	public pointDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup("java:comp/env/jdbc/dppom");
			conn = source.getConnection();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			System.out.println("DB연결 실패");
		}
	}

	// 자원 반납
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 선택한 회원정보 출력
	public List<pointDTO> selectAll(Map<String, Object> map, String id) {

		List<pointDTO> memberlist = new Vector<pointDTO>();
		int start = (int) map.get("start");
		int end = (int) map.get("end");

		try {
			String sql = "select * from(select tt.*, rownum vNum from (select tb.*, rownum rNum from "
					+ " (SELECT idx, id, detail, useddate, used_type, adjust "
					+ " FROM point WHERE id=? ORDER BY idx desc)Tb order by rNum asc)tt)where vNum between ? and ?";
			System.out.println(sql);

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			System.out.println(id);

			rs = psmt.executeQuery();
			while (rs.next()) {
				pointDTO dto = new pointDTO();

				dto.setIdx(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setDetail(rs.getString(3));
				dto.setUseddate(rs.getDate(4));
				dto.setUsed_type(rs.getString(5));
				dto.setAdjust(rs.getInt(6));

				memberlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberlist;
	}

	// 전체 레코드수 얻기
	public int getTotalRecordCount(Map<String, Object> map, String id) {
		int totalRecord = 0;
		try {
			System.out.println("id는?" + id);

			String sql = "select count(*) from point where id=?";
			if (map.get("type") != null) {
				sql += "and used_type='" + map.get("type") + "'";
			}

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("전체 레코드수 얻기" + sql);

			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRecord;
	}

	// 포인트 감점 - 신고 20점 , 다운로드 10점 id , 상황 ( 신고인지 , 다운로드인지)
	public void minusPoint(String id, String type, String board_idx, String what) {
		// int affected =0 ;

		memberDAO memdao = new memberDAO();

		System.out.println("포인트차감" + type + " " + what);
		try {
			String sql = "";

			if (type.equals("report")) {
				// 신고인 경우 -20
				sql = "insert into point values(point_seq.nextval, ? , '게시글 신고 처리', sysdate, '차감', -20)";

				// 게시글인 경우
				if (what.equals("board")) {
					// 게시글 삭제 (board_idx)
					boardDAO boarddao = new boardDAO();
					int boarddelete = boarddao.delete(board_idx);
				} else {
					ReplyDAO replydao = new ReplyDAO();
					replydao.delete(board_idx);
				}

				// 포인트 처리
				memdao.minusPoint(id, 20);

			}
			if (type.equals("fakereport")) {
				// 허위 신고인 경우
				sql = "insert into point values(point_seq.nextval, ? , '게시글 허위 신고', sysdate, '차감', -20)";

				// 포인트 처리
				memdao.minusPoint(id, 20);

			}
			if (type.equals("download")) {
				// 다운로드 한 경우
				sql = "insert into point values(point_seq.nextval, ? , '게시글 다운로드', sysdate, '차감', -10)";

				// 포인트 처리
				memdao.minusPoint(id, 10);
			}
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.executeUpdate();

			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return affected;
	}

}