package dppom.model.reply;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class ReplyDAO implements IReplyDAO {
	JdbcTemplate template;

	public ReplyDAO() {
		try {
			this.template = com.kosmo.Dppom.Constant.template;
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		try {
			String query = "SELECT count(*) FROM reply ";
			query += "WHERE board_type = '" + map.get("board_type") + "' ";
			query += "AND board_idx = '" + map.get("board_idx") + "' ";
			System.out.println(query);
			return template.queryForObject(query, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ReplyDTO> selectAll(Map<String, Object> map) {

		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());
		String query = "SELECT * FROM ( " + "SELECT Tb.*, rownum rNum FROM ( " + "SELECT * FROM reply ";
		query += "WHERE board_type = '" + map.get("board_type") + "' ";
		query += "AND board_idx = '" + map.get("board_idx") + "' ";
		query += "ORDER BY reply_idx DESC, reply_step ASC " + ") Tb " + ") WHERE rNum BETWEEN " + start + " AND " + end;
		System.out.println(query);
		return (List<ReplyDTO>) template.query(query, new BeanPropertyRowMapper<ReplyDTO>(ReplyDTO.class));
	}

	@Override
	public void write(final String id, final String content, final String board_type, final String board_idx,
			final int reply_step, final int reply_indent) {
		String query = "INSERT INTO reply "
				+ "(idx, id, content, regidate, board_type, board_idx, reply_idx, reply_step, reply_indent) "
				+ "values (reply_seq.nextval, ?, ?, sysdate, ?, ?, reply_seq.currval, ?, ?) ";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, id);
				psmt.setString(2, content);
				psmt.setString(3, board_type);
				psmt.setString(4, board_idx);
				psmt.setInt(5, reply_step);
				psmt.setInt(6, reply_indent);
			}
		});
	}

	@Override
	public void delete(final String idx) {
		String query = "UPDATE reply SET content='삭제된 댓글입니다.', regidate=null WHERE idx=?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// setString()해도된다.
				psmt.setString(1, idx);
			}
		});
	}

	@Override
	public void edit(final String idx, final String content) {
		String query = "UPDATE reply SET content=? , regidate=sysdate WHERE idx=?";

		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, content);
				psmt.setString(2, idx);
			}
		});
	}

	@Override
	public ReplyDTO selectOne(String idx) {
		String query = "SELECT * FROM reply WHERE idx=" + idx;

		return (ReplyDTO) template.queryForObject(query, new BeanPropertyRowMapper<ReplyDTO>(ReplyDTO.class));
	}

	@Override
	public void replyWrite(final String id, final String content, final String board_type, final int board_idx,
			final int reply_idx, final int reply_step, final int reply_indent) {
		// 답변글쓰기전 레코드 업데이트
		replyPrevUpdate(board_type, board_idx, reply_idx, reply_step);

		String query = "INSERT INTO reply "
				+ "(idx, id, content, regidate, board_type, board_idx, reply_idx, reply_step, reply_indent) "
				+ "values (reply_seq.nextval, ?, ?, sysdate, ?, ?, ?, ?, ?) ";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, id);
				psmt.setString(2, content);
				psmt.setString(3, board_type);
				psmt.setInt(4, board_idx);
				psmt.setInt(5, reply_idx);
				psmt.setInt(6, reply_step);
				psmt.setInt(7, reply_indent);
			}
		});
	}

	@Override
	public void replyPrevUpdate(final String board_type, final int board_idx, final int reply_idx,
			final int reply_step) {
		String query = "UPDATE reply " + "SET reply_step=reply_step+1 " + "WHERE board_type = '" + board_type + "' "
				+ "AND board_idx = " + board_idx + " " + "AND reply_idx=? AND reply_step>=?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setInt(1, reply_idx);
				psmt.setInt(2, reply_step);
			}
		});
	}
}
