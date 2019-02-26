package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import jp.co.linkstaff.iis.model.Promotion;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.service.PromotionService;
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
public class PromotionControllerTest{
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PromotionService promotionService;
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getName() throws Exception{		
		Promotion prom =  new Promotion();
		prom.setCaregoryTitle("Category1");
		prom.setPromotionName("new promotion");
		
		promotionService.addNewPromotion(prom);
		Long id = prom.getPromotionId();		
	   	ResponseEntity<Promotion> response = restTemplate.getForEntity("/promotionjob/"+id, Promotion.class);
	   	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getPromotionName()).isEqualTo("new promotion");
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updatePromotion() throws Exception{		
		Promotion promotion =  new Promotion();
		promotion.setPromotionName("one");		
	   	restTemplate.put("/promotionjob/6",promotion);
	}
}
