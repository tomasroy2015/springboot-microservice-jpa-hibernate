package jp.co.linkstaff.iis.service;

import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import jp.co.linkstaff.iis.model.WorkContent;
import jp.co.linkstaff.iis.repository.WorkContentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkContentServiceTest {
	@Mock
	WorkContentRepository repository;
	@InjectMocks
	WorkContentService service;
	private static final long ID = 1;
	
	
	@Test
	public void findByIdTest() {
//		service.getWork(ID);
//		verify(repository).findById(ID);
	}
	@Test
	public void getAllTest() {
		service.getAllWork();
		verify(repository).findAll();		
	}
	@Test
	public void saveTest() {
		WorkContent workContent = mock(WorkContent.class);
		service.addNewWork(workContent);
		verify(repository).save(workContent);
	}
	@Test
	public void deleteTest() {
		service.deleteWork(ID);
		verify(repository).deleteById(ID);
	}
}
