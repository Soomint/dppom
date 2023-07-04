package dppom.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kosmo.Dppom.Constant;

public class PointDAO {

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
	public PointDAO() {
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

	// 페이지 위한 토탈 레코드수
	public int getTotalCount(Map<String, Object> map) {
		String query = "SELECT count(*) FROM point where id ='" + map.get("id") + "' ";
		if (map.get("used_type") != null) {
			query += "and used_type like '%" + map.get("used_type") + "%' ";
		}

		System.out.println(query);
		return template.queryForObject(query, Integer.class);
	}

	// 리스트
	public ArrayList<PointDTO> MypointList(Map<String, Object> map, String id) {

		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());

		// 쿼리문 작성
		String query = "select * from " + "( select Tb.*, rownum rNum " + "from ( select * from point where id='" + id
				+ "' order by idx desc ) Tb " + "   )" + " where rNum between " + start + " and " + end;

		// query = "select * from point where id='"+id+"'"; //문자는 ''붙여야함
		System.out.println(query);

		// 실행
		return (ArrayList<PointDTO>) template.query(query, new BeanPropertyRowMapper<PointDTO>(PointDTO.class));

	}

	// 포인트 총합
	public int TotalPoint(String id) {

		String query = "select sum(adjust) from point where id='" + id + "'"; // 문자는
																				// ''붙여야함
		System.out.println(query);

		// 실행문장 (박싱,언박싱)
		return template.queryForObject(query, Integer.class);
	}

	// 적립 포인트 조회
	public ArrayList<PointDTO> mySearchPoint(Map<String, Object> map, String used_type, String id) {

		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());

		// 쿼리문 작성
		String query = "select * from " + "( select Tb.*, rownum rNum " + "from "
				+ "( SELECT * FROM point WHERE used_type like '%" + used_type + "%' and id='" + id + "') Tb " + "   )"
				+ " where rNum between " + start + " and " + end;

		// query = "SELECT * FROM point WHERE used_type like '%" + used_type +
		// "%' and id='"+id+"'";
		System.out.println(query);

		// 실행
		return (ArrayList<PointDTO>) template.query(query, new BeanPropertyRowMapper<PointDTO>(PointDTO.class));

	}

	// 게시글 포인트 적립
	public void pointInsert(int point, String id, String detail) {
		System.out.println("pointInsert 실행");

		try {
			String query = "INSERT INTO point(idx, id, detail, used_type, adjust, useddate) VALUES(point_seq.nextval, '"
					+ id + "', " + detail + ", '적립', " + point + ", sysdate)";

			System.out.println(query);

			this.template.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 게시글 포인트 적립
	public void pointMinus(int point, String id, String detail) {
		System.out.println("pointMinus 실행");

		try {
			String query = "INSERT INTO point(idx, id, detail, used_type, adjust, useddate) VALUES(point_seq.nextval, '"
					+ id + "', " + detail + ", '차감', " + point + ", sysdate)";

			System.out.println(query);

			this.template.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 로그인 포인트 적립
	public void pointPlus(int point, String id, String detail) {
		System.out.println("pointPlus 실행");

		try {
			String query = "INSERT INTO point(idx, id, detail, used_type, adjust, useddate) VALUES(point_seq.nextval, '"
					+ id + "', " + detail + ", '적립', " + point + ", sysdate)";

			System.out.println(query);

			this.template.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}