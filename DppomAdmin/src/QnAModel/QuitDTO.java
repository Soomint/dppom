package QnAModel;

import java.sql.Date;

public class QuitDTO 
{
	private String id;
	private java.sql.Date regidate;
	private String feedback_reason;
	
	public QuitDTO(String id, Date regidate, String feedback_reason) {
		this.id = id;
		this.regidate = regidate;
		this.feedback_reason = feedback_reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.sql.Date getRegidate() {
		return regidate;
	}

	public void setRegidate(java.sql.Date regidate) {
		this.regidate = regidate;
	}

	public String getFeedback_reason() {
		return feedback_reason;
	}

	public void setFeedback_reason(String feedback_reason) {
		this.feedback_reason = feedback_reason;
	}
}
