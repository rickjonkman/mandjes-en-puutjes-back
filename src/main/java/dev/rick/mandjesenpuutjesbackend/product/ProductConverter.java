package dev.rick.mandjesenpuutjesbackend.product;

import dev.rick.mandjesenpuutjesbackend.exceptions.NullPointerException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {


    public List<ProductOutputDTO> convertToDTOList(List<Product> products) {
        List<ProductOutputDTO> outputDTOList = new ArrayList<>();
        for (Product product : products) {
            ProductOutputDTO converted = convertToDTO(product);
            outputDTOList.add(converted);
        }
        return outputDTOList;
    }

    public List<Product> convertToProductList(List<ProductInputDTO> productsDTO) {
        List<Product> productList = new ArrayList<>();
        for (ProductInputDTO inputDTO : productsDTO) {
            Product converted = convertToProduct(inputDTO);
            productList.add(converted);
        }
        return productList;
    }

    public Product convertToProduct(ProductInputDTO inputDTO) {
        if (inputDTO == null) {
            throw new NullPointerException("Input should not be null.");
        } else {
            Product product = new Product();
            product.setProductName(inputDTO.getProductName());
            return product;
        }

    }

    public ProductOutputDTO convertToDTO(Product product) {
        return new ProductOutputDTO(
                product.getProductId(),
                product.getProductName()
        );
    }
}
