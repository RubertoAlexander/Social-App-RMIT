//package com.sept.rest.webservices.restfulwebservices;
//
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.http.MediaType;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sept.rest.webservices.restfulwebservices.register.NewUser;
//import com.sept.rest.webservices.restfulwebservices.register.RegistorJpaResource;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(RegistorJpaResource.class)
//public class RegistrationControllerIntergrationTest {
//	
//    @Autowired
//    private MockMvc mvc;
// 
//    @MockBean
//    private RegistorJpaResource service;
//    
//    @Autowired
//    ObjectMapper objectMapper;
//    
//    
//    
////    @Test
////    public void Check_if_posted_json__registors_a_User()  throws Exception {
////    	
////    	
////    	mvc.perform(post("/sign-up").content("{\"username\":\"john\",\"password\":\"password\"}")
////    			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
////    	
////    	
////    }    
//    
//    
//    
//    

}
