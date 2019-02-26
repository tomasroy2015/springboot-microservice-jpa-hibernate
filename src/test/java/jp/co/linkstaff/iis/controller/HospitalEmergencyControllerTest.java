package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.model.HospitalEmergency;
import jp.co.linkstaff.iis.repository.HospitalEmergencyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HospitalEmergencyControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private HospitalEmergencyController hospitalEmergencyController;
	@Autowired
	private HospitalEmergencyRepository hospitalEmergencyRepository;
	@Test
    public void contexLoads() throws Exception {
        assertThat(hospitalEmergencyController).isNotNull();
    }

	@Test
	public void hospitalEmergencyEndPointTest() throws Exception{		
		HospitalEmergency hospitalEmergency =  new HospitalEmergency();
		// ...... start test add method .....
		hospitalEmergency.setHospitalEmergencyCode("E12");
		hospitalEmergency.setHospitalEmergencyName("Test");
		hospitalEmergencyController.addHospitalEmergency(hospitalEmergency);
        // ...... end test add method
		Long id = hospitalEmergency.getHospitalEmergencyId();
		// ...... start test put method .....
		hospitalEmergency.setHospitalEmergencyName("hi");
		restTemplate.put("/hospitalemergency/"+id, hospitalEmergency);
		 // ...... end test put method
		ResponseEntity<HospitalEmergency> response = restTemplate.getForEntity("/hospitalemergency/" + id, HospitalEmergency.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getHospitalEmergencyName()).isEqualTo("hi");
	   	// test soft delete method (isDeleted field becomes true)
		hospitalEmergencyController.deleteHospitalEmergency(id);
	   	// test hard delete (delete from database)
		hospitalEmergencyRepository.delete(hospitalEmergency);	   	
	}
}
