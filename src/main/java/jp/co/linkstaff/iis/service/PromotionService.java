package jp.co.linkstaff.iis.service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import jp.co.linkstaff.iis.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author .....
 *
 */
@Service
public class PromotionService {
	private static final Logger logger = LogManager.getLogger( PromotionService.class);
	@Autowired
	private PromotionRepository promotionRepository;
	/**
	 * 
	 * @param promotionRepository
	 */
	// @Autowired
	// public PromotionService( PromotionRepository promotionRepository) {
	// 	this.promotionRepository = promotionRepository;
	// }
	/**
	 * add new promotion
	 * @param promotion
	 */
	public Promotion addNewPromotion(Promotion promotion) {
		try {
			return promotionRepository.save(promotion);
		} catch (Exception e) {
			logger.error("PromotionService.'\n' Method: addNewPromotion() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * fetch promotion list 
	 * @return list
	 */
	public List<Promotion> getAllPromotion() {
		List<Promotion> list = (List<Promotion>) promotionRepository.findAll();
		return list;
	}
	/**
	 * get a particular promotion according promotionId
	 * @param promotionId
	 * @return promotion 
	 */
	public Promotion getPromotion(Long promotionId) {
		boolean isDataExist = false;
		Promotion promotion = null;
		try {
			isDataExist = promotionRepository.findById(promotionId).isPresent();
			promotion = isDataExist ? promotionRepository.findById(promotionId).get() : null;
		} catch (Exception e) {
			logger.error("PromotionService.'\n' Method: getPromotion() '\n' Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
		return promotion;
	}		
	/**
	 * updated promotion info according promotion id
	 * @param promotion
	 * @param promotionId
	 */
	public Promotion updatePromotion(Promotion promotion, Long promotionId) {		
		try {
			promotion.setPromotionId(promotionId);
			return promotionRepository.save(promotion);
		} catch (Exception e) {
			logger.error("PromotionService.\n Method: updatePromotion() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * delete a promotion info according id
	 * @param id
	 */
	public void deletePromotion(Long promotionId) {		
		try {
			promotionRepository.deleteById(promotionId);
		} catch (Exception e) {
			logger.error("PromotionService.\n Method: deletePromotion() \n Error Details:" + e.getMessage());
			throw new ServerErrorException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param pageable
	 * @return promotion list
	 */
	public Page<Promotion> searchJobs(Pageable pageable) {
		return promotionRepository.findAll(pageable);
	}
	
}