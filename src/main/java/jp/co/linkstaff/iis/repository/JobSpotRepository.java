package jp.co.linkstaff.iis.repository;

import jp.co.linkstaff.iis.model.JobSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * <JobSpot,Long> class name and type of primary key
 * @author .....
 *
 */
@Repository
public interface JobSpotRepository extends CrudRepository<JobSpot,Long> {
	Page<JobSpot> findAll(Pageable pageable);

}
