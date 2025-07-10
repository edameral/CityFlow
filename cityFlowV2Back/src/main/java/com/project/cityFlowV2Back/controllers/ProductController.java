package com.project.cityFlowV2Back.controllers;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cityFlowV2Back.entities.Product;
import com.project.cityFlowV2Back.request.ProductCreateRequest;
import com.project.cityFlowV2Back.request.ProductUpdateRequest;
import com.project.cityFlowV2Back.responses.ProductResponse;
import com.project.cityFlowV2Back.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	private final GeometryFactory geometryFactory = new GeometryFactory();
	
    // Poligon koordinatları (longitude, latitude sırasıyla)
    private final Coordinate[] coords = new Coordinate[] {
        new Coordinate(29.975679182946493, 39.424692568585925),
        new Coordinate(29.979812101053483, 39.42281031171265),
        new Coordinate(29.981513939545863, 39.42514157608079),
        new Coordinate(29.977782439487754, 39.4268961980587),
        new Coordinate(29.975728252692164, 39.42469830108931),
        new Coordinate(29.975679182946493, 39.424692568585925) // kapalı poligon
    };
    
    private final Polygon polygon;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.polygon = geometryFactory.createPolygon(coords);
    }
    
    @GetMapping("/check-location/{productId}")
    public boolean checkProductLocation(@PathVariable Long productId) {
        Product product = productService.getOneProductId(productId);
        if (product == null || product.getLocation() == null) {
            return false;
        }

        String locationStr = product.getLocation(); // örn: "latitude,longitude" formatında olabilir
        String[] parts = locationStr.split(",");
        if (parts.length != 2) return false;

        try {
            double latitude = Double.parseDouble(parts[0].trim());
            double longitude = Double.parseDouble(parts[1].trim());

            // GeoJSON ve JTS için sıralama: Coordinate(longitude, latitude)
            Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

            return polygon.contains(point);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @GetMapping("/check-locations")
    public List<ProductResponse> checkAllProductsLocation() {
        List<Product> allProducts = productService.getAllProducts();

        return allProducts.stream()
            .filter(product -> {
                if (product.getLocation() == null) return false;
                String[] parts = product.getLocation().split(",");
                if (parts.length != 2) return false;
                try {
                    double latitude = Double.parseDouble(parts[0].trim());
                    double longitude = Double.parseDouble(parts[1].trim());
                    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
                    return polygon.contains(point);
                } catch (NumberFormatException e) {
                    return false;
                }
            })
            .map(ProductResponse::new)
            .toList();
    }
	
	@GetMapping
	public List<Product> getAllProduct(){
		System.out.println("tüm ürünler listelendi.");
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public ProductResponse getOneProduct(@PathVariable long productId){
		return productService.getOneProducts(productId);
	}
	
	@PostMapping
	public Product getOneCreateProduct(@RequestBody ProductCreateRequest productCreateRequest){
		System.out.println("veri eklendi");
		return productService.createOneProduct(productCreateRequest);
	}
	
	@DeleteMapping("/{productId}")
	public void deleteOneProduct(@PathVariable long productId){
		System.out.println("veri silindi mi");
		productService.deleteById(productId);
	}
	
	@PutMapping("/{productId}")
	public Product updateOneProduct(@PathVariable Long productId,@RequestBody ProductUpdateRequest request) {
		return productService.updateOneProduct(productId, request);
	}
	
	@GetMapping("/user/{userId}")
	public List<Product> getProductByUserId(@PathVariable Long userId){
		System.out.println("categorye göre liste");
		return productService.getProductsByUserId(userId);
	}
	
}
