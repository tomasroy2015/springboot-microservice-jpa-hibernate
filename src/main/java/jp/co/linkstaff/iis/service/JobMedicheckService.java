package jp.co.linkstaff.iis.service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import jp.co.linkstaff.iis.model.JobMedicheck;
import org.springframework.data.domain.Pageable;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.repository.JobMedicheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author .....
 *
 */
@Service
public class JobMedicheckService {
	private static final Logger logger = LogManager.getLogger(AreaService .class);
	@Autowired
    private JobMedicheckRepository jobRepository;
	/**
	 * 
	 * @param jobRepository
	 */
	// @Autowired
	// public JobMedicheckService(JobMedicheckRepository jobRepository) {
	// 	this.jobRepository = jobRepository;
	// }
	/**
	 * add a new job of medicheck 
	 * @param job
	 */
	public JobMedicheck addNewJob(JobMedicheck job){
		try {
		 return jobRepository.save(job);
		} catch (Exception e) {
			logger.error("JobMedicheckService.'\n' Method: addNewJob() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @return job list of medicheck
	 */
	public List<JobMedicheck> getAllJobs(){
		return (List<JobMedicheck>) jobRepository.findAll();
	}
	/**
	 * 
	 * @param id
	 * @return particular job against job medicheckId 
	 */
	public JobMedicheck getJob(Long medicheckId){
		JobMedicheck medi = null;
		try {
			//isDataExist = areaRepository.findById(id).isPresent();
			medi = jobRepository.findById(medicheckId).get();
		} catch (Exception e) {
			logger.error("JobMedicheckService.'\n' Method: getJob() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
		return medi;
	}
	/**
	 * update job info according job medicheckId 
	 * @param job
	 * @param id
	 */
	public JobMedicheck update(JobMedicheck job,Long medicheckId){
		try {
		job.setMedicheckJobId(medicheckId);
		return jobRepository.save(job);
		} catch (Exception e) {
			logger.error("JobMedicheckService.\n Method: update() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * delete a job according job id 
	 * @param id
	 */
	public void delete(Long medicheckId){
		try {
		jobRepository.deleteById(medicheckId);
		} catch (Exception e) {
			logger.error("JobMedicheckService.\n Method: delete() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param pageable
	 * @return job list of medicheck 
	 */
	public Page<JobMedicheck> searchJobs(Pageable pageable) {
		return jobRepository.findAll(pageable);
	}
}
