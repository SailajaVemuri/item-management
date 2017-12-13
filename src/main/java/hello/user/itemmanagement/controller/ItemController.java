package hello.user.itemmanagement.controller;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.user.itemmanagement.exception.BusinessException;
import hello.user.itemmanagement.model.ItemObject;
import hello.user.itemmanagement.service.ItemService;

@RestController
public class ItemController {
	private final ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService){
		this.itemService = itemService;
	}
	
	@Autowired
	 ServletContext context;
	
	@GetMapping("/items/{itemId}")
	public ResponseEntity<?> fetchItem(@PathVariable String itemId){
		
		ItemObject user = null;
		try {
			user = itemService.fetchItem(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null)		
			return new ResponseEntity<ItemObject>(user, HttpStatus.OK);
		else {
			ResponseObject resObj = new ResponseObject();
			resObj.setResMsg("Does not exist");
			resObj.setUserId(itemId);
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.OK);
		}
			
	}
	
	@PostMapping("/createItem")
	public ResponseEntity<ResponseObject> createItem(@Valid @RequestBody ItemObject item){
		ResponseObject resObj = new ResponseObject();
		ItemObject itemCreated  = null;
		try{
			itemCreated = itemService.createItem(item);
		}
		catch(BusinessException e){
			resObj.setResMsg("Item creation Failed");
			resObj.setUserId(item.getId());
			resObj.addValError(new ValError(e.getErrCode(), e.getErrField(), e.getErrMsg()));
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(itemCreated != null){			
			resObj.setResMsg("Item created successfully");
			resObj.setUserId(itemCreated.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.CREATED);
		}else{			
			resObj.setResMsg("Item creation failed");
			resObj.setUserId(item.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
		}
				
	}
	
	@PostMapping("/updateItem")
	public ResponseEntity<ResponseObject> updateItem(@Valid @RequestBody ItemObject item, RedirectAttributes redirectAttributes){
		
		ItemObject userUpdated = null;
		try {
			userUpdated = itemService.updateItem(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseObject resObj;
		if(userUpdated != null){
			resObj = new ResponseObject();
			resObj.setResMsg("Item updated successfully");
			resObj.setUserId(userUpdated.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.CREATED);
		}else
		{
			resObj = new ResponseObject();
			resObj.setResMsg("Item does not exist");
			resObj.setUserId(item.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@DeleteMapping("/deleteItem/{itemId}")
	public ResponseEntity<ResponseObject> deleteItem(@PathVariable String itemId, RedirectAttributes redirectAttributes){
		Boolean res = null;
		try {
			res = itemService.deleteItem(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == Boolean.TRUE){
			ResponseObject resObj = new ResponseObject();
			resObj.setResMsg("Item deleted successfully");
			resObj.setUserId(itemId);
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.OK);
		}else
		{
			ResponseObject resObj = new ResponseObject();
			resObj.setResMsg("Item does not exist");
			resObj.setUserId(itemId);
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
		}
		
		
			
		
	}

}