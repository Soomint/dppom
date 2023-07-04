package ReportModel;

public class ReportDTO 
{
	private int idx;
	private String id;
	private String board_type;
	private String report_reason;
	private int board_idx;
	private int reply_idx;
	private String board_id;
	
	
	public ReportDTO(){}


	public ReportDTO(int idx, String id, String board_type, String report_reason, int board_idx, int reply_idx, String board_id) {
		//super();
		this.idx = idx;
		this.id = id;
		this.board_type = board_type;
		this.report_reason = report_reason;
		this.board_idx = board_idx;
		this.reply_idx = reply_idx;
		this.board_id = board_id;
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


	public String getBoard_type() {
		return board_type;
	}


	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}


	public String getReport_reason() {
		return report_reason;
	}


	public void setReport_reason(String report_reason) {
		this.report_reason = report_reason;
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
   
	
	public String getBoard_id() {
		return board_id;
	}


	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
   
	
}