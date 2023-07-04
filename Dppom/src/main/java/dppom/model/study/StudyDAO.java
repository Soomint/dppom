package dppom.model.study;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudyDAO implements IStudyDAO {
	JdbcTemplate template;

	// DB연결
	public StudyDAO() {
		try {
			this.template = com.kosmo.Dppom.Constant.template;
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
	}

	@Override
	public List<StudyDTO> selectAll(Map<String, Object> map) {
		int start = 1;
		int end = 5;
		if (map.get("start") != null && map.get("end") != null) {
			start = Integer.parseInt(map.get("start").toString());
			end = Integer.parseInt(map.get("end").toString());
		}
		String query = "SELECT * FROM ( " + "SELECT Tb.*, rownum rNum FROM ( " + "SELECT * FROM study ";
		if (map.get("study_type") != null) {
			query += "WHERE study_type LIKE '%" + map.get("study_type") + "%' ";
			if (map.get("searchWord") != null) {
				query += "AND category ='" + map.get("searchColumn") + "' ";
				query += "AND title LIKE '%" + map.get("searchWord") + "%' ";
			}
		} else {
			if (map.get("searchWord") != null) {
				query += "WHERE category ='" + map.get("searchColumn") + "' ";
				query += "AND title LIKE '%" + map.get("searchWord") + "%' ";
			}
		}
		query += "ORDER BY idx DESC ";
		query += ") Tb ";
		query += ") WHERE rNum BETWEEN " + start + " AND " + end;
		System.out.println(query);
		return (List<StudyDTO>) template.query(query, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		String query = "SELECT count(*) FROM study ";
		if (map.get("study_type") != null) {
			query += "WHERE study_type like '%" + map.get("study_type") + "%' ";
			if (map.get("searchWord") != null) {
				query += "AND category = '" + map.get("searchColumn") + "' ";
				query += "AND title LIKE '%" + map.get("searchWord") + "%' ";
			}
		} else {
			if (map.get("searchWord") != null) {
				query += "WHERE category = '" + map.get("searchColumn") + "' ";
				query += "AND title LIKE '%" + map.get("searchWord") + "%' ";
			}
		}
		System.out.println(query);
		return template.queryForObject(query, Integer.class);
	}
}