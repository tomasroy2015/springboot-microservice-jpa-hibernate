package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.JobFulltime;
import jp.co.linkstaff.iis.repository.JobFulltimeRepository;

import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
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
public class JobFulltimeControllerTest {
	@LocalServerPort
    private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private JobFulltimeController jobFulltimeController;
	@Autowired
	private JobFulltimeRepository jobFulltimeRepository;
	@Test
    public void contexLoads() throws Exception {
        assertThat(jobFulltimeController).isNotNull();
    }
	@Test
	public void jobFulltimeEndPointTest() throws Exception{		
		JobFulltime jobfulltime =  new JobFulltime();
		// ...... start test add method .....
		jobfulltime.setContactEmail("hello@gmail.com");
		jobfulltime.setJobCode("J01");
		jobFulltimeController.addJobFulltime(jobfulltime);
        // ...... end test add method
		Long id = jobfulltime.getFulltimeJobId();
		// ...... start test put method .....
		jobfulltime.setJobCode("J001");
		restTemplate.put("/admin/fulltimejob/"+id, jobfulltime);
		 // ...... end test put method
		ResponseEntity<JobFulltime> response = restTemplate.getForEntity("/admin/fulltimejob/"+id, JobFulltime.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getContactEmail()).isEqualTo("hello@gmail.com");
	   	// test soft delete method (isDeleted field becomes true)
	   	jobFulltimeController.deleteJobFulltime(id);
	   	// test hard delete (delete from database)
	   	jobFulltimeRepository.delete(jobfulltime);	   	
	}
	
	
	
	
	
	
	
}
