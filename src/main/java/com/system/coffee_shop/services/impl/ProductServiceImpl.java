package com.system.coffee_shop.services.impl;

import com.system.coffee_shop.entity.Product;
//import com.system.kinmel.entity.Sale;
import com.system.coffee_shop.pojo.ProductPojo;
import com.system.coffee_shop.repo.CartRepo;
import com.system.coffee_shop.repo.ProductRepo;
//import com.system.kinmel.repo.SaleRepo;
import com.system.coffee_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final CartRepo cartRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/product";

    @Override
    public ProductPojo saveProduct(ProductPojo productPojo) throws IOException {
        Product product;
        if (productPojo.getId() != null){
            product = productRepo.findById(productPojo.getId()).orElseThrow(() -> new RuntimeException("Not found"));
        }else {
            product = new Product();
        }
        product.setProduct_name(productPojo.getProduct_name());
        product.setProduct_price(productPojo.getProduct_price());
        product.setProduct_quantity(productPojo.getProduct_quantity());
        product.setDate(productPojo.getDate());

        if(productPojo.getProduct_image()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productPojo.getProduct_image().getOriginalFilename());
            fileNames.append(productPojo.getProduct_image().getOriginalFilename());
            Files.write(fileNameAndPath, productPojo.getProduct_image().getBytes());
            product.setProduct_image(productPojo.getProduct_image().getOriginalFilename());

        }
        productRepo.save(product);
        return new ProductPojo(product);
    }

//
//    @Override
//    public String saveProduct(ProductPojo productPojo) throws IOException {
//        Product product = new Product();
//
//        product.setProduct_name(productPojo.getProduct_name());
//
//        if(!Objects.equals(productPojo.getProduct_image().getOriginalFilename(), "")){
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY+"profile-picture\\", productPojo.getProduct_image().getOriginalFilename());
//            Files.write(fileNameAndPath, productPojo.getProduct_image().getBytes());
//
//            product.setProduct_image(productPojo.getProduct_image().getOriginalFilename());
//        }
//
//        product.setProduct_price(productPojo.getProduct_price());
//        product.setProduct_quantity(productPojo.getProduct_quantity());
//        productRepo.save(product);
//
//        return "DONE";
//    }

    @Override
    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }


    @Override
    public List<Product> fetchAll() {
        return listMapping(productRepo.findAll());
    }

    @Override
    public Product getSingle(Integer id) {
        Product product = productRepo.findById(id).orElseThrow();
        product = Product.builder()
                .id(product.getId())
                .product_quantity(product.getProduct_quantity())
                .product_image(getImageBase64(product.getProduct_image()))
                .product_name(product.getProduct_name())
                .product_price(product.getProduct_price())
                .date(product.getDate())
                .build();
        return product;
    }

//    @Override
//    public List<Product> fetchByCategory(Integer id) {
//        return listMapping(productRepo.findByProduct_category(id));
//    }

    @Override
    public List<Product> fetchNew() {
        return listMapping(productRepo.findNew());
    }

    @Override
    public List<Product> trending() {
        List<Product> trendingItems = new ArrayList<>();
        for (Integer i : cartRepo.fetchTrending().orElseThrow()){
            trendingItems.add(productRepo.findById(i).orElseThrow());
        }
        return listMapping(trendingItems);
    }

    @Override
    public List<Product> mostPopular() {
        List<Product> mostPopular = new ArrayList<>();
        for (Integer i : cartRepo.most().orElseThrow()){
            mostPopular.add(productRepo.findById(i).orElseThrow());
        }
        return listMapping(mostPopular);
    }

    @Override
    public List<Product> bestSeller() {
        List<Product> seller = new ArrayList<>();
        for (Integer i : cartRepo.best().orElseThrow()){
            seller.add(productRepo.findById(i).orElseThrow());
        }
        return listMapping(seller);
    }

    @Override
    public Map<Integer, Double> comparePrice(List<Product> products) {
        return null;
    }

    public List<Product> listMapping
            (List<Product> list){
        Stream<Product> allProductsWithImage=list.stream().map(product ->
                Product.builder()
                        .id(product.getId())
                        .product_quantity(product.getProduct_quantity())
                        .product_image(getImageBase64(product.getProduct_image()))
                        .product_name(product.getProduct_name())
                        .product_price(product.getProduct_price())
                        .date(product.getDate())
                        .product_imageBase64(getImageBase64(product.getProduct_image()))
                        .build()
        );
        list = allProductsWithImage.toList();
        return new ArrayList<>(list);
    }

//    public Map<Integer, Double> comparePrice(List<Product> products){
//        List<Sale> comparePrice = saleRepo.saleProducts();
//        Map<Integer, Double> priceDiscount = new HashMap<>();
//
//        for (Product product : products) {
//            for (Sale sale : comparePrice) {
//                if (Objects.equals(product.getId(), sale.getProduct().getId())) {
//                    priceDiscount.put(product.getId(), sale.getDiscountPercent());
//                }
//            }
//        }
//        return priceDiscount;
//    }

    public static String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir") + "/product/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
}
