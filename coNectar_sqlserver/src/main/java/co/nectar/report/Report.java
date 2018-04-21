package co.nectar.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.nectar.user.User;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer reportId;
	
	@OneToOne
	private User reported;
	
	@OneToOne
	private User reporter;
	
	private int reason;
	
	private String details;

	public Report() {
		super();
	}
	
	
	public Report(Integer reportId, User reported, User reporter, int reason, String details) {
		super();
		this.reportId = reportId;
		this.reported = reported;
		this.reporter = reporter;
		this.reason = reason;
		this.details = details;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public User getReported() {
		return reported;
	}

	public void setReported(User reported) {
		this.reported = reported;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@JsonIgnore
	/**
	 * check if report is a valid report
	 * returns false if details is a null pointer
	 * returns false if reason is less than 0
	 * returns true otherwise
	 * @return true or false based on report
	 */
	public boolean isValid() {
		if(this.details == null)
			return false;
		if(this.reason < 0)
			return false;
		return true;
	}
	
	
	
	
}
