package com.quick.start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.quick.start.controllers.ProductController;
import com.quick.start.domain.Product;
import com.quick.start.services.ProductService;


import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;


@WebMvcTest(controllers = { ProductController.class })
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@BeforeEach
	public void setup() {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		when(productService.getAllProducts()).thenReturn(products);
		when(productService.getProductById(1)).thenReturn(new Product());
	}

	@Test
	public void testGetAllProducts() throws Exception {
		mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(view().name("products"))
				.andExpect(model().attribute("products", hasSize(2)));

	}

	@Test
	public void testGetProductById() throws Exception {
		mockMvc.perform(get("/product/1")).andExpect(status().isOk()).andExpect(view().name("product"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));

	}

	@Test
	public void testEdit() throws Exception {
		mockMvc.perform(get("/product/edit/1")).andExpect(status().isOk()).andExpect(view().name("productform"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));

	}

	@Test
	public void testNew() throws Exception {
		mockMvc.perform(get("/product/new")).andExpect(status().isOk()).andExpect(view().name("productform"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
		Mockito.verifyNoInteractions(productService);
		;
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		Integer id = 1;
		String description = "Product 1";
		BigDecimal price = new BigDecimal("55.5");
		String imageUrl = "imagecom";
		Product product = new Product(id, description, price, imageUrl);
		when(productService.saveOrUpdate(Mockito.any(Product.class))).thenReturn(product);

		mockMvc.perform(post("/product").param("id", "2").param("description", description)
				.param("price", price.toString()).param("imageUrl", imageUrl)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/product/1"));

		// vérifier si springMvcController a bien mapper l'objet en se basant sur le
		// webAction
		/*
		 * .param("id","2") .param("description", description) 
		 * .param("price", price.toString()) .param("imageUrl", imageUrl)
		 */
		ArgumentCaptor<Product> boundedProduct = ArgumentCaptor.forClass(Product.class);
		Mockito.verify(productService).saveOrUpdate(boundedProduct.capture());
		assertEquals(2, boundedProduct.getValue().getId());
	}

	@Test
	public void testDelete() throws Exception {
		Integer id = 1;

		mockMvc.perform(get("/product/delete/1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/products"));

		Mockito.verify(productService, Mockito.times(1)).removeProduct(id);
	}

}
