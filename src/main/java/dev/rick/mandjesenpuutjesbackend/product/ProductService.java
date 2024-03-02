package dev.rick.mandjesenpuutjesbackend.product;

import dev.rick.mandjesenpuutjesbackend.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductConverter converter;
    private final ProductRepository productRepo;


    public List<ProductOutputDTO> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return converter.convertToDTOList(allProducts);
    }

    public ProductOutputDTO getProductByName(String productName) {
        Product foundProduct = findProductByName(productName);
        if (foundProduct != null) {
            return converter.convertToDTO(foundProduct);
        } else {
            throw new RecordNotFoundException(productName);
        }
    }

    public ProductOutputDTO addNewProduct(String productName) {
        boolean doesProductNameExist = checkIfProductNameExists(productName);
        if (doesProductNameExist) {
            throw new NameIsTakenException(productName);
        } else {
            Product newProduct = new Product();
            newProduct.setProductName(productName);
            productRepo.save(newProduct);

            return converter.convertToDTO(newProduct);
        }
    }

    public void deleteProductByProductName(String productName) {
        Product foundProduct = findProductByName(productName);
        if (foundProduct != null) {
            productRepo.deleteById(foundProduct.getProductId());
        } else {
            throw new RecordNotFoundException(productName);
        }
    }

    public Product findProductByName(String productName) {
        Optional<Product> optionalProduct = productRepo.findProductByProductName(productName);
        return optionalProduct.orElse(null);
    }

    public boolean checkIfProductNameExists(String productName) {
        Optional<Product> optionalProduct = productRepo.findProductByProductName(productName);
        return optionalProduct.isPresent();
    }


}
