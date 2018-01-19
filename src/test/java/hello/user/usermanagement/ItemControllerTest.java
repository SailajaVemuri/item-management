package hello.user.usermanagement;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hello.user.itemmanagement.controller.ItemController;
import hello.user.itemmanagement.exception.BusinessException;
import hello.user.itemmanagement.exception.ERR_CODES;
import hello.user.itemmanagement.model.ItemObject;
import hello.user.itemmanagement.service.ItemService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure =false)
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;
    
       
    ItemObject mockItem = new ItemObject();
	
    String exampleUserJson = "{\"id\":\"123423\",\"fName\":\"John\",\"lName\":\"Matt\",\"email\":\"John.Smith@gmail.com\",\"pinCode\":\"123456\"}";
    
    String resultJson = "{\"resMsg\":\"User created successfully\",\"userId\":\"1234\",\"valErrors\":null}";

    @Test
    public void shouldCreateItem() throws Exception {
    	mockItem.setId(4444L);
    	mockItem.setItemName("John");
    	mockItem.setPrice(25.36f);
    	mockItem.setQuantity(5);

    	
    	Mockito.when(itemService.createItem(Mockito.any(ItemObject.class))).thenReturn(mockItem);
    			
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createUser")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);

			
    	ResultMatcher expectedMsg = MockMvcResultMatchers.jsonPath("resMsg").value("User created successfully");
    	ResultMatcher expectedId =  MockMvcResultMatchers.jsonPath("userId").value("4444");
    	
		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
										.andExpect(expectedMsg)
										.andExpect(expectedId);
		
	    }
    
    
    //Test for user creation failure on using same userid
    @Test
    public void shouldFailCreateItem() throws Exception {
    	
    	BusinessException bre = new BusinessException(ERR_CODES.ITEM_EXISTS, "", "");

    	
    	Mockito.when(itemService.createItem(Mockito.any(ItemObject.class))).thenThrow(bre);
    			
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createUser")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);
   	
    	ResultMatcher expectedMsg = MockMvcResultMatchers.jsonPath("resMsg").value("User creation Failed");
    	ResultMatcher expectedId =  MockMvcResultMatchers.jsonPath("userId").value("123423");
    	
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
										.andExpect(expectedMsg)
										.andExpect(expectedId);
				
    }
    
    @Test
    public void shoutFetchItem() throws Exception{
    	mockItem.setId(4444L);
    	mockItem.setItemName("John");
    	mockItem.setPrice(36.25f);
    	mockItem.setQuantity(5);

    	Mockito.when(itemService.fetchItem(Mockito.anyLong())).thenReturn(mockItem);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/4444").accept(MediaType.APPLICATION_JSON);
    	    	
    	
    	ResultMatcher expectedId =  MockMvcResultMatchers.jsonPath("id").value("4444");
    	ResultMatcher expectedfName = MockMvcResultMatchers.jsonPath("fName").value("John");
    	ResultMatcher expectedEmail = MockMvcResultMatchers.jsonPath("email").value("test@test.com");
    	
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
										.andExpect(expectedId)
										.andExpect(expectedfName)
										.andExpect(expectedEmail);
    	    	
    }
    
    @Test
    public void shouldUpdateItem() throws Exception {
    	mockItem.setId(4444L);
    	mockItem.setItemName("John");
    	mockItem.setPrice(5.23f);
    	mockItem.setQuantity(5);

    	
    	Mockito.when(itemService.updateItem(Mockito.any(ItemObject.class))).thenReturn(mockItem);
    			
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateUser")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);

			
    	ResultMatcher expectedMsg = MockMvcResultMatchers.jsonPath("resMsg").value("User updated successfully");
    	ResultMatcher expectedId =  MockMvcResultMatchers.jsonPath("userId").value("4444");
    	
		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
										.andExpect(expectedMsg)
										.andExpect(expectedId);
		
    }
    
    @Test
    public void shouldDeleteItem() throws Exception {
    	mockItem.setId(4444L);
    	mockItem.setItemName("John");
    	mockItem.setPrice(45.36f);
    	mockItem.setQuantity(5);

    	
    	Mockito.when(itemService.deleteItem(Mockito.anyLong())).thenReturn(Boolean.TRUE);
    			
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteUser/4444");
				
    	ResultMatcher expectedMsg = MockMvcResultMatchers.jsonPath("resMsg").value("User deleted successfully");
    	ResultMatcher expectedId =  MockMvcResultMatchers.jsonPath("userId").value("4444");
    	
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
										.andExpect(expectedMsg)
										.andExpect(expectedId);
		
    }

   
}
