package jp.co.linkstaff.iis.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import jp.co.linkstaff.iis.model.JobFulltime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
/**
 * <JobFulltime,Long> class name and type of primary key
 * @author .....
 * @author khaliddhali
 */
@Repository
public interface JobFulltimeRepository extends JpaRepository<JobFulltime,Long> {
	
	List<JobFulltime> findByIsDeleted(boolean isDeleted);
	
	/**
	 * Overload Method: findByIsDeleted 
	 * Return Pageable instance, that need some implementation.
	 * @param isDeleted
	 * @param pageable
	 * @return
	 */
	Page<JobFulltime> findByIsDeleted(@Param("isDeleted")boolean isDeleted, Pageable pageable);
	
	JobFulltime findByIsDeletedAndFulltimeJobId(boolean isDeleted, Long id);	
	JobFulltime findByIsDeletedAndJobCodeIgnoreCase(boolean isDeleted, String jobCode);
	
	List<JobFulltime> findByIsDeletedAndHospitalCodeIgnoreCase(boolean isDeleted, String hospitalCode);	
	Page<JobFulltime> findByIsDeletedAndHospitalCodeIgnoreCase(@Param("isDeleted")boolean isDeleted, @Param("hospitalCode")String hospitalCode,Pageable pageable);
	
	List<JobFulltime> findByIsDeletedAndJobType(boolean isDeleted, Integer jobType);
	Page<JobFulltime> findByIsDeletedAndJobType(@Param("isDeleted")boolean isDeleted, @Param("jobType")Integer jobType,Pageable pageable);
	
	List<JobFulltime> findByIsDeletedAndContactEmailIgnoreCase(boolean isDeleted, String contactEmail); //should it be a list or just single instance?
	List<JobFulltime> findByIsDeletedAndContactTelIgnoreCase(boolean isDeleted, String contactTel);	//should it be a list or just single instance?
	List<JobFulltime> findByIsDeletedAndWorkTelIgnoreCase(boolean isDeleted, String workTel);
	
	
	/*
	@Query("UPDATE JobFulltime jobFulltime SET jobFulltime.isDeleted = TRUE WHERE jobFulltime.fulljobId = :fulljobId")
	void deleteJobFulltime(@Param("fulljobId") Long fulljobId);*/
}
