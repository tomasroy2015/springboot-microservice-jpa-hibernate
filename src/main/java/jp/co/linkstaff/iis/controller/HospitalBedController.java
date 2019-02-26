package jp.co.linkstaff.iis.controller;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.HospitalBed;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.service.HospitalBedService;
import jp.co.linkstaff.iis.utils.ServerErrorException;
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

/**
 * 
 * @author ....
 *
 */
@RequestMapping(value = "/hospitalbed")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
public class HospitalBedController {
	@Autowired
	private HospitalBedService hospitalBedService;
	/**
	 * add new instance of HospitalBed
	 * @param hospitalBed
	 * @return new instance
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<HospitalBed> addHospitalBed(@RequestBody HospitalBed hospitalBed){
		try {
			HospitalBed bed = hospitalBedService.addNewHospitalBed(hospitalBed);
			return new ResponseEntity<>(bed,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * get total list of HospitalBed
	 * @return bed
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET, produces = { "application/hal+json" })
	public ResponseEntity<List<HospitalBed>> getList() {
		  List<HospitalBed> bed = hospitalBedService.getAllHospitalBed();
		  for (HospitalBed hospitalBed : bed) {
			  Long hospitalBedId = hospitalBed.getHospitalBedId();
	          Link selfLink = linkTo(HospitalBedController.class).slash(hospitalBedId).withSelfRel();
	          hospitalBed.add(selfLink);
	      }
		  return  new ResponseEntity<>(bed,HttpStatus.OK);
	}
	/**
	 * get those HospitalBed list which is not deleted 
	 * @return bed
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/original/list")
	public ResponseEntity<List<HospitalBed>> getHospitalBedOriginalList(){
		List<HospitalBed> bed = hospitalBedService.getAllOriginalHospitalBed();		
        return new ResponseEntity<>(bed,HttpStatus.OK);
	}
	/**
	 * get hospitalSystem instance according hospitalBedId
	 * @param hospitalBedId
	 * @return bed
	 */
	@RequestMapping(value = "/{hospitalBedId}")
	public ResponseEntity<HospitalBed> getHospitalBed(@PathVariable Long hospitalBedId) {
		HospitalBed bed = hospitalBedService.getHospitalBed(hospitalBedId);
		if (bed == null)
			throw new ObjectNotFoundException(" HospitalBed not found of id-" + hospitalBedId);
		return  new ResponseEntity<>(bed,HttpStatus.OK);
	}	
	/**
	 * @param hospitalBed
	 * @param hospitalBedId
	 * @return updated instance
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{hospitalBedId}")
	public ResponseEntity<HospitalBed> updateHospitalBed(@RequestBody HospitalBed hospitalBed, @PathVariable Long hospitalBedId){
		try {
			HospitalBed bed = hospitalBedService.updateHospitalBed(hospitalBed, hospitalBedId);
			return  new ResponseEntity<>(bed,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}	
	/**
	 * we will no delete any data permanently 
	 * if we want to delete single instance then just update isDeleted = true
	 * @param hospitalBedId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "{hospitalBedId}")
	public ResponseEntity<HospitalBed> deleteHospitalBed(@PathVariable Long hospitalBedId) {
		try {
			HospitalBed bed = hospitalBedService.deleteHospitalBed(hospitalBedId);
			return new ResponseEntity<>(bed,HttpStatus.OK);
		} catch(Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
}
