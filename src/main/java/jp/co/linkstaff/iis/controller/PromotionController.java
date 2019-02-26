package jp.co.linkstaff.iis.controller;

import java.util.List;
import jp.co.linkstaff.iis.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.service.PromotionService;
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
@RequestMapping(value = "/promotionjob")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController

public class PromotionController {	
	@Autowired
	private PromotionService promotionService;
	/**
	 * 
	 * @param promotionservice
	 */
//	@Autowired
//	public PromotionController(PromotionService promotionservice) {
//		this.promotionService = promotionservice;
//	}

	/**
	 * add a new instance of promotion job
	 * @param promotion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public void addPromotion(@RequestBody Promotion promotion) {
		try {
			promotionService.addNewPromotion(promotion);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}

	/**
	 * 
	 * @return promotion list 
	 */
	@RequestMapping(value = "/list")
	public List<Promotion> getList() {
		return promotionService.getAllPromotion();
	}
	/**
	 * fetch promotion job info according promotionId
	 * @param id
	 * @return promotion
	 */
	@RequestMapping(value = "/{promotionId}")
	public Promotion getPromotion(@PathVariable Long promotionId) {
		Promotion promotion = promotionService.getPromotion(promotionId);
		if (promotion == null)
			throw new ObjectNotFoundException("Promotion not found of id-" + promotionId);
		return promotion;
	}
	/**
	 * update promotion job information
	 * @param promotion
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{promotionId}")
	public void updatePromotion(@RequestBody Promotion promotion, @PathVariable Long promotionId) {
		try {
			promotionService.updatePromotion(promotion, promotionId);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}

	}	
	/**
	 * delete a particular job according id
	 * @param promotionId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{promotionId}")
	public void deletePromotion(@PathVariable Long promotionId) {
		try {
			promotionService.deletePromotion(promotionId);
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
	public Page<Promotion> searchJobList(@PathVariable int page,@PathVariable int pageSize) {
	    Pageable pageable = new PageRequest(page, pageSize);
	    Page<Promotion> jobs = promotionService.searchJobs(pageable);
	    return jobs;
	}
}
