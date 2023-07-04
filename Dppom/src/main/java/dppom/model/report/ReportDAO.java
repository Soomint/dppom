package dppom.model.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.kosmo.Dppom.Constant;

import dppom.model.board.BoardDTO;

public class ReportDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	// Spring JDBC를 사용하기 위한 설정
	// JDBC템플릿 설정
	private JdbcTemplate template;

	// setter설정
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	// 생성자 - DB연결
	public ReportDAO() {
		try {
			// 커넥션풀 사용
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup("java:comp/env/jdbc/dppom");
			conn = source.getConnection();

			// Spring JDBC템플릿 사용 (DB정보 모두 담겨있음)
			this.template = Constant.template; // static이므로 클래스명으로 바로 접근 가능

			System.out.println("DB연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 실패");
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("자원해제 실패");
		}
	}

	// 게시글 신고 처리
	public void boardReport(final String id, final String board_type, final String report_reason, final int board_idx,
			final String board_id) {

		System.out.println("insertReport");
		System.out.println(id + " " + board_type + " " + report_reason + " " + board_idx + " " + board_id);

		String query = "INSERT INTO report VALUES (report_seq.nextval,?,?,?,?,0,?)";

		System.out.println(query);

		try {
			this.template.update(query, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement psmt) throws SQLException {
					psmt.setString(1, id);
					psmt.setString(2, board_type);
					psmt.setString(3, report_reason);
					psmt.setInt(4, board_idx);
					psmt.setString(5, board_id);
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 댓글 신고 처리
	public void replyReport(final String id, final String report_reason, final int reply_idx, final String reply_id) {

		System.out.println("insertReport");
		System.out.println(id + " " + report_reason + " " + reply_idx + " " + reply_id);

		String query = "INSERT INTO report VALUES (report_seq.nextval,?,'reply',?,0,?,?)";

		System.out.println(query);

		try {
			this.template.update(query, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement psmt) throws SQLException {
					psmt.setString(1, id);
					psmt.setString(2, report_reason);
					psmt.setInt(3, reply_idx);
					psmt.setString(4, reply_id);
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 신고 리스트
	public ArrayList<BoardDTO> MyreportList(Map<String, Object> map, String id) {

		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());

		String query = "select * from report order by idx desc"; // 문자는 ''붙여야함
		System.out.println(query);

		// 실행
		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

	}

}
