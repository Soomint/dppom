package QnAModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class QnADAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	public QnADAO() {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup("java:comp/env/jdbc/dppom");
			conn = source.getConnection();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
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

	public List<QnADTO> selectAll(Map map) {
		List<QnADTO> lists = new Vector<QnADTO>();
		try {
			String query = "SELECT * FROM ( " + "SELECT Tb.*, rownum rNum FROM ( " + "SELECT * FROM qna ";
			if (map.get("searchWord") != null) {
				query += "WHERE " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
			}
			query += "ORDER BY idx DESC " + ") Tb " + ") WHERE rNum BETWEEN ? AND ?";
			System.out.println(query);

			System.out.println("qna list 호출");
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String qna_pass = rs.getString("qna_pass");
				String board_type = rs.getString("board_type");
				String title = rs.getString("title");
				String content = rs.getString("content");
				java.sql.Date regidate = rs.getDate("regidate");
				String reply_flag = rs.getString("reply_flag");
				String reply = rs.getString("reply");
				java.sql.Date reply_date = rs.getDate("reply_date");

				QnADTO dto = new QnADTO(idx, id, qna_pass, board_type, title, content, regidate, reply_flag, reply,
						reply_date);
				lists.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	public List<QnADTO> selectAll2(Map map) {
		List<QnADTO> lists = new Vector<QnADTO>();
		try {
			String query = "SELECT * FROM ( " + "SELECT Tb.*, rownum rNum FROM ( "
					+ "SELECT * FROM qna WHERE reply_flag='X' ";
			if (map.get("searchWord") != null) {
				query += "AND " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
			}
			query += "ORDER BY idx DESC " + ") Tb " + ") WHERE rNum BETWEEN ? AND ?";
			System.out.println(query);

			System.out.println("qna list 호출");
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String qna_pass = rs.getString("qna_pass");
				String board_type = rs.getString("board_type");
				String title = rs.getString("title");
				String content = rs.getString("content");
				java.sql.Date regidate = rs.getDate("regidate");
				String reply_flag = rs.getString("reply_flag");
				String reply = rs.getString("reply");
				java.sql.Date reply_date = rs.getDate("reply_date");

				QnADTO dto = new QnADTO(idx, id, qna_pass, board_type, title, content, regidate, reply_flag, reply,
						reply_date);
				lists.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	public int getTotalRecordCount(Map<String, Object> map) {
		int totalRecord = 0;
		try {
			String query = "SELECT count(*) FROM qna ";
			if (map.get("searchWord") != null) {
				query += "WHERE " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
			}
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRecord;
	}

	public int getTotalRecordCount2(Map<String, Object> map) {
		int totalRecord = 0;
		try {
			String query = "SELECT count(*) FROM qna WHERE reply_flag='X' ";
			if (map.get("searchWord") != null) {
				query += "AND " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
			}
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRecord;
	}
}