package jp.co.linkstaff.iis.controller;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.Department;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.service.DepartmentService;
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

@RequestMapping(value = "/department")
@EnableHypermediaSupport(type = HypermediaType.HAL)
@RestController
@CrossOrigin
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	/**
	 * persist a new instance of department
	 * @param department
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		try {
			Department dep = departmentService.addNewDepartment(department);
			return new ResponseEntity<>(dep,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
	/**
	 * fetch List<Department> departments
	 * @return departments
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = { "application/hal+json" })
	public ResponseEntity<List<Department>> getList() {
		List<Department> departments = departmentService.getAllOriginalDepartment();	
		for (Department department : departments) {
			Long departmentId = department.getDepartmentId();
			Link selfLink = linkTo(DepartmentController.class).slash(departmentId).withSelfRel();
			department.add(selfLink);
		}
		return  new ResponseEntity<>(departments,HttpStatus.OK);
	}
	/**
	 * get those department(list) which is not deleted 
	 * @return dept
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/original/list")
        public ResponseEntity<List<Department>> getDepartmentOriginalList(){
	    List<Department> dept = departmentService.getAllOriginalDepartment();		
        return new ResponseEntity<>(dept,HttpStatus.OK);
	}
	 
	/**
	 * fetch department info according department id
	 * @param id
	 * @return department
	 */
	@RequestMapping(value = "/{departmentId}")
	public ResponseEntity<Department> getDepartment(@PathVariable Long departmentId) {
		Department department = departmentService.getDepartment(departmentId);

		if (department == null)
			throw new ObjectNotFoundException("Department not found of id-" + departmentId);
		return  new ResponseEntity<>(department,HttpStatus.OK);
	}
	/**
	 * update department info against department id
	 * @param department
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{departmentId}")
	public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable Long departmentId) {
		try {
			Department dept = departmentService.updateDepartment(department, departmentId);
			return  new ResponseEntity<>(dept,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
	/**
	 * we will no delete any data permanently 
	 * if we want to delete single instance then just update isDeleted = true
	 * @param departmentId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{departmentId}")
	public ResponseEntity<Department> deleteDepartment(@PathVariable Long departmentId) {
		try {
			Department dept = departmentService.deleteDepartment(departmentId);
			return new ResponseEntity<>(dept,HttpStatus.OK);
		} catch (Exception ex) {
			throw new ServerErrorException("Error occured due to Database operation failure.");
		}
	}
}
