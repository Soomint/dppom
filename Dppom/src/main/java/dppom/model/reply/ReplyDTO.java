package dppom.model.reply;

import java.sql.Date;

public class ReplyDTO 
{
	private int idx;
	private String id;
	private String content;
	private java.sql.Date regidate;
	private String board_type;
	private int board_idx;
	private int reply_idx;
	private int reply_step;
	private int reply_indent;
	private int rNum;
	
	public ReplyDTO(){}

	public ReplyDTO(int idx, String id, String content, Date regidate, String board_type, int board_idx, int reply_idx,
			int reply_step, int reply_indent) {
		super();
		this.idx = idx;
		this.id = id;
		this.content = content;
		this.regidate = regidate;
		this.board_type = board_type;
		this.board_idx = board_idx;
		this.reply_idx = reply_idx;
		this.reply_step = reply_step;
		this.reply_indent = reply_indent;
	}

	public ReplyDTO(int idx, String id, String content, Date regidate, String board_type, int board_idx, int reply_idx,
			int reply_step, int reply_indent, int rNum) {
		super();
		this.idx = idx;
		this.id = id;
		this.content = content;
		this.regidate = regidate;
		this.board_type = board_type;
		this.board_idx = board_idx;
		this.reply_idx = reply_idx;
		this.reply_step = reply_step;
		this.reply_indent = reply_indent;
		this.rNum = rNum;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.sql.Date getRegidate() {
		return regidate;
	}

	public void setRegidate(java.sql.Date regidate) {
		this.regidate = regidate;
	}

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public int getReply_idx() {
		return reply_idx;
	}

	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}

	public int getReply_step() {
		return reply_step;
	}

	public void setReply_step(int reply_step) {
		this.reply_step = reply_step;
	}

	public int getReply_indent() {
		return reply_indent;
	}

	public void setReply_indent(int reply_indent) {
		this.reply_indent = reply_indent;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
}
