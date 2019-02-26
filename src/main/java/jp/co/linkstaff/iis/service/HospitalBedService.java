package jp.co.linkstaff.iis.service;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import jp.co.linkstaff.iis.model.HospitalBed;
import org.springframework.stereotype.Service;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.repository.HospitalBedRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HospitalBedService {
	
	  private static final Logger logger = LogManager.getLogger(HospitalBedService.class);
		@Autowired
		private HospitalBedRepository hospitalBedRepository;
		/**
		 * add new instance of HospitalBed using save method
		 * @param hospitalBed
		 */
		public HospitalBed addNewHospitalBed(HospitalBed hospitalBed) {
			try {
				return hospitalBedRepository.save(hospitalBed);
			} catch (Exception e) {
				logger.error("HospitalBedService.'\n' Method: addNewHospitalBed() '\n' Error Details:" + e.getMessage());
				throw new ServerErrorException(e.getMessage());
			}		
		}
		/**
		 * get all HospitalBed list including soft delete
		 * @return list
		 */
		public List<HospitalBed> getAllHospitalBed() {
			List<HospitalBed> list = (List<HospitalBed>) hospitalBedRepository.findAll();
			return list;
		}
		  /**
	     * get those HospitalBed which is not deleted
	     * @return HospitalBed
	     */
		public List<HospitalBed> getAllOriginalHospitalBed(){
			List<HospitalBed> bed = null;
			try {
				bed = hospitalBedRepository.findByIsDeleted(false); 
			} catch (Exception e) {			
				throw new ServerErrorException("Exception occured at Service in getAllOriginalHospitalBed(). "+e);	
			}		
			return bed;
		}
		/**
		 * we can get HospitalBed against hospitalBedId
		 * @param hospitalBedId
		 * @return hospitalBed
		 */
		public HospitalBed getHospitalBed(Long hospitalBedId) {
			HospitalBed hospitalBed = null;
			try {
				hospitalBed = hospitalBedRepository.findById(hospitalBedId).get();
			} catch (Exception e) {
				logger.error("HospitalBedService.'\n' Method: getHospitalBed() '\n' Error Details:" + e.getMessage());
				throw new ServerErrorException(e.getMessage());
			}
			return hospitalBed;
		}
		/**
		 * we can update single instance against hospitalBedId
		 * @param hospitalBed
		 * @param hospitalBedId
		 */
		public HospitalBed updateHospitalBed(HospitalBed hospitalBed, Long hospitalBedId){	
			Date date = new Date();
			try {
				hospitalBed.setHospitalBedId(hospitalBedId);
				hospitalBed.setUpdatedAt(date);
				return hospitalBedRepository.save(hospitalBed);
			} catch (Exception e) {
				logger.error("HospitalBedService.\n Method: updateHospitalBed() \n Error Details:" + e.getMessage());
				throw new ServerErrorException(e.getMessage());
			}
		}
		/**
		 * we will no delete any data permanently
		 * first get all HospitalBed list which is not deleted
		 * after that set isDeleted field = true(for our understanding that this data is deleted)
		 * @param hospitalBedId
		 */		
		public HospitalBed deleteHospitalBed(Long hospitalBedId) {		
			try {
				HospitalBed hospitalBed = hospitalBedRepository.findById(hospitalBedId).get();
				hospitalBed.setIsDeleted(true);
				hospitalBedRepository.save(hospitalBed);
				return hospitalBed;
			} catch (Exception e) {
				logger.error("HospitalBedService.\n Method: deleteHospitalBed() \n Error Details:" + e.getMessage());
				throw new ServerErrorException(e.getMessage());
			}
		}	
}
