package co.nectar.HtmlResponce;

import java.util.List;

import co.nectar.report.Report;

/**
 * This class defines an object to be converted to a json object
 * 
 * success indicates if the request was filled correctly
 * reports is a list of reports
 * 
 * @author basimon
 *
 */
public class HtmlReportList implements HtmlResponce{

	private boolean success;
	private Iterable<Report> reports;
	

	//constuctors
	public HtmlReportList(boolean success, Iterable<Report> iterable) {
		super();
		this.success = success;
		this.reports = iterable;
	}
	
	//getters and setters
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Iterable<Report> getUsers() {
		return reports;
	}
	public void setUsers(List<Report> reports) {
		this.reports = reports;
	}
	
	
}
