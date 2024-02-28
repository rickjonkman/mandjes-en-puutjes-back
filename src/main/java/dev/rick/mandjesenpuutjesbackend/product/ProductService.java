package dev.rick.mandjesenpuutjesbackend.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }


}
