package com.quick.start.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quick.start.domain.Product;
import com.quick.start.services.ProductService;

/**
 * @author Aymen Chaaben
 *
 */
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/product/{id}")
	public String getProductById(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "product";
	}

	@RequestMapping("/product/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}

	@RequestMapping("/product/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productform";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveOrUpdateProduct(Product product) {
	
		Product result = productService.saveOrUpdate(product);
		return "redirect:/product/" + result.getId();
	}
	
	@RequestMapping("/product/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.removeProduct(id);
		return "redirect:/products";
	}

}
