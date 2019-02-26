package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.JobParttime;
import org.springframework.http.ResponseEntity;
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
public class JobParttimeControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private JobParttimeController jobParttimeController;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(jobParttimeController).isNotNull();
    }

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getContactEmail() throws Exception{
		JobParttime jobparttime = new JobParttime();
		jobparttime.setAge("20");
		jobparttime.setContactEmail("test@gmail.com");
		jobParttimeController.addJob(jobparttime);
		Long id = jobparttime.getParttimeJobId();
		//act
	   	ResponseEntity<JobParttime> response = restTemplate.getForEntity("/admin/parttimejob/" + id, JobParttime.class);
	    //assert
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getContactEmail()).isEqualTo("test@gmail.com");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateParttime() throws Exception{		
		JobParttime pt =  new JobParttime();
		pt.setContactEmail("HELLO@GMAIL.COM");
		pt.setAge("50");		
	   	restTemplate.put("/admin/parttimejob/1",pt);
	}
}
