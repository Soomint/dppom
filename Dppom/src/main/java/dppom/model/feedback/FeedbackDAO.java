package dppom.model.feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.kosmo.Dppom.Constant;

public class FeedbackDAO {

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
	public FeedbackDAO() {
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

	// 자원 해제
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 피드백 쓰기
	public void feedbackInsert(final String id, final String feedback_reason) {
		String query = "INSERT INTO feedback " + "(id, feedback_reason) values (?, ?) ";

		System.out.println(query);

		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, id);
				psmt.setString(2, feedback_reason);
			}
		});

	}

}
