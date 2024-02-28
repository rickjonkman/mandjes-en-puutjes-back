package dev.rick.mandjesenpuutjesbackend.product;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/groceries")
public class ProductController {


    private final ProductService productService;


    @GetMapping("/open/get-all-products")
    public ResponseEntity<List<ProductOutputDTO>> getAllProducts() {
        List<ProductOutputDTO> outputDTOList = productService.getAllProducts();
        return ResponseEntity.ok(outputDTOList);
    }


}
