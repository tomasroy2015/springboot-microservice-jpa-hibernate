package jp.co.linkstaff.iis.controller;

import java.util.List;
import org.springframework.hateoas.Link;
import jp.co.linkstaff.iis.model.JobShiftPattern;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.utils.ObjectNotFoundException;
import jp.co.linkstaff.iis.service.JobShiftPatternService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * 
 * @author ....
 *
 */
@RequestMapping(value = "/shiftpattern")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
public class JobShiftPatternController {
	
	@Autowired
	private JobShiftPatternService jobShiftPatternService;
	/**
	 * add new instance of jobShiftPattern
	 * @param jobShiftPattern
	 * @return new instance
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public JobShiftPattern addJobShiftPattern(@RequestBody JobShiftPattern jobShiftPattern){
		try {
		   return jobShiftPatternService.addNewJobShiftPattern(jobShiftPattern);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * get total list of ShiftPattern
	 * @return shift
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET, produces = { "application/hal+json" })
	public List<JobShiftPattern> getList() {
		  List<JobShiftPattern> shift = jobShiftPatternService.getAllJobShiftPattern();
		  for (JobShiftPattern jobShiftPattern : shift) {
			  Long jobShiftPatternId = jobShiftPattern.getJobShiftPatternId();
	          Link selfLink = linkTo(JobShiftPatternController.class).slash(jobShiftPatternId).withSelfRel();
	          jobShiftPattern.add(selfLink);
	      }
		  return shift;
	}
	/**
	 * get those JobShiftPattern list which is not deleted 
	 * @return shift
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/original/list")
	public List<JobShiftPattern> getJobShiftPatternOriginalList(){
		List<JobShiftPattern> shift =  null;
		try {
			shift = jobShiftPatternService.getAllOriginalJobShiftPattern();		
		} catch (Exception e) {
			throw new ServerErrorException(e.getMessage());
		}
		return shift;
	}
	/**
	 * get JobShiftPattern instance according jobShiftPatternId
	 * @param jobShiftPatternId
	 * @return shift
	 */

	@RequestMapping(value = "/{jobShiftPatternId}")
	public JobShiftPattern getJobShiftPattern(@PathVariable Long jobShiftPatternId) {
		JobShiftPattern shift = jobShiftPatternService.getJobShiftPattern(jobShiftPatternId);
		if (shift == null)
			throw new ObjectNotFoundException(" JobShiftPattern not found of id-" + jobShiftPatternId);
		return shift;
	}	
	/**
	 * @param JobShiftPattern
	 * @param jobShiftPatternId
	 * @return updated instance
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{jobShiftPatternId}")
	public JobShiftPattern updateJobShiftPattern(@RequestBody JobShiftPattern jobShiftPattern, @PathVariable Long jobShiftPatternId){
		try {
			return jobShiftPatternService.updateJobShiftPattern(jobShiftPattern, jobShiftPatternId);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * we will no delete any data permanently 
	 * if we want to delete single instance then just update isDeleted = true
	 * @param jobShiftPatternId
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/del/{jobShiftPatternId}")
	public void deleteJobShiftPattern(@PathVariable Long jobShiftPatternId) {
		try {
			jobShiftPatternService.deleteJobShiftPattern(jobShiftPatternId);
		} catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}

}
