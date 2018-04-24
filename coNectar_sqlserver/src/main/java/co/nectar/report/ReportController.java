package co.nectar.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import co.nectar.HtmlResponce.HtmlResponce;

@CrossOrigin
@RestController
@RequestMapping(path="/ben")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	/**
	 * Return a HtmlReponseList of all reports on system
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/report")
	public HtmlResponce getAllReport() {
		return reportService.getAll();
	}
	/**
	 * returns the list of reports to reported id
	 * @param reported_Id id to get reports to
	 * @return HtmlRepsonse noting success and list of reports
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/report/{reported_Id}")
	public HtmlResponce getReport(@PathVariable int reported_Id) {
		return reportService.getReports(reported_Id);
	}
	
	/**
	 * deletes report between two users
	 * @param reported_Id user id to delete report to
	 * @param reporter_Id user id to delete report from
	 * @return HtmlReponse noting success
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/report/{reported_Id}/from/{reporter_Id}")
	public HtmlResponce deleteReport(@PathVariable int reported_Id, @PathVariable int reporter_Id) {
		return reportService.deleteReport(reported_Id,reporter_Id);
	}
	
	/**
	 * add report between two users
	 * @param reported_Id adds report to this user
	 * @param reporter_Id add report from this user
	 * @param report partially filed report with details and reason code
	 * @return HtmlReponce noting success of report
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/report/{reported_Id}/from/{reporter_Id}")
	public HtmlResponce addReport(@PathVariable int reported_Id, @PathVariable int reporter_Id, @RequestBody Report report) {
		return reportService.addReport(reported_Id,reporter_Id,report);
	}
}
