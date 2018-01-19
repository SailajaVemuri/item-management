package hello.user.itemmanagement.service;

import hello.user.itemmanagement.model.ItemObject;

public interface ItemService {
	public ItemObject createItem(ItemObject item) throws Exception;
	public ItemObject updateItem(ItemObject item) throws Exception;
	public Boolean deleteItem(Long itemId) throws Exception;
	public ItemObject fetchItem(Long itemId) throws Exception;
}
