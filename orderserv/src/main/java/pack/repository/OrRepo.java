package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.entity.Orders;


public interface OrRepo extends JpaRepository<Orders, Integer> {

}
