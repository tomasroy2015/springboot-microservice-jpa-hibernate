package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.Department;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import jp.co.linkstaff.iis.repository.DepartmentRepository;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DepartmentControllerTest {
	@LocalServerPort
    private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DepartmentController departmentController;
	@Test
    public void contexLoads() throws Exception {
        assertThat(departmentController).isNotNull();
    }
	/**
	 * check accurate name against id 
	 * @throws Exception
	 */
	@Test
	public void departmentEndPointTest() throws Exception{		
		Department department =  new Department();
		// ...... start test add method .....
		department.setDepartmentCode("D01");
		department.setDepartmentName("Medical");
		departmentController.addDepartment(department);
        // ...... end test add method
		Long id = department.getDepartmentId();
		// ...... start test put method .....
		department.setDepartmentName("Technical");
		restTemplate.put("/department/"+id, department);
		 // ...... end test put method
		ResponseEntity<Department> response = restTemplate.getForEntity("/department/"+id, Department.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getDepartmentName()).isEqualTo("Technical");
	   	// test soft delete method (isDeleted field becomes true)
	   	departmentController.deleteDepartment(id);
	   	// test hard delete (delete from database)
	   	departmentRepository.delete(department);	   	
	}
}
