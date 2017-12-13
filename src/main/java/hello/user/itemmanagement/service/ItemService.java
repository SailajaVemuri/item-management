package hello.user.itemmanagement.service;

import hello.user.itemmanagement.model.ItemObject;

public interface ItemService {
	public ItemObject createItem(ItemObject user) throws Exception;
	public ItemObject updateItem(ItemObject user) throws Exception;
	public Boolean deleteItem(String userId) throws Exception;
	public ItemObject fetchItem(String userId) throws Exception;
}
