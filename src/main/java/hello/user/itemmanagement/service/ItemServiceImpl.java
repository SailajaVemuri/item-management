package hello.user.itemmanagement.service;

import hello.user.itemmanagement.exception.BusinessException;
import hello.user.itemmanagement.exception.ERR_CODES;
import hello.user.itemmanagement.model.ItemObject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
	
	public List<ItemObject> itemList = new ArrayList<ItemObject>();

	@Override
	public ItemObject createItem(ItemObject user) throws Exception{
		for(ItemObject existingUser : itemList){
			if(existingUser.getId().equals(user.getId())){
				throw new BusinessException(ERR_CODES.USER_EXISTS, "Id", "User already exists");	
			}
		}
		itemList.add(user);
		return user;

	}

	@Override
	public ItemObject updateItem(ItemObject item) throws Exception{
		for(ItemObject existingItem : itemList){
			if(existingItem.getId().equals(item.getId())){
				existingItem.setItemName(item.getItemName());
				existingItem.setQuantity(item.getQuantity());
				existingItem.setPrice(item.getPrice());
				return existingItem;
			}
		}
		return null;
	}

	@Override
	public Boolean deleteItem(String itemId) throws Exception{
		for(ItemObject existingItem : itemList){
			if(existingItem.getId().equals(itemId)){
				itemList.remove(existingItem);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public ItemObject fetchItem(String userId) throws Exception {
		for(ItemObject existingUser : itemList){
			if(existingUser.getId().equals(userId)){
				return existingUser;
			}
		}
		return null;
	}

}
