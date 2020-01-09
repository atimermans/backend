package proyecto_final.backend;

import org.springframework.data.repository.CrudRepository;

import proyecto_final.backend.Item;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ItemRepository extends CrudRepository<Item, Integer> {

}