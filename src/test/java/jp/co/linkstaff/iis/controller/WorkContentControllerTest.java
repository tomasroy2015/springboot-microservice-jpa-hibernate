package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.WorkContent;
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
public class WorkContentControllerTest{
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private WorkContentController workContentController;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(workContentController).isNotNull();
    }

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getName() throws Exception{
		WorkContent workContent = new WorkContent();
		workContent.setContentName("Test");
		workContent.setCode("A01");
		workContentController.addWork(workContent);
		Long id = workContent.getWorkcontentId();
	   	ResponseEntity<WorkContent> response = restTemplate.getForEntity("/workcontent/" + id, WorkContent.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getContentName()).isEqualTo("Test");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateWorkContent() throws Exception{			
		WorkContent workContent =  new WorkContent();
		workContent.setContentName("testdata");
		workContent.setCode("test");
	   	restTemplate.put("/workcontent/6",workContent);
	}
}
