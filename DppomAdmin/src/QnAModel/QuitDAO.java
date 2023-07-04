package QnAModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QuitDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	public QuitDAO() {
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

	public List<QuitDTO> selectAll(Map map) {
		List<QuitDTO> lists = new Vector<QuitDTO>();
		try {
			String query = "SELECT * FROM ( " + "SELECT Tb.*, rownum rNum FROM ( " + "SELECT * FROM feedback "
					+ "ORDER BY regidate DESC " + ") Tb " + ") WHERE rNum BETWEEN ? AND ?";
			System.out.println(query);

			System.out.println("feedback list 호출");
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				java.sql.Date regidate = rs.getDate("regidate");
				String feedback_reason = rs.getString("feedback_reason");

				QuitDTO dto = new QuitDTO(id, regidate, feedback_reason);
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
			String query = "SELECT count(*) FROM feedback ";

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
