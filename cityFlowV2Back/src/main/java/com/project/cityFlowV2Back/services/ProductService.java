package com.project.cityFlowV2Back.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cityFlowV2Back.entities.Product;
import com.project.cityFlowV2Back.entities.User;
import com.project.cityFlowV2Back.repos.ProductRepository;
import com.project.cityFlowV2Back.request.ProductCreateRequest;
import com.project.cityFlowV2Back.request.ProductUpdateRequest;
import com.project.cityFlowV2Back.responses.ProductResponse;


@Service
public class ProductService {
	
	private ProductRepository productRepository;
	private UserService userService;

	public ProductService(ProductRepository productRepository,UserService userService) {
		this.productRepository = productRepository;
		this.userService = userService;
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public List<Product> getProductsByUserId(Long userId){
		return productRepository.findByUserId(userId);
	}
	
	public ProductResponse getOneProducts(Long productId) {
		Product product = productRepository.findById(productId).orElse(null);
		return new ProductResponse(product);
	}
	
	public Product getOneProductId(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}
	
	public Product saveOneProduct(Product newSaveProduct){
		return productRepository.save(newSaveProduct);
	}
	
	public void deleteById(Long deleteProduct){
		productRepository.deleteById(deleteProduct);
	}
	
	public Product createOneProduct(ProductCreateRequest productCreateRequest) {
		User user = userService.getOneUserById(productCreateRequest.getUserId());
		if (user != null) {
			Product toSave = new Product();
			toSave.setId(productCreateRequest.getId());
			toSave.setExplanation(productCreateRequest.getExplanation());
			toSave.setProductName(productCreateRequest.getProductName());
			toSave.setUser(user);
			toSave.setLocation(productCreateRequest.getLocation());
			toSave.setCreatedAt(LocalDateTime.now());
			return productRepository.save(toSave);
		}		
		else {
			return null;
		}
	}
	
	public Product updateOneProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
	    // Mevcut ürünü ID'ye göre kontrol et
	    Product existingProduct = productRepository.findById(productId).orElse(null);
	    if (existingProduct == null) {
	        // Eğer ürün yoksa, null döndür
	        return null;
	    }
	    
	    User user = userService.getOneUserById(productUpdateRequest.getUserId());
	    

	    if (user != null) {
	        // Mevcut ürünü güncelle
	        existingProduct.setExplanation(productUpdateRequest.getExplanation());
	        existingProduct.setProductName(productUpdateRequest.getProductName());
	        existingProduct.setCreatedAt(LocalDateTime.now());
	        // Güncellenmiş ürünü kaydet ve döndür
	        return productRepository.save(existingProduct);
	    } else {
	        // Kategori, alt kategori veya marka eksikse null döndür
	        return null;
	    }
	}
	
}