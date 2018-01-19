package hello.user.itemmanagement.service;

import hello.user.itemmanagement.exception.BusinessException;
import hello.user.itemmanagement.exception.ERR_CODES;
import hello.user.itemmanagement.model.ItemObject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("list")
public class ItemServiceImpl implements ItemService {
	
	public List<ItemObject> itemList = new ArrayList<ItemObject>();

	@Override
	public ItemObject createItem(ItemObject user) throws Exception{
		for(ItemObject existingUser : itemList){
			if(existingUser.getId().equals(user.getId())){
				throw new BusinessException(ERR_CODES.ITEM_EXISTS, "Id", "Item already exists");	
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
	public Boolean deleteItem(Long itemId) throws Exception{
		for(ItemObject existingItem : itemList){
			if(existingItem.getId().equals(itemId)){
				itemList.remove(existingItem);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public ItemObject fetchItem(Long userId) throws Exception {
		for(ItemObject existingUser : itemList){
			if(existingUser.getId().equals(userId)){
				return existingUser;
			}
		}
		return null;
	}

}
