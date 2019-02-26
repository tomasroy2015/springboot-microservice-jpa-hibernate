package jp.co.linkstaff.iis.repository;

import org.springframework.data.domain.Page;
import jp.co.linkstaff.iis.model.JobParttime;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * <JobParttime,Long> class name and type of primary key
 * @author .....
 *
 */
@Repository
public interface JobParttimeRepository extends CrudRepository<JobParttime,Long> {
	Page<JobParttime> findAll(Pageable pageable);

}
