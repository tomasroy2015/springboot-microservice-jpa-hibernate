package jp.co.linkstaff.iis.service;

import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import jp.co.linkstaff.iis.model.Promotion;
import jp.co.linkstaff.iis.repository.PromotionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PromotionServiceTest {
	@Mock
	PromotionRepository repository;
	@InjectMocks
	PromotionService service;
	private static final long ID = 1;
	
	
	@Test
	public void findByIdTest() {
		service.getPromotion(ID);
		verify(repository).findById(ID);
	}
	@Test
	public void getAllTest() {
		service.getAllPromotion();
		verify(repository).findAll();		
	}
	@Test
	public void saveTest() {
		Promotion promotion = mock(Promotion.class);
		service.addNewPromotion(promotion);
		verify(repository).save(promotion);
	}
	@Test
	public void deleteTest() {
		service.deletePromotion(ID);
		verify(repository).deleteById(ID);
	}
}
