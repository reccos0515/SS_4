package co.nectar.report;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.nectar.user.User;

public interface ReportRepository extends CrudRepository<Report,Integer>{
	Optional<Iterable<Report>> findByReported(User reported);
	Optional<Iterable<Report>> findByReportedAndReporter(User reported,User reporter);
	
}
