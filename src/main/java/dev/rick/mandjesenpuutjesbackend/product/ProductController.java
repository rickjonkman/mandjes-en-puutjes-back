package dev.rick.mandjesenpuutjesbackend.product;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;


    @GetMapping("/open/get-all-products")
    public ResponseEntity<List<ProductOutputDTO>> getAllProducts() {
        List<ProductOutputDTO> outputDTOList = productService.getAllProducts();
        return ResponseEntity.ok(outputDTOList);
    }

    @GetMapping("/open/get-product")
    public ResponseEntity<ProductOutputDTO> getProductByName(@RequestParam String productName) {
        ProductOutputDTO outputDTO = productService.getProductByName(productName);
        return ResponseEntity.ok(outputDTO);
    }

    @PostMapping("/open/add-new-product")
    public ResponseEntity<ProductOutputDTO> addNewProduct(@RequestParam String productName) {
        ProductOutputDTO outputDTO = productService.addNewProduct(productName);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/groceries/open/get-product?"+outputDTO.getProductName())
                .toUriString());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/open/delete-product")
    public ResponseEntity<?> deleteProductByProductName(@RequestParam String productName) {
        productService.deleteProductByProductName(productName);
        return ResponseEntity.noContent().build();
    }


}
