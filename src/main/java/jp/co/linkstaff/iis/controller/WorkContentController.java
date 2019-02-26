package jp.co.linkstaff.iis.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import jp.co.linkstaff.iis.model.WorkContent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.service.WorkContentService;
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
@RequestMapping(value = "/workcontent")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
public class WorkContentController {	
	@Autowired
	private WorkContentService workService;
	/**
	 * 
	 * @param workservice
	 */
//	@Autowired
//	public WorkContentController(WorkContentService workservice) {
//		this.workService = workservice;
//	}
	
	//#61 OK
	/**
	 * insert a new instance of work content
	 * @param work
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public void addWork(@RequestBody WorkContent work) {
		try {
			workService.addNewWork(work);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
	/**
	 * 
	 * @return list of work content
	 */

	@RequestMapping(value = "/list")
	public List<WorkContent>getList() {
		return workService.getAllWork();
	}
	
	//#62 
	/**
	 * 
	 * @param id
	 * @return  work
	 */
	@RequestMapping(value = "/{workcontentId}")
	public WorkContent getWork(@PathVariable Long workcontentId) {
		WorkContent work = workService.getWork(workcontentId);
		if (work == null)
			throw new ObjectNotFoundException("Work not found of id-" + workcontentId);
		return work;
	}
	

	/**
	 * update work content info
	 * @param work
	 * @param workcontentId
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workcontentId}")
	public void updateWork(@RequestBody WorkContent work, @PathVariable Long workcontentId) {
		try {
			workService.updateWork(work, workcontentId);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}

	}	
	/**
	 * delete a particular instance against id
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{workcontentId}")
	public void deleteWork(@PathVariable Long workcontentId) {
		try {
			workService.deleteWork(workcontentId);
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
	public Page<WorkContent> searchJobList(@PathVariable int page,@PathVariable int pageSize) {
	    Pageable pageable = new PageRequest(page, pageSize);
	    Page<WorkContent> jobs = workService.searchJobs(pageable);
	    return jobs;
	}
}
