package co.nectar.report;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nectar.HtmlResponce.HtmlError;
import co.nectar.HtmlResponce.HtmlReportList;
import co.nectar.HtmlResponce.HtmlResponce;
import co.nectar.HtmlResponce.HtmlUserList;
import co.nectar.user.User;
import co.nectar.user.UserService;
@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepo;
	
	@Autowired
	UserService userService;
	/**
	 * returns the list of reports to repored id
	 * @param reported_Id id to get reports to
	 * @return HtmlRepsonse noting success and list of reports
	 */
	@SuppressWarnings("unchecked")
	public HtmlResponce getReports(int reported_Id) {
		//local vars
		HtmlResponce htmlmsg;
		String error = "";
		User reported;
		Iterable<Report> reports;
		Optional<Iterable<Report>> opt;
		boolean success = true;
		
		//check if reported_Id is valid
		htmlmsg = userService.getUserById(reported_Id);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user
		reported = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		
		//find all reported to that user
		opt = reportRepo.findByReported(reported);
		if(!opt.isPresent()) {
			return new HtmlError(false, "no reports found");
		}
		
		//get reports
		reports = opt.get();
		return new HtmlReportList(true, reports);
		
	}

	/**
	 * deletes all reports between two users
	 * @param reported_Id user id to delete report to
	 * @param reporter_Id user id to delete report from
	 * @return HtmlReponse noting success
	 */
	public HtmlResponce deleteReport(int reported_Id, int reporter_Id) {
		//local vars
		HtmlResponce htmlmsg;
		String error = "";
		User reported,reporter;
		Iterable<Report> reports;
		Optional<Iterable<Report>> opt;
		
		//check if reported_Id is valid
		htmlmsg = userService.getUserById(reported_Id);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user
		reported = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		//check if reporter_Id is valid
		htmlmsg = userService.getUserById(reporter_Id);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user
		reporter = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		opt = reportRepo.findByReportedAndReporter(reported, reporter);
		if(!opt.isPresent()) {
			return new HtmlError(false,"No reports between those users found");
		}
		
		//get reports
		reports = opt.get();
		
		//delete reports
		reportRepo.delete(reports);
		return new HtmlError(true, "reports deleted");
	}

	/**
	 * add report between two users
	 * @param reported_Id adds report to this user
	 * @param reporter_Id add report from this user
	 * @param report partially filed report with details and reason code
	 * @return HtmlReponce noting success of report
	 */
	public HtmlResponce addReport(int reported_Id, int reporter_Id, Report report) {
		HtmlResponce htmlmsg;
		String error = "";
		User reported,reporter;
		Iterable<Report> reports;
		Optional<Iterable<Report>> opt;
		
		//check if reported_Id is valid
		htmlmsg = userService.getUserById(reported_Id);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user
		reported = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		//check if reporter_Id is valid
		htmlmsg = userService.getUserById(reporter_Id);
		if(!htmlmsg.isSuccess()) {
			error = ((HtmlError) htmlmsg).getMessage();
			return new HtmlError(false, "id to is not valid id: " + error);
		}
		//get user
		reporter = ((HtmlUserList) htmlmsg).getUsers().iterator().next();
		
		//save reporter and reported in report
		if(!report.isValid()) {
			return new HtmlError(false, "report is missing detials or valid reason");
		}
		report.setReported(reported);
		report.setReporter(reporter);
		
		
		reportRepo.save(report);
		return new HtmlError(true, "added report");
	}

	/**
	 * Return all reports on the system
	 * @return HTMLReportList of reports on the system
	 */
	public HtmlResponce getAll() {
		return new HtmlReportList(true, reportRepo.findAll());
	}

}
