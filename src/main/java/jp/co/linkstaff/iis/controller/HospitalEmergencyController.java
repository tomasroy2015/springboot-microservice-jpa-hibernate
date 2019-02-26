package jp.co.linkstaff.iis.controller;

import java.util.List;
import org.springframework.hateoas.Link;
import jp.co.linkstaff.iis.model.HospitalEmergency;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.utils.ObjectNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import jp.co.linkstaff.iis.service.HospitalEmergencyService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author ....
 *
 */
@RequestMapping(value = "/hospitalemergency")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
public class HospitalEmergencyController {	
	@Autowired
	private HospitalEmergencyService hospitalEmergencyService;
	/**
	 * add new instance of hospitalEmergency
	 * @param hospitalEmergency
	 * @return new instance
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<HospitalEmergency> addHospitalEmergency(@RequestBody HospitalEmergency hospitalEmergency){
		try {
			HospitalEmergency emergency = hospitalEmergencyService.addNewHospitalEmergency(hospitalEmergency);
			return new ResponseEntity<>(emergency,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
	/**
	 * get total list of hospitalEmergency
	 * @return emergency
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET, produces = { "application/hal+json" })
	public ResponseEntity<List<HospitalEmergency>> getList() {
		  List<HospitalEmergency> emergency = hospitalEmergencyService.getAllHospitalEmergency();
		  for (HospitalEmergency hospitalEmergency : emergency) {
			  Long hospitalEmergencyId = hospitalEmergency.getHospitalEmergencyId();
	          Link selfLink = linkTo(HospitalEmergencyController.class).slash(hospitalEmergencyId).withSelfRel();
	          hospitalEmergency.add(selfLink);
	      }
		  return  new ResponseEntity<>(emergency,HttpStatus.OK);
	}
	/**
	 * get those HospitalEmergency list which is not deleted 
	 * @return emergency
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/original/list")
	public ResponseEntity<List<HospitalEmergency>> getHospitalEmergencyOriginalList(){
		List<HospitalEmergency> emergency =  hospitalEmergencyService.getAllOriginalHospitalEmergency();
		return new ResponseEntity<>(emergency,HttpStatus.OK);
	}
	/**
	 * get HospitalEmergency instance according hospitalEmergencyId
	 * @param hospitalEmergencyId
	 * @return emergency
	 */
	@RequestMapping(value = "/{hospitalEmergencyId}")
	public ResponseEntity<HospitalEmergency> getHospitalEmergency(@PathVariable Long hospitalEmergencyId) {
		HospitalEmergency emergency = hospitalEmergencyService.getHospitalEmergency(hospitalEmergencyId);
		if ( emergency == null)
			throw new ObjectNotFoundException(" HospitalEmergency not found of id-" + hospitalEmergencyId);
		return  new ResponseEntity<>(emergency,HttpStatus.OK);
	}	
	/**
	 * @param hospitalEmergency
	 * @param hospitalEmergencyId
	 * @return updated instance
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{hospitalEmergencyId}")
	public ResponseEntity<HospitalEmergency> updateHospitalEmergency(@RequestBody HospitalEmergency hospitalEmergency, @PathVariable Long hospitalEmergencyId){
		try {
			HospitalEmergency emergency =  hospitalEmergencyService.updateHospitalEmergency(hospitalEmergency, hospitalEmergencyId);
			return  new ResponseEntity<>(emergency,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * we will no delete any data permanently 
	 * if we want to delete single instance then just update isDeleted = true
	 * @param hospitalEmergencyId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "{hospitalEmergencyId}")
	public ResponseEntity<HospitalEmergency> deleteHospitalEmergency(@PathVariable Long hospitalEmergencyId) {
		try {
			HospitalEmergency emergency = hospitalEmergencyService.deleteHospitalEmergency(hospitalEmergencyId);
			return new ResponseEntity<>(emergency,HttpStatus.OK);
		} catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
}
