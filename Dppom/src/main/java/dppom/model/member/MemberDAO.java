package dppom.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.kosmo.Dppom.Constant;

public class MemberDAO {

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
	public MemberDAO() {
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

	// 회원 정보 조회
	public MemberDTO myInfo(final String id) {
		String query = "select * from member where id='" + id + "'";
		System.out.println(query);

		return (MemberDTO) template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}

	// 회원 정보 수정
	public void memberModify(final String pass, final String email1, final String email2, final String name,
			final String phone, final String univ, final String major, final String id) {
		String query = "UPDATE member SET pass=?, email=?, name=?, phone=?,univ=?, major=? WHERE id=? ";

		this.template.update(query, new PreparedStatementSetter() {

			String email = email1 + "@" + email2;

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, pass);
				psmt.setString(2, email);
				psmt.setString(3, name);
				psmt.setString(4, phone);
				psmt.setString(5, univ);
				psmt.setString(6, major);
				psmt.setString(7, id);
			}

		});

	}

	// ID 찾기
	public String findId(final String name, final String email) {
		String query = "select * from member where name='" + name + "' and email='" + email + "'";

		System.out.println(query);

		String searchId = "";

		try {
			MemberDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
			searchId = dto.getId();
		} catch (Exception e) {
			// org.springframework.dao.EmptyResultDataAccessException: Incorrect
			// result size: expected 1, actual 0
			// e.printStackTrace();
		}

		return searchId;

	}

	// 비밀번호 찾기
	public String findPass(final String id, final String email, final String pass_question, final String pass_answer) {
		String query = "select * from member where id='" + id + "' and email='" + email + "'" + " and pass_question='"
				+ pass_question + "'" + " and pass_answer='" + pass_answer + "'";

		System.out.println(query);
		String isUser = "";

		try {
			MemberDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
			isUser = dto.getId();

		} catch (Exception e) {
			// org.springframework.dao.EmptyResultDataAccessException: Incorrect
			// result size: expected 1, actual 0
			// e.printStackTrace();
			// 예외 처리 하면서 null인 레코드를 가진 어떤 객체를 return 함 .
		}

		return isUser;

	}

	// 비밀번호 수정
	public void modifyPass(final String new_Pass, final String new_PassRe, final String id) {

		String query = "UPDATE member SET pass ='" + new_PassRe + "' where id='" + id + "'";
		System.out.println(query);
		this.template.update(query); // 인파라메타 없을시 update()

	}

	// 회원 포인트 업데이트
	public void myPoint(final String id, final int point) {

		String query = "UPDATE member SET point ='" + point + "' where id='" + id + "'";
		System.out.println(query);
		this.template.update(query);

	}

	// 회원 탈퇴
	public void myOut(final String id) {

		String query = "DELETE FROM member where id='" + id + "'";
		System.out.println(query);
		this.template.update(query);

	}

	// 비밀번호 일치 (회원 탈퇴시 )
	public String password(String id, String pass) {
		String isUser = "";

		String query = "SELECT * FROM member WHERE id='" + id + "' AND pass='" + pass + "'";
		System.out.println(query);

		try {
			MemberDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
			isUser = dto.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUser;
	}

	// 회원 탈퇴
	public void delete(String id) {
		String query = "delete from member where id='" + id + "'";
		System.out.println(query);
	}

	/* 회원가입 */
	// ID 중복 검사
	public String idIdchk(final String id) {
		String query = "select * from member where id='" + id + "'";

		System.out.println(query);

		String searchId = "";

		try {
			MemberDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
			searchId = dto.getId();
		} catch (Exception e) {
			// org.springframework.dao.EmptyResultDataAccessException: Incorrect
			// result size: expected 1, actual 0
			// e.printStackTrace();
		}

		return searchId;

	}

	// 탈퇴한 ID 검사
	public String impossibleId(final String id) {
		String query = "select * from feedback where id='" + id + "'";

		System.out.println(query);

		String searchId = "";

		try {
			MemberDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
			searchId = dto.getId();
		} catch (Exception e) {
			// org.springframework.dao.EmptyResultDataAccessException: Incorrect
			// result size: expected 1, actual 0
			// e.printStackTrace();
		}

		return searchId;

	}

	// 회원 가입
	public void joinMember(final String id, final String pass, final String name, final String email,
			final String phone, final String univ, final String major, final String pass_question,
			final String pass_answer) {

		// ('test', '1', '테스트', 'test@naver.com', '010-1234-1234', 'test',
		// 'test', sysdate, 20, '질문', '답변', 0, 'X');

		String query = "INSERT INTO member VALUES(?,?,?,?,?,?,?,sysdate,20,?,?,0,'X',0,sysdate-1) ";

		System.out.println(query);

		this.template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, id);
				psmt.setString(2, pass);
				psmt.setString(3, name);
				psmt.setString(4, email);
				psmt.setString(5, phone);
				psmt.setString(6, univ);
				psmt.setString(7, major);
				psmt.setString(8, pass_question);
				psmt.setString(9, pass_answer);
			}

		});

	}

	// 신고 카운트 증가
	public void reportUp(final String id) {

		String query = "UPDATE member SET REPORT_CNT = REPORT_CNT +1 where id='" + id + "'";
		System.out.println(query);
		this.template.update(query);

	}

	// 학생증 인증
	public void confirmModify(final String certi_url, final String id) {
		String query = "UPDATE member SET certi_url=? WHERE id=? ";

		this.template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, certi_url);
				psmt.setString(2, id);
			}

		});

	}

	// 회원의 최근 로그인날짜 업데이트
	public void lastLoginUpdate(final String id) {
		String query = "UPDATE member SET lastlogin=sysdate WHERE id=? ";

		this.template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, id);
			}

		});
	}

	// 회원 출석포인트 지급
	public void loginPoint(final String id) {

		String query = "UPDATE member SET point = point+1 where id='" + id + "'";
		System.out.println(query);
		this.template.update(query);

	}

	// 글쓰기 포인트 지급
	public void memberPoint(String id, int point) {
		try {
			System.out.println("memberPoint 실행");

			String query = "update member set point = point + " + point + " where id='" + id + "'";

			System.out.println(query);

			this.template.update(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 글쓰기 포인트 차감
	public void memberminusPoint(String id, int point) {
		try {
			System.out.println("memberminusPoint 실행");

			String query = "update member set point = point + " + point + " where id='" + id + "'";

			System.out.println(query);

			this.template.update(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}