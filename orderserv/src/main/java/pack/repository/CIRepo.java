package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.entity.carditem;



public interface CIRepo extends JpaRepository<carditem, Integer> {

}
