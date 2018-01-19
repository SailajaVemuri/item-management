package hello.user.itemmanagement.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<ItemObject, Long> {
	
	ItemObject findById(Long id);
}
