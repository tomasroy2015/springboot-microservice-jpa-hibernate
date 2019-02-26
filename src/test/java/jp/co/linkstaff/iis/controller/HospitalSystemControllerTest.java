package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.model.HospitalSystem;
import jp.co.linkstaff.iis.repository.HospitalSystemRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HospitalSystemControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private HospitalSystemController hospitalSystemController;
	@Autowired
	private HospitalSystemRepository hospitalSystemRepository;
	@Test
    public void contexLoads() throws Exception {
        assertThat(hospitalSystemController).isNotNull();
    }
	
	@Test
	public void hospitalSystemEndPointTest() throws Exception{		
		HospitalSystem hospitalSystem =  new HospitalSystem();
		// ...... start test add method .....
		hospitalSystem.setHospitalSystemName("Clinic");
		hospitalSystem.setHospitalSystemCode("H01");
		hospitalSystemController.addHospitalSystem(hospitalSystem);
        // ...... end test add method
		Long id = hospitalSystem.getHospitalSystemId();
		// ...... start test put method .....
		hospitalSystem.setHospitalSystemName("hi");
		restTemplate.put("/hospitalsystem/"+id, hospitalSystem);
		 // ...... end test put method
		ResponseEntity<HospitalSystem> response = restTemplate.getForEntity("/hospitalsystem/" + id, HospitalSystem.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getHospitalSystemName()).isEqualTo("hi");
	   	// test soft delete method (isDeleted field becomes true)
		hospitalSystemController.deleteHospitalSystem(id);
	   	// test hard delete (delete from database)
		hospitalSystemRepository.delete(hospitalSystem);	   	
	}
	
	
	
}
