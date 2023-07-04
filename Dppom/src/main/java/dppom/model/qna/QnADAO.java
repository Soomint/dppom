package dppom.model.qna;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class QnADAO implements IQnADAO {
	JdbcTemplate template;

	// DB연결
	public QnADAO() {
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
		String query = "SELECT count(*) FROM qna ";
		if (map.get("searchWord") != null) {
			query += "WHERE " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
		}
		System.out.println(query);
		return template.queryForObject(query, Integer.class);
	}

	@Override
	public List<QnADTO> selectAll(Map<String, Object> map) {

		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());
		String query = "SELECT * FROM ( " + "SELECT Tb.*, rownum rNum FROM ( " + "SELECT * FROM qna ";
		if (map.get("searchWord") != null) {
			query += "WHERE " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY idx DESC " + ") Tb " + ") WHERE rNum BETWEEN " + start + " AND " + end;
		System.out.println(query);
		return (List<QnADTO>) template.query(query, new BeanPropertyRowMapper<QnADTO>(QnADTO.class));
	}

	@Override
	public QnADTO selectOne(String idx) {
		String query = "SELECT * FROM qna WHERE idx=" + idx;

		return (QnADTO) template.queryForObject(query, new BeanPropertyRowMapper<QnADTO>(QnADTO.class));
	}

	@Override
	public int password(String idx, String pass) {
		int retValue = 0;

		String query = "SELECT * FROM qna WHERE idx=" + idx + " AND qna_pass='" + pass + "'";

		try {
			QnADTO dto = template.queryForObject(query, new BeanPropertyRowMapper<QnADTO>(QnADTO.class));
			retValue = dto.getIdx();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	@Override
	public void write(final String id, final String pass, final String title, final String content) {
		String query = "INSERT INTO qna " + "(idx, id, qna_pass, title, content) "
				+ "values (qna_seq.nextval, ?, ?, ?, ?) ";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, id);
				psmt.setString(2, pass);
				psmt.setString(3, title);
				psmt.setString(4, content);
			}
		});
	}

	@Override
	public void adminWrite(final String idx, final String reply) {
		String query = "UPDATE qna SET reply_flag='O', reply=?, reply_date=sysdate WHERE idx=?";

		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, reply);
				psmt.setString(2, idx);
			}
		});
	}

	@Override
	public void delete(final String idx) {
		String query = "DELETE FROM qna WHERE idx=?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// setString()해도된다.
				psmt.setString(1, idx);
			}
		});
	}

	@Override
	public void edit(final String idx, final String pass, final String title, final String content) {
		String query = "UPDATE qna SET qna_pass=?, title=?, content=? , regidate=sysdate WHERE idx=?";

		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, pass);
				psmt.setString(2, title);
				psmt.setString(3, content);
				psmt.setString(4, idx);
			}
		});
	}
}
