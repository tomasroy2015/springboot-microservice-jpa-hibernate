package jp.co.linkstaff.iis.service;

import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.co.linkstaff.iis.repository.PrefectureRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PrefectureServiceTest {
	@Mock
	PrefectureRepository repository;
	@InjectMocks
	PrefectureService service;
	private static final long ID = 1;
	
	
	@Test
	public void findByIdTest() {
		service.getPrefecture(ID);
		verify(repository).findById(ID);
	}
	@Test
	public void getAllTest() {
		service.getAllPrefecture();
		verify(repository).findAll();		
	}

	@Test
	public void deleteTest() {
		service.deletePrefecture(ID);
		verify(repository).deleteById(ID);
	}
}
