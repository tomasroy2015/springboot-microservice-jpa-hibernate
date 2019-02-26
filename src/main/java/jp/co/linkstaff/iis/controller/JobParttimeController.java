package jp.co.linkstaff.iis.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import jp.co.linkstaff.iis.model.JobParttime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.service.JobParttimeService;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.utils.ObjectNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/admin/parttimejob")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
@CrossOrigin
public class JobParttimeController {
	 @Autowired
	 private JobParttimeService jobService;
	    /**
	     * 
	     * @param jobService
	     */
//	    @Autowired
//	    public JobParttimeController(JobParttimeService jobService) {
//	    	this.jobService = jobService;
//	    }

	    /**
	     * add a new instance of parttime job 
	     * @param job
	     */
	    @RequestMapping(method=RequestMethod.POST,value="/add")
		public void addJob(@RequestBody JobParttime job) {
	    	try {
			jobService.addNewJob(job);	
	    	} catch (Exception ex) {
				throw new ServerErrorException(ex.getMessage());
			}
		}
	    /**
	     * 
	     * @return full job list of parttime job
	     */
	    @RequestMapping(value="/list")
		public List<JobParttime> getList() {
		   return jobService.getAllJobs();
		}
		/**
		 * 
		 * @param id
		 * @return particular parttime job info against job parttimeId 
		 */
		@RequestMapping(value="/{parttimeId}")
	    public JobParttime getJob(@PathVariable Long parttimeId) {
			JobParttime job = jobService.getJob(parttimeId);
			if (job == null)
				throw new ObjectNotFoundException("Job not found of id-" + parttimeId);
			return job;			
	    }
		/**
		 * update a particular job info 
		 * @param job
		 * @param id
		 */
		@RequestMapping(method=RequestMethod.PUT,value="/{parttimeId}")
		public void updateJob(@RequestBody JobParttime job,@PathVariable Long parttimeId) {
			try {
			jobService.update(job, parttimeId);	
			} catch (Exception ex) {
				throw new ServerErrorException(ex.getMessage());
			}
		}	
		/**
		 * delete job info against job id  
		 * @param id
		 */
		@RequestMapping(method=RequestMethod.DELETE,value="/{parttimeId}")
		public void deleteJob(@PathVariable Long parttimeId) {
			try {
			jobService.delete(parttimeId);
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
		@RequestMapping(method = RequestMethod.GET,value = "/job/parttime/search/{page}/{pageSize}")
		public Page<JobParttime> searchJobList(@PathVariable int page,@PathVariable int pageSize) {
		    Pageable pageable = new PageRequest(page, pageSize);
		    Page<JobParttime> jobs = jobService.searchJobs(pageable);
		    return jobs;
		}
}
