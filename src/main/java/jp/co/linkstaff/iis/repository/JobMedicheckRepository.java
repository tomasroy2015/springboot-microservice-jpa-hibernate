package jp.co.linkstaff.iis.repository;

import org.springframework.data.domain.Page;
import jp.co.linkstaff.iis.model.JobMedicheck;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * <JobMedicheck,Long> class name and type of primary key
 * @author .....
 *
 */
@Repository
public interface JobMedicheckRepository extends CrudRepository<JobMedicheck,Long>{
	Page<JobMedicheck> findAll(Pageable pageable);
}
