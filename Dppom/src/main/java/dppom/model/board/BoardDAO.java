package dppom.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.kosmo.Dppom.Constant;

public class BoardDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;

	JdbcTemplate template;

	// DB 연결
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup("java:comp/env/jdbc/dppom");
			conn = source.getConnection();

			this.template = Constant.template;
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

	// 마이페이지 내글보기
	public ArrayList<BoardDTO> myPageMyWriting(Map<String, Object> map) {

		String id = (String) map.get("id");
		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());

		String query = "select * from(select Tb.*, rownum rNum from( " + "select * from board where id='" + id + "'";
		if (map.get("board_type") != null) {
			query += " AND board_type='" + map.get("board_type") + "'";
		}
		query += ")Tb) where rNum BETWEEN " + start + " AND " + end;

		System.out.println(query);
		System.out.println("리스트 불러오기 성공");

		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}

	// 리스트 출력
	public ArrayList<BoardDTO> allList(Map<String, Object> map) {

		String board_type = (String) map.get("board_type");
		String category = (String) map.get("category");
		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());

		System.out.println("보드 DAO에서 카테고리 : " + category);

		String query = "select * from (select TT.*, rownum vNum from(select Tb.*, rownum rNum from "
				+ " (select idx, id, title, category, click_cnt, regidate, attachedfile, now_party, total_party, like_cnt  "
				+ " from board where board_type= '" + board_type + "' and category='" + category
				+ "' order by idx asc)Tb order by rNum desc)TT)where vNum between " + start + " and " + end;

		System.out.println(query);
		System.out.println("리스트 불러오기 성공");

		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

	}

	// 상세보기
	public BoardDTO RView(String pidx) {
		// 조회수 증가
		updateClick_cnt(pidx);

		String query = "select * from board where idx=" + pidx;

		System.out.println(query);
		return (BoardDTO) template.queryForObject(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}

	// 조회수증가
	private void updateClick_cnt(final String pidx) {
		String query = "UPDATE board SET click_cnt=click_cnt+1 WHERE idx=?";
		this.template.update(query, new PreparedStatementSetter() {
			// 어나니머스 클래스(무명 클래스) 라고 불리며 라인에서 바로 불러서 사용할때 객체를 만들지 않고 바로 사용한다.
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setInt(1, Integer.parseInt(pidx));
			}
		});

	}

	// 리뷰 글쓰기 처리
	public void writeAction(String id, String univ, String major, String board_type, String category, String title,
			String content, String attachedfile) {
		try {
			String query = "INSERT INTO board(idx, id, univ, major, board_type, category, title, content, attachedfile,"
					+ " click_cnt, like_cnt, report_cnt) "
					+ " VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, univ);
			psmt.setString(3, major);
			psmt.setString(4, board_type);
			psmt.setString(5, category);
			psmt.setString(6, title);
			psmt.setString(7, content);
			psmt.setString(8, attachedfile);

			System.out.println(query);

			int rn = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 리뷰 수정 처리
	public void modifyAction(String id, String univ, String major, String board_type, String category, String title,
			String content, String attachedfile, String idx) {
		try {
			String query = "UPDATE board set id=?, univ=?, major=?, board_type=?, category=?, title=?, content=?, attachedfile=?, regidate=sysdate "
					+ " where idx=?";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, univ);
			psmt.setString(3, major);
			psmt.setString(4, board_type);
			psmt.setString(5, category);
			psmt.setString(6, title);
			psmt.setString(7, content);
			psmt.setString(8, attachedfile);
			psmt.setInt(9, Integer.parseInt(idx));

			System.out.println(query);

			int rn = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 삭제
	public void deleteView(final String idx) {

		String query = "delete from board WHERE idx=?";

		this.template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, idx);

				System.out.println("게시글 번호:" + idx);

			}

		});
	}

	// 전체목록 카운트 하기
	public int getTotalCount(Map<String, Object> map) {
		System.out.println("전체 카운트");

		int totalRecord = 0;

		try {
			String query = "select count(*) from board where board_type='" + map.get("board_type") + "' and category='"
					+ map.get("category") + "'";
			/*
			 * 검색어가 있을경우 검색조건을 쿼리에 추가해줘야 한다. where 컬럼 like '%검색어%' 이런식으로 추가해줘야
			 * 한다.
			 */
			if (map.get("searchWord") != null) {
				query += " AND " + map.get("searchColumn") + " like '%" + map.get("searchWord") + "%' ";
			}

			System.out.println(query);
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRecord;
	}

	// 내글목록 카운트 하기
	public int MygetTotalCount(Map<String, Object> map) {
		System.out.println("MygetTotalCount 카운트");

		int totalRecord = 0;

		try {
			String query = "select count(*) from board where id='" + map.get("id") + "'";
			/*
			 * 검색어가 있을경우 검색조건을 쿼리에 추가해줘야 한다. where 컬럼 like '%검색어%' 이런식으로 추가해줘야
			 * 한다.
			 */
			if (map.get("board_type") != null) {
				query += " AND board_type='" + map.get("board_type") + "'";
			}

			System.out.println(query);
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRecord;
	}

	// 검색
	public ArrayList<BoardDTO> searchAction(Map<String, Object> map) {
		String board_type = (String) map.get("board_type");
		String category = (String) map.get("category");
		int start = (Integer) map.get("start");
		int end = (Integer) map.get("end");
		String column = (String) map.get("searchColumn");
		String word = (String) map.get("searchWord");

		System.out.println("컬럼은? " + column + "검색단어는? " + word);

		String query = "select Tb.*, rownum rNum from " + " (select  *  " + " from board where board_type='"
				+ board_type + "' and category='" + category + "'";
		if (map.get("searchColumn") != null) {
			query += " and " + column + " like '%" + word + "%'";
		}
		query += ")Tb order by rNum desc";

		System.out.println(query);
		System.out.println("리스트 불러오기 성공");

		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}

	// 검색
	public ArrayList<BoardDTO> searchDPAction(Map<String, Object> map) {
		String board_type = (String) map.get("board_type");
		int start = (Integer) map.get("start");
		int end = (Integer) map.get("end");
		String column = (String) map.get("searchColumn");
		String word = (String) map.get("searchWord");
		String category = (String) map.get("category");

		System.out.println("컬럼은? " + column + "검색단어는? " + word);

		String query = "select Tb.*, rownum rNum from " + " (select idx, id, title, category, click_cnt, regidate  "
				+ " from board where board_type='" + board_type + "' and category='" + category + "'";
		if (map.get("searchColumn") != null) {
			query += " and " + column + " like '%" + word + "%'";
		}
		query += ")Tb order by rNum desc";

		System.out.println(query);
		System.out.println("리스트 불러오기 성공");

		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}

	// 파티 글쓰기 처리
	public void writePartyAction(String id, String univ, String major, String board_type, String category, String title,
			String content, String now_party, String total_party) {
		try {
			String query = "INSERT INTO board(idx, id, univ, major, board_type, category, title, content, now_party, total_party, "
					+ " click_cnt, like_cnt, report_cnt) "
					+ " VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0)";

			System.out.println("파티 글쓰기 실행");

			System.out.println("현재인원" + now_party);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, univ);
			psmt.setString(3, major);
			psmt.setString(4, board_type);
			psmt.setString(5, category);
			psmt.setString(6, title);
			psmt.setString(7, content);
			psmt.setString(8, now_party);
			psmt.setString(9, total_party);

			System.out.println(query);

			int rn = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파티 수정 처리
	public void modifyPartyAction(String id, String univ, String major, String board_type, String category,
			String title, String content, String idx, int now_party, int total_party) {
		System.out.println("사람찾아요 수정 실행");
		try {
			String query = "UPDATE board set id=?, univ=?, major=?, board_type=?, category=?, title=?, content=?, now_party=?, total_party=?, regidate=sysdate "
					+ " where idx=?";

			System.out.println("DAO에서 idx: " + idx);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, univ);
			psmt.setString(3, major);
			psmt.setString(4, board_type);
			psmt.setString(5, category);
			psmt.setString(6, title);
			psmt.setString(7, content);
			psmt.setInt(10, Integer.parseInt(idx));
			psmt.setInt(8, now_party);
			psmt.setInt(9, total_party);

			System.out.println(query);

			int rn = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 추천수 +1
	public void likeup(String idx) {
		System.out.println("likeup 실행");
		try {
			String query = "UPDATE board set like_cnt = like_cnt+1  where idx=?";

			System.out.println("idx는? : " + idx);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);

			int rn = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 추천수 -1
	public void likedown(String idx) {
		System.out.println("likedown 실행");
		try {
			String query = "UPDATE board set like_cnt = like_cnt-1  where idx=?";

			System.out.println("idx는? : " + idx);

			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);

			int rn = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// best 리스트 출력
	public ArrayList<BoardDTO> bestList(Map<String, Object> map) {

		System.out.println("bestList 실행");

		String query = "select * from(select Tb.*, rownum rNum from "
				+ " (select idx, id, title, category, click_cnt, regidate, attachedfile, now_party, total_party, like_cnt  "
				+ " from board order by like_cnt desc)Tb order by rNum desc)where rNum between " + 1 + " and " + 6;

		System.out.println(query);
		System.out.println("리스트 불러오기 성공");

		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

	}

	// 최신 사람찾기리스트 출력
	public ArrayList<BoardDTO> NewList(Map<String, Object> map) {

		System.out.println("NewList 실행");

		String query = "select * from(select Tb.*, rownum rNum from "
				+ " (select idx, id, title, category, click_cnt, regidate, attachedfile, now_party, total_party, like_cnt  "
				+ " from board where board_type='party' order by regidate desc)Tb order by rNum desc)where rNum between "
				+ 1 + " and " + 5;

		System.out.println(query);
		System.out.println("리스트 불러오기 성공");

		return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

	}

}