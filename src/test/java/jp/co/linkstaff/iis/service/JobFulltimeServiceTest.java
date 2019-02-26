package jp.co.linkstaff.iis.service;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.linkstaff.iis.repository.JobFulltimeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobFulltimeServiceTest {
	@MockBean
	JobFulltimeRepository dataServiceMock;
	
	@Autowired
	JobFulltimeService businessImpl;
	@Test
	public void testFindMyjob() {
//		when(dataServiceMock.findById((long) 2).get().getContact_email()).thenReturn("tomas.cse.ju@gmail.com");
//		assertEquals("tomas.cse.ju@gmail.com", businessImpl.getJob((long) 2).getContact_email());
	}
}
