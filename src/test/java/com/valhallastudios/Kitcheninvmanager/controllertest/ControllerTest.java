package com.valhallastudios.Kitcheninvmanager.controllertest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.valhallastudios.Kitcheninvmanager.model.Product;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	//test for create
	@Test
	void createCarTest() throws Exception{
		String testProductAsJSON = this.mapper.writeValueAsString(new Product(null, "bacon", 3, 8));
		String testProductAsJSONResponse = this.mapper.writeValueAsString(new Product(3, "bacon", 3, 8));
		
		RequestBuilder request = MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON).content(testProductAsJSON);
		ResultMatcher status = MockMvcResultMatchers.status().isCreated();
		ResultMatcher content = MockMvcResultMatchers.content().json(testProductAsJSONResponse);
		this.mvc.perform(request).andExpect(status).andExpect(content);
	}
	//test for delete
	@Test 
	void deleteProductTest() throws Exception {
		RequestBuilder delete = MockMvcRequestBuilders.delete("/products/1");
		ResultMatcher status = MockMvcResultMatchers.status().isOk();
		
		this.mvc.perform(delete).andExpect(status);
	}
	//test for update
	@Test 
	void updateProductTest() throws Exception {
		String updatedProductAsJSON = this.mapper.writeValueAsString(new Product(2, "milkshake", 5, 2));
		
		RequestBuilder request = MockMvcRequestBuilders.put("/products/2").contentType(MediaType.APPLICATION_JSON).content(updatedProductAsJSON);
		
		ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher content = MockMvcResultMatchers.content().json(updatedProductAsJSON);
		
		this.mvc.perform(request).andExpect(status).andExpect(content);
	}
	//test for get
	@Test
	void getAllProductsTest() throws Exception {
		String listOfProductsAsJSON = this.mapper.writeValueAsString(List.of(
				new Product(1, "eggs", 2, 12),
				new Product(2, "chips", 1, 30)
				));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/products");
		
		ResultMatcher status = MockMvcResultMatchers.status().isOk();
		ResultMatcher content = MockMvcResultMatchers.content().json(listOfProductsAsJSON);
		
		
		this.mvc.perform(request).andExpect(status).andExpect(content);
	}
	
	
}
