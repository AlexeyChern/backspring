package pack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import pack.entity.items;

// This will be AUTO IMPLEMENTED by Spring into a Bean called ItRepo
// CRUD refers Create, Read, Update, Delete

public interface ItRepo extends JpaRepository<items, Integer> {

}
