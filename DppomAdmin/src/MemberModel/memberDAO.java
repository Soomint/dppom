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
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class memberDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	// DB 연결
	public memberDAO() {

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
	public memberDTO selectOne(String id) {

		memberDTO dto = null;

		try {
			String sql = "select id, pass, name, email, phone, univ, major, regidate, point, pass_question, pass_answer, report_cnt, certi_flag, certi_url, lastlogin  "
					+ " from member where id=?";
			System.out.println(sql);

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			while (rs.next()) {
				dto = new memberDTO();

				dto.setId(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setUniv(rs.getString(6));
				dto.setMajor(rs.getString(7));
				dto.setRegidate(rs.getDate(8));
				dto.setPoint(rs.getInt(9));
				dto.setPass_question(rs.getString(10));
				dto.setPass_answer(rs.getString(11));
				dto.setReport_cnt(rs.getInt(12));
				dto.setCerti_flag(rs.getString(13));
				dto.setCerti_url(rs.getString(14));
				dto.setLastlogin(rs.getString(15));

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dto;
	}

	// 모든 회원 출력
	public List<memberDTO> selectAll(Map<String, Object> map) {

		List<memberDTO> memberlist = new Vector<memberDTO>();

		try {
			String sql = "select id, name, email, univ, major, point, regidate, certi_flag, certi_url, lastlogin "
					+ " from member";
			System.out.println(sql);

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				memberDTO dto = new memberDTO();

				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setUniv(rs.getString(4));
				dto.setMajor(rs.getString(5));
				dto.setPoint(rs.getInt(6));
				dto.setRegidate(rs.getDate(7));
				dto.setCerti_flag(rs.getString(8));
				dto.setCerti_url(rs.getString(9));
				dto.setLastlogin(rs.getString(10));

				memberlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return memberlist;
	}

	// 전체 레코드수 얻기
	public int getTotalRecordCount(Map<String, Object> map) {
		int totalRecord = 0;
		try {
			String sql = "select count(*) from member ";

			// 검색어가 있을경우 검색조건을 쿼리에 추가
			if (map.get("WORD") != null) {
				sql += " where " + map.get("COLUMN") + " like '%" + map.get("WORD") + "%'";
			}

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRecord;
	}

	// 인증하기
	public void approval(String id) {
		try {
			String query = "UPDATE member SET certi_flag='O' WHERE id=? ";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 인증 거절하기
	public void refusal(String id) {
		try {
			String query = "UPDATE member SET certi_url='1' WHERE id=? ";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 포인트 감점
	public void minusPoint(String id, int num) {
		try {
			String query = "UPDATE member SET point=point-? where id=? ";

			psmt = conn.prepareStatement(query);
			psmt.setString(2, id);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();

			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}