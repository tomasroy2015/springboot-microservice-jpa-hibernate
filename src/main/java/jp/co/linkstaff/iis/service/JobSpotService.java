package jp.co.linkstaff.iis.service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import jp.co.linkstaff.iis.model.JobSpot;
import org.apache.logging.log4j.LogManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.repository.JobSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author .....
 *
 */
@Service
public class JobSpotService {
	private static final Logger logger = LogManager.getLogger(JobSpotService .class);
	@Autowired
    private JobSpotRepository jobRepository;
	/**
	 * 
	 * @param jobRepository
	 */
	// @Autowired
	// public JobSpotService(JobSpotRepository jobRepository) {
	// 	this.jobRepository = jobRepository;
	// }
	/**
	 * add a new job 
	 * @param job
	 */
	public JobSpot addNewJob(JobSpot job){
		try {
		return jobRepository.save(job);
		} catch (Exception e) {
			logger.error("JobSpotService.'\n' Method: addNewJob() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @return job list of spot 
	 */
	public List<JobSpot> getAllJobs(){
		return (List<JobSpot>) jobRepository.findAll();
	}
	/**
	 * 
	 * @param id
	 * @return a particular job according spotjobId
	 */
	public JobSpot getJob(Long spotjobId){
		JobSpot spot = null;
		try {
			//isDataExist = areaRepository.findById(id).isPresent();
			spot = jobRepository.findById(spotjobId).get();
		} catch (Exception e) {
			logger.error("JobSpotService.'\n' Method: getJob() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
		return spot;				
	}
	/**
	 * update job info according id 
	 * @param job
	 * @param id
	 */
	public JobSpot update(JobSpot job,Long spotjobId){
		try {
		job.setSpotJobId(spotjobId);
		return jobRepository.save(job);
		} catch (Exception e) {
			logger.error("JobSpotService.\n Method: update() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * delete a particular job according job spotjobId 
	 * @param id
	 */
	public void delete(Long spotjobId){
		try {
		jobRepository.deleteById(spotjobId);
		} catch (Exception e) {
			logger.error("JobMedicheckService.\n Method: delete() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param pageable
	 * @return list of spot job 
	 */
	public Page<JobSpot> searchJobs(Pageable pageable) {
		return jobRepository.findAll(pageable);
	}
}
