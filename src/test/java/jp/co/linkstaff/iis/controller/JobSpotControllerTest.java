package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import jp.co.linkstaff.iis.model.JobSpot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.service.JobSpotService;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
/**
 * 
 * @author .....
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobSpotControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private JobSpotService jobSpotService;
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getContactEmail() throws Exception{
		JobSpot jobspot = new JobSpot();
		jobspot.setAge("20");
		jobspot.setContactEmail("test@gmail.com");
		jobSpotService.addNewJob(jobspot);
		Long id = jobspot.getSpotJobId();
	   	ResponseEntity<JobSpot> response = restTemplate.getForEntity("/admin/spotjob/"+id, JobSpot.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getContactEmail()).isEqualTo("test@gmail.com");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateSpot() throws Exception{		
		JobSpot sp =  new JobSpot();
		sp.setContactEmail("HELLO@GMAIL.COM");
		sp.setAge("30");		
	   	restTemplate.put("/admin/spotjob/1",sp);
	}
}
