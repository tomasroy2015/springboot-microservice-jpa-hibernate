package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import jp.co.linkstaff.iis.model.Subject;
import org.springframework.http.HttpStatus;
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
public class SubjectControllerTest{
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private SubjectController subjectController;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(subjectController).isNotNull();
    }

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getName() throws Exception{
		Subject sub = new Subject();
		sub.setSubjectName("Test");
		sub.setSubjectCode("s01");
		subjectController.addSubject(sub);
		Long id = sub.getSubjectId();	
	   	ResponseEntity<Subject> response = restTemplate.getForEntity("/subject/" + id, Subject.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getSubjectCode()).isEqualTo("three");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateSubject() throws Exception{	
		
		Subject subject =  new Subject();
		subject.setSubjectCode("testdata");
		subject.setSubjectName("HI");
	   	restTemplate.put("/subject/6",subject);
	}
}
