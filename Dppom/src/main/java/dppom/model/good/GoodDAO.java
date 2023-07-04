package dppom.model.good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.kosmo.Dppom.Constant;

public class GoodDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	JdbcTemplate template;

	// DB 연결
	public GoodDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup("java:comp/env/jdbc/dppom");
			conn = source.getConnection();

			this.template = Constant.template;
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

	// 좋아요 저장
	public void like(String idx, String id) {
		System.out.println("like 실행");
		try {
			String query = "insert into good(board_idx, id, board_type, reply_idx) values(?, ?, 0, 0)";

			System.out.println("id는? : " + id + " idx는 : " + idx);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.setString(2, id);

			int rn = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 좋아요 눌렀는지 확인
	public int check(String id, String idx) {
		System.out.println("check 실행");

		int totalcount = 0;

		try {
			String query = "select count(*) from good where id=? and board_idx=?";

			System.out.println("id는? : " + id + " idx는 : " + idx);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, idx);

			rs = psmt.executeQuery();
			rs.next();
			totalcount = rs.getInt(1);

			System.out.println("dao에서 check개수 확인하기: " + totalcount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalcount;
	}

	// 좋아요 삭제
	public void likedelete(String idx, String id) {
		System.out.println("like 실행");
		try {
			String query = "delete from good where board_idx=? and id=?";

			System.out.println("id는? : " + id + " idx는 : " + idx);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.setString(2, id);

			int rn = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}