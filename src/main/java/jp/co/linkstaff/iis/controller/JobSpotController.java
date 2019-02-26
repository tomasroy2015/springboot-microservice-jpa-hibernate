package jp.co.linkstaff.iis.controller;

import java.util.List;

import jp.co.linkstaff.iis.model.JobSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jp.co.linkstaff.iis.service.JobSpotService;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.utils.ObjectNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
/**
 * 
 * @author .....
 *
 */
@RequestMapping(value = "/admin/spotjob")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController

public class JobSpotController {
	 @Autowired
    private JobSpotService jobService;
    /**
     * 
     * @param jobService
     */
//    @Autowired
//    public JobSpotController(JobSpotService jobService) {
//    	this.jobService = jobService;
//    }

    /**
     * insert a new instance
     * @param job
     */
    @RequestMapping(method=RequestMethod.POST,value="/add")
	public void addJob(@RequestBody JobSpot job) {
    	try {
		jobService.addNewJob(job);
    	} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
    /**
     * 
     * @return full job list
     */
    @RequestMapping(value="/list")
	public List<JobSpot> getList() {
	   return jobService.getAllJobs();
	}
	/**
	 * 
	 * @param id
	 * @return a particular job info against job id.
	 */
	@RequestMapping(value="/{spotjobId}")
    public JobSpot getJob(@PathVariable Long spotjobId) {	
		JobSpot job = jobService.getJob(spotjobId);
		if (job == null)
			throw new ObjectNotFoundException("Job not found of id-" + spotjobId);
		return job;	
    }
	/**
	 * update job info according spot job id 
	 * @param job
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.PUT,value="/{spotjobId}")
	public void updateJob(@RequestBody JobSpot job,@PathVariable Long spotjobId) {
		try {
		jobService.update(job, spotjobId);	
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * delete a particular job info against spot job spotjobId.
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="/{spotjobId}")
	public void deleteJob(@PathVariable Long spotjobId) {
		try {
		jobService.delete(spotjobId);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @return jobs
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.GET,value = "/search/{page}/{pageSize}")
	public Page<JobSpot> searchJobList(@PathVariable int page,@PathVariable int pageSize) {
	    Pageable pageable = new PageRequest(page, pageSize);
	    Page<JobSpot> jobs = jobService.searchJobs(pageable);
	    return jobs;
	}
}
