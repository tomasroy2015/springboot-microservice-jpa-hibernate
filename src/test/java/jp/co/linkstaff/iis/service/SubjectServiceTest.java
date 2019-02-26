package jp.co.linkstaff.iis.service;

import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import jp.co.linkstaff.iis.model.Subject;
import jp.co.linkstaff.iis.repository.SubjectRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectServiceTest {
	@Mock
	SubjectRepository repository;
	@InjectMocks
	SubjectService service;
	private static final long ID = 1;
	
	
	@Test
	public void findByIdTest() {
		service.getSubject(ID);
		verify(repository).findById(ID);
	}
	@Test
	public void getAllTest() {
		service.getAllSubject();
		verify(repository).findAll();		
	}
	@Test
	public void saveTest() {
		Subject subject = mock(Subject.class);
		service.addNewSubject(subject);
		verify(repository).save(subject);
	}
	@Test
	public void deleteTest() {
		service.deleteSubject(ID);
		verify(repository).deleteById(ID);
	}
}