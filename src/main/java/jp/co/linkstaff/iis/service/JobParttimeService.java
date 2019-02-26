package jp.co.linkstaff.iis.service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.data.domain.Page;
import jp.co.linkstaff.iis.model.JobParttime;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.repository.JobParttimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author .....
 *
 */
@Service
public class JobParttimeService {
	private static final Logger logger = LogManager.getLogger(AreaService .class);
	@Autowired
    private JobParttimeRepository jobRepository;
	/**
	 * 
	 * @param jobRepository
	 */
	// @Autowired
	// public JobParttimeService(JobParttimeRepository jobRepository) {
	// 	this.jobRepository = jobRepository;
	// }
	/**
	 * add a new job of part time 
	 * @param job
	 */
	public JobParttime addNewJob(JobParttime job){
		try {
		return jobRepository.save(job);
		} catch (Exception e) {
			logger.error("JobParttimeService.'\n' Method: addNewJob() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @return job list of job part time 
	 */
	public List<JobParttime> getAllJobs(){
		return (List<JobParttime>) jobRepository.findAll();
	}
	/**
	 * 
	 * @param id
	 * @return return a particular job against id
	 */
	public JobParttime getJob(Long parttimeId){
		JobParttime part = null;
		try {
			//isDataExist = areaRepository.findById(id).isPresent();
			part = jobRepository.findById(parttimeId).get();
		} catch (Exception e) {
			logger.error("JobParttimeService.'\n' Method: getJob() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
		return part;
	}
	/**
	 * update job info according job parttimeId 
	 * @param job
	 * @param id
	 */
	public JobParttime update(JobParttime job,Long parttimeId){
		try {
		job.setParttimeJobId(parttimeId);
		return jobRepository.save(job);
		} catch (Exception e) {
			logger.error("JobParttimeService.\n Method: update() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * delete a particular job according job id 
	 * @param id
	 */
	public void delete(Long parttimeId){
		try {
		jobRepository.deleteById(parttimeId);	
		} catch (Exception e) {
			logger.error("JobParttimeService.\n Method: update() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param pageable
	 * @return part time job list
	 */
	public Page<JobParttime> searchJobs(Pageable pageable) {
		return jobRepository.findAll(pageable);
	}
}
