package jp.co.linkstaff.iis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import jp.co.linkstaff.iis.model.Area;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jp.co.linkstaff.iis.repository.AreaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.web.server.LocalServerPort;
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
public class AreaControllerTest {
	@LocalServerPort
    private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private AreaController areaController;
	@Test
    public void contexLoads() throws Exception {
        assertThat(areaController).isNotNull();
    }
	/**
	 * check accurate name against id 
	 * @throws Exception
	 */
	@Test
	public void areaEndPointTest() throws Exception{		
		Area area =  new Area();
		// ...... start test add method .....
        area.setAreaCode("T099");
        area.setName("Saitama");
        areaController.addArea(area);
        // ...... end test add method
		Long id = area.getAreaId();
		// ...... start test put method .....
		area.setName("Tokyo");
		restTemplate.put("/area/"+id, area);
		 // ...... end test put method
		ResponseEntity<Area> response = restTemplate.getForEntity("/area/"+id, Area.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   	assertThat(response.getBody().getName()).isEqualTo("Tokyo");
	   	// test soft delete method (isDeleted field becomes true)
	   	areaController.deleteArea(id);
	   	// test hard delete (delete from database)
	   	areaRepository.delete(area);	   	
	}
}