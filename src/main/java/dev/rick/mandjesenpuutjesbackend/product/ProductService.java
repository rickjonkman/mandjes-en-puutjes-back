package dev.rick.mandjesenpuutjesbackend.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductConverter converter;
    private final ProductRepository productRepo;


    public List<ProductOutputDTO> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return converter.convertToDTOList(allProducts);
    }


}
