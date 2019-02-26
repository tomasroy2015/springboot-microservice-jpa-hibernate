package jp.co.linkstaff.iis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.hateoas.Link;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jp.co.linkstaff.iis.model.JobFulltime;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.linkstaff.iis.service.InquiryService;
import jp.co.linkstaff.iis.service.JobFulltimeService;

import jp.co.linkstaff.iis.utils.ServerErrorException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 
 * @author .....
 *
 */
@RequestMapping(value = "/admin/fulltimejob")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
@CrossOrigin
public class JobFulltimeController {	
	private static final Logger logger = LogManager.getLogger(InquiryService .class);
	@Autowired
	private JobFulltimeService jobFullTimeService;
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~~~~~*/
	//ADD: OK
	/**
	 * POST Method: Insert single instance of JobFullTime 
	 * @param JobFullTime
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<JobFulltime> addJobFulltime(@RequestBody JobFulltime fullTimeJob) {
		JobFulltime savedInstance = null;
		try {
			savedInstance = jobFullTimeService.addJobFulltime(fullTimeJob);
			return new ResponseEntity<>(savedInstance,HttpStatus.OK);
		} catch (Exception e) {
			savedInstance = new JobFulltime();
			throw new ServerErrorException(e.getMessage());
		}
	}
	//GET: all jobFulltime list OK
		@RequestMapping(method = RequestMethod.GET, value = "/list")
		public ResponseEntity<List<JobFulltime>> getAllJobFulltimeJob(){
			List<JobFulltime> fullTimeJobs =  new ArrayList<JobFulltime>();
			try {
				fullTimeJobs = jobFullTimeService.getAllJobFulltime();		//Nullpointer exception proof (not yet fully tested)	
			} catch (Exception e) {
				//logger.error(String.format("List is empty."+e.getMessage()));
				throw new ServerErrorException(e.getMessage());
			}			
			return new ResponseEntity<>(fullTimeJobs,HttpStatus.OK);
		}
		@RequestMapping(method = RequestMethod.GET, value = "/original/list")
		public ResponseEntity<List<JobFulltime>> getJobFulltimeOriginalList(){
				List<JobFulltime> jobFulltime = jobFullTimeService.getAllOriginalJobFulltimeList();		
			    return new ResponseEntity<>(jobFulltime,HttpStatus.OK);
		}
		//OK
		//GET: Single instance of JobFulltime
		@RequestMapping(method = RequestMethod.GET, value = "/{fulltimeJobId}")
		public ResponseEntity<JobFulltime> getJobFulltime(@PathVariable Long fulltimeJobId) {
			JobFulltime	fullTimeJob = null;		
			try {
				fullTimeJob = (JobFulltime) jobFullTimeService.getJobFulltime(fulltimeJobId);
			} catch (Exception e){
//				logger.error(String.format("Nothing found by id: "+id+"\n"+e.getMessage()));
				throw new ServerErrorException(e.getMessage());
			}		
			return new ResponseEntity<>(fullTimeJob,HttpStatus.OK);
		}	
		
	//UPDATE: OK
	@RequestMapping(method = RequestMethod.PUT, value = "/{fulltimeJobId}")
	public ResponseEntity<JobFulltime> updateJob(@PathVariable Long fulltimeJobId, @RequestBody JobFulltime fullTimeJob) {
		JobFulltime savedInstance = null;
		try {
			savedInstance = jobFullTimeService.updateJobFulltime(fulltimeJobId, fullTimeJob);
			return new ResponseEntity<>(savedInstance,HttpStatus.OK);
		} catch (Exception e) {
			savedInstance = new JobFulltime();
			throw new ServerErrorException(e.getMessage());
//			logger.error(String.format("JobFullTime by id: "+id+"couldn't be updated\n"+e.getMessage()));
		}
	}
	
	//Delete: OK
	@RequestMapping(method = RequestMethod.DELETE, value = "/{fulltimeJobId}")
	public ResponseEntity<JobFulltime> deleteJobFulltime(@PathVariable Long fulltimeJobId) {
		try {
			JobFulltime fulltime = jobFullTimeService.deleteJobFulltime(fulltimeJobId);
			return new ResponseEntity<>(fulltime,HttpStatus.OK);
		} catch(Exception e) {
			throw new ServerErrorException("Error occured due to Database operation failure.");
		}
	}
	
	//GET: all jobFulltime within page OK
	@RequestMapping(method = RequestMethod.GET , value ="/page/{pageNo}/{pageSize}", produces = {"application/hal+json"})
	public Page<JobFulltime> getJobFulltimeList(@PathVariable int pageNo, @PathVariable int pageSize) {
		Page<JobFulltime> jobFulltimePage = null;
		
		try {
			jobFulltimePage = (Page<JobFulltime>) jobFullTimeService.getAllJobFulltime(pageNo, pageSize);	//Possibly Nullpointer exception proof.	PageRequest.Of()  method Implementation handle null pointer exception	
		} catch (Exception e) {
//			logger.error(String.format("No page found.\n"+e.getMessage()));
			throw new ServerErrorException(e.getMessage());
		}
		
		
//		if( !jobFulltimePage.isEmpty() || jobFulltimePage != null) { //null checking might not be necessary. 
//			for(JobFulltime fullTimeJob : jobFulltimePage) {
//				Long jobId = fullTimeJob.getJobFulltimeId();
//				
//				//Creating link for each area object in the list by inquiryId;
//				Link selfLink = linkTo(JobFulltimeController.class).slash(jobId).withSelfRel();
//				fullTimeJob.add(selfLink);
//			}			
//		}
		
		return jobFulltimePage;
	}
	
	
}
