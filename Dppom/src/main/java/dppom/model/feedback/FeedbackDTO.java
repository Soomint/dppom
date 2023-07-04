package dppom.model.feedback;

import java.sql.Date;

public class FeedbackDTO {

	
	private String id;
	private java.sql.Date reigedate;
	private String feedback_reason;
	
	public FeedbackDTO(){}

	public FeedbackDTO(String id, Date reigedate, String feedback_reason) {
		super();
		this.id = id;
		this.reigedate = reigedate;
		this.feedback_reason = feedback_reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.sql.Date getReigedate() {
		return reigedate;
	}

	public void setReigedate(java.sql.Date reigedate) {
		this.reigedate = reigedate;
	}

	public String getFeedback_reason() {
		return feedback_reason;
	}

	public void setFeedback_reason(String feedback_reason) {
		this.feedback_reason = feedback_reason;
	}
	
	
	

}
