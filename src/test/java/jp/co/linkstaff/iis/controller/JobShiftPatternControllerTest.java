package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.model.JobShiftPattern;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobShiftPatternControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private JobShiftPatternController jobShiftPatternController;
	@Test
  public void contexLoads() throws Exception {
      assertThat(jobShiftPatternController).isNotNull();
  }
	@Test
	public void getJobShiftPatternName() throws Exception{
		JobShiftPattern jobShiftPattern = new JobShiftPattern();
		jobShiftPattern.setJobShiftPatternCode("E09");
		jobShiftPattern.setJobShiftPatternName("GFXNHGFHGFH");
		jobShiftPattern.setAmPm("DSFDSF");
		jobShiftPatternController.addJobShiftPattern(jobShiftPattern);
		Long id = jobShiftPattern.getJobShiftPatternId();	
	   	ResponseEntity<JobShiftPattern> response = restTemplate.getForEntity("/shiftpattern/" + id, JobShiftPattern.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getJobShiftPatternName()).isEqualTo("GFHGFHGF");
	}
	@Test
	public void updateJobShiftPattern() throws Exception{	
		JobShiftPattern jobShiftPattern =  new JobShiftPattern();
		jobShiftPattern.setJobShiftPatternCode("E09");
	   	restTemplate.put("/shiftpattern/1",jobShiftPattern);
	}
}
