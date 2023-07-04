package dppom.model.member;

import java.sql.Date;

public class PointDTO {
	
	 private int idx;
	   private String id;
	   private String detail;
	   private java.sql.Date useddate;
	   private String used_type;
	   private int adjust;
	   //private int rNum; // 리스트 번호 
	   
	   public PointDTO(){}

	public PointDTO(int idx, String id, String detail, Date useddate, String used_type, int adjust) {
		super();
		this.idx = idx;
		this.id = id;
		this.detail = detail;
		this.useddate = useddate;
		this.used_type = used_type;
		this.adjust = adjust;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public java.sql.Date getUseddate() {
		return useddate;
	}

	public void setUseddate(java.sql.Date useddate) {
		this.useddate = useddate;
	}

	public String getUsed_type() {
		return used_type;
	}

	public void setUsed_type(String used_type) {
		this.used_type = used_type;
	}

	public int getAdjust() {
		return adjust;
	}

	public void setAdjust(int adjust) {
		this.adjust = adjust;
	}

	

}
