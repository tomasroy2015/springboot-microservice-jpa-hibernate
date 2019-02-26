package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import jp.co.linkstaff.iis.model.Notice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NoticeControllerTest{
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private NoticeController noticeController;
	/**
	 * 
	 * @throws Exception
	 */
	@Test
    public void contexLoads() throws Exception {
        assertThat(noticeController).isNotNull();
    }
	/**
	 * Firstly add a instance with type(private or public) and then test that data
	 * 
	 * @throws Exception
	 */
	@Test
	public void getName() throws Exception{
		Notice notice = new Notice();
		notice.setName("Hello World");
		notice.setType("Private");
		noticeController.addNotice(notice);
		Long id = notice.getNoticeId();	
	   	ResponseEntity<Notice> response = restTemplate.getForEntity("/notice/" + id, Notice.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getName()).isEqualTo("Hello World");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateNotice() throws Exception{	
		Notice notice =  new Notice();
		notice.setName("HI");
		notice.setType("private");
	   	restTemplate.put("/notice/1",notice);
	}
}
