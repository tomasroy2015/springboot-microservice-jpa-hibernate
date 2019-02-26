package jp.co.linkstaff.iis.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.model.JobFulltime;
import org.springframework.stereotype.Service;

import jp.co.linkstaff.iis.repository.JobFulltimeRepository;
import jp.co.linkstaff.iis.utils.ServerErrorException;
/**
 * 
 * @author .....
 * @author khaliddhali (modification)
 *
 */
@Service
public class JobFulltimeService {
	private static final Logger logger = LogManager.getLogger(JobFulltimeService.class);
	
	@Autowired //Hibernate handle object instantiation 
	private JobFulltimeRepository jobFulltimeRepo;
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	/**
	 * Return a single instance of JobFulltime that matches id
	 * @param id
	 * @return Inquiry 
	 */
	public JobFulltime getJobFulltime(Long id) {
		JobFulltime fulltimeJob = null;
		try {
			fulltimeJob = jobFulltimeRepo.findByIsDeletedAndFulltimeJobId(false, id);			
		} catch (Exception e) {
			fulltimeJob = new JobFulltime();
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getInquiry. "+e);
		}
		return fulltimeJob;
	}
	
	/**
	 * Return a list of jobFull
	 * set argument false to get a list of non deleted(flagged) data.
	 * @param boolean isDeleted
	 * @return List<Inquiry>
	 */
	public List<JobFulltime> getAllJobFulltime(){
		List<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = jobFulltimeRepo.findByIsDeleted(false); //set argument to false to get non deleted(flagged) data.
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getAllInquiries(). "+e);
		}
		
		return fullTimeJobs;
	}
	
	/**
	 * Overload Method: getAllInquiries
	 * Returns a page of all Inquires according to page# and number of item in a page.
	 * @param int pageNo,
	 * @param int number of item.
	 * @return Page<Inquiry>
	 */
	public Page<JobFulltime> getAllJobFulltime(int pageNo, int pageSize) {
		Page<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = (Page<JobFulltime>) jobFulltimeRepo.findByIsDeleted(false, PageRequest.of(pageNo, pageSize)); //set argument to false to get non deleted(flagged) data.	
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in a overload getAllInquiries(). "+e);
		}
		return fullTimeJobs;
	}
	
	public JobFulltime getJobFulltimeByJobCode(String jobCode) {
		JobFulltime fullTimeJob = null;
		try {
			fullTimeJob = jobFulltimeRepo.findByIsDeletedAndJobCodeIgnoreCase(false, jobCode);
		} catch(Exception e) {
			fullTimeJob = new JobFulltime();
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in a overload getAllInquiries(). "+e);
		}
		
		return fullTimeJob;
	}
	
	public List<JobFulltime> getJobFulltimeByHospitalCode(String hospitalCode){
		List<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = jobFulltimeRepo.findByIsDeletedAndHospitalCodeIgnoreCase(false, hospitalCode); //set argument to false to get non deleted(flagged) data.
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getAllInquiries(). "+e);
		}
		
		return fullTimeJobs;
	}
	
	public Page<JobFulltime> getJobFulltimeByHospitalCode(String hospitalCode, int pageNo, int numberOfItem) {
		Page<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = (Page<JobFulltime>) jobFulltimeRepo.findByIsDeletedAndHospitalCodeIgnoreCase(false, hospitalCode, PageRequest.of(pageNo, numberOfItem)); //set argument to false to get non deleted(flagged) data.	
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in a overload getAllInquiries(). "+e);
		}
		return fullTimeJobs;
	}
	
	public List<JobFulltime> getJobFullTimeByJobType(Integer jobType){
		List<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = jobFulltimeRepo.findByIsDeletedAndJobType(false, jobType); //set argument to false to get non deleted(flagged) data.
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getAllInquiries(). "+e);
		}
		
		return fullTimeJobs;
	}
	
	/**
	 * 
	 * @param jobType
	 * @param pageNo
	 * @param numberOfItem
	 * @return
	 */
	public Page<JobFulltime> getJobFullTimeByJobType(Integer jobType, int pageNo, int numberOfItem) {
		Page<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = (Page<JobFulltime>) jobFulltimeRepo.findByIsDeletedAndJobType(false, jobType, PageRequest.of(pageNo, numberOfItem)); //set argument to false to get non deleted(flagged) data.	
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in a overload getAllInquiries(). "+e);
		}
		return fullTimeJobs;
	}
	
	/**
	 * Get List of fullTimeJob by contactEmail ignoring case.
	 * @param String email
	 * @return
	 */
	public List<JobFulltime> getFulltimeJobByContactEmail(String email){
		List<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = jobFulltimeRepo.findByIsDeletedAndContactEmailIgnoreCase(false, email); //set argument to false to get non deleted(flagged) data.
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getAllInquiries(). "+e);
		}
		
		return fullTimeJobs;
	}
	
	/**
	 * Get a list of JobFulltime by contactTel ignoring case
	 * @param String contactTel
	 * @return fullTimeJobs
	 */
	public List<JobFulltime> getFulltimeJobByContactTel(String contactTel){
		List<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = jobFulltimeRepo.findByIsDeletedAndContactTelIgnoreCase(false, contactTel); //set argument to false to get non deleted(flagged) data.
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getAllInquiries(). "+e);
		}
		
		return fullTimeJobs;
	}
	
	/**
	 * get a list of JobFulltime by workTel ignoring case
	 * @param String workTel
	 * @return fullTimeJobs
	 */
	public List<JobFulltime> getFulltimeJobByWorkTel(String workTel){
		List<JobFulltime> fullTimeJobs = null;
		try {
			fullTimeJobs = jobFulltimeRepo.findByIsDeletedAndWorkTelIgnoreCase(false, workTel); //set argument to false to get non deleted(flagged) data.
		} catch (Exception e) {
			//is a logger needed, here?
			throw new ServerErrorException("Exception occured at Service in getAllInquiries(). "+e);
		}
		
		return fullTimeJobs;
	}
	
	/**
	 * Insert a single instance of JobFulltime
	 * @param fullTimeJob
	 * @return jobFullTime
	 */
	public JobFulltime addJobFulltime(JobFulltime fullTimeJob) {
		JobFulltime savedInstance = null;
		try {
			savedInstance = jobFulltimeRepo.save(fullTimeJob);
		} catch (Exception e) {
			throw new ServerErrorException("Exception occured at Service in addJobFulltime(). "+e);
		}
		return savedInstance;
	}
	
	public JobFulltime updateJobFulltime(Long id, JobFulltime fullTimeJob) {
		JobFulltime savedInstance = null;
		
		if(jobFulltimeRepo.findByIsDeletedAndFulltimeJobId(false, id) != null) {
			fullTimeJob.setFulltimeJobId(id);	
			fullTimeJob.setUpdatedAt(this.getCurrentDateTime());
			try {
				return savedInstance = jobFulltimeRepo.save(fullTimeJob);			
			} catch(Exception e) {
				//is a logger needed, here?
				throw new ServerErrorException("Exception occured at Service in updateJobFulltime(). "+e);
			}			
		}else {
			logger.error(String.format("JobFulltime by id: %d is not found.\nHence, cannot be updated.", id));
		}
		
		return savedInstance;
	}

	public JobFulltime deleteJobFulltime(Long fulltimeJobId) {		
		try {
			JobFulltime fullTimeJob = jobFulltimeRepo.findById(fulltimeJobId).get();
			fullTimeJob.setIsDeleted(true);
			jobFulltimeRepo.save(fullTimeJob);
			return fullTimeJob;
		} catch (Exception e) {
			logger.error("AreaService.\n Method: deleteArea() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	
	/**
	 * Return current Date and time without a time zone signature. 
	 * @return
	 */
	public Date getCurrentDateTime() {
		Date input = new Date();
		Instant instant = input.toInstant();
		return Date.from(instant);
	}
	
	public List<JobFulltime> getAllOriginalJobFulltimeList(){
		List<JobFulltime> jobFulltime = null;
		try {
			jobFulltime = jobFulltimeRepo.findByIsDeleted(false); 
		} catch (Exception e) {			
			throw new ServerErrorException("Exception occured at Service in getAllOriginalJobFulltime(). "+e);	
		}		
		return jobFulltime;
	}
	
}//End of the class
