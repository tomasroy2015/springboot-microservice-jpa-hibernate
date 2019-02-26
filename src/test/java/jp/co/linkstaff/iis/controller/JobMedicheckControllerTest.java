package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.JobMedicheck;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
/**
 * 
 * @author .....
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobMedicheckControllerTest {
	@LocalServerPort
    private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired

	private JobMedicheckController jobMedicheckController;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(jobMedicheckController).isNotNull();
    }

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getContactEmail() throws Exception{
		JobMedicheck jobMedicheck = new JobMedicheck();
		jobMedicheck.setContactEmail("hello@gmail.com");
		jobMedicheck.setAge("20");
		jobMedicheckController.addJob(jobMedicheck);
		Long id = jobMedicheck.getMedicheckJobId();
	   	ResponseEntity<JobMedicheck> response = restTemplate.getForEntity("/admin/medicheckjob/" + id, JobMedicheck.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getContactEmail()).isEqualTo("hello@gmail.com");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateMedicheck() throws Exception{		
		JobMedicheck mc =  new JobMedicheck();
		mc.setContactEmail("test@gmail.com");
		mc.setAge("100");
	   	restTemplate.put("/admin/medicheckjob/1",mc);

	}

}
