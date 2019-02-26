package jp.co.linkstaff.iis.controller;

import java.util.List;
import org.springframework.hateoas.Link;
import jp.co.linkstaff.iis.model.HospitalSystem;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.service.HospitalSystemService;
import jp.co.linkstaff.iis.utils.ObjectNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/hospitalsystem")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
public class HospitalSystemController{
	@Autowired
	private HospitalSystemService hospitalSystemService;
	/**
	 * add new instance of hospitalSystem
	 * @param hospitalSystem
	 * @return new instance
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<HospitalSystem> addHospitalSystem(@RequestBody HospitalSystem hospitalSystem){
		try {
			HospitalSystem system = hospitalSystemService.addNewHospitalSystem(hospitalSystem);
			return new ResponseEntity<>(system,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * get total list of HospitalSystem
	 * @return system
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET, produces = { "application/hal+json" })
	public ResponseEntity<List<HospitalSystem>> getList() {
		  List<HospitalSystem> system = hospitalSystemService.getAllHospitalSystem();
		  for (HospitalSystem hospitalSystem : system) {
			  Long hospitalSystemId = hospitalSystem.getHospitalSystemId();
	          Link selfLink = linkTo(HospitalSystemController.class).slash(hospitalSystemId).withSelfRel();
	          hospitalSystem.add(selfLink);
	      }
		  return  new ResponseEntity<>(system,HttpStatus.OK);
	}
	/**
	 * get those HospitalSystem list which is not deleted 
	 * @return system
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/original/list")
	public ResponseEntity<List<HospitalSystem>> getNoticeOriginalList(){
		List<HospitalSystem> system =  hospitalSystemService.getAllOriginalHospitalSystem();;
		return new ResponseEntity<>(system,HttpStatus.OK);
	}
	/**
	 * get hospitalSystem instance according hospitalSystemId
	 * @param hospitalSystemId
	 * @return system
	 */
	@RequestMapping(value = "/{hospitalSystemId}")
	public ResponseEntity<HospitalSystem> getHospitalSystem(@PathVariable Long hospitalSystemId) {
		HospitalSystem system = hospitalSystemService.getHospitalSystem(hospitalSystemId);
		if (system == null)
			throw new ObjectNotFoundException(" HospitalSystem not found of id-" + hospitalSystemId);
		return  new ResponseEntity<>(system,HttpStatus.OK);
	}	
	/**
	 * @param hospitalSystem
	 * @param hospitalSystemId
	 * @return updated instance
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{hospitalSystemId}")
	public ResponseEntity<HospitalSystem> updateHospitalSystem(@RequestBody HospitalSystem hospitalSystem, @PathVariable Long hospitalSystemId){
		try {
			HospitalSystem system = hospitalSystemService.updateHospitalSystem(hospitalSystem, hospitalSystemId);
			return  new ResponseEntity<>(system,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * we will no delete any data permanently 
	 * if we want to delete single instance then just update isDeleted = true
	 * @param hospitalSystemId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "{hospitalSystemId}")
	public ResponseEntity<HospitalSystem> deleteHospitalSystem(@PathVariable Long hospitalSystemId) {
		try {
			HospitalSystem system = hospitalSystemService.deleteHospitalSystem(hospitalSystemId);
			return new ResponseEntity<>(system,HttpStatus.OK);
		} catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
}
