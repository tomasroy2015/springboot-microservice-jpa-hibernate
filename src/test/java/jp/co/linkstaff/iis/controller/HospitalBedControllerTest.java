package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.HospitalBed;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import jp.co.linkstaff.iis.repository.HospitalBedRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HospitalBedControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private HospitalBedRepository hospitalBedRepository;
	@Autowired
	private HospitalBedController hospitalBedController;
	@Test
  public void contexLoads() throws Exception {
      assertThat(hospitalBedController).isNotNull();
  }	
	@Test
	public void hospitalBedEndPointTest() throws Exception{		
		HospitalBed hospitalBed =  new HospitalBed();
		// ...... start test add method .....
		hospitalBed.setHospitalBedCode("D01");
		hospitalBed.setBedGeneral(10);
		hospitalBedController.addHospitalBed(hospitalBed);
        // ...... end test add method
		Long id = hospitalBed.getHospitalBedId();
		// ...... start test put method .....
		hospitalBed.setBedGeneral(20);
		restTemplate.put("/hospitalbed/"+id, hospitalBed);
		 // ...... end test put method
		ResponseEntity<HospitalBed> response = restTemplate.getForEntity("/hospitalbed/" + id, HospitalBed.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getBedGeneral()).isEqualTo(20);
	   	// test soft delete method (isDeleted field becomes true)
		hospitalBedController.deleteHospitalBed(id);
	   	// test hard delete (delete from database)
		hospitalBedRepository.delete(hospitalBed);	   	
	}
}
