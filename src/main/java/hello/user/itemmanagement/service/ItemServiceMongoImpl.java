package hello.user.itemmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hello.user.itemmanagement.exception.BusinessException;
import hello.user.itemmanagement.exception.ERR_CODES;
import hello.user.itemmanagement.model.ItemObject;
import hello.user.itemmanagement.model.ItemRepository;

@Service
@Qualifier("mongodb")
public class ItemServiceMongoImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
		
	@Override
	public ItemObject createItem(ItemObject item) throws Exception {
			
		//validate if item already exists
		ItemObject item1 = itemRepository.findById(item.getId());
		if(item1 != null)	
			throw new BusinessException(ERR_CODES.ITEM_EXISTS, "Id", "Item already exists");
		
		return itemRepository.insert(item);
				
	}

	@Override
	public ItemObject updateItem(ItemObject item) throws Exception {
		
		return itemRepository.save(item);
		
	}

	@Override
	public Boolean deleteItem(Long itemId) throws Exception {
		ItemObject existingItem = itemRepository.findById(itemId);
		if(existingItem != null) {
			itemRepository.delete(existingItem);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public ItemObject fetchItem(Long itemId) throws Exception {
		ItemObject user1 = itemRepository.findById(itemId);
		return user1;
	}
}
