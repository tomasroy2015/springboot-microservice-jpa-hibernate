package jp.co.linkstaff.iis.service;

import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import jp.co.linkstaff.iis.model.Area;
import jp.co.linkstaff.iis.repository.AreaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AreaServiceTest {
	@Mock
	AreaRepository repository;
	@InjectMocks
	AreaService service;
	private static final long ID = 1;
	
	
	@Test
	public void findByIdTest() {
//		service.getArea(ID);
//		verify(repository).findById(ID);
	}
	@Test
	public void getAllTest() {
		service.getAllArea();
		verify(repository).findAll();		
	}
	@Test
	public void saveTest() {
		Area area = mock(Area.class);
		service.addNewArea(area);
		verify(repository).save(area);
	}
	@Test
	public void deleteTest() {
		service.deleteArea(ID);
		verify(repository).deleteById(ID);
	}
}
