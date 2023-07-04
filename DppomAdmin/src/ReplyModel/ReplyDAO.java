package ReplyModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import BoardModel.boardDTO;

public class ReplyDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	public ReplyDAO() {
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

	public ReplyDTO ReplyOne(int idx) {

		System.out.println(idx);

		ReplyDTO dto = new ReplyDTO();
		try {
			String sql = "select * from reply where idx=?";
			System.out.println(sql);

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idx);

			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setIdx(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setRegidate(rs.getDate(4));
				dto.setBoard_type(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	// 댓글 삭제
	public void delete(String idx) {

		try {
			String query = "DELETE FROM reply WHERE idx=?";
			System.out.println(query);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
