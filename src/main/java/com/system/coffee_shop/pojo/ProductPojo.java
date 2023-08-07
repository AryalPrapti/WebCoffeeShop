package com.system.coffee_shop.pojo;

import com.system.coffee_shop.entity.Category;
import com.system.coffee_shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPojo {
    private Integer id;

    private String date;
    private String product_name;

    private double product_price;

    private double product_quantity;

    private MultipartFile product_image;

    public ProductPojo(Product product){
        this.id=product.getId();
        this.product_name=product.getProduct_name();
        this.product_price=product.getProduct_price();
        this.product_quantity=product.getProduct_quantity();
//        this.date = String.valueOf(product.getDate());
        this.date = product.getDate();
    }
}
