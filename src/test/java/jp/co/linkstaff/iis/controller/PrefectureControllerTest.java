package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

import jp.co.linkstaff.iis.model.Area;
import jp.co.linkstaff.iis.model.Prefecture;
import jp.co.linkstaff.iis.service.AreaService;

import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest;
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
public class PrefectureControllerTest{
	@Autowired
	private TestRestTemplate restTemplate;
	// @Autowired
	// private PrefectureService prefectureservice;
	@Autowired
	private AreaService areaService;

	
	@Autowired
	private PrefectureController prefectureController;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(prefectureController).isNotNull();
    }
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getName() throws Exception{			
		                  Area area =  new Area();
		                  area.setAreaCode("A01");
		                  
		                  Prefecture pref =  new Prefecture();
		                  pref.setName("test");
		                  pref.setPrefectureCode ("P012");
//		                  pref.setArea(area);
		                  areaService.addNewArea(area);
		                  //pref.setArea(area);
				          //prefectureservice.savePrefecture(pref);
				          Long id = pref.getPrefectureId();	
			   	          ResponseEntity<Prefecture> response = restTemplate.getForEntity("/prefecture/"+id, Prefecture.class);
			   	          System.err.println("zzzzz "+response.getBody().getName());
			   	          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			   	          assertThat(response.getBody().getName()).isEqualTo("test");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void updatePrefecture() throws Exception{	
//		
//		Prefecture prefecture =  new Prefecture();
//		prefecture.setName("one");
//		prefecture.setPref_code("A01");
//	   	restTemplate.put("/prefecture/6",prefecture);
//	}
}
