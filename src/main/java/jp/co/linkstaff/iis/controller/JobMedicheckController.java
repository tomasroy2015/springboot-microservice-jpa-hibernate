package jp.co.linkstaff.iis.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import jp.co.linkstaff.iis.model.JobMedicheck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.service.JobMedicheckService;
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
@RequestMapping(value = "/admin/medicheckjob")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
public class JobMedicheckController {
	 @Autowired
    private JobMedicheckService jobService;
    /**
     * 
     * @param jobService
     */
//    @Autowired
//    public JobMedicheckController(JobMedicheckService jobService) {
//    	this.jobService = jobService;
//
//    }  
    /**
     * add a new instance of medicheck job. 
     * @param job
     */
    @RequestMapping(method=RequestMethod.POST,value="/add")
	public void addJob(@RequestBody JobMedicheck job) {
    	try {
		jobService.addNewJob(job);	
    	} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}  
    /**
     * 
     * @return job list
     */
    @RequestMapping(method = RequestMethod.GET,value="/list")
	public List<JobMedicheck> getList() {
	   return jobService.getAllJobs();
	}
	/**
	 * 
	 * @param id
	 * @return particular job according id.
	 */
	@RequestMapping(value="/{medicheckId}")
    public JobMedicheck getJob(@PathVariable Long medicheckId) {
		JobMedicheck job = jobService.getJob(medicheckId);		
		if (job == null)
			throw new ObjectNotFoundException("Job not found of id-" + medicheckId);
		return job;
    }
	
	/**
	 * update job info of medicheck against medicheckId
	 * @param job
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.PUT,value="/{medicheckId}")
	public void updateJob(@RequestBody JobMedicheck job,@PathVariable Long medicheckId) {
		try {
		jobService.update(job, medicheckId);	
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}			
	/**
	 * delete the particular instance against medicheckId 
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="/{medicheckId}")
	public void deleteJob(@PathVariable Long medicheckId) {
		try {
		jobService.delete(medicheckId);
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
	public Page<JobMedicheck> searchJobList(@PathVariable int page,@PathVariable int pageSize) {
	    Pageable pageable = new PageRequest(page, pageSize);
	    Page<JobMedicheck> jobs = jobService.searchJobs(pageable);
	    return jobs;
	}

}
