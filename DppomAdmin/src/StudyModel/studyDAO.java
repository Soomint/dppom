package StudyModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class studyDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	// DB 연결
	public studyDAO() {

		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup("java:comp/env/jdbc/dppom");
			conn = source.getConnection();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
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

	// 어학/자격증 study 테이블 데이터 리스트로 불러오기
	public ArrayList<studyDTO> list(Map<String, Object> map) {

		ArrayList<studyDTO> lists = new ArrayList<studyDTO>();

		try {

			String query = "SELECT * FROM study "; // id 필요 없음
			// 카테고리명으록 검색
			if (map.get("category") != null) {
				query += "where category = '" + map.get("category") + "' order by idx desc";
			}

			// String query = "SELECT * FROM study order by idx desc";
			psmt = conn.prepareStatement(query);
			System.out.println(query);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int idx = rs.getInt("idx");
				String study_type = rs.getString("study_type");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String url = rs.getString("url");

				// 인자생성자로 데이터 담기 (setter 대신)
				studyDTO dto = new studyDTO(idx, study_type, category, title, content, url);
				lists.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;
	}

	// 작성하기
	public int write(studyDTO dto) {
		int affected = 0;
		try {
			String sql = "insert into study (idx, study_type, category, title, content, url) values(study_seq.nextval,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getStudy_type());
			psmt.setString(2, dto.getCategory());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getUrl());
			affected = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affected;
	}

	// 게시글보기
	public studyDTO selectOne(int idx) {
		studyDTO dto = null;

		try {
			String sql = "select category, title, content, url from study where idx=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idx);

			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new studyDTO();
				dto.setCategory(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setUrl(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public int modify(studyDTO dto) {

		int affected = 0;
		try {

			System.out.println("modifyDTO진입");

			String sql = "update study set category=?, title=?, content=?, url=? where idx=?";

			psmt = conn.prepareStatement(sql);
			// System.out.println("cateogory"+dto.getCategory());
			psmt.setString(1, dto.getCategory());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getUrl());
			psmt.setInt(5, dto.getIdx());

			System.out.println(sql + " " + affected);
			affected = psmt.executeUpdate();
			System.out.println("update 실행완료");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return affected;
	}

	// 삭제하기
	public int delete(String idx) {
		int affected = 0;
		try {
			String sql = "delete from study where idx =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, idx);
			affected = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affected;
	}

	// 전체 데이터 수
	public int getTotalRecord(Map<String, Object> map) {
		int totalRecotd = 0;
		try {
			/*
			 * String sql =
			 * "select count(*) from community c join member m on c.id=m.id ";
			 * //검색어가 있을경우 검색조건을 쿼리에 추가 if(map.get("S_WORD")!=null){ sql +=
			 * "where "+map.get("S_COLUMN")+" like '%"+map.get("S_WORD")+"%' ";
			 * }
			 */

			System.out.println("getTotalRecord 진입");

			String sql = "select count(*) from study";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalRecotd = rs.getInt(1);

			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRecotd;
	}

}