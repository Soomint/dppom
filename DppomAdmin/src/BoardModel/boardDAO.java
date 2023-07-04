package BoardModel;

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

import MemberModel.memberDTO;

public class boardDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	// DB 연결
	public boardDAO() {
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

	// 모든 게시판 출력
	public List<boardDTO> selectAll(Map<String, Object> map, String id) {

		List<boardDTO> boardlist = new Vector<boardDTO>();
		int start = (int) map.get("start");
		int end = (int) map.get("end");

		try {
			String sql = "select * from(select TT.*, rownum vNum from" + " (select Tb.*, rownum rNum from "
					+ "(select idx, title, category, click_cnt, regidate, board_type, id  " + " from board where id=?";
			if (map.get("COLUMN") != null) {
				sql += " and  " + map.get("COLUMN") + " like '%" + map.get("WORD") + "%'";
			}

			sql += "order by idx asc)Tb order by rownum desc)TT)where vNum between ? and ?";

			System.out.println(sql);

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			System.out.println("아이디는? :" + id);

			rs = psmt.executeQuery();
			while (rs.next()) {
				boardDTO dto = new boardDTO();

				dto.setIdx(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setCategory(rs.getString(3));
				dto.setClick_cnt(rs.getInt(4));
				dto.setRegidate(rs.getDate(5));
				dto.setBoard_type(rs.getString(6));
				dto.setId(rs.getString(7));
				dto.setrNum(rs.getInt(8));

				boardlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return boardlist;
	}

	// 전체 레코드수 얻기
	public int getTotalRecordCount(Map<String, Object> map, String id) {
		int totalRecord = 0;
		try {
			String sql = "select count(*) from board where id=?";

			// 검색어가 있을경우 검색조건을 쿼리에 추가
			if (map.get("WORD") != null) {
				sql += " and " + map.get("COLUMN") + " like '%" + map.get("WORD") + "%'";
			}

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRecord;
	}

	// 뷰
	public boardDTO viewPage(int idx) {
		boardDTO dto = null;
		try {
			String sql = "select idx, univ, major, board_type, category, title, content, attachedfile, regidate, "
					+ " click_cnt, like_cnt, download_cnt, report_cnt, now_party, total_party, id  "
					+ " from board where idx=?";
			System.out.println(sql);
			System.out.println(idx);

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idx);

			rs = psmt.executeQuery();
			while (rs.next()) {
				dto = new boardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setUniv(rs.getString(2));
				dto.setMajor(rs.getString(3));
				dto.setBoard_type(rs.getString(4));
				dto.setCategory(rs.getString(5));
				dto.setTitle(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setAttachedfile(rs.getString(8));
				dto.setRegidate(rs.getDate(9));
				dto.setClick_cnt(rs.getInt(10));
				dto.setLike_cnt(rs.getInt(11));
				dto.setDownload_cnt(rs.getInt(12));
				dto.setReport_cnt(rs.getInt(13));
				dto.setNow_party(rs.getInt(14));
				dto.setTotal_party(rs.getInt(15));
				dto.setId(rs.getString(16));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	// 삭제
	public int delete(String idx) {
		int affected = 0;
		try {
			String sql = "delete from board where idx=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, idx);
			affected = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affected;
	}
}