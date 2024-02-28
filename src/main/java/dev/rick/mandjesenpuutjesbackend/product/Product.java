package dev.rick.mandjesenpuutjesbackend.product;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private long productId;

    @Column(name = "name", nullable = false)
    private String productName;


}
