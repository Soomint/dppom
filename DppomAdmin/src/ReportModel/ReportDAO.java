package ReportModel;

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

public class ReportDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	public ReportDAO() {
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

	// 신고 내역 리스트
	public List<ReportDTO> selectAll(Map map) {
		List<ReportDTO> lists = new Vector<ReportDTO>();
		try {
			String query = "SELECT * FROM report order by idx desc";

			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String board_type = rs.getString("board_type");
				String report_reason = rs.getString("report_reason");
				int board_idx = rs.getInt("board_idx");
				int reply_idx = rs.getInt("reply_idx");
				String board_id = rs.getString("board_id");

				ReportDTO dto = new ReportDTO(idx, id, board_type, report_reason, board_idx, reply_idx, board_id);
				lists.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	// 신고 내역 삭제
	public void delete(String idx) {
		try {
			String query = "DELETE FROM report WHERE idx=?";
			System.out.println(query);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}