package dppom.model.good;

public class GoodDTO {
	private String id;
	private String board_type;
	private String board_idx;
	private String reply_idx;
	
	public GoodDTO (){}

	public GoodDTO(String id, String board_type, String board_idx, String reply_idx) {
		super();
		this.id = id;
		this.board_type = board_type;
		this.board_idx = board_idx;
		this.reply_idx = reply_idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public String getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}

	public String getReply_idx() {
		return reply_idx;
	}

	public void setReply_idx(String reply_idx) {
		this.reply_idx = reply_idx;
	}
	
	
}
