package jp.co.linkstaff.iis.controller;

import java.util.List;
import jp.co.linkstaff.iis.model.Area;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.service.AreaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jp.co.linkstaff.iis.utils.ServerErrorException;
import jp.co.linkstaff.iis.utils.ObjectNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
/**
 * 
 * @author .....
 *
 */
@RequestMapping(value = "/area")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
@CrossOrigin
public class AreaController {
	@Autowired
	private AreaService areaService;
	/**
	 * "area":{
		"areaId" : 1
	}
	 * persist new instance
	 * @param area
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<Area> addArea(@RequestBody Area area) {
		try {
			Area areas = areaService.addNewArea(area);
			return new ResponseEntity<>(areas,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException("Error occured due to Database operation failure.");
		}
	}
	/**
	 * fetch List<Area> areas
	 * @return areas
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET, produces = { "application/hal+json" })
	public ResponseEntity<List<Area>> getList() {  
		  List<Area> areas = areaService.getAllArea();
		  for (Area area : areas) {
			  Long areaId = area.getAreaId();
	          Link selfLink = linkTo(AreaController.class).slash(areaId).withSelfRel();
	          area.add(selfLink);
	      }
		  return new ResponseEntity<>(areas,HttpStatus.OK);
	}
	/**
	 * get those area(list) which is not deleted 
	 * @return area
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/original/list")
	public ResponseEntity<List<Area>> getAreaOriginalList(){
			List<Area> area = areaService.getAllOriginalArea();		
		    return new ResponseEntity<>(area,HttpStatus.OK);
	}
	/**	
	 * fetch area info according area id
	 * @param id
	 * @return area
	 */
	@RequestMapping(value = "/{areaId}")
	public ResponseEntity<Area> getArea(@PathVariable Long areaId) {
		Area area = areaService.getArea(areaId);
		if (area == null)
			throw new ObjectNotFoundException("Area not found of id-" + areaId);
		return new ResponseEntity<>(area,HttpStatus.OK);
	}	
	/**
	 * update area info against area id
	 * @param area
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{areaId}")
	public ResponseEntity<Area> updateArea(@RequestBody Area area, @PathVariable Long areaId) {
		try {
			Area areas = areaService.updateArea(area, areaId);
			return new ResponseEntity<>(areas,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
	/**
	 * we will no delete any data permanently 
	 * if we want to delete single instance then just update isDeleted = true
	 * @param areaId
	 * @return areas
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{areaId}")
	public ResponseEntity<Area>  deleteArea(@PathVariable Long areaId) {
		try {
			Area areas = areaService.deleteArea(areaId);
			return new ResponseEntity<>(areas,HttpStatus.OK);
		} catch(Exception ex) {
			throw new ServerErrorException("Error occured due to Database operation failure.");
		}
	}
	/**
	 * @param page
	 * @param pageSize
	 * @return areas
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.GET,value = "/search/{page}/{pageSize}")
	public Page<Area> searchJobList(@PathVariable int page,@PathVariable int pageSize) {
	    Pageable pageable = new PageRequest(page, pageSize);
	    Page<Area> jobs = areaService.searchAreas(pageable);
	    return jobs;
	}
}
