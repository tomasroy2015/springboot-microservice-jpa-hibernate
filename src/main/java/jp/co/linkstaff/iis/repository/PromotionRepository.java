package jp.co.linkstaff.iis.repository;

import jp.co.linkstaff.iis.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * <Promotion,Long> class name and type of primary key
 * @author .....
 *
 */
@Repository
public interface PromotionRepository extends CrudRepository<Promotion,Long> {
	Page<Promotion> findAll(Pageable pageable);
}
